package com.msavaliadorcredito.api.infra;

import com.msavaliadorcredito.api.domain.model.Cartao;
import com.msavaliadorcredito.api.domain.model.CartoesCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoResourceClient {
    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesCliente>> getCartoesByCliente(
            @RequestParam("cpf")String cpf);



    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);

}
