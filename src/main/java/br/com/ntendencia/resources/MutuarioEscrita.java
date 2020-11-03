package br.com.ntendencia.resources;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mutuario")
public class MutuarioEscrita {
	
	@Autowired
	private MutuarioServicesImpl mutuarioService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody Mutuario mutuario) {
		mutuarioService.salvarMutuario(mutuario);
		return "Usuário Salvo";
	}

	
	@DeleteMapping("/delete/{id}")
	public String deleteMutuante(@PathVariable String id) {
		mutuarioService.deleteMutuario(id);
		return "Usuario Deletado";
	}

}
