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

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.services.impl.ContratoEmprestimoServicesImpl;

@RestController
@RequestMapping("/contratoEmprestimos")
public class ContratoEmprestimoResources {
	
	@Autowired
	private ContratoEmprestimoServicesImpl contratoEmprestimoService;
	
	@PostMapping("/save")
	public String createContratoEmprestimo(@RequestBody ContratoEmprestimo contratoEmprestimo) {
		contratoEmprestimoService.contratoEmprestimoSave(contratoEmprestimo);
		return "Usuário Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteContrato(@PathVariable String id) {
		contratoEmprestimoService.deleteContratoEmprestimo(id);
		return "Usuario Deletado";
	}
	
	@GetMapping("/listaContratos")
	public List<ContratoEmprestimo> listaContratos(){
		return contratoEmprestimoService.contratosEmprestimo();
	}
	
}
