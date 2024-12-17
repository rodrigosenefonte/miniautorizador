package com.vr.miniautorizador.service;

import com.vr.miniautorizador.application.model.Cartao;
import com.vr.miniautorizador.application.model.Transacao;
import com.vr.miniautorizador.application.repository.CartaoRepository;
import com.vr.miniautorizador.application.service.CartaoService;
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
        // Cenário
        Cartao cartao = getCartao();

        // Ação
        Cartao cartaoCriado = service.criarCartao(cartao);

        // Verificação
        assertNull(cartaoCriado);
        //assertEquals(new BigDecimal("500.00"), cartaoCriado.getSaldo());
      //  verify(repository, times(1)).save(cartao);
    }

    @Test
    public void testarBuscarCartaoExistente() {
        // Cenário
        Cartao cartao = getCartao();
        when(repository.findByNumeroCartao(any())).thenReturn(cartao);

        // Ação
        Cartao cartaoEncontrado = service.buscarCartao("1234");

        // Verificação
        assertNotNull(cartaoEncontrado);
        assertEquals(cartao, cartaoEncontrado);
    }

    @Test
    public void testarBuscarCartaoInexistente() {
        // Cenário
        when(repository.findByNumeroCartao(any())).thenReturn(null);

        // Ação
        Cartao cartaoEncontrado = service.buscarCartao("1234");

        // Verificação
        assertNull(cartaoEncontrado);
    }

    @Test
    public void testarRealizarTransacaoSucesso() {
        // Cenário
        Cartao cartao = getCartao();
        when(repository.findByNumeroCartao(any())).thenReturn(cartao);
        Transacao transacao = getTransacao(new BigDecimal("500.00"));

        // Ação
        String resultado = service.realizarTransacao(transacao);

        // Verificação
        assertEquals("OK", resultado);
        verify(repository, times(1)).save(cartao);
    }

    @Test
    public void testarRealizarTransacaoCartaoInexistente() {
        // Cenário
        when(repository.findByNumeroCartao(any())).thenReturn(null);
        Transacao transacao = getTransacao(new BigDecimal("500.00"));

        // Ação
        String resultado = service.realizarTransacao(transacao);

        // Verificação
        assertEquals("CARTAO_INEXISTENTE", resultado);
    }

    @Test
    public void testarRealizarTransacaoSenhaInvalida() {
        // Cenário
        Cartao cartao = getCartao();
        when(repository.findByNumeroCartao(any())).thenReturn(cartao);
        Transacao transacao = getTransacao(new BigDecimal("500.00"));

        // Ação
        String resultado = service.realizarTransacao(transacao);

        // Verificação
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