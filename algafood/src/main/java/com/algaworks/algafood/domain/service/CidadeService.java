package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.cidadeexception.CidadeExistenteException;
import com.algaworks.algafood.domain.exception.cidadeexception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private static final String CIDADE_EXISTENTE = 
			"Cidade existente na base de dados."
			+"Caso necessário atualize o seu cadastro";

	private static final String CIDADE_INEXISTENTE = 
			"Não existe um cadastro de cidade com o código %d";
	
	
	private final CidadeRepository cidadeRepository;

	public CidadeService(CidadeRepository cidadeRepository) {
		this.cidadeRepository = cidadeRepository;
	}
	
	
	public Cidade buscar(Long cidadeId) {
		return cidadeRepository
				.findById(cidadeId)
				.orElseThrow(() -> new CidadeNaoEncontradaException(
						String.format(CIDADE_INEXISTENTE, cidadeId)));
	}
	
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	@Transactional
	public Cidade cadastar(Cidade cidade) {
		if(cidadeRepository.existsById(cidade.getId())) {
			throw new CidadeExistenteException(
					String.format(CIDADE_EXISTENTE, cidade.getId())) ;
		}
		return cidadeRepository.save(cidade);
	}
	@Transactional
	public Cidade atualizar(Cidade cidadeParamer) {
		
		Cidade cidade = buscar(cidadeParamer.getId());
		BeanUtils.copyProperties(cidadeParamer, cidade,"id");
		
		return cidadeRepository.save(cidadeParamer);
	}
	
	@Transactional
	public void remover(Long cidadeId) {
		if(!cidadeRepository.existsById(cidadeId)) {
			throw new CidadeNaoEncontradaException(
					String.format(CIDADE_INEXISTENTE, cidadeId));
		}
		cidadeRepository.deleteById(cidadeId);
	}
	
}
