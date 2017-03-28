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
	public Integer deleteTdpCadreInfoTableBeforeUpdate(Long enrollmentYearId)
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_info where enrollment_year_id = :enrollmentYearId");
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();		
		return c;
	}
	
	public Integer updateTdpCadreInfoTableByScheduler(String cadreType, String locationType,Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_info (location_type,location_id,type,count,enrollment_year_id)  ");
		queryStr.append(" select '"+locationType+"', ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" UA.constituency_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" UA.district_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" UA.tehsil_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" UA.ward,  ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			queryStr.append(" UA.local_election_body ,  ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" UA.panchayat_id ,");
		}	
		
		queryStr.append(" '"+cadreType+"',count(*) ,"+enrollmentYearId+" from tdp_cadre_enrollment_year TC1,tdp_cadre TC, user_address UA " +
				"  where TC.address_id = UA.user_address_id and " +
				" TC.enrollment_year = 2014 and TC.is_deleted = 'N' and TC1.tdp_cadre_id = TC.tdp_cadre_id and TC1.is_deleted='N' ");

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
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" and UA.tehsil_id  is not null and UA.local_election_body  is null group by UA.tehsil_id order by UA.tehsil_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" and UA.ward  is not null group by UA.ward order by UA.ward   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		{
			queryStr.append(" and UA.local_election_body  is not null group by UA.local_election_body order by UA.local_election_body   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" and UA.panchayat_id  is not null group by UA.panchayat_id order by UA.panchayat_id   ");
		}
		
		queryStr.append(" and TC1.enrollment_year_id=:enrollmentYearId ");
		Query query = getSession().createSQLQuery(queryStr.toString());
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getLocationWiseCadreRegisterCount(Set<Long> locationIds,String locationType,Long constituencyId,String type,Long tdpCommitteeEnrollMentYearId){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count
		StringBuilder str  = new StringBuilder();
		str.append(" select model.locationId,model.count from TdpCadreInfo model where ");
		if(locationIds != null && locationIds.size()>0)
		{
			str.append(" model.locationId in (:locationIds) and ");
		}
		str.append(" model.locationType = :locationType and model.type = :type ");
		str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId "); 
		queryStr.append(str.toString());
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("locationType", locationType);
		query.setParameter("type", type);
		query.setParameter("tdpCommitteeEnrollMentYearId", tdpCommitteeEnrollMentYearId);
		if(locationIds != null && locationIds.size()>0)
		{
			query.setParameterList("locationIds", locationIds);
		}
		
		
		return query.list();
	}
	
	public Long getTdpCadreCountForLocations(String userAccessType,List<Long> locationIdsList,String type,String locationType,Long tdpCommitteeEnrollMentYearId){
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
		str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("locationType", locationType);
		query.setParameter("type", type);
		query.setParameter("tdpCommitteeEnrollMentYearId", tdpCommitteeEnrollMentYearId);
		if(!userAccessType.equalsIgnoreCase("ALL") && locationIdsList != null && locationIdsList.size() > 0)
			query.setParameterList("locationIdsList", locationIdsList);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getVoterCadreDetailsBySearchCriteria(Long stateId, String locationType,List<Long> locationIdsList,Long tdpCommitteeEnrollMentYearId)
	{
		if(locationType != null)
		{
			StringBuilder queryStr = new StringBuilder();
			boolean isStateWise = false;
			StringBuilder str  = new StringBuilder();
			str.append(" select distinct model.locationId,model.count  ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" ,model2.name from TdpCadreInfo model,Constituency model2 where model2.constituencyId = model.locationId and model.type like '%Registered%' and model.locationType like '%Constituency%' ");
				str.append(" and model2.electionScope.electionType.electionTypeId = 2  ");
				if(locationIdsList != null && locationIdsList.size() > 0)
				{
					str.append(" and model2.district.districtId in (:locationIdsList) ");
				}
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				str.append(" ,model2.districtName from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and model.locationId in (:locationIdsList) and model.locationType like '%District%' ");
				str.append(" and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.districtName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" ,model2.tehsilName from TdpCadreInfo model,Tehsil model2,Booth B where model2.tehsilId = model.locationId and model2.tehsilId = B.tehsil.tehsilId and model.locationType like '%Tehsil%' ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.constituency.constituencyId in (:locationIdsList) and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.tehsilName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" ,model2.panchayatName from TdpCadreInfo model,Panchayat model2,Booth B where model2.panchayatId = model.locationId and model2.panchayatId = B.panchayat.panchayatId and model.locationType like '%Panchayat%' ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.tehsil.tehsilId in (:locationIdsList) and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.panchayatName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" ,model2.name from TdpCadreInfo model,LocalElectionBody model2,Booth B where model2.localElectionBodyId = model.locationId and model2.localElectionBodyId = B.localBody.localElectionBodyId and model.locationType like '%LocalBody%' ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.localBody.localElectionBodyId in (:locationIdsList) and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				str.append(" ,model2.name from TdpCadreInfo model,Constituency model2 where model2.constituencyId = model.locationId and model.type like '%Registered%' and model.locationType like '%Ward%' ");
				str.append(" and model.locationId in (:locationIdsList)  ");	
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.name ");
			}
			else if(stateId != null && stateId.longValue() == 0L) //AP & TS
			{
				isStateWise = true;
				str.append(" ,model2.districtName from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and (model.locationId between 1 and 23) and model.locationType like '%District%' ");
				str.append(" and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.districtName ");
			}
			else if(stateId != null && stateId.longValue() == 1L) //AP
			{
				isStateWise = true;
				str.append(" ,model2.districtName from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and (model.locationId between 11 and 23) and model.locationType like '%District%' ");
				str.append(" and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.districtName ");
			}
			else if(stateId != null && stateId.longValue() == 2L) //TS
			{
				isStateWise = true;
				str.append(" ,model2.districtName from TdpCadreInfo model,District model2 where model.locationId = model2.districtId and (model.locationId between 1 and 10) and model.locationType like '%District%' ");
				str.append(" and model.type like '%Registered%' ");
				str.append(" and model.tdpCommitteeEnrollMentYearId = :tdpCommitteeEnrollMentYearId ");
				str.append(" order by model2.districtName ");
			}
			
			
			queryStr.append(str.toString());
			
			Query query = getSession().createQuery(queryStr.toString());

			if(!isStateWise && (locationIdsList != null && locationIdsList.size() > 0))
			{
				query.setParameterList("locationIdsList", locationIdsList);
			}
			query.setParameter("tdpCommitteeEnrollMentYearId", tdpCommitteeEnrollMentYearId);		 
			return query.list();
		}
		else
		{
			return null;
		}
	}
}
