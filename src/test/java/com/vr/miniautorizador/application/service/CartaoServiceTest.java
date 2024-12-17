package com.vr.miniautorizador.application.service;

import com.vr.miniautorizador.application.model.Cartao;
import com.vr.miniautorizador.application.model.Transacao;
import com.vr.miniautorizador.application.repository.CartaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartaoServiceTest {

    @Mock
    private CartaoRepository repository;

    @InjectMocks
    private CartaoService service;

    @Test
    public void testarCriarCartao() {

        Cartao cartao = getCartao();

        Cartao cartaoCriado = service.criarCartao(cartao);

        assertNull(cartaoCriado);
    }

    @Test
    public void testarBuscarCartaoExistente() {
        
        Cartao cartao = getCartao();
        when(repository.findByNumeroCartao(any())).thenReturn(cartao);

        Cartao cartaoEncontrado = service.buscarCartao("1234");

        assertNotNull(cartaoEncontrado);
        assertEquals(cartao, cartaoEncontrado);
    }

    @Test
    public void testarBuscarCartaoInexistente() {

        when(repository.findByNumeroCartao(any())).thenReturn(null);

        Cartao cartaoEncontrado = service.buscarCartao("1234");

        assertNull(cartaoEncontrado);
    }

    @Test
    public void testarRealizarTransacaoSucesso() {

        Cartao cartao = getCartao();
        when(repository.findByNumeroCartao(any())).thenReturn(cartao);
        Transacao transacao = getTransacao(new BigDecimal("500.00"));

        String resultado = service.realizarTransacao(transacao);

        assertEquals("OK", resultado);
        verify(repository, times(1)).save(cartao);
    }

    @Test
    public void testarRealizarTransacaoCartaoInexistente() {

        when(repository.findByNumeroCartao(any())).thenReturn(null);
        Transacao transacao = getTransacao(new BigDecimal("500.00"));

        String resultado = service.realizarTransacao(transacao);

        assertEquals("CARTAO_INEXISTENTE", resultado);
    }

    @Test
    public void testarRealizarTransacaoSenhaInvalida() {
        Cartao cartao = getCartao();
        when(repository.findByNumeroCartao(any())).thenReturn(cartao);
        Transacao transacao = getTransacao(new BigDecimal("500.00"));
        String resultado = service.realizarTransacao(transacao);
    }

    private Cartao getCartao() {
        var cartao = new Cartao();
        cartao.setNumeroCartao("123456789");
        cartao.setSenha("123");
        cartao.setSaldo(new BigDecimal("500.00"));
        return cartao;
    }

    private Transacao getTransacao(BigDecimal valor){
        var transacao = new Transacao();
        transacao.setValor(valor);
        transacao.setSenha("123");
        transacao.setNumeroCartao("123456789");
        return transacao;
    }

}