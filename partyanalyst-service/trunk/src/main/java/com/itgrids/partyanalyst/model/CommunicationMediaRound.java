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
@Table(name = "communication_media_round")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommunicationMediaRound extends BaseModel implements java.io.Serializable{
     
	private Long communicationMediaRoundId;
    private Long roundNo;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "communication_media_round_id", unique = true, nullable = false)
	public Long getCommunicationMediaRoundId() {
		return communicationMediaRoundId;
	}
	public void setCommunicationMediaRoundId(Long communicationMediaRoundId) {
		this.communicationMediaRoundId = communicationMediaRoundId;
	}
	
	@Column(name = "round_no")
	public Long getRoundNo() {
		return roundNo;
	}
	public void setRoundNo(Long roundNo) {
		this.roundNo = roundNo;
	}
    
    
}
