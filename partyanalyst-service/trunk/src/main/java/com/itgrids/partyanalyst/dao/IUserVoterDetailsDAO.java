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

}
