package com.itgrids.partyanalyst.model;

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
@Table(name = "ivr_respondent_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrRespondentLocation {
	private Long ivrRespondentLocationId;
	private IvrRespondent ivrRespondent;
	private UserAddress userAddress;
	private String isCadre;
	private String isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = " ivr_respondent_location_id", unique = true, nullable = false)
	public Long getIvrRespondentLocationId() {
		return ivrRespondentLocationId;
	}

	public void setIvrRespondentLocationId(Long ivrRespondentLocationId) {
		this.ivrRespondentLocationId = ivrRespondentLocationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ivr_respondent_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public IvrRespondent getIvrRespondent() {
		return ivrRespondent;
	}

	public void setIvrRespondent(IvrRespondent ivrRespondent) {
		this.ivrRespondent = ivrRespondent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "is_cadre")
	public String getIsCadre() {
		return isCadre;
	}

	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
