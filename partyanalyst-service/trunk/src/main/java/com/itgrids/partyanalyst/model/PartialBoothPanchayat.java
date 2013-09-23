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
@Table (name="partial_booth_panchayat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartialBoothPanchayat implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long partialBoothPanchayatId;
	private Booth booth;
	private Panchayat panchayat;
	private String description;
	
	public PartialBoothPanchayat(){
		super();
	}
	
	public PartialBoothPanchayat(Long partialBoothPanchayatId,Booth booth,Panchayat panchayat,String description){
		super();
		this.partialBoothPanchayatId=partialBoothPanchayatId;
		this.booth = booth;
		this.panchayat = panchayat;
		this.description = description;
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "partial_booth_panchayat_id",nullable= false,unique = true)
	public Long getPartialBoothPanchayatId() {
		return partialBoothPanchayatId;
	}
	
	public void setPartialBoothPanchayatId(Long partialBoothPanchayatId) {
		this.partialBoothPanchayatId = partialBoothPanchayatId;
	}
	
	@JoinColumn(name ="booth_id")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	@JoinColumn(name ="panchayat_id")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
