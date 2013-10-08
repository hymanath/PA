package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DelimitationEffectVO implements Serializable{


	private static final long serialVersionUID = 1L;
	private Long presentCount = 0l;
	private Long previousCount = 0l;;
	private Double presentPerc = 0.0;
	private Double previousPerc = 0.0;
	private Long presentPolledVotes =  0l;
	private Long previousPolledVotes = 0l;
	private String partyName;
	private Long partyId;
	private String presentYear;
	private String previousyear;
	private List<DelimitationEffectVO> delimitationEffectVO;
	public Long getPresentCount() {
		return presentCount;
	}
	public void setPresentCount(Long presentCount) {
		this.presentCount = presentCount;
	}
	public Long getPreviousCount() {
		return previousCount;
	}
	public void setPreviousCount(Long previousCount) {
		this.previousCount = previousCount;
	}
	public Double getPresentPerc() {
		return presentPerc;
	}
	public void setPresentPerc(Double presentPerc) {
		this.presentPerc = presentPerc;
	}
	public Double getPreviousPerc() {
		return previousPerc;
	}
	public void setPreviousPerc(Double previousPerc) {
		this.previousPerc = previousPerc;
	}
	public Long getPresentPolledVotes() {
		return presentPolledVotes;
	}
	public void setPresentPolledVotes(Long presentPolledVotes) {
		this.presentPolledVotes = presentPolledVotes;
	}
	public Long getPreviousPolledVotes() {
		return previousPolledVotes;
	}
	public void setPreviousPolledVotes(Long previousPolledVotes) {
		this.previousPolledVotes = previousPolledVotes;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public List<DelimitationEffectVO> getDelimitationEffectVO() {
		return delimitationEffectVO;
	}
	public void setDelimitationEffectVO(
			List<DelimitationEffectVO> delimitationEffectVO) {
		this.delimitationEffectVO = delimitationEffectVO;
	}
	public String getPresentYear() {
		return presentYear;
	}
	public void setPresentYear(String presentYear) {
		this.presentYear = presentYear;
	}
	public String getPreviousyear() {
		return previousyear;
	}
	public void setPreviousyear(String previousyear) {
		this.previousyear = previousyear;
	}
	
	
}
