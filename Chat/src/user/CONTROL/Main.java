package user.CONTROL;

import user.VIEW.ChatView;

public class Main {

	public static void main(String[] args) {
		ChatView chatView = new ChatView();
		ChatControl chat = new ChatControl(chatView);
		chat.atualizaUsuarios();
	}

}
