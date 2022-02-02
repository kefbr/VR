package com.vr.projeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.projeto.controller.dto.TransacaoRq;
import com.vr.projeto.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {
	
	private final TransacaoService transacaoService;
	
	
	public TransacaoController(TransacaoService transacaoService) {
		this.transacaoService = transacaoService;
	}

	@PostMapping("/")
	public ResponseEntity<String> realizarTransacao(@RequestBody TransacaoRq transacao) {
		return transacaoService.realizarTransacao(transacao);
	}
}
