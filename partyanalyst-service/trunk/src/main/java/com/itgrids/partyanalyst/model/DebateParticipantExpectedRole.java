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
@Table(name = "debate_participant_expected_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateParticipantExpectedRole {

private Long debateParticipantRoleId;
private DebateParticipant debateParticipant;

private DebateRoles debateRoles;


 public  DebateParticipantExpectedRole(){}


	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_participant_expected_role_id", unique = true, nullable = false)
public Long getDebateParticipantRoleId() {
	return debateParticipantRoleId;
}


public void setDebateParticipantRoleId(Long debateParticipantRoleId) {
	this.debateParticipantRoleId = debateParticipantRoleId;
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
@JoinColumn(name = "debate_roles_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public DebateRoles getDebateRoles() {
	return debateRoles;
}


public void setDebateRoles(DebateRoles debateRoles) {
	this.debateRoles = debateRoles;
}
 
 
	
	
	
	
	
	
	
}
