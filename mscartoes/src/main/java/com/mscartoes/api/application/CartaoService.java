package com.mscartoes.api.application;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.mscartoes.api.domain.Cartao;
import com.mscartoes.api.infra.repository.CartaoRepository;

@Service
public class CartaoService {
	
	private final CartaoRepository repository;
	
	
	
	
	public CartaoService(CartaoRepository repository) {
		this.repository = repository;
	}



	@Transactional
	public Cartao save(Cartao cartao) {
		return repository.save(cartao);
	}
	
	public List<Cartao> getCartoesRendaMenorIgua(Long renda){
		var rendaBigDecimal = BigDecimal.valueOf(renda);
		return repository.findByRendaLessThanEqual(rendaBigDecimal);
	}
	

}
