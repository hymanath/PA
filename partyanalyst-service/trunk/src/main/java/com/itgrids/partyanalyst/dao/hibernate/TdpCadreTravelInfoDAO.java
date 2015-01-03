package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreTravelInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreTravelInfo;


public class TdpCadreTravelInfoDAO extends GenericDaoHibernate<TdpCadreTravelInfo, Long> implements ITdpCadreTravelInfoDAO{

	public TdpCadreTravelInfoDAO() {
		super(TdpCadreTravelInfo.class);
	}
	
	
	
	public List<TdpCadreTravelInfo> checkCustomerId(Long custId)
	{
		Query query = getSession().createQuery("select model from TdpCadreTravelInfo model where model.custId = :custId and model.isDeleted = 'N' order by model.tdpCadreTravelInfoId desc ");
		query.setMaxResults(1);
		query.setParameter("custId", custId);
		return query.list();
	}
	
	
}
