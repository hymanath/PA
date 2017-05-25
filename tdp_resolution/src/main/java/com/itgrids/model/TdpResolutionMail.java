package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

public class TdpResolutionMail {

	private Long tdpResolutionMailId;
	private TdpResolution tdpResolution;
	private TdpEmail tdpEmail;
	
	@Id
	@Column(name="tdp_resolution_mail_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getTdpResolutionMailId() {
		return tdpResolutionMailId;
	}
	public void setTdpResolutionEmailId(Long tdpResolutionMailId) {
		this.tdpResolutionMailId = tdpResolutionMailId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_resolution_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpResolution getTdpResolution() {
		return tdpResolution;
	}
	public void setResolution(TdpResolution tdpResolution) {
		this.tdpResolution = tdpResolution;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tdp_email_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpEmail getTdpEmail() {
		return tdpEmail;
	}
	public void setTdpEmail(TdpEmail tdpEmail) {
		this.tdpEmail = tdpEmail;
	}
}
