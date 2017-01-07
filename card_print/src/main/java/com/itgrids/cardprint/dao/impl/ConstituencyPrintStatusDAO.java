package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IConstituencyPrintStatusDAO;
import com.itgrids.cardprint.model.ConstituencyPrintStatus;

public class ConstituencyPrintStatusDAO extends GenericDaoHibernate<ConstituencyPrintStatus, Long> implements IConstituencyPrintStatusDAO {

	public ConstituencyPrintStatusDAO(){
		super(ConstituencyPrintStatus.class);
	}
    
	public List<Long> getConstituencyPrintStatus(Long constituencyId){
		Query query = getSession().createQuery("" +
		" select model.constituencyPrintStatusId " +
		" from   ConstituencyPrintStatus model " +
		" where  model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getConstituenciesByPrintVendor(Long printVendorId){
		Query query = getSession().createQuery("" +
	    " select distinct model.constituencyId , model.constituency.name" +
	    " from ConstituencyPrintStatus model " +
	    " where model.printVendorId = :printVendorId " +
	    " order by model.constituency.name");
		query.setParameter("printVendorId",printVendorId);
		return query.list();
	}
	
	public List<Long> getConstituencyPrintStatusIds(Long printVendorId , Long constituencyId){
		Query query = getSession().createQuery("" +
		" select model.constituencyPrintStatusId " +
		" from   ConstituencyPrintStatus model " +
		" where  model.printVendorId =:printVendorId and  model.constituencyId = :constituencyId ");
		query.setParameter("printVendorId", printVendorId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getPrintStatusWiseConstituenciesCount(Long printVendorId){
		Query query = getSession().createQuery(" " +
		" select  model.printStatusId , count(distinct model.constituencyId) " +
		" from    ConstituencyPrintStatus model " +
		" where   model.printVendorId =:printVendorId " +
		" group by model.printStatusId");
		query.setParameter("printVendorId", printVendorId);
		return query.list();
	}
	
	
	
	
	
}
