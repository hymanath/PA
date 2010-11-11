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
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;

public interface IInfluencingPeopleService {
	
	public Long saveInfluencePeople(InfluencingPeopleVO influencingPeopleVO);
	
	public List<SelectOptionVO> getAllInfluencePeoplePositions();
	
	public List<SelectOptionVO> getInfluenceRange();
	
	public void readAndSaveInfluencePeopleDataIntoDB(File file);
	
	public InfluencingPeopleBeanVO saveInfluencePeopleInfo(
			InfluencingPeopleBeanVO influencingPeopleBeanVO1,
			Map<String, Long> influRangeAndValueMap1);
	public InfluencingPeopleBeanVO getDetailsByInfluencingPersonId(Long influencingPersonId);
	public Integer deleteInfluencingPeople(Long influencingPeopleId);
	
	//Influencing People
	public ConstituencyManagementDataVO getInfluencingPeopleOverviewDetails(Long userId);
	public ConstituencyManagementRegionWiseCompleteDataVO getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(Long userId,Long regionId,String regionType,String moduleType,Long categoryId,String categoryType);
	
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getInfluencingPeopleByInfluenceScope(Long userId);
	public ConstituencyManagementDataVO getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long stateId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long districtId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long constituencyId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getMandalRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long mandalId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	public ConstituencyManagementDataVO getLocalELectionBodyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long localBodyId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType);
	
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByRegion(Long userId,Long regionId,String regionType);
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByScope(Long userId,Long regionId,String regionType);
	
	//Local User Groups
	public ConstituencyManagementDataVO getLocalUserGroupOverviewDetails(Long userId);
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getLocalUserGroupByInfluenceScope(Long userId);
	public List<LocalUserGroupDetailsVO> getLocalUserGroupDetails(Long userId,Long regionId,String regionType,Long categoryId,String categoryType);
	
	//SMS to influencing Persons
	public SmsResultVO sendSMSToInfluencingPersons(Long userId,List<Long> influencingPersonIds,String message,Boolean includeName,String module);
	public SmsResultVO sendSMSToInfluencingPersons(Long userId,String influencingPersonIds,String message,Boolean includeName,String module);
	
}
