package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreInfo;
import com.itgrids.partyanalyst.utils.IConstants;


public class TdpCadreInfoDAO extends GenericDaoHibernate<TdpCadreInfo, Long> implements ITdpCadreInfoDAO{

	public TdpCadreInfoDAO() {
		super(TdpCadreInfo.class);
	}
	public Integer deleteTdpCadreInfoTableBeforeUpdate()
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_info ");		
		int c = query.executeUpdate();		
		return c;
	}
	
	public Integer updateTdpCadreInfoTableByScheduler(String cadreType, String locationType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_info (location_type,location_id,type,count)  ");
		queryStr.append(" select '"+locationType+"', ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" UA.constituency_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" UA.district_id, ");
		}
		
		queryStr.append(" '"+cadreType+"',count(*) from tdp_cadre TC, user_address UA where TC.address_id = UA.user_address_id and TC.enrollment_year = 2014 and TC.is_deleted = 'N' ");

		if(cadreType != null && cadreType.equalsIgnoreCase("Printed"))
		{
			queryStr.append(" and  TC.card_number is not null and TC.constituency_id is not null ");
		}
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and UA.constituency_id  is not null group by UA.constituency_id order by UA.constituency_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and UA.district_id  is not null group by UA.district_id order by UA.district_id   ");
		}
		
		Query query = getSession().createSQLQuery(queryStr.toString());
		int c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getLocationWiseCadreRegisterCount(Set<Long> locationIds,String locationType,Long constituencyId,String type){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count
		StringBuilder str  = new StringBuilder();
		str.append(" select model.locationId,model.count from TdpCadreInfo model where ");
		if(locationIds != null && locationIds.size()>0)
		{
			str.append(" model.locationId in (:locationIds) and ");
		}
		str.append(" model.locationType = :locationType and model.type = :type ");
		queryStr.append(str.toString());
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("locationType", locationType);
		query.setParameter("type", type);
		if(locationIds != null && locationIds.size()>0)
		{
			query.setParameterList("locationIds", locationIds);
		}
		
		
		return query.list();
	}
	
	public Long getTdpCadreCountForLocations(String userAccessType,List<Long> locationIdsList,String type,String locationType){
		StringBuilder str = new StringBuilder();
		str.append("select sum(model.count) from TdpCadreInfo model where  ");
		
		if(userAccessType.equalsIgnoreCase("TS"))
		{
			str.append(" (model.locationId between 1 and 10) and ");
		}
		else if(userAccessType.equalsIgnoreCase("AP"))
		{
			str.append(" ( model.locationId between 11 and 23) and  ");
		}
	
		if(!userAccessType.equalsIgnoreCase("ALL") && userAccessType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{			
			if(locationIdsList != null && locationIdsList.size() > 0)
				str.append("  model.locationId in(:locationIdsList) and  ");
		}
		else if(userAccessType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			if(locationIdsList != null && locationIdsList.size() > 0)
				str.append(" model.locationId in(:locationIdsList) and  ");
		}
		str.append(" model.locationType = :locationType and model.type = :type ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationType", locationType);
		query.setParameter("type", type);
		if(!userAccessType.equalsIgnoreCase("ALL") && locationIdsList != null && locationIdsList.size() > 0)
			query.setParameterList("locationIdsList", locationIdsList);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,Long locationId)
	{
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count
		StringBuilder str  = new StringBuilder();
		str.append(" select model.locationId,model.count  ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			str.append(" ,model2.name from TdpCadreInfo model,Constituency model2 where model.locationId = model2.constituencyId and model.type = 'Registration' and model.locationId = :locationId and model.locationType like '%Constituency%' ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			str.append(" ,model2.name from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and model.locationId = :locationId and model.locationType like '%District%' ");
		}
		else if(stateId != null && stateId.longValue() == 0L) //AP & TS
		{
			str.append(" ,model2.name from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and model.locationId between 1 and 23 and model.locationType like '%District%' ");
		}
		else if(stateId != null && stateId.longValue() == 1L) //AP
		{
			str.append(" ,model2.name from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and model.locationId between 11 and 23 and model.locationType like '%District%' ");
		}
		else if(stateId != null && stateId.longValue() == 2L) //TS
		{
			str.append(" ,model2.name from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and model.locationId between 1 and 10 and model.locationType like '%District%' ");
		}
		
		queryStr.append(str.toString());
		
		Query query = getSession().createQuery(queryStr.toString());

		if(locationId != null && locationId.longValue() != 0L)
		{
			query.setParameter("locationId", locationId);
		}
				
		return query.list();
	}
}
