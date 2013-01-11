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
@Table(name = "voter_report_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterReportLevel extends BaseModel implements Serializable{

	
	private static final long serialVersionUID = 6922632189211774141L;
	private Long voterReportLevelId;
	private String reportLevel;
	private Long orderNo ;
	
	public VoterReportLevel(){}
	public VoterReportLevel(String reportLevel, Long orderNo)
	{
		this.reportLevel = reportLevel;
		this.orderNo = orderNo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_report_level_id", unique =true, nullable = false)
	public Long getVoterReportLevelId() {
		return voterReportLevelId;
	}
	public void setVoterReportLevelId(Long voterReportLevelId) {
		this.voterReportLevelId = voterReportLevelId;
	}
	
	@Column(name = "report_level", length = 30)
	public String getReportLevel() {
		return reportLevel;
	}
	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}
	
	@Column(name = "order_no", length = 3)
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
