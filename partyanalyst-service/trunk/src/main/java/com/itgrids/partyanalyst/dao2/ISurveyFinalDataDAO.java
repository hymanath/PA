package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyFinalData;

public interface ISurveyFinalDataDAO extends GenericDao<SurveyFinalData, Long>{
	public List<Object[]> getThirdPartyStatusWithBooths(List<Long> boothIds);
	
	public int deleteExistingBoothDetails(Long boothId);
	public List<Object[]> getBoothWiseErrorCountForAConstituency(Long constituencyId);
	public List<Object[]> getBoothWiseVoterDetails(Long boothId);
	public List<Object[]> getSurveyFinalConstituencyInfo();
	public int updatedThirdPartyStatus(List<Long> voterIds,Long statusId);
	public List<Object[]> getWMUpdatedStatusOnThirdPartyData(List<Long> userIds,List<Long> boothIds);
	
	public int updatedThirdPartyComment(List<Long> voterIds,String comment);
	public List<Object[]> getWmCommentedDetails(Long boothId);
	public List<Object[]> getThirdPartyBooths(Long constituencyId);
	public List<Object[]> getWMUpdatedStatusOnThirdPartyDataByBooth(Long userId,Long boothId);
	
	public List<Object[]> getBoothDetails(Long boothId);
	
	public List<SurveyFinalData> getSurveyFinalDataObj(Long voterId,Long boothId);
	
	public int updateDefaultCasteMatchStatus(Long voterId,Long casteStateId,Long boothId,Long statusId);
	
	public Long getQcCollectedMatchedUnMatchedDetails(Long stateId, List<Long> statusIds,Date fromDate , Date toDate);
	
	public List<Object[]> getQcDataForSelection(Long stateId , List<Long> statusIds,Date fromDate , Date toDate);
	
	public List<Object[]> getBoothWiseQcData(Long stateId , Long constituencyId , List<Long> statusIds,Date fromDate , Date toDate);
	
	public List<Object[]> getVoterFinalCasteDataFromSurveyFinalData(Long constituencyId);
	
	public List<Object[]> getVoterFinalCasteDataFromSurveyDetailsInfo(Long constituencyId);
}
