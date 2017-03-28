package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LoginRequiredUrl;

public interface ILoginRequiredUrlDAO extends GenericDao<LoginRequiredUrl, Long> {
	
	public String getIsLoginUrl(String loginUrl);

	
}
