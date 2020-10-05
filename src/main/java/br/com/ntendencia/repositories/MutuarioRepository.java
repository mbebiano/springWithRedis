package br.com.ntendencia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.Mutuario;

@Repository
public interface MutuarioRepository extends CrudRepository<Mutuario, String> {

}
