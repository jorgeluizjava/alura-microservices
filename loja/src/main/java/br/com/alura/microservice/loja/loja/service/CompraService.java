package br.com.alura.microservice.loja.loja.service;

import br.com.alura.microservice.loja.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {


    public void realizaCompra(CompraDTO compraDTO) {
        RestTemplate client = new RestTemplate();
        ResponseEntity<InfoFornecedorDTO> exchange = client
                                                        .exchange("http://fornecedor/info/" + compraDTO.getEndereco().getEstado(),
                                                        HttpMethod.GET, null, InfoFornecedorDTO.class);

        InfoFornecedorDTO body = exchange.getBody();
        System.out.println(body.getEndereco());

    }
}
