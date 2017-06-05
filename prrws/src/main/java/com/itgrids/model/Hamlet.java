package com.itgrids.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Description
 * @author <a href="mailto:raghupathi.tirumala@itgrids.com">raghu</a> 
 * @version 1.0/
 */


@Entity
@Table(name = "hamlet")
public class Hamlet {
	@Id
	@Column(name="hamlet_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long hamletId;
	@Column(name="hamlet_name")
	private String hamletName;
	@Column(name="tehsil_id")
	private Long tehsilId;
	@Column(name="name_local")
	private String nameLocal;
/*	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id", insertable = false, updatable = false)
	private Tehsil tehsil;*/
	
	
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getNameLocal() {
		return nameLocal;
	}
	public void setNameLocal(String nameLocal) {
		this.nameLocal = nameLocal;
	}
	/*public Tehsil getTehsil() {
		return tehsil;
	}
	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}*/
	
	
	

}
