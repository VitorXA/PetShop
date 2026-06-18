package petShops;

public class Cliente {
	private String nome;
	private int telefone;
	

	public void exibirInfo() {
		System.out.println("Nome: " + nome);
		System.out.println("Telefone: " + telefone);
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}


	public String getNome() {
		return nome;
	}


	public int getTelefone() {
		return telefone;
	}


}