package com.vr.miniautorizador.application.controller;

import com.vr.miniautorizador.application.model.Transacao;
import com.vr.miniautorizador.application.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(@RequestBody Transacao transacao) {
        String resultado = service.realizarTransacao(transacao);
        return ResponseEntity.status(resultado.equals("OK") ? 201 : 422).body(resultado);
    }
}
