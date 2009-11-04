package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AllianceGroup;

public interface IAllianceGroupDAO extends GenericDao<AllianceGroup, Long>  {
	
	public List<AllianceGroup> findByGroupId(Long groupId);
}
