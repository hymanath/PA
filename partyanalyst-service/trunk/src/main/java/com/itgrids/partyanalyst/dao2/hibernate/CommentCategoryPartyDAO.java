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

import com.itgrids.partyanalyst.dao.ICommentCategoryPartyDAO;
import com.itgrids.partyanalyst.model.CommentCategoryParty;

public class CommentCategoryPartyDAO extends GenericDaoHibernate<CommentCategoryParty, Long>
		implements ICommentCategoryPartyDAO {

	public CommentCategoryPartyDAO() {
		super(CommentCategoryParty.class);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryParty> getCommentsOnAPartyInAllElections(
			Long partyId) {
		return getHibernateTemplate().find("from CommentCategoryParty model where model.party.partyId = ?",partyId);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryParty> getCommentsOnAPartyInAnElection(
			String electionType, String electionYear,Long partyId) {
		Object[] params = {electionType,electionYear,partyId};
		return getHibernateTemplate().find("from CommentCategoryParty model where model.election.electionScope.electionType.electionType = ?"+
				" and model.election.electionYear = ?"+
				" and model.party.partyId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<CommentCategoryParty> getCommentsOnAPartyInAnElection(
			Long electionId, Long partyId) {
		Object[] params = {electionId,partyId};
		return getHibernateTemplate().find("from CommentCategoryParty model where model.election.electionId = ? and model.party.partyId = ?",params);
	}


}
