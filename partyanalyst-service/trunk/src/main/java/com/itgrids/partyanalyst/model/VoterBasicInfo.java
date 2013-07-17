package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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

/**
 * opinion_poll entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name="voter_basic_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterBasicInfo extends BaseModel implements Serializable{

	private static final long serialVersionUID = 2048256691512757382L;
	
	private Long voterBasicInfoId;
	private Constituency constituency;
	private VoterReportLevel voterReportLevel;
	private Long reportLevelValue;
	private Long year;
	private String type;
	private Long booths;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private Long totalDiff;
	private Long maleDiff;
	private Long femaleDiff;
	private Integer orderNo;
	
	public VoterBasicInfo(){}
	
	public VoterBasicInfo(Constituency constituency,VoterReportLevel voterReportLevel,Long reportLevelValue,Long year
			,String type,Long booths,Long totalVoters,Long maleVoters,Long femaleVoters,Long totalDiff,Long maleDiff,
			Long femaleDiff,Integer orderNo)
	{
		this.constituency = constituency;
		this.voterReportLevel = voterReportLevel;
		this.reportLevelValue = reportLevelValue;
		this.year = year;
		this.type = type;
		this.booths = booths;
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.totalDiff = totalDiff;
		this.maleDiff = maleDiff;
		this.femaleDiff = femaleDiff;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voter_basic_info_id", unique=true, nullable=false)
	public Long getVoterBasicInfoId() {
		return voterBasicInfoId;
	}

	public void setVoterBasicInfoId(Long voterBasicInfoId) {
		this.voterBasicInfoId = voterBasicInfoId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}

	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="report_level_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterReportLevel getVoterReportLevel() {
		return voterReportLevel;
	}

	public void setVoterReportLevel(VoterReportLevel voterReportLevel) {
		this.voterReportLevel = voterReportLevel;
	}

	@Column(name="report_level_value")
	public Long getReportLevelValue() {
		return reportLevelValue;
	}

	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}

	@Column(name="year")
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	@Column(name="type",length=15)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="booths")
	public Long getBooths() {
		return booths;
	}

	public void setBooths(Long booths) {
		this.booths = booths;
	}

	@Column(name="total_voters")
	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	@Column(name="male_voters")
	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}

	@Column(name="female_voters")
	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	@Column(name="total_diff")
	public Long getTotalDiff() {
		return totalDiff;
	}

	public void setTotalDiff(Long totalDiff) {
		this.totalDiff = totalDiff;
	}

	@Column(name="male_diff")
	public Long getMaleDiff() {
		return maleDiff;
	}

	public void setMaleDiff(Long maleDiff) {
		this.maleDiff = maleDiff;
	}

	@Column(name="female_diff")
	public Long getFemaleDiff() {
		return femaleDiff;
	}

	public void setFemaleDiff(Long femaleDiff) {
		this.femaleDiff = femaleDiff;
	}

	@Column(name="order_no")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}
