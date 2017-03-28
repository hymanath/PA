package com.itgrids.partyanalyst.dao;

import java.util.Date;
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
	public List<Object[]> getBoothWiseErrorCountForConstituencyByUsertypeId(Long constituencyId,Long userTypeId);
	
	public List<Long> getSurveyCallDetailsByVoterId(Long voterId);
	
	public Long getWmVerifiedVoters();
	public Long getWmVerifiedBooths();
	public Long getWmVerifiedConstituencyes();
	public Long getWmVerifiedRecordsCount(String type);
	public List<Object[]> getVerifierCounts(Long stateId,Date fromDate ,Date toDate);
	public List<Object[]> getConstituencyWiseVerifiedVoters(Long startDistrictId,Long endDistrictId,Date startDate,Date endDate);
	public List<Object[]> getConstituencyWiseVerifiedBooths(Long startDistrictId,Long endDistrictId,Date startDate,Date endDate);
	public List<Object[]> getBoothWiseVerifiedDetailsByConstituencyId(Long constituencyId);
	public List<Object[]> getBoothWiseUsersDetailsByConstituencyId(
			Long constituencyId);
	
	public List<Object[]> getVotersDetailsByBoothId(Long boothId);
	public List<Object[]> getTotalVotersByConstituencyIds(List<Long> constituencyIds);
	public List<Object[]> getTotalBoothsByConstituencyIds(List<Long> constituencyIds);

	public List<Long> getDataCollectorWebMonitorDetailsForConstituency(Long constituencyId);
	public List<Long> getDataVerifierWebMonitorDetailsForConstituency(Long constituencyId);
	public List<Long> getVerifiedBoothIdsByConstituencyId(Long constituencyId);
	
	public List<Object[]> getTotalVerifiedBoothsinAllConstituencyIds(List<Long> constituencyIds);
	
	public List<Object[]> getVerifiesCountDetails(Long stateId);
	
	public List<Object[]>  getConstituencyWiseSummaryForWmDc();
	public List<Object[]>  getConstituencyWiseSummaryForWmDV();
	
	public List<Object[]> getConstituencyWiseCasteUpdate();
	public List<Object[]> getConstituencyWiseBoothsCount();
	public List<Object[]> getConstituencyWiseMobilesUnMatched();
	public List<Object[]> getDcBoothWiseCasteCollectedDetailsForConstituency(Long constituencyId);
	public List<Object[]> getDcBoothWiseMobileCollectedDetailsForConstituency(Long constituencyId);
	public List<Object[]> getDvBoothWiseCasteCollectewdDetailsForConstituency(Long constituencyId);
	public List<Object[]> getDVSurveyCallDtailsByboothId(Long boothId,Long surveyUserId);
	public List<String> getInvalidMobileDetailsInCTP();
	
	public List<Object[]> getNewlyCollectdCasteDetails(Long constituencyId);
}
