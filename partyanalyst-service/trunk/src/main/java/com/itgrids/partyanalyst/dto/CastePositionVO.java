package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CastePositionVO {
	
	private Long casteId;
	private String casteName;
	private Long positionId;
	private String positionName;
	private Long count = 0l;
	private List<CastePositionVO> casteList = new ArrayList<CastePositionVO>(0);
	
	
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<CastePositionVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<CastePositionVO> casteList) {
		this.casteList = casteList;
	}
	

}
