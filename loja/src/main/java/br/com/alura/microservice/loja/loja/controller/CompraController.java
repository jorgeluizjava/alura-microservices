package br.com.alura.microservice.loja.loja.controller;

import br.com.alura.microservice.loja.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.loja.model.Compra;
import br.com.alura.microservice.loja.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping(value = "/{id}")
    public Compra getById(@PathVariable("id") Long id) {
        return compraService.getById(id);
    }

    @PostMapping
    public void realizaCompra(@RequestBody CompraDTO compraDTO) {
        System.out.println(compraDTO);
        compraService.realizaCompra(compraDTO);
    }
}
