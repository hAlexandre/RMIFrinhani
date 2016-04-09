package user.CONTROL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import user.VIEW.ChatView;

public class ChatControl {
	
	ArrayList<String> usuarios;
	ChatView view;
	int controleShift = 0, controleEnter=0;
	
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
							view.historico.append("Usu�rio x diz:\n"+str.trim());
							view.historico.append("\n");
							view.enviar.grabFocus();					
							view.mensagem.setText("");					
							view.mensagem.grabFocus();
					
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