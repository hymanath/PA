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
@Table(name = "dynamic_keys")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DynamicKeys extends BaseModel implements java.io.Serializable{

	
	private static final long serialVersionUID = 4753159214500946392L;
	private Long dynamicKeysId;
	private String key;
	private String value;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dynamic_keys_id", unique = true, nullable = false)
	public Long getDynamicKeysId() {
		return dynamicKeysId;
	}
	public void setDynamicKeysId(Long dynamicKeysId) {
		this.dynamicKeysId = dynamicKeysId;
	}
	
	@Column(name="key")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
