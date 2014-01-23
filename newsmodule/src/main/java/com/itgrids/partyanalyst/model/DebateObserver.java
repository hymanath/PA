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
@Table(name = "debate_observer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateObserver  extends BaseModel implements Serializable{
	
	private Integer debateObserverid;
	private Debate debate;
	private Observer observer;
	
	
	public DebateObserver(){}

	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_observer_id", unique = true, nullable = false)
	public Integer getDebateObserverid() {
		return debateObserverid;
	}


	public void setDebateObserverid(Integer debateObserverid) {
		this.debateObserverid = debateObserverid;
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "observer_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Observer getObserver() {
		return observer;
	}


	public void setObserver(Observer observer) {
		this.observer = observer;
	}




}
