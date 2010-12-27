package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * 
 * @author Narender Akula
 *
 */
@Entity
@Table(name="event_action_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEventActionPlan extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long eventActionPlanId;
	private String action;
	private UserEvents userEvents;
	private Date targetDate;
	private List<Cadre> organizers;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "event_action_plan_id", unique = true, nullable = false)
	public Long getEventActionPlanId() {
		return eventActionPlanId;
	}
	
	public void setEventActionPlanId(Long eventActionPlanId) {
		this.eventActionPlanId = eventActionPlanId;
	}

	@Column(name = "action", length = 250)
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_event_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public UserEvents getUserEvents() {
		return userEvents;
	}

	public void setUserEvents(UserEvents userEvents) {
		this.userEvents = userEvents;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "target_date", length = 10)	
	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	@ManyToMany
	@JoinTable(name = "event_action_organizer", 
			joinColumns = { @JoinColumn(name = "event_action_plan_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "cadre_id") })
	public List<Cadre> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(List<Cadre> organizers) {
		this.organizers = organizers;
	}
}
