package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoFornecedorController {

    @Autowired
    private InfoService infoService;

    @GetMapping(value = "/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable("estado") String estado) {
        return infoService.getInfoPorEstado(estado);
    }
}
