package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserSocialNetworkSite;



public interface IUserSocialNetworkSiteDAO extends GenericDao<UserSocialNetworkSite, Long>{
	
	public List getTwitterIdByPartyName(String partyName);
	
	

}
