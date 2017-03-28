
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterFamilyRelation;

public interface IVoterFamilyRelationDAO extends GenericDao<VoterFamilyRelation, Long>{
	public List<Object[]> getAllRelations();
}