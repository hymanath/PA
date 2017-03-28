package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.model.CasteCategory;

public class CasteCategoryDAO  extends GenericDaoHibernate<CasteCategory, Long> implements 
ICasteCategoryDAO{
	
	public CasteCategoryDAO() {
		super(CasteCategory.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getCasteCategoryDetails(){
		
		Query query = getSession().createQuery("select model.casteCategoryId,model.categoryName from CasteCategory model");
		
		return query.list();
	
	}
	public List<Object[]> getAllCasteCategoryDetails(){
		
		Query query = getSession().createQuery("select model.casteCategoryId , model.categoryName from CasteCategory model");
		return query.list();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CasteCategory> getCasteCategoryList()
	{
		return getHibernateTemplate().find(" from CasteCategory model ");
	}

}
