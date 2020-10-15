package br.com.alura.microservice.loja.loja.controller.dto;

public class InforPedidoDTO {

    private Long id;
    private Integer tempoDePreparo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }

    @Override
    public String toString() {
        return "InforPedidoDTO{" +
                "id=" + id +
                ", tempoDePreparo=" + tempoDePreparo +
                '}';
    }
}
