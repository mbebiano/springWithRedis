package br.com.ntendencia.resources;


import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.services.ContratoEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/contrato-emprestimo")
public class ContratoEmprestimoLeitura {
	
	@Autowired
	private ContratoEmprestimoService contratoEmprestimoService;

	@GetMapping("/listar-todos")
	public List<ContratoEmprestimoDTO> listarContratos(){
		return contratoEmprestimoService.listarTodoscontratosEmprestimoDTO();
	}
}