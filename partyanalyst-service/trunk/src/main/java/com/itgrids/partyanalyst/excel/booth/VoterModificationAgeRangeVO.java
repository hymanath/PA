package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;

public class VoterModificationAgeRangeVO implements Serializable{

	private static final long serialVersionUID = -2052313326620421530L;
	
	private String range;
	private Long addedCount = 0L;
	private Long deletedCount = 0L;
	
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public Long getAddedCount() {
		return addedCount;
	}
	public void setAddedCount(Long addedCount) {
		this.addedCount = addedCount;
	}
	public Long getDeletedCount() {
		return deletedCount;
	}
	public void setDeletedCount(Long deletedCount) {
		this.deletedCount = deletedCount;
	}

}
