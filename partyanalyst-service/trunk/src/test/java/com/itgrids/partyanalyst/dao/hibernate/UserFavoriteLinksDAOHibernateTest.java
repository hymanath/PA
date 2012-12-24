package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserFavoriteLinksDAO;
import com.itgrids.partyanalyst.model.UserFavoriteLinks;

public class UserFavoriteLinksDAOHibernateTest  extends BaseDaoTestCase{
	

	private IUserFavoriteLinksDAO userFavoriteLinksDAO;
	

	public void setUserFavoriteLinksDAO(IUserFavoriteLinksDAO userFavoriteLinksDAO) {
		this.userFavoriteLinksDAO = userFavoriteLinksDAO;
	}
	
	public void test()
	{
		List<UserFavoriteLinks> list =userFavoriteLinksDAO.getUserFavouriteLinksAction(1l);
		
		System.out.println(list.size());
	}
}
