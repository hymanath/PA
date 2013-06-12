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
import javax.persistence.OneToOne;
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
	
	private String name;
	
	private String relationshipType;
	private String relativeName;
	private String gender;
	private Long age;
	private String voterIDCardNo;
	private String insertionDate;
	private Date dateOfBirth;
	private String mobileNo;
	private Set<CustomVoter> customVoters = new HashSet<CustomVoter>(0);
	private Set<Respondent> respondent = new HashSet<Respondent>(0);
	
	public Voter(){
		
	}
	
	public Voter(Long voterId) {
		super();
		this.voterId = voterId;
	}

	public Voter(String houseNo,
			String relationshipType, String gender,
			Long age, String voterIDCardNo, 
			Set<BoothConstituencyElectionVoter> boothConstituencyElectionVoters) {
		this.houseNo = houseNo;
		this.relationshipType = relationshipType;
		this.age = age;
		this.voterIDCardNo = voterIDCardNo;
		this.insertionDate = insertionDate;
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

	@Column(name = "house_no", length = 100)
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}


	@Column(name = "relationship_type", length = 50)
	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
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
	

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth", length = 10)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "relative_name", length = 200)
	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	
	@Column(name="mobile_no",length = 15)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voter")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CustomVoter> getCustomVoters() {
		return customVoters;
	}

	public void setCustomVoters(Set<CustomVoter> customVoters) {
		this.customVoters = customVoters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voter")
	public Set<Respondent> getRespondent() {
		return respondent;
	}

	public void setRespondent(Set<Respondent> respondent) {
		this.respondent = respondent;
	}
	
	
}
