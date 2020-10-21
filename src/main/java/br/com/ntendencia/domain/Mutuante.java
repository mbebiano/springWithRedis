package br.com.ntendencia.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="c_mutuante")
public class Mutuante extends User{
	

	
	//private List<ItemEmprestado> itemParaEmprestar = new ArrayList<>();
	
	public Mutuante() {
		
	}

	public Mutuante(String id, String name, String email) {
		super(id, name, email);
	}

//	public List<ItemEmprestado> getItemParaEmprestar() {
//		return itemParaEmprestar;
//	}
//
//	public void setItemParaEmprestar(List<ItemEmprestado> itemEmprestado) {
//		this.itemParaEmprestar= itemEmprestado;
//	}
//
	
}
