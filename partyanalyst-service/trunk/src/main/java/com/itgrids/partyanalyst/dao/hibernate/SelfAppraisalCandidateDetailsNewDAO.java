package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateDetailsNewDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetailsNew, Long> implements ISelfAppraisalCandidateDetailsNewDAO {

	public SelfAppraisalCandidateDetailsNewDAO() {
		super(SelfAppraisalCandidateDetailsNew.class);
	}
	
	 public List<Object[]> getCategoryWiseTourSubmittedLeader(String type,List<Long> monthYearIds,Set<Long> candiateIds){
		 
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		     " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," );
		   if(type.equalsIgnoreCase("tourType")){
			 queryStr.append("model.tourType.tourTypeId,");
		   }else{
			  queryStr.append("model.selfAppraisalTourCategory.selfAppraisalTourCategoryId,"); 
		   }
		   queryStr.append(" count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId) " +
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
                queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		   if(candiateIds != null && candiateIds.size() > 0){
				  queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)"); 
			}
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
	      if(type.equalsIgnoreCase("tourType")){
			 queryStr.append(",model.tourTypeId");
		  }else{
			  queryStr.append(",model.selfAppraisalTourCategoryId"); 
		  }
		   queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId");
		  Query query = getSession().createQuery(queryStr.toString());
		  	if(monthYearIds != null && monthYearIds.size() > 0 ){
			  query.setParameterList("monthYearIds", monthYearIds);
		    }
		  if(candiateIds != null && candiateIds.size() > 0){
			 query.setParameterList("candiateIds", candiateIds);
		  }
		  return query.list();
 }
	  //this dao is used both place coreDashboard and constituency page
	  public List<Object[]> getToursSubmittedLeaderCntDesignationBy(List<Long> monthYearIds,Set<Long> candiateIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidate.selfAppraisalCandidateId " +
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
                queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		   if(candiateIds != null && candiateIds.size() > 0){
			  queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)"); 
		   }
		  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				  " model.selfAppraisalCandidate.selfAppraisalCandidateId");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(candiateIds != null && candiateIds.size() > 0){
			  query.setParameterList("candiateIds", candiateIds);
		   }
		  if(monthYearIds != null && monthYearIds.size() > 0 ){
				  query.setParameterList("monthYearIds", monthYearIds);
		  }
		  return query.list();
   }
	  public List<Object[]> getLeaderComplainceCntCategoryWise(List<Long> monthYearIds,String type,Long selfAppraisalCandiateId,Set<Long> candiateIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
			 " model.selfAppraisalDesignation.designation," +//1
			 " model.selfAppraisalCandidateId," );//2
			if(type.equalsIgnoreCase("tourCategory")){
			 queryStr.append(" model.selfAppraisalTourCategoryId,");//3	
			}else if(type.equalsIgnoreCase("tourType")){
			 queryStr.append(" model.tourTypeId,");//3	
			}
			queryStr.append(" model.selfAppraisalToursMonthId," +//4
			 " sum(model.tourDays) " +//5
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
                queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		   if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
			 queryStr.append(" and model.selfAppraisalCandidateId=:selfAppraisalCandidateId");  
		   }
		   if(candiateIds != null && candiateIds.size() > 0){
			   queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)");    
		   }
		   if(type.equalsIgnoreCase("tourCategory")){
			   queryStr.append(" and model.tourTypeId not in ("+IConstants.GOVT_TOUR_TYPE_ID+")");
		   }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId,");
		    if(type.equalsIgnoreCase("tourCategory")){
			 queryStr.append(" model.selfAppraisalTourCategoryId");	
			}else if(type.equalsIgnoreCase("tourType")){
			 queryStr.append(" model.tourTypeId");	
			}
		    queryStr.append(",model.selfAppraisalToursMonthId");
		    queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId ");
		    if(type.equalsIgnoreCase("tourCategory")){
				 queryStr.append(",model.selfAppraisalTourCategoryId");	
			}else if(type.equalsIgnoreCase("tourType")){
				 queryStr.append(",model.tourTypeId");	
			}
		  Query query = getSession().createQuery(queryStr.toString());
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
			query.setParameterList("monthYearIds", monthYearIds);
		   }
		  if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
			query.setParameter("selfAppraisalCandidateId", selfAppraisalCandiateId);  
		  }
		  if(candiateIds != null && candiateIds.size() > 0){
			  query.setParameterList("candiateIds", candiateIds);    
		   }
		  return query.list();
  }
 public List<Object[]> getTourSubmitteedDtlsDesignationWise(Set<Long> candiateIdSet,List<Long> monthYearIds){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SACD.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		   				" SACD.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//1
		   				" SACD.selfAppraisalCandidate.selfAppraisalCandidateId," +//2
		   				" SACD.selfAppraisalCandidate.tdpCadre.firstname " +//3 
		   		   		" from " +
				   		" SelfAppraisalCandidateDetailsNew SACD " +
				   		" where " +
				   		" SACD.selfAppraisalCandidate.isActive = 'Y' and " +  
				   		" SACD.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and SACD.isDeleted='N'");
		  
		   if (candiateIdSet != null && candiateIdSet.size() > 0) {
			   queryStr.append(" and SACD.selfAppraisalCandidate.selfAppraisalCandidateId in (:candiateIdSet)");
		   }
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
               queryStr.append(" and SACD.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
           }
		   queryStr.append(" group by SACD.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				   " SACD.selfAppraisalCandidate.selfAppraisalCandidateId");   
		   queryStr.append(" order by SACD.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(candiateIdSet != null && candiateIdSet.size() > 0){
			   query.setParameterList("candiateIdSet", candiateIdSet);
		   }
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
				query.setParameterList("monthYearIds", monthYearIds);
		   }
		   return query.list();  
	   }
 public List<Object[]> getMonthWiseTourSubmittedDetails(List<Long> monthYearIds,Long candidateId){
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append(" select " +
	  					" model.selfAppraisalToursMonth.monthName," + //0
	  					" selfAppraisalTourCategory.selfAppraisalTourCategoryId," +//1
	  					" selfAppraisalTourCategory.tourCategory," +//2
	  					" tourType.tourTypeId," +//3
	  					" tourType.tourType," +//4
	  					" model.remarks," +//5
	  					" selfAppraisalDesignation.selfAppraisalDesignationId," +//6
	  					" selfAppraisalDesignation.designation," +//7
	  					" model.selfAppraisalToursMonth.year," +//8
	  					" model.tourDays," +//9
	  					" model.selfAppraisalToursMonth.selfAppraisalToursMonthId" +//10
	  					" from SelfAppraisalCandidateDetailsNew model " +
	  					" left join model.selfAppraisalTourCategory selfAppraisalTourCategory " +
	  					" left join model.tourType tourType" +
	  					" left join model.selfAppraisalDesignation selfAppraisalDesignation " +
	  					" where model.isDeleted='N' " +
	  					" and model.selfAppraisalCandidateId=:selfAppraisalCandidateId");
				    if(monthYearIds != null && monthYearIds.size() > 0 ){
			            queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
			        }
				    queryStr.append(" order by model.selfAppraisalToursMonth.year desc,model.selfAppraisalToursMonth.monthNo desc ");
                   Query query = getSession().createQuery(queryStr.toString());
                   query.setParameter("selfAppraisalCandidateId", candidateId);
                   if(monthYearIds != null && monthYearIds.size() > 0 ){
       				 query.setParameterList("monthYearIds", monthYearIds);
       		        }
               return query.list();
}
 
 public List<Object[]> getSubmittedToursLeadersDetails(List<Long> desigIds,List<Long> monthyearIds){
	 
	 StringBuilder queryStr = new StringBuilder();
	  queryStr.append( " select " +
	    		       " model.selfAppraisalCandidate.selfAppraisalDesignationId," +
	    		       " model.selfAppraisalCandidate.selfAppraisalCandidateId " + // Submitted Leaders Count
	    		       " from SelfAppraisalCandidateDetailsNew model " +
	    		       " where model.selfAppraisalCandidate.isActive='Y' " +
	    		       " and model.isDeleted ='N' "); 
	            	
	  				if(monthyearIds !=null && monthyearIds.size()>0){
	  					queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthyearIds) ");
	  				}
	  				
	                if(desigIds != null && desigIds.size()>0){
	                	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (:desigIds) ");
	                }
	                queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId," +
	                		        " model.selfAppraisalCandidate.selfAppraisalCandidateId " );
	                
	                Query query = getSession().createQuery(queryStr.toString());
	                if(desigIds != null && desigIds.size()>0){  
	     			   query.setParameterList("desigIds",desigIds); 
	     		    } 
	                if(monthyearIds !=null && monthyearIds.size()>0){
	                	query.setParameterList("monthyearIds",monthyearIds); 
	                }
	                return query.list();
	 
 }
 //This DAO is used multiple place (tour application,cadreProfile,constituencyPage)
	public List<Object[]> getCategoryWiseLeaderTourSubmittedCnt(String type,List<Long> monthYearIds, List<Long> designationIds,List<Long> candiateIds) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select "
				+ " model.selfAppraisalDesignation.selfAppraisalDesignationId," +// 0
				" model.selfAppraisalDesignation.designation," + // 1
				" model.selfAppraisalCandidateId,");// 2
		if (type.equalsIgnoreCase("tourCategory")) {
			queryStr.append(" model.selfAppraisalTourCategoryId,");// 3
		} else if (type.equalsIgnoreCase("tourType")) {
			queryStr.append(" model.tourTypeId,");// 3
		}
		queryStr.append(" model.selfAppraisalToursMonthId,"+// 4
				" sum(model.tourDays),model.tdpCadreId,model.tdpCadre.firstname "+ // 7
				" from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' "
				+ " and model.selfAppraisalDesignation.isActive='Y' ");
		if (monthYearIds != null && monthYearIds.size() > 0) {
			queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
		}
		if (type.equalsIgnoreCase("tourCategory")) {
			queryStr.append(" and model.tourTypeId not in ("+ IConstants.GOVT_TOUR_TYPE_ID + ")");
		}
		if (designationIds != null && designationIds.size() > 0) {
			queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in(:designationIds)");
		}
		if (candiateIds != null && candiateIds.size() > 0) {
			queryStr.append(" and model.selfAppraisalCandidateId in (:candidateIds) ");
		}
		queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalCandidateId,");
		if (type.equalsIgnoreCase("tourCategory")) {
			queryStr.append(" model.selfAppraisalTourCategoryId");
		} else if (type.equalsIgnoreCase("tourType")) {
			queryStr.append(" model.tourTypeId");
		}
		queryStr.append(",model.selfAppraisalToursMonthId");
		queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalCandidateId ");
		if (type.equalsIgnoreCase("tourCategory")) {
			queryStr.append(",model.selfAppraisalTourCategoryId");
		} else if (type.equalsIgnoreCase("tourType")) {
			queryStr.append(",model.tourTypeId");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if (monthYearIds != null && monthYearIds.size() > 0) {
			query.setParameterList("monthYearIds", monthYearIds);
		}
		if (designationIds != null && designationIds.size() > 0) {
			query.setParameterList("designationIds", designationIds);
		}
		if (candiateIds != null && candiateIds.size() > 0) {
			query.setParameterList("candidateIds", candiateIds);
		}
		return query.list();
	}
	 
	 public List<Object[]> getCandidateComplainceCntCategoryWise(Date fromDate,Date toDate,String type,List<Long> designationIds,Long candidateId,List<Long> monthyearIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalCandidateId " );
			if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" ,model.selfAppraisalTourCategoryId ");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" ,model.tourTypeId ");	
			}
			queryStr.append(" ,sum(model.tourDays) " +
			 " from SelfAppraisalCandidateDetailsNew model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
			
		 /*  if(fromDate != null && toDate != null ){
			   queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
		   }*/
		   
			if(monthyearIds !=null && monthyearIds.size()>0){
				queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthyearIds) ");
			}
			
		   if(designationIds !=null && designationIds.size()>0){
			   queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
		   }
		   if(candidateId !=null && candidateId>0l){
	        	queryStr.append(" and model.selfAppraisalCandidateId =:candidateId ");
	        }

		   queryStr.append(" group by " +
		  				   "  model.selfAppraisalCandidateId ");
		    if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" ,model.selfAppraisalTourCategoryId ");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" ,model.tourTypeId");	
			}
		    queryStr.append(" order by " +
		  				   "  model.selfAppraisalCandidateId ");
		  Query query = getSession().createQuery(queryStr.toString());
		  /*if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }*/
		 
		  if(designationIds !=null && designationIds.size()>0){
			  query.setParameterList("designationIds", designationIds);    
		  }
		  if(candidateId !=null && candidateId>0l){
			  query.setParameter("candidateId",candidateId);
		  }
		  if(monthyearIds !=null && monthyearIds.size()>0){
			  query.setParameterList("monthyearIds", monthyearIds);
		  }
		  return query.list();
	 }
	 
	 public List<Object[]> getTourSubmitteedCandidates(Date fromDate,Date toDate,List<Long> designationIds,Long candidateId,List<Long> monthyearIds){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" distinct SACL.selfAppraisalCandidate.selfAppraisalCandidateId," +//0
		   				" SACL.selfAppraisalCandidate.tdpCadre.firstname," +//1
		   				" SACL.selfAppraisalCandidate.tdpCadreId," +//2
		   				" SACDN.selfAppraisalToursMonthId " +//3
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL,SelfAppraisalCandidateDetailsNew SACDN " +
				   		" where SACL.selfAppraisalCandidate.selfAppraisalCandidateId = SACDN.selfAppraisalCandidate.selfAppraisalCandidateId   " +
				   		" and SACL.selfAppraisalCandidate.isActive = 'Y'  " +  
				   		" " +
				   		" and SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y'  " +
				   		" and SACDN.isDeleted='N' " );

		   /*if(fromDate != null && toDate != null ){
			   queryStr.append(" and date(SACT.tourDate) between :fromDate and :toDate ");
		   }*/
		   
		   if(monthyearIds !=null && monthyearIds.size()>0){
			   queryStr.append(" and SACDN.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthyearIds) ");
		   }
		   
		   if(candidateId !=null && candidateId>0l){
	        	queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalCandidateId =:candidateId  ");
	        }
		   if(designationIds !=null && designationIds.size()>0){
			   queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
		   }
		   
		   Query query = getSession().createQuery(queryStr.toString());	
		   

		   /*if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		   }*/
		   
		   if(monthyearIds !=null && monthyearIds.size()>0){
			   query.setParameterList("monthyearIds", monthyearIds);
		   }
		   
		   if(candidateId !=null && candidateId>0l){
			   query.setParameter("candidateId",candidateId);
		   }
		   
		   if(designationIds != null && designationIds.size() > 0){
			   query.setParameterList("designationIds",designationIds);   
		   }
		   
		   return query.list();  
	   }
	 
	 public List<Object[]> getDateWiseTourSubmittedDetails(Date fromDate,Date toDate,Long candidateId,List<Long> monthyearIds){
		    StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select " +
		    				" model.selfAppraisalCandidateDetailsNewId," +//0
		  					" selfAppraisalTourCategory.selfAppraisalTourCategoryId," +//1
		  					" selfAppraisalTourCategory.tourCategory," +//2
		  					" tourType.tourTypeId," +//3
		  					" tourType.tourType," +//4		  					
		  					" model.remarks," +//5
		  					" selfAppraisalDesignation.selfAppraisalDesignationId," +//6
		  					" selfAppraisalDesignation.designation," +//7
		  					" model.selfAppraisalToursMonth.monthName," + //8
		  					" model.selfAppraisalToursMonth.year, " +//9
		  					" model.tourDays," +
		  					" model.selfAppraisalCandidate.tdpCadreId " +
		  					" from SelfAppraisalCandidateDetailsNew model " +
		  					" left join model.selfAppraisalTourCategory selfAppraisalTourCategory " +
		  					" left join model.tourType tourType" +
		  					" left join model.selfAppraisalDesignation selfAppraisalDesignation " +
		  					" where model.isDeleted='N' " +
		  					" and model.selfAppraisalCandidateId=:selfAppraisalCandidateId ");
					     /* if(fromDate != null && toDate != null ){
				               queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
				          }*/
		    			
			    		if(monthyearIds !=null && monthyearIds.size()>0){
			    			queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthyearIds) ");
			    		}
		    
	                      Query query = getSession().createQuery(queryStr.toString());
	                      query.setParameter("selfAppraisalCandidateId", candidateId);
	                      /*if(fromDate!= null && toDate!=null){
	           			   query.setDate("fromDate", fromDate);
	           			   query.setDate("toDate", toDate);
	           		      }*/
	                      
	                      if(monthyearIds !=null && monthyearIds.size()>0){
	                    	  query.setParameterList("monthyearIds", monthyearIds);
	                      }
	                      
                   return query.list();
	 }
	 
	 /**
  	  * @author Srishailam Pittala
  	  * date: 7th Jan, 2017
  	  * desc: To get total Tours Details for a cadre
  	  */
       
       public List<Object[]> getToursDetailsforCadre(Long tdpCadreId, List<String> monthYearStrList, Long designationId){
	       	StringBuilder queryStr = new StringBuilder();
	       	queryStr.append("");
	       	queryStr.append(" select distinct selfAppraisalTourCategory.selfAppraisalTourCategoryId, selfAppraisalTourCategory.tourCategory ," +
	       			" selfAppraisalDesignation.selfAppraisalDesignationId, selfAppraisalDesignation.designation, sum(model.tourDays),model.tourTypeId from " +
	       			" SelfAppraisalCandidateDetailsNew model  " +
	       			" left join model.selfAppraisalTourCategory selfAppraisalTourCategory " +
	       			" left join model.selfAppraisalDesignation selfAppraisalDesignation " +
	       			" where model.selfAppraisalDesignationId =:designationId and model.selfAppraisalCandidate.tdpCadreId = :tdpCadreId ");
	       	if(monthYearStrList != null && monthYearStrList.size()>0)
	       		queryStr.append(" and model.selfAppraisalToursMonth.toursMonth in (:monthYearStrList) ");
	       	
	       	queryStr.append(" group by selfAppraisalTourCategory.selfAppraisalTourCategoryId,model.selfAppraisalDesignationId,model.tourTypeId ");
	       	Query query = getSession().createQuery(queryStr.toString());
	       	query.setParameter("tdpCadreId", tdpCadreId);
	       	query.setParameter("designationId", designationId);
	       	if(monthYearStrList != null && monthYearStrList.size()>0)
	       		query.setParameterList("monthYearStrList", monthYearStrList);
       	return query.list();
       }
       
       
	 public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	 }
	 public Long checkForExistingTourDetails(Long candidateId, Long tourCategoryId, Long tourTypeId,Long toursMonthId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.selfAppraisalCandidateDetailsNewId from SelfAppraisalCandidateDetailsNew model where ");
		 if(candidateId != null && candidateId.longValue() > 0L){
			 queryStr.append(" model.selfAppraisalCandidateId = :candidateId ");
		 }
		 if(tourCategoryId != null && tourCategoryId.longValue() > 0L){
			 queryStr.append(" and model.selfAppraisalTourCategoryId = :tourCategoryId ");
		 }
		 if(tourTypeId != null && tourTypeId.longValue() > 0L){
			 queryStr.append(" and model.tourTypeId = :tourTypeId ");
		 }
		 if(toursMonthId != null && toursMonthId.longValue() > 0L){
			 queryStr.append(" and model.toursMonthId = :toursMonthId ");
		 }
		 Query query = getSession().createQuery(queryStr.toString());
		 
		 if(candidateId != null && candidateId.longValue() > 0L){
			 query.setParameter("candidateId", candidateId);
		 }
		 if(tourCategoryId != null && tourCategoryId.longValue() > 0L){
			 query.setParameter("tourCategoryId", tourCategoryId);
		 }
		 if(tourTypeId != null && tourTypeId.longValue() > 0L){
			 query.setParameter("tourTypeId", tourTypeId);
		 }
		 if(toursMonthId != null && toursMonthId.longValue() > 0L){
			 query.setParameter("toursMonthId", toursMonthId);
		 }
		 return (Long) query.uniqueResult();
	 }
	    /**
	  	  * @author Teja
	  	  * date: 30th Jan, 2017
	  	  * desc: To get  Tours Overview  By cadreId
	  	  */
       
    @SuppressWarnings("unchecked")
	public List<Object[]> getToursOverviewByCadre(Long tdpCadreId, Long tourMonthId){
	       	StringBuilder queryStr = new StringBuilder();
	       	
	       	queryStr.append(" select model.selfAppraisalCandidateDetailsNewId,model.selfAppraisalDesignation.selfAppraisalDesignationId," +
	       			" model.selfAppraisalDesignation.designation,model.selfAppraisalTourCategory.selfAppraisalTourCategoryId," +
	       			" model.selfAppraisalTourCategory.tourCategory,model.tourType.tourType," +
	       			" model.tourDays,model.updatedTime,model.selfAppraisalCandidate.selfAppraisalCandidateId,model.tourType.tourTypeId from " +
	       			" SelfAppraisalCandidateDetailsNew model ");
	       	
	    	queryStr.append( " where " +
	       			" model.tdpCadre.tdpCadreId = :tdpCadreId ");
	    	 if(tourMonthId != null){
	    		 queryStr.append( " and model.selfAppraisalToursMonthId = :tourMonthId ");
	    	 }
	       	queryStr.append( " and model.isDeleted ='N' and model.selfAppraisalTourCategory.isDeleted ='N' " +
	       			" and model.tourType.isDeleted ='N' ");
	       	
	   Query query = getSession().createQuery(queryStr.toString());
	      query.setParameter("tdpCadreId", tdpCadreId);
	    if(tourMonthId != null)
	      query.setParameter("tourMonthId", tourMonthId);
       	return query.list();
       }
	
	public List<Object[]> getProgramVistedLeaderDetails(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,List<Long> monthYearIds,List<Long> designationIds){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//1
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId," +//2
		   				" SACL.selfAppraisalCandidate.tdpCadre.firstname " +//3 
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL,SelfAppraisalCandidateProgramDetails SACPD " +
				   		" where SACL.selfAppraisalCandidate.selfAppraisalCandidateId = SACPD.selfAppraisalCandidate.selfAppraisalCandidateId and  " +
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and " +  
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and SACPD.isDeleted='N' and " +
				   		" SACL.userAddress.state.stateId = :stateId ");
		   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.state.stateId in (:userAccessLevelValues)");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.district.districtId in (:userAccessLevelValues)");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			   queryStr.append(" and SACL.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		   }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			   queryStr.append(" and SACL.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		   }
		   if(userTypeId != IConstants.STATE_USER_TYPE_ID || designationIds != null && designationIds.size() > 0){
				  queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) "); 
		   }
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
            queryStr.append(" and SACPD.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
       }
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalCandidateId");   
		   queryStr.append(" order by SACL.selfAppraisalCandidate.selfAppraisalDesignation.orderNo ");
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
				query.setParameterList("monthYearIds", monthYearIds);
		   }
		   if(stateId != null && stateId.longValue() > 0){
			   query.setParameter("stateId", stateId);
		   }
		   if(designationIds != null && designationIds.size() > 0){
			   query.setParameterList("designationIds",designationIds);   
		   }else{
			 /*  if(userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID){
			    	query.setParameterList("designationIds",Arrays.asList(IConstants.STATE_SUB_LEVEL_DESIG_IDS));
		       }else*/ if(userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS));
		       }else if(userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.SECRETARY_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.MP_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS));
		      }else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.INCHARGE_MINISTER_SUB_LEVEL_DESIG_IDS));	
		      }else if(userTypeId.longValue() == IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
		     		query.setParameterList("designationIds",Arrays.asList(IConstants.PARLIAMENT_INCHARGE_SUB_LEVEL_DESIG_IDS));	
		      }    
		   }
		
		   return query.list();  
	   }
	public List<Object[]> getCandiateComment(Long tdpCadreId){
		Query query = getSession().createQuery("select model.selfAppraisalToursMonthId,model.comment " +
				                               " from SelfAppraisalComment model " +
				                               " where model.isDeleted='N' and model.tdpCadreId =:tdpCadreId " +
				                               " order by model.insertedtime desc");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	public List<Object[]> getUniqueTourSubmittedCandiate(Set<Long> tdpCadreIdSet,List<Long> monthYearIds){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append( " select distinct "+
						    " SACD.tdpCadre.tdpCadreId," +
			           		" SACD.tdpCadre.firstname," +
			           		" SACD.tdpCadre.memberShipNo," +
			           		" SACD.tdpCadre.mobileNo "+
			   		   		" from " +
					   		" SelfAppraisalCandidateDetailsNew SACD " +
					   		" where SACD.isDeleted='N'  ");
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
			   queryStr.append(" and SACD.selfAppraisalToursMonth.selfAppraisalToursMonthId in(:monthYearIds) ");
            }
		   if(tdpCadreIdSet != null && tdpCadreIdSet.size() > 0 ){
			   queryStr.append(" and SACD.tdpCadreId in(:tdpCadreIdSet) ");
            }
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(monthYearIds != null && monthYearIds.size() > 0 ){
				query.setParameterList("monthYearIds", monthYearIds);
		   }
		   if(tdpCadreIdSet != null && tdpCadreIdSet.size() > 0 ){
			   query.setParameterList("tdpCadreIdSet", tdpCadreIdSet);
            }
		   return query.list();  
	   }
}
