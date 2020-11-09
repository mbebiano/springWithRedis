package br.com.ntendencia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash(value="c_contratoEmprestimo")
public class ContratoEmprestimo {
	
	@Id
	private String id;

	private String idMutuario;

	private Integer idContrato;

	private List<ItemEmprestado> itensEmprestados = new ArrayList<>();
	
	public ContratoEmprestimo() {}

	public ContratoEmprestimo(String id, String idMutuario, Integer idContrato) {
		this.id = id;
		this.idMutuario= idMutuario;
		this.idContrato = idContrato;
	}
	public ContratoEmprestimo(String idMutuario){ this.idMutuario = idMutuario;}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public String getIdMutuario() {
		return idMutuario;
	}

	public void setIdMutuario(String idMutuario) {
		this.idMutuario = idMutuario;
	}

	public List<ItemEmprestado> getItensEmprestados() {
		return itensEmprestados;
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
		ContratoEmprestimo other = (ContratoEmprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
