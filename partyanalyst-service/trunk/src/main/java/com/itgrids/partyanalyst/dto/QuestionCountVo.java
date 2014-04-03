package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator Mar 30, 2014
 *
 */
public class QuestionCountVo implements Comparable<QuestionCountVo> ,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long questionId;
	private int totalCount=0;
	private Long optionTypeId;
	
    
   
	private int differenceCount;
    
	 public int getDifferenceCount() {
			return differenceCount;
		}
		public void setDifferenceCount(int differenceCount) {
			this.differenceCount = differenceCount;
		}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	//private Map<Long,List<OptionsCountVo>> optionMaps= new HashMap<Long,List<OptionsCountVo>>();
	private Map<Long,OptionsCountVo> optionMap= new HashMap<Long,OptionsCountVo>(8);
	
	
	public Map<Long, OptionsCountVo> getOptionMap() {
		return optionMap;
	}
	public void setOptionMap(Map<Long, OptionsCountVo> optionMap) {
		this.optionMap = optionMap;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	/*public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}*/
	/*public Map<Long, List<OptionsCountVo>> getOptionMaps() {
		return optionMaps;
	}
	public void setOptionMaps(Map<Long, List<OptionsCountVo>> optionMaps) {
		this.optionMaps = optionMaps;
	}*/
	@Override
	public int compareTo(QuestionCountVo o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

}
