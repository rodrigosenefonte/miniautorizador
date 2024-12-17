package com.vr.miniautorizador.application.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Transacao {

    private String numeroCartao;
    private String senha;
    private BigDecimal valor;

}
