package br.com.palpitou.controller;

import br.com.palpitou.dto.request.PremiacaoRequest;
import br.com.palpitou.dto.response.PremiacaoResponse;
import br.com.palpitou.service.PremiacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/premiacao")
public class PremiacaoController {

    private final PremiacaoService premiacaoService;


    @PostMapping
    public ResponseEntity<PremiacaoResponse> salvar(
            @RequestBody @Valid PremiacaoRequest request) {


        PremiacaoResponse resposta =
                premiacaoService.salvar(request);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PremiacaoResponse> buscar(
            @PathVariable Long id) {

        var response =
                premiacaoService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PremiacaoResponse>> listarTodos() {

        var response =
                premiacaoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        premiacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
