package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrMobile;

public interface IIvrMobileDAO extends GenericDao<IvrMobile, Long> {
	public List<Long> getMobilenos(Long scopeId,Long locationID,int maxIndex);
}
