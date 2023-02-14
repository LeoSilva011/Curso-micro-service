package com.mscartoes.api.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mscartoes.api.domain.ClienteCartao;
import com.mscartoes.api.infra.repository.ClienteCartaoRepository;

@Service
public class ClienteCartaoService {

	private final ClienteCartaoRepository repository;
	
	
	public ClienteCartaoService(ClienteCartaoRepository repository) {
		super();
		this.repository = repository;
	}


	public List<ClienteCartao> listaCartoesbyCpf(String cpf){
		return repository.findByCpf(cpf);
		
	}
	
}
