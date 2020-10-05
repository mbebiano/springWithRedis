package br.com.ntendencia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="c_itemEmprestado")
public class ItemEmprestado {
	
	@Id
	private String id;
	private String name;

	private Mutuante mutuante;
	private Mutuario mutuario;
	
	public ItemEmprestado() {
		
	}

	public ItemEmprestado(String id, String name, Mutuante mutuante, Mutuario mutuario) {
		this.id = id;
		this.name = name;
		this.mutuante= mutuante;
		this.mutuario = mutuario;
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
	
	

	public Mutuante getMutuante() {
		return mutuante;
	}

	public void setMutuante(Mutuante mutuante) {
		this.mutuante = mutuante;
	}

	public Mutuario getMutuario() {
		return mutuario;
	}

	public void setMutuario(Mutuario mutuario) {
		this.mutuario = mutuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ItemEmprestado other = (ItemEmprestado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		
	
}
