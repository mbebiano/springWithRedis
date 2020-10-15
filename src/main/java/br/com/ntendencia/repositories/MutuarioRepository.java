package br.com.ntendencia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.Mutuario;

import java.util.Optional;

@Repository
public interface MutuarioRepository extends PagingAndSortingRepository<Mutuario, String> {

    Optional<Mutuario> findByName(String name);

}
