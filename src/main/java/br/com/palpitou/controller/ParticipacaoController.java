package br.com.palpitou.controller;

import br.com.palpitou.dto.ParticipacaoRequest;
import br.com.palpitou.dto.ParticipacaoResponse;
import br.com.palpitou.dto.PutRequestParticipacao;
import br.com.palpitou.dto.PutResponseParticipacao;
import br.com.palpitou.service.ParticipacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/participacoes")
public class ParticipacaoController {

    private final ParticipacaoService participacaoService;

    @PostMapping
    public ResponseEntity<ParticipacaoResponse> salvar(
            @RequestBody @Valid ParticipacaoRequest request){

          ParticipacaoResponse response =
                  participacaoService.salvar(request);

          return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ParticipacaoResponse>> listarTodos(){

        var response =
        participacaoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        participacaoService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipacaoResponse> buscar(@PathVariable Long id){
        var response =
        participacaoService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutResponseParticipacao> atualizar(
            @PathVariable Long id,
            @RequestBody PutRequestParticipacao request
    ){

        var resposta = participacaoService.updateParticipacao(id, request);
        return ResponseEntity.ok(resposta);
    }
}
