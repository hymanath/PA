/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICommentCategoryConstituencyDAO;
import com.itgrids.partyanalyst.model.CommentCategoryConstituency;

public class CommentCategoryConstituencyDAO extends GenericDaoHibernate<CommentCategoryConstituency, Long>
		implements ICommentCategoryConstituencyDAO {

	public CommentCategoryConstituencyDAO() {
		super(CommentCategoryConstituency.class);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryConstituency> getCommentsOnAConstituencyInAElection(
			String electionYear, Long constituencyId) {
		Object[] params = {electionYear,constituencyId};
		return getHibernateTemplate().find("from CommentCategoryConstituency model where model.election.electionYear = ?"+
				" and model.constituency.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryConstituency> getCommentsOnAConstituencyInAElection(
			Long electionId, Long constituencyId) {
		Object[] params = {electionId,constituencyId};
		return getHibernateTemplate().find("from CommentCategoryConstituency model where model.election.electionId = ?"+
				" and model.constituency.constituencyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryConstituency> getCommentsOnAConstituencyInAllElections(
			Long constituencyId) {
		return getHibernateTemplate().find("from CommentCategoryConstituency model where model.constituency.constituencyId = ?",constituencyId);
	}
	
}
