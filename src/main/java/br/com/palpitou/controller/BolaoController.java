package br.com.palpitou.controller;

import br.com.palpitou.dto.*;
import br.com.palpitou.entity.Bolao;
import br.com.palpitou.service.BolaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boloes")
@RequiredArgsConstructor
public class BolaoController {

    private final BolaoService bolaoService;

    @PostMapping
    public ResponseEntity<BolaoResponse> salvar(
            @RequestBody @Valid BolaoRequest request){

        BolaoResponse response =
                bolaoService.salvar(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BolaoResponse>> listarTodos(){

        var response =
                bolaoService.listarTodos();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        bolaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolaoResponse> buscar(@PathVariable Long id){
        var response =
                bolaoService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutResponseBolao> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PutRequestBolao put
    ){
        var resposta = bolaoService.updateBolao(id,put);
        return ResponseEntity.ok(resposta);
    }
}
