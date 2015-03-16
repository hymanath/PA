package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;
import org.hibernate.Query;

import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public interface ITdpCadreCasteInfoDAO extends GenericDao<TdpCadreCasteInfo, Long>{
	public int deleteTdpCadreCasteInfoTableBeforeUpdate();
	public Integer updateTdpCadreCasteInfoTableByScheduler(String cadreType, String locationType);
}
