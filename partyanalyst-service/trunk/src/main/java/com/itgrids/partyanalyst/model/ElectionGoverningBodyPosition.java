package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "election_governing_body_position")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionGoverningBodyPosition {

	private Long governingBodyPositionId;
	private String governingBodyPosition;
	
	public ElectionGoverningBodyPosition(){
		
	}
	
	public ElectionGoverningBodyPosition(Long governingBodyPositionId){
		this.governingBodyPositionId = governingBodyPositionId;
	}
	
	public ElectionGoverningBodyPosition(Long governingBodyPositionId,
			String governingBodyPosition) {
		this.governingBodyPositionId = governingBodyPositionId;
		this.governingBodyPosition = governingBodyPosition;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_governing_body_position_id", unique = true, nullable = false)
	public Long getGoverningBodyPositionId() {
		return governingBodyPositionId;
	}
	
	public void setGoverningBodyPositionId(Long governingBodyPositionId) {
		this.governingBodyPositionId = governingBodyPositionId;
	}
	
	@Column(name = "position")
	public String getGoverningBodyPosition() {
		return governingBodyPosition;
	}
	
	public void setGoverningBodyPosition(String governingBodyPosition) {
		this.governingBodyPosition = governingBodyPosition;
	}
	
	
}
