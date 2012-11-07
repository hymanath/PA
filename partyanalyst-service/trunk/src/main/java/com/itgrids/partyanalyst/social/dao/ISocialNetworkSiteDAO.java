package com.itgrids.partyanalyst.social.dao;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.social.model.SocialNetworkSite;

public interface ISocialNetworkSiteDAO  extends GenericDao<SocialNetworkSite, Long>{
	public Long getSocialNetworkIdByName(String name);
}
