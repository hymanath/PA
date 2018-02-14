package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDeptDesignationPrePostStatusDetailsDAO;
import com.itgrids.model.PmDeptDesignationPrePostStatusDetails;
@Repository
public class PmDeptDesignationPrePostStatusDetailsDAO extends GenericDaoHibernate<PmDeptDesignationPrePostStatusDetails, Long>implements IPmDeptDesignationPrePostStatusDetailsDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmDeptDesignationPrePostStatusDetailsDAO() {
		super(PmDeptDesignationPrePostStatusDetails.class);
		// TODO Auto-generated constructor stub
	}

	public List<Long> getItSelfandPoststatusDetail(List<Long> statusIdsList,Long pmOfficerDesignationId){
		StringBuilder str = new StringBuilder();
		/*str.append("select distinct model.pmPostStatusId  from PmDeptDesignationPrePostStatusDetails model where model.pmPreStatusId in (:statusIdsList) " +
				//" and model.pmOfficerDesignationId = :pmOfficerDesignationId and model.isDeleted='N' " +
				" and  model.isDeleted='N'  and model.pmPostStatus is not null "+
				" and model.pmPostStatus.isDeleted='N' order by model.pmPostStatus.orderNo ");
		*/
		str.append("SELECT distinct model.pm_post_status_id as statusId from pm_dept_designation_pre_post_status_details model, pm_status model2 where " +
				" model.pm_officer_designation_id = :pmOfficerDesignationId and model.pm_pre_status_id in (:statusIdsList) and model.is_deleted='N' and model.pm_post_status_id = model2.pm_status_id" +
				"  order by model2.order_no  ");
		Query query = getSession().createSQLQuery(str.toString()).addScalar("statusId", StandardBasicTypes.LONG);
		query.setParameterList("statusIdsList", statusIdsList);
		query.setParameter("pmOfficerDesignationId", pmOfficerDesignationId);
		return query.list();
	}
	
	public List<Object[]> getMyPreStatusDetail(List<Long>  pmOfficerDesignationIdsList){
		StringBuilder str = new StringBuilder();
		str.append("SELECT distinct model2.pm_status_id as statusId,model2.status as status from pm_dept_designation_pre_post_status_details model, pm_status model2 where " +
				" model.pm_officer_designation_id in (:pmOfficerDesignationIdsList) and model.is_deleted='N' and model.pm_pre_status_id = model2.pm_status_id" +
				"  order by model2.order_no  ");
		Query query = getSession().createSQLQuery(str.toString()).addScalar("statusId", StandardBasicTypes.LONG).addScalar("status", StandardBasicTypes.STRING);
		query.setParameterList("pmOfficerDesignationIdsList", pmOfficerDesignationIdsList);
		return query.list();
	}
	
	public List<Long> getAssignedDesignationsForStatusIdsList(List<Long> statusIdsList){
		StringBuilder str = new StringBuilder();
		str.append("SELECT distinct model.pm_officer_designation_id as pm_officer_designation_id from pm_dept_designation_pre_post_status_details model, pm_status model2 where " +
				" model.pm_pre_status_id in (:statusIdsList) and model.is_deleted='N' and model.pm_post_status_id = model2.pm_status_id" +
				"  order by model2.order_no  ");
		Query query = getSession().createSQLQuery(str.toString()).addScalar("pm_officer_designation_id", StandardBasicTypes.LONG);
		query.setParameterList("statusIdsList", statusIdsList);
		return query.list();
	}
	
}
