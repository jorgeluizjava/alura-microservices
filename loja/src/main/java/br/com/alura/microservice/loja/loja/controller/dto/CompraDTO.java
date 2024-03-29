package br.com.alura.microservice.loja.loja.controller.dto;

import java.util.List;

public class CompraDTO {

    public List<ItemCompraDTO> itens;

    public EnderecoDTO endereco;

    public List<ItemCompraDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraDTO> itens) {
        this.itens = itens;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
