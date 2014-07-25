package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class GenericVO {
	
	private Long id;
    private String name;
    private Double perc;
    private Long rank;
    private String desc;
    private Long count;
    private List<GenericVO> genericVOList = new ArrayList<GenericVO>();
    private String percent;
    private String mobileNo;
    
    private int verificationProcessCount;
    private int verificationCompletionCount;
    private int verificationStartedCount;
    
    
    private List<Long> verificationProcessList = new ArrayList<Long>();
    private List<Long> verificationCompletionList = new ArrayList<Long>();
    private List<Long> verificationStartedList = new ArrayList<Long>();
    
   
	public List<Long> getVerificationProcessList() {
		return verificationProcessList;
	}
	public void setVerificationProcessList(List<Long> verificationProcessList) {
		this.verificationProcessList = verificationProcessList;
	}
	public List<Long> getVerificationCompletionList() {
		return verificationCompletionList;
	}
	public void setVerificationCompletionList(List<Long> verificationCompletionList) {
		this.verificationCompletionList = verificationCompletionList;
	}
	public List<Long> getVerificationStartedList() {
		return verificationStartedList;
	}
	public void setVerificationStartedList(List<Long> verificationStartedList) {
		this.verificationStartedList = verificationStartedList;
	}
	public int getVerificationStartedCount() {
		return verificationStartedCount;
	}
	public void setVerificationStartedCount(int verificationStartedCount) {
		this.verificationStartedCount = verificationStartedCount;
	}
	public int getVerificationProcessCount() {
		return verificationProcessCount;
	}
	public void setVerificationProcessCount(int verificationProcessCount) {
		this.verificationProcessCount = verificationProcessCount;
	}
	public int getVerificationCompletionCount() {
		return verificationCompletionCount;
	}
	public void setVerificationCompletionCount(int verificationCompletionCount) {
		this.verificationCompletionCount = verificationCompletionCount;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
      
    public GenericVO()
    {
    	
    }
    public GenericVO(Long id,String name)
    {
    	this.id = id;
    	this.name = name;
    	
    	
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPerc() {
		return perc;
	}
	public void setPerc(Double perc) {
		this.perc = perc;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}
	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}
	
	
	
	
	
}
