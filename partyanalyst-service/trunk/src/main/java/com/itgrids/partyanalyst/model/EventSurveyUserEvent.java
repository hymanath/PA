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
@Table(name="event_survey_user_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EventSurveyUserEvent extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long eventSurveyUserEventId;
	private EventSurveyUser eventSurveyUser;
	private Event event;
	
	public EventSurveyUserEvent(){}
	
	public EventSurveyUserEvent(EventSurveyUser eventSurveyUser,Event event)
	{
		this.eventSurveyUser = eventSurveyUser;
		this.event = event;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="event_survey_user_event_id", unique=true, nullable=false)
	public Long getEventSurveyUserEventId() {
		return eventSurveyUserEventId;
	}

	public void setEventSurveyUserEventId(Long eventSurveyUserEventId) {
		this.eventSurveyUserEventId = eventSurveyUserEventId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="event_survey_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public EventSurveyUser getEventSurveyUser() {
		return eventSurveyUser;
	}

	public void setEventSurveyUser(EventSurveyUser eventSurveyUser) {
		this.eventSurveyUser = eventSurveyUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="event_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
