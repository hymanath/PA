package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.ICadreFamilyMemberInfoDAO;
import com.itgrids.partyanalyst.model.CadreFamilyMemberInfo;

public class CadreFamilyMemberInfoDAO extends GenericDaoHibernate<CadreFamilyMemberInfo, Long> implements ICadreFamilyMemberInfoDAO{

	public CadreFamilyMemberInfoDAO() {
		super(CadreFamilyMemberInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByCadreId(Long cadreId)
	{
		return getHibernateTemplate().find("select model.cadre.cadreId, model.name, model.dateOfBirth,model.userRelation.relationship, model.userRelation.userRelationId from CadreFamilyMemberInfo model where model.cadre.cadreId = ?", cadreId);
	}

	public Integer deleteFamilyMemberDetailsByCadre(Long cadreId) {
		Query queryObject = getSession().createQuery("delete from CadreFamilyMemberInfo model where model.cadre.cadreId = ?");
		queryObject.setParameter(0, cadreId);
		return queryObject.executeUpdate();
	}
}
