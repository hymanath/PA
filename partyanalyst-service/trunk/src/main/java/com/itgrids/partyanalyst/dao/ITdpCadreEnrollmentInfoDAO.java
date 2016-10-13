package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreEnrollmentInfo;

public interface ITdpCadreEnrollmentInfoDAO extends GenericDao<TdpCadreEnrollmentInfo, Long>{
	public List<Object[]>  getTotalNewRenewalCadreStateWise(String startDate, String endDate);
	public Long getTotalConstituencyForCdrRegStarted(Long stateId);
}