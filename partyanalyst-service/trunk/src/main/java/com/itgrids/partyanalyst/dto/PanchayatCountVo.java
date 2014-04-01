package com.itgrids.partyanalyst.dto;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

/**
 * @author Anilkumar Ravula Mar 30, 2014
 *
 */
/**
 * @author Administrator Mar 30, 2014
 *
 */
public class PanchayatCountVo implements Comparable<PanchayatCountVo>,Serializable {
	
	private Long panchayatId;
	
	private String panchayatName;
	
	private boolean effected;
	//private Map<Long,QuestionCountVo> QuestionsMap= new HashMap<Long, QuestionCountVo>(17,0.90f);
	
	private int effectedCount=0;
    
	private List<Long> booths;
	
	private String boothsList;
	
	

	

	

	public String getBoothsList() {
		return boothsList;
	}





	public void setBoothsList(String boothsList) {
		this.boothsList = boothsList;
	}





	public List<Long> getBooths() {
		return booths;
	}





	public void setBooths(List<Long> booths) {
		this.booths = booths;
	}





	public int getEffectedCount() {
		return effectedCount;
	}





	public void setEffectedCount(int effectedCount) {
		this.effectedCount = effectedCount;
	}





	public boolean isEffected() {
		return effected;
	}





	public void setEffected(boolean effected) {
		this.effected = effected;
	}





	
    
	
    
    
    
    
    public String getPanchayatName() {
		return panchayatName;
	}





	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}










	/*@Override
	public String toString() {
		return "PanchayatCountVo [effectedCount=" + effectedCount
				+ ", panchayatId=" + panchayatId + ", QuestionsMap="
				+ QuestionsMap + "]";
	}





	public Map<Long, QuestionCountVo> getQuestionsMap() {
		return QuestionsMap;
	}*/





	/*public void setQuestionsMap(Map<Long, QuestionCountVo> questionsMap) {
		QuestionsMap = questionsMap;
	}*/





	public Long getPanchayatId() {
		return panchayatId;
	}





	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}


	public int compareTo(PanchayatCountVo o) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
     

}
