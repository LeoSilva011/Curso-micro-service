package com.mscartoes.api.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private BandeiraCartao bandeira;
	private BigDecimal renda;
	private BigDecimal limiteBasico;
	
	
	
	
	
	public Cartao() {
	}
	public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
		this.nome = nome;
		this.bandeira = bandeira;
		this.renda = renda;
		this.limiteBasico = limiteBasico;
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
	public BigDecimal getLimiteBasico() {
		return limiteBasico;
	}
	public void setLimiteBasico(BigDecimal limiteBasico) {
		this.limiteBasico = limiteBasico;
	}
	public Long getId() {
		return id;
	}
	
	
	

}
