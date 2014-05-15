package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyWiseMarginCountsVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long partyId;
	private String partyName;
	private Long count;
	private String margin;
	private Long marginMin;
	private Long marginMax;
	
	private List<PartyWiseMarginCountsVO> marginsVO;
	private List<PartyWiseMarginCountsVO> partiesList;
	
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public Long getMarginMin() {
		return marginMin;
	}

	public void setMarginMin(Long marginMin) {
		this.marginMin = marginMin;
	}

	public Long getMarginMax() {
		return marginMax;
	}

	public void setMarginMax(Long marginMax) {
		this.marginMax = marginMax;
	}

	public List<PartyWiseMarginCountsVO> getMarginsVO() {
		return marginsVO;
	}

	public void setMarginsVO(List<PartyWiseMarginCountsVO> marginsVO) {
		this.marginsVO = marginsVO;
	}

	public List<PartyWiseMarginCountsVO> getPartiesList() {
		return partiesList;
	}

	public void setPartiesList(List<PartyWiseMarginCountsVO> partiesList) {
		this.partiesList = partiesList;
	}
	
	
	
	
}
