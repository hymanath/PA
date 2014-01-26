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
@Table(name = "debate_participant_charcs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateParticipantCharcs {

	
	private Long debateParticipantCharcsId;
	private DebateParticipant debateParticipant;
	private Characteristics characteristics;
	private Double scale;
	
	
	
	public DebateParticipantCharcs(){}


	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_participant_charcs_id", unique = true, nullable = false)
	public Long getDebateParticipantCharcsId() {
		return debateParticipantCharcsId;
	}



	public void setDebateParticipantCharcsId(Long debateParticipantCharcsId) {
		this.debateParticipantCharcsId = debateParticipantCharcsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_participant_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DebateParticipant getDebateParticipant() {
		return debateParticipant;
	}


	public void setDebateParticipant(DebateParticipant debateParticipant) {
		this.debateParticipant = debateParticipant;
	}


	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "characteristics_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Characteristics getCharacteristics() {
		return characteristics;
	}



	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}


	@Column(name = "scale")
	public Double getScale() {
		return scale;
	}



	public void setScale(Double scale) {
		this.scale = scale;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
