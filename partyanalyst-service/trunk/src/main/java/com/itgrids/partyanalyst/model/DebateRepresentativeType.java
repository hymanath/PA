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
 * 
 * @author Srujana
 *
 */


@Entity
@Table(name = "debate_representative_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateRepresentativeType implements java.io.Serializable{

	private Long debateRepresentativeTypeId;
	private String type;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "debate_representative_type_id", unique = true, nullable = false)
	public Long getDebateRepresentativeTypeId() {
		return debateRepresentativeTypeId;
	}
	public void setDebateRepresentativeTypeId(Long debateRepresentativeTypeId) {
		this.debateRepresentativeTypeId = debateRepresentativeTypeId;
	}
	
	@Column(name="position")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
