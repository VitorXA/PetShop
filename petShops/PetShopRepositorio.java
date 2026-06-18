package petShops;

import java.util.ArrayList;
import java.util.List;

public class PetShopRepositorio {

	private final ArrayList<Animais> animais = new ArrayList<>();

	public void adicionar(Animais a) {
		animais.add(a);
	}

	/**
	 * Busca um animal pelo nome (case-insensitive).
	 * 
	 * @return o Animal encontrado, ou null se não existir.
	 */
	public Animais buscarPorNome(String nome) {

		return null;
	}

	/**
	 * Remove o animal com o nome informado.
	 * 
	 * @return true se encontrou e removeu, false caso contrário.
	 */
	public boolean remover(String nome) {
		return true;
	}

	/** Retorna a lista completa de animais cadastrados (cópia defensiva). */
	public ArrayList<Animais> listarTodos() {
		return animais;
	}

	/** Quantidade de animais cadastrados no repositório. */
	public int quantidade() {
		return 0;
	}
}