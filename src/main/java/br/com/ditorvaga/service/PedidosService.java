package br.com.ditorvaga.service;

import br.com.ditorvaga.dto.ListPedidosRequestDTO;
import br.com.ditorvaga.dto.PedidoRequestDTO;
import br.com.ditorvaga.dto.PedidoResponseDTO;
import br.com.ditorvaga.exception.NumeroControleException;
import br.com.ditorvaga.model.Cliente;
import br.com.ditorvaga.model.Pedido;
import br.com.ditorvaga.repository.PedidosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidosService {
    private PedidosRepository repository;

    public PedidoResponseDTO inserirPedidos(ListPedidosRequestDTO request) throws NumeroControleException {
        PedidoResponseDTO result = new PedidoResponseDTO();
        result.setPedidos(new LinkedList<>());
        for (PedidoRequestDTO dto: request.getListaDePedidos()) {
            Pedido pedido = this.repository.findByNumeroControle(dto.getNumeroControle());
            if (pedido != null) {
                //TODO Verificar depois se quer que processe os outros a seguir
                throw new NumeroControleException();
            }
            int desconto = 0;
            if (dto.getQuantidade() > 5) {
                desconto = 5;
                if (dto.getQuantidade() > 10) {
                    desconto = 10;
                }
            }
            pedido = this.montaPedido(dto, desconto);
            result.setValor(result.getValor() + pedido.getValor() - pedido.getDesconto());
            result.getPedidos().add(pedido);
            this.repository.save(pedido);
        }
        return result;
    }

    private Pedido montaPedido(PedidoRequestDTO dto, int desconto) {
        Pedido result = new Pedido();
        result.setNumeroControle(dto.getNumeroControle());
        result.setDataCadastro(dto.getDataCadastro());
        result.setNome(dto.getNome());
        result.setValor(dto.getValor());
        result.setDesconto(dto.getValor() * desconto / 100);
        result.setQuantidade(dto.getQuantidade());
        result.setCliente(new Cliente());
        result.getCliente().setId(dto.getCodigoCliente());
        return result;
    }

    public Pedido getPedido(long id) {
        Optional<Pedido> result = this.repository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public List<Pedido> obterTodos() {
        return this.repository.findAll();
    }

    public Pedido filtrarPedido(long numeroPedido, LocalDate dataCadastro) {
        return this.repository.findByNumeroControleOrDataCadastro(numeroPedido, dataCadastro);
    }
}
