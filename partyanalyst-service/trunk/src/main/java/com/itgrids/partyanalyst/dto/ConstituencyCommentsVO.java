/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 15,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ConstituencyCommentsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3166856300837822581L;
	
	private Long constituencyId;
	private String constituency;
	private String commentDesc;
	private String commentedBy;
	private String commentedOn;
	
	//getters and setters
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getCommentDesc() {
		return commentDesc;
	}
	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
	}
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	public String getCommentedOn() {
		return commentedOn;
	}
	public void setCommentedOn(String commentedOn) {
		this.commentedOn = commentedOn;
	}

}
