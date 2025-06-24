package tela;

import javax.swing.*;
import java.awt.Font;
import controller.ImovelController; 

public class TelaCadastroImoveis extends JFrame {
    private JTextField textFieldEndereco, textFieldValor, textFieldInformacoes;
    private JCheckBox checkCasa, checkApartamento;
    private String cpfUsuario;

    private ImovelController controller = new ImovelController();

    public TelaCadastroImoveis(String cpf) {
        this.cpfUsuario = cpf;

        setTitle("Cadastro de Imóveis");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEndereco.setBounds(10, 33, 80, 20);
        getContentPane().add(lblEndereco);

        textFieldEndereco = new JTextField();
        textFieldEndereco.setBounds(110, 35, 282, 20);
        getContentPane().add(textFieldEndereco);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTipo.setBounds(10, 64, 80, 20);
        getContentPane().add(lblTipo);

        checkCasa = new JCheckBox("Casa");
        checkCasa.setBounds(110, 66, 72, 20);
        getContentPane().add(checkCasa);

        checkApartamento = new JCheckBox("Apartamento");
        checkApartamento.setBounds(184, 66, 120, 20);
        getContentPane().add(checkApartamento);

        checkCasa.addActionListener(e -> {
            if (checkCasa.isSelected()) {
                checkApartamento.setSelected(false);
            }
        });
        checkApartamento.addActionListener(e -> {
            if (checkApartamento.isSelected()) {
                checkCasa.setSelected(false);
            }
        });

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblValor.setBounds(10, 95, 80, 20);
        getContentPane().add(lblValor);

        textFieldValor = new JTextField();
        textFieldValor.setBounds(110, 97, 100, 20);
        getContentPane().add(textFieldValor);

        JLabel lblInfo = new JLabel("Informações:");
        lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblInfo.setBounds(10, 126, 100, 20);
        getContentPane().add(lblInfo);

        textFieldInformacoes = new JTextField();
        textFieldInformacoes.setBounds(110, 128, 282, 20);
        getContentPane().add(textFieldInformacoes);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(248, 200, 107, 37);
        getContentPane().add(btnSalvar);
        btnSalvar.addActionListener(e -> salvarImovel());

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(65, 200, 107, 37);
        getContentPane().add(btnLimpar);
        btnLimpar.addActionListener(e -> limparCampos());

        setVisible(true);
    }

    private void salvarImovel() {
        String endereco = textFieldEndereco.getText().trim();
        String tipo = checkCasa.isSelected() ? "Casa" : checkApartamento.isSelected() ? "Apartamento" : "";
        String valorStr = textFieldValor.getText().trim();
        String informacoes = textFieldInformacoes.getText().trim();

        if (endereco.isEmpty() || tipo.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean salvou = controller.salvarImovel(endereco, tipo, valorStr, informacoes, this.cpfUsuario);

        if (salvou) {
            JOptionPane.showMessageDialog(this, "Imóvel salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar o imóvel.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        textFieldEndereco.setText("");
        textFieldValor.setText("");
        textFieldInformacoes.setText("");
        checkCasa.setSelected(false);
        checkApartamento.setSelected(false);
    }
}
