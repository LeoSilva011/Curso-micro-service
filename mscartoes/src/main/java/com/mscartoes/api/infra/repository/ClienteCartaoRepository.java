package com.mscartoes.api.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscartoes.api.domain.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long>{
	
	List<ClienteCartao> findByCpf(String cpf);

}
