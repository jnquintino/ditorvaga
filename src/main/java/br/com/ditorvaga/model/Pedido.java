package br.com.ditorvaga.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private long numeroControle;

    @Column
    private LocalDate dataCadastro = LocalDate.now();

    @Column
    private String nome;

    @Column
    private double valor;

    @Column
    private long quantidade = 1;

    @ManyToOne
    @JoinColumn(name = "codigoCliente")
    private Cliente cliente;

    @Column
    private double desconto;

}
