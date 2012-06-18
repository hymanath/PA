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
	 private Registration mainAccountUser;
	 private Set<UserAnnouncement> userAnnouncement = new HashSet<UserAnnouncement>(0);
	 private Set<Registration> totalSubUsers = new HashSet<Registration>(0);
	 private Set<SmsHistory> smsHistory = new HashSet<SmsHistory>(0);
	 private Set<UserEntitlementGroupRegion> userEntitlementGroupRegions = new HashSet<UserEntitlementGroupRegion>(0);
	 private Set<UserCandidateRelation> userCandidateRelations = new HashSet<UserCandidateRelation>(0);
	 private Set<UserLoginDetails> userLoginDetails = new HashSet<UserLoginDetails>(0);
	// private Set<UserRoles> userRoles = new HashSet<UserRoles>(0);
	 private State state;
	 private District district;
	 private Constituency constituency;
	 private Long stateId;
	 private Long districtId;
	 private Long constituencyId;
	 private String profileImg;
	 private Date registeredDate;
	 private Date updatedDate;
	 private String isPwdChanged;
	 private Set<UserProfileOpts> userProfileOptses = new HashSet<UserProfileOpts>(0);
	 private Set<UserApprovalDetails> userApprovalDetails = new HashSet<UserApprovalDetails>(0);
	 
	 private Set<CustomMessage> customMessageSender = new HashSet<CustomMessage>(0); 
	 private Set<CustomMessage> customMessageRecepient = new HashSet<CustomMessage>(0);
	 private Set<UserConnectedto> userConnectedtoSource = new HashSet<UserConnectedto>(0); 
	 private Set<UserConnectedto> userConnectedtoTarget = new HashSet<UserConnectedto>(0);
	 private Set<UserReferralEmails> userReferralEmails = new HashSet<UserReferralEmails>(0);
	 
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
	@Column(name = "firstname", length = 70)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "middlename", length = 70)
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Column(name = "lastname", length = 70)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name = "username", length = 50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", length = 50)
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
	
	@Column(name = "email", length = 50)
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
	@Column(name = "address", length = 250)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "gender", length = 10)
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="main_account_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getMainAccountUser() {
		return mainAccountUser;
	}

	public void setMainAccountUser(Registration mainAccountUser) {
		this.mainAccountUser = mainAccountUser;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mainAccountUser")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<Registration> getTotalSubUsers() {
		return totalSubUsers;
	}

	public void setTotalSubUsers(Set<Registration> totalSubUsers) {
		this.totalSubUsers = totalSubUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<SmsHistory> getSmsHistory() {
		return smsHistory;
	}

	public void setSmsHistory(Set<SmsHistory> smsHistory) {
		this.smsHistory = smsHistory;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserEntitlementGroupRegion> getUserEntitlementGroupRegions() {
		return userEntitlementGroupRegions;
	}

	public void setUserEntitlementGroupRegions(
			Set<UserEntitlementGroupRegion> userEntitlementGroupRegions) {
		this.userEntitlementGroupRegions = userEntitlementGroupRegions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserCandidateRelation> getUserCandidateRelations() {
		return userCandidateRelations;
	}

	public void setUserCandidateRelations(
			Set<UserCandidateRelation> userCandidateRelations) {
		this.userCandidateRelations = userCandidateRelations;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserLoginDetails> getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(Set<UserLoginDetails> userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public State getState() {
		return state;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="district_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}*/
	@Column(name="profile_Img",length=100)
	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	@Column(name = "registered_time", length = 10)
	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "is_pwd_changed", length = 10)
	public String getIsPwdChanged() {
		return isPwdChanged;
	}

	public void setIsPwdChanged(String isPwdChanged) {
		this.isPwdChanged = isPwdChanged;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserProfileOpts> getUserProfileOptses() {
		return userProfileOptses;
	}

	public void setUserProfileOptses(Set<UserProfileOpts> userProfileOptses) {
		this.userProfileOptses = userProfileOptses;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "registration")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserApprovalDetails> getUserApprovalDetails() {
		return userApprovalDetails;
	}

	public void setUserApprovalDetails(Set<UserApprovalDetails> userApprovalDetails) {
		this.userApprovalDetails = userApprovalDetails;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sender")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CustomMessage> getCustomMessageSender() {
		return customMessageSender;
	}

	public void setCustomMessageSender(Set<CustomMessage> customMessageSender) {
		this.customMessageSender = customMessageSender;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recepient")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<CustomMessage> getCustomMessageRecepient() {
		return customMessageRecepient;
	}

	public void setCustomMessageRecepient(Set<CustomMessage> customMessageRecepient) {
		this.customMessageRecepient = customMessageRecepient;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userSource")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserConnectedto> getUserConnectedtoSource() {
		return userConnectedtoSource;
	}

	public void setUserConnectedtoSource(Set<UserConnectedto> userConnectedtoSource) {
		this.userConnectedtoSource = userConnectedtoSource;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userTarget")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserConnectedto> getUserConnectedtoTarget() {
		return userConnectedtoTarget;
	}

	public void setUserConnectedtoTarget(Set<UserConnectedto> userConnectedtoTarget) {
		this.userConnectedtoTarget = userConnectedtoTarget;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserReferralEmails> getUserReferralEmails() {
		return userReferralEmails;
	}

	public void setUserReferralEmails(Set<UserReferralEmails> userReferralEmails) {
		this.userReferralEmails = userReferralEmails;
	}

	@Column(name = "state_id", length = 10)
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	@Column(name = "district_id", length = 10)
	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@Column(name = "constituency_id", length = 10)
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
}
