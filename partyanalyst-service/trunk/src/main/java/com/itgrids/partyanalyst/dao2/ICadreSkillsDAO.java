package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreSkills;
import com.itgrids.partyanalyst.model.PartyCadreSkills;

public interface ICadreSkillsDAO extends GenericDao<CadreSkills, Long> {

	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreSkillAndUser(Long userId,Long skillId);
	
	@SuppressWarnings("unchecked")
	public List getCadreIdsByCadreSkillListAndUser(Long userId,List<Long> skillIds);
	
	@SuppressWarnings("unchecked")
	public List getCadreBySkillAndCadreIds(Long skillId,List<Long> cadreIds);
	
	@SuppressWarnings("unchecked")
	public List getCadreBySkillListAndCadreIds(List<Long> skillId,List<Long> cadreIds);
	
	public List<CadreSkills> findByCadreId(Long cadreId);
	
	public Integer deleteSkillsByCadreId(Long cadreId);
}
