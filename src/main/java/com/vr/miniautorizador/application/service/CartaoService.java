package com.vr.miniautorizador.application.service;

import com.vr.miniautorizador.application.model.Cartao;
import com.vr.miniautorizador.application.model.Transacao;
import com.vr.miniautorizador.application.repository.CartaoRepository;
import com.vr.miniautorizador.application.vo.CartaoVO;
import com.vr.miniautorizador.application.vo.SaldoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    public Cartao criarCartao(Cartao cartao) {
        cartao.setSaldo(new BigDecimal("500.00"));
        return repository.save(cartao);
    }

    public Cartao buscarCartao(String numeroCartao) {
        return repository.findByNumeroCartao(numeroCartao);
    }

    public String realizarTransacao(Transacao transacao) {
        Cartao cartao = buscarCartao(transacao.getNumeroCartao());

        String statusCartao = cartao == null ? "CARTAO_INEXISTENTE"
                : !cartao.getSenha().equals(transacao.getSenha()) ? "SENHA_INVALIDA"
                : cartao.getSaldo().subtract(transacao.getValor()).compareTo(BigDecimal.ZERO) < 0 ? "SALDO_INSUFICIENTE"
                : "OK";

        Optional.ofNullable(cartao)
                .filter(c -> "OK".equals(statusCartao))
                .ifPresent(c -> {
                    c.setSaldo(c.getSaldo().subtract(transacao.getValor()));
                    repository.save(c);
                });

        return statusCartao;
    }

    public CartaoVO criarCartaoVO(Cartao cartao){
        var vo = new CartaoVO();
        vo.setNumeroCartao(cartao.getNumeroCartao());
        vo.setSenha(cartao.getSenha());
        return vo;
    }

    public SaldoVO criarSaldoVO(Cartao cartao){
        var vo = new SaldoVO();
        vo.setSaldo(cartao.getSaldo());
        return vo;
    }
}