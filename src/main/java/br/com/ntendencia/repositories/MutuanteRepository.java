package br.com.ntendencia.repositories;


import br.com.ntendencia.domain.Mutuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.Mutuante;

import java.util.Optional;

@Repository
public interface MutuanteRepository extends CrudRepository<Mutuante, String> {
    Optional<Mutuante> findByName(String name);
}
