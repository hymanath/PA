package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VoterNames;

public interface IVoterNamesDAO extends GenericDao<VoterNames, Long>{

	public List<VoterNames> gerVoterNamesObjByVoterId(Long voterId);
	public List<Object[]> getVoterNames(Long constituencyId,Long publicationDateId);
	public List<String> getVoterTeluguNames(Long voterId);
	public int updateVoterName(String name,Long voterNamesId);
	public List gerVoterNamesModelByVoterId(Long voterId);
}
