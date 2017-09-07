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
@Table(name="health_test")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HealthTest extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long healthTestId;
	private String testName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "health_test_id", unique = true, nullable = false)
	
	public Long getHealthTestId() {
		return healthTestId;
	}
	public void setHealthTestId(Long healthTestId) {
		this.healthTestId = healthTestId;
	}
	@Column(name="test_name" ,length=20)
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
}
