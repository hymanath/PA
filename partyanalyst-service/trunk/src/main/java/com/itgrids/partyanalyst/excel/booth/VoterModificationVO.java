package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;
import java.util.List;

public class VoterModificationVO implements Serializable{

	private static final long serialVersionUID = 8698571252742085313L;
	
	private Long previousPublicationId;
	private Long presentPublicationId;
	private Long addedCount = 0L;
	private Long deletedCount = 0L;
	private String name;
	private Long id;
	
	private Long maleVotersAdded = 0L;
	private Long femaleVotersAdded = 0L;
	private Long maleVotersDeleted = 0L;
	private Long femaleVotersDeleted = 0L;
	
	

	public Long getMaleVotersDeleted() {
		return maleVotersDeleted;
	}

	public void setMaleVotersDeleted(Long maleVotersDeleted) {
		this.maleVotersDeleted = maleVotersDeleted;
	}

	public Long getFemaleVotersDeleted() {
		return femaleVotersDeleted;
	}

	public void setFemaleVotersDeleted(Long femaleVotersDeleted) {
		this.femaleVotersDeleted = femaleVotersDeleted;
	}

	public Long getMaleVotersAdded() {
		return maleVotersAdded;
	}

	public void setMaleVotersAdded(Long maleVotersAdded) {
		this.maleVotersAdded = maleVotersAdded;
	}

	public Long getFemaleVotersAdded() {
		return femaleVotersAdded;
	}

	public void setFemaleVotersAdded(Long femaleVotersAdded) {
		this.femaleVotersAdded = femaleVotersAdded;
	}

	private List<VoterModificationVO> modifiedVotersList;
	private List<VoterModificationVO> modifiedLocalBodyVotersList;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<VoterModificationVO> getModifiedVotersList() {
		return modifiedVotersList;
	}

	public void setModifiedVotersList(List<VoterModificationVO> modifiedVotersList) {
		this.modifiedVotersList = modifiedVotersList;
	}

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
	

	public List<VoterModificationVO> getModifiedLocalBodyVotersList() {
		return modifiedLocalBodyVotersList;
	}

	public void setModifiedLocalBodyVotersList(
			List<VoterModificationVO> modifiedLocalBodyVotersList) {
		this.modifiedLocalBodyVotersList = modifiedLocalBodyVotersList;
	}
}
