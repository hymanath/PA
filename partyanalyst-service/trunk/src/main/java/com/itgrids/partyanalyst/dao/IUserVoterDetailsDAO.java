/* 
 * Copyright (c) 2009 IT Grids.

 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.hibernate.UserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.UserVoterDetails;

public interface IUserVoterDetailsDAO extends GenericDao<UserVoterDetails, Long>{

	public List<UserVoterDetails> getUserVoterDetails(Long voterId,Long userId);
	
	public void updateUserVoterDetails(Long voterId,Long userId,Long partyId,Long castStateId);
	
	public List<Object[]> getCasteByVoterId(Long userId,List<Long> voterId);
	
	public void updateUserVoterPartyDetails(Long voterId,Long userId,Long partyId,Long castStateId);
	
	public void updateUserVoterCastDetails(Long voterId,Long userId,Long partyId,Long castStateId);

	public List<UserVoterDetails> getAllUserVoterDetails(List<Long> voterIds,Long userId);
	
	public List<Object[]> getUserVoterDetailsByVoterIds(List<Long> voterIds,Long userId);
	
	public List<UserVoterDetails> getUserVoterDtlsVoterIds(List<Long> voterIds,Long userId);
}
