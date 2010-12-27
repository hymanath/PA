package com.itgrids.partyanalyst.model;

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
@Table(name = "booth_village_census")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothVillageCensus extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -5494800213942399353L;

	private Long boothVillageCensusId;
	private Long villageCensusCode;
	private Booth booth;
	
	public BoothVillageCensus(){
		
	}
	
	public BoothVillageCensus(Long boothVillageCensusId){
		this.boothVillageCensusId = boothVillageCensusId;
	}
	
	public BoothVillageCensus(Long villageCensusCode, Booth booth) {
		this.villageCensusCode = villageCensusCode;
		this.booth = booth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_village_census_id", unique = true, nullable = false)
	public Long getBoothVillageCensusId() {
		return boothVillageCensusId;
	}
	
	public void setBoothVillageCensusId(Long boothVillageCensusId) {
		this.boothVillageCensusId = boothVillageCensusId;
	}

	@Column(name = "village_census_code")
	public Long getVillageCensusCode() {
		return villageCensusCode;
	}

	public void setVillageCensusCode(Long villageCensusCode) {
		this.villageCensusCode = villageCensusCode;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
}
