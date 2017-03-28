package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreAgerangeInfo;

public interface ITdpCadreAgerangeInfoDAO extends GenericDao<TdpCadreAgerangeInfo, Long>{
	public int deleteTdpCadreReageRangeInfoTableBeforeUpdate(Long enrollmentYearId);
	public Integer updateTdpCadReageRangeInfoTableByScheduler(String locationType,Long enrollmentYearId);
}
