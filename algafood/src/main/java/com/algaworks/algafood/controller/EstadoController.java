package com.algaworks.algafood.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;

@Controller // receber e trata
@RequestMapping(value = "/estados") // Mapear esta URI na classe
@ResponseBody // Irá colocar o corpo de resposta no corpo de resposta da resquisição
public class EstadoController {
	
	private EstadoService estadoService;
	
	public EstadoController(EstadoService estadoService) {
		this.estadoService = estadoService;
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(Long estadoId) { 
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(estadoService.buscarId(estadoId));
	}
	
	@GetMapping //todas as requisições com o verbo GET, serão esquecutas por esse método 
	public ResponseEntity<List<Estado> >listar() {
		return ResponseEntity.ok(estadoService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Estado> cadastrar(@RequestBody Estado estado) {
		return ResponseEntity.ok(estadoService.cadastrar(estado));
	}
	
	@PutMapping
	public ResponseEntity<Estado> atualizar(@RequestBody  Estado estado) {
		 return ResponseEntity.ok(estadoService.atualizar(estado));
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long estadoId) {
		estadoService.remover(estadoId);
	}

}
