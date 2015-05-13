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
@Table(name = "card_print_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardPrintUser {
	
	private Long cardPrintUserId;
	private String name;
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "card_print_user_id", nullable = false, unique = true)
	public Long getCardPrintUserId() {
		return cardPrintUserId;
	}
	public void setCardPrintUserId(Long cardPrintUserId) {
		this.cardPrintUserId = cardPrintUserId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
