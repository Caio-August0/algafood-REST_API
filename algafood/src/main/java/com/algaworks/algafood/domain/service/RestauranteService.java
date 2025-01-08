package com.algaworks.algafood.domain.service;


import java.util.List;

import com.algaworks.algafood.domain.exception.restauranteexception.RestauranteExistenteException;
import com.algaworks.algafood.domain.exception.restauranteexception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RestauranteService {
	
	private static final String MSG_RESTAURANTE_EXISTENTE = 
			"Restaurante existente na base de dados."
			+"Caso necessário atualize o seu cadastro";
	
	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO =
			"O restaurante de id: %d, não foi encontrada.";

	
	private final RestauranteRepository restauranteRepositorio;
	private final CozinhaService cozinhaService;

	public RestauranteService(RestauranteRepository restauranteRepositorio,
							  CozinhaService cozinhaService) {
		this.restauranteRepositorio = restauranteRepositorio;
		this.cozinhaService = cozinhaService;
	}
	
	public Restaurante buscarId(Long resturanteId) {
		return restauranteRepositorio
				.findById(resturanteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(
						String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, resturanteId)));
	}
	
	public List<Restaurante> listar() {
		return restauranteRepositorio.findAll();
	}

	@Transactional
	public Restaurante cadastrar(Restaurante restauranteParamer) {

		 if(restauranteRepositorio.existsById(restauranteParamer.getId())) {
			 throw new RestauranteExistenteException(
						String.format(MSG_RESTAURANTE_EXISTENTE, restauranteParamer.getId()));
		 }

		 return restauranteRepositorio.save(restauranteParamer);
	}

	@Transactional
	public Restaurante atualizar(Restaurante restauranteParamer) {
		System.out.println("Entrei no Service");
		Restaurante restaurante = buscarId(restauranteParamer.getId());
		
		Long cozinhaId = restauranteParamer.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarId(cozinhaId);

		restauranteParamer.setCozinha(cozinha);
		BeanUtils.copyProperties(restauranteParamer, restaurante,
				"id","formasDePagamento","produtos","endereco","dataCadastro","dataAtualizacao","produtos");


		System.out.println("Sai do Service");
		return restauranteRepositorio.save(restaurante);
	}
	
}
