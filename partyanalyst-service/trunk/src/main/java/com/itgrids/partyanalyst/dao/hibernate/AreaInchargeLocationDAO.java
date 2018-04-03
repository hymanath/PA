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
	
	public List<Object[]> getAssignedAndUnAssignedBooths(Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct AIL.areaInchargeLocationId,AIL.isAssinged," +
				" panchayat.panchayatId,panchayat.panchayatName," +
				" tehsil.tehsilId,tehsil.tehsilName  " +
				" from  AreaInchargeLocation AIL " +
				" left join AIL.address.panchayat panchayat " +
				" left join AIL.address.tehsil tehsil  " +
				" " );
		if(levelId != null && levelValue != null && levelValue .longValue() >0l && levelId.longValue() == 3l){
			sb.append(" where AIL.address.district.districtId =:levelValue ");
		}else if(levelId != null && levelValue != null && levelValue .longValue() >0l && levelId.longValue() == 4l){
			sb.append(" where AIL.address.constituency.constituencyId =:levelValue "); 
		}
		sb.append(" group by AIL.areaInchargeLocationId order by AIL.areaInchargeLocationId ");
		Query query = getSession().createQuery(sb.toString());
		if(levelId != null && levelValue != null && levelValue .longValue() >0l && levelId.longValue() == 3l){
			query.setParameter("levelValue", levelValue);
		}else if(levelId != null && levelValue != null && levelValue .longValue() >0l && levelId.longValue() == 4l){
			query.setParameter("levelValue", levelValue);
		}
		return query.list();
	}
	public List<Object[]> getAreaInchargesStatusWiseCount(Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		  sb.append(" select distinct AIL.areaInchargeLocationId,AIL.isAssinged " );
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
		  sb.append(",AIL.address.constituency.name " );
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" ,AIL.address.district.districtName ");
		}
		  sb.append("from  AreaInchargeLocation AIL where ");
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" AIL.address.district.districtId =:levelValue "); 
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			sb.append(" AIL.address.constituency.constituencyId =:levelValue "); 
		}
		//sb.append(" group by AIL.areaInchargeLocationId order by AIL.areaInchargeLocationId ");
		Query query = getSession().createQuery(sb.toString());
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			query.setParameter("levelValue", levelValue);
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			query.setParameter("levelValue", levelValue);
		}
		return query.list();
		
	}
		
}
