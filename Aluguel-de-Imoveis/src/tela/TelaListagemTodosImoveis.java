package tela;

import javax.swing.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import model.Conexao;
import model.Imovel;
import model.ImovelDAO;
import model.UsuarioDAO;

public class TelaListagemTodosImoveis extends JFrame {
	private String cpfUsuario;
	private JTable tabelaImoveis;
	private JTextField txtMinValor, txtMaxValor;

	public TelaListagemTodosImoveis() {
		setTitle("Listagem de Imóveis");
		setBounds(100, 100, 800, 450); 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblMinValor = new JLabel("Valor mínimo:");
		lblMinValor.setBounds(20, 20, 100, 20);
		getContentPane().add(lblMinValor);

		txtMinValor = new JTextField();
		txtMinValor.setBounds(120, 20, 80, 20);
		getContentPane().add(txtMinValor);

		JLabel lblMaxValor = new JLabel("Valor máximo:");
		lblMaxValor.setBounds(220, 20, 100, 20);
		getContentPane().add(lblMaxValor);

		txtMaxValor = new JTextField();
		txtMaxValor.setBounds(320, 20, 80, 20);
		getContentPane().add(txtMaxValor);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(420, 20, 100, 25);
		getContentPane().add(btnFiltrar);
		btnFiltrar.addActionListener(e -> filtrarImoveis());

		carregarImoveisDoUsuario(-1, -1);
		
		setVisible(true);
	}

	private void carregarImoveisDoUsuario(double minValor, double maxValor) {
		ImovelDAO ImovelDAO = new ImovelDAO();
		List<Imovel> listaImoveis = (cpfUsuario == null) 
				? ImovelDAO.buscarTodosImoveis() 
						: ImovelDAO.buscarImoveisPorCpf(cpfUsuario);

		String[] colunas = {"Endereço", "Tipo", "Valor", "Informações", "Telefone", "Email"};
		DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

		for (Imovel imovel : listaImoveis) {
			if ((minValor == -1 || imovel.getValor() >= minValor) && (maxValor == -1 || imovel.getValor() <= maxValor)) {
				Object[] linha = {
						imovel.getEndereco(),
						imovel.getTipo(),
						imovel.getValor(),
						imovel.getInformacoes(),
						imovel.getTelefoneProprietario(),
						imovel.getEmailProprietario()    
				};
				modelo.addRow(linha);
			}
		}

		tabelaImoveis = new JTable(modelo);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
		tabelaImoveis.setRowSorter(sorter);

		JScrollPane scrollPane = new JScrollPane(tabelaImoveis);
		scrollPane.setBounds(10, 60, 760, 280);
		getContentPane().add(scrollPane);
	}

	private void filtrarImoveis() {
	    double minValor, maxValor;

	    try {
	        minValor = txtMinValor.getText().isEmpty() ? Double.MIN_VALUE : Double.parseDouble(txtMinValor.getText());
	        maxValor = txtMaxValor.getText().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(txtMaxValor.getText());
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Insira valores numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel modelo = (DefaultTableModel) tabelaImoveis.getModel();
	    modelo.setRowCount(0);
	    ImovelDAO ImovelDAO = new ImovelDAO();
	    List<Imovel> listaImoveis = (cpfUsuario == null) ? ImovelDAO.buscarTodosImoveis() : ImovelDAO.buscarImoveisPorCpf(cpfUsuario);

	    for (Imovel imovel : listaImoveis) {
	        if ((imovel.getValor() >= minValor) && (imovel.getValor() <= maxValor)) {
	            Object[] linha = {
	                imovel.getEndereco(),
	                imovel.getTipo(),
	                imovel.getValor(),
	                imovel.getInformacoes(),
	                imovel.getTelefoneProprietario(),
	                imovel.getEmailProprietario()
	            };
	            modelo.addRow(linha);
	        }
	    }
	    revalidate();
	    repaint();
	}
}