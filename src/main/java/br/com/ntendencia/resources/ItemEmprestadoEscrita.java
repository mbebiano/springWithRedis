package br.com.ntendencia.resources;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.services.impl.ItemEmprestadoServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itemEmprestado")
public class ItemEmprestadoEscrita {
	
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

}