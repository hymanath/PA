package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TelecastType;

public interface ITelecastTypeDAO extends GenericDao<TelecastType, Long>{

	public List<TelecastType> getTelecastTimeDetails();
}
