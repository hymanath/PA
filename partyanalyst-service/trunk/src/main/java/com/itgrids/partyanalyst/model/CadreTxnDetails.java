package com.itgrids.partyanalyst.model;

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
@Table(name = "cadre_txn_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreTxnDetails implements java.io.Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6017137644243190119L;
	private Long cadreTxnDetailsId;
	private Long cadreSurveyUserId;
	private CadreSurveyUser cadreSurveyUser;
	private Long constiteuncyId;
	private Constituency constituency;
	private Long sinkedRecords;
	private Long pendingRecords;
	private Long totalAmount;
	private Long paidAmount;
	private Long pendingAmount;
	private Date insertedTime;  
	private Date updatedTime; 
	private Date surveyTime; 
	private String txnStatus;
	private String completeStatus;
	private String uniqueKey;
	private String mobileNo;
	private CadreOtpDetails cadreOtpDetails;
	private Long cadreOtpDetailsId;
	
	private String 		agentName;
	private String		agentReconConstyName;
	private String      agentVillage;
	private String 		agentMobileNo;
	
	public CadreTxnDetails() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_txn_details_id", nullable = false, unique = true)
	public Long getCadreTxnDetailsId() {
		return cadreTxnDetailsId;
	}

	public void setCadreTxnDetailsId(Long cadreTxnDetailsId) {
		this.cadreTxnDetailsId = cadreTxnDetailsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_survey_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}

	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}

	@Column(name = "cadre_survey_user_id", length =10)
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}

	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@Column(name = "constituency_id", length =20)
	public Long getConstiteuncyId() {
		return constiteuncyId;
	}

	public void setConstiteuncyId(Long constiteuncyId) {
		this.constiteuncyId = constiteuncyId;
	}

	@Column(name = "sinked_records", length =20)
	public Long getSinkedRecords() {
		return sinkedRecords;
	}

	public void setSinkedRecords(Long sinkedRecords) {
		this.sinkedRecords = sinkedRecords;
	}

    @Column(name = "pending_records", length =20)
	public Long getPendingRecords() {
		return pendingRecords;
	}

	public void setPendingRecords(Long pendingRecords) {
		this.pendingRecords = pendingRecords;
	}

	@Column(name = "total_amount", length =15)
	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "paid_amount", length =15)
	public Long getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}

	@Column(name = "pending_amount", length =15)
	public Long getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Long pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Column(name = "survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}

	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}

	@Column(name = "txn_status", length =45)
	public String getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(String txnStatus) {
		this.txnStatus = txnStatus;
	}

	@Column(name = "complete_status", length =45)
	public String getCompleteStatus() {
		return completeStatus;
	}
	
	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	@Column(name = "unique_key", length =45)
	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="agent_name")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Column(name="agent_constituency")
	public String getAgentReconConstyName() {
		return agentReconConstyName;
	}

	public void setAgentReconConstyName(String agentReconConstyName) {
		this.agentReconConstyName = agentReconConstyName;
	}

	@Column(name="agent_village")
	public String getAgentVillage() {
		return agentVillage;
	}

	public void setAgentVillage(String agentVillage) {
		this.agentVillage = agentVillage;
	}

	@Column(name="agent_mobile_no")
	public String getAgentMobileNo() {
		return agentMobileNo;
	}

	public void setAgentMobileNo(String agentMobileNo) {
		this.agentMobileNo = agentMobileNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_otp_details_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreOtpDetails getCadreOtpDetails() {
		return cadreOtpDetails;
	}

	public void setCadreOtpDetails(CadreOtpDetails cadreOtpDetails) {
		this.cadreOtpDetails = cadreOtpDetails;
	}

	@Column(name="cadre_otp_details_id")
	public Long getCadreOtpDetailsId() {
		return cadreOtpDetailsId;
	}

	public void setCadreOtpDetailsId(Long cadreOtpDetailsId) {
		this.cadreOtpDetailsId = cadreOtpDetailsId;
	}
	
	
	
	

}
