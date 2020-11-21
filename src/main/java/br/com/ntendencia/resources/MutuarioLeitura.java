package br.com.ntendencia.resources;

import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.services.MutuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mutuario")
public class MutuarioLeitura {
	
	@Autowired
	private MutuarioService mutuarioService;

	@GetMapping("/mutuarioId/{id}")
	public MutuarioDTO mutuarioPorId(@PathVariable String id){
		return mutuarioService.findById(id);
		}

	@GetMapping("/procuraPorNome/{nome}")
	public MutuarioDTO mutuarioPorNome(@PathVariable String nome){
		return mutuarioService.procurarPorNome(nome);
	}

	@GetMapping("/listaTodos")
	public ResponseEntity<List<MutuarioDTO>> listaTodos(){
		return ResponseEntity.ok().body(mutuarioService.listaMutuariosDTO());
	}

}
