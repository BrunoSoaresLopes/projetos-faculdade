package tela;

import javax.swing.*;
import controller.UsuarioController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaLoginUsuario extends JFrame {
    private JPasswordField txtSenha;
    private JTextField txtCpf;
    private UsuarioController controller = new UsuarioController();

    public TelaLoginUsuario() {
        setTitle("Login de Usuário");
        setBounds(100, 100, 350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCpf.setBounds(20, 41, 80, 20);
        getContentPane().add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(87, 43, 200, 20);
        getContentPane().add(txtCpf);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSenha.setBounds(20, 84, 80, 20);
        getContentPane().add(lblSenha);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(87, 86, 200, 20);
        getContentPane().add(txtSenha);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(180, 153, 120, 30);
        getContentPane().add(btnLogin);
        btnLogin.addActionListener(e -> validarLogin());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 153, 120, 30);
        getContentPane().add(btnVoltar);
        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaMenuInicial();
        });

        setVisible(true);
    }

    private void validarLogin() {
        String cpf = txtCpf.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF e Senha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (controller.autenticar(cpf, senha)) {
            dispose();
            new TelaMenuPrincipal(cpf);
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou Senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}