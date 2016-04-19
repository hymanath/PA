package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentCandidateType;

public interface IAppointmentCandidateTypeDAO extends GenericDao<AppointmentCandidateType, Long>{

	public List<Object[]> getAllCandidateTypes();
	public List<Object[]> getCandidateTypesByIds(List<Long> ids);
}
