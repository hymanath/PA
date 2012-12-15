package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IFavoriteLinkPageDAO;
import com.itgrids.partyanalyst.model.FavoriteLinkPage;

public class FavoriteLinkPageDAO  extends GenericDaoHibernate<FavoriteLinkPage, Long> implements IFavoriteLinkPageDAO {
	
	
	public FavoriteLinkPageDAO(){
		super(FavoriteLinkPage.class);
		
	}

}
