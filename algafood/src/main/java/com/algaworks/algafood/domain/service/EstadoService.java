package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.estadoexception.EstadoEmUsoException;
import com.algaworks.algafood.domain.exception.estadoexception.EstadoExistenteException;
import com.algaworks.algafood.domain.exception.estadoexception.EstadoNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	private static final String MSG_ESTADO_EXISTENTE = 
			"Estado existente na base de dados."
					+"Caso necessário atualize o seu cadastro";

	private static final String MSG_ESTADO_EM_USO  = 
	        "Estado de código %d não pode ser removido, pois está em uso";

	private static final String MSG_ESTADO_NAO_ENCONTRADO = 
	        "Não existe um cadastro de estado com código %d";

	
	private final EstadoRepository estadoRepository;

	public EstadoService(EstadoRepository estadoRepository) {
		this.estadoRepository = estadoRepository;
	}
	
	public Estado buscarId(Long estadoId) {
		return estadoRepository
			.findById(estadoId)
			.orElseThrow(() -> 
			new EstadoNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)) );
	}
	
	
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@Transactional
	public Estado cadastrar(Estado estado) {
		if(estadoRepository.existsById(estado.getId())) {
			throw new EstadoExistenteException(
					String.format(MSG_ESTADO_EXISTENTE, estado.getId()));
		}
		return estadoRepository.save(estado);
	}
	
	
	@Transactional
	public Estado atualizar(Estado estadoParamer) {
		Estado estado = buscarId(estadoParamer.getId());
		BeanUtils.copyProperties(estadoParamer, estado,"id");
		return estado;
	}
	
	
	@Transactional
	public void remover(Long estadoId) {
		try {
			estadoRepository.delete(buscarId(estadoId));
		} catch(DataIntegrityViolationException e){
			throw new EstadoEmUsoException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
}
