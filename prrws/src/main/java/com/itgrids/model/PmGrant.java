package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_grant")
public class PmGrant {
	
   private Long pmGrantId;
   private String pmGrantName;
   private String isDeleted;
   private Long orderNo;
   
    @Id
	@Column(name="pm_grant_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
    public Long getPmGrantId() {
		return pmGrantId;
	}
	public void setPmGrantId(Long pmGrantId) {
		this.pmGrantId = pmGrantId;
	}
	@Column(name="pm_grant_name")
	public String getPmGrantName() {
		return pmGrantName;
	}
	public void setPmGrantName(String pmGrantName) {
		this.pmGrantName = pmGrantName;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	} 
}
