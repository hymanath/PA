package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreComminicationSkillsStatus;

public interface ICadreComminicationSkillsStatusDAO extends GenericDao<CadreComminicationSkillsStatus, Long>{
	 public List<Object[]> getAllCadreComminicationSkills();
}
