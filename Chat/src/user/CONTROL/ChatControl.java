package user.CONTROL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.jmx.JmxConfigurator;
import org.jgroups.protocols.MPING;
import org.jgroups.protocols.TP;
import org.jgroups.stack.ProtocolStack;
import org.jgroups.util.Util;

import user.VIEW.ChatView;

public class ChatControl extends ReceiverAdapter{
	public static String str1 = new String("192.168.10.101");
	JChannel channel;	
	String canal;
	ChatView chat;
	int controleShift = 0, controleEnter=0;
	final List<String> state = new LinkedList<String>();
	List<Address> usuarios;
	Address id;
	
	
	public void viewAccepted(View new_view) {					        
	        usuarios = new_view.getMembers();	        
	        atualizaUsuarios();
    }
	
	public void receive(Message msg) {
        String line=msg.getSrc() + ": " + msg.getObject();        
        chat.historico.append(line+"\n");
        synchronized(state) {
            state.add(line);
        }
    }
	

	
	
	void start() throws Exception {	
		String str = System.getProperty("user.dir");
		str = str.concat(new String("\\udp.xml"));									
        channel=new JChannel();   
        channel.setReceiver(this);   
        channel.connect(canal);           
        channel.getProtocolStack().findProtocol(TP.class).setValue("bind_addr",InetAddress.getByName("192.168.0.103") );
        
        chat.canal.setText(canal);      
        atualizaUsuarios();
        id = usuarios.get(usuarios.size()-1);        
    }
	
	
	 void eventLoop() {        
        while(true) {
            
        }
    }
	
	public ChatControl(ChatView view, String s)
	{
		this.chat = view;		
		this.canal = s;
		view.enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {										
				if(view.mensagem.getText().equals("\n")||view.mensagem.getText().trim().equals(""))
				{
					view.mensagem.setText("");
					view.mensagem.grabFocus();
				}
					
				else
				{
					enviar(view.mensagem.getText());
					view.mensagem.setText("");
					view.mensagem.grabFocus();
				}
				
			}
		});
		
		view.mensagem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
//				mensagem normal com enter
				if(view.chckbxPressionarEnterPara.isSelected() && controleShift==0)
				{					
					if(arg0.getKeyCode()==10)
					{	
						String str = view.mensagem.getText();
						if(str.equals("\n"))
						{
							view.mensagem.setText("");							
						}
						else
						{
							view.enviar.grabFocus();					
							view.mensagem.setText("");					
							view.mensagem.grabFocus();	
							enviar(str);							
						}
					}
				}
//				Verificação shift+enter
				if(arg0.getKeyCode()==16)				
					controleShift = 0;
				
			}
						
			@Override
			public void keyPressed(KeyEvent arg1) {
//				Verificação shift+enter
				if(view.chckbxPressionarEnterPara.isSelected())
				{
					if(arg1.getKeyCode()==16)										
						controleShift = 1;					
				}
				if(arg1.getKeyCode()==10 && controleShift==1)
				{
					view.mensagem.setText(view.mensagem.getText()+"\n");
				}
			}
		});
	}
	
	private void atualizaUsuarios()
	{		
		chat.usuarios.setText("");
		ArrayList<String> aux = new ArrayList<>();	
		for(Address u : usuarios)
		{			
			aux.add(u.toString());
		}
		aux.sort(null);
		for(String s : aux)
		{
			chat.usuarios.append(s+"\n");
		}
	}
	
	private void enviar(String str)
	{
		Message msg=new Message(null, null,str.trim());
        try {
			channel.send(msg);
			
		} catch (Exception e) {
			e.printStackTrace();
		}			        
	}
}
