package models.entidade;

import java.io.Serializable;
import java.util.Date;

public class Lancamento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private Date data;
	private Double valor;
	
	private Usuario usuario;

	public Lancamento() {
		
	}
	
	public Lancamento(Integer id, String descricao, Date data, Double valor, Usuario usuario) {
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", data=" + data + ", valor=" + valor
				+ ", usuario=" + usuario + "]";
	}
	
}
