package com.vr.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.projeto.model.Cartao;


@Repository
public interface CartaoRepository extends JpaRepository<Cartao,String>{
	List<Cartao> findByNumero(String numero);
}
