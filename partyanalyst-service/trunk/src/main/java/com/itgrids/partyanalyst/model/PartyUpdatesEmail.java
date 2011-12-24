package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "party_updates_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyUpdatesEmail implements Serializable {

	private Long partyUpdatesEmailId;
	private Party party;
	private String email;
	private String unsubscribed;

	public PartyUpdatesEmail() {

	}

	public PartyUpdatesEmail(Long partyUpdatesEmailId, Party party,
			String email, String unsubscribed) {
		this.partyUpdatesEmailId = partyUpdatesEmailId;
		this.party = party;
		this.email = email;
		this.unsubscribed = unsubscribed;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_updates_email_id", unique = true, nullable = false)
	public Long getPartyUpdatesEmailId() {
		return partyUpdatesEmailId;
	}

	public void setPartyUpdatesEmailId(Long partyUpdatesEmailId) {
		this.partyUpdatesEmailId = partyUpdatesEmailId;
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

	@Column(name = "email", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "unsubscribed", length = 10)
	public String getUnsubscribed() {
		return unsubscribed;
	}

	public void setUnsubscribed(String unsubscribed) {
		this.unsubscribed = unsubscribed;
	}

}
