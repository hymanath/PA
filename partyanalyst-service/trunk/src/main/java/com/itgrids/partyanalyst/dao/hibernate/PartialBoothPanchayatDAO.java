package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public class PartialBoothPanchayatDAO extends
GenericDaoHibernate<PartialBoothPanchayat, Long> implements IPartialBoothPanchayatDAO{
	
	public PartialBoothPanchayatDAO(){
		super(PartialBoothPanchayat.class);		
	}

	@SuppressWarnings("unchecked")
	public List<Long> getPartialBoothPanchayatDetailsByPanchayatId(Long panchayatId,Long publicationDateId) {
		String queryString = "select distinct(model.booth.boothId) from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId  and model.booth.publicationDate.publicationDateId = :publicationDateId" ;
				//" and model.booth.isPartial like '%y%'";
		Query query = getSession().createQuery(queryString);
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
	return query.list();
	}
	
	public List<PartialBoothPanchayat> getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(Long panchayatId,Long publicationDateId) {
		String queryString = " from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId and model.booth.publicationDate.publicationDateId =:publicationDateId " ;
		Query query = getSession().createQuery(queryString);
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
	return query.list();
	}
	
	public List<Long> getPartialBoothDetailsByPanchayatIdsAndPublicationDateId(List<Long> panchayatIds , Long publicationDateId)
	{
		
		String queryString = " select model.booth.boothId from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId and model.booth.publicationDate.publicationDateId in(:publicationDateIds)";
		
			Query query = getSession().createQuery(queryString);
			
			query.setParameterList("panchayatIds", panchayatIds);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
	}
}
