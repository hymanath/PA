package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "voter_report_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterReportLevel extends BaseModel implements Serializable{

	
	private static final long serialVersionUID = 6922632189211774141L;
	private Long voterReportLevelId;
	private String reportLevel;
	private Long orderNo ;
	private Set<VoterInfo> voterInfos = new HashSet<VoterInfo>(0);
	private Set<VoterAgeInfo> voterAgeInfos = new HashSet<VoterAgeInfo>(0);
	private Set<VoterFamilyInfo> voterFamilyInfos = new HashSet<VoterFamilyInfo>(0);
	private Set<VoterCastBasicInfo> voterCastBasicInfos = new HashSet<VoterCastBasicInfo>(0);
	private Set<VoterCastInfo> voterCastInfos = new HashSet<VoterCastInfo>(0);
	
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY , mappedBy = "voterReportLevel")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterInfo> getVoterInfos() {
		return voterInfos;
	}
	public void setVoterInfos(Set<VoterInfo> voterInfos) {
		this.voterInfos = voterInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterReportLevel")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterAgeInfo> getVoterAgeInfos() {
		return voterAgeInfos;
	}
	public void setVoterAgeInfos(Set<VoterAgeInfo> voterAgeInfos) {
		this.voterAgeInfos = voterAgeInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterReportLevel")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterFamilyInfo> getVoterFamilyInfos() {
		return voterFamilyInfos;
	}
	public void setVoterFamilyInfos(Set<VoterFamilyInfo> voterFamilyInfos) {
		this.voterFamilyInfos = voterFamilyInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterReportLevel")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterCastBasicInfo> getVoterCastBasicInfos() {
		return voterCastBasicInfos;
	}
	public void setVoterCastBasicInfos(Set<VoterCastBasicInfo> voterCastBasicInfos) {
		this.voterCastBasicInfos = voterCastBasicInfos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterReportLevel")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Set<VoterCastInfo> getVoterCastInfos() {
		return voterCastInfos;
	}
	public void setVoterCastInfos(Set<VoterCastInfo> voterCastInfos) {
		this.voterCastInfos = voterCastInfos;
	}
	
		
}
