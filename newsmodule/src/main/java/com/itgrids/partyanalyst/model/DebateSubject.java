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
@Table(name = "debate_subject")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateSubject {

	
private Long debateSubjectId;
private Debate debate;
private String subject;
private Font font;


public DebateSubject(){}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "debate_subject_id", unique = true, nullable = false)
public Long getDebateSubjectId() {
	return debateSubjectId;
}



public void setDebateSubjectId(Long debateSubjectId) {
	this.debateSubjectId = debateSubjectId;
}


@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
@JoinColumn(name = "debate_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
public Debate getDebate() {
	return debate;
}



public void setDebate(Debate debate) {
	this.debate = debate;
}


@Column(name = "subject",length=50)
public String getSubject() {
	return subject;
}



public void setSubject(String subject) {
	this.subject = subject;
}

@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "font_id")
@LazyToOne(LazyToOneOption.NO_PROXY)
@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
public Font getFont() {
	return font;
}

public void setFont(Font font) {
	this.font = font;
}





	
	
	
	
	
	
	
	
}
