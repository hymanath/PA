package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingIvrStatusDAO;
import com.itgrids.partyanalyst.model.PartyMeetingIvrStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingIvrStatusDAO extends GenericDaoHibernate<PartyMeetingIvrStatus, Long> implements IPartyMeetingIvrStatusDAO{

	public PartyMeetingIvrStatusDAO() {
		super(PartyMeetingIvrStatus.class);
	}

	 public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate)
		{
			StringBuilder queryStr = new StringBuilder();

				queryStr.append(" select count(model.party_meeting_id) from party_meeting_ivr_status model2,party_meeting model where model.party_meeting_id = model2.party_meeting_id and  model.is_active='Y' ");
				
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.location_value in (:committeeLevelValueList) ");
				
				if(fromDate != null && toDate != null)
				{
					//queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
					queryStr.append(" and (model.start_date between :fromDate and :toDate) ");
				}
				queryStr.append(" and ( model2.is_conducted_by_ivr = 'Y' or model2.is_conducted_by_pc ='Y') ");
				
			Query query = getSession().createSQLQuery(queryStr.toString());
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			
			return (BigInteger) query.uniqueResult();
		}
	    
	 
	    public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList)
		{
			StringBuilder queryStr = new StringBuilder();
			
				queryStr.append(" select date(model.start_date) , count(model.party_meeting_id) from party_meeting_ivr_status model2,party_meeting model where model.party_meeting_id = model2.party_meeting_id and  model.is_active='Y' ");
				queryStr.append("");
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.location_value in (:committeeLevelValueList) ");
				
				if(searchDatesList != null && searchDatesList.size()>0)					
					queryStr.append(" and  date(model.start_date) in (:searchDatesList) ");
				else if(fromDate != null && toDate != null)
				{
					//queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
					queryStr.append(" and (model.start_date between :fromDate and :toDate) ");
				}
				queryStr.append(" and (model2.is_conducted_by_ivr = 'Y' or model2.is_conducted_by_pc ='Y' ) ");
				queryStr.append(" group by   date(model.start_date) ");
							
			Query query = getSession().createSQLQuery(queryStr.toString());
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			if(searchDatesList != null && searchDatesList.size()>0)	
				query.setParameterList("searchDatesList", searchDatesList);
			else if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}

			return query.list();
		}
}
