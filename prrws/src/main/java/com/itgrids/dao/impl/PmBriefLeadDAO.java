package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmBriefLeadDAO;
import com.itgrids.model.PmBriefLead;
@Repository
public class PmBriefLeadDAO extends GenericDaoHibernate<PmBriefLead, Long> implements IPmBriefLeadDAO {
	@Autowired
	SessionFactory sessionFactory; 
	public PmBriefLeadDAO() {
		super(PmBriefLead.class);
	}
	public List<Object[]> gePmBriefLeadDetailsList(Long deptDesignationId){
		Query qry = getSession().createQuery(" select distinct model.pmBriefLead.pmBriefLeadId,model.pmBriefLead.shortName from PmDepartmentDesignationBriefLeads model " +
				" where model.pmBriefLead.isDeleted='N' and model.isDeleted ='N' and model.pmDepartmentDesignationId=:deptDesignationId " +
				" order by model.pmBriefLead.orderNo asc ");
		qry.setParameter("deptDesignationId", deptDesignationId);
		return qry.list();
	}
	
	
	public List<Object[]> getAllPmBriefLeadDetailsList(){
		Query qry = getSession().createQuery(" select distinct model.pmBriefLeadId,model.shortName from PmBriefLead model " +
				" where model.isDeleted='N' and model.parentBriefLeadId is null order by model.orderNo asc ");
		return qry.list();
	}
	
	
	public List<Object[]> getAllPmBriefLeadDetailsDeptWiseList(List<Long> deptIdsList,List<Long> statusIds,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmBriefLeadId,model.pmBriefLead.shortName from PmSubWorkDetails model " +
				" where model.isDeleted='N' and model.pmBriefLead.parentBriefLeadId is null and  model.pmBriefLead.isDeleted='N' " );
		if(deptIdsList != null && deptIdsList.size() >0 && !deptIdsList.isEmpty()){
			sb.append(" and model.pmDepartmentId in(:deptIdsList) ");
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append(" and model.pmStatusId in (:statusIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		sb.append(" order by model.pmBriefLead.orderNo asc ");
		Query qry = getSession().createQuery(sb.toString());
		if(deptIdsList != null && deptIdsList.size() >0 && !deptIdsList.isEmpty()){
			qry.setParameterList("deptIdsList", deptIdsList);
		}
		if(statusIds != null && statusIds.size() >0){
			qry.setParameterList("statusIds", statusIds);
		}
		if(fromDate != null && toDate != null){
			qry.setParameter("fromDate", fromDate);
			qry.setParameter("toDate", toDate);
		}
		return qry.list();
 }       
}

