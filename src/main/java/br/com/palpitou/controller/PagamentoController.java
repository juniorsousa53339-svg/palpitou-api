package br.com.palpitou.controller;


import br.com.palpitou.dto.*;
import br.com.palpitou.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponse> salvar(
            @RequestBody @Valid PagamentoRequest request){

        PagamentoResponse response =
                pagamentoService.salvar(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponse>> listarTodos(){

        var response = pagamentoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscar(@PathVariable Long id){
        var response = pagamentoService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutResponsePagamento> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PutRequestPagamento put
    ){
        var response = pagamentoService.update(id, put);
        return ResponseEntity.ok(response);
    }
}
