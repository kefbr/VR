package com.vr.projeto.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vr.projeto.controller.dto.TransacaoRq;
import com.vr.projeto.model.Cartao;
import com.vr.projeto.repository.CartaoRepository;

@Service
public class TransacaoService {
	
	private final CartaoRepository cartaoRepository;
	Logger logger = LoggerFactory.getLogger(TransacaoService.class);

	public TransacaoService(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<String> realizarTransacao(TransacaoRq transacao){
		Cartao cartao;
		try {
			cartao = verificaSeOCartaoExiste(transacao);
			verificaSenha(transacao, cartao);
			verificaSaldo(transacao, cartao);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		//Depois de ter subtraido o saldo, salvar no banco.
		try {
			cartaoRepository.save(cartao);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao efetuar transação", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Verifica se existe o numero negativo, se não subtrai e seta o saldo.
	private Boolean verificaSaldo(TransacaoRq transacao, Cartao cartao) throws Exception {
		Double saldo =  cartao.getSaldo() - transacao.getValor();
		switch (saldo.toString().indexOf("-")) {
		case -1:
			cartao.setSaldo(saldo);
			return true;
		default:
			throw new Exception("Saldo Insuficiente.");
			
		}
	}
	//Compara as senhas e retorna.
	private Boolean verificaSenha(TransacaoRq transacao, Cartao cartao) throws Exception {
		switch (cartao.getSenha().compareTo(transacao.getSenhaCartao())) {
		case 0:
			return true;
		default:
			throw new Exception("Senha invalida.");
		}
	}
	
	private Cartao verificaSeOCartaoExiste(TransacaoRq transacao) throws Exception {
		try {
			var cartao = new Cartao();
			cartao = cartaoRepository.findById(transacao.getNumeroCartao()).get();
			return cartao;
		} catch (Exception e) {
			throw new Exception("Cartão Inexistente.");
		}
		
	}
}
