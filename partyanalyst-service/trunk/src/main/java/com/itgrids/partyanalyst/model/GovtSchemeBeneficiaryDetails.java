package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.CascadeType;
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
@Table(name = "govt_scheme_beneficiary_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSchemeBeneficiaryDetails extends BaseModel implements Serializable{
	
	private Long govtSchemeBeneficiaryDetailsId;
	private String voterId;
	private String benefiaryName;
	private String avmId;
	private Long tdpCadreId;
	private String mobileNo;
	private Long govtSchemesId;
	private Long govtSchemePhaseId;
	private Long govtSchemeBenefitTypeId;
	private String userAddressId;
	private Long benefitedAmount;
	private Time benefitedTime;
	private String isDeleted;
	
	private TdpCadre tdpCadre;
	private Voter voter;
	private GovtSchemePhase govtSchemePhase;
	private GovtSchemeBenefitType govtSchemeBenefitType;
	private UserAddress userAddress;
	private GovtSchemes govtSchemes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_scheme_beneficiary_details_id", unique = true, nullable = false)
	public Long getGovtSchemeBeneficiaryDetailsId() {
		return govtSchemeBeneficiaryDetailsId;
	}
	public void setGovtSchemeBeneficiaryDetailsId(Long govtSchemeBeneficiaryDetailsId) {
		this.govtSchemeBeneficiaryDetailsId = govtSchemeBeneficiaryDetailsId;
	}
	
	@Column(name = "voter_id")
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	
	@Column(name = "benefiary_name")
	public String getBenefiaryName() {
		return benefiaryName;
	}
	public void setBenefiaryName(String benefiaryName) {
		this.benefiaryName = benefiaryName;
	}
	
	@Column(name = "avm_id")
	public String getAvmId() {
		return avmId;
	}
	public void setAvmId(String avmId) {
		this.avmId = avmId;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "govt_schemes_id")
	public Long getGovtSchemesId() {
		return govtSchemesId;
	}
	public void setGovtSchemesId(Long govtSchemesId) {
		this.govtSchemesId = govtSchemesId;
	}
	
	@Column(name = "govt_scheme_phase_id")
	public Long getGovtSchemePhaseId() {
		return govtSchemePhaseId;
	}
	public void setGovtSchemePhaseId(Long govtSchemePhaseId) {
		this.govtSchemePhaseId = govtSchemePhaseId;
	}
	
	@Column(name = "govt_scheme_benefit_type_id")
	public Long getGovtSchemeBenefitTypeId() {
		return govtSchemeBenefitTypeId;
	}
	public void setGovtSchemeBenefitTypeId(Long govtSchemeBenefitTypeId) {
		this.govtSchemeBenefitTypeId = govtSchemeBenefitTypeId;
	}
	
	@Column(name = "user_address_id")
	public String getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
	}
	
	@Column(name = "benefited_amount")
	public Long getBenefitedAmount() {
		return benefitedAmount;
	}
	public void setBenefitedAmount(Long benefitedAmount) {
		this.benefitedAmount = benefitedAmount;
	}
	
	@Column(name = "benefited_time")
	public Time getBenefitedTime() {
		return benefitedTime;
	}
	public void setBenefitedTime(Time benefitedTime) {
		this.benefitedTime = benefitedTime;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_scheme_phase_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtSchemePhase getGovtSchemePhase() {
		return govtSchemePhase;
	}
	public void setGovtSchemePhase(GovtSchemePhase govtSchemePhase) {
		this.govtSchemePhase = govtSchemePhase;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_scheme_benefit_type_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtSchemeBenefitType getGovtSchemeBenefitType() {
		return govtSchemeBenefitType;
	}
	public void setGovtSchemeBenefitType(GovtSchemeBenefitType govtSchemeBenefitType) {
		this.govtSchemeBenefitType = govtSchemeBenefitType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_address_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_schemes_id",insertable=false, updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtSchemes getGovtSchemes() {
		return govtSchemes;
	}
	public void setGovtSchemes(GovtSchemes govtSchemes) {
		this.govtSchemes = govtSchemes;
	}
	
}
