package br.com.palpitou.controller;

import br.com.palpitou.dto.TimeRequest;
import br.com.palpitou.dto.TimeResponse;
import br.com.palpitou.service.TimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
@RequiredArgsConstructor
public class TimeController {

    private final TimeService timeService;


    @PostMapping
    public ResponseEntity<TimeResponse> save(
            @RequestBody @Valid TimeRequest request) {

        TimeResponse response = timeService.salvar(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TimeResponse>> findAll() {

        var response = timeService.listarTodos();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TimeRequest request
    ){
        var response = timeService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        timeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeResponse> findById(@PathVariable Long id){
        var response = timeService.buscar(id);
        return ResponseEntity.ok(response);
    }
}
