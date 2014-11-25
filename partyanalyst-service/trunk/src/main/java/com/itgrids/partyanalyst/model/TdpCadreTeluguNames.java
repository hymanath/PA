package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "tdp_cadre_telugu_names")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreTeluguNames extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 8830553612367452799L;
	private Long tdpCadreTeluguNamesId;
	private String teluguName;
	private String englishName;
	private Long tdpCadreId;
	private Date insertedTime;
	private TdpCadre tdpCadre;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_telugu_names_id", nullable = false, unique = true)
	public Long getTdpCadreTeluguNamesId() {
		return tdpCadreTeluguNamesId;
	}
	public void setTdpCadreTeluguNamesId(Long tdpCadreTeluguNamesId) {
		this.tdpCadreTeluguNamesId = tdpCadreTeluguNamesId;
	}
	
	@Column(name="telugu_name")
	public String getTeluguName() {
		return teluguName;
	}
	public void setTeluguName(String teluguName) {
		this.teluguName = teluguName;
	}
	
	@Column(name="english_name")
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	
	
	
	
}
