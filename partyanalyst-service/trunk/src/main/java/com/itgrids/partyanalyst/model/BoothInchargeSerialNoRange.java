package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "booth_incharge_serial_no_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothInchargeSerialNoRange extends BaseModel implements java.io.Serializable {
	
	private Long boothInchargeSerialNoRangeId;
	private String range;
	private Long minValue;
	private Long maxValue;
	private Long orderNo ;
	private String isDeleted;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge_serial_no_range_id", unique = true, nullable = false)
	public Long getBoothInchargeSerialNoRangeId() {
		return boothInchargeSerialNoRangeId;
	}
	public void setBoothInchargeSerialNoRangeId(Long boothInchargeSerialNoRangeId) {
		this.boothInchargeSerialNoRangeId = boothInchargeSerialNoRangeId;
	}
	@Column(name="range")
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	@Column(name="min_value")
	public Long getMinValue() {
		return minValue;
	}
	public void setMinValue(Long minValue) {
		this.minValue = minValue;
	}
	@Column(name="max_value")
	public Long getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
