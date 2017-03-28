package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CallSupportType;

public interface ICallSupportTypeDAO extends GenericDao<CallSupportType, Long>{
	public List<Object[]> getCallSupportType();
}
