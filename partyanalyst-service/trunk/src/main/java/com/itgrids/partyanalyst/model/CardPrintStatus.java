package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "print_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardPrintStatus extends BaseModel implements java.io.Serializable {
	
	private Long cardPrintStatusId;
	private String cardPrintStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "print_status_id", unique = true, nullable = false)
	public Long getCardPrintStatusId() {
		return cardPrintStatusId;
	}
	public void setCardPrintStatusId(Long cardPrintStatusId) {
		this.cardPrintStatusId = cardPrintStatusId;
	}
	
	@Column(name = "print_status")
	public String getCardPrintStatus() {
		return cardPrintStatus;
	}
	public void setCardPrintStatus(String cardPrintStatus) {
		this.cardPrintStatus = cardPrintStatus;
	}
	
}
