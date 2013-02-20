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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "influencing_people")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfluencingPeople extends BaseModel{

	private static final long serialVersionUID = 1L;	
	private Long influencingPeopleId;
	private User user;
	private String firstName;
	private String lastName;
	private String influencingScope;
	private String influencingScopeValue;
	private Party party;
	private String caste;
	private String occupation;
	private String phoneNo;
	private String gender;
	private String email;
	private InfluencingPeoplePosition influencingPeoplePosition;
	private UserAddress userAddress;
	private String middleName;
	private String fatherOrSpouseName;
	private Hamlet hamlet;
	private Voter voter;
	
	public InfluencingPeople(){
		
	}

	public InfluencingPeople(Long influencingPeopleId){
		this.influencingPeopleId = influencingPeopleId;
	}

	public InfluencingPeople(String firstName,
			String lastName, String influencingScope,
			Party party, String caste, String occupation, String phoneNo,
			InfluencingPeoplePosition influencingPeoplePosition,String gender,String email, Hamlet hamlet,UserAddress userAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.influencingScope = influencingScope;
		this.party = party;
		this.caste = caste;
		this.occupation = occupation;
		this.phoneNo = phoneNo;
		this.influencingPeoplePosition = influencingPeoplePosition;
		this.userAddress = userAddress;
		this.hamlet = hamlet;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "influencing_people_id", unique = true, nullable = false)
	public Long getInfluencingPeopleId() {
		return influencingPeopleId;
	}

	public void setInfluencingPeopleId(Long influencingPeopleId) {
		this.influencingPeopleId = influencingPeopleId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "first_name", length = 250)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 250)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "influencing_scope", length = 250)
	public String getInfluencingScope() {
		return influencingScope;
	}

	public void setInfluencingScope(String influencingScope) {
		this.influencingScope = influencingScope;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@Column(name = "caste", length = 250)
	public String getCaste() {
		return caste;
	}
	
	public void setCaste(String caste) {
		this.caste = caste;
	}
	
	@Column(name = "occupation", length = 250)
	public String getOccupation() {
		return occupation;
	}	

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Column(name = "phone", length = 250)
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Column(name = "gender", length = 250)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "email_id", length = 250)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "influencing_people_position_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public InfluencingPeoplePosition getInfluencingPeoplePosition() {
		return influencingPeoplePosition;
	}

	public void setInfluencingPeoplePosition(
			InfluencingPeoplePosition influencingPeoplePosition) {
		this.influencingPeoplePosition = influencingPeoplePosition;
	}

	@Column(name = "influencing_scope_value", length = 250)
	public String getInfluencingScopeValue() {
		return influencingScopeValue;
	}

	public void setInfluencingScopeValue(String influencingScopeValue) {
		this.influencingScopeValue = influencingScopeValue;
	}

	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "father_or_spouse_name", length = 25)
	public String getFatherOrSpouseName() {
		return fatherOrSpouseName;
	}

	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.fatherOrSpouseName = fatherOrSpouseName;
	}
	@Column(name = "middle_name", length = 25)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	
	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
	return voter;
	}

	public void setVoter(Voter voter) {
	this.voter = voter;
	}


}
