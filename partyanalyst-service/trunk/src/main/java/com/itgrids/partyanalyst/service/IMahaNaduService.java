package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreVo;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;

public interface IMahaNaduService {
	public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID,Long tehsilId,Long localElecBodyId);
	
	public CadreVo searchCadreDetails(Long userId,Long constiId, String searchBy,String searchType,String sort,String sortBy,int startIndex,int maxResult);
	
	 public List<SelectOptionVO> getIncomeSources();
	
	 public List<SelectOptionVO> getCasteCategories();
	 
	 public List<SelectOptionVO> getPartyDesignations();
	 
	 public List<SelectOptionVO> getgovernmentDesignations();
	 public ResultStatus saveCadreInfoForMahaNadu(CadreVo CadreVoToSave);
	 public CadreVo getCadreCompleteInfo(Long cadreId);
	 
	 public CadreVo getDetailToPopulate(String voterIdCardNo,Long publicationId);
	 
	 public CadreVo searchVoterInfo(Long userId,Long boothId, String searchName,String searchType,String sort,String sortBy,int startIndex,int maxResult);
	 //public ResultStatus insertDataintoEventInfo();
	 public ResultStatus insertDataintoEventInfo(Date startDate,Date endDate,List<Long> eventIds);

	// public List<MahanaduEventVO> getSubEventInfo(Long parentId,Long userId);
	// public List<MahanaduEventVO> getEventInfoByReportType(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds);
	 public List<MahanaduEventVO> getHourWiseSubEventsCount(Long parentEventId,List<Long> subEventIds,String startDate,List<Long> enrollmentYearIds);
	 public List<MahanaduEventVO> getEventMembersCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate,List<Long> enrollmentYearIds);
	 public List<MahanaduEventVO> getSubEventCount(Long parentId,List<Long> subEventIds,String startDate,String endDate,List<Long> enrollmentYearIds);
	 public List<MahanaduEventVO> getEventsForUser(Long userId);
	 public List<MahanaduEventVO> getEventInfoByReportType(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate,String dataRetrievingType,Long parentEventId,String eventType);
	 public List<MahanaduEventVO> getDayWiseSubEventsCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate,List<Long> enrollmentYearIds);
	 public List<MahanaduEventVO> getSubEvent(Long eventId);
	 public List<MahanaduEventVO> getMembersDetailsBySubEvent(Long eventId,String startDate,String endDate,Integer startIndex,Integer maxIndex,List<Long> enrollmentYearIds);
	 public String updateTabAllocationDetails(Long authId,String cause,Long userId);
	 public List<CadreRegisterInfo> getAuthDetails(Long id,String variable);
	 public List<MahanaduEventVO> getAttendeeSummaryForEvents(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate);
	 public List<MahanaduEventVO> getOtherStateDeligatesCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate);
	 public List<MahanaduEventVO> getStatewiseCount(Long eventId,String startDate,String endDate,List<Long> enrollmentYearIds);
	 public PartyMeetingVO getParticipatedCandidateEventDetails(Long tdpCadreId);
	 public List<MahanaduEventVO> getAttendeeSummaryForEventsList(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds);
	 public List<IdNameVO> getSubEventsOfEvent(Long eventId);
	 public List<MahanaduEventVO> getDaysUniqueAndRevisitSummary(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String dateValues,List<Long> enrollmentIdsList);
	 public List<MahanaduEventVO> getDayWiseVisitSummary(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String date,List<Long> enrollmentIdsList);
	 
	 public ResultStatus insertDataintoEventInfo1(Date startDate,Date endDate,Long parentEventId,List<Long> subEventIds);
	 public List<IdNameVO> getSubEventsOfNewEvent(Long eventId);
	 public ResultStatus savingBloodDonateCandidateDetails(String name,String mobileNo,String memberShipId,String fromDateTimeStr);
	 public List<IdAndNameVO> getAllBloodDonateRegiCandidateDetails(String type,String attendedType);
	 public String getYouTubeUrls();
	 public Map<Long,Set<Long>> getTdpcadreIdsByEventIds(List<Long> eventIds);
	
}

