package br.com.ntendencia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ntendencia.domain.User;
import br.com.ntendencia.services.impl.UserServicesImpl;

@RestController
@RequestMapping("/users")
public class UserResources {
	
	@Autowired
	private UserServicesImpl userService;
	
	@PostMapping("/save")
	public String createUser(@RequestBody User user) {
		userService.userSave(user);
		return "Usuário Salvo";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return "Usuario Deletado";
	}
	
}
