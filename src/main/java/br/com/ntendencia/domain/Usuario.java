package br.com.ntendencia.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@RedisHash(value="c_usuario")
public class Usuario  {

	@Id
	private String idUsuario;
	@Indexed
	private String name;
	private String email;

	public Usuario() {
	}

	public Usuario(String idUsuario, String name, String email) {
		this.idUsuario = idUsuario;
		this.name = name;
		this.email = email;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public @NotBlank String getName() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario)) return false;
		Usuario usuario = (Usuario) o;
		return getIdUsuario().equals(usuario.getIdUsuario());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIdUsuario());
	}
}
