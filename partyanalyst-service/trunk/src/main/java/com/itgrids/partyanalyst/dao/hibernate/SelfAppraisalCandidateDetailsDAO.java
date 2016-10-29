package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateDetailsDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetails, Long> implements
		ISelfAppraisalCandidateDetailsDAO {
	  public SelfAppraisalCandidateDetailsDAO() {
			super(SelfAppraisalCandidateDetails.class);
	  }
	  
	  public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,Long desigId){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append( " select " +
		    		       " model.selfAppraisalCandidate.selfAppraisalDesignationId," +
		    		       " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +
		    		       " sum(model.ownTours)," +
		    		       " sum(model.inchargeTours) " +
		    		       " from SelfAppraisalCandidateDetails model " +
		    		       " where model.selfAppraisalCandidate.isActive='Y' "); 
		                if(fromDate != null && toDate != null ){
		                	queryStr.append(" and date(model.updatedTime) between :fromDate and :toDate ");
		                }
		                if(desigId != null){
		                	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId = :desigId ");
		                }
		                queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId ");
		                Query query = getSession().createQuery(queryStr.toString());
		                if(fromDate != null && toDate != null ){
		                	query.setDate("fromDate", fromDate);
		                	query.setDate("toDate", toDate);
		                }
		                if(desigId != null){
		     			   query.setParameter("desigId",desigId); 
		     		    }    
		                return query.list();
	  }
	  public List<Object[]> getCandidateDtlsList(Date startDate, Date endDate,List<Long> candidateList){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
		  				  " SACD.selfAppraisalCandidateDetailsId, " +
		  				  " SACD.selfAppraisalCandidate.selfAppraisalCandidateId, " +
		  				  " SACD.selfAppraisalCandidate.tdpCadre.firstname, " +
		  				  " SACD.month, " +
		  				  " SACD.year, " +
		  				  " SACD.ownTours, " +
		  				  " SACD.inchargeTours," +    
		  				  " SACD.remarks, " +
		  				  " SACD.reportPath " +          
		  				  " from " +
		  				  " SelfAppraisalCandidateDetails SACD " +  
		  				  " where " +
		  				  " SACD.selfAppraisalCandidate.selfAppraisalCandidateId in (:candidateList) and " +
		  				  " date(SACD.updatedTime) between :fromDate and :toDate ");
		  Query query = getSession().createQuery(queryStr.toString());
		  if(startDate != null && endDate != null ){
			  query.setDate("fromDate", startDate);
			  query.setDate("toDate", endDate);
          }
		  if(candidateList != null && candidateList.size() > 0){
			  query.setParameterList("candidateList",candidateList);
		  }
		  
		  return query.list();
	  }
	  public List<Object[]> getMemberDtls(Long candidateDtlsId){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select " +
				  		  " SACD.selfAppraisalCandidateId, " +//0
				  		  " SACD.month, " +//1
				  		  " SACD.year, " +//2
				  		  " SACD.ownLocationValue, " +//3
				  		  " SACD.ownLocationScopeId, " +//4
				  		  " SACD.ownTours, " +//5
				  		  " SACD.inchargeLocationValue, " +//6
				  		  " SACD.inchargeLocationScopeId, " +//7
				  		  " SACD.inchargeTours, " +//8
				  		  " SACD.remarks, " +//9
				  		  " SACD.reportPath " +//10
				  		  " from " +
				  		  " SelfAppraisalCandidateDetails SACD " +
				  		  " where " +
				  		  " SACD.selfAppraisalCandidateDetailsId = :candidateDtlsId");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("candidateDtlsId",candidateDtlsId);
		  return query.list();  
	  
	  public List<Object[]> getToursDetailstDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		     StringBuilder queryStr = new StringBuilder();
		     queryStr.append(" select " +
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		     		        " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +//1
		     		        " sum(model.ownTours)," +//2
		     		        " sum(model.inchargeTours)" +//3
		     		        " from SelfAppraisalCandidateDetails model,SelfAppraisalCandidateLocation model1 " +
		     		        " where model.selfAppraisalCandidate.selfAppraisalCandidateId=model1.selfAppraisalCandidate.selfAppraisalCandidateId " +
		     		        " and " +
		     		        " model.selfAppraisalCandidate.isActive='Y' " +
		     		        " and model.selfAppraisalCandidate.selfAppraisalDesignation.isActive='Y' ");
			      if(stateId != null && stateId.longValue() > 0){
							queryStr.append(" and model1.userAddress.state.stateId =:stateId ");
				  }
			      if(fromDate != null && toDate != null ){
                     queryStr.append(" and date(model.updatedTime) between :fromDate and :toDate ");
                  }
			    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
				   queryStr.append(" and model1.userAddress.state.stateId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				   queryStr.append(" and model1.userAddress.district.districtId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			        queryStr.append(" and model1.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			        queryStr.append(" and model1.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				    queryStr.append(" and model1.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				    queryStr.append(" and model1.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				    queryStr.append(" and model1.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				    queryStr.append(" and model1.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
				 }
		     	
		     	 queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId ");
		     	  Query query = getSession().createQuery(queryStr.toString());
		     		
		 		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		 			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 		 }
		 		if(stateId != null && stateId.longValue() > 0){
		 			 query.setParameter("stateId", stateId);
		 		}
		 		 if(fromDate!= null && toDate!=null){
		 			   query.setDate("fromDate", fromDate);
		 			   query.setDate("toDate", toDate);
		 		 }
		 		 return query.list();
	   }
}
