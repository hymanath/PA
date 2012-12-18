package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.hibernate.Query;

import com.itgrids.partyanalyst.model.FavoriteLinkPage;

public interface IFavoriteLinkPageDAO extends GenericDao<FavoriteLinkPage, Long>{
	

	public List<FavoriteLinkPage> getFavoriteLinkByActionName(String link);
	
	

}
