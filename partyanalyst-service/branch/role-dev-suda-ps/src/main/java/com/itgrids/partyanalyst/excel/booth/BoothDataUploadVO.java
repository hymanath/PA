package com.itgrids.partyanalyst.excel.booth;

import java.util.List;

public class BoothDataUploadVO {

	private String constituencyName;
	private Long districtId;
	private List<BoothInfo> booths;
	
	public BoothDataUploadVO(){
		
	}
	
	public BoothDataUploadVO(String constituencyName, Long districtId,
			List<BoothInfo> booths) {
		this.constituencyName = constituencyName;
		this.districtId = districtId;
		this.booths = booths;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public List<BoothInfo> getBooths() {
		return booths;
	}

	public void setBooths(List<BoothInfo> booths) {
		this.booths = booths;
	}
	
	
}
