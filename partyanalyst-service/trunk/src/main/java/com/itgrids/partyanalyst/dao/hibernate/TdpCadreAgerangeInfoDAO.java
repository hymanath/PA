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

	public int deleteTdpCadreReageRangeInfoTableBeforeUpdate(Long enrollmentYearId)
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_agerange_info where tdp_cadre_enrollment_id = :enrollmentYearId");
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();		
		return c;
	}

	public Integer updateTdpCadReageRangeInfoTableByScheduler(String locationType,Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_agerange_info (location_type,location_id,age_range_id,count,tdp_cadre_enrollment_id)   ");
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
		
		queryStr.append(" TC.age_range_id,count(*) ,"+enrollmentYearId+" from tdp_cadre_enrollment_year TC1, tdp_cadre TC, user_address UA " +
				" where TC.address_id = UA.user_address_id  and TC.enrollment_year = 2014 and TC.is_deleted = 'N' and TC.age_range_id is not null " +
				" and TC1.tdp_cadre_id = TC.tdp_cadre_id and TC1.is_deleted='N' ");
		
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
			queryStr.append(" and UA.tehsil_id  is not null and UA.local_election_body  is null group by UA.tehsil_id,TC.age_range_id order by UA.tehsil_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" and UA.local_election_body  is not null group by UA.local_election_body,TC.age_range_id order by UA.local_election_body ");
		}		
		queryStr.append(" and TC1.enrollment_year_id=:enrollmentYearId ");
		Query query = getSession().createSQLQuery(queryStr.toString());
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();
		return c;
	}
}
