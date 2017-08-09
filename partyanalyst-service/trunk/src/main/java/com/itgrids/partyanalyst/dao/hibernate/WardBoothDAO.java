package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IWardBoothDAO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.WardBooth;

public class WardBoothDAO  extends GenericDaoHibernate<WardBooth, Long> implements IWardBoothDAO{
	public WardBoothDAO() {
		super(WardBooth.class);
		
	}
	
	public Long getWardsCount(Long wardId,Long boothId,Long publicationDateId){
		Query query = getSession().createQuery("select count(model.wardBoothId) from WardBooth model where model.ward.constituencyId =:wardId " +
				" and model.booth.boothId = :boothId and model.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("wardId", wardId);
		query.setParameter("boothId", boothId);
		query.setParameter("publicationDateId", publicationDateId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getWardBothData(Long publicationDateId,Long constituencyId){
		Query query = getSession().createQuery("select model.wardBoothId,model.ward.constituencyId,model.booth.boothId,model.publicationDate.publicationDateId from WardBooth model" +
				"  where model.publicationDate.publicationDateId = :publicationDateId and model.constituency.constituencyId =:constituencyId ");
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Constituency> getWardDetailsByBoothId(Long boothId){
		Query query = getSession().createQuery("select model.ward from WardBooth model where model.booth.boothId = :boothId ");
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
}
