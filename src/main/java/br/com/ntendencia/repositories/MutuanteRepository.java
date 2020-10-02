package br.com.ntendencia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.Mutuante;

@Repository
public interface MutuanteRepository extends CrudRepository<Mutuante, String> {

}
