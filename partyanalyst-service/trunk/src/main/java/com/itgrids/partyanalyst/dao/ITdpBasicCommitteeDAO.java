package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpBasicCommittee;

public interface ITdpBasicCommitteeDAO  extends GenericDao<TdpBasicCommittee, Long>{
	public List<Object[]> getBasicCommittees();
	public List<Object[]> getBasicCommitteesByTypeId(Long committeeTypeId);
}
