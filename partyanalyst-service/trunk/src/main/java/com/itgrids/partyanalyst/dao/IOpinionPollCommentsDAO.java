package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.OpinionPollComments;

public interface IOpinionPollCommentsDAO  extends GenericDao<OpinionPollComments, Long>{
	
	public List<Object[]> getCommentDetailsByQuestionId(Long pollId);

}
