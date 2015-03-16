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

	public int deleteTdpCadreCasteInfoTableBeforeUpdate()
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_agerange_info ");		
		int c = query.executeUpdate();		
		return c;
	}

	public Integer updateTdpCadreCasteInfoTableByScheduler(String cadreType, String locationType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_agerange_info (location_type,location_id,age_range_id,count)   ");
		queryStr.append(" select '"+locationType+"', ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" UA.constituency_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" UA.district_id, ");
		}
		queryStr.append(" TC.age_range_id,count(*) from tdp_cadre TC, user_address UA " +
				" where TC.address_id = UA.user_address_id  and TC.enrollment_year = 2014 and TC.is_deleted = 'N' and TC.age_range_id is not null ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and UA.constituency_id  is not null group by UA.constituency_id,TC.age_range_id order by UA.constituency_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and UA.district_id  is not null group by UA.district_id,TC.age_range_id order by UA.district_id   ");
		}
		Query query = getSession().createSQLQuery(queryStr.toString());
		int c = query.executeUpdate();
		return c;
	}
}
