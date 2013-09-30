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
@Table(name = "hamlet_booth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HamletBooth extends BaseModel implements Serializable{

	
	private static final long serialVersionUID = 3010997703255871804L;
	private Long hamletBoothId;
	/*private Long boothId;
	private Long hamletId;*/
	private Booth booth;
	private Hamlet hamlet;
	
	public HamletBooth(){}
	/*public HamletBooth(Long boothId, Long hamletId)
	{
		this.boothId = boothId;
		this.hamletId = hamletId;
	}*/
	
	public HamletBooth(Booth booth, Hamlet hamlet)
	{
		this.booth = booth;
		this.hamlet = hamlet;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="hamlet_booth_id",unique = true,nullable = false)
	public Long getHamletBoothId() {
		return hamletBoothId;
	}
	public void setHamletBoothId(Long hamletBoothId) {
		this.hamletBoothId = hamletBoothId;
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
	
	/*@Column(name = "booth_id",length = 15)
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@Column(name = "hamlet_id" ,length = 15)
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}*/
	
	
	
	
 
}
