package br.com.alura.microservice.loja.loja.repository;


import br.com.alura.microservice.loja.loja.model.Compra;
import org.springframework.data.repository.CrudRepository;

public interface CompraRepository extends CrudRepository<Compra, Long> {
}
