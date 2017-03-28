package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceTypeInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataSourceTypeInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreDataSourceTypeInfoDAO extends GenericDaoHibernate<TdpCadreDataSourceTypeInfo, Long> implements ITdpCadreDataSourceTypeInfoDAO {

	public TdpCadreDataSourceTypeInfoDAO() {
		super(TdpCadreDataSourceTypeInfo.class);
	}
	
	public int deleteAllRecords(){
    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_data_source_type_info ");
    	return query.executeUpdate();
    }

	 public List<Object[]> getTdpCadreCountsByDataDourceType(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  model.tdpCadre.dataSourceType,count(distinct model.tdpCadre.tdpCadreId)" +
	    			  " from    TdpCadreEnrollmentYear model " +
					  " where   model.isDeleted = 'N' and model.tdpCadre.isDe" +
					  "leted = 'N' and "+
					  "         model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId and" +
					  "         ( model.tdpCadre.insertedUserId not in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+")  or model.tdpCadre.insertedUserId is null) " );
			if(date != null){
				sb.append(" and date(model.tdpCadre.surveyTime) =  :date ");
			}
			sb.append(" group by model.tdpCadre.dataSourceType ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date!= null ){
			   query.setDate("date", date);
		    }
			return query.list();
    }
	 
	 public List<Object[]> getRenewalTdpCadreCountsByDataDourceType(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  tc.dataSourceType,count(distinct tc.tdpCadreId )  " +
					  " from    TdpCadre tc , TdpCadreEnrollmentYear year1, TdpCadreEnrollmentYear year2 " +
					  " where   tc.tdpCadreId = year1.tdpCadre.tdpCadreId and  tc.tdpCadreId = year2.tdpCadre.tdpCadreId and " +
					  "         tc.isDeleted = 'N' and tc.enrollmentYear = 2014 and " +
					  "         year1.isDeleted = 'N' and year1.enrollmentYear.enrollmentYearId = :previousEnrollmentYear and " +
					  "         year2.isDeleted = 'N' and year2.enrollmentYear.enrollmentYearId = :presentEnrollmentYear " +
					  "  and (tc.insertedUserId not in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+") or tc.insertedUserId is null) ");
					  
			if(date != null){
				sb.append(" and date(tc.surveyTime) = :date");
			}
			sb.append(" group by tc.dataSourceType ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("previousEnrollmentYear",IConstants.PREVIOUS_CADRE_ENROLLMENT_YEAR);
			query.setParameter("presentEnrollmentYear",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date != null){
				 query.setDate("date", date);
			}
			return query.list();
     }
	 
	 public List<Object[]> getTdpCadreRecordsCountByPartyOffice(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  model.tdpCadre.insertedWebUser.userId,count(distinct model.tdpCadre.tdpCadreId) " +
					  " from    TdpCadreEnrollmentYear model " +
					  " where   model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and "+
					  "         model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId " +
					  " and model.tdpCadre.insertedUserId in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+") " );
			if( date != null ){
				sb.append(" and date(model.tdpCadre.surveyTime) = :date ");
			}
			sb.append(" group by model.tdpCadre.insertedWebUser.userId ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date!= null){
				   query.setDate("date", date);
			}
			return query.list();
	}
	 
	 public List<Object[]> getRenewalTdpCadreRecordsCountByPartyOffice(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  tc.insertedWebUser.userId,count(distinct tc.tdpCadreId )  " +
					  " from    TdpCadre tc , TdpCadreEnrollmentYear year1, TdpCadreEnrollmentYear year2 " +
					  " where   tc.tdpCadreId = year1.tdpCadre.tdpCadreId and  tc.tdpCadreId = year2.tdpCadre.tdpCadreId and " +
					  "         tc.isDeleted = 'N' and tc.enrollmentYear = 2014 and " +
					  "         year1.isDeleted = 'N' and year1.enrollmentYear.enrollmentYearId = :previousEnrollmentYear and " +
					  "         year2.isDeleted = 'N' and year2.enrollmentYear.enrollmentYearId = :presentEnrollmentYear " +
					  "  and tc.insertedUserId in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+") ");
					  
			if(date != null){
				sb.append(" and date(tc.surveyTime) = :date ");
			}
			sb.append(" group by tc.insertedWebUser.userId ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("previousEnrollmentYear",IConstants.PREVIOUS_CADRE_ENROLLMENT_YEAR);
			query.setParameter("presentEnrollmentYear",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date != null){
				query.setDate("date", date);
			}
			return query.list();
	 }
	 
	 public List<Object[]> getDataSourceTypeWiseCountsByType(String type){
		 Query query = getSession().createQuery("select model.dataSourceType,sum(model.cadre2016)," +
		 								" sum(model.newCadre),model.newCadrePercent," +
		 								" sum(model.renewalCadre),model.renewalCadrePercent" +
		 								" from TdpCadreDataSourceTypeInfo model" +
		 								" where model.type = :type" +
		 								" group by model.dataSourceType");
		 query.setParameter("type", type);
		 return query.list();
	 }
	 
	
	 public List<Object[]> getTdpCadreCountsByDataDourceTypeByDistrict(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  model.tdpCadre.userAddress.district.districtId,model.tdpCadre.dataSourceType,count(distinct model.tdpCadre.tdpCadreId)" +
	    			  " from    TdpCadreEnrollmentYear model " +
					  " where   model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and "+
					  "         model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId and" +
					  "         ( model.tdpCadre.insertedUserId not in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+")  or model.tdpCadre.insertedUserId is null ) " );
			if(date != null){
				sb.append(" and date(model.tdpCadre.surveyTime) =  :date ");
			}
			sb.append(" group by model.tdpCadre.userAddress.district.districtId , model.tdpCadre.dataSourceType ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date!= null ){
			   query.setDate("date", date);
		    }
			return query.list();
   }
	 
	 public List<Object[]> getRenewalTdpCadreCountsByDataDourceTypeByDistrict(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  tc.userAddress.district.districtId , tc.dataSourceType,count(distinct tc.tdpCadreId )  " +
					  " from    TdpCadre tc , TdpCadreEnrollmentYear year1, TdpCadreEnrollmentYear year2 " +
					  " where   tc.tdpCadreId = year1.tdpCadre.tdpCadreId and  tc.tdpCadreId = year2.tdpCadre.tdpCadreId and " +
					  "         tc.isDeleted = 'N' and tc.enrollmentYear = 2014 and " +
					  "         year1.isDeleted = 'N' and year1.enrollmentYear.enrollmentYearId = :previousEnrollmentYear and " +
					  "         year2.isDeleted = 'N' and year2.enrollmentYear.enrollmentYearId = :presentEnrollmentYear " +
					  "  and (tc.insertedUserId not in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+") or tc.insertedUserId is null) ");
					  
			if(date != null){
				sb.append(" and date(tc.surveyTime) = :date");
			}
			sb.append(" group by tc.userAddress.district.districtId , tc.dataSourceType ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("previousEnrollmentYear",IConstants.PREVIOUS_CADRE_ENROLLMENT_YEAR);
			query.setParameter("presentEnrollmentYear",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date != null){
				 query.setDate("date", date);
			}
			return query.list();
   }
	 
	 public List<Object[]> getTdpCadreRecordsCountByPartyOfficeByDistrict(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  model.tdpCadre.userAddress.district.districtId , model.tdpCadre.insertedUserId,count(distinct model.tdpCadre.tdpCadreId) " +
					  " from    TdpCadreEnrollmentYear model " +
					  " where   model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and "+
					  "         model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId " +
					  " and model.tdpCadre.insertedUserId in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+") " );
			if( date != null ){
				sb.append(" and date(model.tdpCadre.surveyTime) = :date ");
			}
			sb.append(" group by model.tdpCadre.userAddress.district.districtId, model.tdpCadre.insertedUserId");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date!= null){
				   query.setDate("date", date);
			}
			return query.list();
	}
	 
	 public List<Object[]> getRenewalTdpCadreRecordsCountByPartyOfficeByDistrict(Date date){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select  tc.userAddress.district.districtId , tc.insertedUserId , count(distinct tc.tdpCadreId )  " +
					  " from    TdpCadre tc , TdpCadreEnrollmentYear year1, TdpCadreEnrollmentYear year2 " +
					  " where   tc.tdpCadreId = year1.tdpCadre.tdpCadreId and  tc.tdpCadreId = year2.tdpCadre.tdpCadreId and " +
					  "         tc.isDeleted = 'N' and tc.enrollmentYear = 2014 and " +
					  "         year1.isDeleted = 'N' and year1.enrollmentYear.enrollmentYearId = :previousEnrollmentYear and " +
					  "         year2.isDeleted = 'N' and year2.enrollmentYear.enrollmentYearId = :presentEnrollmentYear " +
					  "  and tc.insertedUserId in ("+IConstants.LATEST_HYD_PARTY_OFFICE_USER_IDS+","+IConstants.LATEST_VIJ_PARTY_OFFICE_USER_IDS+") ");
					  
			if(date != null){
				sb.append(" and date(tc.surveyTime) = :date ");
			}
			sb.append(" group by tc.userAddress.district.districtId  , tc.insertedUserId ");
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameter("previousEnrollmentYear",IConstants.PREVIOUS_CADRE_ENROLLMENT_YEAR);
			query.setParameter("presentEnrollmentYear",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
			if(date != null){
				query.setDate("date", date);
			}
			return query.list();
	 }
	 
}
