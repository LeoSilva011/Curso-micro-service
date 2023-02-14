package com.mscartoes.api.application;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mscartoes.api.application.representation.CartaoSaveRequest;
import com.mscartoes.api.application.representation.CartoesPorClienteResponse;
import com.mscartoes.api.domain.Cartao;
import com.mscartoes.api.domain.ClienteCartao;

@RestController
@RequestMapping("/cartoes")

public class CartaoController {

	
	private final CartaoService cartaoService;
	private final ClienteCartaoService clienteCartaoService;
	

	


	public CartaoController(CartaoService service, ClienteCartaoService clienteCartaoService) {
		this.cartaoService = service;
		this.clienteCartaoService = clienteCartaoService;
	}

	@GetMapping
	public String status() {
		return "API de cartões OK";
	}
	
	@PostMapping
	public ResponseEntity cadastra( @RequestBody CartaoSaveRequest request ) {
		Cartao cartao = request.toModel();
		cartaoService.save(cartao);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
		
	}
	@GetMapping(params = "renda")
	public ResponseEntity<List<Cartao>> getCartoesRendaAte( @RequestParam("renda") Long renda){
		List<Cartao> list = cartaoService.getCartoesRendaMenorIgua(renda);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(params = "cpf")
	public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(
			@RequestParam("cpf")String cpf){
		List<ClienteCartao> lista =  clienteCartaoService.listaCartoesbyCpf(cpf);
		List<CartoesPorClienteResponse>resultList = lista.stream()
				.map(CartoesPorClienteResponse::fromModel)
				.collect(Collectors.toList());
		return ResponseEntity.ok(resultList);
		
	}
	
	
}
