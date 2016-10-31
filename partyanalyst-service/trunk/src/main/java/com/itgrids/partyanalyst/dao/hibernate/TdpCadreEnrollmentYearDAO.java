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
	public TdpCadreEnrollmentYear getOnlineTdpCadreEnrollmentYearDetailsByTdpCadreId(Long tdpCadreId,String dataSourceType)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model from TdpCadreEnrollmentYear model where model.tdpCadreId = :tdpCadreId  and ");
		queryStr.append(" model.tdpCadre.isDeleted = 'O' and model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId and model.isDeleted = 'Y' ");
			queryStr.append(" and model.tdpCadre.payMentStatus ='"+IConstants.NOT_PAID_STATUS+"' ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("enrollmentYearId",  IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
		return (TdpCadreEnrollmentYear)query.uniqueResult();
		
	}
	
	public List<Long> getPreviousElectionYearsOfCadre(Long tdpCadreId){
		
		Query query = getSession().createQuery("select model.enrollmentYear.year from TdpCadreEnrollmentYear model " +
				" where model.tdpCadreId = :tdpCadreId and model.enrollmentYear.isActive = 'N'");
		
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
}
