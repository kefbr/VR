package com.vr.projeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.projeto.controller.dto.CartaoRq;
import com.vr.projeto.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {
	
	private final CartaoService cartaoService;
	
	public CartaoController(CartaoService cartaoService) {
		this.cartaoService = cartaoService;
	}
	
	@PostMapping("/")
	public ResponseEntity<CartaoRq> criarCartao(@RequestBody CartaoRq cartao) {
		return cartaoService.salvarCartao(cartao);
	}
	
	@GetMapping("/{numeroCartao}")
	public ResponseEntity<String> retornarSaldoCartao(@PathVariable("numeroCartao") String id) {
		return cartaoService.retornarSaldoCartao(id);
	}
	
	
}
