package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "speech_aspect")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpeechAspect extends BaseModel implements Serializable{
	
	private Long speechAspectId;
	private String aspect;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "speech_aspect_id", unique = true, nullable = false)
	public Long getSpeechAspectId() {
		return speechAspectId;
	}
	public void setSpeechAspectId(Long speechAspectId) {
		this.speechAspectId = speechAspectId;
	}
	
	@Column(name = "aspect")
	public String getAspect() {
		return aspect;
	}
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}
	
	

}
