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
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends BaseModel implements Serializable{

	private static final long serialVersionUID = -1087247750076396944L;
	
	private Long userId;
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
	private String userType;
	private Party party;
	private String includePartyImpDateStatus;
	private User parentUser;
	private User mainAccountUser;
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
	 
	 public User(){}
	 
	 public User(String firstName, String middleName, String lastName, String gender,
			 String userName, String password,  Date dateOfBirth, String email, String phone,
			 String mobile, String address, String country, String pincode, String accessType,
			 String accessValue, UserCategory userCategory, String userType, Party party, 
			 String includePartyImpDateStatus, User parentUser, User mainAccountUser, 
			 Long stateId, Long districtId, Long constituencyId, String profileImg, Date registeredDate,
			 Date updatedDate, String isPwdChanged)
	 {
		 this.firstName  = firstName;
		 this.middleName  = middleName;
		 this.lastName  = lastName;
		 this.gender  = gender;
		 this.userName  = userName;
		 this.password  = password; 
		 this.dateOfBirth  = dateOfBirth;
		 this.email  = email;
		 this.phone  = phone;
		 this.mobile  = mobile;
		 this.address  = address;
		 this.country  = country;
		 this.pincode  = pincode;
		 this.accessType  = accessType;
		 this.accessValue  = accessValue;
		 this.userCategory  = userCategory;
		 this.userType  = userType;
		 this.party  = party;
		 this.includePartyImpDateStatus  = includePartyImpDateStatus;
		 this.parentUser  = parentUser;
		 this.mainAccountUser  = mainAccountUser;
		 this.stateId  = stateId;
		 this.districtId  = districtId;
		 this.constituencyId  = constituencyId;
		 this.profileImg  = profileImg;
		 this.registeredDate  = registeredDate;
		 this.updatedDate  = updatedDate;
		 this.isPwdChanged  = isPwdChanged; 
	 }

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id", unique=true, nullable=false)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	@Column(name = "gender", length = 10)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="parent_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getParentUser() {
		return parentUser;
	}

	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="main_account_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getMainAccountUser() {
		return mainAccountUser;
	}

	public void setMainAccountUser(User mainAccountUser) {
		this.mainAccountUser = mainAccountUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="district_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id", updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
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

	@Column(name="profile_Img",length=100)
	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	@Column(name="registered_time")
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserProfileOpts> getUserProfileOptses() {
		return userProfileOptses;
	}

	public void setUserProfileOptses(Set<UserProfileOpts> userProfileOptses) {
		this.userProfileOptses = userProfileOptses;
	}

}
