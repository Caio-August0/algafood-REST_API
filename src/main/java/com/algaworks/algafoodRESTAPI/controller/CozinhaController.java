package com.algaworks.algafoodRESTAPI.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafoodRESTAPI.domain.model.Cozinha;
import com.algaworks.algafoodRESTAPI.repository.CozinhaRepositorio;


//@RestController
@Controller

@RequestMapping(value = "/cozinhas") // Mapea a URI ao Controlador
//Requisições que chegam na URI especificada,
//todas a requisições com essa URI será controlado por essa classe

@ResponseBody // Todas as respostas dos métodos irão para o corpo de resposta da requisição   
public class CozinhaController {
    
    private CozinhaRepositorio cozinhaRepositorio;

    // recebe a injeção do bean CozinhaRepositoryImpl
    public CozinhaController(CozinhaRepositorio cozinhaRepositorio) {
        this.cozinhaRepositorio = cozinhaRepositorio;
    }

    @GetMapping //todas as requisições com o verbo http get, será executado por esse método
    public ResponseEntity<List<Cozinha>> listar() {
    	// Neste caso não usamos o NOT_FOUND, pois o recurso, só está vazio. E não é um erro causado pelo consumidor da API
    	List<Cozinha> cozinhas = cozinhaRepositorio.listar();  // Retorna uma coleção de recurso
    	if (cozinhas.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();// Neste caso não é tão indicada usar esse tipo de status é melhor deixa o 200(OK)
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
    	Cozinha cozinha = cozinhaRepositorio.buscar(id);
    	// Retorna um status que indica que o erro foi causado pelo o cosumidor da API
    	// E indica que o recurso não existe
    	if (cozinha != null) {
    		return ResponseEntity.status(HttpStatus.FOUND).body(cozinha);
    	}
    	return ResponseEntity.
    			status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/por-nome")
    public List<Cozinha> consultarPorNome(@RequestParam String nomeCozinha) // @RequestParam nos permite realizar uma Query String
    {
    	return cozinhaRepositorio.consultarPorNome(nomeCozinha);
    }
    
    @PostMapping //todas as requisições com o verbo http POST, será executado por esse método
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
    	 cozinha = cozinhaRepositorio.adicionar(cozinha);
    	 return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
    }
    @PutMapping
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha) {
    	
    	if (cozinhaRepositorio.buscar(cozinha.getId()) != null) {
    		cozinha = cozinhaRepositorio.atualizar(cozinha);
    		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
    	}
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @DeleteMapping
    public ResponseEntity<?> remover(@RequestBody Cozinha cozinha) {
    	try {
    		if(cozinhaRepositorio.buscar(cozinha.getId()) != null) {
        		cozinhaRepositorio.remover(cozinha);
        		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        		// Se é uma reposta de sucesso e não tem corpo usa o NO_CONTENT
        	}
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}catch (DataIntegrityViolationException dataIntegrityViolationException){
    		// Isso ocorre pelo fato da chave estageira
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    		//Capturamos a exceção a fim de não encerrar a execução do no programa
    	}
    }
    
}

