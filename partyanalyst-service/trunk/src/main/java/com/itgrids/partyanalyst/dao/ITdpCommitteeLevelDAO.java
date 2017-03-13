package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeLevel;

public interface ITdpCommitteeLevelDAO  extends GenericDao<TdpCommitteeLevel, Long>{
	public List<Object[]> getAllLevels();
	public List<Object[]> getLevels();
	public List<Object[]> getCommitteeInfoList();
}
