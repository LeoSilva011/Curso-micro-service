package com.msclientes.api.application.reresentation;

import com.msclientes.api.domain.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {
	
	private String cpf;
	private String nome;
	private String idade;
	
	
	public Cliente toModel() {
		return new Cliente(cpf, nome, idade);
	}
	
	
	
	

	

}
