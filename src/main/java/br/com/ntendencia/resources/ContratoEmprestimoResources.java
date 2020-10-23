package br.com.ntendencia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.services.impl.ContratoEmprestimoServicesImpl;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;

@RestController
@RequestMapping("/contratoEmprestimos")
public class ContratoEmprestimoResources {
	
	@Autowired
	private ContratoEmprestimoServicesImpl contratoEmprestimoService;
	
	@Autowired
	private MutuarioServicesImpl mutuarioService;
	
	@PostMapping("/save")
	public String createContratoEmprestimo(@RequestBody ContratoEmprestimo contratoEmprestimo) {
		return contratoEmprestimoService.contratoEmprestimoSave(contratoEmprestimo);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteContrato(@PathVariable String id) {
		contratoEmprestimoService.deleteContratoEmprestimo(id);
		return "Contrato Deletado";
	}
	

	
	@GetMapping("/listaContratos")
	public ResponseEntity<List<ContratoEmprestimo>> findAll(){
		List<ContratoEmprestimo> list = contratoEmprestimoService.contratosEmprestimo();
		return ResponseEntity.ok().body(list);
	}
	
}
