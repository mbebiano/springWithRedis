package br.com.ntendencia.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.ntendencia.domain.Mutuario;

@Repository
public interface MutuarioRepository extends CrudRepository<Mutuario, String> {
	
	
//	default Mutuario findByName(String name) {
//	    List<Mutuario> list = (List<Mutuario>) findAll();
//	    for(Mutuario m : list) {
//			m.getName();
//			if(m.getName().equals(name)) {
//				return m;
//			}
//		}
//	    return null;
//	}

}
