package br.com.ntendencia.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="c_itemEmprestado")
public class ItemEmprestado {
	
	@Id
	private String id;
	private String name;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	
	public ItemEmprestado() {
		
	}

	public ItemEmprestado(String id, String name, Date dataEmprestimo, Date dataDevolucao) {
		this.id = id;
		this.name = name;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
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

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	
	
}
