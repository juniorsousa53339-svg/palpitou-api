package br.com.palpitou.controller;

import br.com.palpitou.dto.request.ParticipacaoRequest;
import br.com.palpitou.dto.response.ParticipacaoResponse;
import br.com.palpitou.dto.putRequest.PutRequestParticipacao;
import br.com.palpitou.dto.putResponse.PutResponseParticipacao;
import br.com.palpitou.service.ParticipacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ParticipacaoResponse>> listarTodos(){

        var response =
        participacaoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PutResponseParticipacao> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PutRequestParticipacao request
    ){

        var resposta = participacaoService.updateParticipacao(id, request);
        return ResponseEntity.ok(resposta);
    }
}
