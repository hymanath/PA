package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PanchayatResult;

public interface IPanchayatResultDAO extends GenericDao<PanchayatResult, Long>{
	public List<Object[]> getPartyWiseWonInPanchayts(List<Long> panchayatIds);
}
