package com.msclientes.api.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.msclientes.api.domain.Cliente;
import com.msclientes.api.infra.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository repository;
	
	
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}


	@Transactional
	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}

	
	public Optional<Cliente> getByCPF(String cpf){
		return repository.findByCpf(cpf);
	}
}
