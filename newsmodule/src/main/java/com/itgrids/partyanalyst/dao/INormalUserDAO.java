/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NormalUser;

public interface INormalUserDAO extends GenericDao<NormalUser, Long> {

	public List<NormalUser> findByUserId(Long userId);
}
