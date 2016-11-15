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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="tdp_cadre_data_source_type_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreDataSourceTypeInfo extends BaseModel implements Serializable{
	
	private Long tdpCadreDataSourceTypeInfoId;
	private Long type;
	private Long dataSourceType;
	private Long cadre2016;
	private Long newCadre;
	private Date newCadrePercent;
	private Date renewalCadre;
	private String renewalCadrePercent;
	private String insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_data_source_type_info_id", unique = true, nullable = false)
	public Long getTdpCadreDataSourceTypeInfoId() {
		return tdpCadreDataSourceTypeInfoId;
	}
	public void setTdpCadreDataSourceTypeInfoId(Long tdpCadreDataSourceTypeInfoId) {
		this.tdpCadreDataSourceTypeInfoId = tdpCadreDataSourceTypeInfoId;
	}
	@Column(name = "type")
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	@Column(name = "data_source_type")
	public Long getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(Long dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	@Column(name = "cadre_2016")
	public Long getCadre2016() {
		return cadre2016;
	}
	public void setCadre2016(Long cadre2016) {
		this.cadre2016 = cadre2016;
	}
	@Column(name = "new_cadre")
	public Long getNewCadre() {
		return newCadre;
	}
	public void setNewCadre(Long newCadre) {
		this.newCadre = newCadre;
	}
	@Column(name = "new_cadre_percent")
	public Date getNewCadrePercent() {
		return newCadrePercent;
	}
	public void setNewCadrePercent(Date newCadrePercent) {
		this.newCadrePercent = newCadrePercent;
	}
	@Column(name = "renewal_cadre")
	public Date getRenewalCadre() {
		return renewalCadre;
	}
	public void setRenewalCadre(Date renewalCadre) {
		this.renewalCadre = renewalCadre;
	}
	@Column(name = "renewal_cadre_percent")
	public String getRenewalCadrePercent() {
		return renewalCadrePercent;
	}
	public void setRenewalCadrePercent(String renewalCadrePercent) {
		this.renewalCadrePercent = renewalCadrePercent;
	}
	@Column(name = "inserted_time")
	public String getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(String insertedTime) {
		this.insertedTime = insertedTime;
	}

}
