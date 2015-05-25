package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreFamilyInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreFamilyInfo;


public class TdpCadreFamilyInfoDAO extends GenericDaoHibernate<TdpCadreFamilyInfo, Long> implements ITdpCadreFamilyInfoDAO{

	public TdpCadreFamilyInfoDAO() {
		super(TdpCadreFamilyInfo.class);
	}
	
	public Integer deleteFamilyInfoByCadre(Long tdpCadreId)
	{
		
		Query query = getSession().createQuery("delete from TdpCadreFamilyInfo model where model.tdpCadre.tdpCadreId =:tdpCadreId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.executeUpdate();
	}

}
