package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Debate;
public interface IDebateDAO extends GenericDao<Debate, Long>{
	public List<Object[]> getDebateDetailsForSelectedDebate(Long debateId,Long stateId);
	
	public int removeDebate(Long debateId);
	
	public int deleteFlagDebate(Long debateId);
	
	public Object[] getLatestDebate();
}
