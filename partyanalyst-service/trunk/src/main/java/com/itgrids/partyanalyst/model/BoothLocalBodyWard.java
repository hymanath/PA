package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "booth_local_body_ward")
public class BoothLocalBodyWard extends BaseModel{

	private Long boothLocalBodyWardId;
	private Booth booth;
	private Constituency localBodyWard;
	
	public BoothLocalBodyWard(){
		
	}
	
	public BoothLocalBodyWard(Long boothLocalBodyWardId){
		this.boothLocalBodyWardId = boothLocalBodyWardId;
	}
	
	public BoothLocalBodyWard(Booth booth,
			Constituency localBodyWard) {
		this.booth = booth;
		this.localBodyWard = localBodyWard;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_local_body_ward_id", unique = true, nullable = false)
	public Long getBoothLocalBodyWardId() {
		return boothLocalBodyWardId;
	}
	
	public void setBoothLocalBodyWardId(Long boothLocalBodyWardId) {
		this.boothLocalBodyWardId = boothLocalBodyWardId;
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
	@JoinColumn(name = "ward_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getLocalBodyWard() {
		return localBodyWard;
	}
	
	public void setLocalBodyWard(Constituency localBodyWard) {
		this.localBodyWard = localBodyWard;
	}
	
}
