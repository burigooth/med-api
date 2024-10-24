package com.med.api.controller;

import com.med.api.domain.usuario.DadosAutenticacao;
import com.med.api.domain.usuario.Usuario;
import com.med.api.infra.security.DadosTokenJWT;
import com.med.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efeturLogin(@RequestBody @Valid DadosAutenticacao dados){
        // Cria um token de autenticação com os dados de login e senha baseado no nosso DTO
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticado = authManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) autenticado.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}