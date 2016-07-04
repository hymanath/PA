package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CallPurpose;


public interface ICallPurposeDAO extends GenericDao<CallPurpose, Long> {
	public List<Object[]> getAllCallPurposes();

}
