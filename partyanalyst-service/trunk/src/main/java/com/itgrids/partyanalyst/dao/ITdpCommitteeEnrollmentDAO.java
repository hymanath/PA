package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeEnrollment;

public interface ITdpCommitteeEnrollmentDAO  extends GenericDao<TdpCommitteeEnrollment, Long>{
	public List<Object[]> getCadreCommitteYearsList();
    public List<Object[]> getTdpCadreEnrollmentYear();

}
