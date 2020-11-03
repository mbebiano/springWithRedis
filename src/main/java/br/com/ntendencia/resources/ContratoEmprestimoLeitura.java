package br.com.ntendencia.resources;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.services.impl.ContratoEmprestimoServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratoEmprestimos")
public class ContratoEmprestimoLeitura {
	
	@Autowired
	private ContratoEmprestimoServicesImpl contratoEmprestimoService;

	@GetMapping("/listaContratos")
	public ResponseEntity<List<ContratoEmprestimo>> findAll(){
		List<ContratoEmprestimo> list = contratoEmprestimoService.listarTodoscontratosEmprestimo();
		return ResponseEntity.ok().body(list);
	}
	
}
