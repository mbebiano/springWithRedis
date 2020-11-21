package br.com.ntendencia.resources;


import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.services.ContratoEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/contratoEmprestimos")
public class ContratoEmprestimoLeitura {
	
	@Autowired
	private ContratoEmprestimoService contratoEmprestimoService;

	@GetMapping("/listaContratos")
	public ResponseEntity<List<ContratoEmprestimoDTO>> findAll(){
		return ResponseEntity.ok().body(contratoEmprestimoService.listarTodoscontratosEmprestimoDTO());
	}

	@GetMapping("/listarContratos")
	public List<ContratoEmprestimoDTO> listarContratos(){
		return contratoEmprestimoService.listarTodoscontratosEmprestimoDTO();
	}

}
