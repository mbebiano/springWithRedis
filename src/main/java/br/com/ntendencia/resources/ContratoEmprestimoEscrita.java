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
public class ContratoEmprestimoEscrita {
	
	@Autowired
	private ContratoEmprestimoServicesImpl contratoEmprestimoService;
	
	@Autowired
	private MutuarioServicesImpl mutuarioService;
	
	@PostMapping("/save")
	public String createContratoEmprestimo(@RequestBody ContratoEmprestimo contratoEmprestimo) {
		contratoEmprestimoService.contratoEmprestimoSave(contratoEmprestimo);
		return "Contrato Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteContrato(@PathVariable String id) {
		contratoEmprestimoService.deleteContratoEmprestimo(id);
		return "Contrato Deletado";
	}

	
}
