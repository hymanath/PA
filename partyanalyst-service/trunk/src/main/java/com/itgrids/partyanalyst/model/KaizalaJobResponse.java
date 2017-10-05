package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "kaizala_job_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaJobResponse extends BaseModel implements Serializable{
	
	private Long kaizalaJobResponseId;
	private Long kaizalaEventsResponseId;
	private String jobResponse;
	
	private KaizalaEventsResponse kaizalaEventsResponse;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kaizala_job_response_id", unique = true, nullable = false)
	public Long getKaizalaJobResponseId() {
		return kaizalaJobResponseId;
	}

	public void setKaizalaJobResponseId(Long kaizalaJobResponseId) {
		this.kaizalaJobResponseId = kaizalaJobResponseId;
	}
	@Column(name = "kaizala_events_response_id")
	public Long getKaizalaEventsResponseId() {
		return kaizalaEventsResponseId;
	}

	public void setKaizalaEventsResponseId(Long kaizalaEventsResponseId) {
		this.kaizalaEventsResponseId = kaizalaEventsResponseId;
	}
	@Column(name = "job_response")
	public String getJobResponse() {
		return jobResponse;
	}

	public void setJobResponse(String jobResponse) {
		this.jobResponse = jobResponse;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="kaizala_events_response_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public KaizalaEventsResponse getKaizalaEventsResponse() {
		return kaizalaEventsResponse;
	}

	public void setKaizalaEventsResponse(KaizalaEventsResponse kaizalaEventsResponse) {
		this.kaizalaEventsResponse = kaizalaEventsResponse;
	}
	
}
