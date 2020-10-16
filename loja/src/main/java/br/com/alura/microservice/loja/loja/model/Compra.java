package br.com.alura.microservice.loja.loja.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    private Integer tempoDePreparo;
    private String endereco;
    private LocalDate dataParaEntrega;
    private Long voucher;
    private CompraState compraState;

    /**
     * Frameworks only
     */
    @Deprecated
    public Compra() {
    }

    public Compra(CompraState compraState) {
        this.compraState = compraState;
    }

    public Compra(Long pedidoId, Integer tempoDePreparo, String endereco, LocalDate dataParaEntrega, Long voucher) {
        this.pedidoId = pedidoId;
        this.tempoDePreparo = tempoDePreparo;
        this.endereco = endereco;
        this.dataParaEntrega = dataParaEntrega;
        this.voucher = voucher;
    }

    public Long getId() {
        return id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataParaEntrega() {
        return dataParaEntrega;
    }

    public Long getVoucher() {
        return voucher;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataParaEntrega(LocalDate dataParaEntrega) {
        this.dataParaEntrega = dataParaEntrega;
    }

    public void setVoucher(Long voucher) {
        this.voucher = voucher;
    }

    public void setCompraState(CompraState compraState) {
        this.compraState = compraState;
    }
}
