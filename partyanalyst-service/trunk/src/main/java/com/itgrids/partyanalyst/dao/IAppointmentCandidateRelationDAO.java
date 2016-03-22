package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;

public interface IAppointmentCandidateRelationDAO extends GenericDao<AppointmentCandidateRelation, Long> {
	
	public List<Object[]> getAppointmentsBySearchCriteria(Long designationId,Long priorityId,Long statusId,Long districtId,Long constituencyId);
	public List<Object[]> getAppointmentRelatedCandidates(List<Long> appointmentIds);
	public List<Object[]> getCandidatePreviousApptDetails(List<Long> candidateIds);
}
