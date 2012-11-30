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
@Table(name = "hamlet_booth_publication")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HamletBoothPublication implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long hamletBoothPublicationId;
	private Booth booth;
	private Hamlet hamlet;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hamlet_booth_publication_id", unique=true, nullable=false)
	public Long getHamletBoothPublicationId() {
		return hamletBoothPublicationId;
	}
	public void setHamletBoothPublicationId(Long hamletBoothPublicationId) {
		this.hamletBoothPublicationId = hamletBoothPublicationId;
	}
	
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	
	
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="hamlet_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Hamlet getHamlet() {
		return hamlet;
	}
	public void setHamlet(Hamlet hamlet) {
		this.hamlet = hamlet;
	}
	
	

}
