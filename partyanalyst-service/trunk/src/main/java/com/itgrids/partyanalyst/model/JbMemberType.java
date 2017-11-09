package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "jb_member_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbMemberType extends BaseModel implements Serializable {
	
		 private Long jbMemberTypeId;
		 private String memberType;
		 private Long orderNo;
		  
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "jb_member_type_id", unique = true, nullable = false)
		public Long getJbMemberTypeId() {
			return jbMemberTypeId;
		}
		public void setJbMemberTypeId(Long jbMemberTypeId) {
			this.jbMemberTypeId = jbMemberTypeId;
		}
		@Column(name="member_type")
		public String getMemberType() {
			return memberType;
		}
		public void setMemberType(String memberType) {
			this.memberType = memberType;
		}
		@Column(name="order_no")
		public Long getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(Long orderNo) {
			this.orderNo = orderNo;
		}
		  
}
