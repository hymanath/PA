package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentTracking;

public interface IAppointmentTrackingDAO extends GenericDao<AppointmentTracking, Long> {
	public List<Object[]> getAppointmentTrackingDetails(Long appointmentId);
	public Long getAllRescheduledApptCounts(Long apptUserId);
	public Long getAllRescheduledCandiCounts(Long apptUserId);
	public List<Object[]> getAllRescheduledApptAndCandiDetails(Long apptUserId);
	public List<Object[]> getAllRescheduledCommentsAndrescheduledDates(Long apptUserId);
	public List<Object[]> getCandiWiseRescheduledAppts(Long apptUserId);
	public List<Object[]> getMeberWiseRescheduledAppts(Long apptUserId);
	public List<Object[]> getMeberWiseRescheduledAppts(Long apptUserId,List<Long> appointmentCandidateIds);
	public List<Object[]> overviewSummaryOfRescheduledCandidates(Long apptUserId);
}
