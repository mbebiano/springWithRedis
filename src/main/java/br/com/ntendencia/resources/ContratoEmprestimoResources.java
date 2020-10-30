package br.com.ntendencia.resources;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.services.impl.ContratoEmprestimoServicesImpl;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratoEmprestimos")
public class ContratoEmprestimoResources {
	
	@Autowired
	private ContratoEmprestimoServicesImpl contratoEmprestimoService;
	
	@Autowired
	private MutuarioServicesImpl mutuarioService;
	
	@PostMapping("/save")
	public String createContratoEmprestimo(@RequestBody ContratoEmprestimo contratoEmprestimo) {
		System.out.println(contratoEmprestimoService.contratoEmprestimoSave(contratoEmprestimo));

		return "Contrato Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteContrato(@PathVariable String id) {
		contratoEmprestimoService.deleteContratoEmprestimo(id);
		return "Contrato Deletado";
	}
	

	
	@GetMapping("/listaContratos")
	public ResponseEntity<List<ContratoEmprestimo>> findAll(){
		List<ContratoEmprestimo> list = contratoEmprestimoService.listarTodoscontratosEmprestimo();
		return ResponseEntity.ok().body(list);
	}
	
}
