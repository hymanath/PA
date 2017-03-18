package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SelfAppraisalComment;

public interface ISelfAppraisalCommentDAO extends GenericDao<SelfAppraisalComment, Long>{

	public List<String> getCandidateCommentDetails(Long tdpCadreId, Long tourMonthId);
}
