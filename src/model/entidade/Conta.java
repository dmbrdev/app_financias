package model.entidade;

import java.io.Serializable;

public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private double saldo;
	private Usuario usuario;
	
	public Conta() {
	}

	public Conta(Integer id, String nome, double saldo, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.saldo = saldo;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
//		return "Conta [id=" + id + ", nome=" + nome + ", saldo=" + saldo + ", usuario=" + usuario+ "]";
		return this.nome;
	}


	
	
	
}
