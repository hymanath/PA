package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IHamletBoothPublicationDAO;
import com.itgrids.partyanalyst.model.HamletBoothPublication;

public class HamletBoothPublicationDAO extends
		GenericDaoHibernate<HamletBoothPublication, Long> implements
		IHamletBoothPublicationDAO {
	public HamletBoothPublicationDAO(){		
		super(HamletBoothPublication.class);		
	}	
	
	public List<Object[]> getBoothsInPanchayatByPublicationId(Long panchayatId,Long publicationDateId){
		Query query = getSession().createQuery("select distinct model.booth.boothId,model.booth.partNo from HamletBoothPublication model where model.booth.publicationDate.publicationDateId = :publicationDateId and " +
				" model.hamlet.hamletId in(select distinct model1.hamlet.hamletId from PanchayatHamlet model1 where model1.panchayat.panchayatId =:panchayatId )") ;
		  query.setParameter("publicationDateId", publicationDateId);
		  query.setParameter("panchayatId", panchayatId);
		  return query.list();
	}
}
