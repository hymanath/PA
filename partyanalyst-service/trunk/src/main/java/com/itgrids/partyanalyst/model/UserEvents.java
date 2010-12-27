package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;
/**
 * 
 * @author Narender Akula
 *
 */
@Entity
@Table(name="user_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEvents extends BaseModel {

	private static final long serialVersionUID = 1L;
	private Long userEventsId;
	private Registration registration;
	private String description;
	private String locationType;
	private Long locationId;
	private Date startDate;
	private Date endDate;
	private String title;
	private List<Cadre> organizers;
	private List<UserEventActionPlan> userEventActionPlans;
	private String isDeleted;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "user_event_id", unique = true, nullable = false)
	public Long getUserEventsId() {
		return userEventsId;
	}
	public void setUserEventsId(Long userEventsId) {
		this.userEventsId = userEventsId;
	}
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id")
	 @org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "location_type", length = 100)
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	@Column(name = "location_id")
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@ManyToMany
	@JoinTable(name = "user_events_organizer", 
			joinColumns = { @JoinColumn(name = "user_event_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "cadre_id") })
	public List<Cadre> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(List<Cadre> organizers) {
		this.organizers = organizers;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEvents")
	public List<UserEventActionPlan> getUserEventActionPlans() {
		return userEventActionPlans;
	}
	public void setUserEventActionPlans(
			List<UserEventActionPlan> userEventActionPlans) {
		this.userEventActionPlans = userEventActionPlans;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "is_deleted", length = 6)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		/*if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEvents other = (UserEvents) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;*/
		return true;
	}
	
	
}
