package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreEnrollmentYear;

public interface ITdpCadreEnrollmentYearDAO extends GenericDao<TdpCadreEnrollmentYear, Long>{

	public List<Long> getPreviousElectionYearsOfCadre(Long tdpCadreId);
	public Long getMaxRecordFromEnrollmentYear(Long tdpCadreId);
}
