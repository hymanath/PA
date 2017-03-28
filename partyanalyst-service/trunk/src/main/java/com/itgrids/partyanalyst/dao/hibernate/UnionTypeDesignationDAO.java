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
		Query query = getSession().createQuery(" select distinct model.designation.designationId,model.designation.designation " +
				" from UnionTypeDesignation model " +
				" where model.designation.isActive='Y' and model.unionTypeId=:uniontypeId ");
		query.setParameter("uniontypeId", uniontypeId);
		return query.list();
	}
	
	public List<Object[]> getDesignationsOfTdpMemberTypeIdsList(List<Long> tdpMemberTypeIdsList){
		Query query = getSession().createQuery(" select distinct model.tdpMemberTypeId, model.designation.designationId,model.designation.designation " +
				" from UnionTypeDesignation model " +
				" where model.designation.isActive='Y' and model.tdpMemberTypeId in (:tdpMemberTypeIdsList) ");
		query.setParameterList("tdpMemberTypeIdsList", tdpMemberTypeIdsList);
		return query.list();
	}
	
	
	public List<Object[]> getDesignationsOfTdpMemberTypeId(Long tdpMemberTypeId){
		Query query = getSession().createQuery(" select distinct model.tdpMemberTypeId, model.designation.designationId,model.designation.designation " +
				" from UnionTypeDesignation model " +
				" where model.designation.isActive='Y' and model.tdpMemberTypeId =:tdpMemberTypeId ");
		query.setParameter("tdpMemberTypeId", tdpMemberTypeId);
		return query.list();
	}
}
