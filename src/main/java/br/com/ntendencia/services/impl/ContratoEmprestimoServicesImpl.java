package br.com.ntendencia.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuario;
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

	@Autowired MutuarioServicesImpl mutuarioServices;
	
	@Override
	public String contratoEmprestimoSave(ContratoEmprestimo contratoEmprestimo) {
		List<ItemEmprestadoDTO> itensEmprestados=contratoEmprestimo.getItemEmprestadoDTO();
		List<String> ids = new ArrayList<String>();
		itensEmprestados.forEach(itemEmprestadoDTO -> ids.add(itemEmprestadoDTO.getId()));
		for(String id : ids){
			Optional<ItemEmprestado> itemProcura = itemEmprestadoServices.procuraItemEmprestado(id);
			if(itemProcura.get().getMutuarioDTO()!=null){
				return "Um item já está emprestado";
			}
		}
		try{
			 if (contratoEmprestimoRepo.findById(contratoEmprestimo.getId()).isPresent()){
				 return "Contrato Não Salvo, id será gerado pelo banco";
			}
		} catch (Exception e) {
			String idMutuario = contratoEmprestimo.getMutuarioDTO().getId();
			if(mutuarioServices.findById(idMutuario)!=null){
				Mutuario mutuario =mutuarioServices.findById(idMutuario);
				List<ItemEmprestadoDTO> list = contratoEmprestimo.getItemEmprestadoDTO();
				for(ItemEmprestadoDTO item : list){
					mutuarioServices.atualizaMutuarioItens(mutuario.getId(), item);
				}
			}
			else{
				return "Contrato não salvo, mutuario não encontrado";
			}
			contratoEmprestimoRepo.save(contratoEmprestimo);
			return "Contrato Salvo";
		}
		return "Contrato Não Salvo";
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
