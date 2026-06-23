package petShops;

import java.util.ArrayList;
import java.util.List;

public class PetShopRepositorio {

	private final ArrayList<Animais> animais = new ArrayList<>();

	public void adicionar(Animais a) {
		animais.add(a);
	}


	public Animais buscarPorNome(String nome) {
		for (Animais dog : animais) {
			if (dog.getNome().equalsIgnoreCase(nome)) {
				return dog;
			}
		}
		return null;
	}


	public boolean remover(String nome) {
		Animais dog = buscarPorNome(nome);
		if (dog != null) {
			animais.remove(dog);
			Animais.qntA--;
			return true;
		}
		return false;
	}

	public ArrayList<Animais> listarTodos() {
		return animais;
	}


	public int quantidade() {
		return animais.size();
	}
}



