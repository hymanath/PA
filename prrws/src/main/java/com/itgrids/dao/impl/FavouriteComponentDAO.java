package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IFavouriteComponentDAO;
import com.itgrids.model.FavouriteComponent;
import com.itgrids.utils.DateUtilService;

@Repository
public class FavouriteComponentDAO extends GenericDaoHibernate<FavouriteComponent, Long> implements IFavouriteComponentDAO {

	public FavouriteComponentDAO() {
		super(FavouriteComponent.class);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getFavouriteComponentId(String name) {
		Query  query =  getSession().createQuery("select model.favouriteComponentId from FavouriteComponent model where model.isDeleted='N' and model.name=:name ");
		query.setParameter("name", name);
		return query.list();
	}
	
	public int updateFavouriteComponentDtls(Long favouriteComponentId,String actionType) {
		Query query = getSession().createQuery(" update FavouriteComponent model set model.isDeleted=:actionType,model.updatedTime=:updatedTime where model.favouriteComponentId=:favouriteComponentId ");
		query.setParameter("favouriteComponentId", favouriteComponentId);
		query.setParameter("actionType", actionType);
		query.setParameter("updatedTime", new DateUtilService().getCurrentDateAndTime());
		return query.executeUpdate();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getFavouriteComponencts() {
		return getSession().createQuery(" select distinct model.favouriteComponentId,model.name,model.url,model.orderNo from FavouriteComponent model where model.isDeleted='N' order by model.orderNo ").list();
	}
	
	public Long getMaxOrderNo(){
		return (Long) getSession().createQuery("select max(model.orderNo) from FavouriteComponent model where model.isDeleted='N'").uniqueResult();
	}
}
