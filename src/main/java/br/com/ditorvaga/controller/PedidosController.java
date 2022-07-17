package br.com.ditorvaga.controller;

import br.com.ditorvaga.dto.FilterRequestDTO;
import br.com.ditorvaga.dto.ListPedidosRequestDTO;
import br.com.ditorvaga.dto.ListPedidosRequestXMLDTO;
import br.com.ditorvaga.exception.BadRequestException;
import br.com.ditorvaga.exception.NotAcceptableException;
import br.com.ditorvaga.exception.NumeroControleException;
import br.com.ditorvaga.service.PedidosService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@AllArgsConstructor
public class PedidosController {
    private PedidosService service;

    private void validateRequest(ListPedidosRequestDTO request) throws BadRequestException, NotAcceptableException {
        if (request.getListaDePedidos() == null || request.getListaDePedidos().size() <= 0) {
            throw new BadRequestException();
        } else if (request.getListaDePedidos().size() > 10) {
            throw new NotAcceptableException();
        }
    }

    private ResponseEntity<?> processPedidos(ListPedidosRequestDTO request) {
        try {
            this.validateRequest(request);
            return new ResponseEntity<>(this.service.inserirPedidos(request), HttpStatus.CREATED);
        } catch (BadRequestException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotAcceptableException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (NumeroControleException exception) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
    }

    @PostMapping(value = "xml", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> inserirXML(@RequestBody ListPedidosRequestXMLDTO request) {
        return this.processPedidos(request);
    }

    @PostMapping()
    public ResponseEntity<?> inserirJson(@RequestBody ListPedidosRequestDTO request) {
        return this.processPedidos(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> obterPedido(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getPedido(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> obterTodos() {
        return new ResponseEntity<>(this.service.obterTodos(), HttpStatus.OK);
    }

    @PostMapping("filter")
    public ResponseEntity<?> filtrarPedido(@RequestBody FilterRequestDTO request) {
        return new ResponseEntity<>(this.service.filtrarPedido(request.getNumeroPedido(), request.getDataCadastro()), HttpStatus.OK);
    }

}
