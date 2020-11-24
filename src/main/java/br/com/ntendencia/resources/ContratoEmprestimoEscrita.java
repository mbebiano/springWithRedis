package br.com.ntendencia.resources;

import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.services.ContratoEmprestimoService;
import br.com.ntendencia.services.impl.MutuarioServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/contrato-emprestimo")
public class ContratoEmprestimoEscrita {
	
	@Autowired
	private ContratoEmprestimoService contratoEmprestimoService;
	
	@Autowired
	private MutuarioServicesImpl mutuarioService;
	
	@PostMapping("/salvar")
	public String createContratoEmprestimo(@RequestBody @Valid ContratoEmprestimoDTO contratoEmprestimoDTO) {
		contratoEmprestimoService.contratoEmprestimoSave(contratoEmprestimoDTO);
		return "Contrato Salvo";
	}
	
	@DeleteMapping("/deletar/{id}")
	public String deleteContrato(@PathVariable String id) {
		contratoEmprestimoService.deleteContratoEmprestimo(id);
		return "Contrato Deletado";
	}
}