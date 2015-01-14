package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenericVO implements Serializable, Comparable<GenericVO>{
	
	private Long id;
    private String name;
    private Double perc;
    private Long rank;
    private String desc;
    private Long count;
    private List<GenericVO> genericVOList = new ArrayList<GenericVO>();
    private BasicVO basicVO = new BasicVO();
    private String percent;
    private String mobileNo;
    
    private String caste;
    
    private int verificationProcessCount;
    private int verificationCompletionCount;
    private int verificationStartedCount;
    
    
    private List<Long> verificationProcessList = new ArrayList<Long>();
    private List<Long> verificationCompletionList = new ArrayList<Long>();
    private List<Long> verificationStartedList = new ArrayList<Long>();
    
   private String startTime;
   private String endTime;
   private String workedTime;

   private String nomineeName;
   private Long nomineeAge;
   private Long nomineeGenderId;
   private Long voterRelationType;
   private Long aadhireNo;
   private String referenceNo;
   private List<String> ageRangeList = new ArrayList<String>();
   
   
	public List<String> getAgeRangeList() {
	return ageRangeList;
	}
	public void setAgeRangeList(List<String> ageRangeList) {
		this.ageRangeList = ageRangeList;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public BasicVO getBasicVO() {
	return basicVO;
	}
	public void setBasicVO(BasicVO basicVO) {
		this.basicVO = basicVO;
	}
	public String getStartTime() {
	return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWorkedTime() {
		return workedTime;
	}
	public void setWorkedTime(String workedTime) {
		this.workedTime = workedTime;
	}
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
	
	@Override
	public boolean equals(Object obj){
		if(id==null)
			id = -1L;
		if(obj instanceof GenericVO){
			GenericVO vo = (GenericVO) obj;
			return this.id.equals(vo.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(id==null)
			id = -1L;
		return this.id.intValue();
	}
	
	//@Override
	public int compareTo(GenericVO obj) {
		
		if(obj  instanceof GenericVO){
			GenericVO vo = (GenericVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public Long getNomineeAge() {
		return nomineeAge;
	}
	public void setNomineeAge(Long nomineeAge) {
		this.nomineeAge = nomineeAge;
	}
	public Long getNomineeGenderId() {
		return nomineeGenderId;
	}
	public void setNomineeGenderId(Long nomineeGenderId) {
		this.nomineeGenderId = nomineeGenderId;
	}
	public Long getVoterRelationType() {
		return voterRelationType;
	}
	public void setVoterRelationType(Long voterRelationType) {
		this.voterRelationType = voterRelationType;
	}
	public Long getAadhireNo() {
		return aadhireNo;
	}
	public void setAadhireNo(Long aadhireNo) {
		this.aadhireNo = aadhireNo;
	}
	
	
	
	
	
}
