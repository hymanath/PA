package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalDesignation;

public class SelfAppraisalDesignationDAO extends GenericDaoHibernate<SelfAppraisalDesignation, Long> implements ISelfAppraisalDesignationDAO {
	public SelfAppraisalDesignationDAO() {
		super(SelfAppraisalDesignation.class);
	}

	public List<Object[]> getDesiganationList() {
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.selfAppraisalDesignationId,model.designation from SelfAppraisalDesignation model where model.isActive='Y' order by  model.orderNo ");
		  Query query = getSession().createQuery(queryStr.toString());
		  return query.list();
	}
	public List<Object[]> getDesignationDtls(List<Long> desigList){
		StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select model.selfAppraisalDesignationId,model.designation,model.orderNo from SelfAppraisalDesignation model where model.isActive='Y' " +
		  		" and model.selfAppraisalDesignationId in (:desigList) " +
		  		" order by model.orderNo ");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameterList("desigList", desigList);
		  return query.list();
	}
}
