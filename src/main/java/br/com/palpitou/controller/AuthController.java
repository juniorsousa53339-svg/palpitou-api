package br.com.palpitou.controller;


import br.com.palpitou.dto.request.LoginRequest;
import br.com.palpitou.dto.response.LoginResponse;
import br.com.palpitou.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

       LoginResponse loginResponse =
               authService.login(loginRequest);


       return ResponseEntity.ok(loginResponse);
    }
}
