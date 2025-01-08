package com.algaworks.algafood.domain.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeVaziaException;
import com.algaworks.algafood.domain.exception.cozinhaexception.CozinhaEmUsoException;
import com.algaworks.algafood.domain.exception.cozinhaexception.CozinhaExistenteException;
import com.algaworks.algafood.domain.exception.cozinhaexception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CozinhaService {
	
	private static final String COZINHA_EXISTENTE = 
			"Cidade existente na base de dados."
			+"Caso necessário atualize o seu cadastro";

	private static final String MSG_COZINHA_ESTÁ_EM_USO = 
			"A cozinha de id: %d,está em uso.";

	private static final String MSG_COZINHA_NAO_ENCONTRADA =
			"A cozinha de id: %d, não foi encontrada.";
	
	
	private final CozinhaRepository cozinhaRepositorio;
	private final RestauranteRepository restauranteRepository;
	
	
	public CozinhaService(CozinhaRepository cozinhaRepositorio,
			RestauranteRepository restauranteRepository) {
		this.cozinhaRepositorio = cozinhaRepositorio;
		this.restauranteRepository = restauranteRepository;
	}	
	
	
	public List<Cozinha> listar() {	
		return cozinhaRepositorio.findAll();
	}
	
	public Cozinha buscarId(Long cozinhaId) {
		return cozinhaRepositorio.findById(cozinhaId)
			.orElseThrow(() -> new CozinhaNaoEncontradaException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA,cozinhaId)) );
	}
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		if(cozinhaRepositorio.existsById(cozinha.getId())) {
			throw new CozinhaExistenteException(
					String.format(COZINHA_EXISTENTE, cozinha.getId()));
		}
		return cozinhaRepositorio.save(cozinha);
	}
	
	@Transactional
	public Cozinha atualizar(Cozinha cozinhaParamer){
		Cozinha cozinha= buscarId(cozinhaParamer.getId());
		BeanUtils.copyProperties(cozinhaParamer,cozinha,"id","restaurantes");
		
		return cozinhaRepositorio.save(cozinha);
	}
	
	@Transactional
	public void remover(Long cozinhaId){
		
		if(!cozinhaRepositorio.existsById(cozinhaId)) {
			throw new CozinhaNaoEncontradaException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		}
		
		Cozinha cozinha = buscarId(cozinhaId);
		
		if(restauranteRepository.existsByCozinha(cozinha)) {
			throw new CozinhaEmUsoException(
					String.format(MSG_COZINHA_ESTÁ_EM_USO, cozinhaId));
		}
		cozinhaRepositorio.deleteById(cozinhaId);
	}
	
	
	public List<Cozinha> consultarUnicaPorNome(String nome) {
		
		if (nome.isEmpty()) {
			System.out.println("vazia");
			throw new EntidadeVaziaException("String vazia");
		}
		return cozinhaRepositorio.findPodemos_Adicionar_Qualquer_CoisaByNome(nome);
	}
	
	
	public List<Cozinha> consultarPorNome(String nome) {
		
		if (nome.isEmpty()) {
			System.out.println("vazia");
			throw new EntidadeVaziaException("String vazia");
		}
		return cozinhaRepositorio.findByNomeContaining(nome);
	}
}
