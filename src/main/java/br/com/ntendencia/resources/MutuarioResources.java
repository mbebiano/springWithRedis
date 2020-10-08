package br.com.ntendencia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntendencia.domain.ItemEmprestado;
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
	
	//informo a lista de itens emprestados para um id de mutuario
	@GetMapping("/listaItens/{id}")
	public List<ItemEmprestado> listaContratos(@PathVariable String id){
		return mutuarioService.listaItens(id);
	}
	
	
	@GetMapping("/mutuarioId/{id}")
	public Mutuario mutuarioPorId(@PathVariable String id){
		return mutuarioService.findById(id);
		}
	@GetMapping("/mutuarioPorNome/{name}")
	public Mutuario mutuarioPorName(@PathVariable String name){
		return mutuarioService.findByName(name);
		}

}
