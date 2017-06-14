package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FundSanctionMatrixDetails;

public interface IFundSanctionMatrixDetailsDAO extends GenericDao<FundSanctionMatrixDetails,Long> {
	public List<Object[]> getPreviousYearDtls(Long scopeId,Long previousYearId);
}
