package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmGrantDAO;
import com.itgrids.model.PmGrant;

@Repository
public class PmGrantDAO extends GenericDaoHibernate<PmGrant, Long> implements IPmGrantDAO {
	@Autowired
	SessionFactory sessionFactory; 
	public PmGrantDAO() {
		super(PmGrant.class);
		
	}

public List<Object[]> getPmGrantList(){
		
		Query qry = getSession().createQuery(" select model.pmGrantId,model.pmGrantName from  PmGrant model where model.isDeleted ='N' order by model.orderNo asc ");
		return qry.list();
	}

}
