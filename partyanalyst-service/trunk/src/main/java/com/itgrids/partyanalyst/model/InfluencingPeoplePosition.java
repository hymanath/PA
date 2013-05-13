package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "influencing_people_position")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InfluencingPeoplePosition extends BaseModel{

	private static final long serialVersionUID = 1L;
	private Long influencingPeoplePositionId;
	private String position;
	private String description;
	private Date updatedDate;
	private Set<InfluencingPeople> influencingPeoples = new HashSet<InfluencingPeople>();
	private String isGlobal;
	private Long userId;
	private User user;
	
	public InfluencingPeoplePosition(){
		
	}
	
	public InfluencingPeoplePosition(Long influencingPeoplePositionId){
		this.influencingPeoplePositionId = influencingPeoplePositionId;
	}

	public InfluencingPeoplePosition(String position, String description, Date updatedDate,
			Set<InfluencingPeople> influencingPeoples) {
		this.position = position;
		this.description = description;
		this.updatedDate = updatedDate;
		this.influencingPeoples = influencingPeoples;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "influencing_people_position_id", unique = true, nullable = false)
	public Long getInfluencingPeoplePositionId() {
		return influencingPeoplePositionId;
	}

	public void setInfluencingPeoplePositionId(Long influencingPeoplePositionId) {
		this.influencingPeoplePositionId = influencingPeoplePositionId;
	}

	@Column(name = "position_type", length = 50)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "position_desc", length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "upadated_date", length = 10)
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "influencingPeoplePosition")
	public Set<InfluencingPeople> getInfluencingPeoples() {
		return influencingPeoples;
	}

	public void setInfluencingPeoples(Set<InfluencingPeople> influencingPeoples) {
		this.influencingPeoples = influencingPeoples;
	}
	@Column(name = "is_global",length = 20)
	public String getIsGlobal() {
		return isGlobal;
	}
	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}
	@Column(name = "user_id",length = 15)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
