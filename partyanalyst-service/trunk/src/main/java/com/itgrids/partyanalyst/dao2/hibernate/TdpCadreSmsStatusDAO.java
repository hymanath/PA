package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dao.ITdpCadreSmsStatusDAO;
import com.itgrids.partyanalyst.model.TdpCadreSmsStatus;

public class TdpCadreSmsStatusDAO extends GenericDaoHibernate<TdpCadreSmsStatus, Long> implements ITdpCadreSmsStatusDAO{

	public TdpCadreSmsStatusDAO() {
		super(TdpCadreSmsStatus.class);
		
	}

	
	public List<Object[]> getTdpCadreIds()
	{
		Query query = getSession().createQuery("select model.tdpCadreSmsStatusId,model1.tdpCadreId from" +
				" TdpCadreSmsStatus model,TdpCadre model1 where model.trNumber = model1.refNo and model.mobileNo = model1.mobileNo and model.tdpCadreId is null");
		return query.list();
	}
	
	 public Integer updateTdpCadre(Long Id,Long tdpCadreId)
	 {
		 Query query = getSession().createQuery("update TdpCadreSmsStatus model set model.tdpCadreId = :tdpCadreId where model.tdpCadreSmsStatusId = :Id");
		 query.setParameter("Id", Id);
		 query.setParameter("tdpCadreId", tdpCadreId);
		return query.executeUpdate();
		 
	 }
}
