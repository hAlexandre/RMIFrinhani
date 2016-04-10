package user.CONTROL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import user.VIEW.CanaisView;
import user.VIEW.ChatView;

public class CanaisControl implements ActionListener{
	
	private CanaisView view;
	
	public CanaisControl(CanaisView c)
	{
		view = c;
	}
	
//	Controlar entrada de canais
	
	public void actionPerformed(ActionEvent e)
	{			
		String str = e.getActionCommand().replace(" ", "");
		try {
			view.fechar();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ChatView chatView = new ChatView();	
		ChatControl chat = new ChatControl(chatView, str);
		try {
			chat.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}