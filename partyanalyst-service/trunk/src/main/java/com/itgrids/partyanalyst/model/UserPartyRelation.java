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
@Table(name = "user_party_relation")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserPartyRelation extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052000503369662694L;

	private Long userPartyRelationId;
	private User user;
	private Party party;
	
	public UserPartyRelation(){}
	public UserPartyRelation(User user, Party party)
	{
		this.user = user;
		this.party = party;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_party_relation_id", unique=true, nullable=false)
	public Long getUserPartyRelationId() {
		return userPartyRelationId;
	}
	public void setUserPartyRelationId(Long userPartyRelationId) {
		this.userPartyRelationId = userPartyRelationId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	
}
