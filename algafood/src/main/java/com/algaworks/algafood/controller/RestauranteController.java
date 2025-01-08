package com.algaworks.algafood.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.model.Cozinha;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
	
	private final RestauranteService restauranteService; 

	public RestauranteController(RestauranteService restauranteService) {
		this.restauranteService = restauranteService;
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {

/*		Restaurante restaurante =  restauranteService.buscarId(restauranteId);

		Cozinha cozinha = restaurante.getCozinha();//Realiza a busca porém não faz o uso
		cozinha.getNome(); //Somente aqui irá ocorrer o select no banco de dados pois vai ser usado

		return null;*/

		return ResponseEntity.status(HttpStatus.FOUND).
				body(restauranteService.buscarId(restauranteId));

	}
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listar() {
		return ResponseEntity.ok(restauranteService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Restaurante> cadastrar(@RequestBody Restaurante restaurante) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(restauranteService.cadastrar(restaurante));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void atualizar(@RequestBody Restaurante restaurante) {
		System.out.println("Entrei no controller");

		Restaurante restaurante1 = restauranteService.atualizar(restaurante);
		System.out.println("Sai Controller---FIM----");
		System.out.println(restaurante1);

	}
	
	/*
	
	@GetMapping("/por-nome")
	public ResponseEntity<List<Restaurante>> consultarPorNome(@RequestParam String nome) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(restauranteRepositorio.consultarPorNome(nome));
	}

	@GetMapping("/por-nome-e-taxas-frete")
	public ResponseEntity<List<Restaurante>> consultarTaxasFrete(
		 @RequestParam(defaultValue = "") String nome,
		 BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
	    List<Restaurante> r = restauranteRepositorio.find(nome, taxaInicial, taxaFinal); 
	    return ResponseEntity.status(HttpStatus.OK).body(r);
	    
	    /* Não é bom anotar os parâmetros com @RequestParam, 
	     * pois ele faz um find do corpo de requisição ou parte dele
	     * ao parâmetro do método, caso não receba nada ocorrerá um erro.
	     * 
	     * O atridefaultValue = "0" apesar de não
	     * aceitar números, podemos passar número de string que o Spring
	     * consegue identificar que o valor padrão será atributo para variável 
	     * Big Decimal e fazer a conversão.
	     * 
	     * @RequestParam(defaultValue = "0")
	     * 
	     * Porém não é recomendado que use  Request Parameter com o defaultValue
	     * Caso não passemos taxaInicial e taxaFinal o request parameter faz com que 
	     * recebam o valor padrão especifica que no caso é 0 e então ele irá fazer uma
	     * consulta no qual os restaurantes tenham a taxaFrete igual a 0 e isso 
	     * pode ocasionar ém um consulta vazia.
	     * 
	}
	
	
	
	@GetMapping("/com-frete-gratis")
	public ResponseEntity<List<Restaurante>> consultarFreteGratis(String nome) {
	    return ResponseEntity.status(HttpStatus.OK).
	    		body(restauranteRepositorio.findComFrteGratis(nome));
	}    

	@GetMapping("/taxa-frete")
	public ResponseEntity<List<Restaurante>> consultarTaxaFrete(BigDecimal taxal) {
		List<Restaurante> r =restauranteRepositorio.findByTaxaFrete(taxal); 
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}
	@GetMapping("/primeiro")
	public ResponseEntity<Restaurante> consultarOPrimeiroRestaurante(String nome) {
		Optional<Restaurante> r = restauranteRepositorio.findFirstRestauranteByNomeContaining(nome);
		return ResponseEntity.status(HttpStatus.OK).body(r.get());
	}
	
	@GetMapping("/top-dois")
	public ResponseEntity<List<Restaurante>>consultarTop2 (String nome) {
		return ResponseEntity.status(HttpStatus.OK).body( restauranteRepositorio.findTop2ByNomeContaining(nome));
	}
	
	@GetMapping("/count-por-cozinha")
	public ResponseEntity<Integer> consultarPorCozinha(@RequestParam Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(
				restauranteRepositorio.countByCozinhaId(id));
	}*/
}
