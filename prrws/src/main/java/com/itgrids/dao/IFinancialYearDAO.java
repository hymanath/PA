package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FinancialYear;

public interface IFinancialYearDAO extends GenericDao<FinancialYear,Long>{

	public List<Object[]> getAllFiniancialYears();
	public List<Object[]> getAllFiniancialYearsByIds(List<Long> financialYearIds);
	
}
