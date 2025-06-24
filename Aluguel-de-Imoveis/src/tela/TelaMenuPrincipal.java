package tela;

import javax.swing.*;
import controller.UsuarioController;
import model.Usuario;
import java.awt.Font;

public class TelaMenuPrincipal extends JFrame {
	private String cpfUsuario;

	public TelaMenuPrincipal(String cpf) {
		this.cpfUsuario = cpf;
		
		UsuarioController controller = new UsuarioController();
        Usuario usuarioLogado = controller.obterDadosUsuario(cpf);
        String nomeUsuario = "Usuário";
        if (usuarioLogado != null) {
            nomeUsuario = usuarioLogado.getNome();
        }
        
		setTitle("Menu Principal");
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNome = new JLabel("Bem-vindo(a), " + nomeUsuario + "!");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNome.setBounds(72, 30, 284, 20);
		getContentPane().add(lblNome);

		JLabel lblTitulo = new JLabel("Selecione uma opção:");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setBounds(72, 61, 200, 30);
		getContentPane().add(lblTitulo);

		JButton btnCadastrarImovel = new JButton("Cadastrar seu imóvel");
		btnCadastrarImovel.setBounds(100, 111, 200, 30);
		getContentPane().add(btnCadastrarImovel);
		btnCadastrarImovel.addActionListener(e -> abrirCadastroImoveis());

		JButton btnAtualizarImovel = new JButton("Atualizar seu imóvel");
		btnAtualizarImovel.setBounds(100, 152, 200, 30);
		getContentPane().add(btnAtualizarImovel);
		btnAtualizarImovel.addActionListener(e -> abrirAtualizarImoveis());

		JButton btnVerImoveis = new JButton("Ver imóveis disponíveis");
		btnVerImoveis.setBounds(100, 193, 200, 30);
		getContentPane().add(btnVerImoveis);
		btnVerImoveis.addActionListener(e -> abrirListagemImoveis());

		setVisible(true);
	}

	private void abrirCadastroImoveis() {
		new TelaCadastroImoveis(cpfUsuario);
	}

	private void abrirAtualizarImoveis() {
		new TelaListagemImoveis(cpfUsuario);
	}

	private void abrirListagemImoveis() {
		new TelaListagemTodosImoveis();
	}
}
