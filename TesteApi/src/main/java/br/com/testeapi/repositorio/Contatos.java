package br.com.testeapi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testeapi.modelo.Contato;

public interface Contatos extends JpaRepository<Contato, Long> {

}