package TESTE;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Pessoa {

	@Expected(start = 0, size = 5, type = String.class)
	private String nome;
	
	@Expected(start = 5, size = 2, type = Integer.class)
	private Integer idade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("nome", nome).append("idade", idade).toString();
	}

}
