package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ImovelDAO {
	
	public List<Imovel> buscarImoveisPorCpf(String cpf) {
        List<Imovel> listaImoveis = new ArrayList<>();
        String sql = "SELECT i.id, i.endereco, i.tipo, i.valor, i.informacoes, u.Telefone, u.Email " +
                     "FROM imoveis AS i " +
                     "JOIN usuario AS u ON i.cpf_usuario = u.CPF " +
                     "WHERE i.cpf_usuario = ?";
        Conexao.conectar();
        try (Connection conn = Conexao.conexao;
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Imovel imovel = new Imovel(
                        rs.getInt("id"),
                        rs.getString("endereco"),
                        rs.getString("tipo"),
                        rs.getDouble("valor"),
                        rs.getString("informacoes"),
                        rs.getString("telefone"),
                        rs.getString("email")   
                    );
                    listaImoveis.add(imovel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return listaImoveis;
    }

    public List<Imovel> buscarTodosImoveis() {
        List<Imovel> listaImoveis = new ArrayList<>();
        String sql = "SELECT i.id, i.endereco, i.tipo, i.valor, i.informacoes, u.Telefone, u.Email " +
                     "FROM imoveis AS i " +
                     "JOIN usuario AS u ON i.cpf_usuario = u.CPF";

        Conexao.conectar();
        try (Connection conn = Conexao.conexao;
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Imovel imovel = new Imovel(
                    rs.getInt("id"),
                    rs.getString("endereco"),
                    rs.getString("tipo"),
                    rs.getDouble("valor"),
                    rs.getString("informacoes"),
                    rs.getString("telefone"),
                    rs.getString("email")   
                );
                listaImoveis.add(imovel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return listaImoveis;
    }

    public boolean salvarImovel(Imovel imovel, String cpfUsuario) {
        String sql = "INSERT INTO imoveis (endereco, tipo, valor, informacoes, cpf_usuario) VALUES (?, ?, ?, ?, ?)";
        Conexao.conectar();
        try (Connection conn = Conexao.conexao;
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, imovel.getEndereco());
            stmt.setString(2, imovel.getTipo());
            stmt.setDouble(3, imovel.getValor());
            stmt.setString(4, imovel.getInformacoes());
            stmt.setString(5, cpfUsuario);
            
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar imóvel" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; 
        } finally {
            Conexao.desconectar();
        }
    }

    public boolean atualizarImovel(Imovel imovel) {
        String sql = "UPDATE imoveis SET endereco = ?, tipo = ?, valor = ?, informacoes = ? WHERE id = ?";
        Conexao.conectar();
        try (Connection conn = Conexao.conexao;
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, imovel.getEndereco());
            stmt.setString(2, imovel.getTipo());
            stmt.setDouble(3, imovel.getValor());
            stmt.setString(4, imovel.getInformacoes());
            stmt.setInt(5, imovel.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar imóvel " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return false; 
        } finally {
            Conexao.desconectar();
        }
    }

    public boolean excluirImovel(int idImovel) {
        int resposta = JOptionPane.showConfirmDialog(null, 
            "Tem certeza que deseja excluir este imóvel?", "Confirmação", 
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (resposta == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM imoveis WHERE id = ?";
            Conexao.conectar();
            try (Connection conn = Conexao.conexao;
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, idImovel);
                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir imóvel: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            } finally {
                Conexao.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Exclusão cancelada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

}
