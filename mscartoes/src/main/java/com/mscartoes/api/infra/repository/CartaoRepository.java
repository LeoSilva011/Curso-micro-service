package com.mscartoes.api.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscartoes.api.domain.Cartao;

public interface CartaoRepository  extends JpaRepository<Cartao, Long>{

	List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);

}
