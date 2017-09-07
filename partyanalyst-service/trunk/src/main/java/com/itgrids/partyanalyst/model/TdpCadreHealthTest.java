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
@Table(name="tdp_cadre_health_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreHealthTest extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long tdpCadreHealthTestId;
	private Long tdpCadreId;
	private Long healthTestId;
	private String reportPath;
	private Date testDate;
	private Date insertedTime;
	private String isDeleted;
	private TdpCadre  tdpCadre;
	private HealthTest healthTest;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_health_test_id", unique = true, nullable = false)
	public Long getTdpCadreHealthTestId() {
		return tdpCadreHealthTestId;
	}
	public void setTdpCadreHealthTestId(Long tdpCadreHealthTestId) {
		this.tdpCadreHealthTestId = tdpCadreHealthTestId;
	}
	
	@Column(name="tdp_cadre_id" ,length=20)
	 public Long getTdpCadreId() {
		return tdpCadreId;
	 }
	 public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	 }
	
	@Column(name="health_test_id")
	public Long getHealthTestId() {
		return healthTestId;
	}
	public void setHealthTestId(Long healthTestId) {
		this.healthTestId = healthTestId;
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
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "health_test_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HealthTest getHealthTest() {
		return healthTest;
	}
	public void setHealthTest(HealthTest healthTest) {
		this.healthTest = healthTest;
	}
	
	@Column(name="report_path")
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	@Column(name="test_date")
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
