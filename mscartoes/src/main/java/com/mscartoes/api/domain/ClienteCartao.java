package com.mscartoes.api.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ClienteCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	
	@ManyToOne
	@JoinColumn(name="id_cartao")
	private Cartao cartao;
	private BigDecimal limite;
	
	
	
	
	
	public ClienteCartao(Long id, String cpf, Cartao cartao, BigDecimal limite) {
		this.cpf = cpf;
		this.cartao = cartao;
		this.limite = limite;
	}
	
	
	public ClienteCartao() {
		super();
	}


	public Long getId() {
		return id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public BigDecimal getLimite() {
		return limite;
	}
	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
	
	
	
	
	
}
