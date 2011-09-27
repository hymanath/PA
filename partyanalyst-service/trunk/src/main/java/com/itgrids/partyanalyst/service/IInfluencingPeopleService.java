package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ConstituencyManagementDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseCompleteDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleDetailsVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.RegionSelectOptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;

public interface IInfluencingPeopleService {
	
	public Long saveInfluencePeople(InfluencingPeopleVO influencingPeopleVO);
	
	public List<SelectOptionVO> getAllInfluencePeoplePositions();
	
	public List<SelectOptionVO> getInfluenceRange();
	
	public InfluencingPeopleBeanVO saveInfluencePeopleInfo(InfluencingPeopleBeanVO influencingPeopleBeanVO1);
	
	public InfluencingPeopleBeanVO getDetailsByInfluencingPersonId(Long influencingPersonId);
	public Integer deleteInfluencingPeople(Long influencingPeopleId);
	
	//Influencing People
	public ConstituencyManagementDataVO getInfluencingPeopleOverviewDetails(Long userId,String userAccessType,String userAccessValue);
	public ConstituencyManagementRegionWiseCompleteDataVO getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(Long userId,Long regionId,String regionType,String moduleType,Long categoryId,String categoryType,String areaType);
	
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getInfluencingPeopleByInfluenceScope(Long userId);
	public ConstituencyManagementDataVO getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long stateId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long districtId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getMPConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId, Long mpConstituencyId, Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long constituencyId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getMandalRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long mandalId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType,String areaType,Long constituencyId);
	public ConstituencyManagementDataVO getLocalELectionBodyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long localBodyId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType,String areaType,Long regionId);
	public ConstituencyManagementDataVO getWardRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long wardId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType,String areaType,Long constituencyId);
	
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByRegion(Long userId,Long regionId,String regionType,Long parentRegionId);
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByScope(Long userId,Long regionId,String regionType);
	
	public InfluencingPeopleBeanVO getMoreResultsForInfluencingPeopleById(Long influencingPersonId);
	
	//Local User Groups
	public ConstituencyManagementDataVO getLocalUserGroupOverviewDetails(Long userId,String userAccessType,String userAccessValue);
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getLocalUserGroupByInfluenceScope(Long userId);
	public List<LocalUserGroupDetailsVO> getLocalUserGroupDetails(Long userId,Long regionId,String regionType,Long categoryId,String categoryType);
	
	//SMS to influencing Persons
	public SmsResultVO sendSMSToInfluencingPersons(Long userId,List<Long> influencingPersonIds,String message,Boolean includeName,String module,String senderName);
	public SmsResultVO sendSMSToInfluencingPersons(Long userId,String influencingPersonIds,String message,Boolean includeName,String module,String senderName);
	
	public List<RegionSelectOptionVO> getRegionsSelectOptionsForInput(Long regionId,String regionType,String selectedType);
	
	public List<SelectOptionVO> getLocalGroupCategoriesList(Long userId);
	public List<SelectOptionVO> getLocalGroupCategoriesList(Long userId,Long groupCategoryId);
	
	public LocalUserGroupDetailsVO saveLocalUserGroupDetailsTODB(LocalUserGroupDetailsVO localGroupDetails); 
	
	public List<SelectOptionVO> getLocalGroupsByCategoryAndUser(Long categoryId,Long userId);
	public List<SelectOptionVO> getLocalGroupDetailsByGroupId(Long groupId);
	public List<SelectOptionVO> getDesignationsByCategoryAndUser(Long categoryId,Long userId);
	
	public List<SelectOptionVO> getGroupCategoryByCategoryId(Long categoryId);
	
	public List<SelectOptionVO> saveNewDesignationForACategory(Long categoryId,Long userId,String desigType,String desigDesc);
	
	public LocalUserGroupDetailsVO getLocalUserGroupDetailsById(Long localUserGroupId);
	
	public UserGroupMembersVO saveUserGroupMemberDetails(UserGroupMembersVO userGroupMemberVO);
    public Integer deleteLocalUserGroup(Long groupId);
    
    public Long getStateIdOfAUser(String accessType,Long accessValue);
    
    public List<SelectOptionVO> saveNewPositionForInfluencingPeople(final String newPosition);
   
    public List<SelectOptionVO> saveNewGroupCatagory(final String group ,final Long userId);
	
}
