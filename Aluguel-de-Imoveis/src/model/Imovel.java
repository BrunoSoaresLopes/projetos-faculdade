package model;

public class Imovel {
    private int id;
    private String endereco;
    private String tipo;
    private double valor;
    private String informacoes;
    private String telefoneProprietario;
    private String emailProprietario;
    
    public Imovel(int id, String endereco, String tipo, double valor, String informacoes) {
        this.id = id;
        this.endereco = endereco;
        this.tipo = tipo;
        this.valor = valor;
        this.informacoes = informacoes;
    }
    
    public Imovel(int id, String endereco, String tipo, double valor, String informacoes, String telefoneProprietario, String emailProprietario) {
        this(id, endereco, tipo, valor, informacoes);
        this.telefoneProprietario = telefoneProprietario;
        this.emailProprietario = emailProprietario;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

	public String getTelefoneProprietario() {
		return telefoneProprietario;
	}

	public void setTelefoneProprietario(String telefoneProprietario) {
		this.telefoneProprietario = telefoneProprietario;
	}

	public String getEmailProprietario() {
		return emailProprietario;
	}

	public void setEmailProprietario(String emailProprietario) {
		this.emailProprietario = emailProprietario;
	}
    
    
}

