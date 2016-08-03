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
		
		  /*select  child.user_type_id , child.type as child, parent.user_type_id , parent.type as parent
		  from    user_type_relation utr 
				  join user_type  as child on utr.user_type_id = child.user_type_id
		          left join user_type as parent on utr.parent_user_type_id = parent.user_type_id
		  where   utr.is_active= 'Y' ;*/
		Query query = getSession().createQuery("" +
		" select  parent.userTypeId,parent.type,child.userTypeId,child.type " +
		" from   UserTypeRelation model" +
		"        join model.childUserType child " +
		"        left join model.parentUserType parent " +
		" where  model.isActive = 'Y' ");
		return query.list();
	}

}
