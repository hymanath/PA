package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CallStatus;

public interface ICallStatusDAO extends GenericDao<CallStatus,Long>{
	public List<Object[]> getCallStatus();
}