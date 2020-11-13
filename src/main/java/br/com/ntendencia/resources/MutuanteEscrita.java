package br.com.ntendencia.resources;


import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.services.impl.MutuanteServicesImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/mutuantes")
public class MutuanteEscrita {
	
	@Autowired
	private MutuanteServicesImpl mutuanteService;

	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/save")
	public String createUser(@RequestBody @Valid MutuanteDTO mutuanteDTO) {
		mutuanteService.salvarMutuante(mutuanteDTO);
		return "Usuário Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMutuante(@PathVariable String id) {
		mutuanteService.deleteMutuante(id);
		return "Usuario Deletado";
	}

}
