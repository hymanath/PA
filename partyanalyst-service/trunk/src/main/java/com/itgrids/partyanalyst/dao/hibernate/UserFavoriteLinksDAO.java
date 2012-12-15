package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserFavoriteLinksDAO;
import com.itgrids.partyanalyst.model.UserFavoriteLinks;

public class UserFavoriteLinksDAO  extends GenericDaoHibernate<UserFavoriteLinks, Long>implements IUserFavoriteLinksDAO {
	
	
	public UserFavoriteLinksDAO(){		
		super(UserFavoriteLinks.class);		
	}

}
