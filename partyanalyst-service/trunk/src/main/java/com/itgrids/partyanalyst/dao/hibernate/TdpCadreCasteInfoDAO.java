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
	
	public Integer updateTdpCadreCasteInfoTableByScheduler(String cadreType, String locationType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_caste_info (location_type,location_id,caste_state_id,count,caste_category_id)  ");
		queryStr.append(" select '"+locationType+"', ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" UA.constituency_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" UA.district_id, ");
		}
		queryStr.append(" TC.caste_state_id,count(*),CC.caste_category_id from tdp_cadre TC, user_address UA, caste_state CS, caste_category_group CCG, caste_category CC " +
				" where TC.address_id = UA.user_address_id and TC.caste_state_id = CS.caste_state_id and CS.caste_category_group_id = CCG.caste_category_group_id and " +
				" CCG.caste_category_id = CC.caste_category_id " +
				" and TC.enrollment_year = 2014 and TC.is_deleted = 'N' ");
		
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
}
