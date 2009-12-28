package com.itgrids.partyanalyst.excel.booth;

import java.util.List;

public class VoterDataUploadVO {

	private Long districtId;
	private String constituencyName;
	private String mandalName;
	private String partNo;
	private List<VoterVO> voterVOs;
	
	public VoterDataUploadVO(){
		
	}
	
	public VoterDataUploadVO(Long districtId, String constituencyName, String mandalName,
			String partNo, List<VoterVO> voterVOs) {
		this.districtId = districtId;
		this.constituencyName = constituencyName;
		this.mandalName = mandalName;
		this.partNo = partNo;
		this.voterVOs = voterVOs;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public List<VoterVO> getVoterVOs() {
		return voterVOs;
	}

	public void setVoterVOs(List<VoterVO> voterVOs) {
		this.voterVOs = voterVOs;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	
}
