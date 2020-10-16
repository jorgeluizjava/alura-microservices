package br.com.alura.microservice.loja.loja.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    private Long id;
    private Integer tempoDePreparo;
    private String endereco;

    /**
     * Frameworks only
     */
    @Deprecated
    public Compra() {
    }

    public Compra(Long id, Integer tempoDePreparo, String endereco) {
        this.id = id;
        this.tempoDePreparo = tempoDePreparo;
        this.endereco = endereco;
    }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
