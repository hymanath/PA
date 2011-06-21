package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "voter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Voter extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long voterId;
	private String houseNo;
	private String firstName;
	private String lastName;
	private String relationshipType;
	private String relativeFirstName;
	private String relativeLastName;
	private String cast;
	private String castCatagery;
	private String castSubCatagery;
	private String gender;
	private Long age;
	private String localArea;
	private String voterIDCardNo;
	private String insertionDate;
	private Hamlet hamlet;
	private Date dateOfBirth;
	
	private Set<BoothConstituencyElectionVoter> boothConstituencyElectionVoters = new HashSet<BoothConstituencyElectionVoter>(0);
	
	public Voter(){
		
	}
	
	public Voter(Long voterId) {
		super();
		this.voterId = voterId;
	}

	public Voter(String houseNo, String firstName, String lastName,
			String relationshipType, String relativeFirstName, String relativeLastName,
			String cast, String castCatagery, String castSubCatagery, String gender,
			Long age, String voterIDCardNo, String localArea, String insertionDate, Hamlet hamlet,
			Set<BoothConstituencyElectionVoter> boothConstituencyElectionVoters) {
		this.houseNo = houseNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.relationshipType = relationshipType;
		this.relativeFirstName = relativeFirstName;
		this.relativeLastName = relativeLastName;
		this.cast = cast;
		this.castCatagery = castCatagery;
		this.castSubCatagery = castSubCatagery;
		this.gender = gender;
		this.age = age;
		this.voterIDCardNo = voterIDCardNo;
		this.insertionDate = insertionDate;
		this.hamlet = hamlet;
		this.localArea = localArea;
		this.boothConstituencyElectionVoters = boothConstituencyElectionVoters;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_id", unique = true, nullable = false)
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	@Column(name = "house_no", length = 25)
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	@Column(name = "first_name", length = 50)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 50)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "relationship_type", length = 50)
	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	@Column(name = "relative_first_name", length = 50)
	public String getRelativeFirstName() {
		return relativeFirstName;
	}

	public void setRelativeFirstName(String relativeFirstName) {
		this.relativeFirstName = relativeFirstName;
	}

	@Column(name = "relative_last_name", length = 50)
	public String getRelativeLastName() {
		return relativeLastName;
	}

	public void setRelativeLastName(String relativeLastName) {
		this.relativeLastName = relativeLastName;
	}

	@Column(name = "cast", length = 25)
	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	@Column(name = "cast_catagery", length = 25)
	public String getCastCatagery() {
		return castCatagery;
	}

	public void setCastCatagery(String castCatagery) {
		this.castCatagery = castCatagery;
	}

	@Column(name = "cast_subcatagery", length = 25)
	public String getCastSubCatagery() {
		return castSubCatagery;
	}

	public void setCastSubCatagery(String castSubCatagery) {
		this.castSubCatagery = castSubCatagery;
	}

	@Column(name = "gender", length = 25)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "age")
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	@Column(name = "voter_id_card_no", length = 25)
	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}

	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}

	@Column(name = "insertion_date", length = 20)
	public String getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(String insertionDate) {
		this.insertionDate = insertionDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}

	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voter")
	public Set<BoothConstituencyElectionVoter> getBoothConstituencyElectionVoters() {
		return boothConstituencyElectionVoters;
	}

	public void setBoothConstituencyElectionVoters(
			Set<BoothConstituencyElectionVoter> boothConstituencyElectionVoters) {
		this.boothConstituencyElectionVoters = boothConstituencyElectionVoters;
	}

	@Column(name = "local_area", length = 50)
	public String getLocalArea() {
		return localArea;
	}

	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", length = 10)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
