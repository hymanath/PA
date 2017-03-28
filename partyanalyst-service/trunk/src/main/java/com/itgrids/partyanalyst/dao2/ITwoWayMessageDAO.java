package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TwoWayMessage;

public interface ITwoWayMessageDAO  extends GenericDao<TwoWayMessage, Long>{
	public List<Object[]> getMessagesInfo(Integer startIndex,Integer maxIndex);
	public Long getMessagesCount();
}
