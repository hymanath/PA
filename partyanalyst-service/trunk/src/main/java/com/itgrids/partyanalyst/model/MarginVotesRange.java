package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * MarginVotesRange entity. 
 * @author <a href="ramakrishna.madapati@itgrids.com">krishna</a>
 */
@Entity
@Table(name = "margin_votes_range")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MarginVotesRange extends BaseModel implements java.io.Serializable {
	
	
	
	private Long marginVotesRangeId;
	private String rangeValue;
	private String description;
	private Long minValue;
	private Long maxValue;
	private Long orderNo;
	private String isDeleted ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "margin_votes_range_id", unique = true, nullable = false)
	public Long getMarginVotesRangeId() {
		return marginVotesRangeId;
	}
	public void setMarginVotesRangeId(Long marginVotesRangeId) {
		this.marginVotesRangeId = marginVotesRangeId;
	}
	@Column(name="range_value")
	public String getRangeValue() {
		return rangeValue;
	}
	public void setRangeValue(String rangeValue) {
		this.rangeValue = rangeValue;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
