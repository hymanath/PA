package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateProgramDetailsDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateProgramDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateProgramDetailsDAO extends GenericDaoHibernate<SelfAppraisalCandidateProgramDetails, Long>
		implements ISelfAppraisalCandidateProgramDetailsDAO {
        public SelfAppraisalCandidateProgramDetailsDAO(){
        	super(SelfAppraisalCandidateProgramDetails.class);
        }
        public List<Object[]> getDesignationWiseTourProgramVisitedCandidate(List<Long> monthIds,Set<Long> candiateIds){
        	StringBuilder queryStr = new StringBuilder();
        	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 "  model.selfAppraisalDesignation.designation," +
        	 				 "  model.selfAppraisalCandidate.selfAppraisalCandidateId " +
        	 				 " from SelfAppraisalCandidateProgramDetails model " +
        	 				 " where model.isDeleted='N' ");
        	 if(candiateIds != null && candiateIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalCandidateId in (:candiateIds) ");
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) "); 
        	 }
        	 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId ");
        	 Query query = getSession().createQuery(queryStr.toString());
        	 if(candiateIds != null && candiateIds.size() > 0){
        	    query.setParameterList("candiateIds", candiateIds);	 
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
           	   query.setParameterList("monthIds", monthIds);	 
           	 } 
        	 return query.list();
        }
        public List<Object[]> getDesignationWiseTourProgramVisitedDtls(List<Long> monthIds,Set<Long> candiateIds){
        	StringBuilder queryStr = new StringBuilder();
        	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalDesignation.designation," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId," +
        	 				 " model.selfAppraisalProgramId," +
        	 				 " model.selfAppraisalToursMonthId," +
        	 				 " sum(model.tourVisits) " +
        	 				 " from SelfAppraisalCandidateProgramDetails model " +
        	 				 " where model.isDeleted='N' ");
        	 if(candiateIds != null && candiateIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalCandidateId in (:candiateIds) ");
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) "); 
        	 }
        	 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId," +
        	 				 " model.selfAppraisalProgramId," +
        	 				 " model.selfAppraisalToursMonthId " +
        	 				 " order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId," +
        	 				 " model.selfAppraisalProgramId," +
        	 				 " model.selfAppraisalToursMonthId");
        	 Query query = getSession().createQuery(queryStr.toString());
        	 if(candiateIds != null && candiateIds.size() > 0){
        	    query.setParameterList("candiateIds", candiateIds);	 
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
           	   query.setParameterList("monthIds", monthIds);	 
           	 } 
        	 return query.list();
        }
        public List<Object[]> getTourProgramWiseVisitedCandiate(List<Long> monthIds,Set<Long> candiateIds){
        	StringBuilder queryStr = new StringBuilder();
        	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 			     " model.selfAppraisalDesignation.designation," +
        	 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
        	 				 " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId) " +
        	 				 " from SelfAppraisalCandidateProgramDetails model " +
        	 				 " where model.isDeleted='N' ");
        	 if(candiateIds != null && candiateIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalCandidateId in (:candiateIds) ");
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) "); 
        	 }
        	 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalProgramId");
        	 Query query = getSession().createQuery(queryStr.toString());
        	 if(candiateIds != null && candiateIds.size() > 0){
        	    query.setParameterList("candiateIds", candiateIds);	 
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
           	   query.setParameterList("monthIds", monthIds);	 
           	 } 
        	 return query.list();
        }
        public List<Object[]> getMonthWiseTourSubmittedDetails(List<Long> monthYearIds,Long candidateId){
    	    StringBuilder queryStr = new StringBuilder();
    	    queryStr.append(" select " +
    	  					" model.selfAppraisalToursMonth.monthName," + //0
    	  					" model.selfAppraisalProgram.selfAppraisalProgramId," +//1
    	  					" model.selfAppraisalProgram.program," +//2
    	  					" 0," +//3
    	  					"'Program'," +//4
    	  					" model.remarks," +//5
    	  					" model.selfAppraisalDesignation.selfAppraisalDesignationId," +//6
    	  					" model.selfAppraisalDesignation.designation," +//7
    	  					" model.selfAppraisalToursMonth.year," +//8
    	  					" model.tourVisits," +//9
    	  					" model.selfAppraisalToursMonth.selfAppraisalToursMonthId" +//10
    	  					" from SelfAppraisalCandidateProgramDetails model " +
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
       public List<Object[]> getDesignationWiseTourProgramDtls(List<Long> monthIds,List<Long> designationIds,List<Long> candidateIds){
        	 StringBuilder queryStr = new StringBuilder();
        	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalDesignation.designation," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId," +
        	 				 " model.selfAppraisalProgramId," +
        	 				 " model.selfAppraisalToursMonthId," +
        	 				 " sum(model.tourVisits)," +
        	 				 " model.selfAppraisalCandidate.tdpCadreId," +
        	 				 " model.selfAppraisalCandidate.tdpCadre.firstname " +
        	 				 " from SelfAppraisalCandidateProgramDetails model " +
        	 				 " where model.isDeleted='N' ");
        	 if(designationIds != null && designationIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) "); 
        	 }
        	 if(candidateIds != null && candidateIds.size() > 0){
        		 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalCandidateId in (:candidateIds) "); 
        	 }
        	 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId," +
        	 				 " model.selfAppraisalProgramId," +
        	 				 " model.selfAppraisalToursMonthId " +
        	 				 " order by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
        	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId," +
        	 				 " model.selfAppraisalProgramId," +
        	 				 " model.selfAppraisalToursMonthId");
        	 Query query = getSession().createQuery(queryStr.toString());
        	 if(designationIds != null && designationIds.size() > 0){
        	    query.setParameterList("designationIds", designationIds);	 
        	 }
        	 if(monthIds != null && monthIds.size() > 0){
           	   query.setParameterList("monthIds", monthIds);	 
           	 } 
        	 if(candidateIds != null && candidateIds.size() > 0){
             	   query.setParameterList("candidateIds", candidateIds);	 
             } 
        	 return query.list();
        }
       public List<Object[]> getDesignationWiseTourProgramSubmittedCandidateCnt(List<Long> monthIds,List<Long> designationIds){
       	StringBuilder queryStr = new StringBuilder();
       	 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +
       	 				 "  model.selfAppraisalCandidate.selfAppraisalCandidateId " +
       	 				 " from SelfAppraisalCandidateProgramDetails model " +
       	 				 " where model.isDeleted='N' ");
       	 if(designationIds != null && designationIds.size() > 0){
       		 queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) ");
       	 }
       	 if(monthIds != null && monthIds.size() > 0){
       		 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) "); 
       	 }
       	 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
       	 				 " model.selfAppraisalCandidate.selfAppraisalCandidateId ");
       	 Query query = getSession().createQuery(queryStr.toString());
       	 if(designationIds != null && designationIds.size() > 0){
       	    query.setParameterList("designationIds", designationIds);	 
       	 }
       	 if(monthIds != null && monthIds.size() > 0){
          	   query.setParameterList("monthIds", monthIds);	 
          	 } 
       	 return query.list();
       }
       
       @SuppressWarnings("unchecked")
   	public List<Object[]> getToursProgramOverviewByCadre(Long tdpCadreId, Long tourMonthId){
   	       	StringBuilder queryStr = new StringBuilder();
   	       	
   	       	
   	       	queryStr.append(" SELECT model.selfAppraisalCandidateProgramDetailsId,model.selfAppraisalDesignation.selfAppraisalDesignationId," +
   	       			" model.selfAppraisalDesignation.designation,model.selfAppraisalProgram.selfAppraisalProgramId," +
   	       			" model.selfAppraisalProgram.program " +    	       	
   	       			" ,model.tourVisits,model.updatedTime,model.selfAppraisalCandidate.selfAppraisalCandidateId " +
   	       			" FROM " +
   	       			" SelfAppraisalCandidateProgramDetails model ");
   	       	
   	    	queryStr.append( " where " +
   	       			" model.selfAppraisalCandidate.tdpCadreId = :tdpCadreId and model.selfAppraisalToursMonthId = :tourMonthId " +
   	       			" and model.isDeleted ='N' and model.selfAppraisalProgram.isDeleted ='N' ");

   	       	
   	   Query query = getSession().createQuery(queryStr.toString());
   	   
   	   query.setParameter("tdpCadreId", tdpCadreId);
   	   query.setParameter("tourMonthId", tourMonthId);
   	      
          	return query.list();
          }
}
