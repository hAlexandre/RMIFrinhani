package user.CONTROL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import user.VIEW.ChatView;

public class ChatControl extends ReceiverAdapter{
	
	JChannel channel;
	ArrayList<String> usuarios;
	ChatView view;
	int controleShift = 0, controleEnter=0;
	final List<String> state=new LinkedList<String>();
	

	
	public void viewAccepted(View new_view) {
	        System.out.println("** view: " + new_view);
    }
	
	public void receive(Message msg) {
        String line=msg.getSrc() + ": " + msg.getObject();
        System.out.println(line);
        view.historico.append(line+"\n");
        synchronized(state) {
            state.add(line);
        }
    }
	
	public byte[] getState() {
        synchronized(state) {
            try {
                return Util.objectToByteBuffer(state);
            }
            catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
	
	public void setState(byte[] new_state) {
        try {
            @SuppressWarnings("unchecked")
			List<String> list=(List<String>)Util.objectFromByteBuffer(new_state);
            synchronized(state) {
                state.clear();
                state.addAll(list);
            }
            System.out.println("received state (" + list.size() + " messages in chat history):");
            for(String str: list) {
                System.out.println(str);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	void start() throws Exception {		
        channel=new JChannel();
        channel.setReceiver(this);
        channel.connect("ChatCluster");
        channel.getState(null, 10000);
        atualizaUsuarios();
        eventLoop();
        channel.close();
    }
	
	
	private void eventLoop() {        
        while(true) {
            
        }
    }
	
	public ChatControl(ChatView view)
	{
		this.view = view;		
		view.enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {										
				view.historico.append("Usu�rio x diz:\n"+view.mensagem.getText()+"\n");
				view.mensagem.setText("");
				view.mensagem.grabFocus();
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
//							view.historico.append("Usu�rio x diz:\n"+str.trim());
//							view.historico.append("\n");
							view.enviar.grabFocus();					
							view.mensagem.setText("");					
							view.mensagem.grabFocus();
							Message msg=new Message(null, null,str.trim());
			                try {
								channel.send(msg);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						}
					}
				}
//				Verifica��o shift+enter
				if(arg0.getKeyCode()==16)				
					controleShift = 0;
				
			}
			
			
			@Override
			public void keyPressed(KeyEvent arg1) {
//				Verifica��o shift+enter
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
	
	public void atualizaUsuarios()
	{
		usuarios = new ArrayList<>();
		usuarios.add("Usuario x");
		usuarios.sort(null);
		for(String user : usuarios)
		{
			view.usuarios.append(user+"\n");
		}
	}
	
}
