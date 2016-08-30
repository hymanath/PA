package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserTypeRelationDAO;
import com.itgrids.partyanalyst.model.UserTypeRelation;

public class UserTypeRelationDAO extends GenericDaoHibernate<UserTypeRelation,Long> implements IUserTypeRelationDAO {
	
	public UserTypeRelationDAO() {
		super(UserTypeRelation.class);
	}
	
	public List<Object[]>  getParentUserTypesAndItsChildUserTypes(){
		  
		Query query = getSession().createQuery("" +
		" select  parent.userTypeId,parent.type," +
		"         child.userTypeId,child.type " +
		" from   UserTypeRelation model" +
		"        join model.childUserType child " +
		"        left join model.parentUserType parent " +
		" where  model.isActive = 'Y' ");
		return query.list();
	}
	
	public List<Object[]> getChildUserTypesByItsParentUserType(Long parentUserTypeId){
		
		Query query = getSession().createQuery("" +
		" select model.userTypeId,model.childUserType.type,model.parentUserTypeId,model.parentUserType.type " +
		" from  UserTypeRelation model " +
		" where model.parentUserTypeId = :parentUserTypeId and model.isActive = 'Y' " +
		" order by model.userTypeId asc ");
		query.setParameter("parentUserTypeId",parentUserTypeId);
		return query.list();
	}
	
}
