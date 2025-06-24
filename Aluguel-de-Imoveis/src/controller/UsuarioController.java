package controller;

import model.Usuario;
import model.UsuarioDAO;

public class UsuarioController {
    UsuarioDAO dao = new UsuarioDAO();
    
    public void salvar(String nome, String telefone, String email, String cpf, String senha) {
        Usuario usuario = new Usuario(nome, telefone, email, cpf, senha);
        dao.salvar(usuario);
    }
    
    public boolean autenticar(String cpf, String senha) {
        if (cpf.isEmpty() || senha.isEmpty()) {
            return false;
        }
        return dao.validarLogin(cpf, senha);
    }
    
    public boolean validarCPF(String cpf) {
        return dao.verificarExistenciaCPF(cpf);
    }
    
    public Usuario obterDadosUsuario(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }
        return dao.buscarUsuarioPorCpf(cpf);
    }
}
