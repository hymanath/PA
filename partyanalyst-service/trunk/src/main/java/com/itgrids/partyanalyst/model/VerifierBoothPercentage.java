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
@Table(name = "verifier_booth_percentage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VerifierBoothPercentage {
	
	
	private Long verifierBoothPercentageId;
	private Long boothId;
	private String percentage;
	
	private Booth booth;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "verifier_booth_percentage_id", unique = true, nullable = false)
	public Long getVerifierBoothPercentageId() {
		return verifierBoothPercentageId;
	}

	public void setVerifierBoothPercentageId(Long verifierBoothPercentageId) {
		this.verifierBoothPercentageId = verifierBoothPercentageId;
	}

	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	@Column(name="percentage")
	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
}
