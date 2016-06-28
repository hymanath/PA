package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class DebatePartyWiseCountVO implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String partyName;
	private Long totalDebates;
	private Long charId;
	private String charsName;
	private Double debateScale;
	private String perc;
	
	private List<DebatePartyWiseCountVO> subList;
	
	private DebateRankingVO rankingVO;
	
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getTotalDebates() {
		return totalDebates;
	}
	public void setTotalDebates(Long totalDebates) {
		this.totalDebates = totalDebates;
	}
	public Double getDebateScale() {
		return debateScale;
	}
	public void setDebateScale(Double debateScale) {
		this.debateScale = debateScale;
	}
	public Long getCharId() {
		return charId;
	}
	public void setCharId(Long charId) {
		this.charId = charId;
	}
	public String getCharsName() {
		return charsName;
	}
	public void setCharsName(String charsName) {
		this.charsName = charsName;
	}
	public List<DebatePartyWiseCountVO> getSubList() {
		return subList;
	}
	public void setSubList(List<DebatePartyWiseCountVO> subList) {
		this.subList = subList;
	}
	public String getPerc() {
		return perc;
	}
	public void setPerc(String perc) {
		this.perc = perc;
	}
	public DebateRankingVO getRankingVO() {
		return rankingVO;
	}
	public void setRankingVO(DebateRankingVO rankingVO) {
		this.rankingVO = rankingVO;
	}
	
	
	
	
}
