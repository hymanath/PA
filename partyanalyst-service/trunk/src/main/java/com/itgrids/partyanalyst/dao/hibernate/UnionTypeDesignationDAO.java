package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUnionTypeDesignationDAO;
import com.itgrids.partyanalyst.model.UnionTypeDesignation;

public class UnionTypeDesignationDAO extends GenericDaoHibernate<UnionTypeDesignation, Long> implements IUnionTypeDesignationDAO{

	public UnionTypeDesignationDAO() {
		super(UnionTypeDesignation.class);
		
	}
	
	public List<Object[]> getDesignationsOfUnionType(Long uniontypeId){
		Query query = getSession().createQuery(" select model.designation.designationId,model.designation.designation " +
				" from UnionTypeDesignation model " +
				" where model.designation.isActive='Y' and model.unionTypeId=:uniontypeId ");
		query.setParameter("uniontypeId", uniontypeId);
		return query.list();
	}
}
