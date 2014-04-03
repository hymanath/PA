package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "sms_sent_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsSentType extends BaseModel implements java.io.Serializable  {

	private static final long serialVersionUID = 2770484105252236590L;
	
	// Fields
	
	private Long smsSentTypeId;
	private String type;
	
	public SmsSentType(Long smsSentTypeId,String type) {
		this.smsSentTypeId = smsSentTypeId;
		this.type = type;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sms_sent_type_id", unique = true, nullable = false)
	public Long getSmsSentTypeId() {
		return smsSentTypeId;
	}
	public void setSmsSentTypeId(Long smsSentTypeId) {
		this.smsSentTypeId = smsSentTypeId;
	}
	
	@Column(name="type" , length = 45)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
