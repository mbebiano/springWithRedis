package br.com.ntendencia.domain;

import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.dto.MutuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Collection;

@RedisHash(value="c_itemEmprestado")
public class ItemEmprestado  {
	
	@Id
	private String id;
	private String name;
	// Criar DTO
	@JsonIgnore
	private MutuarioDTO mutuarioDTO;
	@JsonIgnore
	private MutuanteDTO mutuanteDTO;

	public ItemEmprestado() {
		
	}

	public ItemEmprestado(String id, String name) {
		this.id = id;
		this.name = name;
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

	public MutuarioDTO getMutuario() {
		return mutuarioDTO;
	}

	public void setMutuario(MutuarioDTO mutuarioDTO) {
		this.mutuarioDTO = mutuarioDTO;
	}

	public MutuanteDTO getMutuanteDTO() {
		return mutuanteDTO;
	}

	public void setMutuanteDTO(MutuanteDTO mutuanteDTO) {
		this.mutuanteDTO = mutuanteDTO;
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
