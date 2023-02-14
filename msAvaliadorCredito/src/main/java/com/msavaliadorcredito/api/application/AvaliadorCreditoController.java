package com.msavaliadorcredito.api.application;

import com.msavaliadorcredito.api.application.ex.DadosClienteNotFoundException;
import com.msavaliadorcredito.api.application.ex.ErroComunicacaoMicroserviceException;
import com.msavaliadorcredito.api.application.ex.ErroSolicitacaoCartaoException;
import com.msavaliadorcredito.api.domain.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacoes-credito")
public class AvaliadorCreditoController {
	
	
	private final AvaliadorCreditoService avaliadorCreditoService;
	
	
	
	
	public AvaliadorCreditoController(AvaliadorCreditoService avaliadorCreditoService) {
		super();
		this.avaliadorCreditoService = avaliadorCreditoService;
	}


	@GetMapping
	public String status() {
		return "API de avaliaçõa de credito OK";
	}


	@GetMapping(value = "situacao-cliente", params = "cpf")
	public ResponseEntity consultaSituacaoCiente(@RequestParam("cpf") String cpf){

		try {
			SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (ErroComunicacaoMicroserviceException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}


	}

	@PostMapping
	public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
		try {

			RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
			return ResponseEntity.ok(retornoAvaliacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (ErroComunicacaoMicroserviceException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
	}

	@PostMapping("solicitacoes-cartao")
	public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
		try {
			ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService
					.solicitarEmissaoCartao(dados);
			return ResponseEntity.ok(protocoloSolicitacaoCartao);

		}catch (ErroSolicitacaoCartaoException e){
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
