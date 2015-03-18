package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreCasteInfoDAO extends GenericDaoHibernate<TdpCadreCasteInfo,Long > implements ITdpCadreCasteInfoDAO{

	public TdpCadreCasteInfoDAO() {
		super(TdpCadreCasteInfo.class);
	}

	public int deleteTdpCadreCasteInfoTableBeforeUpdate()
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_caste_info ");		
		int c = query.executeUpdate();		
		return c;
	}
	
	public Integer updateTdpCadreCasteInfoTableByScheduler(String locationType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_caste_info (location_type,location_id,caste_state_id,count,caste_category_id)  ");
		
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
		queryStr.append(" CS.caste_state_id,count(CS.caste_state_id),CC.caste_category_id from tdp_cadre TC, user_address UA, caste_state CS, caste_category_group CCG, caste_category CC " +
				" where TC.address_id = UA.user_address_id and TC.caste_state_id = CS.caste_state_id and CS.caste_category_group_id = CCG.caste_category_group_id and " +
				" CCG.caste_category_id = CC.caste_category_id " +
				" and TC.enrollment_year = 2014 and TC.is_deleted = 'N' ");
		
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
			queryStr.append(" and UA.tehsil_id  is not null group by CS.caste_state_id,UA.tehsil_id order by UA.tehsil_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" and UA.local_election_body  is not null group by CS.caste_state_id,UA.local_election_body order by UA.local_election_body,CC.caste_category_id   ");
		}
		Query query = getSession().createSQLQuery(queryStr.toString());
		int c = query.executeUpdate();
		return c;
	}
}
