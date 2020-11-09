package br.com.ntendencia.services;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.dto.ContratoEmprestimoDTO;

import java.util.List;

public interface ContratoEmprestimoService {
	
	ContratoEmprestimo contratoEmprestimoSave(ContratoEmprestimoDTO contratoEmprestimoDTO);
	void deleteContratoEmprestimo(String id);
	List<ContratoEmprestimo> listarTodoscontratosEmprestimo();
	List<ContratoEmprestimoDTO> listarTodoscontratosEmprestimoDTO();
	public Integer gerarId();
	
}
