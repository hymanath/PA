package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "tdp_cadre_family_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreFamilyDetails implements Serializable{

	
	private static final long serialVersionUID = -4898155853845716990L;
	private Long 						tdpCadreFamilyDetailsId;
	private Long 						tdpCadreId;
	private Long		 				educationId;
	private Long 						occupationId;
	private Long 						voterId;
	private String 						voterName;
	private String 						isDeleted;
	private Long						familyRelationId;
	
	private Date 						insertedDate;
	private Date 						updatedDate;
	
	private TdpCadre 					tdpCadre;
	private Voter 						voter;
	private EducationalQualifications 	education;
	private Occupation 					occupation;
	private Long                        age;
	private String                      gender;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_family_details_id", unique = true, nullable = false)
	public Long getTdpCadreFamilyDetailsId() {
		return tdpCadreFamilyDetailsId;
	}
	public void setTdpCadreFamilyDetailsId(Long tdpCadreFamilyDetailsId) {
		this.tdpCadreFamilyDetailsId = tdpCadreFamilyDetailsId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="education_id")
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	
	@Column(name="occupation_id")
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	
	@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name="inserted_date")
	public Date getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}
	
	@Column(name="update_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "voter_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "education_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EducationalQualifications getEducation() {
		return education;
	}
	public void setEducation(EducationalQualifications education) {
		this.education = education;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "occupation_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="voter_name")
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="family_relation_id")
	public Long getFamilyRelationId() {
		return familyRelationId;
	}
	public void setFamilyRelationId(Long familyRelationId) {
		this.familyRelationId = familyRelationId;
	}
	
	
}
