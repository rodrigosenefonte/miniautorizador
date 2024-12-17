package com.vr.miniautorizador.application.repository;


import com.vr.miniautorizador.application.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Cartao findByNumeroCartao(String numeroCartao);
}
