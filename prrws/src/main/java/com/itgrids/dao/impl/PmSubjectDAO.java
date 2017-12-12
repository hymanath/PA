package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubjectDAO;
import com.itgrids.model.PmSubject;
@Repository
public class PmSubjectDAO extends GenericDaoHibernate<PmSubject, Long> implements IPmSubjectDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmSubjectDAO() {
		super(PmSubject.class);
		
	}

	public List<Object[]> getPmSubjectList(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmSubjectId,model.subject "+
				  " from PmSubject model " +
				  " where model.parentPmSubjectId is null and model.isDeleted = 'N'  order by model.orderNo asc ");
		Query qry = getSession().createQuery(sb.toString());
		return qry.list();
	}
	
	public List<Object[]> getPmSubSubjectList(Long subjectId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmSubjectId,model.subject "+
				  " from PmSubject model " );
		sb.append( " where ");
		if(subjectId != null && subjectId.longValue() >0l)
			sb.append(" model.parentPmSubjectId= :subjectId ");
			sb.append(" and model.isDeleted = 'N' ");
		Query qry =getSession().createQuery(sb.toString());
		if(subjectId != null && subjectId.longValue() >0l){
			qry.setParameter("subjectId", subjectId);
		}
		
		return qry.list();
	}

}
