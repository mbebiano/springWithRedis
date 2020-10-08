package br.com.ntendencia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.services.impl.ItemEmprestadoServicesImpl;

@RestController
@RequestMapping("/itemEmprestado")
public class ItemEmprestadoResources {
	
	@Autowired
	private ItemEmprestadoServicesImpl itemEmprestadoService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody ItemEmprestado itemEmprestado) {
		itemEmprestadoService.itemEmprestadoSave(itemEmprestado);
		return "Item Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable String id) {
		itemEmprestadoService.deleteItemEmprestado(id);
		return "Item Deletado";
	}
	
	@GetMapping("/listaItens")
	public List<ItemEmprestado> listaItens(){
		return itemEmprestadoService.itensEmprestados();
	}
	

	
}
