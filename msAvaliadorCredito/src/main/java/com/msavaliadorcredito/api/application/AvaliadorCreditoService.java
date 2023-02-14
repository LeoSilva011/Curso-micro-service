package com.msavaliadorcredito.api.application;

import com.msavaliadorcredito.api.application.ex.DadosClienteNotFoundException;
import com.msavaliadorcredito.api.application.ex.ErroComunicacaoMicroserviceException;
import com.msavaliadorcredito.api.application.ex.ErroSolicitacaoCartaoException;
import com.msavaliadorcredito.api.domain.model.*;
import com.msavaliadorcredito.api.infra.CartaoResourceClient;
import com.msavaliadorcredito.api.infra.msqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msavaliadorcredito.api.infra.ClienteResourceClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {


	private final ClienteResourceClient clientResourceClient;
	private final CartaoResourceClient cartaoResourceClient;
	private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

	public SituacaoCliente obterSituacaoCliente(String cpf)
			throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
		//obter dados do cliente da api -- mscliente
		//obter cartoes do cliente -mscartoes

		try {


			ResponseEntity<List<CartoesCliente>> cartaoResponse = cartaoResourceClient.getCartoesByCliente(cpf);
			ResponseEntity<DadosCliente> dadosClienteResponse = clientResourceClient.dadosCliente(cpf);
			return SituacaoCliente
					.builder()
					.cliente(dadosClienteResponse.getBody())
					.cartoes(cartaoResponse.getBody())
					.build();
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
		}
	}

	public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
			throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clientResourceClient.dadosCliente(cpf);
			ResponseEntity<List<Cartao>> cartoesResponse = cartaoResourceClient.getCartoesRendaAte(renda);

			List<Cartao> cartoes = cartoesResponse.getBody();
			var listaCartoesAprovados = cartoes.stream().map(cartao -> {
				DadosCliente dadosCliente = dadosClienteResponse.getBody();


				BigDecimal limiteBasico = cartao.getLimiteBasico();
				BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

				var fator = idadeBD.divide(BigDecimal.valueOf(10));
				BigDecimal limiteAprovado = fator.multiply(limiteBasico);

				CartaoAprovado aprovado = new CartaoAprovado();
				aprovado.setNome(cartao.getNome());
				aprovado.setBandeira(cartao.getBandeira());
				aprovado.setLimiteAprovado(limiteAprovado);

				return aprovado;
			}).collect(Collectors.toList());
			return new RetornoAvaliacaoCliente(listaCartoesAprovados);

		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
		}

	}


	public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
		try {
			emissaoCartaoPublisher.solicitarCartao(dados);
			var protocolo = UUID.randomUUID().toString();
			return new ProtocoloSolicitacaoCartao(protocolo);

		} catch (Exception e) {
			throw new ErroSolicitacaoCartaoException(e.getMessage());
		}
	}
}
