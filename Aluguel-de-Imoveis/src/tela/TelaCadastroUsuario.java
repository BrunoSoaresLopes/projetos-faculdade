package tela;

import javax.swing.*;
import controller.UsuarioController;
import java.awt.Font;
import java.util.Arrays;

public class TelaCadastroUsuario extends JFrame {
    private JTextField txtNome, txtTelefone, txtEmail, txtCpf;
    private JPasswordField txtSenha, txtConfirmaSenha;
    
    private UsuarioController controller = new UsuarioController();

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setBounds(100, 100, 400, 324);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNome.setBounds(40, 27, 100, 20);
        getContentPane().add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(160, 26, 200, 20);
        getContentPane().add(txtNome);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTelefone.setBounds(40, 58, 100, 20);
        getContentPane().add(lblTelefone);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(160, 60, 200, 20);
        getContentPane().add(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(40, 89, 100, 20);
        getContentPane().add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(160, 91, 200, 20);
        getContentPane().add(txtEmail);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCpf.setBounds(40, 120, 100, 20);
        getContentPane().add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(160, 122, 200, 20);
        getContentPane().add(txtCpf);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSenha.setBounds(40, 151, 100, 20);
        getContentPane().add(lblSenha);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(160, 153, 200, 20);
        getContentPane().add(txtSenha);

        JLabel lblConfirmaSenha = new JLabel("Confirmar Senha:");
        lblConfirmaSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblConfirmaSenha.setBounds(40, 182, 125, 20);
        getContentPane().add(lblConfirmaSenha);
        txtConfirmaSenha = new JPasswordField();
        txtConfirmaSenha.setBounds(160, 184, 200, 20);
        getContentPane().add(txtConfirmaSenha);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(221, 242, 120, 30);
        getContentPane().add(btnCadastrar);
        btnCadastrar.addActionListener(e -> validarCadastro());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(40, 242, 120, 30);
        getContentPane().add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaMenuInicial();
        });
        
        setVisible(true);
    }

    private void validarCadastro() {
        String nome = txtNome.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        String cpf = txtCpf.getText().trim();
        char[] senhaChars = txtSenha.getPassword();
        char[] confirmaSenhaChars = txtConfirmaSenha.getPassword();

        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cpf.isEmpty() || senhaChars.length == 0) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Arrays.equals(senhaChars, confirmaSenhaChars)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!cpf.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this, "CPF inválido! Deve conter exatamente 11 dígitos numéricos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Formato de e-mail inválido!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String senha = new String(senhaChars);
        
        controller.salvar(nome, telefone, email, cpf, senha);
        
        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso! Faça o login.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new TelaLoginUsuario();
    }
}