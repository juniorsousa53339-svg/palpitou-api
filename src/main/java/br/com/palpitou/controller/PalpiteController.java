package br.com.palpitou.controller;

import br.com.palpitou.dto.*;
import br.com.palpitou.service.PalpiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palpites")
@RequiredArgsConstructor
public class PalpiteController {

    private final PalpiteService palpiteService;

    @PostMapping
    public ResponseEntity<PalpiteResponse> salvar(
            @RequestBody @Valid PalpiteRequest request){

        PalpiteResponse response =
                palpiteService.salvar(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PalpiteResponse>> listarTodos(){

        var response = palpiteService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        palpiteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalpiteResponse> buscar(@PathVariable Long id){
        var response = palpiteService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutResponsePalpite> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PutRequestPalpite put
    ){
        var response = palpiteService.update(id, put);
        return ResponseEntity.ok(response);
    }

}
