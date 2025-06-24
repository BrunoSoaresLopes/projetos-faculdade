package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO {

   public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (Nome, Telefone, Email, CPF, Senha) VALUES (?, ?, ?, ?, ?)";
        Conexao.conectar();
        try (Connection conn = Conexao.conexao; 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getSenha());

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
            Conexao.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar(); 
        }
    }
  
   public boolean validarLogin(String cpf, String senha) {
	    String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ? AND senha = ?";
	    Conexao.conectar();
	    try (Connection conn = Conexao.conexao;
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, cpf);
	        stmt.setString(2, senha);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexao.desconectar();
	    }
	    return false;
	}
   
    public boolean verificarExistenciaCPF(String cpf) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
        Conexao.conectar();
        try (Connection conn = Conexao.conexao;
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar(); 
        }
        return false;
    }
    
    public Usuario buscarUsuarioPorCpf(String cpf) {
        String sql = "SELECT nome, telefone, email, cpf, senha FROM usuario WHERE cpf = ?";
        Usuario usuarioEncontrado = null;
        Conexao.conectar();
        try (Connection conn = Conexao.conexao;
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario(
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por CPF: " + e.getMessage());
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return usuarioEncontrado;
    }
}