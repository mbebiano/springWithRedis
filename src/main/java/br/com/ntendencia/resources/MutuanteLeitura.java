package br.com.ntendencia.resources;

import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.services.MutuanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mutuante")
public class MutuanteLeitura {
	
	@Autowired
	private MutuanteService mutuanteService;

	@ResponseBody
	@GetMapping("/procurar-por-id")
	public MutuanteDTO mutuantePorId(@RequestParam("id") String id){
		return mutuanteService.procurarPorId(id);
	}

	@GetMapping("/procurar-por-nome/{nome}")
	public MutuanteDTO mutuantePorNome(@PathVariable String nome){
		return mutuanteService.procurarPorNome(nome);
	}

	@GetMapping("/listar-todos")
	public ResponseEntity<List<MutuanteDTO>> listaTodosDTO(){
		return ResponseEntity.ok().body(mutuanteService.listaMutuantesDTO());
	}
}