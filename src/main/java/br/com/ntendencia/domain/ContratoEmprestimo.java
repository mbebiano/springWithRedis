package br.com.ntendencia.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ntendencia.dto.ItemEmprestadoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.dto.MutuarioDTO;

@RedisHash(value="c_contratoEmprestimo")
public class ContratoEmprestimo {
	
	@Id
	private String id;
	
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;

	private MutuanteDTO mutuanteDTO;
	private MutuarioDTO mutuarioDTO;

	private List<ItemEmprestadoDTO> itemEmprestadoDTO = new ArrayList<>();
	
	public ContratoEmprestimo() {}

	
	public ContratoEmprestimo(String id, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
		this.id = id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public MutuanteDTO getMutuanteDTO() {
		return mutuanteDTO;
	}

	public void setMutuanteDTO(MutuanteDTO mutuanteDTO) {
		this.mutuanteDTO = mutuanteDTO;
	}

	public MutuarioDTO getMutuarioDTO() {
		return mutuarioDTO;
	}

	public void setMutuarioDTO(MutuarioDTO mutuarioDTO) {
		this.mutuarioDTO = mutuarioDTO;
	}

	public List<ItemEmprestadoDTO> getItemEmprestadoDTO() {
		return itemEmprestadoDTO;
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
