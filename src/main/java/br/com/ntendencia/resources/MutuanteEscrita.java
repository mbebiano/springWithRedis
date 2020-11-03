package br.com.ntendencia.resources;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.services.impl.MutuanteServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutuantes")
public class MutuanteEscrita {
	
	@Autowired
	private MutuanteServicesImpl mutuanteService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody Mutuante mutuante) {
		mutuanteService.salvarMutuante(mutuante);
		return "Usuário Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMutuante(@PathVariable String id) {
		mutuanteService.deleteMutuante(id);
		return "Usuario Deletado";
	}

}
