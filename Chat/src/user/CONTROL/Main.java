package user.CONTROL;

import org.jgroups.View;

import user.VIEW.CanaisView;
import user.VIEW.ChatView;

public class Main {

	public static void main(String[] args) throws Exception {				
		System.setProperty("java.net.preferIPv4Stack" , "true");
		CanaisView canais = new CanaisView();			
	}

}
