//package br.com.ntendencia.controllers;
//
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.ntendencia.domain.User;
//import br.com.ntendencia.repositories.UserRepository;
//
//@RestController
//public class UserController {
//	
//	private UserRepository userRepository;
//	
//	public UserController(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//	
//	@GetMapping("/users")
//	public Map<String, User> findAll(){
//		return userRepository.findAll();
//	}
//	
//	@GetMapping("/users/{id}")
//	public User findByID(@PathVariable String id){
//		return userRepository.findById(id);
//	}
//	
//	@PostMapping("/users")
//	public void createUser(@RequestBody User user) {
//		userRepository.save(user);
//	}
//	
//	@DeleteMapping("/users/{id}")
//	public void deleteUser(@PathVariable String id) {
//		 userRepository.delete(id);
//	}
//}
