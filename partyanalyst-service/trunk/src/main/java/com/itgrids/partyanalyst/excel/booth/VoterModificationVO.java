package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;

public class VoterModificationVO implements Serializable{

	private static final long serialVersionUID = 8698571252742085313L;
	
	private Long previousPublicationId;
	private Long presentPublicationId;
	private Long addedCount;
	private Long deletedCount;
	
	public Long getPreviousPublicationId() {
		return previousPublicationId;
	}
	
	public void setPreviousPublicationId(Long previousPublicationId) {
		this.previousPublicationId = previousPublicationId;
	}
	
	public Long getPresentPublicationId() {
		return presentPublicationId;
	}
	public void setPresentPublicationId(Long presentPublicationId) {
		this.presentPublicationId = presentPublicationId;
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
