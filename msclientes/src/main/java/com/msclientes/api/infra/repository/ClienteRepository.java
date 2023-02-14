package com.msclientes.api.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msclientes.api.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByCpf(String cpf);

}
