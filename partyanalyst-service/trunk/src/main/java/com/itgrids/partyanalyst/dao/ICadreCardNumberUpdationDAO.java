package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreCardNumberUpdation;

public interface ICadreCardNumberUpdationDAO extends GenericDao<CadreCardNumberUpdation, Long>{

	public List<Object[]> getPrintCountsForAllUser(Date startDate,Date endDate);
	public List<Object[]> getPrintCountsForUser(Date startDate,Date endDate,Long userId);
	public List<Object[]> getReprintCountsForAllUser(Date startDate,Date endDate);
	public List getReprintCountsByDate(Date startDate,Date endDate);
}
