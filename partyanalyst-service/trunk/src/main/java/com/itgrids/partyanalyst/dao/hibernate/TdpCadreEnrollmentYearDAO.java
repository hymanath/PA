package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.model.TdpCadreEnrollmentYear;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreEnrollmentYearDAO extends GenericDaoHibernate<TdpCadreEnrollmentYear, Long> implements ITdpCadreEnrollmentYearDAO{

	public TdpCadreEnrollmentYearDAO() {
		super(TdpCadreEnrollmentYear.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TdpCadreEnrollmentYear> getOnlineTdpCadreEnrollmentYearDetailsByTdpCadreId(Long tdpCadreId,String dataSourceType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model from TdpCadreEnrollmentYear model where model.tdpCadreId = :tdpCadreId  and ");
		queryStr.append("  model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId and model.isDeleted = 'Y' ");
			queryStr.append(" and model.tdpCadre.payMentStatus ='"+IConstants.NOT_PAID_STATUS+"' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("enrollmentYearId",  IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
		return query.list();
		
	}
	
	public List<Long> getPreviousElectionYearsOfCadre(Long tdpCadreId){
		
		Query query = getSession().createQuery("select model.enrollmentYear.year from TdpCadreEnrollmentYear model " +
				" where model.tdpCadreId = :tdpCadreId and model.isDeleted='N' and model.tdpCadre.isDeleted='N'  ");
		
		query.setParameter("tdpCadreId",tdpCadreId);
		
		return query.list();
	}
	public Long getMaxRecordFromEnrollmentYear(Long tdpCadreId){
		
		Query query = getSession().createQuery("select max(model.enrollmentYear.year) from TdpCadreEnrollmentYear model " +
				" where model.tdpCadreId = :tdpCadreId ");
		
		query.setParameter("tdpCadreId",tdpCadreId);
		
		return (Long) query.uniqueResult();
	}
	public List<Object[]> getTotalRenewlCadreLocationWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date frmDt, Date toDt){
		StringBuilder queryStr = new StringBuilder();  
		queryStr.append(" select ");   
		
		if(accessLvlId != null && accessLvlId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append(" TC.userAddress.state.stateId, count(distinct TC.tdpCadreId)");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" TC.userAddress.district.districtId, count(distinct TC.tdpCadreId)");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	          queryStr.append(" TC.userAddress.parliamentConstituency.constituencyId, count(distinct TC.tdpCadreId)");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" TC.userAddress.constituency.constituencyId, count(distinct TC.tdpCadreId)");  
		}
		queryStr.append(" from  TdpCadreEnrollmentYear TCEY1, TdpCadreEnrollmentYear TCEY2, TdpCadre TC where" +  
						" TC.tdpCadreId = TCEY1.tdpCadre.tdpCadreId AND " +
						" TC.tdpCadreId = TCEY2.tdpCadre.tdpCadreId AND " +
						" TC.isDeleted = 'N' AND " +
						" TC.enrollmentYear = 2014 AND " +    
						" TCEY1.isDeleted = 'N' AND " +
						" TCEY1.enrollmentYear.enrollmentYearId = 4 AND " +
						" TCEY2.isDeleted = 'N' AND " +
						" TCEY2.enrollmentYear.enrollmentYearId = 3 AND ");
		if(frmDt!= null && toDt!=null){
			  queryStr.append(" date(TC.surveyTime) between :fromDate and :toDate and ");	 
	   	}
		
		if(stateId != null && stateId.longValue() > 0){
			if(stateId.longValue()==1l){
				queryStr.append(" TC.userAddress.district.districtId > 10 and  TC.userAddress.state.stateId = 1  " );
			}else if(stateId.longValue()==36l){
				queryStr.append(" TC.userAddress.district.districtId < 11 ");  
			}
		}   
	  
	   
		if(accessLvlId != null && accessLvlId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			queryStr.append(" group by TC.userAddress.state.stateId ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
            queryStr.append(" group by TC.userAddress.district.districtId ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			queryStr.append(" group by TC.userAddress.parliamentConstituency.constituencyId ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" group by TC.userAddress.constituency.constituencyId ");  
		}
	
		Query query = getSession().createQuery(queryStr.toString());    
		if(frmDt!= null && toDt!=null){
			query.setDate("fromDate", frmDt);
		  	query.setDate("toDate", toDt);
   	  	}  
		
		return query.list();   
	}
	public List<Object[]> getTotalRenewlCadreSourceWise(Long accessLvlId,List<Long> accessLvlValue,Long stateId,Date frmDt, Date toDt){
		StringBuilder queryStr = new StringBuilder();  
		queryStr.append(" select ");
		
		
	    queryStr.append(" TC.dataSourceType, count(distinct TC.tdpCadreId)");  
		
		queryStr.append(" from  TdpCadreEnrollmentYear TCEY1, TdpCadreEnrollmentYear TCEY2, TdpCadre TC where" +
						" TC.tdpCadreId = TCEY1.tdpCadre.tdpCadreId AND " +
						" TC.tdpCadreId = TCEY2.tdpCadre.tdpCadreId AND " +
						" TC.isDeleted = 'N' AND " +
						" TC.enrollmentYear = 2014 AND " +    
						" TCEY1.isDeleted = 'N' AND " +
						" TCEY1.enrollmentYear.enrollmentYearId = 4 AND " +
						" TCEY2.isDeleted = 'N' AND " +
						" TCEY2.enrollmentYear.enrollmentYearId = 3 AND ");
		if(frmDt!= null && toDt!=null){
			  queryStr.append(" date(TC.surveyTime) between :fromDate and :toDate and ");  	 
	   	}
		if(accessLvlId != null && accessLvlId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append(" TC.userAddress.state.stateId in (:accessLvlValue) and ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" TC.userAddress.district.districtId in (:accessLvlValue) and ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	          queryStr.append(" TC.userAddress.parliamentConstituency.constituencyId in (:accessLvlValue) and ");  
		}else if(accessLvlId != null && accessLvlId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" TC.userAddress.constituency.constituencyId in (:accessLvlValue) and  ");  
		}
		if(stateId != null && stateId.longValue() > 0){
			if(stateId.longValue()==1l){
				queryStr.append(" TC.userAddress.district.districtId > 10 and  TC.userAddress.state.stateId = 1  " );
			}else if(stateId.longValue()==36l){
				queryStr.append(" TC.userAddress.district.districtId < 11 ");
			}
		}  
		queryStr.append(" group by TC.dataSourceType ");  
		
		Query query = getSession().createQuery(queryStr.toString());    
		if(frmDt!= null && toDt!=null){
			query.setDate("fromDate", frmDt);
		  	query.setDate("toDate", toDt);
   	  	}
		if(accessLvlId != null){
			query.setParameterList("accessLvlValue", accessLvlValue);
		}
		return query.list();   
	}
	
	public List<Object[]> getTotalRenewlCadreBasedOnUserType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Long userType,Date fromDate,Date toDate){
	
		   StringBuilder queryStr = new StringBuilder();  
		   queryStr.append(" select ");
		 
		  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	   	      queryStr.append(" TC.userAddress.district.districtId,");
	      }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	     	  || userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
	   	 	  queryStr.append(" TC.userAddress.constituency.constituencyId,");
		   }
		 queryStr.append(" count(TC.tdpCadreId)");
         queryStr.append(" from  TdpCadreEnrollmentYear TCEY1, TdpCadreEnrollmentYear TCEY2, TdpCadre TC where" +
				" TC.tdpCadreId = TCEY1.tdpCadre.tdpCadreId AND " +
				" TC.tdpCadreId = TCEY2.tdpCadre.tdpCadreId AND " +
				" TC.isDeleted = 'N' AND " +
				" TC.enrollmentYear = 2014 AND " +    
				" TCEY1.isDeleted = 'N' AND " +
				" TCEY1.enrollmentYear.enrollmentYearId = 4 AND " +
				" TCEY2.isDeleted = 'N' AND " +
				" TCEY2.enrollmentYear.enrollmentYearId = 3 ");
		  if(stateId != null && stateId.longValue() > 0){
				 if(stateId != null && stateId.longValue() > 0){
					   if(stateId.longValue()==1l){
							queryStr.append(" and TC.userAddress.district.districtId > 10 and  TC.userAddress.state.stateId = 1 ");
						}else if(stateId.longValue()==36l){
							queryStr.append(" and  TC.userAddress.district.districtId < 11 ");
						}
				 } 
		   }
	  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(TC.surveyTime) between :fromDate and :toDate ");	 
	  }
	  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
        queryStr.append(" and TC.userAddress.state.stateId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
            queryStr.append(" and TC.userAddress.district.districtId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
         queryStr.append(" and TC.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
         queryStr.append(" and TC.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
            queryStr.append(" and TC.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" and TC.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" and TC.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" and TC.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	  }
	   if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
    	  queryStr.append(" group by TC.userAddress.district.districtId");
       }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
    	|| userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
    	 	  queryStr.append("group by TC.userAddress.constituency.constituencyId");
	   }
		   Query query = getSession().createQuery(queryStr.toString());    
		   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		     query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	       }
		   if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			 }
		return query.list();   
	}
	
	public List<Object[]> getTotalRenewlCadreCntLocationWise(Long stateId,String locationType,Date fromDate,Date toDate,Long userAccessLevelId,List<Long> userAccessLevelValues){
		
		    StringBuilder queryStr = new StringBuilder(); 
		   
	        queryStr.append(" select ");
	        if(locationType != null && locationType.equalsIgnoreCase("District")){
		           queryStr.append(" TC.userAddress.district.districtId,");
	   	      }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	    	 	   queryStr.append(" TC.userAddress.constituency.constituencyId,");
			  }
	         queryStr.append("count(TC.tdpCadreId)");
	         queryStr.append(" from  TdpCadreEnrollmentYear TCEY1, TdpCadreEnrollmentYear TCEY2, TdpCadre TC where" +
					" TC.tdpCadreId = TCEY1.tdpCadre.tdpCadreId AND " +
					" TC.tdpCadreId = TCEY2.tdpCadre.tdpCadreId AND " +
					" TC.isDeleted = 'N' AND " +
					" TC.enrollmentYear = 2014 AND " +    
					" TCEY1.isDeleted = 'N' AND " +
					" TCEY1.enrollmentYear.enrollmentYearId = 4 AND " +
					" TCEY2.isDeleted = 'N' AND " +
					" TCEY2.enrollmentYear.enrollmentYearId = 3 ");
		  if(stateId != null && stateId.longValue() > 0){
				 if(stateId != null && stateId.longValue() > 0){
					   if(stateId.longValue()==1l){
							queryStr.append(" and TC.userAddress.district.districtId > 10 and  TC.userAddress.state.stateId = 1 ");
						}else if(stateId.longValue()==36l){
							queryStr.append(" and  TC.userAddress.district.districtId < 11 ");
						}
				 } 
		   }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(TC.surveyTime) between :fromDate and :toDate ");	 
		 }
		      if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		        queryStr.append(" and TC.userAddress.state.stateId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		            queryStr.append(" and TC.userAddress.district.districtId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		         queryStr.append(" and TC.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		         queryStr.append(" and TC.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		            queryStr.append(" and TC.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			         queryStr.append(" and TC.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			         queryStr.append(" and TC.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			         queryStr.append(" and TC.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
			  }
		  
	      if(locationType != null && locationType.equalsIgnoreCase("District")){
	           queryStr.append(" group by TC.userAddress.district.districtId ");
          }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
    	 	   queryStr.append(" group by TC.userAddress.constituency.constituencyId");
		  }
		   Query query = getSession().createQuery(queryStr.toString()); 
		   if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			 }
		     if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			     query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		       }
		return query.list();   
	}

	  public List<Object[]> getCadreRegistrationCountByDataSourceType(Date fromDate,Date toDate,Long stateId){
		  
		     StringBuilder queryStr = new StringBuilder();
		     
		     queryStr.append(" select model.tdpCadre.dataSourceType," +
		     		         " count(distinct model.tdpCadre.tdpCadreId) " +
		     		         " from TdpCadreEnrollmentYear model " +
		     		         " where " +
		     		         " model.tdpCadre.enrollmentYear='2014' and model.tdpCadre.isDeleted='N'" +
				             " and model.enrollmentYearId= 4 and model.isDeleted='N' ");
		     
		     if(stateId != null && stateId.longValue() == 1l){
		    	    queryStr.append("  and model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId != null && stateId.longValue() == 36l){
					queryStr.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
				}else if(stateId != null && stateId.longValue() == 0l){
					queryStr.append(" and model.tdpCadre.userAddress.state.stateId = 1 ");
				} 
		     
		     if(fromDate!= null && toDate!=null){
				  queryStr.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
			 }
		      
		     queryStr.append(" group by model.tdpCadre.dataSourceType ");
		      
		     Query query = getSession().createQuery(queryStr.toString());
		    
		     if(fromDate!= null && toDate!=null){
				   query.setDate("fromDate", fromDate);
				   query.setDate("toDate", toDate);
				 }
		     return query.list();
	  }
	  public List<Object[]> getCadreRegistrationCountByCadreVerificationStatus(Date fromDate,Date toDate,Long stateId){
		  
		     StringBuilder queryStr = new StringBuilder();
		     
		     queryStr.append(" select model.tdpCadre.cadreVerificationStatusId,model.tdpCadre.dataSourceType," +
		     		         " count(distinct model.tdpCadre.tdpCadreId) " +
		     		         " from TdpCadreEnrollmentYear model " +
		     		         " where " +
		     		         " model.tdpCadre.enrollmentYear='2014' and model.tdpCadre.isDeleted='N' " +
				             " and model.enrollmentYearId= 4 and model.isDeleted='N' ");
		     
		     	if(stateId != null && stateId.longValue() == 1l){
		    	    queryStr.append("  and  model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId != null && stateId.longValue() == 36l){
					queryStr.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
				}else if(stateId != null && stateId.longValue() == 0l){
					queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
				}
		     	
		     if(fromDate!= null && toDate!=null){
				  queryStr.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
			 }
		      queryStr.append(" group by model.tdpCadre.cadreVerificationStatusId,model.tdpCadre.dataSourceType order by model.tdpCadre.cadreVerificationStatusId asc ");
			   
		     Query query = getSession().createQuery(queryStr.toString());
		     
		     if(fromDate!= null && toDate!=null){
				   query.setDate("fromDate", fromDate);
				   query.setDate("toDate", toDate);
				 }
		     return query.list();
	  }
	  public List<Object[]> getTabCadreRegistrationCountLastOneHoursUserWise(Date date,String dataSourceType,String verificationStatus,Long stateId){
		  
		        StringBuilder queryStr = new StringBuilder();
		     
		         queryStr.append(" select " +
		         		         " model.tdpCadre.insertedUserId," +//0
		         		         " model.tdpCadre.tabUserInfoId," +//1
			     		         " count(distinct model.tdpCadre.tdpCadreId) " +//2
			     		         " from TdpCadreEnrollmentYear model " +
			     		         " where " +
			     		         " model.tdpCadre.enrollmentYear='2014' and model.tdpCadre.isDeleted='N'" +
					             " and model.enrollmentYearId= 4 and model.isDeleted='N' "); 
		     
			     if(date!= null && date!=null){
					  queryStr.append(" and model.tdpCadre.surveyTime >=:lastOneHour ");	 
				 }
		    
			     	if(stateId != null && stateId.longValue() == 1l){
			    	    queryStr.append("  and  model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
					}else if(stateId != null && stateId.longValue() == 36l){
						queryStr.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
					}else if(stateId != null && stateId.longValue() == 0l){
						queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
					}
			     
		    	 if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Approved")){
		    	   queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =1 ");	 
		    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Rejected")){
		    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =2 "); 
		    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Pending")){
		    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId is null "); 
		    	 }
		    	 if(dataSourceType != null && dataSourceType.equalsIgnoreCase("TAB")){
                    queryStr.append(" and model.tdpCadre.dataSourceType='TAB' ") ;
		    	 }
		         queryStr.append(" group by model.tdpCadre.insertedBy,model.tdpCadre.tabUserInfoId" +
		         		        "  order by model.tdpCadre.insertedBy,model.tdpCadre.tabUserInfoId ");
		      
		         Query query = getSession().createQuery(queryStr.toString());
		    
			     if(date!= null && date!=null){
					 query.setParameter("lastOneHour", date);
				 }
		     return query.list();
	  }
	  public List<Object[]> getTabCadreRegistrationCountUserWise(Date fromDate,Date toDate,String dataSourceType,String verificationStatus,Long stateId){
		  
	       StringBuilder queryStr = new StringBuilder();
	     
	         queryStr.append(" select " +
	         		         " model.tdpCadre.insertedBy.cadreSurveyUserId," +//0
	         		         " model.tdpCadre.insertedBy.userName," +//1
	         		         " model.tdpCadre.tabUserInfo.tabUserInfoId," +//2
	         		         " model.tdpCadre.tabUserInfo.name," +//3
	         		         " model.tdpCadre.tabUserInfo.mobileNo," +//4
	         		         " model.tdpCadre.cadreVerificationStatusId, " +//5
		     		         " count(distinct model.tdpCadre.tdpCadreId) " +//6
		     		         " from TdpCadreEnrollmentYear model " +
		     		         " where " +
		     		         " model.tdpCadre.enrollmentYear='2014' and model.tdpCadre.isDeleted='N'" +
				             " and model.enrollmentYearId= 4 and model.isDeleted='N' "); 
	     
	         if(fromDate!= null && toDate!=null){
				  queryStr.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
			 }
		      
	         	if(stateId != null && stateId.longValue() == 1l){
		    	    queryStr.append("  and  model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId != null && stateId.longValue() == 36l){
					queryStr.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
				}else if(stateId != null && stateId.longValue() == 0l){
					queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
				}
	         
	    	 if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Approved")){
	    	   queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =1 ");	 
	    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Rejected")){
	    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =2 "); 
	    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Pending")){
	    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId is null "); 
	    	 }
	    	 if(dataSourceType != null && dataSourceType.equalsIgnoreCase("TAB")){
               queryStr.append(" and model.tdpCadre.dataSourceType='TAB' ") ;
	    	 }
	         queryStr.append(" group by model.tdpCadre.insertedBy,model.tdpCadre.tabUserInfoId, model.tdpCadre.cadreVerificationStatusId " +
	         		         " order by model.tdpCadre.insertedBy,model.tdpCadre.tabUserInfoId, model.tdpCadre.cadreVerificationStatusId ");
	      
	         Query query = getSession().createQuery(queryStr.toString());
	    
            if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			 }
	     return query.list();
 }
	  public List<Object[]> getWebAndOnlineCadreRegistrationCountLastOneHoursUserWise(Date date,String dataSourceType,String verificationStatus,Long stateId){
		  
	       StringBuilder queryStr = new StringBuilder();
	     
	         queryStr.append(" select " +
	         		         " model.tdpCadre.insertedWebUserId," +
		     		         " count(distinct model.tdpCadre.tdpCadreId) " +
		     		         " from TdpCadreEnrollmentYear model " +
		     		         " where " +
		     		         " model.tdpCadre.enrollmentYear='2014' and model.tdpCadre.isDeleted='N'" +
				             " and model.enrollmentYearId= 4 and model.isDeleted='N' "); 
	     
		     if(date!= null && date!=null){
				  queryStr.append(" and model.tdpCadre.surveyTime >=:lastOneHour ");	 
			 }
	    
		     	if(stateId != null && stateId.longValue() == 1l){
		    	    queryStr.append("  and  model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId != null && stateId.longValue() == 36l){
					queryStr.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
				}else if(stateId != null && stateId.longValue() == 0l){
					queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
				}
		     
	    	 if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Approved")){
	    	   queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =1 ");	 
	    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Rejected")){
	    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =2 "); 
	    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Pending")){
	    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId is null "); 
	    	 }
	    	 if(dataSourceType != null && dataSourceType.trim().length() > 0){
               queryStr.append(" and model.tdpCadre.dataSourceType=:dataSourceType ") ;
	    	 }
	         queryStr.append(" group by model.tdpCadre.insertedWebUserId order by model.tdpCadre.insertedWebUserId ");
	      
	         Query query = getSession().createQuery(queryStr.toString());
	    
		     if(date!= null && date!=null){
				   query.setParameter("lastOneHour", date);
			 }
		     if(dataSourceType != null && dataSourceType.trim().length() > 0){
		    	 query.setParameter("dataSourceType", dataSourceType); 
		     }
	     return query.list();
 }
	  public List<Object[]> getWebAndOnlineCadreRegistrationCountUserWise(Date fromDate,Date toDate,String dataSourceType,String verificationStatus,Long stateId){
		  
	       StringBuilder queryStr = new StringBuilder();
	     
	         queryStr.append(" select " +
	         		         " model.tdpCadre.insertedWebUser.userId," +//0
	         		         " model.tdpCadre.insertedWebUser.userName," +//1
	         		         " model.tdpCadre.insertedWebUser.mobile," +//2
	         		         " model.tdpCadre.cadreVerificationStatusId," +//3
		     		         " count(distinct model.tdpCadre.tdpCadreId) " +//4
		     		         " from TdpCadreEnrollmentYear model " +
		     		         " where " +
		     		         " model.tdpCadre.enrollmentYear='2014' and model.tdpCadre.isDeleted='N'" +
				             " and model.enrollmentYearId= 4 and model.isDeleted='N' "); 
	     
	         if(fromDate!= null && toDate!=null){
				  queryStr.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
			 }
	         
	         	if(stateId != null && stateId.longValue() == 1l){
		    	    queryStr.append("  and  model.tdpCadre.userAddress.district.districtId between 11 and 23 ");
				}else if(stateId != null && stateId.longValue() == 36l){
					queryStr.append(" and  model.tdpCadre.userAddress.district.districtId between 1 and 10 ");
				}else if(stateId != null && stateId.longValue() == 0l){
					queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
				}
	         
		     if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Approved")){
	    	   queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =1 ");	 
	    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Rejected")){
	    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId =2 "); 
	    	 }else if(verificationStatus != null && verificationStatus.equalsIgnoreCase("Pending")){
	    		 queryStr.append(" and model.tdpCadre.cadreVerificationStatusId is null "); 
	    	 }
	    	 if(dataSourceType != null && dataSourceType.trim().length() > 0){
              queryStr.append(" and model.tdpCadre.dataSourceType=:dataSourceType ") ;
	    	 }
	         queryStr.append(" group by model.tdpCadre.insertedWebUserId,model.tdpCadre.cadreVerificationStatusId " +
	         		         " order by model.tdpCadre.insertedWebUserId,model.tdpCadre.cadreVerificationStatusId ");
	      
	         Query query = getSession().createQuery(queryStr.toString());
	    
	         if(fromDate!= null && toDate!=null){
				   query.setDate("fromDate", fromDate);
				   query.setDate("toDate", toDate);
			 }
		     if(dataSourceType != null && dataSourceType.trim().length() > 0){
		    	 query.setParameter("dataSourceType", dataSourceType); 
		     }
	     return query.list();
	  }
	  public List<Object[]> getInFieldCount(Date today){  
		  StringBuilder queryStr = new StringBuilder();
	      queryStr.append("select TCEY.tdpCadre.userAddress.district.districtId, count(distinct TCEY.tdpCadre.insertedUserId) from " +
	      		" TdpCadreEnrollmentYear TCEY where ");
	      queryStr.append(" TCEY.tdpCadre.userAddress.district.districtId between 1 and 23 ");
	      queryStr.append(" and TCEY.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.enrollmentYear = 2014 " +
	      		" and TCEY.enrollmentYearId = 4 " +   
	      		" and TCEY.tdpCadre.surveyTime > (:today) " +
	      		" group by TCEY.tdpCadre.userAddress.district.districtId ");  
	      Query query = getSession().createQuery(queryStr.toString());
	      query.setParameter("today", today);
	      return query.list();      
	  }
	  public List<Object[]> getTodayFieldCount(Date today){  
		  StringBuilder queryStr = new StringBuilder();
	      queryStr.append("select TCEY.tdpCadre.userAddress.district.districtId, count(distinct TCEY.tdpCadre.insertedUserId) from " +
	      		" TdpCadreEnrollmentYear TCEY where ");
	      queryStr.append(" TCEY.tdpCadre.userAddress.district.districtId between 1 and 23 ");
	      
	      queryStr.append(" and TCEY.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.enrollmentYear = 2014 " +
	      		" and TCEY.enrollmentYearId = 4 " +  
	      		" and date(TCEY.tdpCadre.surveyTime) = (:today) " +
	      		" group by TCEY.tdpCadre.userAddress.district.districtId ");  
	      Query query = getSession().createQuery(queryStr.toString());
	      query.setDate("today", today);      
	      return query.list();          
	  }
	  /*SELECT COUNT(DISTINCT TC.created_by) FROM tdp_cadre_enrollment_year EY,tdp_cadre TC,user_address UA
	  WHERE
	  EY.tdp_cadre_id = TC.tdp_cadre_id AND
	  TC.address_id = UA.user_address_id AND
	  TC.is_deleted = 'N' AND
	  TC.enrollment_year = 2014 AND
	  EY.enrollment_year_id = 4 AND
	  EY.is_deleted = 'N' AND 
	  UA.district_id BETWEEN 11 AND 23 AND
	  TC.survey_time > '2016-11-01 16:23:00';*/
	  
	  public List<Object[]> getHourWiseUserPerformanceInfo(Long cadreSurveyUserId,Long tabUserId,Date dayBfrYes,Date today){
		  /**
		   * select date(tc.survey_time),hour(tc.survey_time),count(tc.tdp_cadre_id) 
				from tdp_cadre_enrollment_year ey,tdp_cadre tc
				where ey.tdp_cadre_id = tc.tdp_cadre_id and ey.enrollment_year_id = 4
							and ey.is_deleted = 'N' and tc.enrollment_year = 2014 
				            and tc.is_deleted = 'N' and date(tc.survey_time) between '2016-10-30' and '2016-11-01'
				group by hour(tc.survey_time),date(tc.survey_time)
				order by date(tc.survey_time),hour(tc.survey_time);
		   */
		  StringBuilder sb = new StringBuilder();
		  sb.append("select date(tc.survey_time),hour(tc.survey_time),count(tc.tdp_cadre_id)" +
		  					" from tdp_cadre_enrollment_year ey,tdp_cadre tc" +
		  					" where ey.tdp_cadre_id = tc.tdp_cadre_id");
		  if(dayBfrYes != null && today != null)
			  sb.append(" and (date(tc.survey_time) between :dayBfrYes and :today)");
		  sb.append(" and tc.created_by = :cadreSurveyUserId and tc.tab_user_info_id = :tabUserId" +
		  					" and ey.enrollment_year_id = 4" +
		  					" and ey.is_deleted = 'N' and tc.enrollment_year = 2014" +
		  					" and tc.is_deleted = 'N'" +
		  					" group by hour(tc.survey_time),date(tc.survey_time),tc.tab_user_info_id,tc.created_by" +
		  					" order by date(tc.survey_time),hour(tc.survey_time)");
		  Query query = getSession().createSQLQuery(sb.toString());
		  if(dayBfrYes != null && today != null){
			  query.setParameter("dayBfrYes", dayBfrYes);
			  query.setParameter("today", today);
		  }
		  query.setParameter("cadreSurveyUserId", cadreSurveyUserId);
		  query.setParameter("tabUserId", tabUserId);
		  return query.list();
	  }
	  public List<Object[]> getDistWiseCountList(){
		  StringBuilder sb = new StringBuilder();
		  sb.append("select TCEY.tdpCadre.userAddress.district.districtId, TCEY.tdpCadre.dataSourceType, count(istinct TCEY.tdpCadre.tdpCadreId) from " +
		  		" TdpCadreEnrollmentYear TCEY where " +
		  		" TCEY.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.enrollmentYear = 2014 " +
	      		" and TCEY.enrollmentYearId = 4 " +
	      		" and TCEY.tdpCadre.userAddress.district.districtId between 1 and 23 " +
	      		" group by TCEY.tdpCadre.userAddress.district.districtId, TCEY.tdpCadre.dataSourceType " +
	      		" order by TCEY.tdpCadre.userAddress.district.districtId ");
		  Query query = getSession().createQuery(sb.toString());
		  return query.list();
	  }
	  public List<Object[]> getDistWiseRenewCountList(){
		  StringBuilder sb = new StringBuilder();
		  sb.append("select TC.userAddress.district.districtId, TC.dataSourceType, count(istinct TC.tdpCadreId) from " +
		  		" TdpCadreEnrollmentYear TCEY1, TdpCadreEnrollmentYear TCEY2, TdpCadre TC where " +
		  		" TCEY1.tdpCadreId = TC.tdpCadreId " +
		  		" and TCEY1.isDeleted = 'N' " +
	      		" and TC.isDeleted = 'N' " +
	      		" and TC.enrollmentYear = 2014 " +
	      		" and TCEY1.enrollmentYearId = 4 " +
	      		" and TCEY2.tdpCadreId = TC.tdpCadreId " +
		  		" and TCEY2.isDeleted = 'N' " +
	      		" and TCEY2.enrollmentYearId = 3 " +
	      		" and TCEY1.tdpCadreId = TCEY2.tdpCadreId " +  
	      		" and TC.userAddress.district.districtId between 1 and 23 " +
	      		" group by TC.userAddress.district.districtId, TC.dataSourceType " +
	      		" order by TC.userAddress.district.districtId ");
		  Query query = getSession().createQuery(sb.toString());
		  return query.list();
	  }
	 
	  public List<Object[]> getConstWiseCountList(){
		  StringBuilder sb = new StringBuilder();
		  sb.append("select TCEY.tdpCadre.userAddress.constituency.constituencyId, TCEY.tdpCadre.dataSourceType, count(istinct TCEY.tdpCadre.tdpCadreId) from " +
		  		" TdpCadreEnrollmentYear TCEY where " +
		  		" TCEY.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.isDeleted = 'N' " +
	      		" and TCEY.tdpCadre.enrollmentYear = 2014 " +
	      		" and TCEY.enrollmentYearId = 4 " +
	      		" and TCEY.tdpCadre.userAddress.district.districtId between 1 and 23 " +
	      		" and TCEY.tdpCadre.userAddress.constituency.deformDate is null " +
	      		" and TCEY.tdpCadre.userAddress.constituency.electionScope.electionScopeId = 2 " +
	      		" group by TCEY.tdpCadre.userAddress.constituency.constituencyId, TCEY.tdpCadre.dataSourceType " +
	      		" order by TCEY.tdpCadre.userAddress.constituency.constituencyId ");
		  Query query = getSession().createQuery(sb.toString());
		  return query.list();
	  }
	  public List<Object[]> getConstWiseRenewCountList(){
		  StringBuilder sb = new StringBuilder();
		  sb.append("select TC.userAddress.constituency.constituencyId, TC.dataSourceType, count(istinct TC.tdpCadreId) from " +
		  		" TdpCadreEnrollmentYear TCEY1, TdpCadreEnrollmentYear TCEY1, TdpCadre TC where " +
		  		" TCEY1.tdpCadreId = TC.tdpCadreId " +
		  		" and TCEY1.isDeleted = 'N' " +
	      		" and TC.isDeleted = 'N' " +
	      		" and TC.enrollmentYear = 2014 " +
	      		" and TCEY1.enrollmentYearId = 4 " +
	      		" and TCEY2.tdpCadreId = TC.tdpCadreId " +
		  		" and TCEY2.isDeleted = 'N' " +
	      		" and TCEY2.enrollmentYearId = 3 " +
	      		" and TCEY1.tdpCadreId = TCEY2.tdpCadreId " +  
	      		" and TC.userAddress.district.districtId between 1 and 23 " +
	      		" and TC.userAddress.constituency.deformDate is null " +
	      		" and TC.userAddress.constituency.electionScope.electionScopeId = 2 " +
	      		" group by TC.userAddress.constituency.constituencyId, TC.dataSourceType " +
	      		" order by TC.userAddress.constituency.constituencyId ");
		  Query query = getSession().createQuery(sb.toString());
		  return query.list();
	  }
	
	  public List<Object[]> getCadreDetailsBySearch(String searchType,Long searchValue,String locationType,Long locationVal,String gender,Long minAge,Long maxAge){
			StringBuilder sb = new StringBuilder();
			sb.append("select model.tdpCadre.tdpCadreId ,model.tdpCadre.firstname," +
					" model.tdpCadre.mobileNo,model.tdpCadre.userAddress.constituency.name," +
					" model.tdpCadre.memberShipNo, voter.voterIDCardNo, model.tdpCadre.image " +
					" from TdpCadreEnrollmentYear model" +
					" LEFT JOIN model.tdpCadre.voter voter" +
					" where model.isDeleted = 'N' and model.enrollmentYearId = 4" +
					" and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014");
			if(searchType != null && searchType.trim().equalsIgnoreCase("caste"))
				sb.append(" and model.tdpCadre.casteStateId = :searchValue");
			else if(searchType != null && searchType.trim().equalsIgnoreCase("gender"))
				sb.append(" and model.tdpCadre.gender = :gender");
			else if(searchType != null && searchType.trim().equalsIgnoreCase("age"))
				sb.append(" and model.tdpCadre.age between :minAge and :maxAge");
			else if(searchType != null && searchType.equalsIgnoreCase("casteGroup"))
				sb.append(" and model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId = :searchValue");
			else if(searchType != null && searchType.trim().equalsIgnoreCase("education"))
				sb.append(" and model.tdpCadre.educationalQualifications.eduQualificationId = :searchValue");
			
			if(locationType != null && locationType.trim().equalsIgnoreCase("mandal"))
				sb.append(" and model.tdpCadre.userAddress.tehsil.tehsilId = :locationVal");
			else if(locationType != null && locationType.trim().equalsIgnoreCase("muncipality"))
				sb.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :locationVal");
			
			Query query = getSession().createQuery(sb.toString());
			if(searchType != null && searchType.trim().equalsIgnoreCase("gender"))
				query.setParameter("gender", gender);
			else if(searchType != null && searchType.trim().equalsIgnoreCase("age")){
				query.setParameter("minAge", minAge);
				query.setParameter("maxAge", maxAge);
			}
			else
				query.setParameter("searchValue", searchValue);
			
			if(locationType != null && locationVal != null)
				query.setParameter("locationVal", locationVal);
			
			
			return query.list();
		}
	  
	  public List<Object[]> getCadrAddressDetailsByCadred(Long tdpCadreId,Long yearId) {
			StringBuilder queryString=new StringBuilder();
			queryString.append( " select model.tdpCadre.tdpCadreId, " +//0
								" state.stateId," +//1
								" state.stateName," +//2
								" district.districtId," +//3
								" district.districtName,"+//4
					            " constituency.constituencyId," +//5
					            " constituency.name," +//6
					            " tehsil.tehsilId," +//7
					            " tehsil.tehsilName,"+//8
								" ward.constituencyId," +//9
								" ward.name," +//10
								" panchayat.panchayatId," +//11
								" panchayat.panchayatName,"+//12
					            " localElectionBody.localElectionBodyId," +//13
					            " localElectionBody.name ,"+ //14
								" constituency.areaType ," +//15
								" booth.boothId, " +//16
								" booth.partNo, " +//17
								" voter.voterId," +//18
								" familyVoter.voterId, " +//19
								" booth.publicationDate.publicationDateId, " +//20
								" state.stateId, " +//21
								" state.stateName " +//22
								" from  TdpCadreEnrollmentYear  model " +
								" left join model.tdpCadre.familyVoter familyVoter " +
								" left join model.tdpCadre.voter voter " +
								" left join model.tdpCadre.userAddress.state state" +
								" left join model.tdpCadre.userAddress.district district" +
								" left join model.tdpCadre.userAddress.constituency constituency" +
								" left join model.tdpCadre.userAddress.tehsil tehsil" +
								" left join model.tdpCadre.userAddress.ward ward" +
								" left join model.tdpCadre.userAddress.panchayat panchayat" +
								" left join model.tdpCadre.userAddress.localElectionBody localElectionBody " +
								" left join model.tdpCadre.userAddress.booth booth " +
								" left join model.tdpCadre.userAddress.state state " +
								" where " +
								" model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=:enrollmentYear and model.tdpCadre.tdpCadreId=:tdpCadreId" +
								" and model.isDeleted = 'N' and model.enrollmentYear.enrollmentYearId = :yearId   " );
								//" and model.enrollmentYear.isActive = 'Y' ");
			
				Query query=getSession().createQuery(queryString.toString());
				
				query.setParameter("tdpCadreId", tdpCadreId);
				query.setParameter("yearId", yearId);
				query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
			
			return  query.list();
		}
	  @SuppressWarnings("unchecked")
		public List<Object[]> getEnrolledDetailsByTdpCadreId(List<Long> tdpCadreList)
		{
			Query query=getSession().createQuery(" select model.tdpCadreId,model.enrollmentYearId from  TdpCadreEnrollmentYear model where" +
					" model.tdpCadreId in (:tdpCadreList) and model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N'  ");
		
			query.setParameterList("tdpCadreList", tdpCadreList);
			return query.list();
			
		}
		
		public List<Object[]> getLatestEnrollmentYearForCadreIds(List<Long> tdpCadreIds){
			Query query = getSession().createQuery(" select model.tdpCadreId,model.enrollmentYear.year " +
					" from TdpCadreEnrollmentYear model " +
					" where model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and model.tdpCadreId in (:tdpCadreIds) " +
					" group by model.tdpCadreId order by model.enrollmentYearId desc ");
			query.setParameterList("tdpCadreIds", tdpCadreIds);
			return query.list();
		}
}
