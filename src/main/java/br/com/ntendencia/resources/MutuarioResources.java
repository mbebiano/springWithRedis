package br.com.ntendencia.resources;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mutuario")
public class MutuarioResources {
	
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

	@GetMapping("/mutuarioId/{id}")
	public Mutuario mutuarioPorId(@PathVariable String id){
		return mutuarioService.findById(id);
		}

	@GetMapping("/procuraPorNome/{nome}")
	public Mutuario mutuarioPorNome(@PathVariable String nome){
		return mutuarioService.procurarPorNome(nome);
	}

	@GetMapping("/listaTodos")
	public ResponseEntity<List<Mutuario>> listaTodos(){
		return ResponseEntity.ok().body(mutuarioService.listaMutuarios());
	}
}
