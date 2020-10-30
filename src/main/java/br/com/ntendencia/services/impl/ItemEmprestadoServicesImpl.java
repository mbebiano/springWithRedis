package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.services.ItemEmprestadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ItemEmprestadoServicesImpl implements ItemEmprestadoService {

	@Autowired
	private ItemEmprestadoRepository itemEmprestadoRepo;
	@Autowired
	private MutuanteServicesImpl mutuanteService;
	
	@Override
	public ItemEmprestado salvarItemEmprestado(ItemEmprestado itemEmprestado) {
//		String id = itemEmprestado.getMutuanteDTO().getId();
//		try{
//			if (mutuanteService.procurarPorId(id) == null) {
//				throw new ResourceNotFoundException(id);
//			}
//			if (itemEmprestado.getId()!= null){
//				throw new ResourceNotFoundException(itemEmprestado.getId());
//			}
//			return itemEmprestadoRepo.save(itemEmprestado);
//		}
//		catch (RuntimeException e){
//			e.printStackTrace();
//			throw new ResourceNotFoundException(id);
//		}
		Integer idItemEmprestado = gerarId();
		itemEmprestado.setId(idItemEmprestado.toString());
		itemEmprestado.setIdItemEmprestado(idItemEmprestado);
		return itemEmprestadoRepo.save(itemEmprestado);
	}

	@Override
	public void deletarItemEmprestado(String id) {
		itemEmprestadoRepo.deleteById(id);
	}
	@Override
	public List<ItemEmprestado> listarItensEmprestados() {
		return (List<ItemEmprestado>) itemEmprestadoRepo.findAll();
	}

	@Override
	public Optional<ItemEmprestado> procurarItemEmprestado(String id) {
		return itemEmprestadoRepo.findById(id);
	}

	@Override
	public void alterarStatusItemEmprestado(String id, EStatus status) {
		ItemEmprestado obj = procurarItemEmprestado(id).orElseThrow();
		obj.seteStatus(status);
	}

	@Override
	public Integer gerarId() {
		List<ItemEmprestado> itemEmprestadoList = listarItensEmprestados();
		Integer ultimoIdItemEmprestado = 0;
		Optional<ItemEmprestado> itemEmprestadoOpt = itemEmprestadoList.stream().max(Comparator.comparingInt(ItemEmprestado::getIdItemEmprestado));
		if (itemEmprestadoOpt.isPresent()){
			ultimoIdItemEmprestado = itemEmprestadoOpt.get().getIdItemEmprestado();
		}
		return ultimoIdItemEmprestado+1;
	}

	@Override
	public void definirDataDeEmprestimo(ItemEmprestado itemEmprestado) {
		itemEmprestado.setDataEmprestimo(LocalDate.now());
	}

	@Override
	public ItemEmprestado atualizarItemEmprestado(ItemEmprestado obj) {
		Optional<ItemEmprestado> novoObj = procurarItemEmprestado(obj.getId());
		atualizarDadoItemEmprestado(novoObj, obj);
		return itemEmprestadoRepo.save(obj);
	}
	@Override
	public void atualizarDadoItemEmprestado(Optional<ItemEmprestado> novoObj, ItemEmprestado obj) {
		novoObj.get().setName(obj.getName());
		novoObj.get().setDataEmprestimo(obj.getDataEmprestimo());
		novoObj.get().seteStatus(obj.geteStatus());
	}
}
