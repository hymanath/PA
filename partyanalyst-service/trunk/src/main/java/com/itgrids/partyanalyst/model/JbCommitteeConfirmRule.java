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
@Table(name = "jb_committee_confirm_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeConfirmRule extends BaseModel implements Serializable {

	
	private Long jbCommitteeConfirmRuleId;
	private String desc;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_confirm_rule_id", unique = true, nullable = false)
	public Long getJbCommitteeConfirmRuleId() {
		return jbCommitteeConfirmRuleId;
	}
	public void setJbCommitteeConfirmRuleId(Long jbCommitteeConfirmRuleId) {
		this.jbCommitteeConfirmRuleId = jbCommitteeConfirmRuleId;
	}
	@Column(name="desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
