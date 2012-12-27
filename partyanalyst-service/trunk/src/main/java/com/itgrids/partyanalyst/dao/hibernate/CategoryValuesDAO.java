package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICategoryValuesDAO;
import com.itgrids.partyanalyst.model.CategoryValues;

public class CategoryValuesDAO extends GenericDaoHibernate<CategoryValues, Long> implements ICategoryValuesDAO{

	public CategoryValuesDAO() {
		super(CategoryValues.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterCategoryValues(Long voterCategoryId) {
		Query queryObject = getSession().createQuery("select model.categoryValuesId,model.categoryValue from CategoryValues model where model.userCategoryValues.userCategoryValuesId=?");
		queryObject.setLong(0,voterCategoryId);
		return queryObject.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryValues> getCategoryValues(){
		
		return getHibernateTemplate().find("from CategoryValues model");
	}
}
