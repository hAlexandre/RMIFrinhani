package user.CONTROL;

import org.jgroups.View;

import user.VIEW.ChatView;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ChatView chatView = new ChatView();
		System.out.println("OI");
		new ChatControl(chatView).start();		
				
	}

}
