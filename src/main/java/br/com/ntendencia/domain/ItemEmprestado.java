package br.com.ntendencia.domain;

import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.enums.EStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash(value="c_itemEmprestado")
public class ItemEmprestado  {
	
	@Id
	private String id;
	private String name;
	private String idMutuante;
	// Criar DTO

	private Integer idItemEmprestado;
	private LocalDate dataEmprestimo;
	private long qtdDiasDeDevolucao;
	private EStatus eStatus;

	public ItemEmprestado() {
		
	}
	public ItemEmprestado(ItemEmprestadoDTO itemEmprestadoDTO){

	}
	public ItemEmprestado(String name, String idMutuante, long qtdDiasDeDevolucao) {
		this.name = name;
		this.idMutuante = idMutuante;
		this.qtdDiasDeDevolucao = qtdDiasDeDevolucao;
	}

//	public LocalDate dataDevolucao(){
//		LocalDate dataDeDevolucao = getDataEmprestimo().plusDays(this.qtdDiasDeDevolucao);
//		return dataDeDevolucao;
//		// nao ira existir essa função será feita direta no service
//	}

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

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public long getQtdDiasDeDevolucao() {
		return qtdDiasDeDevolucao;
	}

	public void setQtdDiasDeDevolucao(long qtdDiasDeDevolucao) {
		this.qtdDiasDeDevolucao = qtdDiasDeDevolucao;
	}

	public EStatus geteStatus() {
		return eStatus;
	}

	public void seteStatus(EStatus eStatus) {
		this.eStatus = eStatus;
	}

	public String getIdMutuante() {
		return idMutuante;
	}

	public void setIdMutuante(String idMutuante) {
		this.idMutuante = idMutuante;
	}

	public Integer getIdItemEmprestado() {
		return idItemEmprestado;
	}

	public void setIdItemEmprestado(Integer idItemEmprestado) {
		this.idItemEmprestado = idItemEmprestado;
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