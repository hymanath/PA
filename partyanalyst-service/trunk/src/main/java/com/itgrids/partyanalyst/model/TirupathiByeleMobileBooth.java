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
@Table(name="tirupathi_byele_mobile_booth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TirupathiByeleMobileBooth extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6455050842550570647L;
	
	private Long tirupathiByeleMobileBoothId;
	private Long boothId;
	private Booth booth;
	private String mobileNo;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tirupathi_byele_mobile_booth_id", unique=true, nullable=false)
	public Long getTirupathiByeleMobileBoothId() {
		return tirupathiByeleMobileBoothId;
	}

	public void setTirupathiByeleMobileBoothId(Long tirupathiByeleMobileBoothId) {
		this.tirupathiByeleMobileBoothId = tirupathiByeleMobileBoothId;
	}

	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}

	public void setBooth(Booth booth) {
		this.booth = booth;
	}

	@Column(name="mobile_no", length = 45)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
		
}
