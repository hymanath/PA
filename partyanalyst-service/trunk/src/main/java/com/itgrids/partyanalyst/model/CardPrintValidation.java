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
@Table(name = "card_print_validation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardPrintValidation extends BaseModel implements Serializable{
   
	
	private static final long serialVersionUID = 4921104915311915718L;
	private Long cardPrintValidationId;
	private Long cardPrintVendorId;
	private Long tdpCadreId;
	private String memberShipId;
	private String printStatus;
	private String rejectReason;
	private Long cardPrintValidationUserId;
	private Date insertedTime;
	private String boxNo;
	
	
	
	private CardPrintVendor cardPrintVendor;
	private TdpCadre   tdpCadre;
	private CardPrintValidationUser cardPrintValidationUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_print_validation_id", unique = true, nullable = false)
	public Long getCardPrintValidationId() {
		return cardPrintValidationId;
	}
	public void setCardPrintValidationId(Long cardPrintValidationId) {
		this.cardPrintValidationId = cardPrintValidationId;
	}
	
	@Column(name="card_print_vendor_id")
	public Long getCardPrintVendorId() {
		return cardPrintVendorId;
	}
	public void setCardPrintVendorId(Long cardPrintVendorId) {
		this.cardPrintVendorId = cardPrintVendorId;
	}
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="membership_id")
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	
	@Column(name="print_status")
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	@Column(name="card_print_validation_user_id")
	public Long getCardPrintValidationUserId() {
		return cardPrintValidationUserId;
	}
	public void setCardPrintValidationUserId(Long cardPrintValidationUserId) {
		this.cardPrintValidationUserId = cardPrintValidationUserId;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="box_no")
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "card_print_vendor_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardPrintVendor getCardPrintVendor() {
		return cardPrintVendor;
	}
	public void setCardPrintVendor(CardPrintVendor cardPrintVendor) {
		this.cardPrintVendor = cardPrintVendor;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "card_print_validation_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardPrintValidationUser getCardPrintValidationUser() {
		return cardPrintValidationUser;
	}
	public void setCardPrintValidationUser(
			CardPrintValidationUser cardPrintValidationUser) {
		this.cardPrintValidationUser = cardPrintValidationUser;
	}
	
	@Column(name="reject_reason")
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
}
