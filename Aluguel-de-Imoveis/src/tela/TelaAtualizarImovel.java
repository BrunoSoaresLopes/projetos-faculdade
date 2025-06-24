package tela;

import javax.swing.*;
import java.awt.Font;
import model.Imovel;
import controller.ImovelController; 

public class TelaAtualizarImovel extends JFrame {
    private JTextField textFieldEndereco;
    private JTextField textFieldValor;
    private JCheckBox checkCasa;
    private JCheckBox checkApartamento;
    private JTextField textFieldInformacoes;
    private int idImovel;
    
    private ImovelController controller = new ImovelController();

    public TelaAtualizarImovel(Imovel imovel) {
        this.idImovel = imovel.getId();

        setTitle("Atualizar Imóvel");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEndereco.setBounds(10, 33, 80, 20);
        getContentPane().add(lblEndereco);

        textFieldEndereco = new JTextField(imovel.getEndereco());
        textFieldEndereco.setBounds(110, 35, 282, 20);
        getContentPane().add(textFieldEndereco);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTipo.setBounds(10, 64, 80, 20);
        getContentPane().add(lblTipo);

        checkCasa = new JCheckBox("Casa");
        checkCasa.setBounds(110, 66, 72, 20);
        checkCasa.setSelected(imovel.getTipo().equals("Casa"));
        getContentPane().add(checkCasa);

        checkApartamento = new JCheckBox("Apartamento");
        checkApartamento.setBounds(184, 66, 120, 20);
        checkApartamento.setSelected(imovel.getTipo().equals("Apartamento"));
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

        textFieldValor = new JTextField(String.valueOf(imovel.getValor()));
        textFieldValor.setBounds(110, 97, 100, 20);
        getContentPane().add(textFieldValor);

        JLabel lblInfo = new JLabel("Informações:");
        lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblInfo.setBounds(10, 126, 100, 20);
        getContentPane().add(lblInfo);

        textFieldInformacoes = new JTextField(imovel.getInformacoes());
        textFieldInformacoes.setBounds(110, 128, 282, 20);
        getContentPane().add(textFieldInformacoes);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(154, 198, 120, 38);
        getContentPane().add(btnAtualizar);
        btnAtualizar.addActionListener(e -> atualizarImovel());

        setVisible(true);
    }

    private void atualizarImovel() {
        String endereco = textFieldEndereco.getText().trim();
        String tipo = checkCasa.isSelected() ? "Casa" : "Apartamento";
        String informacoes = textFieldInformacoes.getText().trim();
        double valor;

        try {
            valor = Double.parseDouble(textFieldValor.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite um valor numérico válido!", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean atualizou = controller.atualizarImovel(this.idImovel, endereco, tipo, valor, informacoes);

        if (atualizou) {
            JOptionPane.showMessageDialog(this, "Imóvel atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar imóvel!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}