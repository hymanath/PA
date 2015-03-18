package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreAgerangeInfo;

public interface ITdpCadreAgerangeInfoDAO extends GenericDao<TdpCadreAgerangeInfo, Long>{
	public int deleteTdpCadreReageRangeInfoTableBeforeUpdate();
	public Integer updateTdpCadReageRangeInfoTableByScheduler(String locationType);
}
