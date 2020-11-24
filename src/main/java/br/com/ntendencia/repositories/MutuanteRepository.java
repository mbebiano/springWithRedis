package br.com.ntendencia.repositories;


import br.com.ntendencia.domain.Mutuante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface MutuanteRepository extends CrudRepository<Mutuante, String> {
    Optional<Mutuante> findByName(String name);

    default List<Mutuante> findAllByName(String name){
        List<Mutuante> mutuanteList = (List<Mutuante>) findAll();
        return mutuanteList.stream().filter(mutuante ->
            name.equalsIgnoreCase(mutuante.getName())
        ).collect(Collectors.toList());



//        List<Mutuante> mutuantesMesmoNome = new ArrayList<>();
//        for(Mutuante mutuante: mutuanteList){
//            if(mutuante.getName().matches(name)){
//                mutuantesMesmoNome.add(mutuante);
//            }
//        }
//        return mutuantesMesmoNome;
    }

}