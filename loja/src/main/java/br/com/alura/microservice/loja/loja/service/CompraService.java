package br.com.alura.microservice.loja.loja.service;

import br.com.alura.microservice.loja.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InforPedidoDTO;
import br.com.alura.microservice.loja.loja.model.Compra;
import br.com.alura.microservice.loja.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    private FornecedorClient fornecedorClient;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallBack", threadPoolKey = "realizaCompraTrheadPool")
    public Compra realizaCompra(CompraDTO compraDTO) {

        LOG.info("Buscando informações do fornecedor de {}", compraDTO.getEndereco().getEstado());
        InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());

        LOG.info("Endereço do fornecedor: " + infoFornecedorDTO.getEndereco());

        LOG.info("Realizando um pedido");
        InforPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        LOG.info("Pedido realizado com sucesso: {}", pedido);

        return new Compra(pedido.getId(), pedido.getTempoDePreparo(), compraDTO.getEndereco().toString());
    }

    public Compra realizaCompraFallBack(CompraDTO compraDTO) {
        return new Compra(null, null, compraDTO.getEndereco().toString());
    }

}
