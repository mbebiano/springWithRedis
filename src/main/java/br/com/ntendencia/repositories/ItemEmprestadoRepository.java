package br.com.ntendencia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.ItemEmprestado;

@Repository
public interface ItemEmprestadoRepository extends CrudRepository<ItemEmprestado, String> {

}