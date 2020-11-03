package br.com.ntendencia.resources;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.services.impl.MutuanteServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mutuantes")
public class MutuanteLeitura {
	
	@Autowired
	private MutuanteServicesImpl mutuanteService;

	@GetMapping("/mutuanteId/{id}")
	public Mutuante mutuantePorId(@PathVariable String id){
		return mutuanteService.procurarPorId(id);
	}

	@GetMapping("/procuraPorNome/{nome}")
	public Mutuante mutuantePorNome(@PathVariable String nome){
		return mutuanteService.procurarPorNome(nome);
	}

	@GetMapping("/listaTodos")
	public ResponseEntity<List<Mutuante>> listaTodos(){
		return ResponseEntity.ok().body(mutuanteService.listaMutuantes());
	}
	
}
