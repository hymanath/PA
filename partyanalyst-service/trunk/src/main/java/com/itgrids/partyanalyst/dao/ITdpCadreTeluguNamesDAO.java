package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreTeluguNames;

public interface ITdpCadreTeluguNamesDAO extends GenericDao<TdpCadreTeluguNames, Long>{
	public List<String> getTeluguVoterNameByTdpCadreId(Long tdpCadreId);
	public List<Object[]> getTeluguVoterNameByTdpCadreIds(List<Long> tdpCadreIds);
	public List getModelByTdpCadreId(Long tdpCadreId);
}
