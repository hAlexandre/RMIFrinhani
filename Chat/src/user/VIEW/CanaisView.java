package user.VIEW;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;import java.util.Locale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import user.CONTROL.CanaisControl;

import javax.swing.JInternalFrame;

public class CanaisView {		
	
	public JFrame janela;
	public JScrollPane scroll;
	JPanel panel;
	public JButton btnAtualizar;
	int k = 0;
	private CanaisControl canais;
	
	public CanaisView()
	{
		janela = new JFrame("Canais de chat");
		janela.setVisible(true);
		janela.setSize(700, 600);
		janela.getContentPane().setLayout(null);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(269, 11, 89, 23);
		janela.getContentPane().add(btnAtualizar);		
		
		
		panel = new JPanel();		
		panel.setLocation(100, 100);

		panel.setVisible(true);
		panel.setBounds(0,0,150,95);
		 
		
		panel.setLayout(null);
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBounds(100,50,400,400);
		janela.getContentPane().add(scroll);
		canais = new CanaisControl(this);
		for(int i = 1 ; i <= 10 ; i++)
		{			
			criaBotao(new JButton("Entrar canal "+i));			
			panel.setBounds(0, 0, 100, k);			
		}
		
		
		
	}
	
	private void criaBotao(JButton botao)
	{				
		panel.add(botao);		
		botao.addActionListener(canais);
		botao.setBounds(0,k,390,30);		
		k+=35;
	}
	
	public void fechar() throws Throwable
	{
		janela.dispose();
	}
}
