package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreVo;
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
	 public List<MahanaduEventVO> getHourWiseSubEventsCount(Long parentEventId,List<Long> subEventIds,String startDate );
	 public List<MahanaduEventVO> getEventMembersCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate);
	 public List<MahanaduEventVO> getSubEventCount(Long parentId,List<Long> subEventIds,String startDate,String endDate);
	 public List<MahanaduEventVO> getEventsForUser(Long userId);
	 public List<MahanaduEventVO> getEventInfoByReportType(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate,String dataRetrievingType,Long parentEventId,String eventType);
	 public List<MahanaduEventVO> getDayWiseSubEventsCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate );
	 public List<MahanaduEventVO> getSubEvent(Long eventId);
	 public List<MahanaduEventVO> getMembersDetailsBySubEvent(Long eventId,String startDate,String endDate,Integer startIndex,Integer maxIndex);
	 public String updateTabAllocationDetails(Long authId,String cause,Long userId);
	 public List<CadreRegisterInfo> getAuthDetails(Long id,String variable);
	 public List<MahanaduEventVO> getAttendeeSummaryForEvents(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds,String startDate,String endDate);
	 public List<MahanaduEventVO> getOtherStateDeligatesCount(Long parentEventId,List<Long> subEventIds,String startDate,String endDate);
	 public List<MahanaduEventVO> getStatewiseCount(Long eventId,String startDate,String endDate);
	 public PartyMeetingVO getParticipatedCandidateEventDetails(Long tdpCadreId);
	 public List<MahanaduEventVO> getAttendeeSummaryForEventsList(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds);
	 public List<IdNameVO> getSubEventsOfEvent(Long eventId);
	 public List<MahanaduEventVO> getDaysUniqueAndRevisitSummary(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds);
	 public List<MahanaduEventVO> getDayWiseVisitSummary(Long eventId,Long stateId,Long reportLevelId,List<Long> subEventIds);
	 
	 public ResultStatus insertDataintoEventInfo1(Date startDate,Date endDate,Long parentEventId,List<Long> subEventIds);
}

