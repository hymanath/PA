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
@Table(name = "ward_booth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WardBooth  extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1956256713311484167L;
	private Long wardBoothId;
	private Constituency ward;
	private Booth booth;
	private PublicationDate publicationDate;
	private Constituency constituency;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ward_booth_id", unique = true , nullable = false)
	public Long getWardBoothId() {
		return wardBoothId;
	}
	
	public void setWardBoothId(Long wardBoothId) {
		this.wardBoothId = wardBoothId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getWard() {
		return ward;
	}
	
	public void setWard(Constituency ward) {
		this.ward = ward;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_date_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}
	
	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	
}
