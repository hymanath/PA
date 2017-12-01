package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILocalElectionBodyDAO;
import com.itgrids.model.LocalElectionBody;

@Repository
public class LocalElectionBodyDAO extends GenericDaoHibernate<LocalElectionBody, Long> implements ILocalElectionBodyDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public LocalElectionBodyDAO() {
		super(LocalElectionBody.class);
	}
	
	public List<Object[]> getLocalElectionBodyByTehsilId(List<Long> tehsilIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.localElectionBodyId,model.name,model.electionType.electionType from LocalElectionBody model "
				+ "where model.tehsilId in (:tehsilIds) order by model.name ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameterList("tehsilIds", tehsilIds);
		return query.list();
	}
}
