package br.com.ntendencia.services.impl;

import java.util.List;
import java.util.Optional;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;

@Service
public class ContratoEmprestimoServicesImpl implements ContratoEmprestimoService {

	@Autowired
	private ContratoEmprestimoRepository contratoEmprestimoRepo;

	@Autowired
	private ItemEmprestadoServicesImpl itemEmprestadoServices;
	
	@Override
	public String contratoEmprestimoSave(ContratoEmprestimo contratoEmprestimo) {
		ItemEmprestadoDTO itemEmprestadoDTO = (ItemEmprestadoDTO) contratoEmprestimo.getItemEmprestadoDTO();
		String id = itemEmprestadoDTO.getId();
		Optional<ItemEmprestado> obj =itemEmprestadoServices.procuraItemEmprestado(id);
		ItemEmprestado itemEmprestado = obj.get();

		if (itemEmprestado.getMutuarioDTO() != null) {
			return "Objeto já esta emprestado";
		}
		contratoEmprestimoRepo.save(contratoEmprestimo);
		return "Contrato Salvo";
	}

	@Override
	public void deleteContratoEmprestimo(String id) {
		contratoEmprestimoRepo.deleteById(id);
		
	}

	@Override
	public List<ContratoEmprestimo> contratosEmprestimo() {
		return (List<ContratoEmprestimo>) contratoEmprestimoRepo.findAll();
	}
}
