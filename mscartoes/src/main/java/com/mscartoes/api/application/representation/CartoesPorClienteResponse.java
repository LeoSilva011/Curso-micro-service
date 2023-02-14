package com.mscartoes.api.application.representation;

import java.math.BigDecimal;

import com.mscartoes.api.domain.ClienteCartao;

public class CartoesPorClienteResponse {
	
	
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
	
	
	public CartoesPorClienteResponse(String nome, String bandeira, BigDecimal limiteLiberado) {
		super();
		this.nome = nome;
		this.bandeira = bandeira;
		this.limiteLiberado = limiteLiberado;
	}


	public CartoesPorClienteResponse() {
		super();
	}


	public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
		return new CartoesPorClienteResponse(
				model.getCartao().getNome(),
				model.getCartao().getBandeira().toString(),
				model.getLimite()
				);
				
				
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	public BigDecimal getLimiteLiberado() {
		return limiteLiberado;
	}
	public void setLimiteLiberado(BigDecimal limiteLiberado) {
		this.limiteLiberado = limiteLiberado;
	}
	
	
	
	
	

}
