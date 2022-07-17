package br.com.ditorvaga.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListPedidosRequestDTO {
    private List<PedidoRequestDTO> listaDePedidos;
}
