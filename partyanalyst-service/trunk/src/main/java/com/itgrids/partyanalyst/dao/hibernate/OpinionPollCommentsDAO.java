package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOpinionPollCommentsDAO;
import com.itgrids.partyanalyst.model.OpinionPollComments;

public class OpinionPollCommentsDAO extends GenericDaoHibernate<OpinionPollComments,Long>  implements IOpinionPollCommentsDAO {	
	
	public OpinionPollCommentsDAO() {
		super(OpinionPollComments.class);
	}
	
	public List<Object[]> getCommentDetailsByQuestionId(Long pollId){
		
		return getHibernateTemplate().find("select model.comment.commentId,model.comment.comment,model.firstName,model.lastName,model.insertedTime from OpinionPollComments model where model.opinionPoll.opinionPollId=? order by model.insertedTime DESC",pollId);
		
	}
}
