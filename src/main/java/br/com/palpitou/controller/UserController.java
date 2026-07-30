package br.com.palpitou.controller;


import br.com.palpitou.dto.PutRequestUser;
import br.com.palpitou.dto.PutResponseUser;
import br.com.palpitou.dto.UserRequest;
import br.com.palpitou.dto.UserResponse;


import br.com.palpitou.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping
    public ResponseEntity<UserResponse> salvar(
            @RequestBody @Valid UserRequest request) {

        UserResponse resposta = userService.salvar(request);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listarTodos() {

        var resposta = userService.listarTodos();

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}/Me")
    public ResponseEntity<PutResponseUser> atualizarUser(
            @PathVariable Long id,
            @RequestBody @Valid PutRequestUser request
    ) {
        var resposta = userService.updateUser(id, request);

        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}/Role")
    public ResponseEntity<UserResponse> atualizarAdmin(

            @PathVariable Long id,
            @RequestBody @Valid UserRequest request
    ){
        var resposta = userService.updateAdmin(id, request);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {

      var resposta = userService.buscar(id);
      return ResponseEntity.ok(resposta);
    }
}
