package petShops;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPetShop extends JFrame {

	private final PetShopRepositorio repositorio = new PetShopRepositorio();

	// ── Campos do formulário ───────────────────────────────
	private final JTextField campNome = new JTextField(10);
	private final JTextField campRaca = new JTextField(10);
	private final JTextField campIdade = new JTextField(3);
	private final JTextField campTutor = new JTextField(10);
	private final JTextField campTelefone = new JTextField(10);

	// ── �?rea de resultado ──────────────────────────────────
	private final JTextArea areaResultado = new JTextArea(12, 50);

	// ── Botões ─────────────────────────────────────────────
	private final JButton btnCadastrar = new JButton("Cadastrar");
	private final JButton btnBuscar = new JButton("Buscar");
	private final JButton btnAtualizar = new JButton("Atualizar");
	private final JButton btnRemover = new JButton("Remover");
	private final JButton btnListarTodos = new JButton("Listar Todos");

	// ── Construtor ─────────────────────────────────────────
	public TelaPetShop() {
		super("Pet Shop — Gerenciador de Animais");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// O JFrame usa BorderLayout por padrão
		setLayout(new BorderLayout(8, 8));

		add(criarPainelFormulario(), BorderLayout.NORTH);
		add(criarAreaResultado(), BorderLayout.CENTER);
		add(criarPainelBotoes(), BorderLayout.SOUTH);

		configurarListeners();

		setSize(900, 600);
		pack();
		setLocationRelativeTo(null); // centraliza na tela

		setVisible(true);
	}

	// ── Painel Norte: formulário ───────────────────────────
	private JPanel criarPainelFormulario() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
		painel.setBorder(BorderFactory.createTitledBorder("Dados do Pet e Tutor"));

		painel.add(new JLabel("Nome: "));
		painel.add(campNome);
		painel.add(new JLabel("Raça: "));
		painel.add(campRaca);
		painel.add(new JLabel("Idade: "));
		painel.add(campIdade);
		painel.add(new JLabel("Tutor: "));
		painel.add(campTutor);
		painel.add(new JLabel("Telefone: "));
		painel.add(campTelefone);

		return painel;
	}

	// ── Centro: área de texto com scroll ──────────────────
	private JScrollPane criarAreaResultado() {
		areaResultado.setEditable(false);
		areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
		areaResultado.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
		exibirTexto("Bem-vindo ao sistema do Pet Shop!\n"
				+ "Preencha os campos acima e use os botões para gerenciar os pets.\n");
		return new JScrollPane(areaResultado);
	}

	// ── Painel Sul: botões ─────────────────────────────────
	private JPanel criarPainelBotoes() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
		painel.add(btnCadastrar);
		painel.add(btnBuscar);
		painel.add(btnAtualizar);
		painel.add(btnRemover);
		painel.add(btnListarTodos);

		return painel;
	}

	// ── ActionListeners ────────────────────────────────────
	private void configurarListeners() {

		// ---- CADASTRAR ----
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = campNome.getText().trim();
				String raca = campRaca.getText().trim();
				int idade = 0;
				String tutor = campTutor.getText().trim();
				int telefone = 0;

				if (nome.isEmpty()) {
					exibirTexto("ERRO: O campo Nome é obrigatório.");
					return;
				}
				if (raca.isEmpty()) {
					raca = "Indefinida";
				}

				if (tutor.isEmpty()) {
					tutor = "(Cachorro sem dono)";
				}

				try {
					idade = Integer.parseInt(campIdade.getText());
					if (idade < 0) {
						throw new NumberFormatException();
					}

				} catch (NumberFormatException ex) {
					exibirTexto("Por favor, insira uma idade válida.");
					return;
				}

				try {
					telefone = Integer.parseInt(campTelefone.getText());
					if (telefone < 0) {
						throw new NumberFormatException();
					}

				} catch (NumberFormatException ex) {
					exibirTexto("Por favor, insira um número válido.");
					return;
				}

				Cliente dono = new Cliente();
				dono.setNome(tutor);
				dono.setTelefone(telefone);

				Cachorro novo = new Cachorro(nome, raca, idade, dono);

				repositorio.adicionar(novo);
				limparCampos();
				exibirTexto("Pet cadastrado com sucesso!\n\n" + novo.exibirDados());
			}
		});
		
		// ---- BUSCAR ----
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeBusca = campNome.getText().trim();
				if (nomeBusca.isEmpty()) {
					exibirTexto("Digite o nome do cachorro para fazer a busca");
					return;
				}

				Animais encontrado = repositorio.buscarPorNome(nomeBusca);
				if (encontrado != null) {
					exibirTexto("Cachorro encontrado no sistema: \n" + encontrado.exibirDados());
				} else {
					exibirTexto("Cachorro não encontrado no sistema, verifique o nome  digitado.");
				}
			}
		});
		
		// ---- ATUALIZAR ----
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dogName = campNome.getText().trim();
				if (dogName.isEmpty()) {
					exibirTexto("Digite o nome do cachorro para fazer atualizar seus dados");
					return;
				}
				
				Animais encontrado = repositorio.buscarPorNome(dogName);
				
				if (encontrado != null) {
					String nome = campNome.getText().trim();
					String raca = campRaca.getText().trim();
					int idade = 0;
					String tutor = campTutor.getText().trim();
					int telefone = 0;

					if (raca.isEmpty()) {
						raca = "Indefinida";
					}

					if (tutor.isEmpty()) {
						tutor = "(Cachorro sem dono)";
					}

					try {
						idade = Integer.parseInt(campIdade.getText());
						if (idade < 0) {
							throw new NumberFormatException();
						}

					} catch (NumberFormatException ex) {
						exibirTexto("Por favor, insira uma idade válida.");
						return;
					}

					try {
						telefone = Integer.parseInt(campTelefone.getText());
						if (telefone < 0) {
							throw new NumberFormatException();
						}

					} catch (NumberFormatException ex) {
						exibirTexto("Por favor, insira um número válido.");
						return;
					}

					Cliente dono = new Cliente();
					dono.setNome(tutor);
					dono.setTelefone(telefone);

					Cachorro atualizado = new Cachorro(nome, raca, idade, dono);

					repositorio.remover(dogName);
					repositorio.adicionar(atualizado);
					exibirTexto("Dados atualizados com sucesso!\n\n" + atualizado.exibirDados());

					limparCampos();
				}
			}
		});

		// ---- LISTAR ----
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (repositorio.quantidade() == 0) {
					exibirTexto("O repositorio está vazio, cadastre um cachorro para utilizar essa opção.");
					return;
				}
				
				
				String textoFinal = "";

				for (Animais a : repositorio.listarTodos()) {
					textoFinal += a.exibirDados() + "\n_________________________________\n \n";
				}
				exibirTexto(textoFinal);
			}

		});

		// ---- REMOVER ----
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dogRemove = campNome.getText().trim();
				if (dogRemove.isEmpty()) {
					exibirTexto("Digite o nome de um cachorro primeiro.");
					return;
				}

				boolean removed = repositorio.remover(dogRemove);
				if (removed) {
					exibirTexto("Cachorro removido com sucesso.");
				} else {
					exibirTexto("Não foi possivel remover tal cachorro, verifique o nome e tente novamente.");
				}

			}
		});
	}

	// ── Métodos auxiliares ─────────────────────────────────

	/** Exibe texto na área de resultado, substituindo o conteúdo anterior. */
	private void exibirTexto(String texto) {
		areaResultado.setText(texto);
	}

	/** Limpa todos os campos do formulário. */
	private void limparCampos() {
		campNome.setText("");
		campRaca.setText("");
		campTutor.setText("");
		campIdade.setText("");
		campTelefone.setText("");
		campNome.requestFocus();

	}

}