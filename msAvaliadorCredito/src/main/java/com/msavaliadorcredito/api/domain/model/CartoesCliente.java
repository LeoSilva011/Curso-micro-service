package com.msavaliadorcredito.api.domain.model;

import java.math.BigDecimal;

public class CartoesCliente {
	
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
	
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
