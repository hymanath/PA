package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterRelation;

public interface IVoterRelationDAO extends GenericDao<VoterRelation, Long>{
	public List<Object[]> getAllRelationDetails();
	public List<Object[]> getRelationDetails(List<String> description );
}
