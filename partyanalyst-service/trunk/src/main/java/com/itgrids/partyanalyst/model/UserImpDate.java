package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
/**
 * 
 * @author Narender Akla
 *
 */
@Entity	
@Table(name="user_imp_dates")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserImpDate  extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long userImpDateID;
	private Registration user;
	private String title;
	private String description;
	private Date effectiveDate;
	private Date tillDate;
	private String recFreqType;
	private String isDeleted;
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_imp_dates_id", unique = true, nullable = false)
	public Long getUserImpDateID() {
		return userImpDateID;
	}
	public void setUserImpDateID(Long userImpDateID) {
		this.userImpDateID = userImpDateID;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}
	public void setUser(Registration user) {
		this.user = user;
	}
	@Column(name = "title", length = 100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "effective_date")
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@Column(name = "till_date")
	public Date getTillDate() {
		return tillDate;
	}
	public void setTillDate(Date tillDate) {
		this.tillDate = tillDate;
	}
	
	@Column(name = "rec_freq_type", length = 10)
	public String getRecFreqType() {
		return recFreqType;
	}
	public void setRecFreqType(String recFreqType) {
		this.recFreqType = recFreqType;
	}
	
	@Column(name = "is_deleted", length = 6)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
