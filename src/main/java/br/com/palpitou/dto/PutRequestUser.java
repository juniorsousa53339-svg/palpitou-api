package br.com.palpitou.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PutRequestUser {
    private String nome;
    private String email;
    private String senha;
}
