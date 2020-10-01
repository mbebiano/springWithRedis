package br.com.ntendencia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
	


}
