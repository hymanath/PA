package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreCasteInfoDAO extends GenericDaoHibernate<TdpCadreCasteInfo,Long > implements ITdpCadreCasteInfoDAO{

	public TdpCadreCasteInfoDAO() {
		super(TdpCadreCasteInfo.class);
	}

	public int deleteTdpCadreCasteInfoTableBeforeUpdate( Long enrollmentYearId)
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_caste_info where tdp_cadre_enrollment_id=:enrollmentYearId ");
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();		
		return c;
	}
	
	public Integer updateTdpCadreCasteInfoTableByScheduler(String locationType, Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_caste_info (location_type,location_id,caste_state_id,count,caste_category_id,tdp_cadre_enrollment_id)  ");
		
		if(locationType != null && !locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" select '"+locationType+"', ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" select 'LocalBody', ");
		}
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" UA.constituency_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" UA.district_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" UA.panchayat_id,");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" UA.ward,");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" UA.tehsil_id , ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" UA.local_election_body, ");
		}
		queryStr.append(" CS.caste_state_id,count(CS.caste_state_id),CC.caste_category_id ,"+enrollmentYearId+"  from  tdp_cadre_enrollment_year TC1,tdp_cadre TC, user_address UA, caste_state CS, caste_category_group CCG, caste_category CC " +
				" where TC.address_id = UA.user_address_id and TC.caste_state_id = CS.caste_state_id and CS.caste_category_group_id = CCG.caste_category_group_id and " +
				" CCG.caste_category_id = CC.caste_category_id " +
				" and TC.enrollment_year = 2014 and TC.is_deleted = 'N' and TC1.tdp_cadre_id = TC.tdp_cadre_id and TC1.is_deleted='N' ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and UA.constituency_id  is not null group by CS.caste_state_id,UA.constituency_id order by UA.constituency_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and UA.district_id  is not null group by CS.caste_state_id,UA.district_id order by UA.district_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" and UA.panchayat_id  is not null group by CS.caste_state_id,UA.panchayat_id order by UA.panchayat_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" and UA.ward  is not null group by CS.caste_state_id,UA.ward order by UA.ward,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" and UA.tehsil_id  is not null and UA.local_election_body  is null group by CS.caste_state_id,UA.tehsil_id order by UA.tehsil_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" and UA.local_election_body  is not null group by CS.caste_state_id,UA.local_election_body order by UA.local_election_body,CC.caste_category_id   ");
		}
		queryStr.append(" and TC1.enrollment_year_id=:enrollmentYearId ");
		Query query = getSession().createSQLQuery(queryStr.toString());
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId, Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		boolean isStateWise = false;
		StringBuilder str  = new StringBuilder();
		if(locationType != null)
		{
			str.append(" select distinct model.locationId,model.count ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" , model2.name from TdpCadreCasteInfo model, Constituency model2 where model2.constituencyId = model.locationId   and model2.district.districtId in (:locationIdsList) and model.locationType like '%Constituency%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				str.append(", model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId and  model.locationId in (:locationIdsList) and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" ,model2.tehsilName from TdpCadreCasteInfo model,Tehsil model2,Booth B where model2.tehsilId = model.locationId and model2.tehsilId = B.tehsil.tehsilId and model.locationType like '%Tehsil%' ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.constituency.constituencyId in (:locationIdsList)  ");
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.tehsilName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" ,model2.panchayatName from TdpCadreCasteInfo model,Panchayat model2,Booth B where model2.panchayatId = model.locationId and model2.panchayatId = B.panchayat.panchayatId and model.locationType like '%Panchayat%' ");
				str.append(" and B.publicationDate.publicationDateId = 11 and B.tehsil.tehsilId in (:locationIdsList)  ");
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.panchayatName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" ,model2.name from TdpCadreCasteInfo model,LocalElectionBody model2,Booth B where model2.localElectionBodyId = model.locationId and model2.localElectionBodyId = B.localBody.localElectionBodyId and model.locationType like '%LocalBody%' ");
				str.append(" and B.publicationDate.publicationDateId = 11 and B.localBody.localElectionBodyId in (:locationIdsList)  ");
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				str.append(" ,model2.name from TdpCadreCasteInfo model,Constituency model2 where model2.constituencyId = model.locationId and model.locationType like '%Ward%' ");
				str.append(" and model.locationId in (:locationIdsList)  ");	
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.name ");
			}
			else if(stateId != null && stateId.longValue() == 0L) //AP & TS
			{
				isStateWise = true;
				str.append(" , model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId  and model.locationId between 1 and 23 and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(stateId != null && stateId.longValue() == 1L) //AP
			{
				isStateWise = true;
				str.append(" , model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId  and model.locationId between 11 and 23 and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(stateId != null && stateId.longValue() == 2L) //TS
			{
				isStateWise = true;
				str.append(" , model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId  and model.locationId between 1 and 10 and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			
			
			queryStr.append(str.toString());
			
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("casteStateId", casteStateId);
			
			if(!isStateWise && (locationIdsList != null && locationIdsList.size()>0))
			{
				query.setParameterList("locationIdsList", locationIdsList);
			}
			
			return query.list();
		}
		else
		{
			return null;
		}
	}
}
