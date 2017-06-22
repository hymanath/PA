package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class KPIVO {
	
	private Long partiallyCoveredAchivement = 0l;
	private Long partiallyCoveredTarget = 0l;
	private Long qualityAffectedAchivement = 0l;
	private Long qualityAffectedTarget = 0l;
	private List<Long> kpiVoList = new ArrayList<Long>(0);
	private String partiallyCoveredPerc = "", qualityAffectedPerc = "";
	
	
	
	public String getPartiallyCoveredPerc() {
		return partiallyCoveredPerc;
	}
	public void setPartiallyCoveredPerc(String partiallyCoveredPerc) {
		this.partiallyCoveredPerc = partiallyCoveredPerc;
	}
	public String getQualityAffectedPerc() {
		return qualityAffectedPerc;
	}
	public void setQualityAffectedPerc(String qualityAffectedPerc) {
		this.qualityAffectedPerc = qualityAffectedPerc;
	}
	public Long getPartiallyCoveredAchivement() {
		return partiallyCoveredAchivement;
	}
	public void setPartiallyCoveredAchivement(Long partiallyCoveredAchivement) {
		this.partiallyCoveredAchivement = partiallyCoveredAchivement;
	}
	public Long getPartiallyCoveredTarget() {
		return partiallyCoveredTarget;
	}
	public void setPartiallyCoveredTarget(Long partiallyCoveredTarget) {
		this.partiallyCoveredTarget = partiallyCoveredTarget;
	}
	public Long getQualityAffectedAchivement() {
		return qualityAffectedAchivement;
	}
	public void setQualityAffectedAchivement(Long qualityAffectedAchivement) {
		this.qualityAffectedAchivement = qualityAffectedAchivement;
	}
	public Long getQualityAffectedTarget() {
		return qualityAffectedTarget;
	}
	public void setQualityAffectedTarget(Long qualityAffectedTarget) {
		this.qualityAffectedTarget = qualityAffectedTarget;
	}
	public List<Long> getKpiVoList() {
		return kpiVoList;
	}
	public void setKpiVoList(List<Long> kpiVoList) {
		this.kpiVoList = kpiVoList;
	}
	
	
}
