package br.com.palpitou.controller;

import br.com.palpitou.dto.CampeonatoRequest;
import br.com.palpitou.dto.CampeonatoResponse;
import br.com.palpitou.service.CampeonatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campeonatos")
@RequiredArgsConstructor
public class CampeonatoController {

    private final CampeonatoService campeonatoService;


    @PostMapping
    public ResponseEntity<CampeonatoResponse> salvar(
            @RequestBody @Valid CampeonatoRequest request) {

        CampeonatoResponse resposta =
                campeonatoService.salvar(request);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<CampeonatoResponse>> listarTodos() {

        var resposta =
                campeonatoService.listarTodos();

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampeonatoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid CampeonatoRequest request
    ) {

        var resposta = campeonatoService.updateCamp(id, request);

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        campeonatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoResponse> buscar(@PathVariable Long id) {

        var  resposta = campeonatoService.buscar(id);
        return ResponseEntity.ok(resposta);
    }
}
