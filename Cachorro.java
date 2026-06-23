package petShops;

public class Cachorro extends Animais implements AtendivelNoEstetica {
	public Cachorro(String nome, String raca, int idade, Cliente dono) {
		super(nome, raca, idade, dono);
	}
	
	@Override
	public void darBanho() {
		System.out.println("Seu cachorro acabou de tomar banho!");
	}
	
	@Override
	public void cortarUnhas() {
		System.out.println("Unhas cortadas com sucesso!");
	}
	
	@Override
	public void emitirSom() {
		System.out.println("O seu cachorro " + nome + " diz Au Au!!");
	}

};