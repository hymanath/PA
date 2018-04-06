package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAreaInchargeLocationDAO;
import com.itgrids.partyanalyst.model.AreaInchargeLocation;

public class AreaInchargeLocationDAO extends GenericDaoHibernate<AreaInchargeLocation, Long> implements IAreaInchargeLocationDAO {

	public AreaInchargeLocationDAO(){
		super(AreaInchargeLocation.class);
	}
	
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
	//AIL.address.booth.partNo,AIL.areaInchargeLocationId
	public List<Object[]> getAssignedAndUnAssignedBooths(Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct AIL.address.booth.partNo,AIL.isAssinged," +
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
		sb.append(" group by AIL.address.booth.partNo order by AIL.address.booth.partNo ");
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
		  sb.append(" select distinct AIL.address.booth.partNo,AIL.isAssinged " );
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
	//0-locationId,1-isAssigned,2-constituencyId,3-name
	public List<Object[]> getConstituenciesBaseBoothLocationCount(Long userAccessLevelId,Set<Long> locationValuesSet){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct AIL.areaInchargeLocationId,AIL.isAssinged, " +
				" AIL.areaInchargeLocation.address.constituency.constituencyId,AIL.areaInchargeLocation.address.constituency.name" );
		
		return null;
		
	}
	public List<Long> getBoothPortInchageLocationIds(List<String> portNos,Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIL.areaInchargeLocationId from  AreaInchargeLocation AIL  where  " );
		if(portNos != null && portNos.size()>0){
			sb.append(" AIL.address.booth.partNo in(:portNos) and AIL.address.booth.publicationDate.publicationDateId =24 "); 
		}
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" and  AIL.address.district.districtId =:levelValue "); 
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			sb.append(" and AIL.address.constituency.constituencyId =:levelValue "); 
		}
		Query query = getSession().createQuery(sb.toString());
		if(portNos != null && portNos.size()>0){
			query.setParameterList("portNos", portNos);
		}
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			query.setParameter("levelValue", levelValue);
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			query.setParameter("levelValue", levelValue);
		}
		return query.list();
		
	}
	public Long getBoothPortInchageLocationId(String portNo,Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIL.areaInchargeLocationId from  AreaInchargeLocation AIL  where  " );
		if(portNo != null){
			sb.append(" AIL.address.booth.partNo =:portNo and"); 
		}
		sb.append(" AIL.isAssinged ='Y' and AIL.isDeleted ='N' and AIL.address.booth.publicationDate.publicationDateId =24");
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" and AIL.address.district.districtId =:levelValue "); 
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			sb.append(" and AIL.address.constituency.constituencyId =:levelValue "); 
		}
		Query query = getSession().createQuery(sb.toString());
		if(portNo != null){
			query.setParameter("portNo", portNo);
		}
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			query.setParameter("levelValue", levelValue);
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			query.setParameter("levelValue", levelValue);
		}
		return (Long)query.uniqueResult();
		
	}
		
}
