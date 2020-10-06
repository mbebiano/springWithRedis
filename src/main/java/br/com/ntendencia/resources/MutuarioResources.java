package br.com.ntendencia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;

@RestController
@RequestMapping("/mutuario")
public class MutuarioResources {
	
	@Autowired
	private MutuarioServicesImpl mutuarioService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody Mutuario mutuario) {
		mutuarioService.mutuarioSave(mutuario);
		return "Usuário Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMutuante(@PathVariable String id) {
		mutuarioService.deleteMutuario(id);
		return "Usuario Deletado";
	}
	
}
