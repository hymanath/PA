package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FundSanctionMatrixRange;

public interface IFundSanctionMatrixRangeDAO extends GenericDao<FundSanctionMatrixRange,Long> {
	public List<Object[]> getFundSanctionRangeList();
}
