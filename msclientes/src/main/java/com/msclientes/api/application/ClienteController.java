package com.msclientes.api.application;

import java.net.URI;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.msclientes.api.application.reresentation.ClienteSaveRequest;



@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

	private static final Logger log  = LoggerFactory.getLogger(ClienteController.class);
	
	private final ClienteService service;
	

	@GetMapping
	public String status() {
		return "API de Clientes OK";
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteSaveRequest request) {
		var cliente = request.toModel();
		service.save(cliente);
		URI headerLocation = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.query("cpf={cpf}")
				.buildAndExpand(cliente.getCpf())
				.toUri();
		return ResponseEntity.created(headerLocation).build();
	}
	
	
	@GetMapping(params = "cpf")
	public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
		var cliente = service.getByCPF(cpf);
		if(cliente.isEmpty()) {
			return ResponseEntity.notFound().build();		}
	
	return ResponseEntity.ok(cliente);
	}

}
