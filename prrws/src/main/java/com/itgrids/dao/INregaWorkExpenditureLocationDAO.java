package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.NregaWorkExpenditureLocation;

public interface INregaWorkExpenditureLocationDAO extends GenericDao<NregaWorkExpenditureLocation, Long>{

	public List<Object[]> getWorkWiseExpenditureOverviewInConstituency(Long constituencyId,List<Long> financialYearIds);
	public List<Object[]> getWorkWiseExpenditureDetailsInConstituency(Long constituencyId,List<Long> financialYearIds);
	public List<Object[]> getProgramExpenditureDetailsInConstituency(Long constituencyId,List<Long> financialYearIds);
}
