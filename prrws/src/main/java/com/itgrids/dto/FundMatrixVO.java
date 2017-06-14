package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FundMatrixVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String range;
	private List<RangeVO> rangeList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public List<RangeVO> getRangeList() {
		if(rangeList == null){
			rangeList = new ArrayList<RangeVO>();
		}
		return rangeList;
	}
	
}
