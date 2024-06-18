package Programa;

import utilitarios.Utils;

public class Conta {
	
	private static int contadorDeContas = 1;
	
	private int numeroConta;
	private Pessoa pessoa;
	private Double saldo = 0.0;
	
	//construtor
	public Conta(Pessoa pessoa) {
		this.numeroConta = contadorDeContas;
		this.pessoa = pessoa;
		contadorDeContas += 1;
	}
	
	//getters e setters
	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public String toString() {
		return "\nNúmero da conta: " + Utils.longToString(this.getNumeroConta())+
			   "\nNome: " + this.pessoa.getNome() +
			   "\nCPF: " + this.pessoa.getCpf()+
			   "\nE-mail: " + this.pessoa.getEmail()+
			   "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
			   "\n";
	}
	
	//métodos
	
	
	
	public void depositarValor(Double valor) {
		if(valor > 0) {
			setSaldo(getSaldo() + valor);
			System.out.println("Valor depositado com sucesso!");
		}
		else
			System.out.println("Não foi possível realizar o depósito");
	}
	
	public void sacarValor(Double valor) {
		if(this.getSaldo() >= valor && valor > 0 && this.getSaldo() > 0) {
			setSaldo(getSaldo() - valor);
			System.out.println("Valor sacado com sucesso!");
		}
		else
			System.out.println("Não foi possível realizar o saque!");
	}
	
	public void transferirValor(Conta contaParaReceber, Double valor) {
		if(valor > 0 && this.getSaldo() >= valor && this.getSaldo() > 0) {
			contaParaReceber.setSaldo(contaParaReceber.getSaldo() + valor);
			this.setSaldo(this.getSaldo() - valor);
			System.out.println("Valor transferido com sucesso!");
		}
		else
			System.out.println("Não foi possível realizar a transferencia");
	}
}
