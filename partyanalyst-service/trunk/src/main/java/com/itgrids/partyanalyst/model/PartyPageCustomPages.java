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

/**
 * party_page_custom_pages entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="party_page_custom_pages")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyPageCustomPages extends BaseModel implements Serializable{

	private static final long serialVersionUID = -8933100304599273867L;
	
	private Long partyPageCustomPagesId;
	private Party party;
	private CustomPage customPage;
	
	public PartyPageCustomPages()
	{}
	
	public PartyPageCustomPages(Party party,CustomPage customPage)
	{
		this.party = party;
		this.customPage = customPage;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_page_custom_pages_id", unique=true, nullable=false)
	public Long getPartyPageCustomPagesId() {
		return partyPageCustomPagesId;
	}

	public void setPartyPageCustomPagesId(Long partyPageCustomPagesId) {
		this.partyPageCustomPagesId = partyPageCustomPagesId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="custom_page_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CustomPage getCustomPage() {
		return customPage;
	}

	public void setCustomPage(CustomPage customPage) {
		this.customPage = customPage;
	}
	
	
	
}
