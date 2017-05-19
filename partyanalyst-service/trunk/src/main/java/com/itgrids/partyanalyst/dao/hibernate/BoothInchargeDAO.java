package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.model.BoothIncharge;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeDAO extends GenericDaoHibernate<BoothIncharge, Long> implements IBoothInchargeDAO{

	public BoothInchargeDAO() {
		super(BoothIncharge.class);
	}
	
	public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId){
		StringBuilder query = new StringBuilder("select model.booth.partNo, model.booth.villagesCovered, " +
					" model.booth.constituency.name, " +
					" model.booth.panchayat.panchayatName," +
					" model.tdpCadre.firstname, model.tdpCadre.mobileNo, model.tdpCadre.memberShipNo, " +
					" model.tdpCadre.image, " +
					" model.booth.tehsil.tehsilName " +
					" from BoothIncharge model " +
					" where " +
					" model.isDeleted='N' "+
					" and model.tdpCadre.isDeleted='N' and model.booth.publicationDate.publicationDateId = :publicationDate");
		if(constituencyId !=null && constituencyId.longValue() > 0)
		query.append(" and model.booth.constituency.constituencyId=:constituencyId");
		if(mandalId !=null && mandalId.longValue() > 0)
		query.append(" and model.booth.tehsil.tehsilId=:mandalId");
		if(boothId !=null && boothId.longValue() > 0)
		query.append(" and model.booth.boothId=:boothId");
		
		Query query1=getSession().createQuery(query.toString());
		query1.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
		if(constituencyId !=null && constituencyId.longValue() > 0){
			query1.setParameter("constituencyId", constituencyId);
		}
		if(mandalId !=null && mandalId.longValue() > 0){
			query1.setParameter("mandalId", mandalId);
		}
		if(boothId !=null && boothId.longValue() > 0){
			query1.setParameter("boothId", boothId);
		}
		return query1.list();
	}
	
	public List<Long> getCadreIdsForLocation(List<Long> tdpCadreIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select " +
				" model.tdpCadre.tdpCadreId " +
				" from BoothIncharge model " +
				" where model.isActive = 'Y' and model.isDeleted = 'N'");
		if(tdpCadreIds != null && tdpCadreIds.size() > 0l)
		{
			sb.append(" and model.tdpCadre.tdpCadreId in (:tdpCadreIds)");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreIds != null && tdpCadreIds.size() > 0l) 
			query.setParameterList("tdpCadreIds", tdpCadreIds);
		
		return query.list();
	}
	public BoothIncharge getExistingMember(Long tdpCadreId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select model" +
				" from BoothIncharge model" );
		
		if(type != null && type.trim().equalsIgnoreCase("removeOption"))
			sb.append(" where model.isActive = 'Y' and model.isDeleted = 'N'");
		else if(type != null && type.trim().equalsIgnoreCase("addOption"))
			sb.append(" where model.isActive = 'N' and model.isDeleted = 'N'");
		
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l){
			sb.append(" and model.tdpCadreId = :tdpCadreId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l)
			query.setParameter("tdpCadreId", tdpCadreId);
		
		return (BoothIncharge) query.uniqueResult();
		
	}
}
