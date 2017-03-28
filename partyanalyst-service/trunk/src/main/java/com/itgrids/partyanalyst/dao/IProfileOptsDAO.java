package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProfileOpts;

public interface IProfileOptsDAO extends GenericDao<ProfileOpts, Long>{

	public List getAllProfileOpts();
	
}
