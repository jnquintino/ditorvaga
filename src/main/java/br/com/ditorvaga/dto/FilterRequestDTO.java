package br.com.ditorvaga.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FilterRequestDTO {
    private long numeroPedido;
    private LocalDate dataCadastro = LocalDate.now();
}
