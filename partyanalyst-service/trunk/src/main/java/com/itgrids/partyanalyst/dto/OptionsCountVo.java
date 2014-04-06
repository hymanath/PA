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
	//private List<String> remarks;
	public int getCount() {
		return count;
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
