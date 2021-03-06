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

import org.hibernate.annotations.NotFoundAction;
/**
 * 
 * @author Narender Akula
 *
 */
@Entity
@Table(name = "cadre")
public class Cadre extends BaseModel{

	 private Long cadreId;
	 private Registration registration;
	 private CadreLevel cadreLevel;
	 private Long cadreLevelValue;
	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String gender;
	 private String email;
	 private String mobile;
	 private State state;
	 private District district;
	 private Constituency constituency;
	 private Tehsil tehsil;
	 //private Long villageId;
	 private Township village;
	 private Long boothId;
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_id", unique = true, nullable = false)
	 public Long getCadreId() {
		return cadreId;
	 }
	
	 public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	 }
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id")
	 @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	 public Registration getRegistration() {
		return registration;
	 }
	
	 public void setRegistration(Registration registration) {
		this.registration = registration;
	 }
	//
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "cadre_level_id")
	 public CadreLevel getCadreLevel() {
		return cadreLevel;
	 }
	
	 public void setCadreLevel(CadreLevel cadreLevel) {
		this.cadreLevel = cadreLevel;
	 }
	
	 @Column(name = "first_name", length = 40)
	 public String getFirstName() {
		return firstName;
	 }
	 public void setFirstName(String firstName) {
		this.firstName = firstName;
	 }
	
	 @Column(name = "middle_name", length = 40)
	 public String getMiddleName() {
		return middleName;
	 }
	
	 public void setMiddleName(String middleName) {
		this.middleName = middleName;
	 }
	
	 @Column(name = "last_name", length = 40)
	 public String getLastName() {
		return lastName;
	 }
	 public void setLastName(String lastName) {
		this.lastName = lastName;
	 }
	
	 @Column(name = "gender", length = 1)
	 public String getGender() {
		return gender;
	 }
	 public void setGender(String gender) {
		this.gender = gender;
	 }
	
	 @Column(name = "email", length = 11)
	 public String getEmail() {
		return email;
	 }
	 public void setEmail(String email) {
		this.email = email;
	 }
	
	 @Column(name = "mobile_no", length = 11)	
	 public String getMobile() {
		return mobile;
	 }
	 public void setMobile(String mobile) {
		this.mobile = mobile;
	 }
	
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "state_id")
	 public State getState() {
		return this.state;
	 }
	
	 public void setState(State state) {
		this.state = state;
	 }
		
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "district_id")
	 public District getDistrict() {
		return this.district;
	 }
	 public void setDistrict(District district) {
		this.district = district;
	 }

	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "constituency_id")
	 public Constituency getConstituency(){
		 return constituency;
	 }
	 
	 public void setConstituency(Constituency constituency){
		 this.constituency = constituency;
	 }
	 
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "mandal_id")
	 public Tehsil getTehsil() {
		return this.tehsil;
	 }
	 public void setTehsil(Tehsil tehsil) {
	 	this.tehsil = tehsil;
	 }
	 
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinColumn(name = "village_id")
	 public Township getVillage() {
		return village;
	 }
	 public void setVillage(Township village) {
		this.village = village;
	 }
	 @Column(name = "booth_id", length = 15)
	 public Long getBoothId() {
		return boothId;
	 }
	 public void setBoothId(Long boothId) {
		this.boothId = boothId;
	 }
	 @Column(name = "cadre_level_value", length = 15)
	public Long getCadreLevelValue() {
		return cadreLevelValue;
	}

	public void setCadreLevelValue(Long cadreLevelValue) {
		this.cadreLevelValue = cadreLevelValue;
	}
	 
}
