package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;

public interface IAppointmentCandidateRelationDAO extends GenericDao<AppointmentCandidateRelation, Long> {
	public List<Object[]> getAllAppointmentDetails(int startIndex,int maxIndex,Long aptUserId);
	public List<Object[]> countAppointmentDetails();
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId,Date fromDate,Date toDate,Long selUserId,Long cndTypeId,Long dateType);
	public List<Object[]> getAppointmentRelatedCandidates(List<Long> appointmentIds);
	public List<Object[]> getCandidatePreviousApptDetails(List<Long> candidateIds);
	public List<Object[]> getAppointmentCandidateDetails(List<Long> appointmentIds);
	public List<Object[]> getAppointmentSearchDetails(Date fromDate,Date toDate,AppointmentInputVO inputVo,String searchType);
	public List<Object[]> getLastVisitsByCandidates(List<Long> candidateIds,Long apptUserId);
	public List<Object[]> getCandidatePreviousApptDetails1(List<Long> candidateIds,Long apptUserID);
	public List<Object[]> getAppointmentCandidateMobileNos(Long appointmentId);
	public List<Object[]> getAppointmentStatusOverview(Long appointmentUserId);
	public List<Object[]> getAppointStatusOverviewforCandidate(Long apointmntcandidteId,Long apptUserId);
   public List<String> getAppointmentIdsforSendSms(Long appointmentId);
	
	public List<Object[]> getApptAndMembersCountsByStatus(Long apptUserId);
	public List<Object[]> getLabelledAndNonLabelledApptIdsForWaitingStatus(Long apptUserId,String labelStatus,Long waitingAppointmentStatusId);
	public List<Object[]> getOnlyFixedStatusCounts(Long apptUserId,Date currentDateAndTime,Long fixedAppointmentStatusId);
	public List<Object[]> getAttendedStatusCounts(Long apptUserId,Date currentDateAndTime,Long attendedAppointmentStatusId,Long fixedAppointmentStatusId);
	public List<Object[]> getFixedAttendedCount(Long apointmntcandidteId,Date currentDateAndTime,Long apptUserId);
	public List<Object[]> getAppointmentHistoryDetailsByCandidateId(Long apointmntcandidteId,Long apptUserId);
	
	public List<Long> getApptCandidIds(Long appointmentId);
	public List<Object[]> checkApptsAsVoid(Date insertedTime,Long apptStatusId,List<Long> apptCandiIds,Long apptUserId);
	public List<Object[]> getApptCandidIdsAndInsertedTime(Long appointmentId);
	public List<Object[]> getCandidCountsByStatesAndStatus(Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate);
	public List<Object[]>  getCommitteeLevelAppointments(List<Long> statusIds,String type,Long appointCandidteId);
	public List<Object[]> getTotalCountCandiByLocation(String queryString,Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate);
	public List<Object[]> getCountsOfCandiByLocation(String queryString,Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate,List<Long> candiTypeIds);
	public List<Object[]>  getLevelWiseCount(List<Long> statusIds,String type, Long levelId,Long aptUserId);
	public List<Object[]> getAppointmentSearchDetailsForStatus(Date fromDate,Date toDate,AppointmentInputVO inputVo,String searchType);
	public List<Object[]> checkIsAppointmentEligible(List<String> membershipNoList,List<Long> apptStatusIds,Long appointmentUserId);
	
	public List<Object[]> eachStatusApptCountAndUniqueMemCount(Long apptUserId,List<Long> statusIds);
	public Object[] combinedStatusApptAndUniqueMemCount(Long apptUserId,List<Long> statusIds);
	public List<Object[]> checkIsAppointmentEligibleCadre(List<Long> cadreList,List<Long> apptStatusIds,Long appointmentUserId);
	
	public List<Object[]> getAllScheduledApptsByDate(Long apptUserId,Date date,Long apptStatusId);
	
	public List<Object[]> getTotalAppointmentDetails(Long appointmentId);
	public List<Object[]> getCandidateAppointmentDetails(List<Long> appointmentUserIds,Long tdpcadreId);
	public List<Object[]> getCandidateCountsOfStatesByStatuses(Long appointmentUserId,List<Long> apptStatusIds,Date startDate,Date endDate);
	
	public List<Object[]> getTotalAppointmentsForCandiates(List<Long> appointmentCandidateIds,Long appointmentUserId,Long appStatusId);
	public List<Object[]> getCandidateLastVisitedDtl(List<Long> appointmentCandidateIds,Long appointmentUserId,Long appStatusId);
	public List<Object[]> getTotalApptsByCandiates(List<Long> appointmentCandidateIds,Long appointmentUserId);
	public List<Long> checkAppointmentCandidateExistsWithAppointment(Long appointmentId,Long appointmentCandidateId);
}
