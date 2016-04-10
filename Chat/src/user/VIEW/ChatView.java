package user.VIEW;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class ChatView {

	public JFrame janela;
	public JTextArea mensagem, historico, usuarios ;
	public JScrollPane scrollPane;
	public JButton enviar;
	public JCheckBox chckbxPressionarEnterPara;
	private JLabel lblUsuriosConectados;
	private JScrollPane scrollPane_2;
	public JLabel canal;
	
	public ChatView()
	{
		janela = new JFrame("Chat");
		
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.getContentPane().setLayout(null);
		janela.setSize(800, 600);
		mensagem = new JTextArea();		
				
		scrollPane = new JScrollPane(mensagem);		
		scrollPane.setVisible(true);
		scrollPane.setEnabled(true);
		janela.getContentPane().add(scrollPane);
		scrollPane.setBounds(198,486,477,38);				
				
		mensagem.setRows(1);
		mensagem.setLineWrap(true);
		mensagem.setBounds(0,0,100,70);
		
		enviar = new JButton("Enviar");
		
		janela.getContentPane().add(enviar);
		enviar.setBounds(685,486,89,38);
		
		lblUsuriosConectados = new JLabel("Usu�rios Conectados");
		lblUsuriosConectados.setForeground(Color.BLUE);
		lblUsuriosConectados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsuriosConectados.setBounds(24, 14, 118, 20);				
		janela.getContentPane().add(lblUsuriosConectados);
		

		usuarios = new JTextArea();
		usuarios.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(usuarios);
		scrollPane_1.setBounds(10, 45, 164, 479);
		janela.getContentPane().add(scrollPane_1);
				
		historico = new JTextArea();
		scrollPane_2 = new JScrollPane(historico);
		historico.setEditable(false);
		scrollPane_2.setBounds(198, 45, 576, 406);
		janela.getContentPane().add(scrollPane_2);
		
		chckbxPressionarEnterPara = new JCheckBox("Pressionar enter para enviar");		
		chckbxPressionarEnterPara.setBounds(208, 531, 201, 23);
		janela.getContentPane().add(chckbxPressionarEnterPara);
		chckbxPressionarEnterPara.setSelected(true);
		
		canal = new JLabel("New label");
		canal.setBounds(286, 17, 144, 14);
		janela.getContentPane().add(canal);
		janela.repaint();

	}
}
