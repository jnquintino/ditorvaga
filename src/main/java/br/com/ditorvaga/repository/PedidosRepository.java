package br.com.ditorvaga.repository;

import br.com.ditorvaga.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Long> {

    Pedido findByNumeroControle(long numeroControle);

    Pedido findByNumeroControleOrDataCadastro(long numeroControle, LocalDate dataCadastro);

}
