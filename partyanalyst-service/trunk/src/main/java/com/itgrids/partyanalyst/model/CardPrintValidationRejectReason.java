package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "card_print_validation_reject_reason")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardPrintValidationRejectReason extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -7956398359753938914L;
	
	private Long cardPrintValidationRejectReasonId;
	private Long cardPrintValidationId;
	private Long printRejectReasonId;
	
	private CardPrintValidation carPrintValidation;
	private PrintRejectReason printRejectReason;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_print_validation_reject_reason_id", unique = true, nullable = false)
	public Long getCardPrintValidationRejectReasonId() {
		return cardPrintValidationRejectReasonId;
	}
	public void setCardPrintValidationRejectReasonId(
			Long cardPrintValidationRejectReasonId) {
		this.cardPrintValidationRejectReasonId = cardPrintValidationRejectReasonId;
	}
	
	
	@Column(name="card_print_validation_id")
	public Long getCardPrintValidationId() {
		return cardPrintValidationId;
	}
	public void setCardPrintValidationId(Long cardPrintValidationId) {
		this.cardPrintValidationId = cardPrintValidationId;
	}
	
	@Column(name="print_reject_reason_id")
	public Long getPrintRejectReasonId() {
		return printRejectReasonId;
	}
	public void setPrintRejectReasonId(Long printRejectReasonId) {
		this.printRejectReasonId = printRejectReasonId;
	}
	

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "card_print_validation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardPrintValidation getCarPrintValidation() {
		return carPrintValidation;
	}
	public void setCarPrintValidation(CardPrintValidation carPrintValidation) {
		this.carPrintValidation = carPrintValidation;
	}
	

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "print_reject_reason_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PrintRejectReason getPrintRejectReason() {
		return printRejectReason;
	}
	public void setPrintRejectReason(PrintRejectReason printRejectReason) {
		this.printRejectReason = printRejectReason;
	}
	
	
	
	
}
