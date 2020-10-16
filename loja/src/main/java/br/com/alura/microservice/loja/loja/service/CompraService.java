package br.com.alura.microservice.loja.loja.service;

import br.com.alura.microservice.loja.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.loja.controller.dto.*;
import br.com.alura.microservice.loja.loja.model.Compra;
import br.com.alura.microservice.loja.loja.model.CompraState;
import br.com.alura.microservice.loja.loja.repository.CompraRepository;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private FornecedorClient fornecedorClient;

    private TransportadorClient transportadorClient;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallBack", threadPoolKey = "realizaCompraTrheadPool")
    public Compra realizaCompra(CompraDTO compraDTO) {

        Compra novaCompra = new Compra(CompraState.RECEBIDO);
        novaCompra.setEndereco(compraDTO.getEndereco().toString());
        compraRepository.save(novaCompra);

        LOG.info("Buscando informações do fornecedor de {}", compraDTO.getEndereco().getEstado());
        InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());

        LOG.info("Endereço do fornecedor: " + infoFornecedorDTO.getEndereco());

        LOG.info("Realizando um pedido");
        InforPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        LOG.info("Pedido realizado com sucesso: {}", pedido);

        novaCompra.setPedidoId(pedido.getId());
        novaCompra.setTempoDePreparo(pedido.getTempoDePreparo());
        novaCompra.setCompraState(CompraState.REALIZADO);
        compraRepository.save(novaCompra);

        LOG.info("Gerando o voucher");

        InfoEntregaDTO infoEntregaDTO = new InfoEntregaDTO(pedido.getId(), LocalDate.now().plusDays(pedido.getTempoDePreparo()), infoFornecedorDTO.getEndereco(), compraDTO.getEndereco().toString());
        VoucherDTO voucherDTO = transportadorClient.reservaEntrega(infoEntregaDTO);

        LOG.info("Voucher gerado com sucesso");

        novaCompra.setDataParaEntrega(voucherDTO.getPrevisaoParaEntrega());
        novaCompra.setVoucher(voucherDTO.getNumero());
        novaCompra.setCompraState(CompraState.RESERVA_ENTREGA_REALIZADA);

        compraRepository.save(novaCompra);

        return novaCompra;
    }

    public Compra realizaCompraFallBack(CompraDTO compraDTO) {
        return new Compra(null, null, compraDTO.getEndereco().toString(), null, null);
    }

}
