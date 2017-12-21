package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectOptionVO implements Serializable, Comparable<SelectOptionVO> {
	
	private static long serialVersionUID = 1L;
	private String name;
	private Long id;
	private String url;
	private String type;
	private String value;
	private String location;
	private String villageCovered;
	private List<SelectOptionVO> selectOptionsList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> selectOptionsList1 = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> selectOptionsList2 = new ArrayList<SelectOptionVO>(0);
	private boolean hampletPresent;
	private String partno;
	private List<Long> locationValuesList;
	private Long populateId;
	private Long orderId;
	private Long parentUserId;
	private Long mainAccountId;
	private boolean flag;
	private Long totalCount;
	private Long validCount;
	private List<ConstituencyElectionResultsVO> assemblyList;
	private List<ConstituencyElectionResultsVO> parliamentList;
	private List<ConstituencyElectionResultsVO> mptcZptcList ;
	private SelectOptionVO panchatsList;
	private Double perc;
	private List<LocationWiseBoothDetailsVO> result = new ArrayList<LocationWiseBoothDetailsVO>(0);
	private String percentage;
	private String latitude;
	private String longititude;
	private String mandalName;
	private String panchayatName;
	private Long count=0l;
	private List<Long> totalCountList;
	private Long constituencyId;
	private String status;
	private String committeeMemberStatus;
	private String state;
	private Long boothNumber;
	private String boothName;
	private Long mobileNumber;
	private Long memberShipNo;
	private String firstName;
	private String roleName;
	private Long roleMappingId;
	private Long inchargeId;
	private Long serialNo;
	private String originalLocation;
	private String votingPercentage;
	private List<SelectOptionVO> subList = new ArrayList<SelectOptionVO>(0);
	private Long locationId;
	private Double scalePerc;
	public String getVotingPercentage() {
		return votingPercentage;
	}

	public void setVotingPercentage(String votingPercentage) {
		this.votingPercentage = votingPercentage;
	}

	public List<SelectOptionVO> getSubList() {
		return subList;
	}

	public void setSubList(List<SelectOptionVO> subList) {
		this.subList = subList;
	}

	public String getOriginalLocation() {
		return originalLocation;
	}

	public void setOriginalLocation(String originalLocation) {
		this.originalLocation = originalLocation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public Long getBoothNumber() {
		return boothNumber;
	}

	public void setBoothNumber(Long boothNumber) {
		this.boothNumber = boothNumber;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getMemberShipNo() {
		return memberShipNo;
	}

	public void setMemberShipNo(Long memberShipNo) {
		this.memberShipNo = memberShipNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<LocationWiseBoothDetailsVO> getResult() {
		return result;
	}

	public void setResult(List<LocationWiseBoothDetailsVO> result) {
		this.result = result;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public List<SelectOptionVO> getSelectOptionsList2() {
		return selectOptionsList2;
	}

	public void setSelectOptionsList2(List<SelectOptionVO> selectOptionsList2) {
		this.selectOptionsList2 = selectOptionsList2;
	}

	public SelectOptionVO getPanchatsList() {
		return panchatsList;
	}

	public void setPanchatsList(SelectOptionVO panchatsList) {
		this.panchatsList = panchatsList;
	}

	public List<ConstituencyElectionResultsVO> getMptcZptcList() {
		return mptcZptcList;
	}

	public void setMptcZptcList(List<ConstituencyElectionResultsVO> mptcZptcList) {
		this.mptcZptcList = mptcZptcList;
	}

	public List<ConstituencyElectionResultsVO> getAssemblyList() {
		return assemblyList;
	}

	public void setAssemblyList(List<ConstituencyElectionResultsVO> assemblyList) {
		this.assemblyList = assemblyList;
	}

	public List<ConstituencyElectionResultsVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<ConstituencyElectionResultsVO> parliamentList) {
		this.parliamentList = parliamentList;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getValidCount() {
		return validCount;
	}

	public void setValidCount(Long validCount) {
		this.validCount = validCount;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getPopulateId() {
		return populateId;
	}

	public void setPopulateId(Long populateId) {
		this.populateId = populateId;
	}

	public String getPartno() {
		return partno;
	}

	public void setPartno(String partno) {
		this.partno = partno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SelectOptionVO(){}
	
	public SelectOptionVO(Long id, String name)
	{
		this.id = id;
		this.name = name;	
	}
	
	public SelectOptionVO(Long id, String name,String location,String villageCovered)
	{
		this.id = id;
		this.name = name;
		this.location = location;	
		this.villageCovered = villageCovered;	
	}
	
	public SelectOptionVO(Long id, String name,String url){
		this.id = id;
		this.name = name;
		this.url = url;
	}
	
	public SelectOptionVO(List<Long> locationValuesList, String type)
	{
		this.locationValuesList = locationValuesList;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<SelectOptionVO> getSelectOptionsList1() {
		return selectOptionsList1;
	}

	public void setSelectOptionsList1(List<SelectOptionVO> selectOptionsList1) {
		this.selectOptionsList1 = selectOptionsList1;
	}

	@Override
	public boolean equals(Object obj){
		if(id==null)
			id = -1L;
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
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

	public int compareTo(SelectOptionVO obj) {
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}

	public List<SelectOptionVO> getSelectOptionsList() {
		return selectOptionsList;
	}

	public void setSelectOptionsList(List<SelectOptionVO> selectOptionsList) {
		this.selectOptionsList = selectOptionsList;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVillageCovered() {
		return villageCovered;
	}

	public void setVillageCovered(String villageCovered) {
		this.villageCovered = villageCovered;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public boolean isHampletPresent() {
		return hampletPresent;
	}

	public void setHampletPresent(boolean hampletPresent) {
		this.hampletPresent = hampletPresent;
	}

	public List<Long> getLocationValuesList() {
		return locationValuesList;
	}

	public void setLocationValuesList(List<Long> locationValuesList) {
		this.locationValuesList = locationValuesList;
	}

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Long getMainAccountId() {
		return mainAccountId;
	}

	public void setMainAccountId(Long mainAccountId) {
		this.mainAccountId = mainAccountId;
	}

	public Double getPerc() {
		return perc;
	}

	public void setPerc(Double perc) {
		this.perc = perc;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongititude() {
		return longititude;
	}

	public void setLongititude(String longititude) {
		this.longititude = longititude;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<Long> getTotalCountList() {
		return totalCountList;
	}

	public void setTotalCountList(List<Long> totalCountList) {
		this.totalCountList = totalCountList;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommitteeMemberStatus() {
		return committeeMemberStatus;
	}

	public void setCommitteeMemberStatus(String committeeMemberStatus) {
		this.committeeMemberStatus = committeeMemberStatus;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleMappingId() {
		return roleMappingId;
	}

	public void setRoleMappingId(Long roleMappingId) {
		this.roleMappingId = roleMappingId;
	}

	public Long getInchargeId() {
		return inchargeId;
	}

	public void setInchargeId(Long inchargeId) {
		this.inchargeId = inchargeId;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Double getScalePerc() {
		return scalePerc;
	}

	public void setScalePerc(Double scalePerc) {
		this.scalePerc = scalePerc;
	}
	
	
	
}
