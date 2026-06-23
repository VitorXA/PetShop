package petShops;

public abstract class Animais{
	protected String nome;
	protected String raca;
	protected int idade;
	protected boolean faminto = true;
	protected Cliente dono;

	public static int qntA;
	
	public static int getQntA() {
		System.out.println("A quantidade de animais cadastrados é " + qntA);
		return qntA;
	}

	public String getNome() {
		return nome;
	}

	public String getRaca() {
		return raca;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean isFaminto() {
		return faminto;
	}

	public Cliente getDono() {
		return dono;
	}

	public Animais(String nome, String raca, int idade, Cliente dono) {
		this.nome = nome;
		this.raca = raca;
		this.setIdade(idade);
		this.dono = dono;
		qntA++;
	}

	public abstract void emitirSom();

	public String exibirDados() {
		if (idade <0) {
			return("A idade digitada para o " + nome + " está incorreta!!");
		}
		return("O nome do seu animal é " + nome + "\n A raça do seu animal é " + raca + " \n A idade do seu animal é " + idade + "\n O dono do " + nome + " é " + dono.getNome() + "\n E seu telefone é " + dono.getTelefone());
		
	}

}