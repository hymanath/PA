package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AppointmentInputVO;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;

public interface IAppointmentCandidateRelationDAO extends GenericDao<AppointmentCandidateRelation, Long> {
	public List<Object[]> getAllAppointmentDetails(int startIndex,int maxIndex);
	public List<Object[]> countAppointmentDetails();
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId,Date fromDate,Date toDate);
	public List<Object[]> getAppointmentRelatedCandidates(List<Long> appointmentIds);
	public List<Object[]> getCandidatePreviousApptDetails(List<Long> candidateIds);
	public List<Object[]> getAppointmentCandidateDetails(List<Long> appointmentIds);
	public List<Object[]> getAppointmentSearchDetails(Date fromDate,Date toDate,AppointmentInputVO inputVo,String searchType);
	public List<Object[]> getLastVisitsByCandidates(List<Long> candidateIds);
	public List<Object[]> getCandidatePreviousApptDetails1(List<Long> candidateIds);
	public List<Object[]> getAppointmentCandidateMobileNos(Long appointmentId);
}
