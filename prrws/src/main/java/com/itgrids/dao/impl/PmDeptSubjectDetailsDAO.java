package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmDeptSubjectDetailsDAO;
import com.itgrids.model.PmDeptSubjectDetails;

@Repository
public class PmDeptSubjectDetailsDAO extends GenericDaoHibernate<PmDeptSubjectDetails, Long> implements IPmDeptSubjectDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmDeptSubjectDetailsDAO() {
		super(PmDeptSubjectDetails.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Object[]> getPmSubjectList(Long deptId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmSubjectId,model.pmSubject.subject "+
				  " from PmDeptSubjectDetails model " +
				  " where model.isDeleted = 'N' and model.pmSubject.isDeleted = 'N' ");
		if(deptId != null && deptId.longValue()>0L){
			sb.append(" and model.pmDepartmentId = :deptId   ");
		}
		sb.append("  order by model.pmSubject.subject asc   ");
		Query qry = getSession().createQuery(sb.toString());
		if(deptId != null && deptId.longValue() >0l){
			qry.setParameter("deptId", deptId);
		}
		return qry.list();
	}

}
