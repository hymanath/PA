package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreSmsLeaderLocationDAO;
import com.itgrids.partyanalyst.model.TdpCadreSmsLeaderLocation;

public class TdpCadreSmsLeaderLocationDAO extends GenericDaoHibernate<TdpCadreSmsLeaderLocation, Long> implements ITdpCadreSmsLeaderLocationDAO{

	public TdpCadreSmsLeaderLocationDAO() {
		super(TdpCadreSmsLeaderLocation.class);
	}
	
	public List<Object[]> locationWiseRegistrationSMSTracking(){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select TCSL.mobile_no as mobileNo,TCSLL.location_scope_id as locationScopeId," +
				" TCSLL.location_value as locationValue," +
				" TCSLL.location_name as locationName," +
				" TCTC.target_count as targetCount," +
				" TCLI.type as type," +
				" TCLI.cadre_2016 as cadre," +
				" TCSL.name as personName ," +
				" TCSL.is_group as isGroup," +
				" TCLI.cadre_2014 as 2014Cadre," +
				" TCLI.renewal_cadre as renewel " +
				" " +
				" FROM " +
				" tdp_cadre_sms_leader_location TCSLL,tdp_cadre_sms_leader TCSL, " +
				" tdp_cadre_target_count  TCTC,tdp_cadre_location_info TCLI " +
				" " +
				" WHERE" +
				" " +
				" TCSLL.tdp_cadre_sms_leader_id = TCSL.tdp_cadre_sms_leader_id " +
				" and TCSLL.location_scope_id = TCTC.location_scope_id " +
				" and TCSLL.location_value = TCTC.location_value " +
				" and TCLI.location_scope_id = TCSLL.location_scope_id " +
				" and TCLI.location_value = TCSLL.location_value " +
				" and TCSL.is_deleted = 'N' " +
				" and TCSLL.is_deleted = 'N' " +
				" and TCTC.is_deleted = 'N' " +
				" and TCTC.enrollment_year_id = 4 " );
		
		
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("mobileNo", Hibernate.STRING)
				.addScalar("locationScopeId",  Hibernate.LONG)
				.addScalar("locationValue", Hibernate.LONG)
				.addScalar("locationName", Hibernate.STRING)				
				.addScalar("targetCount", Hibernate.LONG)
				.addScalar("type", Hibernate.STRING)
				.addScalar("cadre", Hibernate.LONG)
				.addScalar("personName", Hibernate.STRING)
				.addScalar("isGroup", Hibernate.STRING)
				.addScalar("2014Cadre", Hibernate.LONG)
				.addScalar("renewel", Hibernate.LONG);
				
		return query.list();
		
	}

}
