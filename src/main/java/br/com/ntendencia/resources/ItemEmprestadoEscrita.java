package br.com.ntendencia.resources;

import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.services.ItemEmprestadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itemEmprestado")
public class ItemEmprestadoEscrita {
	
	@Autowired
	private ItemEmprestadoService itemEmprestadoService;

	@PostMapping("/save")
	public String createUser(@RequestBody ItemEmprestadoDTO itemEmprestadoDTO) {
		itemEmprestadoService.salvarItemEmprestado(itemEmprestadoDTO);
		return "Item salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable String id) {
		itemEmprestadoService.deletarItemEmprestado(id);
		return "Item Deletado";
	}

}
