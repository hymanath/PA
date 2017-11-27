package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRwsWorkLocationDAO;
import com.itgrids.model.RwsWorkLocation;

@Repository
public class RwsWorkLocationDAO  extends GenericDaoHibernate<RwsWorkLocation, Long> implements IRwsWorkLocationDAO {

		@Autowired
		SessionFactory sessionFactory;
		public RwsWorkLocationDAO() {
			super(RwsWorkLocation.class);
		}
		@Override
		public RwsWorkLocation getWorkdetailsByHabAndId(int workId,String habCode) {
			
			Query query= sessionFactory.getCurrentSession().createQuery("from RwsWorkLocation where rwsWorkId=:workId and habitationCode =:habCode");
			query.setParameter("workId", workId);
			query.setParameter("habCode", habCode);
			RwsWorkLocation workLocation = (RwsWorkLocation) query.uniqueResult();
			
			return workLocation;
		}

}
