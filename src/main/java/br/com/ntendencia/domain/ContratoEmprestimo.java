package br.com.ntendencia.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="c_contratoEmprestimo")
public class ContratoEmprestimo {
	
	@Id
	private String id;
	private Date dataEmprestimo;
	private Date dataDevolucao;
	
	private Mutuante mutuante;
	private Mutuario mutuario;
	private List<ItemEmprestado> itemEmprestado = new ArrayList<>();
	
	public ContratoEmprestimo() {}

	
	public ContratoEmprestimo(String id, Date dataEmprestimo, Date dataDevolucao, Mutuante mutuante,
			Mutuario mutuario) {
		this.id = id;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucao = dataDevolucao;
		this.mutuante = mutuante;
		this.mutuario = mutuario;
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


	public List<ItemEmprestado> getItemEmprestado() {
		return itemEmprestado;
	}


	public void setItemEmprestado(List<ItemEmprestado> itemEmprestado) {
		this.itemEmprestado = itemEmprestado;
	}
	
	public void setItemEmprestado2(ItemEmprestado itemEmprestado) {
		this.itemEmprestado.add(itemEmprestado);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
