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

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas") 
/*Mapea a URI ao Controlador
Requisições que chegam na URI especificada,
todas a requisições com essa URI será controlado por essa classe*/


public class CozinhaController {
     /* Estamos usando a interface para acessar as funcionalidades 
      * da cozinhaRepositorioImpl
	 	porém como essa interface na a encapsula mais,
	 	 não temos mais acesso a suas funcionalidades
    */
	private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(cozinhaService.buscarId(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
    	// Neste caso não usamos o NOT_FOUND, pois o recurso, só está vazio. E não é um erro causado pelo consumidor da API
    	  // Retorna uma coleção de recurso
    		// Neste caso não é tão indicada usar esse tipo de status é melhor deixa o 200(OK)

    	return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
    	 return ResponseEntity.ok(cozinhaService.salvar(cozinha));
    }
    
    @PutMapping
    public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha) {
    	return ResponseEntity.ok(cozinhaService.atualizar(cozinha));
    }
    
    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> deletar(@PathVariable Long cozinhaId) {
    		cozinhaService.remover(cozinhaId);
    		return ResponseEntity.noContent().build();
    }
    
   
    /*
    @GetMapping("/existe")
    public boolean isCozinha(String nome) {
    	return cozinhaRepositorio.existsByNome(nome);
    }
    

    
    @GetMapping("/unica-por-nome")
    public ResponseEntity<List<Cozinha>> consultarUnicaPorNome(@RequestParam String nome) {
    	
    	try {
    		List<Cozinha> cozinha = cozinhaService.consultarPorNome(nome);
    		return ResponseEntity.status(HttpStatus.FOUND).body(cozinha);
    	} catch ( EntidadeVaziaException entidadeVaziaException) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    }
    
    
    @GetMapping("/por-nome")
    public ResponseEntity<List<Cozinha>> consultarPorNome(@RequestParam String nome) {
    	
    	try {
    		List<Cozinha> cozinha = cozinhaService.consultarPorNome(nome);
    		return ResponseEntity.status(HttpStatus.FOUND).body(cozinha);
    	} catch ( EntidadeVaziaException entidadeVaziaException) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    }
    
    */
}  
    /*1º-Forma de customizar exceções usando Try Catch
     * 
     * Desvantagem desssa forma, robusta não conseguímos 
     * configurar a mensagem e o Código HTTP utilizando
     * apenas um único recurso da linguaguem ou do framework.
     * 
     * Pelo fato de a resposta não ter corpo o objeto de exceção 
     * não será mostrado apenas a mensagem  
     * */
    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> remover(@PathVariable Long id) {
//    	try {
//    		cozinhaService.remover(id);
//    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    		// Se é uma reposta de sucesso e não tem corpo usa o NO_CONTENT
//    	}catch (EntidadeNaoEncontradaException e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    	}catch (EntidadeEmUsoException emUsoException){
//    		// Isso ocorre pelo fato da chave estageira
//    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
//    		//Capturamos a exceção a fim de não encerrar a execução do no programa
//    	}
//    }
    
    
    /* 2º-Forma de customizar exceções usando a anotação @ResponseStatus
     * 
     * Conseguímos tornar o código da classe mais limpo.Porém
     * há um desvantagens não conseguímos customizar o corpo da
     * exception lançada,apenas configurar o status do corpo de 
     * resposta. Fazemos isso nas classes de exceção e nos 
     * métodos*/
    
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT) 
//	public void remover(@PathVariable Long id) {
//		cozinhaService.remover(id);
//	}
    
    /* 3º-Forma de customizar exceções usando a classe ResponseStatusException 
     * 
     * A ResponseStatusException é uma classe base para lançar 
     * exceções. Com ela evitamos a necessidade de criar classes 
     * de exceções customizadas,(pode ser uma vantagem e desvantagem
     * depende da sua necessidade)tem como recurso a customização 
     * de status e mensagem. Como uma única classe conseguímos criar
     * exceções com diferentes Código HTTP e mensagens. 
     * 
     *  Por ser uma classe base, temos especializações, que seriam 
     * 	as subclasses com código HTTP configurado. 
     * */
    
	
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> remover(@PathVariable Long id) {
//    	try {
//    		cozinhaService.remover(id);
//    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    	}catch (EntidadeNaoEncontradaException e) {
//    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
//    	}catch (EntidadeEmUsoException e){
//    		throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
//    	}
//    }
    
    /* 4º-Forma de customizar exceções é criar especializações
     * da classe ResponseStatusEsception.
     * 
     * Porém essa forma não é uma boa prática pois as especializações 
     * são utilizadas dentro de uma Classe de Serviço onde temos regras 
     * de negócios para os modelos de domínio. Não é uma boa, a Classe de
     * Serviço ter conhecimento sobre as configurações do Código HTTP, isso
     * deve ser de conhecimento da Classe Controller que tem a responsabilidade 
     * de configurar e cria a resposta da requisição.
     * 
     * Nessa forma, as Classes de Exceções Customizadas se tornam especializações,
     * extendendo a Classe ResponseStattusException. Como isso podemos customizar 
     * o tipo de exceção lançada assim como definir a mensagem e o código HTTP que 
     * deverá ser lançado.
     * 
     * Porém podemos MELHORAR isso criando dois construtores dentro da Classe de 
     * Exceção, sendo que um desses recebe como argumento uma mensagem.Dentro do
     * seu corpo temos definido um Código HTTP e a chamada do segundo Constutor 
     * que recebe esse Código e a mensagem. Essa forma envita que tenhamos  especificar
     * o Código HTTP dentro da classe serviço.
     * 
     * Mas isso ainda não torna ela uma boa prática, porque Classes de Exceções 
     * são Classes de Domínio, pois se encontram dentro do pacote Domain, logo
     * não se torna algo tão ideal de se fazer. 
     * 
     * OLHAR DENTRO DA CLASSE EntidadeNaoEncontradaException
     * (As outras não foram alteradas).
     * 
     * 
     * 
     * Com isso NÃO PRECISAMOS mais usar os CATCh do try já que as classes se
     * tornaram especializações e já tem o status e a mensagens configurados
     * */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> remover(@PathVariable Long id) {
//    	
//    	cozinhaService.remover(id);
//    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    	
////    	try {
//    	
//    	
////    		// Se é uma reposta de sucesso e não tem corpo usa o NO_CONTENT
////    	}catch (EntidadeNaoEncontradaException e) {
////        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
////    	}catch (EntidadeEmUsoException emUsoException){
////    		// Isso ocorre pelo fato da chave estageira
////    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
////    		//Capturamos a exceção a fim de não encerrar a execução do no programa
////    	}
//    }



