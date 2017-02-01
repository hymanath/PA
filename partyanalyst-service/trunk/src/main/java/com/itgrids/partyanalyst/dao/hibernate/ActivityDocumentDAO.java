package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityDocumentDAO;
import com.itgrids.partyanalyst.model.ActivityDocument;
import com.itgrids.partyanalyst.utils.IConstants;

public class ActivityDocumentDAO extends GenericDaoHibernate<ActivityDocument, Long> implements IActivityDocumentDAO{

	public ActivityDocumentDAO() {
		super(ActivityDocument.class);
		
	}
	
	public List<Object[]> getImagesCoveredAndTotalImagesCountForConstituencies(List<Long> districtsList,List<Long> activityScopeIdsLis,String  searchType,
			String  type,Long userAccessLevelId,Set<Long> userAccessLevelValues){
		
		
		StringBuilder queryStr = new StringBuilder();
		//type = searchType ;
		queryStr.append("select ");
		
		if(searchType != null && searchType.equalsIgnoreCase("constituency"))
			queryStr.append(" constituency.constituencyId," );
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(" district.districtId," );
		if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(" state.stateId," );
			 /*if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("   model.address.state.stateId,  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append("  model.address.district.districtId , ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append("  model.address.parliamentConstituency.constituencyId , ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("   model.address.constituency.constituencyId , ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append("  model.address.tehsil.tehsilId , ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append("  model.address.constituency.localElectionBody.localElectionBodyId , "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append("  model.address.panchayat.panchayatId , "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append("  model.address.ward.constituencyId ,"); 
			 }*/
		
		if(type != null && type.equalsIgnoreCase("panchayat")){
			queryStr.append(" count(distinct panchayat.panchayatId), ");
		}else if( type != null && type.equalsIgnoreCase("ward")){
			queryStr.append("  count(distinct ward.constituencyId), ");
		}else if( type != null && type.equalsIgnoreCase("mandal")){
			queryStr.append("  count( distinct tehsil.tehsilId), ");
		}else if( type != null && type.equalsIgnoreCase("town")){
			queryStr.append("  count(distinct localElectionBody.localElectionBodyId), ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			queryStr.append("  count(distinct constituency.constituencyId), ");
		}else if(type != null && type.equalsIgnoreCase("district"))
			queryStr.append(" count(distinct district.districtId)," );
		if(type != null && type.equalsIgnoreCase("state"))
			queryStr.append(" count(distinct model.userAddress.state.stateId)," );
		queryStr.append(" count( distinct model1.activityDocumentId) " );
		queryStr.append(" ,model1.activityScope.activityScopeId ");
		queryStr.append("  from ActivityDocument model1,ActivityInfoDocument model " +
				" left join  model.userAddress userAddress " +
				" left join  userAddress.state state " +
				" left join  userAddress.district district " +
				" left join  userAddress.constituency constituency " +
				" left join  userAddress.localElectionBody localElectionBody " +
				" left join  userAddress.tehsil tehsil " +
				" left join  userAddress.ward ward " +
				" left join  userAddress.panchayat panchayat ");
		
		
		queryStr.append(" where model.isDeleted='N'  and " +
				" model1.activityDocumentId = model.activityDocument.activityDocumentId   " );
		
		/*
		if(type != null && type.equalsIgnoreCase("panchayat")){
			queryStr.append(" and  panchayat is not null   and  ward  is null ");
		}else if( type != null && type.equalsIgnoreCase("ward")){
			queryStr.append("  and  ward  is not null and  panchayat is null  ");
		}else if( type != null && type.equalsIgnoreCase("mandal")){
			queryStr.append("  and  tehsil is not null and localElectionBodyId is null  ");
		}else if( type != null && type.equalsIgnoreCase("town")){
			queryStr.append("  and model.userAddress.localElectionBody.localElectionBodyId is not null  and  model.userAddress.tehsil is  null ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			queryStr.append("  and  model.userAddress.constituency.constituencyId is not null ");
		}else if(type != null && type.equalsIgnoreCase("district"))
			queryStr.append(" and  model.userAddress.district.districtId is not null " );
		if(type != null && type.equalsIgnoreCase("state"))
			queryStr.append(" and  model.userAddress.state is not null " );
		*/
		
		queryStr.append(" and model1.activityScope.activityScopeId  in (:activityScopeIdsLis)  ");
		
		if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
			 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("  and state.stateId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append(" and district.districtId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append(" and userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("  and constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append(" and tehsil.tehsilId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append(" and localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append(" and panchayat.panchayatId in (:userAccessLevelValues) "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append(" and ward.constituencyId in (:userAccessLevelValues) "); 
			 }
		}else{
			if(districtsList != null && districtsList.size() > 0l)
				if(districtsList.get(0).longValue()>0L)
					queryStr.append("  and district.districtId in (:districtsList) ");
		}
		queryStr.append(" group by model1.activityScope.activityScopeId ");
		if(searchType != null && searchType.equalsIgnoreCase("constituency"))
			queryStr.append(" , constituency.constituencyId ");
		else if(searchType != null && searchType.equalsIgnoreCase("district"))
			queryStr.append(", district.districtId " );
		if(searchType != null && searchType.equalsIgnoreCase("state"))
			queryStr.append(", state.stateId " );
		/*if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		       queryStr.append("   ,model.address.state.stateId  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			     queryStr.append("  ,model.address.district.districtId  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			     queryStr.append("  ,model.address.parliamentConstituency.constituencyId  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			     queryStr.append("   ,model.address.constituency.constituencyId  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			        queryStr.append("  ,model.address.tehsil.tehsilId  ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			        queryStr.append("  ,model.address.constituency.localElectionBody.localElectionBodyId "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			        queryStr.append("  ,model.address.panchayat.panchayatId  "); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			        queryStr.append("  ,model.address.ward.constituencyId "); 
			 }*/
		
		/*if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("panchayat")){
			queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.panchayat.panchayatId ");
		}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("ward")){
			queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.ward.constituencyId ");
		}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("mandal")){
			queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.tehsil.tehsilId ");
		}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("town")){
			queryStr.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId ");
		}else if(searchType != null && searchType.equalsIgnoreCase("constituency") && type != null && type.equalsIgnoreCase("constituency")){
			queryStr.append(" group by model.userAddress.constituency.constituencyId ");
		}*/
		
		Query query = getSession().createQuery(queryStr.toString());
		
		/*if(districtsList != null && districtsList.size() > 0l)
			if(districtsList.get(0).longValue()>0L)
				query.setParameterList("districtsList",districtsList );*/
		if(userAccessLevelValues != null && userAccessLevelValues.size() >0)
			query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		
		if(activityScopeIdsLis != null && activityScopeIdsLis.size()>0)
			query.setParameterList("activityScopeIdsLis",activityScopeIdsLis );
		
		return query.list();
	
	
}
	
	public List<Object[]> getImagesCoveredAndTotalImagesForConstituencies(List<Long> districtsList,List<Long> activityScopeIdsLis,String  searchType,String  type,Long stateId,Long levelId
			,Long userAccessLevelId,Set<Long> userAccessLevelValues){
		
			
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append("select ");
			
			if(type != null && type.equalsIgnoreCase("constituency")){
				queryStr.append(" model.userAddress.constituency.constituencyId," );
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.panchayat.panchayatId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.tehsil.tehsilId),count(model1.activityDocumentId)" );
		            }
		          }
			}else if(type != null && type.equalsIgnoreCase("localElectn")){
				queryStr.append(" model.userAddress.constituency.constituencyId," );
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.ward.constituencyId),'' " );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.localElectionBody.localElectionBodyId),'' " );
		            }
		         }
			}else if(type != null && type.equalsIgnoreCase("panchayat") || searchType != null && searchType.equalsIgnoreCase("onlyvillage")){
				queryStr.append("  model.userAddress.panchayat.panchayatId, ");
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.panchayat.panchayatId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.tehsil.tehsilId),count(model1.activityDocumentId)" );
		            }
		         }
			}else if(type != null && type.equalsIgnoreCase("ward")){
				queryStr.append("  model.userAddress.ward.constituencyId, ");
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.ward.constituencyId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.localElectionBody.localElectionBodyId),count(model1.activityDocumentId)" );
		            }
		          }
			}else if( type != null && type.equalsIgnoreCase("mandal")){
				queryStr.append("  model.userAddress.tehsil.tehsilId, ");
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.panchayat.panchayatId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.tehsil.tehsilId),count(model1.activityDocumentId)" );
		            }
		          }
			}else if( type != null && type.equalsIgnoreCase("town")){
				queryStr.append("  model.userAddress.localElectionBody.localElectionBodyId, ");
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.ward.constituencyId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.localElectionBody.localElectionBodyId),count(model1.activityDocumentId)" );
		            }
		          }
			}else if(type != null && type.equalsIgnoreCase("district")){
				queryStr.append(" model.userAddress.district.districtId," );
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.panchayat.panchayatId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.tehsil.tehsilId),count(model1.activityDocumentId) " );
		            }
		                    
		          }
			}else if(type != null && type.equalsIgnoreCase("state")){
				queryStr.append(" model.userAddress.state.stateId," );
				if(levelId != null){
		            if(levelId.longValue() == 1L){
		            	queryStr.append(" count(distinct model.userAddress.panchayat.panchayatId),count(model1.activityDocumentId)" );
		            }
		            else if(levelId.longValue() == 2L){
		            	queryStr.append(" count(distinct model.userAddress.tehsil.tehsilId),count(model1.activityDocumentId)" );
		            }
		          }
			}
			
			if(levelId.longValue() == 3L){
            	queryStr.append(" count(distinct model.userAddress.district.districtId) " );
            }
            else if(levelId.longValue() == 4L){
            	queryStr.append(" count(distinct model.userAddress.state.stateId) " );
            }else if(levelId.longValue() == 5L){
            	queryStr.append(" count(distinct model.userAddress.constituency.constituencyId) " ); 
            }
			
			if(type != null && type.equalsIgnoreCase("localElectn"))
        		queryStr.append(" ,count(model1.activityDocumentId)" );
        	else 
        		queryStr.append(" ,'' " );
			
			//queryStr.append(" count(model1.activityDocumentId) " );
			queryStr.append(" ,model1.activityScope.activityScopeId ");
			queryStr.append("  from ActivityDocument model1,ActivityInfoDocument model where model.isDeleted='N'  and " +
					" model1.activityDocumentId = model.activityDocument.activityDocumentId   " );
			
			queryStr.append(" and model1.activityScope.activityScopeId  in (:activityScopeIdsLis)  ");
			if(districtsList == null || districtsList.size() == 0){
			if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
				 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
				       queryStr.append("  and model.userAddress.state.stateId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
					     queryStr.append(" and model.userAddress.district.districtId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
					     queryStr.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
					     queryStr.append("  and model.userAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
					        queryStr.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLevelValues) ");  
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
					        queryStr.append(" and model.userAddress.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
					        queryStr.append(" and model.userAddress.panchayat.panchayatId in (:userAccessLevelValues) "); 
					 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
					        queryStr.append(" and model.userAddress.ward.constituencyId in (:userAccessLevelValues) "); 
					 }
			}}else if(districtsList != null && districtsList.size() > 0l){
				
					if(type != null && type.equalsIgnoreCase("constituency") || type != null && type.equalsIgnoreCase("localElectn"))
						queryStr.append("  and model.userAddress.district.districtId in  (:districtsList) ");
					else if(type != null && type.equalsIgnoreCase("panchayat"))
						queryStr.append("  and model.userAddress.tehsil.tehsilId in  (:districtsList) ");
					else if(type != null && type.equalsIgnoreCase("ward"))
						queryStr.append("  and model.userAddress.localElectionBody.localElectionBodyId in  (:districtsList) ");
					else if( type != null && type.equalsIgnoreCase("mandal")  || type.equalsIgnoreCase("town"))
						queryStr.append("  and model.userAddress.constituency.constituencyId in  (:districtsList) ");
					else if(searchType != null && searchType.equalsIgnoreCase("onlyvillage"))
						queryStr.append("  and model.userAddress.panchayat.panchayatId in  (:districtsList) ");
				}/*if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
					 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
					       queryStr.append("  and model.address.state.stateId in (:userAccessLevelValues) ");  
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
						     queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues) ");  
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
						     queryStr.append(" and model.address.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						     queryStr.append("  and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
						        queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues) ");  
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
						        queryStr.append(" and model.address.constituency.localElectionBody.localElectionBodyId in (:userAccessLevelValues) "); 
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
						        queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues) "); 
						 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
						        queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues) "); 
						 }
					}*/else{
					if(stateId != null && stateId.longValue() == 1l){
						queryStr.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
					}else if(stateId != null && stateId.longValue() == 2l){
						queryStr.append("  and model.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
					}else if(stateId != null && stateId.longValue() == 0l){
						queryStr.append("  and model.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")");
					}
				}
			
			queryStr.append(" group by model1.activityScope.activityScopeId ");
			
			if(type != null && type.equalsIgnoreCase("constituency") || type.equalsIgnoreCase("localElectn"))
				queryStr.append(" ,model.userAddress.constituency.constituencyId " );
			else if(type != null && type.equalsIgnoreCase("panchayat"))
				queryStr.append("  ,model.userAddress.panchayat.panchayatId ");
			else if(type != null && type.equalsIgnoreCase("ward"))
				queryStr.append("  ,model.userAddress.ward.constituencyId  ");
			else if( type != null && type.equalsIgnoreCase("mandal"))
				queryStr.append("  ,model.userAddress.tehsil.tehsilId ");
			else if( type != null && type.equalsIgnoreCase("town"))
				queryStr.append(" , model.userAddress.localElectionBody.localElectionBodyId ");
			else if(type != null && type.equalsIgnoreCase("district"))
				queryStr.append(", model.userAddress.district.districtId " );
			else if(type != null && type.equalsIgnoreCase("state"))
				queryStr.append(", model.userAddress.state.stateId " );
			
			Query query = getSession().createQuery(queryStr.toString());
			
			if(districtsList != null && districtsList.size() > 0l){
				if(districtsList.get(0).longValue()>0L)
					query.setParameterList("districtsList",districtsList );
			}
			if(districtsList == null || districtsList.size() == 0){
				if(userAccessLevelId != null && userAccessLevelId.longValue() > 0l){
					query.setParameterList("userAccessLevelValues",userAccessLevelValues );
				}
			}
			if(activityScopeIdsLis != null && activityScopeIdsLis.size()>0)
				query.setParameterList("activityScopeIdsLis",activityScopeIdsLis );
			
			return query.list();
		
		
	}

}
