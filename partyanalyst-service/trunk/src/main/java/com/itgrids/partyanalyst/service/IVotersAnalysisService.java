package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;

public interface IVotersAnalysisService {
	
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId);
	
	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Integer startIndex , Integer maxRecords , String order,
			String columnName);
	
	public List<Long> getImpFamiles(Long id,Long publicationDateId,String name);
	
	public VoterCastInfoVO getVotersCastDetails(Long id,Long publicationDateId,String type);
	
	public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId);
	
	public  List<VoterCastInfoVO> getVotersCastDetailsForSubLevels(Long id,Long publicationDateId,String type,Long userId);
	
	public List<VotersDetailsVO> getVotersDetailsByAgewise(Long constituencyId, Long tehsilId,Long panchayatId,Long boothId,
			 Long publicationDateId , String type);
	public ImportantFamiliesInfoVo getImportantFamiliesInfo(String type,Long id,Long publicationDateId);
	
	public List<VoterHouseInfoVO> getVoterHouseInfoDetails(Long id, Long publicationDateId,String checkedEle);
	
	public List<VoterHouseInfoVO> getFamilyInfo(Long boothId, Long publicationDateId,String houseNo);
	
	public VoterHouseInfoVO getBoothDetailsForVoter(Long boothId);

	public List<VotersDetailsVO>  getAgewiseVotersDetailsForPanchayatisByTehsilId(Long tehsilId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByPanchayatId(Long panchayatId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForBoothsByLocalElectionBodyId(Long localElectionBodyId,Long publicationDateId);
	public List<VotersDetailsVO> getAgewiseVotersDetailsForTehsilsByConstituencyId(Long constituencyId,Long publicationDateId);
	
	public List<VoterHouseInfoVO> getVoterDetailsByCaste(Long id,Long publicationDateId,String caste);

	public VoterHouseInfoVO getVoterPersonalDetailsByVoterId(Long voterId,Long user);
	
	public void updateVoterDetails(VoterHouseInfoVO voterHouseInfoVO);
	
	public ResultStatus insertVoterData(Long constituencyId,Long publicationDateId,Integer startIndex, Integer maxResults);
	
	public List<VoterHouseInfoVO> getUserCategoryValues();
	
	public List<SelectOptionVO> getVoterCategoryValues(Long voterCategoryId,String letters);
	
	public SelectOptionVO storeGroupName(Long userId ,String name);
	
	public List<SelectOptionVO> findVoterCategoryValues(Long userId);
	
	public SelectOptionVO storeCategoryVakues(Long userId,String name, Long id);
	
	public List<SelectOptionVO> getAllPublicationDates();
	
	public List<SelectOptionVO> getConstituenciesList();
	
	public VoterCastInfoVO getVotersCastWiseDetailsInALocation(Long userId,String locationType,Long locationId,Long publicationDateId);
	
	public List<SelectOptionVO> getcastCategoryGroups();
	
	public ResultStatus saveCasteName(Long userId, Long stateId, Long casteCategoryGroupId, String casteName);
}
