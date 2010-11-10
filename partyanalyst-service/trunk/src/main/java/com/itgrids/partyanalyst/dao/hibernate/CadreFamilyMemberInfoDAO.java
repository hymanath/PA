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
}
