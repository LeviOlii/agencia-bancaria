package Programa;

import java.util.ArrayList;
import java.util.Scanner;

import utilitarios.Utils;

public class AgenciaBancaria {
	
	static ArrayList<Conta> contasBancarias;
	static Scanner input = new Scanner(System.in);
	
	public static void criarConta() {
		Scanner entrada = new Scanner(System.in);
		String nome, cpf, email;
		
		System.out.println("\nInsira suas informações:\n");
		System.out.print("Nome: ");
		nome = entrada.nextLine();
		
		System.out.print("CPF: ");
		cpf = entrada.nextLine();
		
		System.out.print("E-mail: ");
		email = entrada.nextLine();
		
		Pessoa pessoa = new Pessoa(nome, cpf, email);
		Conta conta = new Conta(pessoa);
		
		System.out.println("ID da Conta: " + Utils.longToString(conta.getNumeroConta()));
		System.out.print("\nAperte a tecla ENTER para voltar ao Menu");
		
		
		entrada.nextLine();
		System.out.println("\n");
		contasBancarias.add(conta);		
		operacoes();
	}
	
	public static Conta encontrarConta() {
		Conta semContas = null;
		System.out.print("\nID da Conta: ");
		int numeroConta = input.nextInt();
		
		if(contasBancarias.size() > 0) {
			for(int i = 0; i<contasBancarias.size(); i++) {
				if(contasBancarias.get(i).getNumeroConta() == numeroConta) {
					return contasBancarias.get(i);
				}
			}
		}
		System.out.println("\nNão foi possível encontrar sua conta!\n");
		return semContas;
	}
	
	public static void depositarEmConta() {
		Conta c = encontrarConta();
		if(c != null) {
			double valor;
			System.out.println("Bem vindo de volta, " + c.getPessoa().getNome());
			System.out.println("\nSaldo atual: " + Utils.doubleToString(c.getSaldo()));
			System.out.println("Insira o valor em R$ a depositar: ");
			valor = input.nextDouble();
			
			c.depositarValor(valor);
			System.out.println("\nSaldo atual: " + Utils.doubleToString(c.getSaldo()));
			operacoes();
		}
		else {
			operacoes();
		}
	}
	
	public static void sacarEmConta() {
		Conta c = encontrarConta();
		if(c != null) {
			double valor;
			System.out.println("Nome do titular da conta: " + c.getPessoa().getNome());
			System.out.println("\nSaldo atual: " + Utils.doubleToString(c.getSaldo()));
			System.out.println("Insira o valor em R$ a sacar: ");
			valor = input.nextDouble();
			
			c.sacarValor(valor);
			System.out.println("\nSaldo atual: " + Utils.doubleToString(c.getSaldo()));
			operacoes();
		}
		else {
			operacoes();
		}
	}
	
	public static void listarContas() {
		Scanner entrada = new Scanner(System.in);
		if(contasBancarias.size() < 1) {
			System.out.println("Não há contas disponíveis!\n");
			operacoes();
		}
		System.out.println("----------LISTANDO TODAS AS CONTAS DISPONÍVEIS----------");
		for(int i = 0; i<contasBancarias.size(); i++) {
			System.out.print(contasBancarias.get(i).toString());
			System.out.println("--------------------------------------------------------");
		}
		System.out.print("\nAperte a tecla ENTER para voltar ao Menu");
		entrada.nextLine();
		System.out.println("\n");
		operacoes();
	}
	
	public static void transferirParaConta() {
		Scanner entrada = new Scanner(System.in);
		Double valor;
		
		System.out.println("\nInsira o ID do pagante e remetente, respectivamente:");
		Conta pagante = encontrarConta();
		Conta remetente = encontrarConta();
		if(pagante == null || remetente == null) {
			System.out.println("Ocorreu um ERRO! Pelo menos um dos IDs não foram reconhecidos!");
			operacoes();
		}
		
		System.out.print("Insira o valor a ser transferido: ");
		valor = entrada.nextDouble();
		pagante.transferirValor(remetente, valor);
		
		System.out.println("\nSaldo atual de " + pagante.getPessoa().getNome() + ": " + Utils.doubleToString(pagante.getSaldo()));
		System.out.println("Saldo atual de " + remetente.getPessoa().getNome() + ": " + Utils.doubleToString(remetente.getSaldo()));
		operacoes();
	}
	
	public static void encerrarPrograma() {
		System.out.print("\nEncerrando o programa.");
		System.exit(0);
	}
	
	public static void casoNumeroInvalido() {
		System.out.println("\nOPÇÃO NÃO EXISTENTE. TENTE NOVAMENTE!\n");
	}
	
	
	public static void main(String[] args) {
		contasBancarias = new ArrayList<Conta>();
		operacoes();
	}
	
	public static void operacoes() {

		int entrada = 0;
		
		System.out.println("\n-------------------------------------------");
		System.out.println("---------Bem vindo a nossa Agencia---------");
		System.out.println("-------------------------------------------");
		System.out.println(" ****** Escolha a operação desejada! ******");
		System.out.println("-------------------------------------------");
		System.out.println("|   Opção 1 - Criar Conta   |");
		System.out.println("|   Opção 2 - Depositar     |");
		System.out.println("|   Opção 3 - Sacar         |");
		System.out.println("|   Opção 4 - Transferir    |");
		System.out.println("|   Opção 5 - Listar Contas |");
		System.out.println("|   Opção 6 - Sair          |");
		
		try {
			entrada = input.nextInt();
		}catch(Exception e) {
			System.out.println("\nNÃO FOI POSSÍVEL IDENTIFICAR A OPÇÃO DESEJADA. TENTE NOVAMENTE!");
			operacoes();
		}
					
		switch(entrada) {
		case 1:
			criarConta();
		case 2:
			depositarEmConta();
		case 3:
			sacarEmConta();
		case 4:
			transferirParaConta();
		case 5:
			listarContas();
		case 6:
			encerrarPrograma();
		default:
			casoNumeroInvalido();
		}
	}
}
