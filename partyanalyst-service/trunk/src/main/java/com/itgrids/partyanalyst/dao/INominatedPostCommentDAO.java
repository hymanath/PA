package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostComment;

public interface INominatedPostCommentDAO extends GenericDao<NominatedPostComment, Long>{

	public List<Object[]> getCommentsCountForCandidateIds(Set<Long> candidateIds);
	public List<Object[]> getFinalyzedCommentsForCandidate(Long candidateId);
	public List<Object[]> getShortListingCommentsForCandidate(Long candidateId);
}
