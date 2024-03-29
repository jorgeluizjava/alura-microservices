package br.com.alura.microservice.loja.loja.client;

import br.com.alura.microservice.loja.loja.controller.dto.InfoEntregaDTO;
import br.com.alura.microservice.loja.loja.controller.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "fornecedor")
public interface TransportadorClient {

    @PostMapping(value = "/entrega")
    public VoucherDTO reservaEntrega(@RequestBody InfoEntregaDTO entregaDTO);
}
