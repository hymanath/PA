package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFavoriteLinkPageDAO;
import com.itgrids.partyanalyst.model.FavoriteLinkPage;

public class FavoriteLinkPageDAO  extends GenericDaoHibernate<FavoriteLinkPage, Long> implements IFavoriteLinkPageDAO {
	
	
	public FavoriteLinkPageDAO(){
		super(FavoriteLinkPage.class);
		
	}
	
	
	public List<FavoriteLinkPage> getFavoriteLinkByActionName(String link){
		
		Query query =getSession().createQuery("select model from FavoriteLinkPage model where model.url = ?");
		
		
		query.setParameter(0, link);
		
		return query.list();
		
	}

}
