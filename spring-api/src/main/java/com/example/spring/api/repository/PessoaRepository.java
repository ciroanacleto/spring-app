package com.example.spring.api.repository;

import com.example.spring.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cletus on 10/01/19.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
