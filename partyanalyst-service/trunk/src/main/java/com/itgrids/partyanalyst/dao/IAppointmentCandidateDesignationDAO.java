package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentCandidateDesignation;

public interface IAppointmentCandidateDesignationDAO extends GenericDao<AppointmentCandidateDesignation, Long> {
	public List<Object[]> getAppCandidateDesigList();
	public List<Object[]> getAppCandidateDesigListByType(Long typeId);
	public String checkDesignationExistOrNot(Long appointmentCandidateTypeId,String designation);

}
