package controller;

import model.Imovel;
import model.ImovelDAO;

public class ImovelController {
    private ImovelDAO dao = new ImovelDAO();

    public boolean salvarImovel(String endereco, String tipo, String valorStr, String informacoes, String cpfProprietario) {
        if (endereco.isEmpty() || tipo.isEmpty() || valorStr.isEmpty()) {
            System.err.println("Controller: Campos obrigatórios não preenchidos.");
            return false;
        }

        try {
            double valor = Double.parseDouble(valorStr);

            if (valor < 0) {
                System.err.println("Controller: Valor do imóvel não pode ser negativo.");
                return false;
            }

            Imovel novoImovel = new Imovel(0, endereco, tipo, valor, informacoes);
            
            return dao.salvarImovel(novoImovel, cpfProprietario);

        } catch (NumberFormatException e) {
            System.err.println("Controller: Valor inválido fornecido.");
            return false;
        }
    }

    public boolean atualizarImovel(int id, String endereco, String tipo, double valor, String informacoes) {
        Imovel imovelAtualizado = new Imovel(id, endereco, tipo, valor, informacoes);
        return dao.atualizarImovel(imovelAtualizado);
    }
    
     public boolean excluirImovel(int id) {
        return dao.excluirImovel(id);
     }
}