package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VoterHouseInfoVO implements Comparable<VoterHouseInfoVO>{

	private String houseNo;
	private int numberOfPeople;
	private String cast;
	private String elder;
	private String younger;
	private Long totalHousesCount; 
	private Long boothId;
	private Long sNo;
	private String name;
	private String gender;
	private Long age;
	private String gaurdian;
	private String relationship;
	private String castCategory;
	private Long voterId;
	private Long partyId;
	private Long userVoterDetailsId;
	private Long userId;
	private String boothName;
	private String villiageCovered;
	private String panchayatName;
	private List<SelectOptionVO> casteGroupNameList;
	private Long userCategoryValueId;
	private Long userCategoryValuesId1;
	private String userCategoryValueName;
	private String setValue;
	private Long voterCategoryValuesId = 0l;
	private String voterCategoryValuesName;
	private Long categoryValuesId;
	//private String categoryValue;
	private List<SelectOptionVO> category;
	private List categoryValues;
	private String youngerGender;
	private Long youngerAge;
	private String elderGender;
	private Long elderAge;
	private String voterGroupName;
	private List<VoterHouseInfoVO> categoriesList;
	private List<AddressVO> categories;
	private List<SelectOptionVO> parties;
	private Long casteStateId;
	private String voterIdCardNo;	
	private List<VoterHouseInfoVO> votersList;
	private List<VoterHouseInfoVO> boothsList;
	private List<VoterHouseInfoVO> familiesList;
	private Long publicationId;
	private Long toAge;
	private String mobileNo;
	private int startIndex;
	private int maxIndex;
	private String sortBy;
	private String sortByColum;
	private boolean partyPresent;
	private boolean castPresent;
	private boolean all;
	private boolean categoriesPresent;
	private List<Long> ids;
	private String party;
	private String[] sortIds;
	private boolean sortReq;
	private String sortEle;
	private String partyName;
	private String queryType;
	private Long fromSno;
	private Long toSno;
	private Boolean isInfluencePerson;
	private Boolean isCadrePerson;
	private Boolean isPoliticion;
	private Long subLocalityId;
	private Long count;
	private List<SelectOptionVO> userCategoriesList;
	private List<SelectOptionVO> subLocalities;	
	private String partNo;
	private String selType = "";
	private Long selTypeId;
	private boolean mobileNoPresent;
	private boolean voterGroupPresent;
	private String voterGroup;
	
	private List<String> influenceNames;
	private List<String>  cadreNames;
	private List<String>  politicianNames;
	private String influencePartyName;
	private List<SelectOptionVO> customGroups;
	private boolean  groupPresent;	
	
	private Long customGroupId;	
	
	private String groupType;
	private boolean mandal;
	private Long constituencyId;
	private boolean muncipality;
	private Long groupLocationValue;
	private boolean constituency;
    private Long mandalId;
    private Long localEleBodyId;
    private String elderCaste;
    private String youngerCaste;
    private String tehsilName;
    private String constituencyType;
    private String districtName;
    private Long pinCode;
    private List<GenericVO> occupationList;
    private List<GenericVO> educationList;
    private List<GenericVO> socialPositionList;
    private Long categoryValueId;
    
    private List<GenericVO> familyRelsList;
    private Long voterFamilyRelId;
    private boolean isDisable;
    private boolean makeItEnable;
    private String casteName;
    private Long HHFamilyDetailsId;
    private Long vtrFamilyRelTypeId;
    private boolean isChildren;
    private List<GenericVO> boothLeaderList;
    
    private String ownerMobNo;
    private Long leaderId;
    private String address;
    
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwnerMobNo() {
		return ownerMobNo;
	}

	public void setOwnerMobNo(String ownerMobNo) {
		this.ownerMobNo = ownerMobNo;
	}

	public Long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	public List<GenericVO> getBoothLeaderList() {
		return boothLeaderList;
	}

	public void setBoothLeaderList(List<GenericVO> boothLeaderList) {
		this.boothLeaderList = boothLeaderList;
	}

	public boolean isChildren() {
		return isChildren;
	}

	public void setChildren(boolean isChildren) {
		this.isChildren = isChildren;
	}

	public Long getHHFamilyDetailsId() {
		return HHFamilyDetailsId;
	}

	public void setHHFamilyDetailsId(Long hHFamilyDetailsId) {
		HHFamilyDetailsId = hHFamilyDetailsId;
	}

	public Long getVtrFamilyRelTypeId() {
		return vtrFamilyRelTypeId;
	}

	public void setVtrFamilyRelTypeId(Long vtrFamilyRelTypeId) {
		this.vtrFamilyRelTypeId = vtrFamilyRelTypeId;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public boolean isMakeItEnable() {
		return makeItEnable;
	}

	public void setMakeItEnable(boolean makeItEnable) {
		this.makeItEnable = makeItEnable;
	}

	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public Long getVoterFamilyRelId() {
		return voterFamilyRelId;
	}

	public void setVoterFamilyRelId(Long voterFamilyRelId) {
		this.voterFamilyRelId = voterFamilyRelId;
	}

	public List<GenericVO> getFamilyRelsList() {
		return familyRelsList;
	}

	public void setFamilyRelsList(List<GenericVO> familyRelsList) {
		this.familyRelsList = familyRelsList;
	}

	public Long getCategoryValueId() {
		return categoryValueId;
	}

	public void setCategoryValueId(Long categoryValueId) {
		this.categoryValueId = categoryValueId;
	}

	public List<GenericVO> getOccupationList() {
		return occupationList;
	}

	public void setOccupationList(List<GenericVO> occupationList) {
		this.occupationList = occupationList;
	}

	public List<GenericVO> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<GenericVO> educationList) {
		this.educationList = educationList;
	}

	public List<GenericVO> getSocialPositionList() {
		return socialPositionList;
	}

	public void setSocialPositionList(List<GenericVO> socialPositionList) {
		this.socialPositionList = socialPositionList;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public String getConstituencyType() {
		return constituencyType;
	}

	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getElderCaste() {
		return elderCaste;
	}

	public void setElderCaste(String elderCaste) {
		this.elderCaste = elderCaste;
	}

	public String getYoungerCaste() {
		return youngerCaste;
	}

	public void setYoungerCaste(String youngerCaste) {
		this.youngerCaste = youngerCaste;
	}

	public String getInfluencePartyName() {
		return influencePartyName;
	}

	public void setInfluencePartyName(String influencePartyName) {
		this.influencePartyName = influencePartyName;
	}

	public boolean isConstituency() {
		return constituency;
	}

	public void setConstituency(boolean constituency) {
		this.constituency = constituency;
	}

	public Long getGroupLocationValue() {
		return groupLocationValue;
	}

	public void setGroupLocationValue(Long groupLocationValue) {
		this.groupLocationValue = groupLocationValue;
	}

	public boolean isMuncipality() {
		return muncipality;
	}

	public void setMuncipality(boolean muncipality) {
		this.muncipality = muncipality;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public boolean isMandal() {
		return mandal;
	}

	public void setMandal(boolean mandal) {
		this.mandal = mandal;
	}

	public boolean isGroupPresent() {
		return groupPresent;
	}

	public void setGroupPresent(boolean groupPresent) {
		this.groupPresent = groupPresent;
	}

	public Long getCustomGroupId() {
		return customGroupId;
	}

	public void setCustomGroupId(Long customGroupId) {
		this.customGroupId = customGroupId;
	}

	public List<SelectOptionVO> getCustomGroups() {
		return customGroups;
	}

	public void setCustomGroups(List<SelectOptionVO> customGroups) {
		this.customGroups = customGroups;
	}

	public List<String> getCadreNames() {
		return cadreNames;
	}

	public void setCadreNames(List<String> cadreNames) {
		this.cadreNames = cadreNames;
	}

	public List<String> getPoliticianNames() {
		return politicianNames;
	}

	public void setPoliticianNames(List<String> politicianNames) {
		this.politicianNames = politicianNames;
	}

	public List<String> getInfluenceNames() {
		return influenceNames;
	}

	public void setInfluenceNames(List<String> influenceNames) {
		this.influenceNames = influenceNames;
	}

	public Long getSelTypeId() {
		return selTypeId;
	}

	public void setSelTypeId(Long selTypeId) {
		this.selTypeId = selTypeId;
	}

	public String getSelType() {
		return selType;
	}

	public void setSelType(String selType) {
		this.selType = selType;
	}

	public String getPartNo() {
		return partNo;
	}

	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

	public List<SelectOptionVO> getSubLocalities() {
		return subLocalities;
	}

	public void setSubLocalities(List<SelectOptionVO> subLocalities) {
		this.subLocalities = subLocalities;
	}

	public List<SelectOptionVO> getUserCategoriesList() {
		return userCategoriesList;
	}

	public void setUserCategoriesList(List<SelectOptionVO> userCategoriesList) {
		this.userCategoriesList = userCategoriesList;
	}

	public Long getSubLocalityId() {
		return subLocalityId;
	}

	public void setSubLocalityId(Long subLocalityId) {
		this.subLocalityId = subLocalityId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	//start
	private List<SelectOptionVO> localitiesList;
	private boolean localityPresent;
	private String  selectedType;
	private Long selectedTypeId;
	private Long localitityId;
	private Long hamletId;
	private String location;
	private boolean hamletPresent;
	private boolean localAreaPresent;
	private List<SelectOptionVO> hamletIds; 
	private List<SelectOptionVO> localAreaIds; 
	private String hamletName;
	private String localAreaName;
	private String wardName;

	public String getHamletName() {
		return hamletName;
	}

	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}

	public String getLocalAreaName() {
		return localAreaName;
	}

	public void setLocalAreaName(String localAreaName) {
		this.localAreaName = localAreaName;
	}

	public boolean isHamletPresent() {
		return hamletPresent;
	}

	public void setHamletPresent(boolean hamletPresent) {
		this.hamletPresent = hamletPresent;
	}

	public boolean isLocalAreaPresent() {
		return localAreaPresent;
	}

	public void setLocalAreaPresent(boolean localAreaPresent) {
		this.localAreaPresent = localAreaPresent;
	}

	public List<SelectOptionVO> getHamletIds() {
		return hamletIds;
	}

	public void setHamletIds(List<SelectOptionVO> hamletIds) {
		this.hamletIds = hamletIds;
	}

	public List<SelectOptionVO> getLocalAreaIds() {
		return localAreaIds;
	}

	public void setLocalAreaIds(List<SelectOptionVO> localAreaIds) {
		this.localAreaIds = localAreaIds;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getLocalitityId() {
		return localitityId;
	}

	public void setLocalitityId(Long localitityId) {
		this.localitityId = localitityId;
	}

	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public List<SelectOptionVO> getLocalitiesList() {
		return localitiesList;
	}

	public void setLocalitiesList(List<SelectOptionVO> localitiesList) {
		this.localitiesList = localitiesList;
	}

	public boolean isLocalityPresent() {
		return localityPresent;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public Long getSelectedTypeId() {
		return selectedTypeId;
	}

	public void setSelectedTypeId(Long selectedTypeId) {
		this.selectedTypeId = selectedTypeId;
	}

	public void setLocalityPresent(boolean localityPresent) {
		this.localityPresent = localityPresent;
	}
//end
	public VoterHouseInfoVO(){
		
	}

	public VoterHouseInfoVO(String houseNo, int numberOfPeople, String cast,
			String elder, String younger) {
		this.houseNo = houseNo;
		this.numberOfPeople = numberOfPeople;
		this.cast = cast;
		this.elder = elder;
		this.younger = younger;
	}

	public VoterHouseInfoVO(Long sNo, String name){
		this.sNo = sNo;
		this.name = name;
	}


	public List<AddressVO> getCategories() {
		return categories;
	}

	public void setCategories(List<AddressVO> categories) {
		this.categories = categories;
	}

	public String getVoterCategoryValuesName() {
		return voterCategoryValuesName;
	}

	public void setVoterCategoryValuesName(String voterCategoryValuesName) {
		this.voterCategoryValuesName = voterCategoryValuesName;
	}

	public List getCategoryValues() {
		return categoryValues;
	}

	public void setCategoryValues(List categoryValues) {
		this.categoryValues = categoryValues;
	}

	public List<SelectOptionVO> getCategory() {
		return category;
	}

	public void setCategory(List<SelectOptionVO> category) {
		this.category = category;
	}

	/*public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}*/

	public Long getUserCategoryValuesId1() {
		return userCategoryValuesId1;
	}

	public void setUserCategoryValuesId1(Long userCategoryValuesId1) {
		this.userCategoryValuesId1 = userCategoryValuesId1;
	}

	public Long getCategoryValuesId() {
		return categoryValuesId;
	}

	public void setCategoryValuesId(Long categoryValuesId) {
		this.categoryValuesId = categoryValuesId;
	}

	public Long getVoterCategoryValuesId() {
		return voterCategoryValuesId;
	}

	public void setVoterCategoryValuesId(Long voterCategoryValuesId) {
		this.voterCategoryValuesId = voterCategoryValuesId;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	/*public Long getUserCategoryValuesId() {
		return userCategoryValuesId;
	}

	public void setUserCategoryValuesId(Long userCategoryValuesId) {
		this.userCategoryValuesId = userCategoryValuesId;
	}

	public String getUserCategoryValuesName() {
		return userCategoryValuesName;
	}

	public void setUserCategoryValuesName(String userCategoryValuesName) {
		this.userCategoryValuesName = userCategoryValuesName;
	}*/

	public List<SelectOptionVO> getCasteGroupNameList() {
		return casteGroupNameList;
	}

	public void setCasteGroupNameList(List<SelectOptionVO> casteGroupNameList) {
		this.casteGroupNameList = casteGroupNameList;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public String getVilliageCovered() {
		return villiageCovered;
	}

	public void setVilliageCovered(String villiageCovered) {
		this.villiageCovered = villiageCovered;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserVoterDetailsId() {
		return userVoterDetailsId;
	}

	public void setUserVoterDetailsId(Long userVoterDetailsId) {
		this.userVoterDetailsId = userVoterDetailsId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getElder() {
		return elder;
	}

	public void setElder(String elder) {
		this.elder = elder;
	}

	public String getYounger() {
		return younger;
	}

	public void setYounger(String younger) {
		this.younger = younger;
	}

	public Long getTotalHousesCount() {
		return totalHousesCount;
	}

	public void setTotalHousesCount(Long totalHousesCount) {
		this.totalHousesCount = totalHousesCount;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getGaurdian() {
		return gaurdian;
	}

	public void setGaurdian(String gaurdian) {
		this.gaurdian = gaurdian;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}
	
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getYoungerGender() {
		return youngerGender;
	}

	public void setYoungerGender(String youngerGender) {
		this.youngerGender = youngerGender;
	}

	public Long getYoungerAge() {
		return youngerAge;
	}

	public void setYoungerAge(Long youngerAge) {
		this.youngerAge = youngerAge;
	}

	public String getElderGender() {
		return elderGender;
	}

	public void setElderGender(String elderGender) {
		this.elderGender = elderGender;
	}

	public Long getElderAge() {
		return elderAge;
	}

	public void setElderAge(Long elderAge) {
		this.elderAge = elderAge;
	}

	public String getVoterGroupName() {
		return voterGroupName;
	}

	public void setVoterGroupName(String voterGroupName) {
		this.voterGroupName = voterGroupName;
	}

	public Long getUserCategoryValueId() {
		return userCategoryValueId;
	}

	public void setUserCategoryValueId(Long userCategoryValueId) {
		this.userCategoryValueId = userCategoryValueId;
	}

	public String getUserCategoryValueName() {
		return userCategoryValueName;
	}

	public void setUserCategoryValueName(String userCategoryValueName) {
		this.userCategoryValueName = userCategoryValueName;
	}

	public List<VoterHouseInfoVO> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(List<VoterHouseInfoVO> categoriesList) {
		this.categoriesList = categoriesList;
	}

	public List<SelectOptionVO> getParties() {
		return parties;
	}

	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}

	public Long getCasteStateId() {
		return casteStateId;
	}

	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}

	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}

	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}

	public List<VoterHouseInfoVO> getVotersList() {
		return votersList;
	}

	public void setVotersList(List<VoterHouseInfoVO> votersList) {
		this.votersList = votersList;
	}

	public Long getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}

	public List<VoterHouseInfoVO> getBoothsList() {
		return boothsList;
	}

	public void setBoothsList(List<VoterHouseInfoVO> boothsList) {
		this.boothsList = boothsList;
	}

	public List<VoterHouseInfoVO> getFamiliesList() {
		return familiesList;
	}

	public void setFamiliesList(List<VoterHouseInfoVO> familiesList) {
		this.familiesList = familiesList;
	}

	public Long getToAge() {
		return toAge;
	}

	public void setToAge(Long toAge) {
		this.toAge = toAge;
	}
	

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortByColum() {
		return sortByColum;
	}

	public void setSortByColum(String sortByColum) {
		this.sortByColum = sortByColum;
	}

	public boolean isPartyPresent() {
		return partyPresent;
	}

	public void setPartyPresent(boolean partyPresent) {
		this.partyPresent = partyPresent;
	}

	public boolean isCastPresent() {
		return castPresent;
	}

	public void setCastPresent(boolean castPresent) {
		this.castPresent = castPresent;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public boolean isCategoriesPresent() {
		return categoriesPresent;
	}

	public void setCategoriesPresent(boolean categoriesPresent) {
		this.categoriesPresent = categoriesPresent;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String[] getSortIds() {
		return sortIds;
	}

	public void setSortIds(String[] sortIds) {
		this.sortIds = sortIds;
	}

	public boolean isSortReq() {
		return sortReq;
	}

	public void setSortReq(boolean sortReq) {
		this.sortReq = sortReq;
	}

	public String getSortEle() {
		return sortEle;
	}

	public void setSortEle(String sortEle) {
		this.sortEle = sortEle;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public Long getFromSno() {
		return fromSno;
	}

	public void setFromSno(Long fromSno) {
		this.fromSno = fromSno;
	}

	public Long getToSno() {
		return toSno;
	}

	public void setToSno(Long toSno) {
		this.toSno = toSno;
	}

	public Boolean getIsInfluencePerson() {
		return isInfluencePerson;
	}

	public void setIsInfluencePerson(Boolean isInfluencePerson) {
		this.isInfluencePerson = isInfluencePerson;
	}

	public Boolean getIsCadrePerson() {
		return isCadrePerson;
	}

	public void setIsCadrePerson(Boolean isCadrePerson) {
		this.isCadrePerson = isCadrePerson;
	}

	public Boolean getIsPoliticion() {
		return isPoliticion;
	}

	public void setIsPoliticion(Boolean isPoliticion) {
		this.isPoliticion = isPoliticion;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public boolean isMobileNoPresent() {
		return mobileNoPresent;
	}

	public void setMobileNoPresent(boolean mobileNoPresent) {
		this.mobileNoPresent = mobileNoPresent;
	}

	public boolean isVoterGroupPresent() {
		return voterGroupPresent;
	}

	public void setVoterGroupPresent(boolean voterGroupPresent) {
		this.voterGroupPresent = voterGroupPresent;
	}

	public String getVoterGroup() {
		return voterGroup;
	}

	public void setVoterGroup(String voterGroup) {
		this.voterGroup = voterGroup;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public Long getLocalEleBodyId() {
		return localEleBodyId;
	}

	public void setLocalEleBodyId(Long localEleBodyId) {
		this.localEleBodyId = localEleBodyId;
	}

	public int compareTo(VoterHouseInfoVO vo) {
		
		if(this.cast == null || vo.getCast() == null)
			return 1;
		return this.cast.compareTo(vo.getCast());
	}
	
	
	
}
