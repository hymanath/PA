package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.model.CasteCategoryGroup;

public class CasteCategoryGroupDAO extends GenericDaoHibernate<CasteCategoryGroup, Long> implements 
ICasteCategoryGroupDAO{

	public CasteCategoryGroupDAO(){
		super(CasteCategoryGroup.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteCategoryGroupNames(Long casteCategoryId){
		
		Query query = getSession().createQuery("select model.casteCategoryGroupId,model.casteCategoryGroupName from CasteCategoryGroup model where model.casteCategory.casteCategoryId = ?");
		query.setParameter(0,casteCategoryId);
		System.out.println("value in CasteCategoryGroupNames:"+query.list());
		return query.list();
	
	}
@SuppressWarnings("unchecked")
public List<Object[]> getAllCasteCategoryGroupDetails(Long casteCategoryId){
		
		Query query = getSession().createQuery("select model.casteCategoryGroupId , model.casteCategoryGroupName from CasteCategoryGroup model where model.casteCategory.casteCategoryId=?");
		query.setParameter(0,casteCategoryId);
		return query.list();
		
		
	}
@SuppressWarnings("unchecked")
public List<Object[]> getAllCasteCategoryGroupInfoDetails(){
	
	Query query = getSession().createQuery("select model.casteCategoryGroupId , model.casteCategoryGroupName from CasteCategoryGroup model");
	
	return query.list();
	
	
}

@SuppressWarnings("unchecked")
public Long getCasteNamesOfCategories(Long casteCategoryId){
	
Query query=getSession().createQuery("select model.casteCategoryGroupId from CasteCategoryGroup model where model.casteCategory.casteCategoryId=?");
query.setParameter(0,casteCategoryId);
return (Long)query.uniqueResult();
}

 @SuppressWarnings("unchecked")
 public List<CasteCategoryGroup> getCasteCategoryGroupList()
 {
	return getHibernateTemplate().find(" from CasteCategoryGroup model ");
 }
	
}
