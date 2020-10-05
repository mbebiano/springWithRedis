package br.com.ntendencia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.ContratoEmprestimo;

@Repository
public interface ContratoEmprestimoRepository extends CrudRepository<ContratoEmprestimo, String> {

}
