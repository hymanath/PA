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
@Table(name="ananymous_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AnanymousUser extends BaseModel implements java.io.Serializable  {
	
	 private static final long serialVersionUID = 1L;
	 
	 private Long userId;
	 private String name;
	 private String lastName;
	 private String gender;
	 private String username;
	 private String password;
	 private Date dateofbirth;
	 private String email;
	 private String phone;
	 private String mobile;
	 private String address;
	 private String pincode;
	 private State state;
	 private District district;
	 private Constituency constituency;
	 private String isPwdChanged;
	 private String profileImg;
	 private Date registeredDate;
	 private Date updatedDate;
	 
	public AnanymousUser()
	 {
		 
	 }
	 
	 public AnanymousUser(Long userId)
	 {
		 this.userId = userId;
	 }
	
	public AnanymousUser(String name, String gender,
			String username, String password, Date dateofbirth, String email,
			String phone, String mobile, String address, String pincode,
			State state, District district, Constituency constituency,
			Date registeredDate,
			Date updatedDate) {
		this.name = name;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.dateofbirth = dateofbirth;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.pincode = pincode;
		this.state = state;
		this.district = district;
		this.constituency = constituency;
		this.registeredDate = registeredDate;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id", unique=true, nullable=false)
	public Long getUserId() {
		return userId;
	}

	@Column(name="name",length=70)
	public String getName() {
		return name;
	}

	@Column(name="gender",length=10)
	public String getGender() {
		return gender;
	}

	@Column(name="username",length=40)
	public String getUsername() {
		return username;
	}

	@Column(name="password",length=40)
	public String getPassword() {
		return password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateofbirth", length = 10)
	public Date getDateofbirth() {
		return dateofbirth;
	}

	@Column(name="email",length=100)
	public String getEmail() {
		return email;
	}

	@Column(name="phone",length=25)
	public String getPhone() {
		return phone;
	}

	@Column(name="mobile",length=25)
	public String getMobile() {
		return mobile;
	}
	
	@Column(name="address",length=250)
	public String getAddress() {
		return address;
	}

	@Column(name="pincode",length=15)
	public String getPincode() {
		return pincode;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
		public State getState() {
		return state;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="district_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public District getDistrict() {
		return district;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	@Column(name="last_name",length=70)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
		

	
}
