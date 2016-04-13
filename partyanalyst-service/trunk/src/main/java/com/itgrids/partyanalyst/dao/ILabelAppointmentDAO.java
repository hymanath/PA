package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LabelAppointment;

public interface ILabelAppointmentDAO extends GenericDao<LabelAppointment, Long> {
	
	public  List<Object[]> getLableDetailsWithStatusWiseCounts(Date labelDate,Long userId,Long statusId);
	public  List<Object[]>  checkLabelWithAppointment(Long appointmentLabelId,List<Long> appointmentIds);
	public List<Long> getAppointmentsForALabel(Long appointmentLabelId);
	public Integer deleteLabeledAppointments(Long appointmentLabelId,List<Long> appointmentIds);
	public Integer updateLabeledAppointments(Long appointmentLabelId,List<Long> appointmentIds,Long userId,Date date);
	public List<LabelAppointment> getDetailsOfLabelledAppointments(Long appointmentLabelId , List<Long> appointmentIds);
	public List<Object[]> getAppointmentsOfALableForUpdate(Long lableId,String callFrom);
	public List<Object[]> getLabelAppointmentsForFixed(Date toDayDate,String searchType,String type);
	public List<Object[]> getStatusLabelAppointments(Date toDayDate,String type);
	public List<Object[]> getLabelAppointmentsForFixedSatus(Date toDayDate,String searchType,String type,Long aptUserId);
	public List<Object[]> getLabelAppointmentsStatus(Date toDayDate,String type,Long aptUserId);
	public List<Object[]> getTimeSlotsDetails(Long appointmentLabelId);
	public List<Object[]> getViewAppointmentsOfALable(Long lableId);
	public List<LabelAppointment> getAppointmentsOfLabel(List<Long> appotIds,Long labelId);
	public Integer updateIsDeletedStatus(List<Long> apptIds,Long labelId,Long registrationId,Date date);
}
