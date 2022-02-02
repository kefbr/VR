package com.vr.projeto.controller.dto;

import com.vr.projeto.model.Cartao;

public class CartaoRs {
	
	private String numeroCartao;
	private String senha;
	private Double saldo;
	
	//Metodo para converter a entidade em uma entidade repository. O datalhe é que eu poderia implementar um modelMapper, mas preferi fazer assim.
	public static CartaoRs converter(Cartao c) {
		var cartao = new CartaoRs();
		cartao.setNumeroCartao(c.getNumero());
		cartao.setSaldo(c.getSaldo());
		cartao.setSenha(c.getSenha());
		return cartao;
	}
	
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numero) {
		this.numeroCartao = numero;
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
}
