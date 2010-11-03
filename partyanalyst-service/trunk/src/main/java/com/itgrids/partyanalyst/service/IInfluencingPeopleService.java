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
import com.itgrids.partyanalyst.dto.SelectOptionVO;

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
	
	public ConstituencyManagementDataVO getInfluencingPeopleOverviewDetails(Long userId);
	public ConstituencyManagementRegionWiseCompleteDataVO getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(Long userId,Long regionId,String regionType);
	
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getInfluencingPeopleByInfluenceScope(Long userId);
	public ConstituencyManagementDataVO getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long stateId,Boolean isScopeData);
	public ConstituencyManagementDataVO getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long districtId,Boolean isScopeData);
	public ConstituencyManagementDataVO getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long constituencyId,Boolean isScopeData);
	public ConstituencyManagementDataVO getMandalRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long mandalId,Boolean isScopeData);
	public ConstituencyManagementDataVO getLocalELectionBodyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long localBodyId,Boolean isScopeData);
	
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByRegion(Long userId,Long regionId,String regionType);
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByScope(Long userId,Long regionId,String regionType);
}
