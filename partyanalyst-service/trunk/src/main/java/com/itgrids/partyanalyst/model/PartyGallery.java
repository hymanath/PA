package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "party_gallery")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyGallery implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Long partyGalleryId;
	private String isDelete;
    private String isPrivate;
	private Date createdDate;
	private Date updatedDate;
	private Party party;
	private Gallary gallery;
	public PartyGallery()
	{
		
	}
	public PartyGallery(Long partyGalleryId,String isDelete,String isPrivate,
			Date createdDate,Date updatedDate,Party party,Gallary gallery){
		
		this.partyGalleryId = partyGalleryId;
		this. isDelete =  isDelete;
		this.isPrivate= isPrivate;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.party=party;
		this.gallery=gallery;
		
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_gallery_id", unique = true, nullable = false)
	public Long getPartyGalleryId() {
		return partyGalleryId;
	}
	
	public void setPartyGalleryId(Long partyGalleryId) {
		this.partyGalleryId = partyGalleryId;
	}
	@Column(name="is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	@Column(name="is_private")
	public String getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(String isPrivate) {
		this.isPrivate = isPrivate;
	}
	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "updated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id",nullable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}

    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="gallary_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Gallary getGallery() {
		return gallery;
	}
	public void setGallery(Gallary gallery) {
		this.gallery = gallery;
	}

}
