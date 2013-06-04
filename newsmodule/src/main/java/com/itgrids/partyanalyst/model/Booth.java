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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
import com.itgrids.partyanalyst.model.BaseModel;


@Entity
@Table(name = "booth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
	private Panchayat panchayat;
	

	private Constituency constituency;
	private LocalElectionBody localBody;
	//private BoothLocalBodyWard boothLocalBodyWard;
	private Long year;
	//private Set<BoothConstituencyElection> boothConstituencyElections = new HashSet<BoothConstituencyElection>(0);
	//private Set<BoothVillageCensus> boothVillageCensuses = new HashSet<BoothVillageCensus>(0);
	private Set<UserAddress> userAddressBooths = new HashSet<UserAddress>(0);
	private Set<LocalGroupRegion> localGroupRegion = new HashSet<LocalGroupRegion>(0);
	
	//private Set<BoothPublicationVoter> boothPublicationVoters = new HashSet<BoothPublicationVoter>(0);
	//private Set<HamletBoothPublication> hamletBoothPublications = new HashSet<HamletBoothPublication>(0);
	
	private PublicationDate publicationDate;
	
	private Constituency localBodyWard;
	
	/** default constructor */
	public Booth() {
	}

	/** minimal constructor */
	public Booth(Long boothId) {
		this.boothId = boothId;
	}

	/** full constructor *//*
	public Booth(String partNo, String partName, String location,
			String villagesCovered, Tehsil tehsil, Long maleVoters, Long femaleVoters,
			Long totalVoters, Constituency constituency, Long year, LocalElectionBody localBody,
			Set<BoothConstituencyElection> boothConstituencyElections,
			Set<BoothVillageCensus> boothVillageCensuses,Set<LocalGroupRegion> localGroupRegion,PublicationDate publicationDate) {
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
		this.constituency = constituency;
		this.year = year;
		this.localBody = localBody;
		this.localGroupRegion = localGroupRegion;
		this.publicationDate = publicationDate;
	}*/

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

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
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
*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Column(name = "year")
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	@Column(name = "village_covered", length = 500)
	public String getVillagesCovered() {
		return villagesCovered;
	}

	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_election_body_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalBody() {
		return localBody;
	}

	public void setLocalBody(LocalElectionBody localBody) {
		this.localBody = localBody;
	}

	/*@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	public BoothLocalBodyWard getBoothLocalBodyWard() {
		return boothLocalBodyWard;
	}

	public void setBoothLocalBodyWard(BoothLocalBodyWard boothLocalBodyWard) {
		this.boothLocalBodyWard = boothLocalBodyWard;
	}*/
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserAddress> getUserAddressBooths() {
		return userAddressBooths;
	}

	public void setUserAddressBooths(Set<UserAddress> userAddressBooths) {
		this.userAddressBooths = userAddressBooths;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<LocalGroupRegion> getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(Set<LocalGroupRegion> localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_date_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<BoothPublicationVoter> getBoothPublicationVoters() {
		return boothPublicationVoters;
	}

	public void setBoothPublicationVoters(
			Set<BoothPublicationVoter> boothPublicationVoters) {
		this.boothPublicationVoters = boothPublicationVoters;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "booth")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<HamletBoothPublication> getHamletBoothPublications() {
		return hamletBoothPublications;
	}

	public void setHamletBoothPublications(
			Set<HamletBoothPublication> hamletBoothPublications) {
		this.hamletBoothPublications = hamletBoothPublications;
	}*/

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="panchayat_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}

	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getLocalBodyWard() {
		return localBodyWard;
	}

	public void setLocalBodyWard(Constituency localBodyWard) {
		this.localBodyWard = localBodyWard;
	}
	
	
}
