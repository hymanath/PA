package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface IVoterReportService {
	
	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue);
	
	public ResultStatus insertVotersPartyDataToIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	public ResultStatus deletevotermodificationFromIntermediateTables(Long constituencyId,Long publicationId);
	
	public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	 public ResultStatus deleteVoterModifiedData(Long constituencyId,Long publicationDateId);
	 
	public void  getVotersCastWiseDetailsInALocationFromIntermediateTable(Long userId,Long reportLvlId,Long locationId,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO);
		
	/** This Method is used to get voterInfo by using boothId */
	public List<VoterVO> getVoterDetailsForAdminEdit(Long boothId,Long userId,Long startIndex,Long endIndex);
	public void getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO mainVO);
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,Long userId,Long constituencyId);
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleValues(List<SelectOptionVO> subList,Long publicationDateId,Long userId,Long constituencyId,Long locationLvl);
	
	public VoterVO saveVoterDetailsList(List<VoterVO> voterIds,Long userId,Long boothId);
	  
	public List<VoterHouseInfoVO> getVoterInfoByBIdandVId(List<VoterHouseInfoVO> votersList,Long publicationDateId);
	  
	public VoterVO saveVoterSearchDetailsList(List<VoterVO> voterIds,Long userId);
		
	public List<SelectOptionVO> getWardsInMunicipality(Long lclElecBodyId,Long publicationDateId);
	
	public List<VoterCastInfoVO> getVoterAttributeDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	
	public List<VoterCastInfoVO> getVoterAttributeSubDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId);
	
	public List<SelectOptionVO> getPanchayatsByTehsilId(Long tehsilId);
	
	public List<SelectOptionVO> getBoothsByPanchayatIDConstiId(Long panchayatId,Long constituencyId);
	
	public Long getLatestPublicationIdByConstiId(Long constituencyId);
	
	public List<SelectOptionVO> getSelectedUserCategoeryDetails(Long userId,List<Long> ids , String type,String status,Long constituencyId,Long publicationId);
	
	public List<SelectOptionVO> getAllWardsInUrbanConstituency(Long constituencyId,Long publicationId);
	
	public List<SelectOptionVO> getUserCategoeryValuesForWards(Long userId,Long constituencyId,List<Long> ids ,String status,Long publicationId);
	
	public List<SelectOptionVO> getAllBoothsForSelectedWards(List<Long> ids,Long publicationId);
	
	public List<SelectOptionVO> getUserCategoeryValuesForMuncipalWards(Long userId,Long constituencyId,String type,List<Long> ids,Long publicationId);
}
