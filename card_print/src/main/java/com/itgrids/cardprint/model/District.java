package com.itgrids.cardprint.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "district")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class District extends BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 6608505852511099145L;
	
	private Long districtId;
	private State state;
	private String districtName;
	private String districtCapital;
	private Double area;
	private Double population;
	private Long districtCode;
	private Date startDate;
	private Date deformDate;
	
	private String localName;
	// Constructors

	/** default constructor */
	public District() {
	}

	/** minimal constructor */
	public District(Long districtId) {
		this.districtId = districtId;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "district_id", unique = true, nullable = false)
	public Long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "district_name", length = 100)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "district_capital", length = 100)
	public String getDistrictCapital() {
		return this.districtCapital;
	}

	public void setDistrictCapital(String districtCapital) {
		this.districtCapital = districtCapital;
	}

	@Column(name = "area", precision = 15, scale = 5)
	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "population", precision = 10, scale = 0)
	public Double getPopulation() {
		return this.population;
	}

	public void setPopulation(Double population) {
		this.population = population;
	}

	@Column(name = "district_code")
	public Long getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deform_date", length = 10)
	public Date getDeformDate() {
		return this.deformDate;
	}

	public void setDeformDate(Date deformDate) {
		this.deformDate = deformDate;
	}

	@Column(name="name_local")
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}
	
	
}
