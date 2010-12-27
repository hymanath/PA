package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_sms_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsTrack extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long smsTrackId;
	private Long renewalSmsCount;
	private String renewalDate;
	private String description;
	private Registration registration;
	private Set<SmsHistory> smsHistory = new HashSet<SmsHistory>(0);
		
	// Constructors

	/** default constructor */
	public SmsTrack() {
	}

	/** minimal constructor */
	public SmsTrack(Long smsTrackId) {
		this.smsTrackId = smsTrackId;
	}

	/** full constructor */
	public SmsTrack(Long smsTrackId,Registration registration,Long renewalSmsCount,String renewalDate,String description,Set<SmsHistory> smsHistory) {
		this.smsTrackId = smsTrackId;
		this.registration = registration;
		this.renewalSmsCount = renewalSmsCount;
		this.renewalDate = renewalDate;
		this.description = description;
		this.smsHistory = smsHistory;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_sms_track_id", unique = true, nullable = false)
	public Long getSmsTrackId() {
		return smsTrackId;
	}

	public void setSmsTrackId(Long smsTrackId) {
		this.smsTrackId = smsTrackId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "smsTrack")
	public Set<SmsHistory> getSmsHistory() {
		return smsHistory;
	}

	public void setSmsHistory(Set<SmsHistory> smsHistory) {
		this.smsHistory = smsHistory;
	}

	@Column(name = "renewal_sms_count", length = 250)
	public Long getRenewalSmsCount() {
		return renewalSmsCount;
	}

	public void setRenewalSmsCount(Long renewalSmsCount) {
		this.renewalSmsCount = renewalSmsCount;
	}

	@Column(name = "renewal_date", length = 250)
	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
