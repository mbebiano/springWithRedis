package br.com.ntendencia.resources;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.services.impl.ItemEmprestadoServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itemEmprestado")
public class ItemEmprestadoResources {
	
	@Autowired
	private ItemEmprestadoServicesImpl itemEmprestadoService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody ItemEmprestado itemEmprestado) {
		itemEmprestadoService.salvarItemEmprestado(itemEmprestado);
		return "Item salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable String id) {
		itemEmprestadoService.deletarItemEmprestado(id);
		return "Item Deletado";
	}
	
	@GetMapping("/listaItens")
	public List<ItemEmprestado> listaItens(){
		return itemEmprestadoService.listarItensEmprestados();
	}

	@GetMapping("/buscarPorId/{id}")
	public Optional<ItemEmprestado> buscarPorId(@PathVariable String id){
		return itemEmprestadoService.procurarItemEmprestado(id);
	}
	
}
