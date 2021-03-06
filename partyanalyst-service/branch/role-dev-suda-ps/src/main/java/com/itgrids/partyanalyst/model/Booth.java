package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "booth")
public class Booth extends BaseModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 4318484808348719448L;

	private Long boothId;
	private String partNo;
	private String partName;
	private String location;
	private String villagesCovered;
	private Tehsil tehsil;
	private Long maleVoters;
	private Long femaleVoters;
	private Long totalVoters;
	private Set<BoothConstituencyElection> boothConstituencyElections = new HashSet<BoothConstituencyElection>(0);
	private Set<BoothVillageCensus> boothVillageCensuses = new HashSet<BoothVillageCensus>(0);
	
	/** default constructor */
	public Booth() {
	}

	/** minimal constructor */
	public Booth(Long boothId) {
		this.boothId = boothId;
	}

	/** full constructor */
	public Booth(String partNo, String partName, String location,
			String villagesCovered, Tehsil tehsil, Long maleVoters, Long femaleVoters,
			Long totalVoters,
			Set<BoothConstituencyElection> boothConstituencyElections,
			Set<BoothVillageCensus> boothVillageCensuses) {
		this.partNo = partNo;
		this.partName = partName;
		this.location = location;
		this.villagesCovered = villagesCovered;
		this.tehsil = tehsil;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.totalVoters = totalVoters;
		this.boothConstituencyElections = boothConstituencyElections;
		this.boothVillageCensuses = boothVillageCensuses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_id", unique = true, nullable = false)
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	@Column(name = "part_no", length = 50)
	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	@Column(name = "part_name", length = 250)
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	} 
	
	@Column(name = "location", length = 250)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "village_covered", length = 250)
	public String getvillagesCovered() {
		return villagesCovered;
	}

	public void setvillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tehsil_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Tehsil getTehsil() {
		return tehsil;
	}

	public void setTehsil(Tehsil tehsil) {
		this.tehsil = tehsil;
	}

	@Column(name = "male_voters")
	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}

	@Column(name = "female_voters")
	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	@Column(name = "total_voters")
	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	public Set<BoothConstituencyElection> getBoothConstituencyElections() {
		return boothConstituencyElections;
	}

	public void setBoothConstituencyElections(
			Set<BoothConstituencyElection> boothConstituencyElections) {
		this.boothConstituencyElections = boothConstituencyElections;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	public Set<BoothVillageCensus> getBoothVillageCensuses() {
		return boothVillageCensuses;
	}

	public void setBoothVillageCensuses(Set<BoothVillageCensus> boothVillageCensuses) {
		this.boothVillageCensuses = boothVillageCensuses;
	}
	
	
}
