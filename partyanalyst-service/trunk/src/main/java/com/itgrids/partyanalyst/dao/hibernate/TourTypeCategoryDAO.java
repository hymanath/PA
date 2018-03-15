package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITourTypeCategoryDAO;
import com.itgrids.partyanalyst.model.TourTypeCategory;

public class TourTypeCategoryDAO extends GenericDaoHibernate<TourTypeCategory, Long> implements ITourTypeCategoryDAO {

	public TourTypeCategoryDAO() {
		super (TourTypeCategory.class);
	}
	
	public List<Object[]> getTourTypeCategories() {
		Query query = getSession().createQuery("select model.tourTypeCategoryId,model.tourTypeCategory " +
				                              " from  TourTypeCategory model where model.isDeleted='N' ");
		return query.list();
	}
}
