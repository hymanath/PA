package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetails;
import com.itgrids.partyanalyst.utils.IConstants;

public class SelfAppraisalCandidateDetailsDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetails, Long> implements ISelfAppraisalCandidateDetailsDAO {
	  public SelfAppraisalCandidateDetailsDAO() {
			super(SelfAppraisalCandidateDetails.class);
	  }

	  public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,List<Long> desigIds){
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
		                if(desigIds != null){
		                	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (:desigIds) ");
		                }
		                queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId ");
		                Query query = getSession().createQuery(queryStr.toString());
		                if(fromDate != null && toDate != null ){
		                	query.setDate("fromDate", fromDate);
		                	query.setDate("toDate", toDate);
		                }
		                if(desigIds != null){  
		     			   query.setParameterList("desigIds",desigIds); 
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
	  }
	  
	  public List<Object[]> getToursDetailstDesignationByBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		     StringBuilder queryStr = new StringBuilder();
		     queryStr.append(" select " +
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		     		        " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +//1
		     		        " sum(model.ownTours)," +//2
		     		        " sum(model.inchargeTours)," +//3
		     		        " count(distinct model.selfAppraisalCandidateDetailsId)" +//4
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
	  public Long getTourCount(Long candidateId,List<Long> locValLst){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select sum(SACD.ownTours) from " +
		  				" SelfAppraisalCandidateDetails SACD " +
		  				" where " +
		  				" SACD.selfAppraisalCandidateId = :candidateId and " +
		  				" SACD.ownLocationValue in (:locValLst) ");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("candidateId", candidateId);
		  query.setParameterList("locValLst", locValLst);
		  return (Long) query.uniqueResult();
	  }
	  public List<Object[]> getCndWiseAndLocValWiseCountList(Date fromDate, Date toDate){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" select SACD.selfAppraisalCandidateId, SACD.ownLocationValue, sum(SACD.ownTours) " +
		  		" from " +
		  		" SelfAppraisalCandidateDetails SACD  ");
		  if(fromDate != null && toDate != null){
			  queryStr.append(" where date(SACD.updatedTime) between (:fromDate) and (:toDate) ");
		  }
		  queryStr.append(" group by " +
		  		" SACD.selfAppraisalCandidateId, SACD.ownLocationValue "); 
		  Query query = getSession().createQuery(queryStr.toString());
		  if(fromDate != null && toDate != null){
			  query.setDate("fromDate", fromDate);
			  query.setDate("toDate",toDate);
		  }
		  return query.list();
	  }
	  public List<Object[]> getToursVisitedDetailsDistrictWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String reportType){
		     StringBuilder queryStr = new StringBuilder();
		     queryStr.append(" select " +
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		     		 		" model.selfAppraisalCandidate.selfAppraisalDesignation.designation," +
		     		 		" model1.selfAppraisalLocationScopeId, ");//1
		     		 		if(reportType != null && reportType.equalsIgnoreCase("MP")){
		     		 		queryStr.append(" model1.userAddress.parliamentConstituency.constituencyId," +//2
	  		     		 				    " model1.userAddress.parliamentConstituency.name,");		
		     		 		}else{
		     		 		  queryStr.append(" model1.userAddress.district.districtId," +//2
		  		     		 				  " model1.userAddress.district.districtName,");	
		     		 		}
		     		 		queryStr.append("model.selfAppraisalCandidate.selfAppraisalCandidateId," +//5
		     		        " count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +//6
		     		        " sum(model.ownTours)," +//7
		     		        " sum(model.inchargeTours)," +//8
		     		        " count(distinct model.selfAppraisalCandidateDetailsId)" +//9
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
				if(reportType != null && reportType.equalsIgnoreCase("MP")){
					  queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
					  				  " model1.userAddress.parliamentConstituency.constituencyId ");
				}else{
					 queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignation.selfAppraisalDesignationId," +
					 		         " model1.userAddress.district.districtId ");	
				}
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
	  
	  public List<Object[]> getToursVisitedDetailsLocationWiseBasedOnUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,String locationType,Long candiateId){
		     StringBuilder queryStr = new StringBuilder();
		     		queryStr.append(" select distinct " );
     		 		if(locationType != null && locationType.equalsIgnoreCase("District")){
     		 		  queryStr.append(" model1.userAddress.district.districtId," +//0
     		 				  		  " model1.userAddress.district.districtName,");//1	
	 		 		}else if(locationType != null && locationType.equalsIgnoreCase("ParliamentConstituency")){
	 			 		queryStr.append(" model1.userAddress.parliamentConstituency.constituencyId," +//0
     		 				            " model1.userAddress.parliamentConstituency.name,");//1		
     		 		}else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
     		 		   queryStr.append(" model1.userAddress.constituency.constituencyId," +//0
     		 		  				   " model1.userAddress.constituency.name,");//1
     		 		}
     		 	    queryStr.append(" count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +//2
     		        " sum(model.ownTours)," +//3
     		        " sum(model.inchargeTours)" +//4
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
			    if(userAccessLevelId != null && userAccessLevelId.longValue()==1l){//district
				   queryStr.append(" and model1.userAddress.district.districtId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==2l){//parliamentConstituency
			        queryStr.append(" and model1.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==3l){//Assembly
			        queryStr.append(" and model1.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
				 }
			     if(candiateId != null && candiateId.longValue() > 0){
			    	 queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalCandidateId=:candiateId");
			     }
			      queryStr.append(" group by ");
			      if(locationType != null && locationType.equalsIgnoreCase("District")){
   		 		     queryStr.append(" model1.userAddress.district.districtId " +
   		 		     				"  order by model1.userAddress.district.districtId ");	
	 		 	  }else if(locationType != null && locationType.equalsIgnoreCase("parliamentConstituency")){
	 			     queryStr.append(" model1.userAddress.parliamentConstituency.constituencyId " +
	 			     				"  order by model1.userAddress.parliamentConstituency.constituencyId");
     		 	  }else if(locationType != null && locationType.equalsIgnoreCase("constituency")){
   		 		   queryStr.append(" model1.userAddress.constituency.constituencyId " +
   		 		   				  "  order by model1.userAddress.constituency.constituencyId ");
   		 	 		}
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
		 		 if(candiateId != null && candiateId.longValue() > 0){
		 			   query.setParameter("candiateId", candiateId);
		 		 }
		 		 return query.list();
	}
	public List<Object[]> getCommendAndFilePathDtls(List<Long> cndIdListForCmtAndFile, Date fromDate, Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.selfAppraisalCandidate.selfAppraisalCandidateId, " +//0
						" model.month, " +//1    
						" model.year, " +//2
						" model.remarks, " +//3
						" model.reportPath " +//4
						" from SelfAppraisalCandidateDetails model where " +
						" model.selfAppraisalCandidate.selfAppraisalCandidateId in (:cndIdListForCmtAndFile) " +
						" and date(model.updatedTime) between (:fromDate) and (:toDate) " );
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("cndIdListForCmtAndFile", cndIdListForCmtAndFile);  
		query.setDate("fromDate",fromDate);
		query.setDate("toDate",toDate);  
		return query.list();
	}
	public List<Object[]> getSubmittedToursDetails(Date startDate, Date endDate, List<Long> desigIdList,Long userAccessLevelId, Set<Long> locationValueSet){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select " +
						" SACD.selfAppraisalCandidate.selfAppraisalDesignationId, " +
						" count(distinct SACD.selfAppraisalCandidate.selfAppraisalCandidateId), " +
						" sum(SACD.ownTours), " +
						" sum(SACD.inchargeTours) " +
						" from SelfAppraisalCandidateDetails SACD, SelfAppraisalCandidateLocation SACL where " +
						" SACD.selfAppraisalCandidate.isActive='Y' " +
						" and date(SACD.updatedTime) between :fromDate and :toDate " +
						" and SACD.selfAppraisalCandidate.selfAppraisalDesignationId in (:desigIdList) " +
						" and SACD.selfAppraisalCandidate.selfAppraisalCandidateId = SACL.selfAppraisalCandidate.selfAppraisalCandidateId ");
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
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			queryStr.append(" and SACL.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			queryStr.append(" and SACL.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			queryStr.append(" and SACL.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		}
		Query query = getSession().createQuery(queryStr.toString());
        if(startDate != null && endDate != null ){
        	query.setDate("fromDate", startDate);
        	query.setDate("toDate", endDate);
        }
        if(desigIdList != null){  
			   query.setParameterList("desigIdList",desigIdList); 
		} 
        if(locationValueSet != null){    
			   query.setParameterList("userAccessLevelValues",locationValueSet); 
		}
        return query.list();
	}
	/* public List<Object[]> getSubmittedToursLeadersDetails(Date fromDate,Date toDate,List<Long> desigIds){
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
		                if(desigIds != null){
		                	queryStr.append(" and model.selfAppraisalCandidate.selfAppraisalDesignationId in (:desigIds) ");
		                }
		                queryStr.append(" group by model.selfAppraisalCandidate.selfAppraisalDesignationId ");
		                Query query = getSession().createQuery(queryStr.toString());
		                if(fromDate != null && toDate != null ){
		                	query.setDate("fromDate", fromDate);
		                	query.setDate("toDate", toDate);
		                }
		                if(desigIds != null){  
		     			   query.setParameterList("desigIds",desigIds); 
		     		    }    
		                return query.list();
	  }*/
	  public List<Object[]> getToursVisitedDetailsByUserAccessLevel(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate){
		     StringBuilder queryStr = new StringBuilder();
		          queryStr.append(" select " );
					     if(userAccessLevelId != null && userAccessLevelId.longValue()==1l){//district 
					 		  queryStr.append(" model1.userAddress.district.districtId," +//0
			   		 				  		 "  model1.userAddress.district.districtName,");//1	
					     }else if(userAccessLevelId != null && userAccessLevelId.longValue()==2l){//parliamentConstituency
					    	  queryStr.append(" model1.userAddress.parliamentConstituency.constituencyId," +//0
			   		 				           " model1.userAddress.parliamentConstituency.name,");//1		
					     }else if(userAccessLevelId != null && userAccessLevelId.longValue()==3){//constituency
					    	  queryStr.append(" model1.userAddress.constituency.constituencyId," +////0
					  				          " model1.userAddress.constituency.name,");//1
					     }
		              	queryStr.append(" count(distinct model.selfAppraisalCandidate.selfAppraisalCandidateId)," +//2
		     		        " sum(model.ownTours)," +//3
		     		        " sum(model.inchargeTours)," +//4
		     		        " count(distinct model.selfAppraisalCandidateDetailsId)" +//5
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
			    if(userAccessLevelId != null && userAccessLevelId.longValue()==1l){
				   queryStr.append(" and model1.userAddress.district.districtId in (:userAccessLevelValues)");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==2l){
			        queryStr.append(" and model1.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==3l){
			        queryStr.append(" and model1.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
				 }
			    if(userAccessLevelId != null && userAccessLevelId.longValue()==1l){
			 		  queryStr.append(" group by model1.userAddress.district.districtId "); 
	 		     }else if(userAccessLevelId != null && userAccessLevelId.longValue()==2l){
			    	  queryStr.append("  group by model1.userAddress.parliamentConstituency.constituencyId " );
	 		     }else if(userAccessLevelId != null && userAccessLevelId.longValue()==3l){
			    	  queryStr.append(" group by model1.userAddress.constituency.constituencyId ");
			     }
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
