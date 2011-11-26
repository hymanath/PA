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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "party_profile_description")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class PartyProfileDescription extends BaseModel implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;

	private Party party;
	private String description;
	private Long orderNo;
	private Long partyProfileDescriptionId;
	public PartyProfileDescription(){
		
	}
	
   public PartyProfileDescription(Long partyProfileDescriptionId,
		Party party, String description, Long orderNo) {

	this.partyProfileDescriptionId = partyProfileDescriptionId;
	this.party = party;
	this.description = description;
	this.orderNo = orderNo;
 }
   @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_profile_description_id", unique = true, nullable = false)
	
	public Long getPartyProfileDescriptionId() {
		return partyProfileDescriptionId;
	}

	public void setPartyProfileDescriptionId(Long partyProfileDescriptionId) {
		this.partyProfileDescriptionId = partyProfileDescriptionId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}


	
}
