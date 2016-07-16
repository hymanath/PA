package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostFinal;

public interface INominatedPostFinalDAO extends GenericDao<NominatedPostFinal, Long>{

	public List<Object[]> getNominatedPostMemberDetails(Long levelId,Long levelValue,Long departmentId,Long boardId,Long positionId,String type);
	public Long getNominatedPostFinalDetails(Long nominatedPostId,Long nominationPostCandidateId);
}
