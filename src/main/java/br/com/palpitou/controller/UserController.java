package br.com.palpitou.controller;


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

        userService.salvar(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
       return (ResponseEntity<List<UserResponse>>) userService.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UserRequest request
    ){
    userService.updateUser(id, request);
    return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
