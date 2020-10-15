package br.com.alura.microservice.loja.loja.client;

import br.com.alura.microservice.loja.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fornecedor")
public interface FornecedorClient {

    @GetMapping(value = "/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable("estado") String estado);
}
