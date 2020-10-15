package br.com.alura.microservice.loja.loja.client;

import br.com.alura.microservice.loja.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InforPedidoDTO;
import br.com.alura.microservice.loja.loja.controller.dto.ItemCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "fornecedor")
public interface FornecedorClient {

    @GetMapping(value = "/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable("estado") String estado);

    InforPedidoDTO realizaPedido(List<ItemCompraDTO> itens);
}
