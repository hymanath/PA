package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.VoterInfo;

public interface ICustomVoterGroupAnalysisService {/*
	
	public List<VoterCastInfoVO> getCasteWiseCustomVotersCount(Long customVoterGroupId,Long userId);
	
	public List<VoterCastInfoVO> getCustomGroupWiseVoterCasteDetails(Long userId,String areaType,Long locationValue);
	
	public String getGroupNameByGroupId(Long customVoterGroupId);
	
	public List<VoterHouseInfoVO> getCustomVoterDetails(Long casteStateId, Long casteId, Long customVoterGroupId, Long userId);
	
	//public VoterCastInfoVO getCustomGroupWiseVoterCasteDetails(Long userId,String areaType,Long locationValue);
	public VotersDetailsVO getAgeWiseCustomVotersInGroup(Long userId,Long customGroupId,Long publicationDateId);
	public List<VoterVO> getVoterDetailsForCustomVoterGroup(Long customvoterGroupId,Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId);
	
	public List<VoterVO> getVoterDetailsForCustomVoterGroup(Long customvoterGroupId,Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId,Long publicationDateId);
	
	
	 public List<VoterVO> getVoterDataForCustomGroup(VoterDataVO voterDataVO,Long userId,List<Long> categories);
	 
	 public VoterInfo getTotalVotersDetailsbyCustomVoterGroup(Long userId,Long customVoterGroupId,Long publicationId);
	 
	 public InfluencingPeopleBeanVO getInfluencingPeopleCount(Long userId,Long customVoterGroupId,Long publicationDateId);
	 
	 public List<VoterVO> showVoterDetailsForSelcetedType(Long userId,Long customVoterGroupId,Long publicationDateId,String btnName,Integer startIndex,Integer maxRecords,String order,String columnName);
	 public List<SelectOptionVO> getCustomVoterGroups(Long constituencyId,Long id,String groupType,Long userId);
*/}
