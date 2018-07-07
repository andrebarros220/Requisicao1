package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Requisicao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@PrimaryKeyJoinColumn
	@Column
	private int codigo;
	@Column
	private String nome_colaborador;
	@Column
	private String nome_produto;
	@Column
	private String ladoplaca;
	@Column
	private String partnumber;
	@Column
	private String comentario;
	@Column
	private String status;
	@SuppressWarnings("unused")
	private boolean statusativo;
		
	
	public String getStatusativo() {
		if ( status == null) {
			return "ABERTO";
		} else {
			return "FECHADO";
		}
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatusativo(boolean statusativo) {
		this.statusativo = statusativo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome_colaborador() {
		return nome_colaborador;
	}
	public void setNome_colaborador(String nome_colaborador) {
		this.nome_colaborador = nome_colaborador;
	}
	public String getNome_produto() {
		return nome_produto;
	}
	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}
	public String getLadoplaca() {
		return ladoplaca;
	}
	public void setLadoplaca(String ladoplaca) {
		this.ladoplaca = ladoplaca;
	}
	public String getPartnumber() {
		return partnumber;
	}
	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 310;
		int result = 1;
		result = prime * result + codigo;
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
		Requisicao other = (Requisicao) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	}
