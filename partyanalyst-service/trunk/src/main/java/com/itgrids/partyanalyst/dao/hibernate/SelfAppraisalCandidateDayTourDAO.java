package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDayTourDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDayTour;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateDayTourDAO extends GenericDaoHibernate<SelfAppraisalCandidateDayTour, Long> implements
		ISelfAppraisalCandidateDayTourDAO {
	
	   public SelfAppraisalCandidateDayTourDAO(){
		   super(SelfAppraisalCandidateDayTour.class);
	   }
	   public List<Object[]> getToursSubmittedLeaderCntDesignationBy(Date fromDate,Date toDate,Set<Long> candiateIds){
				StringBuilder queryStr = new StringBuilder();
				queryStr.append(" select " +
				 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
 				 " model.selfAppraisalDesignation.designation," +
 				 " count(distinct model.selfAppraisalCandidateId) " +
 				 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
 				 " and model.selfAppraisalDesignation.isActive='Y' ");
			   if(fromDate != null && toDate != null ){
	                  queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
	           }
			   if(candiateIds != null && candiateIds.size() > 0){
				  queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)"); 
			   }
 			  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
 			  Query query = getSession().createQuery(queryStr.toString());
 			  if(fromDate!= null && toDate!=null){
	 			   query.setDate("fromDate", fromDate);
	 			   query.setDate("toDate", toDate);
 			  }
 			  if(candiateIds != null && candiateIds.size() > 0){
				  query.setParameterList("candiateIds", candiateIds);
			   }
 			  return query.list();
	     }
	   public List<Object[]> getLeaderComplainceCnt(Date fromDate,Date toDate){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidateId," +
			 " count(distinct model.tourDate) " +
			 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(fromDate != null && toDate != null ){
                 queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
          }
		  queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				 "  model.selfAppraisalCandidateId " +
		  				 " order by  model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				 " model.selfAppraisalCandidateId ");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		  return query.list();
      }
	 public List<Object[]> getLeaderComplainceCntCategoryWise(Date fromDate,Date toDate,String type,Long selfAppraisalCandiateId,Set<Long> candiateIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidateId," );
			if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" model.selfAppraisalTourCategoryId,");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" model.tourTypeId,");	
			}
			queryStr.append(" count(distinct model.tourDate) " +
			 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(fromDate != null && toDate != null ){
                queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
           }
		 /*  if(type.equalsIgnoreCase("Govt")){
			   queryStr.append(" and model.tourTypeId = 2 ");
		   }*/
		   if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
			 queryStr.append(" and model.selfAppraisalCandidateId=:selfAppraisalCandidateId");  
		   }
		   if(candiateIds != null && candiateIds.size() > 0){
			   queryStr.append(" and model.selfAppraisalCandidateId in (:candiateIds)");    
		   }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId,");
		    if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" model.selfAppraisalTourCategoryId");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" model.tourTypeId");	
			}
		    queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId ");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		  if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
			query.setParameter("selfAppraisalCandidateId", selfAppraisalCandiateId);  
		  }
		  if(candiateIds != null && candiateIds.size() > 0){
			  query.setParameterList("candiateIds", candiateIds);    
		   }
		  return query.list();
    }
	 public List<Object[]> getTourSubmitteedDesignationWiseAllCandiateBasedOnUserAccessLevel(Long stateId,Long userAccessLevelId,Set<Long> locationValueSet,Long userTypeId,Date fromDate,Date toDate,List<Long> designationIds){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		   				" SACL.selfAppraisalCandidate.selfAppraisalDesignation.designation," +//1
		   				" SACL.selfAppraisalCandidate.selfAppraisalCandidateId," +//2
		   				" SACL.selfAppraisalCandidate.tdpCadre.firstname " +//3 
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL,SelfAppraisalCandidateDayTour SACT " +
				   		" where SACL.selfAppraisalCandidate.selfAppraisalCandidateId = SACT.selfAppraisalCandidate.selfAppraisalCandidateId and  " +
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and " +  
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and " +
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
		   if(fromDate != null && toDate != null ){
               queryStr.append(" and date(SACT.tourDate) between :fromDate and :toDate ");
          }
		   queryStr.append(" group by SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
		   				   " SACL.selfAppraisalCandidate.selfAppraisalCandidateId");   
		   Query query = getSession().createQuery(queryStr.toString());	
		   
		   if(locationValueSet != null && locationValueSet.size() > 0){
			   query.setParameterList("userAccessLevelValues", locationValueSet);
		   }
		   if(stateId != null && stateId.longValue() > 0){
			   query.setParameter("stateId", stateId);
		   }
		   if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
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
		      }   
		   }
		
		   return query.list();  
	   }
	 public List<Object[]> getDateWiseTourSubmittedDetails(Date fromDate,Date toDate,Long candidateId){
		    StringBuilder queryStr = new StringBuilder();
		    queryStr.append(" select " +
		  					" date(model.tourDate)," + //0
		  					" selfAppraisalTourCategory.selfAppraisalTourCategoryId," +//1
		  					" selfAppraisalTourCategory.tourCategory," +//2
		  					" tourType.tourTypeId," +//3
		  					" tourType.tourType," +//4
		  					" district.districtId," +//5
		  					" district.districtName," +//6
		  					" constituency.constituencyId," +//7
		  					" constituency.name," +//8
		  					" model.selfAppraisalCandidateDayTourId," +//9
		  					" parliamentConstituency.constituencyId," +//10
		  					" parliamentConstituency.name," +//11
		  					" model.comment," +//12
		  					" selfAppraisalDesignation.selfAppraisalDesignationId," +//13
		  					" selfAppraisalDesignation.designation" +//14
		  					" from SelfAppraisalCandidateDayTour model " +
		  					" left join model.userAddress userAddress " +
		  					" left join userAddress.district district " +
		  					" left join userAddress.constituency constituency " +
		  					" left join model.selfAppraisalTourCategory selfAppraisalTourCategory " +
		  					" left join model.tourType tourType" +
		  					" left join userAddress.parliamentConstituency parliamentConstituency" +
		  					" left join model.selfAppraisalDesignation selfAppraisalDesignation " +
		  					" where  " +
		  					" model.selfAppraisalCandidateId=:selfAppraisalCandidateId ");
					      if(fromDate != null && toDate != null ){
				               queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
				          }
	                      Query query = getSession().createQuery(queryStr.toString());
	                      query.setParameter("selfAppraisalCandidateId", candidateId);
	                      if(fromDate!= null && toDate!=null){
	           			   query.setDate("fromDate", fromDate);
	           			   query.setDate("toDate", toDate);
	           		       }
                      return query.list();
	 }
	 public List<Object[]> getCategoryWiseTourSubmittedLeader(Date fromDate,Date toDate,String type){
		 
			 StringBuilder queryStr = new StringBuilder();
			 queryStr.append(" select " +
			     " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
				 " model.selfAppraisalDesignation.designation," );
			   if(type.equalsIgnoreCase("Govt")){
				 queryStr.append("model.tourType.tourTypeId,");
			   }else{
				  queryStr.append("model.selfAppraisalTourCategory.selfAppraisalTourCategoryId,"); 
			   }
			   queryStr.append(" count(distinct model.selfAppraisalCandidateId) " +
				 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
				 " and model.selfAppraisalDesignation.isActive='Y' ");
			   if(fromDate != null && toDate != null ){
	                  queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
	           }
			/*   if(type.equalsIgnoreCase("Govt")){
				   queryStr.append(" and model.tourTypeId = 2 ");
			   }*/
			   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
		      if(type.equalsIgnoreCase("Govt")){
				 queryStr.append(",model.tourTypeId");
			  }else{
				  queryStr.append(",model.selfAppraisalTourCategoryId"); 
			  }
			    queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId");
			  Query query = getSession().createQuery(queryStr.toString());
			  if(fromDate!= null && toDate!=null){
 			   query.setDate("fromDate", fromDate);
 			   query.setDate("toDate", toDate);
			  }
			  return query.list();
	 }
	 
	 public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,List<Long> desigIds){
		 
		 StringBuilder queryStr = new StringBuilder();
		  queryStr.append( " select " +
		    		       " model.selfAppraisalCandidate.selfAppraisalDesignationId," +
		    		       " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," + // Submitted Leaders Count
		    		       " count(model.selfAppraisalCandidateDayTourId) " + // Submitted  Tours Count

		    		       " from SelfAppraisalCandidateDayTour model " +
		    		       " where model.selfAppraisalCandidate.isActive='Y' " +
		    		       " and model.isDeleted ='N' "); 
		                if(fromDate != null && toDate != null ){
		                	queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
		                }
		                if(desigIds != null && desigIds.size()>0){
		                	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (:desigIds) ");
		                }
		                queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId " );
		                
		                Query query = getSession().createQuery(queryStr.toString());
		                if(fromDate != null && toDate != null ){
		                	query.setDate("fromDate", fromDate);
		                	query.setDate("toDate", toDate);
		                }
		                if(desigIds != null && desigIds.size()>0){  
		     			   query.setParameterList("desigIds",desigIds); 
		     		    }    
		                return query.list();
		 
	 }
	 
	 public List<Object[]> getCandidateWiseTargetCompletedDays(Date fromDate,Date toDate,List<Long> desigIds){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append( " select " +
  		       " model.selfAppraisalCandidate.selfAppraisalDesignationId," +
  		       " " +
  		       " model.selfAppraisalCandidate.selfAppraisalCandidateId, " + // Submitted Leaders Count
  		       " count(distinct model.tourDate) " + 

  		       " from SelfAppraisalCandidateDayTour model " +
  		       " where model.selfAppraisalCandidate.isActive='Y' " +
  		       " and model.isDeleted ='N' " +
  		       " and model.selfAppraisalCandidate.selfAppraisalDesignation.isActive='Y' "); 
              if(fromDate != null && toDate != null ){
              	queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
              }
              if(desigIds != null && desigIds.size()>0){
              	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (:desigIds) ");
              }
              queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId,model.selfAppraisalCandidate.selfAppraisalCandidateId ");
              Query query = getSession().createQuery(queryStr.toString());
              if(fromDate != null && toDate != null ){
              	query.setDate("fromDate", fromDate);
              	query.setDate("toDate", toDate);
              }
              if(desigIds != null && desigIds.size()>0){  
   			   		query.setParameterList("desigIds",desigIds); 
   		      }    
              return query.list();
		 
	 }
	 
	 public List<Object[]> getCandidateComplainceCntCategoryWise(Date fromDate,Date toDate,String type,List<Long> designationIds,Long candidateId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalCandidateId " );
			if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" ,model.selfAppraisalTourCategoryId ");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" ,model.tourTypeId ");	
			}
			queryStr.append(" ,count(distinct model.tourDate) " +
			 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
			
		   if(fromDate != null && toDate != null ){
             queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
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
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		 
		  if(designationIds !=null && designationIds.size()>0){
			  query.setParameterList("designationIds", designationIds);    
		  }
		  if(candidateId !=null && candidateId>0l){
			  query.setParameter("candidateId",candidateId);
		  }
		  return query.list();
 }
	 
	 public List<Object[]> getTourSubmitteedCandidates(Date fromDate,Date toDate,List<Long> designationIds,Long candidateId){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append(" select  " +
		   				" distinct SACL.selfAppraisalCandidate.selfAppraisalCandidateId," +//0
		   				" SACL.selfAppraisalCandidate.tdpCadre.firstname " +//1
		   		   		" from " +
				   		" SelfAppraisalCandidateLocationNew SACL,SelfAppraisalCandidateDayTour SACT " +
				   		" where SACL.selfAppraisalCandidate.selfAppraisalCandidateId = SACT.selfAppraisalCandidate.selfAppraisalCandidateId and  " +
				   		" SACL.selfAppraisalCandidate.isActive = 'Y' and " +  
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) and " +
				   		" SACL.selfAppraisalCandidate.selfAppraisalDesignation.isActive = 'Y' and " +
				   		" SACT.isDeleted='N' " );

		   if(fromDate != null && toDate != null ){
             queryStr.append(" and date(SACT.tourDate) between :fromDate and :toDate ");
		   }
		   
		   if(candidateId !=null && candidateId>0l){
	        	queryStr.append(" and SACL.selfAppraisalCandidate.selfAppraisalCandidateId =:candidateId ");
	        }
		   
		   Query query = getSession().createQuery(queryStr.toString());	
		   

		   if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		   }
		   if(designationIds != null && designationIds.size() > 0){
			   query.setParameterList("designationIds",designationIds);   
		   }
		
		   if(candidateId !=null && candidateId>0l){
			   query.setParameter("candidateId",candidateId);
		   }
		   
		   return query.list();  
	   }
	 
	 public List<Object[]> getCategoryWiseTourSubmittedLeaderDesignation(Date fromDate,Date toDate,String type,List<Long> designationIds){
		 
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		     " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," );
		   if(type.equalsIgnoreCase("Govt")){
			 queryStr.append("model.tourType.tourTypeId,");
		   }else{
			  queryStr.append("model.selfAppraisalTourCategory.selfAppraisalTourCategoryId,"); 
		   }
		   queryStr.append(" count(distinct model.selfAppraisalCandidateId) " +
			 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(fromDate != null && toDate != null ){
                  queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
           }
		   
		   if(designationIds !=null && designationIds.size()>0){
			   queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
		   }
		   
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId");
	      if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(",model.tourTypeId");
		  }else{
			  queryStr.append(",model.selfAppraisalTourCategoryId"); 
		  }
		    queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		  
		  if(designationIds !=null && designationIds.size()>0){
			  query.setParameterList("designationIds", designationIds);
		  }
		  
		  return query.list();
 }
	 public List<Object[]> getCategoryWiseLeaderTourSubmittedCnt(Date fromDate,Date toDate,String type){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
			 " model.selfAppraisalDesignation.selfAppraisalDesignationId," +
			 " model.selfAppraisalDesignation.designation," +
			 " model.selfAppraisalCandidateId," );
			if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" model.selfAppraisalTourCategoryId,");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" model.tourTypeId,");	
			}
			queryStr.append(" count(distinct model.tourDate) " +
			 " from SelfAppraisalCandidateDayTour model where model.isDeleted='N' " +
			 " and model.selfAppraisalDesignation.isActive='Y' ");
		   if(fromDate != null && toDate != null ){
             queryStr.append(" and date(model.tourDate) between :fromDate and :toDate ");
        }
		   queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId,");
		    if(type.equalsIgnoreCase("Category")){
			 queryStr.append(" model.selfAppraisalTourCategoryId");	
			}else if(type.equalsIgnoreCase("Govt")){
			 queryStr.append(" model.tourTypeId");	
			}
		    queryStr.append(" order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		  				   "  model.selfAppraisalCandidateId ");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
		  }
		  return query.list();
 }
}
