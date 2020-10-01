package br.com.ntendencia.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ntendencia.domain.User;
import br.com.ntendencia.repositories.UserRepository;
import br.com.ntendencia.services.UserService;

@Service
public class UserServicesImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void userSave(User user) {
		userRepo.save(user);
	}
	
	@Override
	public void deleteUser(@PathVariable String id) {
		 userRepo.deleteById(id);
	}
	
}
