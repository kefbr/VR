package com.vr.projeto.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.vr.projeto.controller.dto.CartaoRq;
import com.vr.projeto.controller.dto.CartaoRs;
import com.vr.projeto.model.Cartao;
import com.vr.projeto.repository.CartaoRepository;

@Service
public class CartaoService {
	
	private final CartaoRepository cartaoRepository;
	
	public CartaoService(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ResponseEntity<CartaoRq> salvarCartao(CartaoRq cartaoRq) {
		try {
			cartaoRepository.findById(cartaoRq.getNumeroCartao()).get();
			return new ResponseEntity<CartaoRq>(cartaoRq, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			try {
				var c = new Cartao();
				c.setNumero(cartaoRq.getNumeroCartao());
				c.setSenha(cartaoRq.getSenha());
				c.saldoInicial();
				cartaoRepository.save(c);
				return new ResponseEntity<CartaoRq>(cartaoRq, HttpStatus.CREATED);
			} catch (Exception e2) {
				return new ResponseEntity<CartaoRq>(cartaoRq, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	@Transactional
	public ResponseEntity<String> retornarSaldoCartao(@PathVariable("numeroCartao") String id) {
		try {
			return new ResponseEntity<>(CartaoRs.converter(cartaoRepository.getById(id)).getSaldo().toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
