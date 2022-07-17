package br.com.ditorvaga.dto;

import br.com.ditorvaga.model.Pedido;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoResponseDTO {
    private List<Pedido> pedidos;
    private double valor = 0d;
}
