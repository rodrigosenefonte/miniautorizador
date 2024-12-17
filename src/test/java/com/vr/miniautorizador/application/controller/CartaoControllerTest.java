package com.vr.miniautorizador.application.controller;

import com.vr.miniautorizador.application.model.Cartao;
import com.vr.miniautorizador.application.service.CartaoService;
import com.vr.miniautorizador.application.vo.CartaoVO;
import com.vr.miniautorizador.application.vo.SaldoVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartaoControllerTest {

    @Mock
    private CartaoService service;

    @InjectMocks
    private CartaoController controller;

    @Test
    public void testarCriarCartaoSucesso() {

        Cartao cartao = getCartao();
        when(service.buscarCartao(any())).thenReturn(null);
        when(service.criarCartao(any())).thenReturn(cartao);

        ResponseEntity<CartaoVO> resposta = controller.criarCartao(cartao);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNotNull(resposta);
    }

    @Test
    public void testarCriarCartaoJaExiste() {

        Cartao cartao = getCartao();
        when(service.buscarCartao(any())).thenReturn(cartao);

        ResponseEntity<CartaoVO> resposta = controller.criarCartao(cartao);

        assertEquals(422, resposta.getStatusCodeValue());
        assertNotNull(resposta);
    }

    @Test
    public void testarBuscarCartaoSucesso() {

        Cartao cartao = getCartao();
        when(service.buscarCartao(any())).thenReturn(cartao);

        ResponseEntity<SaldoVO> resposta = controller.buscarCartao("123");

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta);
    }

    @Test
    public void testarBuscarCartaoNaoEncontrado() {

        when(service.buscarCartao(any())).thenReturn(null);

        ResponseEntity<SaldoVO> resposta = controller.buscarCartao("1234");

        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals(null, resposta.getBody());
    }

    private Cartao getCartao(){
        Cartao cartao = new Cartao();
        cartao.setSaldo(new BigDecimal(123));
        cartao.setNumeroCartao("123456789");
        return cartao;
    }
}