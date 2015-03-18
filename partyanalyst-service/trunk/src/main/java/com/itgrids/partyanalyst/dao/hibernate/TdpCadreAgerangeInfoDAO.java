package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreAgerangeInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreAgerangeInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreAgerangeInfoDAO extends GenericDaoHibernate<TdpCadreAgerangeInfo,Long> implements ITdpCadreAgerangeInfoDAO {

	public TdpCadreAgerangeInfoDAO() {
		super(TdpCadreAgerangeInfo.class);
	}

	public int deleteTdpCadreReageRangeInfoTableBeforeUpdate()
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_agerange_info ");		
		int c = query.executeUpdate();		
		return c;
	}

	public Integer updateTdpCadReageRangeInfoTableByScheduler(String locationType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_agerange_info (location_type,location_id,age_range_id,count)   ");
		//queryStr.append(" select '"+locationType+"', ");
		
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
		
		queryStr.append(" TC.age_range_id,count(*) from tdp_cadre TC, user_address UA " +
				" where TC.address_id = UA.user_address_id  and TC.enrollment_year = 2014 and TC.is_deleted = 'N' and TC.age_range_id is not null ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and UA.constituency_id  is not null group by UA.constituency_id,TC.age_range_id order by UA.constituency_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and UA.district_id  is not null group by UA.district_id,TC.age_range_id order by UA.district_id  ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" and UA.panchayat_id  is not null group by UA.panchayat_id,TC.age_range_id order by UA.panchayat_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" and UA.ward  is not null group by UA.ward,TC.age_range_id order by UA.ward   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" and UA.tehsil_id  is not null group by UA.tehsil_id,TC.age_range_id order by UA.tehsil_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" and UA.local_election_body  is not null group by UA.local_election_body,TC.age_range_id order by UA.local_election_body ");
		}		
		Query query = getSession().createSQLQuery(queryStr.toString());
		int c = query.executeUpdate();
		return c;
	}
}
