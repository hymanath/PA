package com.itgrids.partyanalyst.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="td_member_total")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdMember {

	private Long nMemberId;
	private Long nMemberIDOld;
	private String sMemberShipNo;
	private String sMemberName;
	private String sMemberTName;
	private String sRelationName;
	private Long nRelationType;
	private Long nMemberType;
	private String sGender;
	private String sAddress3;
	private String sLandPhoneNumber;
	private String sMobileNumber;
	private String sPinCode;
	private Date dateofBirth;
	private String sPhoto;
	private Date joinedDate;
	private Long nQualificationID;
	private Long qualifficationIdTemp;
	private Long nBoothID;
	private Long nHabitationID;
	private Long nPanchayatID;
	private String bActive;
	private Long nCreatedOperatorID;
	private Long nModifiedOperatorID;
	private Date dtCreated;
	private Date dtLastModified;
	private Long nAssemblyID;
	private Long assemblyIdTemp;
	private Long nMandalID;
	private Long mandalIdTemp;
	private String sEmail;
	private Long localElectionBodyIdTemp;
	private Long sOccupation;
	private Long occupationTemp;
	private Long nCasteID;
	private Long casteIdTemp;
	private String sPresentDesignation;
	private String sReferenceMemberName;
	private String nReferenceDesignation;
	private Date dtRefDate;
	private Long nIStatus;
	private Long nReferenceMemberID;
	private Date dtSyncdate;
	private Long nStatus;
	private Long nUStatus;
	private Date dtStatesDate;
	private Long nFStatus;
	private Long panchayatIdTemp;
	private Panchayat panchayat;
	private Long education;
	private Long tehsilId;
	private Long localElectionId;
	private Long occupationId;
	private Long casteStateId;
	private Long enrollmentYear;
	
/*
CREATE TABLE `td_member` (
  `nMemberID` bigint(20) NOT NULL,
  `nMemberID_OLD` bigint(20) DEFAULT NULL,
  `sMemberShipNo` varchar(10) DEFAULT NULL,
  `sMemberName` varchar(100) DEFAULT NULL,
  `sMemberTName` longtext,
  `sRelationName` varchar(100) DEFAULT NULL,
  `nRelationType` int(11) DEFAULT NULL,
  `nMemberType` int(11) DEFAULT NULL,
  `sGender` varchar(1) DEFAULT NULL,
  `sAddress3` varchar(256) DEFAULT NULL,
  `sLandPhoneNumber` varchar(15) DEFAULT NULL,
  `sMobileNumber` varchar(15) DEFAULT NULL,
  `sPinCode` varchar(15) DEFAULT NULL,
  `dtDOB` datetime DEFAULT NULL,
  `sPhoto` varchar(256) DEFAULT NULL,
  `dtJoined` datetime DEFAULT NULL,
  `nQualificationID` int(11) DEFAULT NULL,
  `qualiffication_id_temp` bigint(15) DEFAULT NULL,
  `nBoothID` int(11) DEFAULT NULL,
  `nHabitationID` int(11) DEFAULT NULL,
  `nPanchayatID` int(11) DEFAULT NULL,
  `bActive` varchar(1) NOT NULL,
  `nCreatedOperatorID` int(11) DEFAULT NULL,
  `nModifiedOperatorID` int(11) DEFAULT NULL,
  `dtCreated` datetime DEFAULT NULL,
  `dtLastModified` datetime DEFAULT NULL,
  `nAssemblyID` int(11) NOT NULL,
  `assembly_id_temp` bigint(20) DEFAULT NULL,
  `nMandalID` int(11) NOT NULL,
  `mandal_id_temp` bigint(15) DEFAULT NULL,
  `sEmail` varchar(50) DEFAULT NULL,
  `local_election_body_id_temp` bigint(20) DEFAULT NULL,
  `sOccupation` int(11) DEFAULT NULL,
  `occupation_temp` bigint(15) DEFAULT NULL,
  `nCasteID` int(11) NOT NULL,
  `caste_id_temp` bigint(15) DEFAULT NULL,
  `sPresentDesignation` varchar(4000) DEFAULT NULL,
  `sReferenceMemberName` varchar(100) DEFAULT NULL,
  `nReferenceDesignation` varchar(50) DEFAULT NULL,
  `dtRefDate` datetime DEFAULT NULL,
  `nIStatus` int(11) DEFAULT NULL,
  `nReferenceMemberID` bigint(20) DEFAULT NULL,
  `dtSyncdate` datetime DEFAULT NULL,
  `nStatus` int(11) DEFAULT NULL,
  `nUStatus` int(11) DEFAULT NULL,
  `dtStatesDate` datetime DEFAULT NULL,
  `nFStatus` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;*/
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "nMemberId", unique = true, nullable = false)
	public Long getnMemberId() {
		return nMemberId;
	}
	public void setnMemberId(Long nMemberId) {
		this.nMemberId = nMemberId;
	}
	
	@Column(name="nMemberIDOld")
	public Long getnMemberIDOld() {
		return nMemberIDOld;
	}
	public void setnMemberIDOld(Long nMemberIDOld) {
		this.nMemberIDOld = nMemberIDOld;
	}
	
	@Column(name="sMemberShipNo")
	public String getsMemberShipNo() {
		return sMemberShipNo;
	}
	public void setsMemberShipNo(String sMemberShipNo) {
		this.sMemberShipNo = sMemberShipNo;
	}
	
	@Column(name="sMemberName")
	public String getsMemberName() {
		return sMemberName;
	}
	public void setsMemberName(String sMemberName) {
		this.sMemberName = sMemberName;
	}
	@Column(name="sMemberTName")
	public String getsMemberTName() {
		return sMemberTName;
	}
	public void setsMemberTName(String sMemberTName) {
		this.sMemberTName = sMemberTName;
	}
	
	@Column(name="sRelationName")
	public String getsRelationName() {
		return sRelationName;
	}
	public void setsRelationName(String sRelationName) {
		this.sRelationName = sRelationName;
	}
	
	@Column(name="nRelationType")
	public Long getnRelationType() {
		return nRelationType;
	}
	public void setnRelationType(Long nRelationType) {
		this.nRelationType = nRelationType;
	}
	
	@Column(name="nMemberType")
	public Long getnMemberType() {
		return nMemberType;
	}
	public void setnMemberType(Long nMemberType) {
		this.nMemberType = nMemberType;
	}
	
	@Column(name="sGender")
	public String getsGender() {
		return sGender;
	}
	public void setsGender(String sGender) {
		this.sGender = sGender;
	}
	
	@Column(name="sAddress3")
	public String getsAddress3() {
		return sAddress3;
	}
	public void setsAddress3(String sAddress3) {
		this.sAddress3 = sAddress3;
	}
	
	@Column(name="sLandPhoneNumber")
	public String getsLandPhoneNumber() {
		return sLandPhoneNumber;
	}
	public void setsLandPhoneNumber(String sLandPhoneNumber) {
		this.sLandPhoneNumber = sLandPhoneNumber;
	}
	
	@Column(name="sMobileNumber")
	public String getsMobileNumber() {
		return sMobileNumber;
	}
	public void setsMobileNumber(String sMobileNumber) {
		this.sMobileNumber = sMobileNumber;
	}
	
	@Column(name="sPinCode")
	public String getsPinCode() {
		return sPinCode;
	}
	public void setsPinCode(String sPinCode) {
		this.sPinCode = sPinCode;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtDOB")
	public Date getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	
	@Column(name="sPhoto")
	public String getsPhoto() {
		return sPhoto;
	}
	public void setsPhoto(String sPhoto) {
		this.sPhoto = sPhoto;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtJoined")
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	@Column(name="nQualificationID")
	public Long getnQualificationID() {
		return nQualificationID;
	}
	public void setnQualificationID(Long nQualificationID) {
		this.nQualificationID = nQualificationID;
	}
	
	@Column(name="qualiffication_id_temp")
	public Long getQualifficationIdTemp() {
		return qualifficationIdTemp;
	}
	public void setQualifficationIdTemp(Long qualifficationIdTemp) {
		this.qualifficationIdTemp = qualifficationIdTemp;
	}
	
	@Column(name="nBoothID")
	public Long getnBoothID() {
		return nBoothID;
	}
	public void setnBoothID(Long nBoothID) {
		this.nBoothID = nBoothID;
	}
	
	@Column(name="nHabitationID")
	public Long getnHabitationID() {
		return nHabitationID;
	}
	public void setnHabitationID(Long nHabitationID) {
		this.nHabitationID = nHabitationID;
	}
	
	@Column(name="nPanchayatID")
	public Long getnPanchayatID() {
		return nPanchayatID;
	}
	public void setnPanchayatID(Long nPanchayatID) {
		this.nPanchayatID = nPanchayatID;
	}
	
	@Column(name="bActive")
	public String getbActive() {
		return bActive;
	}
	public void setbActive(String bActive) {
		this.bActive = bActive;
	}
	
	@Column(name="nCreatedOperatorID")
	public Long getnCreatedOperatorID() {
		return nCreatedOperatorID;
	}
	public void setnCreatedOperatorID(Long nCreatedOperatorID) {
		this.nCreatedOperatorID = nCreatedOperatorID;
	}
	
	@Column(name="nModifiedOperatorID")
	public Long getnModifiedOperatorID() {
		return nModifiedOperatorID;
	}
	public void setnModifiedOperatorID(Long nModifiedOperatorID) {
		this.nModifiedOperatorID = nModifiedOperatorID;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtCreated")
	public Date getDtCreated() {
		return dtCreated;
	}
	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="dtLastModified")
	public Date getDtLastModified() {
		return dtLastModified;
	}
	public void setDtLastModified(Date dtLastModified) {
		this.dtLastModified = dtLastModified;
	}
	
	@Column(name="nAssemblyID")
	public Long getnAssemblyID() {
		return nAssemblyID;
	}
	public void setnAssemblyID(Long nAssemblyID) {
		this.nAssemblyID = nAssemblyID;
	}
	
	//@Column(name="assembly_id_temp")
	@Column(name="constituency_id_pa")
	public Long getAssemblyIdTemp() {
		return assemblyIdTemp;
	}
	public void setAssemblyIdTemp(Long assemblyIdTemp) {
		this.assemblyIdTemp = assemblyIdTemp;
	}
	
	@Column(name="nMandalID")
	public Long getnMandalID() {
		return nMandalID;
	}
	public void setnMandalID(Long nMandalID) {
		this.nMandalID = nMandalID;
	}
	
	@Column(name="mandal_id_temp")
	public Long getMandalIdTemp() {
		return mandalIdTemp;
	}
	public void setMandalIdTemp(Long mandalIdTemp) {
		this.mandalIdTemp = mandalIdTemp;
	}
	
	@Column(name="sEmail")
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	
	@Column(name="local_election_body_id_temp")
	public Long getLocalElectionBodyIdTemp() {
		return localElectionBodyIdTemp;
	}
	public void setLocalElectionBodyIdTemp(Long localElectionBodyIdTemp) {
		this.localElectionBodyIdTemp = localElectionBodyIdTemp;
	}
	
	@Column(name="sOccupation")
	public Long getsOccupation() {
		return sOccupation;
	}
	public void setsOccupation(Long sOccupation) {
		this.sOccupation = sOccupation;
	}
	
	@Column(name="occupation_temp")
	public Long getOccupationTemp() {
		return occupationTemp;
	}
	public void setOccupationTemp(Long occupationTemp) {
		this.occupationTemp = occupationTemp;
	}
	
	@Column(name="nCasteID")
	public Long getnCasteID() {
		return nCasteID;
	}
	public void setnCasteID(Long nCasteID) {
		this.nCasteID = nCasteID;
	}
	
	@Column(name="caste_id_temp")
	public Long getCasteIdTemp() {
		return casteIdTemp;
	}
	public void setCasteIdTemp(Long casteIdTemp) {
		this.casteIdTemp = casteIdTemp;
	}
	
	@Column(name="sPresentDesignation")
	public String getsPresentDesignation() {
		return sPresentDesignation;
	}
	public void setsPresentDesignation(String sPresentDesignation) {
		this.sPresentDesignation = sPresentDesignation;
	}
	
	@Column(name="sReferenceMemberName")
	public String getsReferenceMemberName() {
		return sReferenceMemberName;
	}
	public void setsReferenceMemberName(String sReferenceMemberName) {
		this.sReferenceMemberName = sReferenceMemberName;
	}
	
	@Column(name="nReferenceDesignation")
	public String getnReferenceDesignation() {
		return nReferenceDesignation;
	}
	public void setnReferenceDesignation(String nReferenceDesignation) {
		this.nReferenceDesignation = nReferenceDesignation;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtRefDate")
	public Date getDtRefDate() {
		return dtRefDate;
	}
	public void setDtRefDate(Date dtRefDate) {
		this.dtRefDate = dtRefDate;
	}
	
	@Column(name="nIStatus")
	public Long getnIStatus() {
		return nIStatus;
	}
	public void setnIStatus(Long nIStatus) {
		this.nIStatus = nIStatus;
	}
	
	@Column(name="nReferenceMemberID")
	public Long getnReferenceMemberID() {
		return nReferenceMemberID;
	}
	public void setnReferenceMemberID(Long nReferenceMemberID) {
		this.nReferenceMemberID = nReferenceMemberID;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtSyncdate")
	public Date getDtSyncdate() {
		return dtSyncdate;
	}
	public void setDtSyncdate(Date dtSyncdate) {
		this.dtSyncdate = dtSyncdate;
	}
	
	@Column(name="nStatus")
	public Long getnStatus() {
		return nStatus;
	}
	public void setnStatus(Long nStatus) {
		this.nStatus = nStatus;
	}
	
	@Column(name="nUStatus")
	public Long getnUStatus() {
		return nUStatus;
	}
	public void setnUStatus(Long nUStatus) {
		this.nUStatus = nUStatus;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="dtStatesDate")
	public Date getDtStatesDate() {
		return dtStatesDate;
	}
	public void setDtStatesDate(Date dtStatesDate) {
		this.dtStatesDate = dtStatesDate;
	}
	
	@Column(name="nFStatus")
	public Long getnFStatus() {
		return nFStatus;
	}
	public void setnFStatus(Long nFStatus) {
		this.nFStatus = nFStatus;
	}
	
    @Column(name="panchayat_idtemp")
	public Long getPanchayatIdTemp() {
		return panchayatIdTemp;
	}
	public void setPanchayatIdTemp(Long panchayatIdTemp) {
		this.panchayatIdTemp = panchayatIdTemp;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="panchayat_id_pa",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	
	@Column(name="qualification_id_pa")
	public Long getEducation() {
		return education;
	}
	public void setEducation(Long education) {
		this.education = education;
	}
	
	@Column(name="tehsil_id_pa")
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	
	@Column(name="local_election_body_id_pa")
	public Long getLocalElectionId() {
		return localElectionId;
	}
	public void setLocalElectionId(Long localElectionId) {
		this.localElectionId = localElectionId;
	}
	
	@Column(name="occupation_id_pa")
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	
	@Column(name="caste_id_pa")
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	@Column(name="enrollmentYear")
	public Long getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(Long enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	
}