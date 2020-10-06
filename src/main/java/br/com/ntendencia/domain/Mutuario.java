package br.com.ntendencia.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="c_mutuario")
public class Mutuario {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String senha;
	
	private List<ItemEmprestado> itemsEmprestados = new ArrayList<>();
	
	public Mutuario() {
		
	}

	public Mutuario(String id, String name, String email, String senha) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.senha = senha;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ItemEmprestado> getItemsEmprestados() {
		return itemsEmprestados;
	}

	public void setItemsEmprestados(List<ItemEmprestado> itemsEmprestados) {
		this.itemsEmprestados = itemsEmprestados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mutuario other = (Mutuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
