package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCallStatus;

public interface ISurveyCallStatusDAO extends GenericDao<SurveyCallStatus,Long>{

	public Long getSurveyCallDtalsByVoterId(Long voterId);
	
	public List<Object[]> getStatusListForUser(List<Long> userIds,List<Long> boothIds,Long userTypeId);
	
	public List<Object[]> getSurveyCallDtalsByboothId(Long boothId,Long surveyUserId);
	
	public List<Object[]> getBoothWiseWmCasteUpdationDetails(List<Long> boothId);
	public List<Object[]> getBoothsByConstituency(Long constituencyId);
	public List<Object[]> getCasteVotersForAllConstituencies();
	public List<Object[]> getMobileVotersForAllConstituencies();
	
	public SurveyCallStatus getSurveyCallStatusByVoterId(Long voterId);
	
	public List<Object[]> getCasteStatusForBooth(List<Long> boothIds);
	public List<Object[]> getDvSurveyCallDtalsByboothId(Long boothId,Long surveyUserId);
	public List<Object[]> getStatusListForVerifier(List<Long> userIds,List<Long> boothIds,Long userTypeId);
	public List<Object[]> getBoothWiseDvWmCasteUpdationDetails(List<Long> boothIds);
	public List<Object[]> getWmDvMappedUnMappedDetailsBoothWise(Long constituencyId);
}
