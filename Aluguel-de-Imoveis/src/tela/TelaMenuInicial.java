package tela;

import javax.swing.*;
import java.awt.Font;

public class TelaMenuInicial extends JFrame {
	public TelaMenuInicial() {
		setTitle("Menu Inicial");
		setBounds(100, 100, 350, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblTitulo = new JLabel("Selecione uma opção:");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(76, 87, 200, 20);
		getContentPane().add(lblTitulo);

		JButton btnLogin = new JButton("Fazer login");
		btnLogin.setBounds(24, 140, 120, 36);
		getContentPane().add(btnLogin);
		btnLogin.addActionListener(e -> {
			dispose();
			new TelaLoginUsuario(); 
		});

		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(182, 140, 120, 36);
		getContentPane().add(btnCadastro);
		
		JLabel lblNewLabel = new JLabel("Bem vindo!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(24, 29, 288, 20);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Aqui você encontra os melhores imóveis!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(24, 49, 300, 27);
		getContentPane().add(lblNewLabel_1);
		btnCadastro.addActionListener(e -> {
			dispose();
			new TelaCadastroUsuario();
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new TelaMenuInicial();
	}
}
