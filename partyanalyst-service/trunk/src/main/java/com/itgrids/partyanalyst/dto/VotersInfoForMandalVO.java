package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VotersInfoForMandalVO {
	
	private String mandalId;
	private String mandalName;
	private String totalMaleVoters;
	private String totalFemaleVoters;
	private String totalVoters;
	private String isPartial;
	private String percent;
	private BigDecimal totVoters;
	private Boolean isMandal = false;
	private String unKnowVoters;
	private List<VotersInfoForMandalVO> votersInfoForMandalVOList = new ArrayList<VotersInfoForMandalVO>();
	private boolean subLevelExists;
	private Long id;
	private String name;
	private String type;
	private boolean datapresent = true;
	
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getTotalMaleVoters() {
		return totalMaleVoters;
	}
	public void setTotalMaleVoters(String totalMaleVoters) {
		this.totalMaleVoters = totalMaleVoters;
	}
	public String getTotalFemaleVoters() {
		return totalFemaleVoters;
	}
	public void setTotalFemaleVoters(String totalFemaleVoters) {
		this.totalFemaleVoters = totalFemaleVoters;
	}
	public String getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(String totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public BigDecimal getTotVoters() {
		return totVoters;
	}
	public void setTotVoters(BigDecimal totVoters) {
		this.totVoters = totVoters;
	}
	public Boolean getIsMandal() {
		return isMandal;
	}
	public void setIsMandal(Boolean isMandal) {
		this.isMandal = isMandal;
	}
	public String getUnKnowVoters() {
		return unKnowVoters;
	}
	public void setUnKnowVoters(String unKnowVoters) {
		this.unKnowVoters = unKnowVoters;
	}
	public List<VotersInfoForMandalVO> getVotersInfoForMandalVOList() {
		return votersInfoForMandalVOList;
	}
	public void setVotersInfoForMandalVOList(
			List<VotersInfoForMandalVO> votersInfoForMandalVOList) {
		this.votersInfoForMandalVOList = votersInfoForMandalVOList;
	}
	public boolean isSubLevelExists() {
		return subLevelExists;
	}
	public void setSubLevelExists(boolean subLevelExists) {
		this.subLevelExists = subLevelExists;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDatapresent() {
		return datapresent;
	}
	public void setDatapresent(boolean datapresent) {
		this.datapresent = datapresent;
	}
	
}
