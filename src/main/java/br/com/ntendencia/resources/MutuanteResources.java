package br.com.ntendencia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.services.impl.MutuanteServicesImpl;

@RestController
@RequestMapping("/mutuantes")
public class MutuanteResources {
	
	@Autowired
	private MutuanteServicesImpl mutuanteService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody Mutuante mutuante) {
		mutuanteService.mutuanteSave(mutuante);
		return "Usuário Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMutuante(@PathVariable String id) {
		mutuanteService.deleteMutuante(id);
		return "Usuario Deletado";
	}
	
}
