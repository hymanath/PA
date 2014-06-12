package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "user_survey_booths")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserSurveyBooths extends BaseModel implements Serializable{
	
	
	private Long userSurveyBoothsId;
	private User user;
	private Constituency constituency;
	private PublicationDate publicationDate;
	
	public UserSurveyBooths()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_survey_booths_id", unique=true, nullable=false)
	public Long getUserSurveyBoothsId() {
		return userSurveyBoothsId;
	}
	public void setUserSurveyBoothsId(Long userSurveyBoothsId) {
		this.userSurveyBoothsId = userSurveyBoothsId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_date_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	
	
	
	
}
