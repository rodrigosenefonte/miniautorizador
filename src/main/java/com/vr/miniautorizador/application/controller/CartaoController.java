package com.vr.miniautorizador.application.controller;


import com.vr.miniautorizador.application.service.CartaoService;
import com.vr.miniautorizador.application.model.Cartao;
import com.vr.miniautorizador.application.vo.CartaoVO;
import com.vr.miniautorizador.application.vo.SaldoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoVO> criarCartao(@RequestBody Cartao cartao) {
        Cartao c = service.buscarCartao(cartao.getNumeroCartao());
        return ResponseEntity.status(c == null ? 201 : 422).body(c == null ? service.criarCartaoVO(service.criarCartao(cartao)) : service.criarCartaoVO(c));
    }

    @GetMapping("/{numeroCartao}")
    public ResponseEntity<SaldoVO> buscarCartao(@PathVariable String numeroCartao) {
        Cartao c = service.buscarCartao(numeroCartao);
        return ResponseEntity.status(c != null ? 200 : 404).body(c != null ? service.criarSaldoVO(c) : null);
    }
}