package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRefCandidateDepartmentDAO;
import com.itgrids.model.PmRefCandidateDepartment;

@Repository
public class PmRefCandidateDepartmentDAO extends GenericDaoHibernate<PmRefCandidateDepartment, Long> implements IPmRefCandidateDepartmentDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	PmRefCandidateDepartmentDAO(){
		super(PmRefCandidateDepartment.class);
	}
	
	public List<Object[]> getPmRefCandidateDepartments(List<Long> refCandIds){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.pmRefCandidate.pmRefCandidateId ,model.pmDepartment.pmDepartmentId,model.pmDepartment.department " +
				" from PmRefCandidateDepartment model where model.isDeleted='N'  ");
		
		if(refCandIds != null && refCandIds.size() >0){
			sb.append(" and model.pmRefCandidate.pmRefCandidateId in (:refCandIds) ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(refCandIds != null && refCandIds.size() >0){
			query.setParameterList("refCandIds", refCandIds);
		}
		
		return query.list();
	}
	
	
}
