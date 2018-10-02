package br.com.testeapi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Classe de contato.")
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "id", example = "1", required = true, position = 0)
	private Long id;

	@NotNull
	@NotBlank
	@ApiModelProperty(notes = "cpf", example = "111.111.111-11", required = true, position = 1)
	private String cpf;
	
	@NotBlank
	@ApiModelProperty(notes = "nome", example = "João da Silva", required = true, position = 2)
	private String nome;
	
	@NotNull
	@Email
	@ApiModelProperty(notes = "email", example = "email@mail.com", required = true, position = 3)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
