package com.algaworks.algafood.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.cidadeexception.CidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeContoller {

	private final CidadeService cidadeService;

	public CidadeContoller(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}
	@GetMapping
	public ResponseEntity<List<Cidade>> listar() {
		return ResponseEntity.ok(cidadeService.listar());
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(Long cidadeId) {
		return ResponseEntity.status(HttpStatus.FOUND).body(cidadeService.buscar(cidadeId));
	}
	
	@PostMapping
	public ResponseEntity<Cidade> cadastrar(@RequestBody Cidade cidade) {
		return ResponseEntity.ok(cidadeService.cadastar(cidade));
	}
	
	@PutMapping
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade) {
		return ResponseEntity.ok(cidadeService.atualizar(cidade));
	}	
	
	@DeleteMapping("/{cidadeId}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(@PathVariable Long cidadeId) {
		try {
			cidadeService.remover(cidadeId);
			return ResponseEntity.noContent().build();
		} catch (CidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
