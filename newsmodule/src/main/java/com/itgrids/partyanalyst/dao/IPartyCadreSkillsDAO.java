package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyCadreSkills;

public interface IPartyCadreSkillsDAO extends GenericDao<PartyCadreSkills, Long>{

	@SuppressWarnings("unchecked")
	public List<PartyCadreSkills> getCadreSkillsPartywise(Long partyId);
}
