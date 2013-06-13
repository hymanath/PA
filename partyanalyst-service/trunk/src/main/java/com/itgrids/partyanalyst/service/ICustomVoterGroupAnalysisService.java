package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;

public interface ICustomVoterGroupAnalysisService {
	
	public VoterCastInfoVO getVotersCountForPartyByCustomGroup(Long userId,Long custGroupId);
	
	public List<VoterCastInfoVO> getCasteWiseCustomVotersCount(Long customVoterGroupId,Long userId);
	
	public List<VoterCastInfoVO> getCustomGroupWiseVoterCasteDetails(Long userId,String areaType,Long locationValue);
	
	public String getGroupNameByGroupId(Long customVoterGroupId);
	
	public List<VoterHouseInfoVO> getCustomVoterDetails(Long casteStateId, Long casteId, Long customVoterGroupId, Long userId);
	
	//public VoterCastInfoVO getCustomGroupWiseVoterCasteDetails(Long userId,String areaType,Long locationValue);
	public VotersDetailsVO getAgeWiseCustomVotersInGroup(Long userId,Long customGroupId,Long publicationDateId);
	/*public List<VoterVO> getVoterDetailsForCustomVoterGroup(Long customvoterGroupId,Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId);*/
	
	public List<VoterVO> getVoterDetailsForCustomVoterGroup(Long customvoterGroupId,Integer startIndex , Integer maxRecords , String order,
			String columnName,Long userId,Long publicationDateId);
	
	
	 public List<VoterVO> getVoterDataForCustomGroup(VoterDataVO voterDataVO,Long userId,List<Long> categories);
	 
	 public VoterInfo getTotalVotersDetailsbyCustomVoterGroup(Long userId,Long customVoterGroupId,Long publicationId);
	 
	 public InfluencingPeopleBeanVO getInfluencingPeopleCount(Long userId,Long customVoterGroupId,Long publicationDateId);
	 
	 public List<VoterVO> showVoterDetailsForSelcetedType(Long userId,Long customVoterGroupId,Long publicationDateId,String btnName,Integer startIndex,Integer maxRecords,String order,String columnName);
	 
	 public ImportantFamiliesInfoVo getCustomVoterImpFamilyDetails(Long customVoterGroupId,Long publicationDateId,Long userId);
	 
	 public List<VotersDetailsVO> getCustomVotersAgeDetails(Long constituencyId, Long locationId, Long publicationDateId,String areaType, Long userId);
	 
	 public List<VoterVO> getVoterDetailsForAttribute(Long userVoterCategoryValueId,Long casteId,String gender,Integer startIndex,Integer maxIndex,String order,String columnName,Long userId,String locationType,Long locationId,Long publicationId);
	 
	 public List<VoterVO> getVoterDataForAttribute(VoterDataVO voterDataVO,Long userId,List<Long> categories);
	 public VoterInfo getTotalVotersDetailsbyCategoryAndCaste(Long categoryId,Long casteId,Long locationId,Long publicationId,String areaType,Long userId);
	 public InfluencingPeopleBeanVO getInfluencingPeopleCountByCategoryAndCaste(Long categoryId,Long casteId,Long locationId,Long publicationId,
				String areaType,String gender,Long userId);
	 
	 public List<VoterVO> showVoterDetailsForSelcetedTypeByCasteAndCategoryId(Long userId,VoterDataVO voterDataVO,String btnName);
	 
}
