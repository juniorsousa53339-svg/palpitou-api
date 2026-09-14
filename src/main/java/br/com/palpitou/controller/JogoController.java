package br.com.palpitou.controller;

import br.com.palpitou.dto.request.JogoRequest;
import br.com.palpitou.dto.response.JogoResponse;
import br.com.palpitou.dto.putRequest.PutRequestJogo;
import br.com.palpitou.dto.putResponse.PutResponseJogo;
import br.com.palpitou.service.JogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<JogoResponse> salvar(
            @RequestBody @Valid JogoRequest request) {

        JogoResponse resposta =
                jogoService.salvar(request);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<JogoResponse>> listarTodos() {

        var resposta =
                jogoService.listarTodos();

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        jogoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JogoResponse> buscar(@PathVariable Long id) {

        var  resposta = jogoService.buscar(id);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutResponseJogo> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PutRequestJogo putRequest
    ){

        var resposta = jogoService.updateJogo(id, putRequest);
        return ResponseEntity.ok(resposta);
    }
}
