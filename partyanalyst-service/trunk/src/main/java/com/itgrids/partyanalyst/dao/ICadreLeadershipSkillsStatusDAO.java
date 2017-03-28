package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreLeadershipSkillsStatus;

public interface ICadreLeadershipSkillsStatusDAO extends GenericDao<CadreLeadershipSkillsStatus, Long>{
	public List<Object[]> getAllCadreLeadershipSkills();
}
