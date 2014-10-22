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
@Table(name = "card_receiver")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardReceiver implements Serializable {

	private static final long serialVersionUID = 2522481819589602604L;
	
	private Long 		cardReceiverId;
	private CardSender 	cardSender;
	private TdpCadre	tdpCadre;
	private String 		isDeleted;
	private Long 		tdpCadreId;
	private Long 		cardSenderId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="card_receiver_id", unique=true, nullable=false)
	public Long getCardReceiverId() {
		return cardReceiverId;
	}
	public void setCardReceiverId(Long cardReceiverId) {
		this.cardReceiverId = cardReceiverId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "card_sender_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CardSender getCardSender() {
		return cardSender;
	}
	public void setCardSender(CardSender cardSender) {
		this.cardSender = cardSender;
	}
	
	@Column(name="card_sender_id")
	public Long getCardSenderId() {
		return cardSenderId;
	}
	public void setCardSenderId(Long cardSenderId) {
		this.cardSenderId = cardSenderId;
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
	
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}	

}
