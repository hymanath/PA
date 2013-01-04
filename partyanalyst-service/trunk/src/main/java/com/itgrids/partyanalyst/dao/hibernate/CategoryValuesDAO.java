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
	public List<Object[]> getVoterCategoryValues(Long voterCategoryId,String letters) {
		String cName = ""+letters+"%";
		Query queryObject = getSession().createQuery("select model.categoryValuesId,model.categoryValue from CategoryValues model where model.userCategoryValues.userCategoryValuesId=? and model.categoryValue like ?");
		queryObject.setLong(0,voterCategoryId);
		queryObject.setString(1,cName);
		return queryObject.list();		
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoryValues> getCategoryValues(){
		
		return getHibernateTemplate().find("from CategoryValues model");
	}
	
	public List<Long> checkCategoryExist(Long userId,String name, Long id) {
		Object[] values = {userId,name};
		return getHibernateTemplate().find("select count(*) from CategoryValues model where model.user.userId = ? and model.categoryValue = ?",values);
		
	}
	
	public List<Object[]> getCategoryValuesByUserIdCategId(Long userId,Long userCategoryValueId){
		
		Object[] values = {userId,userCategoryValueId};
		return getHibernateTemplate().find("select model.categoryValuesId,model.categoryValue from CategoryValues model where model.user.userId = ? and model.userCategoryValues.userCategoryValuesId=? ",values);
		
	}
}
