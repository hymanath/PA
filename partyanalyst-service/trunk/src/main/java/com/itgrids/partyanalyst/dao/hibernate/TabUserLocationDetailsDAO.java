package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.ITabUserLocationDetailsDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
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
				" tab_user_location_details tuld,tab_user_info tui,district d,constituency c  " +
					"  join ( select tui.tab_user_info_id as tab_user_info_id,max(tuld.survey_time) as survey_time " +
					" from " +
					" tab_user_location_details tuld,tab_user_info tui,constituency c " +
					" where " +
					//" tuld.booth_id = booth.booth_id " +
					"  tui.tab_user_info_id = tuld.tab_user_info_id and tuld.constituency_id = c.constituency_id  " );
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and c.district_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and c.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and c.tehsil_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
				str.append("  and booth.panchayat_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				str.append(" and c.local_election_body_id = :locationId ");
			}
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and date(tuld.survey_time) between :startDate and :endDate ");
		}
		
		str.append(" group by tui.tab_user_info_id) as result " +
				" WHERE " +
				//" tuld.booth_id = booth.booth_id " +
				"  tui.tab_user_info_id = tuld.tab_user_info_id and " +
				" tuld.constituency_id = c.constituency_id  " +
				" and result.tab_user_info_id = tui.tab_user_info_id " +
				" and result.survey_time = tuld.survey_time");
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and c.district_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and c.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and  c.tehsil_id = :locationId ");
			}/*else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
				str.append("  and c.panchayat_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				str.append(" and c.local_election_body = :locationId ");
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
	
	public List<Object[]> getLattitudeLangitudeOfTabbUserByIds(List<Long> locationIsdList){
		StringBuilder str = new StringBuilder();
		str.append(" SELECT "
				+" distinct tuld.tab_user_info_id , "
				+"'', "
				+"'', "
				+"tuld.latitude, "
				+"tuld.longititude , "
				+"tuld.survey_time  "
				+"FROM  "
				+"	tab_user_location_details tuld "
				+" WHERE   "
				+"  tuld.tab_user_info_id is not null and tuld.tab_user_location_details_id in (:locationIsdList) ");
		
		Query query = getSession().createSQLQuery(str.toString());
		query.setParameterList("locationIsdList", locationIsdList);
		
		return query.list();
	}
	

public List<Object[]> getLattitudLangitudeOfTabUser(GISVisualizationParameterVO inputVO,Date startDate,Date endDate){
		
		String type = inputVO.getParentLocationType();
		Long locationId = inputVO.getParentLocationTypeId();
	
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT "
				+"	tuld1.tab_user_info_id as tabUserInfoId, max(tuld1.tab_user_location_details_id) as pkid"
				+" FROM  "
				+"	tab_user_location_details tuld1 " );
		if(inputVO.getChildLocationType() != null && !inputVO.getChildLocationType().equalsIgnoreCase("old"))
			str.append(" , constituency c ");
		else if(type.equalsIgnoreCase(IConstants.STATE) || type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" , constituency c ");
		
		str.append(" WHERE    "
				+"	(date(tuld1.survey_time) between :startDate and :endDate )  and   tuld1.tab_user_info_id is not null ");
		if(type.equalsIgnoreCase(IConstants.STATE) || type.equalsIgnoreCase(IConstants.DISTRICT) ){
			str.append(" and  tuld1.constituency_id = c.constituency_id ");
		}
		if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase("old") && type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.STATE)){
				if(locationId == 36L || locationId == 2L)
					str.append(" and (c.district_id  between 1 and 10 ) ");
				else if( locationId == 1L)
					str.append(" and (c.district_id between 11 and 23 ) ");
				else 
					str.append(" and (c.district_id between 1 and 23 ) ");
			}
			else if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and c.district_id = :locationId and c.state_id =1 and c.election_scope_id = 2 and c.deform_date is null ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and tuld1.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and  c.tehsil_id = :locationId ");
			}
		}
		else{
			if(locationId == 36L || locationId == 2L)
				str.append(" and (c.district_id  between 1 and 10 ) ");
			else if( locationId == 1L)
				str.append(" and (c.district_id between 11 and 23 ) ");
			else 
				str.append(" and (c.district_id between 1 and 23 ) ");
		}
		
		str.append("  and tuld1.constituency_id = c.constituency_id group by tuld1.tab_user_info_id  ");
				
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("tabUserInfoId", Hibernate.LONG)
				.addScalar("pkid", Hibernate.LONG);
			//	.addScalar("mobileNo", Hibernate.STRING)
			//	.addScalar("latitude", Hibernate.STRING)
			//	.addScalar("longititude", Hibernate.STRING)
			//	.addScalar("surveyTime", Hibernate.STRING);
		if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase("old") && 
				 type != null && !type.equalsIgnoreCase(IConstants.STATE))
				query.setParameter("locationId", locationId);
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		
		return query.list();
}
public List<Object[]> getLattitudeLangitudeOfTabUser(Long locationId,Date startDate,Date endDate,String type){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT "
				+"	tuld1.tab_user_info_id as tabUserInfoId, max(tuld1.tab_user_location_details_id) as pkid"
				+" FROM  "
				+"	tab_user_location_details tuld1 " );
		if(type.equalsIgnoreCase(IConstants.STATE) || type.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" , constituency c ");
		
		str.append(" WHERE    "
				+"	(date(tuld1.survey_time) between :startDate and :endDate )  and   tuld1.tab_user_info_id is not null ");
		if(type.equalsIgnoreCase(IConstants.STATE) || type.equalsIgnoreCase(IConstants.DISTRICT) ){
			str.append(" and  tuld1.constituency_id = c.constituency_id ");
		}
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.STATE)){
				if(locationId == 36L || locationId == 2L)
					str.append(" and (c.district_id  between 1 and 10 ) ");
				else if( locationId == 1L)
					str.append(" and (c.district_id between 11 and 23 ) ");

			}
			else if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and c.district_id = :locationId and c.state_id =1 and c.election_scope_id = 2 and c.deform_date is null ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and tuld1.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and  c.tehsil_id = :locationId ");
			}
		}
		
		str.append("  and  tuld1.constituency_id = c.constituency_id group by tuld1.tab_user_info_id  ");
				
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("tabUserInfoId", Hibernate.LONG)
				.addScalar("pkid", Hibernate.LONG);
			//	.addScalar("mobileNo", Hibernate.STRING)
			//	.addScalar("latitude", Hibernate.STRING)
			//	.addScalar("longititude", Hibernate.STRING)
			//	.addScalar("surveyTime", Hibernate.STRING);
		if(type != null && !type.equalsIgnoreCase(IConstants.STATE))
				query.setParameter("locationId", locationId);
		
		if(startDate !=null && endDate !=null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
		
		return query.list();
		
		/*
		str.append("SELECT tui.tab_user_info_id as tabUserInfoId,tui.name as name,tui.mobile_no as mobileNo" +
				",tuld.latitude as latitude,tuld.longititude as longititude,max(tuld.survey_time) as surveyTime " +
				" FROM " +
				" tab_user_location_details tuld,tab_user_info tui,constituency c  ");
		
				str.append("  WHERE " +
				"  tui.tab_user_info_id = tuld.tab_user_info_id and " +
				" tuld.constituency_id = c.constituency_id  " );
				
				if(startDate !=null && endDate !=null){
					str.append(" and date(tuld.survey_time) between :startDate and :endDate ");
				}
				
		if(type != null &&  locationId.longValue()>0L)
		{
			if(type.equalsIgnoreCase(IConstants.STATE)){
				if(locationId == 36L || locationId == 2L)
					str.append(" and c.district_id between 1 and 10 ");
				else if( locationId == 1L)
					str.append(" and c.district_id between 11 and 23 ");
				else
					str.append(" and c.state_id =1 ");
			}
			else if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and c.district_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				str.append("  and c.constituency_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.RURAL)){
				str.append(" and  c.tehsil_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.PANCHAYAT)){
				str.append("  and ua.panchayat_id = :locationId ");
			}else if(type.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				str.append(" and c.local_election_body_id = :locationId ");
			}
		}
			*/
			
			//str.append(" group by tui.tab_user_info_id ");
		
		
		
	}
	/*public List<Object[]> getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO,String type){
		
		try {
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select  ");
				
			 if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" model.constituency.district.districtId,model.constituency.district.districtName,model.tabUserInfo.tabUserInfoId,model.tabUserInfo.name,model.tabUserInfo.imgPath,model.tabUserInfo.mobileNo, count(model.tabUserLocationDetailsId) ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				queryStr.append("  model.constituency.constituencyId,model.constituency.name,model.tabUserInfo.tabUserInfoId,model.tabUserInfo.name,model.tabUserInfo.imgPath,model.tabUserInfo.mobileNo, count(model.tabUserLocationDetailsId) ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append("  model.constituency.tehsil.tehsilId,model.constituency.tehsil.tehsilName,model.tabUserInfo.tabUserInfoId,model.tabUserInfo.name,model.tabUserInfo.imgPath,model.tabUserInfo.mobileNo, count(model.tabUserLocationDetailsId) ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append("  booth.panchayat.panchayatId,booth.panchayat.panchayatName,model.tabUserInfo.tabUserInfoId,model.tabUserInfo.name,model.tabUserInfo.imgPath,model.tabUserInfo.mobileNo, count(model.tdpCadreId) ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append("  model.constituency.localElectionBody.localElectionBodyId,model.constituency.localElectionBody.name,model.tabUserInfo.tabUserInfoId,model.tabUserInfo.name,model.tabUserInfo.imgPath,model.tabUserInfo.mobileNo, count(model.tabUserLocationDetailsId) ");
			}
			 
			queryStr.append(" from ");
			
			queryStr.append(" TabUserLocationDetails model ");
			
			queryStr.append(" where ");
			if(!type.trim().equalsIgnoreCase("LastOneHr")){
				 if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
					queryStr.append("  (date(model.surveyTime) between :startDate and :endDate) ");
				}
			}else{
				queryStr.append("  (date(model.surveyTime) = :startDate) ");
			}
			
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.constituency.district.districtId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
						queryStr.append("  and model.constituency.constituencyId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append(" and  model.constituency.tehsil.tehsilId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
					queryStr.append("  and booth.panchayat.panchayatId = :parentLocationTypeId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" and model.constituency.localElectionBody.localElectionBodyId = :parentLocationTypeId ");
				}
			}
			
			queryStr.append(" group by " );
			if(!type.trim().equalsIgnoreCase("LastOneHr")){
			queryStr.append(" model.tabUserInfo.tabUserInfoId ");
			}else{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append("  model.constituency.district.districtId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					queryStr.append(" model.constituency.constituencyId ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append(" model.constituency.tehsil.tehsilId  ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" model.constituency.localElectionBody.localElectionBodyId  ");
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
					queryStr.append("  booth.localBody.localElectionBodyId ");
				}
			}
			
			
			Query query = getSession().createQuery(queryStr.toString());
			if(!inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) && inputVO.getParentLocationTypeId().longValue()>0L)
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(!type.trim().equalsIgnoreCase("LastOneHr")){
			if(type.equalsIgnoreCase("total")){
				if(inputVO.getStartDate() != null && inputVO.getEndDate() != null){
					query.setDate("startDate", format.parse(inputVO.getStartDate()));
					query.setDate("endDate", format.parse(inputVO.getEndDate()));
				}
			}else if(type.equalsIgnoreCase("today")){
				query.setDate("startDate", new DateUtilService().getCurrentDateAndTime());
				query.setDate("endDate", new DateUtilService().getCurrentDateAndTime());
			}
			}else{
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR_OF_DAY, 1);// last one hour
				query.setCalendarDate("startDate",cal);
			}
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	public List<Object[]> getSurveyUserTrackingDtls(Long cadreSurveyUserId,Date fromDate,Date toDate){
		   StringBuilder queryStr = new StringBuilder();
		     queryStr.append("select distinct " +
		     		         " model.surveyTime," +//0
		     		         " model.longititude," +//1
		     		         " model.latitude " +//2
		     		         " from TabUserLocationDetails model " +
		     		         " where model.cadreSurveyUserId=:cadreSurveyUserId ");
		     if(fromDate != null && toDate != null){
		    	 queryStr.append(" and date(model.surveyTime) between :fromDate and :toDate ");
		     }
		     queryStr.append("  order by date(model.surveyTime) ");
		     Query query = getSession().createQuery(queryStr.toString());
		     query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		     if(fromDate != null && toDate != null){
		    	 query.setParameter("fromDate", fromDate);
		    	 query.setParameter("toDate", toDate);
		     }
		     
		     return query.list();
	}
	
	public List<Object[]> getSurveyUserTrackingDtlsByFieldUser(Long fieldUserId,Long cadreSurveyUserId,Date fromDate,Date toDate,Date fromTime,Date toTime){
		   StringBuilder queryStr = new StringBuilder();
		     queryStr.append("select distinct " +
		     		         " model.surveyTime," +//0
		     		         " model.longititude," +//1
		     		         " model.latitude " +//2
		     		         " from TabUserLocationDetails model,CadreRegUserTabUser model1 " +
		     		         " where model.cadreSurveyUserId = model1.cadreSurveyUserId" +
		     		         " and model1.isDeleted = 'N'");
		     if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		    	 queryStr.append(" and model.cadreSurveyUserId = :cadreSurveyUserId ");
		     if(fieldUserId != null && fieldUserId.longValue() > 0l)
		    	 queryStr.append(" and model1.cadreRegUser.userId = :fieldUserId" +
		    	 		" and model1.cadreRegUser.userType = 'FM'");
		     if(fromDate != null && toDate != null){
		    	 queryStr.append(" and date(model.surveyTime) between :fromDate and :toDate ");
		     }
		     if(fromTime != null && toTime != null){
		    	 queryStr.append(" and model.surveyTime between :fromTime and :toTime ");
		     }
		     queryStr.append("  order by date(model.surveyTime) ");
		     Query query = getSession().createQuery(queryStr.toString());
		     if(cadreSurveyUserId != null && cadreSurveyUserId.longValue() > 0l)
		    	 query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		     if(fieldUserId != null && fieldUserId.longValue() > 0l)
		    	 query.setParameter("fieldUserId", fieldUserId);
		     if(fromDate != null && toDate != null){
		    	 query.setParameter("fromDate", fromDate);
		    	 query.setParameter("toDate", toDate);
		     }
		     if(fromTime != null && toTime != null){
		    	 query.setParameter("fromTime", fromTime);
		    	 query.setParameter("toTime", toTime);
		     }
		     
		     return query.list();
	}
	
  public List<Object[]> getSurveyUserLatestTimeLongitudeAndLatituedeLocationWise(String locationType,List<Long> locationValues,Date fromDate,Date toDate){
	  
	  StringBuilder queryStr = new StringBuilder();
	   queryStr.append(" select distinct " +
	   		          " tul1.cadre_survey_user_id as surveyUserId," +//0
	   		          " tul1.survey_time as surveryTime," +//1
	   		          " tul1.latitude as latitude," +//2
	   		          " tul1.longititude as longititude " +//3
	   		          " from tab_user_location_details tul1,"+
					  " ( select max(tul.survey_time) as survey_time,tul.cadre_survey_user_id " +
					  " from tab_user_location_details tul "+  
					  " group by tul.cadre_survey_user_id) as tul2,constituency c "+
					  " where tul1.cadre_survey_user_id = tul2.cadre_survey_user_id and "+
					  " tul1.survey_time = tul2.survey_time and tul1.constituency_id = c.constituency_id and c.deform_date is null and c.election_scope_id=2 ");
			  
		   	  if(locationType != null && locationType.equalsIgnoreCase("District") && locationValues != null && locationValues.size() > 0){
				  queryStr.append(" and c.district_id in  (:locationValues)");
			  }else if(locationType != null && locationType.equalsIgnoreCase("Constituency") && locationValues != null && locationValues.size() > 0 ){
				  queryStr.append(" and c.constituency_id in(:locationValues)");
			  }
			  if(fromDate != null && toDate != null){
			    	 queryStr.append(" and date(tul1.survey_time) between :fromDate and :toDate");
			  }
		     
		     Session session = getSession();
	         SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	     	 sqlQuery.addScalar("surveyUserId",Hibernate.LONG); 
	    	 sqlQuery.addScalar("surveryTime",Hibernate.STRING); 
	         sqlQuery.addScalar("latitude",Hibernate.STRING); 
		 	 sqlQuery.addScalar("longititude",Hibernate.STRING); 
			 if(fromDate != null && toDate != null){
				 sqlQuery.setParameter("fromDate", fromDate);
				 sqlQuery.setParameter("toDate", toDate);
		     }
			 if(locationValues != null && locationValues.size() >0){
		 	sqlQuery.setParameterList("locationValues", locationValues);	
		 }
		 return sqlQuery.list();
  }
  
  public List<Object[]> getLatestLattitudeAndLongitude(List<Long> cadreSurveyUserIds){
	  
	  StringBuilder str = new StringBuilder();
		str.append(" select model.survey_time,model.longititude,model.latitude,model1.name,model1.mobile_no," +
				" model.cadre_survey_user_id from " +
				" tab_user_location_details model,tab_user_info model1 where model.cadre_survey_user_id in (:cadreSurveyUserIds) " +
				" and model.tab_user_info_id= model1.tab_user_info_id and model1.cadre_survey_user_id=model.cadre_survey_user_id " +
				" group by model.cadre_survey_user_id order by model.tab_user_location_details_id ");
		
		Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(str.toString());
        
        if(cadreSurveyUserIds != null && cadreSurveyUserIds.size() >0){
		 	sqlQuery.setParameterList("cadreSurveyUserIds", cadreSurveyUserIds);
        }
		 	return sqlQuery.list();	
  }
  
}
