package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostMember;

public interface INominatedPostMemberDAO extends GenericDao<NominatedPostMember, Long>{

	public List<Object[]> getAllDeptsAndBoardsByLevel(Long boardLevelId,List<Long> levelValue);
}
