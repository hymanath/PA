package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRoleRelationDAO;
import com.itgrids.partyanalyst.model.CadreRoleRelation;

public class CadreRoleRelationDAO extends GenericDaoHibernate<CadreRoleRelation,Long> implements ICadreRoleRelationDAO{

	public CadreRoleRelationDAO(){
		super(CadreRoleRelation.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRolesByCadreId(Long cadreId)
	{
		return getHibernateTemplate().find("select model.cadreRole.cadreRoleId,model.cadreRole.role from CadreRoleRelation model where model.cadre.cadreId = ?",cadreId);
	}
	
	public Integer deleteRolesByCadreId(Long cadreId)
	{
		Query query = getSession().createQuery("delete from CadreRoleRelation model where model.cadre.cadreId = ?");
		query.setParameter(0,cadreId);
		return query.executeUpdate();
	}
}
