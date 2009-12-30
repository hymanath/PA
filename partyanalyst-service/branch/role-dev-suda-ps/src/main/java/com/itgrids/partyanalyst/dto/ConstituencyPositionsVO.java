/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */

package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyPositionsVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private PositionType type;
	private int positionsWon;
	private List<ConstituencyPositionDetailVO> constituencyPositionDetails;
	private String description;
	
	public ConstituencyPositionsVO(){}
	
	public ConstituencyPositionsVO(PositionType type,
			int positionsWon, List<ConstituencyPositionDetailVO> details, String desc) {
		this.type = type;
		this.positionsWon = positionsWon;
		this.constituencyPositionDetails = details;
		this.description = desc;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PositionType getType() {
		return type;
	}
	public void setType(PositionType type) {
		this.type = type;
	}
	public int getPositionsWon() {
		return positionsWon;
	}
	public void setPositionsWon(int positionsWon) {
		this.positionsWon = positionsWon;
	}
	public List<ConstituencyPositionDetailVO> getConstituencyPositionDetails() {
		return constituencyPositionDetails;
	}
	public void setConstituencyPositionDetails(
			List<ConstituencyPositionDetailVO> constituencyPositionDetails) {
		this.constituencyPositionDetails = constituencyPositionDetails;
	}
}
