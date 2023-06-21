package main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.DaoVeiculo;
import dao.DaoVendedor;
import entidades.Veiculo;
import entidades.Vendedor;

public class Main {

	private static DaoVeiculo daoVeiculo = new DaoVeiculo();
	private static DaoVendedor daoVendedor = new DaoVendedor();
	
	public static void main(String[] args) throws SQLException {
		
		Scanner ler = new Scanner(System.in);
		int opc;
		
		do {System.out.println("--------Menu Concessionaria--------");
		
			System.out.println("Escolha:");
			System.out.println("1 - Cadastrar Vendedor");
			System.out.println("2 - Cadastrar Veiculo");
			System.out.println("3 - Atualizar Veiculo");
			System.out.println("4 - Excluir veiculo");
			System.out.println("5 - Listar Veiculos");
			System.out.println("6 - Pesquisar Veiculos");
			System.out.println("7 - Buscar veiculo");
			System.out.println("8 - Listar Vendedores");
			System.out.println("0 - Sair");
			
			opc = Integer.parseInt( ler.nextLine() );
			
			switch(opc) {
				case 1:
					cadastrarVendedor();
					break;
				case 2:
					cadastrarVeiculo();
					break;
				case 3:
					atualizarVeiculo();
					break;
				case 4:
					excluirVeiculo();
					break;
				case 5:
					ListarVeiculo();
					break;
				case 6:
					pesquisarVeiculo();
					break;
				case 7:
					buscarVeiculo();
					break;
				case 8:
					ListarVendedor();
					break;
				case 0:
					System.out.println("ATE LOGO!.");
					break;
				default:
					System.out.println("OPCAO INVALIDA!");
			}
			
		}while(opc != 0);
	}
	public static void cadastrarVendedor() throws SQLException{
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite o Nome do Vendedor: ");
		String nome = ler.nextLine();
		
		System.out.println("Digite a senha de Vendedor: ");
		String senha = ler.nextLine();

		Vendedor vend = new Vendedor(nome, senha);
		
		daoVendedor.inserir(vend);

		System.out.println("Cadastro Realizado!");

		System.out.println("Vendedor cadastrado com o ID " + vend.getIdVendedor());
	}
	public static void cadastrarVeiculo() throws SQLException {
		Scanner ler = new Scanner(System.in);
		System.out.printf("CADASTRO DE VEICULO \n");
		
		System.out.println("Digite a marca do Veiculo: ");
		String marca = ler.nextLine();
		
		System.out.println("Digite o modelo do Veiculo: ");
		String modelo = ler.nextLine();
		
		System.out.println("Digite o ano de lancamneto: ");
		int ano = Integer.parseInt(ler.nextLine());
		
		System.out.println("Digite o ID do Vendedor:");
		int idVendedor = Integer.parseInt(ler.nextLine());

		Veiculo v = new Veiculo(); // Criar um novo objeto Veiculo
		v.setMarca(marca);
		v.setModelo(modelo);
		v.setAno(ano);
		
		Vendedor vendedor = daoVendedor.buscarPorId(idVendedor); // Buscar o vendedor pelo ID
		v.setVendedor(vendedor); // Atribuir o vendedor ao veículo
		
		boolean inserido = daoVeiculo.inserir(v); // Inserir o veículo no banco de dados
		
		if (inserido == true) {
			System.out.println("Cadastro efetuado!");
			System.out.println("Veiculo cadastrado com o ID " + v.getIdveiculo());
		} else {
			System.out.println("Falha ao cadastrar o veiculo.");
		}
	}

	
	public static void atualizarVeiculo() throws SQLException{
		System.out.println("Atualizando Veiculo");
		
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Digite o ID do Veiculo: ");
		int id = Integer.parseInt(ler.nextLine());

		Veiculo veiculo = daoVeiculo.buscar(id);
		
		if(veiculo != null) {
			
			System.out.println("Marca atual do Veiculo: " + veiculo.getMarca());
			System.out.println("Digite a nova marca ou pressione enter:");
			
			String mar = ler.nextLine();
			
			if(mar.length() > 0) {
				veiculo.setMarca(mar);
			}
			
			System.out.println("Modelo atual do veiculo: " + veiculo.getModelo());
			System.out.println("Informe a nova modelo ou pressione enter:");
			
			String model = ler.nextLine();
			
			if(model.length() > 0) {
				veiculo.setModelo((model));
			}
			
			if( daoVeiculo.atualizar(veiculo) ) {
				System.out.println("Veiculo Atualizado!");
			}else {
				System.out.println("Houve um erro ao atualizar.");
			}
			
		}else {
			System.out.println("Erro ao encontrar veiculo. O veiculo"+ id +" existe?");
		}
	}
	
	public static void buscarVeiculo() throws SQLException {
		System.out.println(" Buscar Veiculo por ID");
		
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Insira o ID do Veiculo: ");
		int id = Integer.parseInt(ler.nextLine());

		Veiculo v = daoVeiculo.buscar(id);
		
		if(v != null) {
			System.out.println("ID: " + v.getIdveiculo());
			System.out.println("Marca: " + v.getMarca());
			System.out.println("Modelo: " + v.getModelo());
			System.out.println("Ano: " + v.getAno());
			System.out.println("Vendedor: " + v.getVendedor().getIdVendedor() +"\n");
		}else {
			System.out.println("Veiculo não existe");
		}
	}

	public static void excluirVeiculo() throws SQLException{
		System.out.println(" Excluindo Veiculo por ID");
		
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Insira o ID: ");
		int id = Integer.parseInt(ler.nextLine());

		boolean r = daoVeiculo.excluir(id);
		
		if( r ) {
			System.out.println("Veiculo excluído!");
		}else {
			System.out.println("Nao foi possivel excluir o Veiculo. O veiculo "+ id +" existe?");
		}
	}
	
	public static void ListarVeiculo() throws SQLException {
		
		System.out.println(" Veiculos Cadastrados: ");
		
		List<Veiculo> veic = daoVeiculo.buscarT();
		
		for(Veiculo vei : veic) {
			System.out.println("ID: " + vei.getIdveiculo());
			System.out.println("Marca: " + vei.getMarca());
			System.out.println("Modelo: " + vei.getModelo());
			System.out.println("Ano: " + vei.getAno());
			System.out.println("Vendedor: " + vei.getVendedor().getIdVendedor());
		}
	}

	public static void pesquisarVeiculo() throws SQLException {
		System.out.println("\n Buscando Veiculos por Modelos ");
		
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Informe o modelo : ");
		String pesqui = ler.nextLine();

		List<Veiculo> veic = daoVeiculo.pesquisarPorModelo(pesqui);
		
		for(Veiculo vei : veic) {
			System.out.println("ID: " + vei.getIdveiculo());
			System.out.println("Marca: " + vei.getMarca());
			System.out.println("Modelo: " + vei.getModelo());
			System.out.println("Ano: " + vei.getAno());
			System.out.println("Vendedor: " + vei.getVendedor().getIdVendedor());
			
			ler.nextLine();
		}
		
	}

	
	public static void ListarVendedor() throws SQLException {
		
		System.out.println("\n Listar Vendedores \n");
		
		List<Vendedor> vend = daoVendedor.buscarT();
		
		for(Vendedor ven : vend) {
			System.out.println("ID: " + ven.getIdVendedor());
			System.out.println("Nome do Vendedor: " + ven.getNome());
			System.out.println("Senha: " + ven.getSenha());
		}
	}
	
}