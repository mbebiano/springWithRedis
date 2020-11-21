package br.com.ntendencia.resources;

import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.services.MutuanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mutuantes")
public class MutuanteLeitura {
	
	@Autowired
	private MutuanteService mutuanteService;

	@GetMapping("/mutuanteId/{id}")
	public MutuanteDTO mutuantePorId(@PathVariable String id){
		return mutuanteService.procurarPorId(id);
	}

	@GetMapping("/procuraPorNome/{nome}")
	public MutuanteDTO mutuantePorNome(@PathVariable String nome){
		return mutuanteService.procurarPorNome(nome);
	}

	@GetMapping("/listaTodos")
	public ResponseEntity<List<MutuanteDTO>> listaTodosDTO(){
		return ResponseEntity.ok().body(mutuanteService.listaMutuantesDTO());
	}
	
}
