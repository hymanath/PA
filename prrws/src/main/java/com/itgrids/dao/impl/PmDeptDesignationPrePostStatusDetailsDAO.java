package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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

	public List<Long> getItSelfandPoststatusDetail(Long statusId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.pmPostStatus.pmStatusId ,model.pmPostStatus.status  from PmDeptDesignationPrePostStatusDetails model where model.pmPreStatusId = :statusId and model.isDeleted='N' " +
				" and model.pmPostStatus.isDeleted='N' order by model.pmPostStatus.orderNo ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("statusId", statusId);
		return query.list();
	}
}
