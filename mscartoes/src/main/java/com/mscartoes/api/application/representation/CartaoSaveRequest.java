package com.mscartoes.api.application.representation;

import java.math.BigDecimal;

import com.mscartoes.api.domain.BandeiraCartao;
import com.mscartoes.api.domain.Cartao;

public class CartaoSaveRequest {
	//essa classe e o  DTO
	private String nome;
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limite;
	
	public Cartao toModel() {
		return new Cartao(nome, bandeira, renda, limite);
		
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BandeiraCartao getBandeira() {
		return bandeira;
	}
	public void setBandeira(BandeiraCartao bandeira) {
		this.bandeira = bandeira;
	}
	public BigDecimal getRenda() {
		return renda;
	}
	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}
	public BigDecimal getLimite() {
		return limite;
	}
	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
	
	
	
	

}
