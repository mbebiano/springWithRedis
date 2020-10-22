package br.com.ntendencia.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	private Mutuante mutuante;
	private MutuarioDTO mutuarioDTO;
	private List<ItemEmprestado> itemEmprestado = new ArrayList<>();
	
	public ContratoEmprestimo() {}

	
	public ContratoEmprestimo(String id, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
		this.id = id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;;
	}

	public Mutuante getMutuante() {
		return mutuante;
	}


	public void setMutuante(Mutuante mutuante) {
		this.mutuante = mutuante;
	}


	public MutuarioDTO getMutuario() {
		return mutuarioDTO;
	}


	public void setMutuario(MutuarioDTO mutuarioDTO) {
		this.mutuarioDTO = mutuarioDTO;
	}


	public List<ItemEmprestado> getItemEmprestado() {
		return itemEmprestado;
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
