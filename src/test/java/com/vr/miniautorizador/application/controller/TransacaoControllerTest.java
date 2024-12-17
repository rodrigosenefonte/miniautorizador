package com.vr.miniautorizador.application.controller;

import com.vr.miniautorizador.application.model.Transacao;
import com.vr.miniautorizador.application.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    @Mock
    private CartaoService service;

    @InjectMocks
    private TransacaoController controller;

    @Test
    public void testarRealizarTransacaoSucesso() {

        Transacao transacao = getTransacao();
        when(service.realizarTransacao(any())).thenReturn("OK");

        ResponseEntity<String> resposta = controller.realizarTransacao(transacao);

        assertEquals(201, resposta.getStatusCodeValue());
        assertEquals("OK", resposta.getBody());
    }

    @Test
    public void testarRealizarTransacaoErro() {

        Transacao transacao = getTransacao();
        when(service.realizarTransacao(any())).thenReturn("erro");

        ResponseEntity<String> resposta = controller.realizarTransacao(transacao);

        assertEquals(422, resposta.getStatusCodeValue());
        assertEquals("erro", resposta.getBody());
    }

    @Test
    public void testarRealizarTransacaoNula() {

        Transacao transacao = null;
        when(service.realizarTransacao(any())).thenReturn("erro");

        ResponseEntity<String> resposta = controller.realizarTransacao(transacao);

        assertEquals(422, resposta.getStatusCodeValue());
    }

    private Transacao getTransacao() {
        var transacao = new Transacao();
        transacao.setNumeroCartao("123456789");
        transacao.setSenha("123");
        transacao.setValor(new BigDecimal(500.00));
        return transacao;
    }
}