package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAreaInchargeLocationDAO;
import com.itgrids.partyanalyst.model.AreaInchargeLocation;

public class AreaInchargeLocationDAO extends GenericDaoHibernate<AreaInchargeLocation, Long> implements IAreaInchargeLocationDAO {

	public AreaInchargeLocationDAO(){
		super(AreaInchargeLocation.class);
	}
	
	
	/*public int getLocationIdsOfBooths(List<Long> boothIds ){
		Query query = getSession().createQuery(" update AreaInchargeLocation model " +
				" set model.isAssinged = 'Y',model.isDeleted ='N' where " +
				"  model.address.booth.boothId in(:boothIds) ");
		
		query.setParameterList("boothIds", boothIds);
		return query.executeUpdate();
	}*/
	public Long getLocationIdsOfBooths(Long boothId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIL.areaInchargeLocationId from  AreaInchargeLocation AIL  where  " );
		if(boothId != null && boothId.longValue()>0l){
			sb.append(" AIL.address.booth.boothId =:boothId "); 
		}
		Query query = getSession().createQuery(sb.toString());
		if(boothId != null && boothId.longValue()>0l){
			query.setParameter("boothId", boothId);
		}
		return (Long)query.uniqueResult();
		
	}
	
	public List<Object[]> getAssignedAndUnAssignedBooths(String status){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct AIL.areaInchargeLocationId,AIL.isAssinged," +
				" AIL.address.panchayat.panchayatId,panchayat.panchayatName," +
				" tehsil.tehsilId,AIL.address.tehsil.tehsilName  " +
				" from  AreaInchargeLocation AIL " +
				" left join AIL.address.panchayat panchayat " +
				" left join AIL.address.tehsil tehsil  " +
				" " );
		if(status != null && !status.isEmpty()){
			sb.append(" where AIL.isAssinged =:status ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(status != null && !status.isEmpty()){
			query.setParameter("status", status);
		}
		return query.list();
	}
	
}
