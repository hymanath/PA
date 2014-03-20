package com.itgrids.partyanalyst.dto;

import java.util.List;

import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;


public class PDFHeadingAndReturnVO {
	private String mainHeading;
	private String subHeading;
	
	private List<VoterAgeRangeVO>  voterInfoByPublicationList;
	private List<VoterModificationAgeRangeVO> ageRangeWiseAddedDeletedList;
	private List<VoterModificationGenderInfoVO> genderWiseVoterModificationInfoForEachPublicationList;
	public String getMainHeading() {
		return mainHeading;
	}
	public void setMainHeading(String mainHeading) {
		this.mainHeading = mainHeading;
	}
	public String getSubHeading() {
		return subHeading;
	}
	public void setSubHeading(String subHeading) {
		this.subHeading = subHeading;
	}
	public List<VoterAgeRangeVO> getVoterInfoByPublicationList() {
		return voterInfoByPublicationList;
	}
	public void setVoterInfoByPublicationList(
			List<VoterAgeRangeVO> voterInfoByPublicationList) {
		this.voterInfoByPublicationList = voterInfoByPublicationList;
	}
	public List<VoterModificationAgeRangeVO> getAgeRangeWiseAddedDeletedList() {
		return ageRangeWiseAddedDeletedList;
	}
	public void setAgeRangeWiseAddedDeletedList(
			List<VoterModificationAgeRangeVO> ageRangeWiseAddedDeletedList) {
		this.ageRangeWiseAddedDeletedList = ageRangeWiseAddedDeletedList;
	}
	public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationInfoForEachPublicationList() {
		return genderWiseVoterModificationInfoForEachPublicationList;
	}
	public void setGenderWiseVoterModificationInfoForEachPublicationList(
			List<VoterModificationGenderInfoVO> genderWiseVoterModificationInfoForEachPublicationList) {
		this.genderWiseVoterModificationInfoForEachPublicationList = genderWiseVoterModificationInfoForEachPublicationList;
	}
	
	
}
