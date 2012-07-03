package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;
import com.itgrids.partyanalyst.model.ProblemComments;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemCommentsDAO extends GenericDaoHibernate<ProblemComments,Long> implements IProblemCommentsDAO{

	public ProblemCommentsDAO()
	{
		super(ProblemComments.class);
	}
	
	public List<Long> getAllApprovedComments(Long problemId)
	{
		return getHibernateTemplate().find("select count(model.problemCommentsId) from ProblemComments model where model.isApproved ='"+IConstants.TRUE+"' and model.isDelete = null and model.problem.problemId = ?",problemId);
	}
}
