/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 14,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommentCategoryParty;

public interface ICommentCategoryPartyDAO extends GenericDao<CommentCategoryParty, Long> {

	public List<CommentCategoryParty> getCommentsOnAPartyInAnElection(String electionType,String electionYear,Long partyId);
	
	public List<CommentCategoryParty> getCommentsOnAPartyInAnElection(Long electionId,Long partyId);
	
	public List<CommentCategoryParty> getCommentsOnAPartyInAllElections(Long partyId);
	
}
