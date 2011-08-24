package com.itgrids.partyanalyst.model;

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
@Table(name = "registration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Registration implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	 private Long registrationId;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String gender;
	 private String userName;
	 private String password; 
	 private Date dateOfBirth;
	 private String email;
	 private String phone;
	 private String mobile;
	 private String address;
	 private String country;
	 private String pincode;
	 private String accessType;
	 private String accessValue;
	 private UserCategory userCategory;
	 private Set<UserGroupRelation> userGroupRelations = new HashSet<UserGroupRelation>(0);
	 private String userType;
	 private Set<ProblemAndProblemSource> problemAndProblemSources = new HashSet<ProblemAndProblemSource>(0);
	 private Party party;
	 private String includePartyImpDateStatus;
	 private Set<UserEvents> userEvents = new HashSet<UserEvents>(0);
	 private Set<UserImpDate> userImpDates = new HashSet<UserImpDate>(0);
	 private Set<UserGroupPrivileges> readWriteUserPrevilegesSet = new HashSet<UserGroupPrivileges>(0);
	 private Set<UserGroupPrivileges> writeWriteUserPrevilegesSet = new HashSet<UserGroupPrivileges>(0);
	 private Set<OpinionPoll> opinionPoll = new HashSet<OpinionPoll>(0); 
	 private Set<InfluencingPeople> influencingPeople = new HashSet<InfluencingPeople>(0);
	 private Registration parentUser;
	 private Set<Registration> subUsers = new HashSet<Registration>(0);
	 private Set<UserAnnouncement> userAnnouncement = new HashSet<UserAnnouncement>(0);
	

	public Registration() {
		 
	}

	public Registration(String firstName,
			String middleName, String lastName, String userName, String password,
			Date dateOfBirth, String email, String phone, String mobile,
			String address, String gender, String country, String pincode, String accessType, String accessValue,
			Party party,String includePartyImpDateStatus,
			Set<UserEvents> userEvents, String userType) {
		super();
		//this.registrationId = registrationId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.userName = userName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.country = country;
		this.pincode = pincode;
		this.accessType = accessType;
		this.accessValue = accessValue;
		this.party = party;
		this.includePartyImpDateStatus = includePartyImpDateStatus;
		this.userEvents = userEvents;
		this.userType = userType;
	}

	public Registration(Long registrationId) {
		 this.registrationId = registrationId;
	}
	
	public Registration(String firstName,
			String middleName, String lastName, String userName, String password,
			Date dateOfBirth, String email, String phone, String mobile,
			String address, String gender, String country, String pincode, String accessType, String accessValue,
			Set<ProblemAndProblemSource> problemAndProblemSources) {
		super();
		//this.registrationId = registrationId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.userName = userName;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.country = country;
		this.pincode = pincode;
		this.accessType = accessType;
		this.accessValue = accessValue;
		this.problemAndProblemSources = problemAndProblemSources;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registration_id", unique = true, nullable = false)
	public Long getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Long registrationId) {
		this.registrationId = registrationId;
	}
	@Column(name = "firstname", length = 40)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "middlename", length = 40)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Column(name = "lastname", length = 40)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "username", length = 40)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", length = 40)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Column(name = "email", length = 60)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "phone", length = 25)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "mobile", length = 25)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "address", length = 50)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "gender", length = 25)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name = "country", length = 25)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name = "pincode", length = 25)
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<ProblemAndProblemSource> getProblemAndProblemSources() {
		return problemAndProblemSources;
	}

	public void setProblemAndProblemSources(Set<ProblemAndProblemSource> problemAndProblemSources) {
		this.problemAndProblemSources = problemAndProblemSources;
	}
	 
	
	
	 @Column(name = "access_type", length = 40)
	 public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	@Column(name = "access_value", length = 40)
	public String getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(String accessValue) {
		this.accessValue = accessValue;
	}	
	
	@Column(name = "user_type", length = 25)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	 public Party getParty() {
	 return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	
	@Column(name = "include_party_imp_date_status", length = 25)
	public String getIncludePartyImpDateStatus() {
		return includePartyImpDateStatus;
	}

	public void setIncludePartyImpDateStatus(String includePartyImpDateStatus) {
		this.includePartyImpDateStatus = includePartyImpDateStatus;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserEvents> getUserEvents() {
	return userEvents;
	}

	public void setUserEvents(Set<UserEvents> userEvents) {
		this.userEvents = userEvents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserImpDate> getUserImpDates() {
		return userImpDates;
	}

	public void setUserImpDates(Set<UserImpDate> userImpDates) {
		this.userImpDates = userImpDates;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "readRightUserId")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupPrivileges> getReadWriteUserPrevilegesSet() {
		return readWriteUserPrevilegesSet;
	}

	public void setReadWriteUserPrevilegesSet(
			Set<UserGroupPrivileges> readWriteUserPrevilegesSet) {
		this.readWriteUserPrevilegesSet = readWriteUserPrevilegesSet;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "writeRightUserId")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupPrivileges> getWriteWriteUserPrevilegesSet() {
		return writeWriteUserPrevilegesSet;
	}

	public void setWriteWriteUserPrevilegesSet(
			Set<UserGroupPrivileges> writeWriteUserPrevilegesSet) {
		this.writeWriteUserPrevilegesSet = writeWriteUserPrevilegesSet;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupRelation> getUserGroupRelations() {
		return userGroupRelations;
	}

	public void setUserGroupRelations(Set<UserGroupRelation> userGroupRelations) {
		this.userGroupRelations = userGroupRelations;
	}

	public void setOpinionPoll(Set<OpinionPoll> opinionPoll) {
		this.opinionPoll = opinionPoll;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<OpinionPoll> getOpinionPoll() {
		return opinionPoll;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<InfluencingPeople> getInfluencingPeople() {
		return influencingPeople;
	}

	public void setInfluencingPeople(Set<InfluencingPeople> influencingPeople) {
		this.influencingPeople = influencingPeople;
	}


	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getParentUser() {
		return parentUser;
	}

	public void setParentUser(Registration parentUser) {
		this.parentUser = parentUser;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentUser")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Registration> getSubUsers() {
		return subUsers;
	}

	public void setSubUsers(Set<Registration> subUsers) {
		this.subUsers = subUsers;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserAnnouncement> getUserAnnouncement() {
		return userAnnouncement;
	}

	public void setUserAnnouncement(Set<UserAnnouncement> userAnnouncement) {
		this.userAnnouncement = userAnnouncement;
	}
	
	
	
}
