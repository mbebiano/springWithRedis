package br.com.ntendencia.services;

import br.com.ntendencia.domain.User;

public interface UserService {
	
	void userSave(User user);
	void deleteUser(String id);
}
