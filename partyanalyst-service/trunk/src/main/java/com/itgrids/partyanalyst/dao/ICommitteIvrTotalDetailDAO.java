package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommitteIvrTotalDetail;

public interface ICommitteIvrTotalDetailDAO extends GenericDao<CommitteIvrTotalDetail, Long>{

	public List<Object[]> getStateWiseIvrDetails(String state);
}
