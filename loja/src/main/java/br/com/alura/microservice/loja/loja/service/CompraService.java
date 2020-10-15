package br.com.alura.microservice.loja.loja.service;

import br.com.alura.microservice.loja.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InforPedidoDTO;
import br.com.alura.microservice.loja.loja.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private Logger LOG = LoggerFactory.getLogger(CompraService.class);

    private FornecedorClient fornecedorClient;

    public Compra realizaCompra(CompraDTO compraDTO) {

        InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());

        LOG.info("Endereço do fornecedor: " + infoFornecedorDTO.getEndereco());

        InforPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        LOG.info("Pedido realizado com sucesso: {}", pedido);

        return new Compra(pedido.getId(), pedido.getTempoDePreparo(), compraDTO.getEndereco().toString());
    }
}
