package br.com.alura.microservice.loja.loja.service;

import br.com.alura.microservice.loja.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InfoFornecedorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private Logger LOG = LoggerFactory.getLogger(CompraService.class);

    private FornecedorClient fornecedorClient;

    public void realizaCompra(CompraDTO compraDTO) {

        InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
        LOG.info("Endereço do fornecedor: " + infoFornecedorDTO.getEndereco());
    }
}
