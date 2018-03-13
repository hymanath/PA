package com.itgrids.dao.impl;

import java.util.List;

import org.apache.solr.search.QueryUtils;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ISubDivisionDAO;
import com.itgrids.model.SubDivision;

@Repository
public class SubDivisionDAO extends GenericDaoHibernate<SubDivision, Long> implements ISubDivisionDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public SubDivisionDAO() {
		super(SubDivision.class);
	}
	
	public List<Object[]> getSubDivisionIdAndNameByIds(List<Long> subDivisionIds){
		Query query = getSession().createQuery(" select model.subDivisionId,model.subDivisionName "
				+ " from SubDivision model "
				+ " where model.subDivisionId in (:subDivisionIds) ");
		query.setParameterList("subDivisionIds", subDivisionIds);
		return query.list();
	}
	
	public List<Object[]> getSubDivisionsOfDivision(Long divisionId){
		Query query = getSession().createQuery(" select model.subDivisionId,model.subDivisionName "
				+ " from SubDivision model "
				+ " where model.divisionId=:divisionId ");
		query.setParameter("divisionId", divisionId);
		return query.list();
	}
}
