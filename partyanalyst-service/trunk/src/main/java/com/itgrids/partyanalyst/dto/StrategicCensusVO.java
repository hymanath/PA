
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class StrategicCensusVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String stateName;
	private String districtName;
	private String constituencyName;
	private Integer count;
	private String message;
	private String conclusion;
	private String Heading;
	private List<StrategicCensusStatusVO> strategicCensusStatusVOList = null ;
	
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	public String getHeading() {
		return Heading;
	}
	public void setHeading(String heading) {
		Heading = heading;
	}
	public List<StrategicCensusStatusVO> getStrategicCensusStatusVOList() {
		return strategicCensusStatusVOList;
	}
	public void setStrategicCensusStatusVOList(
			List<StrategicCensusStatusVO> strategicCensusStatusVOList) {
		this.strategicCensusStatusVOList = strategicCensusStatusVOList;
	} 
	
	
	
}
