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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

/**
 * Description
 * @author <a href="mailto:kondababu.kurakula@itgrids.com">kurakula kondababu</a> 
 * @version 1.0/
 */


@Entity
@Table(name = "panchayat")
public class Panchayat {
	
	@Id
	@Column(name="panchayat_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long panchayatId;
	
	@Column(name="panchayat_name")
	private String panchayatName;
	
	@Column(name="tehsil_id")
	private Long tehsilId;
	
	@Column(name="name_local")
	private String nameLocal;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	private Tehsil tehsil;
	

	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
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
	

}

