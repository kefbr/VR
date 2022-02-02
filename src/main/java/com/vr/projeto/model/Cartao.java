package com.vr.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "cartao")
public class Cartao {
	
	@Id
	private String numero;
	@Column(name="senha")
	private String senha;
	@Min(0)
	@Column(name="saldo")
	private Double saldo;
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public void saldoInicial() {
		this.saldo = 10.00;
	}
}
