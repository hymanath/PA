package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreIVRVO implements Serializable, Comparable<CadreIVRVO>{
	
	private Long id;
	private String name;
	private String mobileNo;
	private String responseKey;
	private Long constituencyId;
	private String constituencyName;
	private Long total = 0l;
	private Long received = 0l;
	private Long notReceived = 0l;
	private Long notRegistered = 0l;
	
	private Long answeredCnt = 0l;
	private Long noOption = 0l;
	private Long wrongOption =0l;
	private Long userBusy =0l;
	private Long noAnswer =0l;
	private Long otherError =0l;
	private Long switchCongestion =0l;
		
	private Long callRejectedCount = 0l;
	private Long newtworkError = 0l;
	private Long unallocatedNumbers = 0l;
	private Long interworkingCount = 0l;

	private String currentStatus;
	private String strDate;
	private String endDate;
	private Long locationId;
	private String locationName;
	private Long panchayatId;
	private String panchayatName;
	private Long localbody;
	private String localbodyName;
	private List<CadreIVRVO> subList = new ArrayList<CadreIVRVO>();
	private Long count =0l;
	private Long printingCompleted = 0l;
	private Long ivrReady = 0l;
	
	private Double receivedPerc;
	private Double notReceivedPerc;
	private Double notMemberPerc;
	private Double answeredPerc;
	private Long tgCount =0l;
	private Double noOptionPerc; 
	private Double wrongOptionPerc;
	private Double userBusyPerc;
	private Double noAnswerPerc;
	private Double switchCongestionPerc;
	private Double otherErrorPerc;
	
	private Double callRejectedPerc ;
	private Double newtworkErrorPerc;
	private Double unallocatedNumbersPerc ;
	private Double interworkingCountPerc ;
	
	
	private Long totalError=0l;
	private Double errorPerc;
	
	private Long totalUnAnswered=0l;
	private Double unAnsweredPerc;
	private Double selectedOptionCntPerc;
	private Double totalErrorPerc;
	private Double totalUnAnsweredPerc;
	
	private Long totalIvrCalls;
	
	private Long answeredIvrCalls;
	
	private Long totalWards;
	private Long totalWardIvr;
	private Long totalWardAnswerdIvr;
	
	
	private List<CadreIVRVO> subListForResponces;
	
	private List<IvrOptionsVO> optionsList;
	private List<IvrOptionsVO> optionsList1;
	
	public CadreIVRVO()
	{
		
	}
	
	public Long getSwitchCongestion() {
		return switchCongestion;
	}




	public void setSwitchCongestion(Long switchCongestion) {
		this.switchCongestion = switchCongestion;
	}




	public Double getSwitchCongestionPerc() {
		return switchCongestionPerc;
	}




	public void setSwitchCongestionPerc(Double switchCongestionPerc) {
		this.switchCongestionPerc = switchCongestionPerc;
	}




	public Long getTotalError() {
		return totalError;
	}




	public void setTotalError(Long totalError) {
		this.totalError = totalError;
	}




	public Double getErrorPerc() {
		return errorPerc;
	}


	public void setErrorPerc(Double errorPerc) {
		this.errorPerc = errorPerc;
	}


	public Long getNoOption() {
		return noOption;
	}


	public void setNoOption(Long noOption) {
		this.noOption = noOption;
	}


	public Long getWrongOption() {
		return wrongOption;
	}


	public void setWrongOption(Long wrongOption) {
		this.wrongOption = wrongOption;
	}


	public Long getUserBusy() {
		return userBusy;
	}


	public void setUserBusy(Long userBusy) {
		this.userBusy = userBusy;
	}


	public Long getNoAnswer() {
		return noAnswer;
	}


	public void setNoAnswer(Long noAnswer) {
		this.noAnswer = noAnswer;
	}


	
	public Long getOtherError() {
		return otherError;
	}




	public void setOtherError(Long otherError) {
		this.otherError = otherError;
	}




	public Double getOtherErrorPerc() {
		return otherErrorPerc;
	}




	public void setOtherErrorPerc(Double otherErrorPerc) {
		this.otherErrorPerc = otherErrorPerc;
	}




	public Double getNoOptionPerc() {
		return noOptionPerc;
	}


	public void setNoOptionPerc(Double noOptionPerc) {
		this.noOptionPerc = noOptionPerc;
	}


	public Double getWrongOptionPerc() {
		return wrongOptionPerc;
	}


	public void setWrongOptionPerc(Double wrongOptionPerc) {
		this.wrongOptionPerc = wrongOptionPerc;
	}


	public Double getUserBusyPerc() {
		return userBusyPerc;
	}


	public void setUserBusyPerc(Double userBusyPerc) {
		this.userBusyPerc = userBusyPerc;
	}


	public Double getNoAnswerPerc() {
		return noAnswerPerc;
	}


	public void setNoAnswerPerc(Double noAnswerPerc) {
		this.noAnswerPerc = noAnswerPerc;
	}

	public Long getTgCount() {
		return tgCount;
	}


	public void setTgCount(Long tgCount) {
		this.tgCount = tgCount;
	}


	public Long getAnsweredCnt() {
		return answeredCnt;
	}

	public void setAnsweredCnt(Long answeredCnt) {
		this.answeredCnt = answeredCnt;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Double getAnsweredPerc() {
		return answeredPerc;
	}

	public void setAnsweredPerc(Double answeredPerc) {
		this.answeredPerc = answeredPerc;
	}

	public Double getReceivedPerc() {
		return receivedPerc;
	}

   public void setReceivedPerc(Double receivedPerc) {
		this.receivedPerc = receivedPerc;
	}

   public Double getNotReceivedPerc() {
		return notReceivedPerc;
	}

   public void setNotReceivedPerc(Double notReceivedPerc) {
		this.notReceivedPerc = notReceivedPerc;
	}

   public Double getNotMemberPerc() {
		return notMemberPerc;
	}

   public void setNotMemberPerc(Double notMemberPerc) {
		this.notMemberPerc = notMemberPerc;
	}
  
	
	public Long getPrintingCompleted() {
		return printingCompleted;
	}
	public void setPrintingCompleted(Long printingCompleted) {
		this.printingCompleted = printingCompleted;
	}
	public Long getIvrReady() {
		return ivrReady;
	}
	public void setIvrReady(Long ivrReady) {
		this.ivrReady = ivrReady;
	}
	
	public Long getLocalbody() {
		return localbody;
	}
	public void setLocalbody(Long localbody) {
		this.localbody = localbody;
	}
	public String getLocalbodyName() {
		return localbodyName;
	}
	public void setLocalbodyName(String localbodyName) {
		this.localbodyName = localbodyName;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
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
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<CadreIVRVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreIVRVO> subList) {
		this.subList = subList;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getResponseKey() {
		return responseKey;
	}
	public void setResponseKey(String responseKey) {
		this.responseKey = responseKey;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getReceived() {
		return received;
	}
	public void setReceived(Long received) {
		this.received = received;
	}
	public Long getNotReceived() {
		return notReceived;
	}
	public void setNotReceived(Long notReceived) {
		this.notReceived = notReceived;
	}
	public Long getNotRegistered() {
		return notRegistered;
	}
	public void setNotRegistered(Long notRegistered) {
		this.notRegistered = notRegistered;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public int compareTo(CadreIVRVO o) {
		CadreIVRVO obj = null;
		if(obj instanceof CadreIVRVO){
			CadreIVRVO vo = (CadreIVRVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}

	public Long getCallRejectedCount() {
		return callRejectedCount;
	}

	public void setCallRejectedCount(Long callRejectedCount) {
		this.callRejectedCount = callRejectedCount;
	}

	public Long getNewtworkError() {
		return newtworkError;
	}

	public void setNewtworkError(Long newtworkError) {
		this.newtworkError = newtworkError;
	}

	public Long getUnallocatedNumbers() {
		return unallocatedNumbers;
	}

	public void setUnallocatedNumbers(Long unallocatedNumbers) {
		this.unallocatedNumbers = unallocatedNumbers;
	}

	public Long getInterworkingCount() {
		return interworkingCount;
	}

	public void setInterworkingCount(Long interworkingCount) {
		this.interworkingCount = interworkingCount;
	}

	public Double getCallRejectedPerc() {
		return callRejectedPerc;
	}

	public void setCallRejectedPerc(Double callRejectedPerc) {
		this.callRejectedPerc = callRejectedPerc;
	}

	public Double getNewtworkErrorPerc() {
		return newtworkErrorPerc;
	}

	public void setNewtworkErrorPerc(Double newtworkErrorPerc) {
		this.newtworkErrorPerc = newtworkErrorPerc;
	}

	public Double getUnallocatedNumbersPerc() {
		return unallocatedNumbersPerc;
	}

	public void setUnallocatedNumbersPerc(Double unallocatedNumbersPerc) {
		this.unallocatedNumbersPerc = unallocatedNumbersPerc;
	}

	public Double getInterworkingCountPerc() {
		return interworkingCountPerc;
	}

	public void setInterworkingCountPerc(Double interworkingCountPerc) {
		this.interworkingCountPerc = interworkingCountPerc;
	}

	public Long getTotalUnAnswered() {
		return totalUnAnswered;
	}

	public void setTotalUnAnswered(Long totalUnAnswered) {
		this.totalUnAnswered = totalUnAnswered;
	}

	public Double getUnAnsweredPerc() {
		return unAnsweredPerc;
	}

	public void setUnAnsweredPerc(Double unAnsweredPerc) {
		this.unAnsweredPerc = unAnsweredPerc;
	}

	public Double getSelectedOptionCntPerc() {
		return selectedOptionCntPerc;
	}

	public void setSelectedOptionCntPerc(Double selectedOptionCntPerc) {
		this.selectedOptionCntPerc = selectedOptionCntPerc;
	}

	public Double getTotalErrorPerc() {
		return totalErrorPerc;
	}

	public void setTotalErrorPerc(Double totalErrorPerc) {
		this.totalErrorPerc = totalErrorPerc;
	}

	public Double getTotalUnAnsweredPerc() {
		return totalUnAnsweredPerc;
	}

	public void setTotalUnAnsweredPerc(Double totalUnAnsweredPerc) {
		this.totalUnAnsweredPerc = totalUnAnsweredPerc;
	}

	public List<CadreIVRVO> getSubListForResponces() {
		return subListForResponces;
	}

	public void setSubListForResponces(List<CadreIVRVO> subListForResponces) {
		this.subListForResponces = subListForResponces;
	}

	public List<IvrOptionsVO> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<IvrOptionsVO> optionsList) {
		this.optionsList = optionsList;
	}

	public Long getTotalIvrCalls() {
		return totalIvrCalls;
	}

	public void setTotalIvrCalls(Long totalIvrCalls) {
		this.totalIvrCalls = totalIvrCalls;
	}

	public Long getAnsweredIvrCalls() {
		return answeredIvrCalls;
	}

	public void setAnsweredIvrCalls(Long answeredIvrCalls) {
		this.answeredIvrCalls = answeredIvrCalls;
	}

	public Long getTotalWards() {
		return totalWards;
	}

	public void setTotalWards(Long totalWards) {
		this.totalWards = totalWards;
	}

	public Long getTotalWardIvr() {
		return totalWardIvr;
	}

	public void setTotalWardIvr(Long totalWardIvr) {
		this.totalWardIvr = totalWardIvr;
	}

	public Long getTotalWardAnswerdIvr() {
		return totalWardAnswerdIvr;
	}

	public void setTotalWardAnswerdIvr(Long totalWardAnswerdIvr) {
		this.totalWardAnswerdIvr = totalWardAnswerdIvr;
	}

	public List<IvrOptionsVO> getOptionsList1() {
		return optionsList1;
	}

	public void setOptionsList1(List<IvrOptionsVO> optionsList1) {
		this.optionsList1 = optionsList1;
	}

	

	
}
