package com.msavaliadorcredito.api.infra.msqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msavaliadorcredito.api.domain.model.DadosSolicitacaoEmissaoCartao;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes;


    public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        var json = convertInfoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), (Object) json);
    }

    private String convertInfoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dados);
        return json;
    }


}
