package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITdpEmailDAO;
import com.itgrids.model.TdpEmail;

@Repository
public class TdpEmailDAO extends GenericDaoHibernate<TdpEmail, Integer> implements ITdpEmailDAO {

	@Autowired
	SessionFactory sessionFactory;

	public TdpEmailDAO() {
		super(TdpEmail.class);

	}

	@SuppressWarnings("unchecked")
	public  List<Object[]> getAllTdpEmailmodel() {
		Query query = getSession().createQuery("select model.tdpEmailId, model.email from " +
				" TdpEmail model where model.isActive = 'Y' ");
		return query.list();
	}

}
