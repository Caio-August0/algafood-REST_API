package com.algaworks.algafoodRESTAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafoodRESTAPI.domain.model.Estado;
import com.algaworks.algafoodRESTAPI.repository.EstadoRepository;

@Controller // receber, trtar e retornar um corpo de resposta
@RequestMapping(value = "/estados") // Mapear essata URI na classe
@ResponseBody // Irá colocar o corpo de resposta no corpo de resposta da resquisição
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping //todas as requisições com o verbo GET, serão esquecutas por esse método 
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

}
