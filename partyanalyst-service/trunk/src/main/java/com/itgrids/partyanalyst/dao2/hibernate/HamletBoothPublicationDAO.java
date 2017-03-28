package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

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

	/**
	 * 	This method is used for getting the hamlet name based on booth id
	 * @author Prasad Thiragabathina
	 * @param Long boothId
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	public List<HamletBoothPublication> getHameletDetailsByBoothId(Long boothId) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("select model from HamletBoothPublication model where model.booth.boothId = ?",boothId);
	}
		
	
	
	public List<Object> getBoothsIds(Long panchayatId,Long publicationDateId){
		Query query = getSession().createQuery("select  distinct h.booth.boothId  from HamletBoothPublication h  " +
                                             " where h.hamlet.hamletId =:id and h.booth.publicationDate.publicationDateId = :publicationDateId ") ;
		                   query.setParameter("publicationDateId", publicationDateId);
		                   query.setParameter("id", panchayatId);
		                   return query.list();
	             }
	public List<Object[]> getBoothsInHamlet(Long panchayatId,Long publicationDateId){
		Query query = getSession().createQuery("select  distinct h.booth.boothId,h.booth.partNo  from HamletBoothPublication h  " +
                                             " where h.hamlet.hamletId =:id and h.booth.publicationDate.publicationDateId = :publicationDateId ") ;
		                   query.setParameter("publicationDateId", publicationDateId);
		                   query.setParameter("id", panchayatId);
		                   return query.list();
	             }
}
