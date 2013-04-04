package com.itgrids.partyanalyst.excel.booth;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

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
	private String locationType;
	private List<SelectOptionVO> selectOptionVOsList;
	
	private Long locationId;
	private Long locationValue;
	private Long constituencyId;
	private String isForGender;
	private String status;
	private String gender;
	private Long ageRangeId;
	
	private Long movedCount = 0L;
	private Long relocatedCount = 0L;
	private Long maleVotersMoved = 0L;
	private Long femaleVotersMoved = 0L;
	private Long maleVotersRelocated = 0L;
	private Long femaleVotersRelocated = 0L;
	

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getIsForGender() {
		return isForGender;
	}

	public void setIsForGender(String isForGender) {
		this.isForGender = isForGender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAgeRangeId() {
		return ageRangeId;
	}

	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}

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

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public List<SelectOptionVO> getSelectOptionVOsList() {
		return selectOptionVOsList;
	}

	public void setSelectOptionVOsList(List<SelectOptionVO> selectOptionVOsList) {
		this.selectOptionVOsList = selectOptionVOsList;
	}

	public Long getMovedCount() {
		return movedCount;
	}

	public void setMovedCount(Long movedCount) {
		this.movedCount = movedCount;
	}

	public Long getRelocatedCount() {
		return relocatedCount;
	}

	public void setRelocatedCount(Long relocatedCount) {
		this.relocatedCount = relocatedCount;
	}

	public Long getMaleVotersMoved() {
		return maleVotersMoved;
	}

	public void setMaleVotersMoved(Long maleVotersMoved) {
		this.maleVotersMoved = maleVotersMoved;
	}

	public Long getFemaleVotersMoved() {
		return femaleVotersMoved;
	}

	public void setFemaleVotersMoved(Long femaleVotersMoved) {
		this.femaleVotersMoved = femaleVotersMoved;
	}

	public Long getMaleVotersRelocated() {
		return maleVotersRelocated;
	}

	public void setMaleVotersRelocated(Long maleVotersRelocated) {
		this.maleVotersRelocated = maleVotersRelocated;
	}

	public Long getFemaleVotersRelocated() {
		return femaleVotersRelocated;
	}

	public void setFemaleVotersRelocated(Long femaleVotersRelocated) {
		this.femaleVotersRelocated = femaleVotersRelocated;
	}
	
	
	
}
