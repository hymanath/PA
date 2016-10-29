package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITabUserLocationDetailsDAO;
import com.itgrids.partyanalyst.model.TabUserLocationDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class TabUserLocationDetailsDAO extends GenericDaoHibernate<TabUserLocationDetails,Long> implements ITabUserLocationDetailsDAO {
	
	public TabUserLocationDetailsDAO() {
		super(TabUserLocationDetails.class);
	}
	
	/*public List<Object[]> getLattitudeLangitudeOfTabUser(Long locationId,Date startDate,Date endDate,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT tui.tab_user_info_id as tabUserInfoId,tui.name as name,tui.mobile_no as mobileNo" +
				",tuld.latitude as latitude,tuld.longititude as longititude,tuld.survey_time as surveyTime " +
				" FROM " +
				" tab_user_location_details tuld,booth booth,tab_user_info tui,district d,constituency c  " +
					"  join ( select tui.tab_user_info_id as tab_user_info_id,max(tuld.survey_time) as survey_time " +
					" from " +
					" tab_user_location_details tuld,booth booth,tab_user_info tui,district d,constituency c " +
					" where " +
					" tuld.booth_id = booth.booth_id " +
					" and tui.tab_user_info_id = tuld.tab_user_info_id and tuld.constituency_id = c.constituency_id and d.district_id=c.district_id " );
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and d.district_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and c.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and booth.tehsil_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
				str.append("  and booth.panchayat_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				str.append(" and booth.local_election_body_id = :locationId ");
			}
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(tuld.survey_time) between :startDate and :endDate ");
		}
		
		str.append(" group by tui.tab_user_info_id) as result " +
				" WHERE " +
				" tuld.booth_id = booth.booth_id " +
				" and tui.tab_user_info_id = tuld.tab_user_info_id and " +
				" tuld.constituency_id = c.constituency_id and d.district_id=c.district_id " +
				" and result.tab_user_info_id = tui.tab_user_info_id " +
				" and result.survey_time = tuld.survey_time");
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and d.district_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and ua.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and  ua.tehsil_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
				str.append("  and ua.panchayat_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				str.append(" and ua.local_election_body = :locationId ");
			}
		}
			
			if(startDate !=null && endDate !=null){
				str.append(" and date(tuld.survey_time) between :startDate and :endDate ");
			}
			str.append(" group by tui.tab_user_info_id;");
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("tabUserInfoId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("mobileNo", Hibernate.STRING)
				.addScalar("latitude", Hibernate.STRING)
				.addScalar("longititude", Hibernate.STRING)
				.addScalar("surveyTime", Hibernate.STRING);
		
		query.setParameter("locationId", locationId);
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		
		return query.list();
		
	}*/
public List<Object[]> getLattitudeLangitudeOfTabUser(Long locationId,Date startDate,Date endDate,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT tui.tab_user_info_id as tabUserInfoId,tui.name as name,tui.mobile_no as mobileNo" +
				",tuld.latitude as latitude,tuld.longititude as longititude,max(tuld.survey_time) as surveyTime " +
				" FROM " +
				" tab_user_location_details tuld,booth booth,tab_user_info tui,district d,constituency c  ");
		
				str.append("  WHERE " +
				" tuld.booth_id = booth.booth_id " +
				" and tui.tab_user_info_id = tuld.tab_user_info_id and " +
				" tuld.constituency_id = c.constituency_id and d.district_id=c.district_id " );
				
				if(startDate !=null && endDate !=null){
					str.append(" and date(tuld.survey_time) between :startDate and :endDate ");
				}
				
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and d.district_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and ua.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and  ua.tehsil_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
				str.append("  and ua.panchayat_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				str.append(" and ua.local_election_body = :locationId ");
			}
		}
			
			
			str.append(" group by tui.tab_user_info_id ");
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("tabUserInfoId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("mobileNo", Hibernate.STRING)
				.addScalar("latitude", Hibernate.STRING)
				.addScalar("longititude", Hibernate.STRING)
				.addScalar("surveyTime", Hibernate.STRING);
		
		query.setParameter("locationId", locationId);
		
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		
		return query.list();
		
	}
}
