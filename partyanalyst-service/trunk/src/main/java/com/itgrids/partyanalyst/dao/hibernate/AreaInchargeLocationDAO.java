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
		sb.append(" group by AIL.address.booth.partNo order by AIL.areaInchargeLocationId asc ");
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
		  sb.append(" select  AIL.address.booth.partNo,AIL.isAssinged " );
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
		  sb.append(",AIL.address.constituency.name " );
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" ,AIL.address.district.districtName ");
		}
		  sb.append("from  AreaInchargeLocation AIL where ");
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" AIL.address.district.districtId =:levelValue and "); 
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			sb.append(" AIL.address.constituency.constituencyId =:levelValue and "); 
		}
		sb.append(" AIL.address.booth.publicationDate =24 ");
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
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct AIL.areaInchargeLocationId,AIL.isAssinged, " +
				" AIL.address.constituency.constituencyId," +
				" AIL.address.constituency.name  from AreaInchargeLocation AIL  " );
		if(locationValuesSet != null && locationValuesSet.size()>0){
		if(userAccessLevelId != null && userAccessLevelId.longValue()==2l){
	         queryStr.append(" where AIL.address.state.stateId in(:locationValuesSet) ");  //0
	      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==3l){
	        queryStr.append(" where AIL.address.district.districtId in(:locationValuesSet) ");  
	      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==10l){
	          queryStr.append(" where AIL.address.parliamentConstituency.constituencyId in(:locationValuesSet) ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==4l){
	          queryStr.append(" where AIL.address.constituency.constituencyId in(:locationValuesSet)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==5l){
	         queryStr.append(" where AIL.address.tehsil.tehsilId in(:locationValuesSet) ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==7l){ //  town/division
		         queryStr.append(" where AIL.address.localElectionBody.localElectionBodyId in(:locationValuesSet)"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==6l){ 
		       queryStr.append(" where AIL.address.panchayat.panchayatId in(:locationValuesSet)"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==8l){ 
		      queryStr.append(" where AIL.address.ward.constituencyId in(:locationValuesSet)"); 
		  }
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(locationValuesSet != null && userAccessLevelId != null && locationValuesSet.size()>0 && userAccessLevelId.longValue()>0){
			query.setParameterList("locationValuesSet", locationValuesSet);
		}
		return query.list();
		
	}
	public List<Long> getBoothPortInchageLocationIds(List<String> portNos,Long levelId,Long levelValue){
		StringBuilder sb = new StringBuilder();
		sb.append(" select AIL.areaInchargeLocationId from  AreaInchargeLocation AIL  where  " );
		if(portNos != null && portNos.size()>0){
			sb.append(" AIL.address.booth.partNo in(:portNos) and AIL.address.booth.publicationDate.publicationDateId =24 and "); 
		}
		if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 3l){
			sb.append(" AIL.address.district.districtId =:levelValue "); 
		}else if(levelId != null && levelValue != null && levelValue.longValue()>0l && levelId.longValue() == 4l){
			sb.append(" AIL.address.constituency.constituencyId =:levelValue "); 
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
