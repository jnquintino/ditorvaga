package br.com.ditorvaga.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PedidoRequestDTO {
    @NotNull
    private long numeroControle;
    private LocalDate dataCadastro = LocalDate.now();
    @NotNull
    private String nome;
    @NotNull
    private double valor;
    private long quantidade = 1;
    @NotNull
    private long codigoCliente;
}
