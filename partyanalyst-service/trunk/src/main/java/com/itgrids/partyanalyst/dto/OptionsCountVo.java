package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionsCountVo implements Comparable<OptionsCountVo>,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long OptionId;
	private Long optionTypeId;
	
	private int prority=0 ;
	private Map<Integer,Integer> priorityCount = new HashMap<Integer,Integer>(4);
	private List<String> textBoxes =new ArrayList<String>(0);
	private int count=0;
	private String optionName;
	private String constincyName;
	private Long constincyId;
	private List<OptionsCountVo> optionsList = new ArrayList<OptionsCountVo>();
	private Long tehsilId;
	private String tehsilName;
	private Long districtId;
	private String districtName;
	private Long panchayatId;
	private String panchayatName;
	private Long localElectionBodyId;
	private String localElectionName;
	private String optnCommnt;
	//private List<String> remarks;
	
	
	
	public int getCount() {
		return count;
	}

	public String getOptnCommnt() {
		return optnCommnt;
	}

	public void setOptnCommnt(String optnCommnt) {
		this.optnCommnt = optnCommnt;
	}

	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}

	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}

	public String getLocalElectionName() {
		return localElectionName;
	}

	public void setLocalElectionName(String localElectionName) {
		this.localElectionName = localElectionName;
	}

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public List<OptionsCountVo> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<OptionsCountVo> optionsList) {
		this.optionsList = optionsList;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getConstincyName() {
		return constincyName;
	}

	public void setConstincyName(String constincyName) {
		this.constincyName = constincyName;
	}

	public Long getConstincyId() {
		return constincyId;
	}

	public void setConstincyId(Long constincyId) {
		this.constincyId = constincyId;
	}

	public List<String> getTextBoxes() {
		return textBoxes;
	}

	public void setTextBoxes(List<String> textBoxes) {
		this.textBoxes = textBoxes;
	}



	public Map<Integer, Integer> getPriorityCount() {
		return priorityCount;
	}



	public void setPriorityCount(Map<Integer, Integer> priorityCount) {
		this.priorityCount = priorityCount;
	}



	public void setCount(int count) {
		this.count = count;
	}



	private String remarks;
	

	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public Long getOptionId() {
		return OptionId;
	}



	public void setOptionId(Long optionId) {
		OptionId = optionId;
	}



	public Long getOptionTypeId() {
		return optionTypeId;
	}



	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}



	



	/*public Long getCount() {
		return count;
	}



	public void setCount(Long count) {
		this.count = count;
	}*/



	public int getPrority() {
		return prority;
	}



	public void setPrority(int prority) {
		this.prority = prority;
	}




	public int compareTo(OptionsCountVo o) {
		
		return 0;
	} 
	
	
	
}
