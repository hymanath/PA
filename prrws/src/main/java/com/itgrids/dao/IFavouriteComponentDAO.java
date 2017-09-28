package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FavouriteComponent;

public interface IFavouriteComponentDAO extends GenericDao<FavouriteComponent,Long>{

	public List<Long> getFavouriteComponentId(String name);
	public int updateFavouriteComponentDtls(Long favouriteComponentId,String actionType);
	public List<Object[]> getFavouriteComponencts();
}
