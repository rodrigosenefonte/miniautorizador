package com.vr.miniautorizador.application.enumerator;

import java.math.BigDecimal;

public enum CartaoEnum {
    CARTAO_INEXISTENTE("CARTAO_INEXISTENTE"),
    SENHA_INVALIDA("SENHA_INVALIDA"),
    SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"),
    OK("OK"),
    VALOR_SALDO("VALOR", new BigDecimal(500.00));

    private final String descricao;
    private final BigDecimal valorSaldo;

    CartaoEnum(String descricao) {
        this.descricao = descricao;
        this.valorSaldo = BigDecimal.ZERO;
    }

    CartaoEnum(String descricao, BigDecimal valorSaldo) {
        this.descricao = descricao;
        this.valorSaldo = valorSaldo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorSaldo() {
        return valorSaldo;
    }
}
