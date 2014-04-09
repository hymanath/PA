package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

		

@Entity
@Table(name = "mobile_numbers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileNumbers extends BaseModel implements Serializable{
	
	private Long mobile;
	private Long slnoinpart;
	private String idcardNO;
	private Long acId;
	private Long partNo;
	private Long distNo;
	
	
	public MobileNumbers()
	{
		
	}
	public MobileNumbers(Long mobile,Long slnoinpart,String idcardNO,Long acId,Long partNo,Long distNo)
	{
	this.mobile = mobile;
	this.slnoinpart =slnoinpart;
	this.idcardNO =idcardNO;
	this.acId =acId;
	this.partNo = partNo;
	this.distNo = distNo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCARD_NO", unique = true, nullable = false)
	public String getIdcardNO() {
		return idcardNO;
	}
	public void setIdcardNO(String idcardNO) {
		this.idcardNO = idcardNO;
	}
	@Column(name = "mobile")
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	@Column(name = "slnoinpart")
	public Long getSlnoinpart() {
		return slnoinpart;
	}
	public void setSlnoinpart(Long slnoinpart) {
		this.slnoinpart = slnoinpart;
	}
	
	@Column(name = "AC_ID")
	public Long getAcId() {
		return acId;
	}
	public void setAcId(Long acId) {
		this.acId = acId;
	}
	@Column(name = "PART_NO")
	public Long getPartNo() {
		return partNo;
	}
	
	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}
	@Column(name = "DIST_NO")
	public Long getDistNo() {
		return distNo;
	}
	public void setDistNo(Long distNo) {
		this.distNo = distNo;
	}
	
	

}
