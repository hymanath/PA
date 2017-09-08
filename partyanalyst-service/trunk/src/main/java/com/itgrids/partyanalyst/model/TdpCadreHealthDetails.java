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
@Table(name="tdp_cadre_health_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreHealthDetails extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long tdpCadreHealthDetailsId;
	private Long tdpCadreId;
	private Long age;
	private String gender;
	private Long height;
	private Long weight;
	private Long spot;
	private Long systolicBp;
	private Long diastolicBp;
	private Long heartPulse;
	private Long spiro;
	private Date testDate;
	private Date updatedTime;
	private String isDeleted;
	private TdpCadre  tdpCadre;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_health_details_id", unique = true, nullable = false)
	
	public Long getTdpCadreHealthDetailsId() {
		return tdpCadreHealthDetailsId;
	}
	public void setTdpCadreHealthDetailsId(Long tdpCadreHealthDetailsId) {
		this.tdpCadreHealthDetailsId = tdpCadreHealthDetailsId;
	}

	
	@Column(name="tdp_cadre_id" ,length=20)
	 public Long getTdpCadreId() {
		return tdpCadreId;
	 }
	 public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	 }
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_cadre_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	 public TdpCadre getTdpCadre() {
		return tdpCadre;
	 }
	 public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	 }
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="test_date")
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	@Column(name="height")
	public Long getHeight() {
		return height;
	}
	public void setHeight(Long height) {
		this.height = height;
	}
	
	@Column(name="weight")
	public Long getWeight() {
		return weight;
	}
	public void setWeight(Long weight) {
		this.weight = weight;
	}
	@Column(name="spot")
	public Long getSpot() {
		return spot;
	}
	public void setSpot(Long spot) {
		this.spot = spot;
	}
	@Column(name="systolic_bp")
	public Long getSystolicBp() {
		return systolicBp;
	}
	public void setSystolicBp(Long systolicBp) {
		this.systolicBp = systolicBp;
	}
	@Column(name="diastolic_bp")
	public Long getDiastolicBp() {
		return diastolicBp;
	}
	public void setDiastolicBp(Long diastolicBp) {
		this.diastolicBp = diastolicBp;
	}
	@Column(name="heart_pulse")
	public Long getHeartPulse() {
		return heartPulse;
	}
	public void setHeartPulse(Long heartPulse) {
		this.heartPulse = heartPulse;
	}
	@Column(name="spiro")
	public Long getSpiro() {
		return spiro;
	}
	public void setSpiro(Long spiro) {
		this.spiro = spiro;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
