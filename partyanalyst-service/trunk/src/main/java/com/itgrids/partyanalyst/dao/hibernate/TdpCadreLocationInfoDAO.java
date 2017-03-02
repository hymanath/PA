package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreLocationInfoDAO extends GenericDaoHibernate<TdpCadreLocationInfo, Long> implements ITdpCadreLocationInfoDAO{

	public TdpCadreLocationInfoDAO() {
		super(TdpCadreLocationInfo.class);
	}
	
	public int deleteAllRecords(List<Long> locationScopeIds){
    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_location_info where location_scope_id in (:locationScopeIds) ");
    	query.setParameterList("locationScopeIds",locationScopeIds);
    	return query.executeUpdate();
    }
	public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_location_info AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
    }
    public List<Object[]> get2014TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue){ 
		
	      StringBuilder queryStr = new StringBuilder();  
	       
	      queryStr.append(" select model.locationValue,sum(model.cadre2014) from TdpCadreLocationInfo model where model.locationScopeId =:locationScopeId and model.type='Total' ");
	     
	      if(locationValue != null && locationValue.size() > 0){
	    	queryStr.append(" and model.locationValue in (:locationValue)");  
	      }
		  queryStr.append(" group by model.locationValue ");
		  
		  Query query = getSession().createQuery(queryStr.toString());
		    query.setParameter("locationScopeId", locationScopeId);
		    
		  if(locationValue != null && locationValue.size() > 0){
			query.setParameterList("locationValue", locationValue);  
		  }
		  
	     return query.list();
	}
    public List<Object[]> get2014TotalCadreCountBasedOnUserType(List<Long> locationValue,Long userTypeId,Long activityMemberId){
    	   
              StringBuilder queryStr = new StringBuilder(); 
              queryStr.append("select distinct ");
              if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	            	  if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
	              		 queryStr.append(" model1.district.districtId,"); 
	              	  }else{
	              		 queryStr.append(" model2.districtId,");
	              	  }   
              }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
        	   || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
        	   || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
	            	  if(activityMemberId != null && activityMemberId.longValue()==53l){
	             		  queryStr.append(" model4.constituencyId,");	  
	                  }else{
	            		  queryStr.append(" model1.constituencyId,");	  
	            	  }
              }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID ){
            	    queryStr.append(" model3.assemblyId,") ; 
              }
              queryStr.append("  sum(model.cadre2014) from TdpCadreLocationInfo model ");
             if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	           	  if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
	           		 queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model.locationScopeId=4 and model1.electionScope.electionScopeId=2 and model1.deformDate is null ");; 
	           	  }else{
	           		 queryStr.append(" ,District model2 where model2.districtId = model.locationValue and model.locationScopeId=3 ");
	           	  }
             }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
       	     || userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
       	     || userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
	              if(activityMemberId != null && activityMemberId.longValue()==53l){//53 ActivityMemberId person is one District President.He has access some constituencies Those Constituencies has mapped in this Table.
	            	  queryStr.append(" ,DistrictConstituencies model4 where model4.constituencyId=model.locationValue and model.locationScopeId=4 ");
	              }else{
	            	  queryStr.append(" ,Constituency model1 where model1.constituencyId = model.locationValue and model1.electionScope.electionScopeId=2 and model.locationScopeId=4 and model1.deformDate is null ");  
	              }
           	 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
           	  queryStr.append(" ,ParliamentAssembly model3 where model3.assemblyId = model.locationValue and model.locationScopeId=4 ");	 
           	 }
             if(userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID ){
	           	   if(locationValue != null && locationValue.size() > 0){
		           		 if(activityMemberId != null && activityMemberId.longValue()==53l){
		           			 queryStr.append("  and model4.districtId in (:locationValue) ");
		           		 }else{
		           			 queryStr.append("  and model1.district.districtId in (:locationValue) "); 
		           		 }
	         	  }
	           	  if(activityMemberId != null && activityMemberId.longValue()==50l){//50 ActivityMemberId person is one District President.He has Access only this constituency(354,355,356,357,358,368).so that we are removing remaining constituencies
         	    	  queryStr.append(" and model1.constituencyId not in (133,134,135,136,137,138,140,141,359)");
         	      }
         	 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
         		 if(locationValue != null && locationValue.size() > 0){
         			 queryStr.append(" and model3.parliamentId in (:locationValue)"); 
         		 } 
         	 }else if(userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID && locationValue != null && locationValue.size() > 0){
         		queryStr.append(" and model1.district.districtId in (:locationValue) ");
         	 }else if(locationValue != null && locationValue.size() > 0){
          	 	queryStr.append(" and model.locationValue in (:locationValue)");  
          	 }
              queryStr.append(" and model.type='Total' ");
         	 if(userTypeId != null && userTypeId.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userTypeId.longValue()==IConstants.STATE_TYPE_USER_ID || userTypeId.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
        		  if(activityMemberId != null && activityMemberId.longValue()==4l || activityMemberId.longValue()==5l){
               		 queryStr.append(" group by model1.district.districtId ");; 
               	  }else{
               		 queryStr.append(" group by model2.districtId ");
               	  }   
        	 }else if(userTypeId != null && userTypeId.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userTypeId.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
            	|| userTypeId.longValue()==IConstants.MLA_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userTypeId.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID 
            	|| userTypeId.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
        		 if(activityMemberId != null && activityMemberId.longValue()==53l){
        			 queryStr.append("  group by model4.constituencyId ");
        		 }else{
        			 queryStr.append("  group by model1.constituencyId ");	 
        		 }
        	 }else if(userTypeId.longValue()==IConstants.MP_USER_TYPE_ID){
        		  queryStr.append("  group by model3.assemblyId "); 
        	 }
	      
		  Query query = getSession().createQuery(queryStr.toString());
		  if(locationValue != null && locationValue.size() > 0){
			query.setParameterList("locationValue", locationValue);  
		  }
		   return query.list();
    }
public List<Object[]> getLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO){
		
		try {
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select distinct ");
				
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  district.districtId,district.districtName as name ,'','','','',model.cadre2014,model.cadre2014Percent," +
						" model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId,constituency.name as name ,constituency.areaType,'','','',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId  as name  ,booth.partNo ,'POLLINGSTATION','','','',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" booth.tehsil.tehsilId , booth.tehsil.tehsilName  as name  ,'RURAL','','','',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" '','','',localElectionBody.localElectionBodyId,localElectionBody.name,'"+IConstants.MUNCIPALITY_CORPORATION_LEVEL+"',model.cadre2014,model.cadre2014Percent" +
						" ,model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent  ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
				queryStr.append(" booth.panchayat.panchayatId , booth.panchayat.panchayatName  as name  ,'Panchayat','','','',model.cadre2014,model.cadre2014Percent" +
						",model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				/*queryStr.append(" booth.boothId  as name  ,booth.partNo ,'POLLINGSTATION','','','',model.cadre2014,model.cadre2014Percent" +
						",model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");*/
				queryStr.append(" booth.panchayat.panchayatId , booth.panchayat.panchayatName  as name  ,'Panchayat','','','',model.cadre2014,model.cadre2014Percent" +
						",model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" '','','',booth.localBody.localElectionBodyId,booth.name,'"+IConstants.MUNCIPALITY_CORPORATION_LEVEL+"',model.cadre2014,model.cadre2014Percent" +
						",model.cadre2016,model.cadre2016Percent,model.newCadre,model.newCadrePercent,model.renewalCadre,model.renewalCadrePercent");
			}
			
			queryStr.append(" from ");
			queryStr.append(" TdpCadreLocationInfo model ");
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append("  ,District district ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){			
				queryStr.append(" ,Constituency constituency ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){			
				queryStr.append(" ,Booth booth ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" ,Booth booth ");
			}
			queryStr.append(" where  model.type ='Total' ");
			
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && 
					 inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L ){
				queryStr.append(" and model.locationScopeId = 5 and model.locationValue = booth.tehsil.tehsilId  ");
				queryStr.append(" and booth.constituency.district.districtId = :districtId   ");
			}
			else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L)
			{
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					queryStr.append(" and model.locationScopeId = 3 and model.locationValue = district.districtId and district.state.stateId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append("  and district.districtId = :childLocationTypeId ");
					}
				}
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					queryStr.append(" and model.locationScopeId = 4 and model.locationValue = constituency.constituencyId  ");
					if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
						queryStr.append(" and (constituency.district.districtId between 11 and 23) ");
					else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
						queryStr.append(" and (constituency.district.districtId between 1 and 10) ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and constituency.constituencyId = :childLocationTypeId ");
					}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					
						queryStr.append(" and booth.constituency.constituencyId = :parentLocationTypeId ");
						if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)){
							queryStr.append(" and model.locationScopeId = 9 and model.locationValue = booth.boothId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.boothId = :childLocationTypeId ");
							}
						}
						else if(inputVO.getAreaType().equalsIgnoreCase(IConstants.RURAL) ){
							queryStr.append(" and model.locationScopeId = 5 and model.locationValue = booth.tehsil.tehsilId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append(" and booth.tehsil.tehsilId = :childLocationTypeId ");
							}
						}
						else if((inputVO.getAreaType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ) ){
							queryStr.append(" and model.locationScopeId = 7 and model.locationValue = booth.localBody.localElectionBodyId  ");
							if(inputVO.getChildLocationTypeId().longValue()>0L){
								queryStr.append("  and booth.localBody.localElectionBodyId = :childLocationTypeId ");
							}
						}
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append("  and  model.locationScopeId = 6 and model.locationValue = booth.panchayat.panchayatId and  booth.tehsil.tehsilId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and  booth.tehsil.tehsilId = :childLocationTypeId ");
					}
				}/*else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					queryStr.append(" and  model.locationScopeId = 7 and model.locationValue = booth.localBody.localElectionBodyId and booth.localBody.localElectionBodyId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and booth.localBody.localElectionBodyId = :childLocationTypeId ");
					}
				}*/else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT) || inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					/*queryStr.append(" and  model.locationScopeId = 6  and model.locationValue = booth.boothId and booth.panchayat.panchayatId = :parentLocationTypeId ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and booth.boothId = :childLocationTypeId ");
					}*/
					
					queryStr.append("  and  model.locationScopeId = 6 and model.locationValue = booth.panchayat.panchayatId and  booth.tehsil.tehsilId = :parentLocationTypeId and booth.publicationDate.publicationDateId = "+IConstants.AFFILIATED_VOTER_PUBLICATION_ID+" ");
					if(inputVO.getChildLocationTypeId().longValue()>0L){
						queryStr.append(" and  booth.tehsil.tehsilId = :childLocationTypeId ");
					}
				}
			}
			
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
			if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 1L)
				queryStr.append(" and (district.districtId between 11 and 23) ");
			else if(inputVO.getStateId() != null && inputVO.getStateId().longValue() == 2L)
				queryStr.append(" and (district.districtId between 1 and 10) ");
			}
			
			queryStr.append(" group by ");
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" district.districtId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" constituency.constituencyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
					queryStr.append(" booth.boothId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL))
					queryStr.append(" booth.tehsil.tehsilId ");
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL))
					queryStr.append(" booth.localBody.localElectionBodyId ");
			}
			else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					queryStr.append(" booth.tehsil.tehsilId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
				queryStr.append(" booth.localBody.localElectionBodyId ");
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" booth.panchayat.panchayatId  ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			if(inputVO.getParentLocationTypeId() != null && inputVO.getParentLocationTypeId().longValue()>0L && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT))
				query.setParameter("parentLocationTypeId", inputVO.getParentLocationTypeId());
			if(inputVO.getChildLocationTypeId() != null && inputVO.getChildLocationTypeId().longValue()>0L)
				query.setParameter("childLocationTypeId", inputVO.getChildLocationTypeId());
			if(inputVO.getParentLocationType() != null &&  inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && 
					 inputVO.getDistrictId() != null && inputVO.getDistrictId().longValue()>0L )
				query.setParameter("districtId", inputVO.getDistrictId());
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();  
		}  
		return null;
	}
    public List<Object[]> get2014TotalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId){
        
    	StringBuilder queryStr = new StringBuilder();  
        
    	if(locationValue != null && locationValue.size() > 0){
    		queryStr.append(" select model.locationValue,sum(model.cadre2014) from TdpCadreLocationInfo model where model.locationScopeId =:locationScopeId " +
    				" and model.type = 'Total' ");
        }
        if(locationValue != null && locationValue.size() > 0){
        	queryStr.append(" and model.locationValue in (:locationValue) ");  
        }  
        
        queryStr.append(" group by model.locationValue order by model.locationValue asc");
      
        Query query = getSession().createQuery(queryStr.toString());
        query.setParameter("locationScopeId", locationScopeId);  
        
        if(locationValue != null && locationValue.size() > 0){
        	query.setParameterList("locationValue", locationValue);  
        }
      
        return query.list();
    }
    
public int insertTdpCadreLocationInfoUpToConstituencyLevel(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  INSERT INTO tdp_cadre_location_info( type,location_scope_id,location_value,cadre_2014,cadre_2014_percent," +
    	"                                       cadre_2016,cadre_2016_percent,new_cadre,new_cadre_percent,renewal_cadre," +
    	"                                       renewal_cadre_percent,inserted_time) " +
        "         SELECT TEMP.type,TEMP.location_scope_id,TEMP.location_value,TEMP.cadre_2014,TEMP.cadre_2014_percent," +
        "                TEMP.cadre_2016,TEMP.cadre_2016_percent,TEMP.new_cadre,TEMP.new_cadre_percent,TEMP.renewal_cadre,TEMP.renewal_cadre_percent,TEMP.inserted_time " +
        "         FROM   tdp_cadre_location_info_temp TEMP " );
    	return query.executeUpdate();
    }
    
    public int insertTdpCadreLocationInfoUpToLowLevel(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  INSERT INTO tdp_cadre_location_info( type,location_scope_id,location_value,cadre_2014,cadre_2014_percent," +
    	"                                       cadre_2016,cadre_2016_percent,new_cadre,new_cadre_percent,renewal_cadre," +
    	"                                       renewal_cadre_percent,inserted_time) " +
        "         SELECT TEMP.type,TEMP.location_scope_id,TEMP.location_value,TEMP.cadre_2014,TEMP.cadre_2014_percent," +
        "                TEMP.cadre_2016,TEMP.cadre_2016_percent,TEMP.new_cadre,TEMP.new_cadre_percent,TEMP.renewal_cadre,TEMP.renewal_cadre_percent,TEMP.inserted_time " +
        "         FROM   tdp_cadre_location_info_temp1 TEMP " );
    	return query.executeUpdate();
    }
    public List<Object[]> get2016LocationWiseRegisteredCountsForConstituency(String type,Long locationScopeId,String locationType, List<Long> locationValue,Long districtId){ 
    	StringBuilder sb = new StringBuilder();  
    	sb.append("	  select " +
    				" model.locationValue," +//0
    				" model.cadre2014," +//1
    				" model.cadre2014Percent," +//2
    				" model.cadre2016," +//3 
    				" model.cadre2016Percent," +//4
    				" model.newCadre," +//5
    				" model.newCadrePercent," +//6
    				" model.renewalCadre," +//7
    				" model.renewalCadrePercent," +//8
    				" model.locationScopeId," +//9
    				" model.type" +//10
					" from " +
					" TdpCadreLocationInfo model");
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l))
    		sb.append(" ,Constituency C");
					
    	sb.append(" where");  
    	
    	if(locationScopeId != null && locationScopeId.longValue() > 0l)
    		sb.append(" model.locationScopeId = :locationScopeId");  
    	
    	if(locationScopeId != null && locationScopeId.longValue() == 3l)
    		sb.append(" and model.locationValue = C.district.districtId");
    	else if(locationScopeId != null && locationScopeId.longValue() == 4l)
    		sb.append(" and model.locationValue = C.constituencyId");
    	
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l)){
    		if(locationType != null && locationType.equalsIgnoreCase("AP")){
    			if(districtId != null && districtId.longValue() > 0l){
    				sb.append(" and C.district.districtId=:districtId and C.district.districtId between 11 and 23");
    			}else{
    				sb.append(" and C.district.districtId between 11 and 23");
    			}
    		}
    			
    		else if(locationType != null && locationType.equalsIgnoreCase("TS"))
    			if(districtId != null && districtId.longValue() > 0l){
    				sb.append(" and C.district.districtId=:districtId and C.district.districtId between 1 and 10");
    			}else{
    				sb.append(" and C.district.districtId between 1 and 10");
    			}
    			
    	}
    	
    	if(!(locationType != null)){
    		sb.append(" and C.district.districtId between 1 and 23");    
    	}  
    	
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l)){
    		sb.append(" and C.deformDate is null and C.electionScope.electionScopeId = 2");
    	}
    		
    	if(type != null && type.equalsIgnoreCase("Total"))
    		sb.append(" and model.type = 'Total'");
    	else if(type != null && type.equalsIgnoreCase("Today"))
    		sb.append(" and model.type = 'Today'");
    	if(locationValue != null){
    		sb.append(" and model.locationValue in (:locationValue) ");
    	}
    	
    	sb.append(" group by model.locationValue");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(locationScopeId != null && locationScopeId.longValue() > 0l)
    		query.setParameter("locationScopeId", locationScopeId);
    	if(locationValue != null){
    		query.setParameterList("locationValue", locationValue);
    	}
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l)){
    		if(districtId != null && districtId.longValue() > 0l){
    			query.setParameter("districtId", districtId);
    		}
    	}
    		return query.list();
    }
    public List<Object[]> get2016LocationWiseRegisteredCounts(String type,Long locationScopeId,String locationType, List<Long> locationValue){ 
    	StringBuilder sb = new StringBuilder();  
    	sb.append("	  select " +
    				" model.locationValue," +//0
    				" model.cadre2014," +//1
    				" model.cadre2014Percent," +//2
    				" model.cadre2016," +//3 
    				" model.cadre2016Percent," +//4
    				" model.newCadre," +//5
    				" model.newCadrePercent," +//6
    				" model.renewalCadre," +//7
    				" model.renewalCadrePercent," +//8
    				" model.locationScopeId," +//9
    				" model.type" +//10
					" from " +
					" TdpCadreLocationInfo model");
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l))
    		sb.append(" ,Constituency C");
					
    	sb.append(" where");  
    	
    	if(locationScopeId != null && locationScopeId.longValue() > 0l)
    		sb.append(" model.locationScopeId = :locationScopeId");  
    	
    	if(locationScopeId != null && locationScopeId.longValue() == 3l)
    		sb.append(" and model.locationValue = C.district.districtId");
    	else if(locationScopeId != null && locationScopeId.longValue() == 4l)
    		sb.append(" and model.locationValue = C.constituencyId");
    	
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l)){
    		if(locationType != null && locationType.equalsIgnoreCase("AP"))
    			sb.append(" and C.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");
    		else if(locationType != null && locationType.equalsIgnoreCase("TS"))
    			sb.append(" and C.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
    	}
    	
    	if(!(locationType != null)){
    		sb.append(" and C.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");    
    	}  
    	
    	if(locationScopeId != null && (locationScopeId.longValue() == 3l || locationScopeId.longValue() == 4l)){
    		sb.append(" and C.deformDate is null and C.electionScope.electionScopeId = 2");
    	}
    		
    	if(type != null && type.equalsIgnoreCase("Total"))   
    		sb.append(" and model.type = 'Total'");
    	else if(type != null && type.equalsIgnoreCase("Today"))
    		sb.append(" and model.type = 'Today'");
    	if(locationValue != null){
    		sb.append(" and model.locationValue in (:locationValue) ");
    	}
    	
    	sb.append(" group by model.locationValue");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(locationScopeId != null && locationScopeId.longValue() > 0l)
    		query.setParameter("locationScopeId", locationScopeId);
    	if(locationValue != null){
    		query.setParameterList("locationValue", locationValue);
    	}
    	return query.list();
    }
    
    public List<Object[]> getDataSourceTypeWiseRegisteredDetails(Date fromDate,Date toDate){
    	StringBuilder sb = new StringBuilder();
    	sb.append("select model.tdpCadre.dataSourceType," +
    				" model.enrollmentYearId," +
		     		" count(distinct model.tdpCadre.tdpCadreId)" +
    				" from TdpCadreEnrollmentYear model" +
    				" where model.tdpCadre.enrollmentYear='2014'" +
    				" and model.tdpCadre.isDeleted='N'" +
				    " and model.enrollmentYearId in (3,4)" +
				    " and model.isDeleted='N'" +
				    " and (model.tdpCadre.insertedWebUser.userId not in (3930,7394) or model.tdpCadre.insertedWebUser.userId is null)");
    	if(fromDate!= null && toDate!=null){
			  sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
		 }
    	sb.append(" group by model.tdpCadre.dataSourceType,model.enrollmentYearId");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		}
    	
    	return query.list();
    }
    
    public List<Object[]> getDataSourceTypeWiseRegisteredDetails1(Date fromDate,Date toDate){
    	StringBuilder sb = new StringBuilder();
    	sb.append("select model.tdpCadre.dataSourceType," +
    				" model.tdpCadre.insertedWebUser.userId," +
    				" model.enrollmentYearId," +
		     		" count(distinct model.tdpCadre.tdpCadreId)" +
    				" from TdpCadreEnrollmentYear model" +
    				" where model.tdpCadre.enrollmentYear='2014'" +
    				" and model.tdpCadre.isDeleted='N'" +
				    " and model.enrollmentYearId in (3,4)" +
				    " and model.isDeleted='N'" +
				    " and model.tdpCadre.insertedWebUser.userId in (3930,7394)");
    	if(fromDate!= null && toDate!=null){
			  sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
		 }
    	sb.append(" group by model.tdpCadre.dataSourceType,model.tdpCadre.insertedWebUser.userId,model.enrollmentYearId");
    	
    	Query query = getSession().createQuery(sb.toString());
    	if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		}
    	
    	return query.list();
    }
    
    public List<Object[]> getTdpCadreRecordsCountLocWise(Date fromDate,Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select  model.tdpCadre.dataSourceType," +
    				" count(distinct model.tdpCadre.tdpCadreId)  " +
				  " from    TdpCadreEnrollmentYear model " +
				  " where   model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and "+
				  "         model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId " +
				  " and (model.tdpCadre.insertedWebUser.userId not in (3930,7394) or model.tdpCadre.insertedWebUser.userId is null) " );
		if( fromDate != null && toDate != null){
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");
		}
		sb.append(" group by model.tdpCadre.dataSourceType ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
		if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			}
	    	
		
		return query.list();
	}
    
public List<Object[]> getTdpCadreRecordsCount(Date fromDate,Date toDate){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select  model.tdpCadre.insertedWebUser.userId," +
    				" count(distinct model.tdpCadre.tdpCadreId)  " +
				  " from    TdpCadreEnrollmentYear model " +
				  " where   model.isDeleted = 'N' and model.tdpCadre.isDeleted = 'N' and "+
				  "         model.tdpCadre.enrollmentYear = 2014 and model.enrollmentYearId = :enrollmentYearId " +
				  " and model.tdpCadre.insertedWebUser.userId in (3930,7394) " );
		if( fromDate != null && toDate != null){
			sb.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");
		}
		sb.append(" group by model.tdpCadre.insertedWebUser.userId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("enrollmentYearId",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
		if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			}
	    	
		
		return query.list();
	}

public List<Object[]> getRenewalTdpCadreRecordsCountLocWise(Date fromDate,Date toDate){
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select  tc.dataSourceType,count(distinct tc.tdpCadreId )  " +
			  " from    TdpCadre tc , TdpCadreEnrollmentYear year1, TdpCadreEnrollmentYear year2 " +
			  " where   tc.tdpCadreId = year1.tdpCadre.tdpCadreId and  tc.tdpCadreId = year2.tdpCadre.tdpCadreId and " +
			  "         tc.isDeleted = 'N' and tc.enrollmentYear = 2014 and " +
			  "         year1.isDeleted = 'N' and year1.enrollmentYear.enrollmentYearId = :previousEnrollmentYear and " +
			  "         year2.isDeleted = 'N' and year2.enrollmentYear.enrollmentYearId = :presentEnrollmentYear " +
			  "  and (tc.insertedWebUser.userId not in (3930,7394) or tc.insertedWebUser.userId is null) ");
			  
	if( fromDate != null && toDate != null){
		sb.append(" and date(tc.surveyTime) between :fromDate and :toDate ");
	}
	sb.append(" group by tc.dataSourceType ");
	
	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("previousEnrollmentYear",IConstants.PREVIOUS_CADRE_ENROLLMENT_YEAR);
	query.setParameter("presentEnrollmentYear",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
	if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		}
	
	return query.list();
}

public List<Object[]> getRenewalTdpCadreRecordsCount(Date fromDate,Date toDate){
	
	StringBuilder sb = new StringBuilder();
	sb.append(" select  tc.insertedWebUser.userId,count(distinct tc.tdpCadreId )  " +
			  " from    TdpCadre tc , TdpCadreEnrollmentYear year1, TdpCadreEnrollmentYear year2 " +
			  " where   tc.tdpCadreId = year1.tdpCadre.tdpCadreId and  tc.tdpCadreId = year2.tdpCadre.tdpCadreId and " +
			  "         tc.isDeleted = 'N' and tc.enrollmentYear = 2014 and " +
			  "         year1.isDeleted = 'N' and year1.enrollmentYear.enrollmentYearId = :previousEnrollmentYear and " +
			  "         year2.isDeleted = 'N' and year2.enrollmentYear.enrollmentYearId = :presentEnrollmentYear " +
			  "  and tc.insertedWebUser.userId in (3930,7394) ");
			  
	if( fromDate != null && toDate != null){
		sb.append(" and date(tc.surveyTime) between :fromDate and :toDate ");
	}
	sb.append(" group by tc.insertedWebUser.userId ");
	
	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("previousEnrollmentYear",IConstants.PREVIOUS_CADRE_ENROLLMENT_YEAR);
	query.setParameter("presentEnrollmentYear",IConstants.PRESENT_CADRE_ENROLLMENT_YEAR);
	if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		}
	
	return query.list();
}

public List<Object[]> getLocationWiseTargets(Long locationScopeId){
	Query query = getSession().createQuery("select model.locationValue," +
								" model.targetCount" +
								" from TdpCadreTargetCount model" +
								" where model.locationScopeId = :locationScopeId" +
								" and model.isDeleted = 'N'" +
								" and model.enrollmentYearId = 4");
	query.setParameter("locationScopeId", locationScopeId);
	return query.list();
}
public List<Object[]> getTotalCadreCountLocationWise2014(Long userAccessLevelId,List<Long> userAccessLevelValues){
	StringBuilder queryStr = new StringBuilder();  
    queryStr.append(" select"); 
    if(userAccessLevelId.equals(4l)){
 	   queryStr.append(" C.district.districtId ");
    }else if(userAccessLevelId.equals(10l)){
 	   queryStr.append(" C.district.districtId ");
    }else{
 	   queryStr.append(" model.locationValue ");
    }
   
    queryStr.append(" ,sum(model.cadre2014) " +  
    				" from " +
    				" TdpCadreLocationInfo model ");
    if(userAccessLevelId.equals(4l)){
    	queryStr.append(", Constituency C ");
    }
    if(userAccessLevelId.equals(10l)){
    	queryStr.append(" ,ParliamentAssembly PA, Constituency C ");
    }
    queryStr.append(" where ");
    if(userAccessLevelId.equals(4l)){
    	queryStr.append("  model.locationValue = C.constituencyId and ");
    }else if(userAccessLevelId.equals(10l)){
    	queryStr.append("   model.locationValue = PA.parliamentId " +
    					" and PA.assemblyId = C.constituencyId and ");
    }
    
   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
     	queryStr.append("  model.locationValue in (:locationValue)");  
   }
   if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
	   queryStr.append(" and model.locationScopeId=:locationScopeId ");
   }
   if(userAccessLevelId.equals(4l)){
	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
   }else if(userAccessLevelId.equals(10l)){
	   queryStr.append(" group by C.district.districtId order by C.district.districtId asc ");
   }else{
	   queryStr.append(" group by model.locationValue order by model.locationValue asc ");
   }
    
  
    Query query = getSession().createQuery(queryStr.toString());
    if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
    	query.setParameterList("locationValue", userAccessLevelValues);
    }
    if(userAccessLevelId != null && userAccessLevelId.longValue() > 0){
      query.setParameter("locationScopeId", userAccessLevelId);
    }  
   return query.list();   
}
public List<Object[]> getConstitiuencyWise2014CadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue){
	
	 StringBuilder queryStr = new StringBuilder();  
	    
    queryStr.append(" select distinct ");
    
    if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
    	queryStr.append(" model2.constituencyId,");
    }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
    	queryStr.append(" model3.assemblyId,");
    }else{
   	queryStr.append(" model.locationValue,"); 
    }
    queryStr.append(" sum(model.cadre2014) from TdpCadreLocationInfo model ");
    
   if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
   	queryStr.append(",Constituency model2 where model2.constituencyId = model.locationValue and model2.electionScope.electionScopeId=2 and model2.deformDate is null and model.locationScopeId=4 ");
   }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
   	queryStr.append(",ParliamentAssembly model3 where model3.assemblyId = model.locationValue  and model.locationScopeId=4 ");
   }else {
     queryStr.append(" where model.locationScopeId=4 ");
   }
   if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
   	queryStr.append(" and model2.district.districtId in (:locationValue) ");
   }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID && locationValue != null && locationValue.size() > 0){
   	queryStr.append(" and model3.parliamentId in (:locationValue) ");
   }else {
   	if(locationValue != null && locationValue.size() > 0){
	 	 	queryStr.append(" and model.locationValue in (:locationValue)");  
	    }
   }
   queryStr.append(" and model.type='Total' ");
   if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.DISTRICT_LEVEl_ACCESS_ID){
   	queryStr.append(" group by model2.constituencyId ");
   }else if(userAccessLevelId != null && userAccessLevelId.longValue() == IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
   	queryStr.append(" group by model3.assemblyId ");
   }else {
   	queryStr.append(" group by model.locationValue ");
   }
	  Query query = getSession().createQuery(queryStr.toString());
	  if(locationValue != null && locationValue.size() > 0){
		query.setParameterList("locationValue", locationValue);  
	  }
	  return query.list();
}
public Long getTotalConstituencyForCdrRegStarted(Long stateId){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append("SELECT count( DISTINCT location_value) as count FROM tdp_cadre_location_info I,constituency C WHERE "+
			" I.location_value = C.constituency_id AND "+
			" type = 'Total' AND "+
			" location_scope_id = 4 AND "+
			" cadre_2016 IS NOT null AND "+    
			" cadre_2016 > 0 AND ");
	if(stateId.equals(1l)){
		queryStr.append(" C.district_id BETWEEN 11 AND 23;");
	}else{
		queryStr.append(" C.district_id BETWEEN 1 AND 10;");
	}  
			
	SQLQuery query = getSession().createSQLQuery(queryStr.toString()).addScalar("count", Hibernate.LONG);    
	return (Long)query.uniqueResult();
}
public List<Long> getTodayTotalStartedRegistrationConstituencyStateWise(Long stateId){
      StringBuilder queryStr = new StringBuilder();
      
     queryStr.append(" select distinct model.locationValue from TdpCadreLocationInfo model,Constituency model1 where model1.constituencyId=model.locationValue " +
     	 	         " and model1.deformDate is null and model1.electionScope.electionScopeId=2 and model.locationScopeId=4  and model.type='Today' " +
     	 	         " and model.cadre2016 is not null and model.cadre2016 > 0");
     if(stateId != null && stateId.longValue() == 1l){
    	 queryStr.append(" and model1.district.districtId > 10 and model1.state.stateId=1");
     }else if(stateId != null && stateId.longValue() == 36l){
    	 queryStr.append(" and model1.district.districtId < 11 "); 
     }
     Query query = getSession().createQuery(queryStr.toString());
      return query.list();
}
public List<Object[]> getTodayTotalStartedRegistrationConstituencyDetailsStateWise(Long stateId){
    StringBuilder queryStr = new StringBuilder();
    
   queryStr.append(" select distinct " +
   		         " model1.district.districtId," +
   		         " model1.district.districtName," +
   		         " model1.constituencyId," +
   		         " model1.name," +
   		         " model.cadre2016 " +
   		         " from TdpCadreLocationInfo model,Constituency model1 " +
   		         " where " +
   		         " model1.constituencyId=model.locationValue " +
   	 	         " and model1.deformDate is null and model1.electionScope.electionScopeId=2 and model.locationScopeId=4  and model.type='Today' " +
   	 	         " and model.cadre2016 is not null and model.cadre2016 > 0 ");
   if(stateId != null && stateId.longValue() == 1){
  	 queryStr.append(" and model1.district.districtId > 10 and model1.state.stateId=1");
   }else if(stateId != null && stateId.longValue() == 36){
  	 queryStr.append(" and model1.district.districtId < 11 "); 
   }
   Query query = getSession().createQuery(queryStr.toString());
    return query.list();
}
public Long getTotalCadreCountLocationWise(Long accessLvlId,List<Long> accessLvlValue,String type){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append("select sum(model.cadre2016) from TdpCadreLocationInfo model where " +
			" model.locationScopeId = :accessLvlId and" +
			" model.locationValue in (:accessLvlValue) and " +
			" model.type = :type " );
	Query query = getSession().createQuery(queryStr.toString());
	query.setParameter("accessLvlId", accessLvlId);  
	query.setParameter("type", type); 
	query.setParameterList("accessLvlValue", accessLvlValue);
	return (Long)query.uniqueResult();  
}
public Long getTotalRenewlCadreLocationWise(Long accessLvlId,List<Long> accessLvlValue,String type){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append("select sum(model.renewalCadre) from TdpCadreLocationInfo model where " +
			" model.locationScopeId = :accessLvlId and" +
			" model.locationValue in (:accessLvlValue) and " +  
			" model.type = :type " );
	Query query = getSession().createQuery(queryStr.toString()); 
	query.setParameter("accessLvlId", accessLvlId); 
	query.setParameter("type", type);   
	query.setParameterList("accessLvlValue", accessLvlValue);
	return (Long)query.uniqueResult();    
}
public List<Object[]> get2014CadreBasedOnLocationIds(Long locationScopeId,List<Long> locationIdList){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append("select model.locationValue,model.cadre2014 from TdpCadreLocationInfo model where " +
			" model.locationScopeId = :accessLvlId and" +
			" model.locationValue in (:accessLvlValue) and " +  
			" model.type = 'Total' " ); 
	Query query = getSession().createQuery(queryStr.toString()); 
	query.setParameter("accessLvlId", locationScopeId);
	query.setParameterList("accessLvlValue", locationIdList);
	return query.list();
}
public List<Object[]> getTotalCadreLocationWise(Long locationScopeId, List<Long> locationIdList,String scope){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append("select model.locationValue,model.cadre2016 from TdpCadreLocationInfo model where " +
			" model.locationScopeId = :accessLvlId and" +
			" model.locationValue in (:accessLvlValue) and " +  
			" model.type = :scope " ); 
	Query query = getSession().createQuery(queryStr.toString()); 
	query.setParameter("accessLvlId", locationScopeId);
	query.setParameterList("accessLvlValue", locationIdList);
	query.setParameter("scope", scope);   
	return query.list();
}
public List<Object[]> getTotalRenewlCadreLocationWiseCount(Long accessLvlId,List<Long> accessLvlValue,String type){
	StringBuilder queryStr = new StringBuilder();
	queryStr.append("select model.locationValue,model.renewalCadre from TdpCadreLocationInfo model where " +
			" model.locationScopeId = :accessLvlId and" +
			" model.locationValue in (:accessLvlValue) and " +  
			" model.type = :type ");  
	Query query = getSession().createQuery(queryStr.toString()); 
	query.setParameter("accessLvlId", accessLvlId); 
	query.setParameter("type", type);   
	query.setParameterList("accessLvlValue", accessLvlValue);  
	return query.list();    
}
public List<Long> getTodayMandalStartedStateWise(Long stateId){
    
    StringBuilder queryStr = new StringBuilder();

    queryStr.append(" select " +
    		       " distinct model.locationValue " +
    		       " from TdpCadreLocationInfo model, ConstituencyTehsil model1" +
    		       " where  model1.tehsil.tehsilId=model.locationValue " +
    		       " and model.locationScopeId=5 and model1.isDeleted='N'  " +
    		       " and model.type='Today' and model.cadre2016 > 0 ");
      if(stateId != null && stateId.longValue() == 1l){
	  	 queryStr.append(" and model1.constituency.district.districtId > 10 and model1.constituency.state.stateId=1");
	   }else if(stateId != null && stateId.longValue() == 36l){
	  	 queryStr.append(" and model1.constituency.district.districtId < 11 "); 
	   }else if(stateId != null && stateId.longValue() == 0l){
		   queryStr.append(" and model1.constituency.district.districtId <= 23 ");
	   }
	   Query query = getSession().createQuery(queryStr.toString());
   return query.list();
}
public List<Object[]> getTodayMandalStartedDtlsStateWise(Long stateId){
    
    StringBuilder queryStr = new StringBuilder();

    queryStr.append("select distinct " +
    		       " model1.constituency.district.districtId," +//0
    		       " model1.constituency.district.districtName," +//1
    		       " model1.constituency.constituencyId," +//2
    		       " model1.constituency.name," +//3
    		       " model1.tehsil.tehsilId," +//4
    		       " model1.tehsil.tehsilName," +//5
    		       " model.cadre2016 " +//6
    		       " from TdpCadreLocationInfo model, ConstituencyTehsil model1 " +
    		       " where  " +
    		       " model1.tehsil.tehsilId=model.locationValue and model.locationScopeId=5 and model1.isDeleted='N' " +
    		       " and model.type='Today' and model.cadre2016 > 0 ");
      if(stateId != null && stateId.longValue() == 1l){
	  	 queryStr.append(" and model1.constituency.district.districtId > 10 and model1.constituency.state.stateId=1");
	   }else if(stateId != null && stateId.longValue() == 36l){
	  	 queryStr.append(" and model1.constituency.district.districtId < 11 "); 
	   }
       queryStr.append(" group by model1.tehsil.tehsilId ");
	   Query query = getSession().createQuery(queryStr.toString());
   return query.list();
}
public List<Long> getTodayLocalElectionBodyStartedStateWise(Long stateId){
    
    StringBuilder queryStr = new StringBuilder();

    queryStr.append(" select " +
    		       " distinct model.locationValue " +
    		       " from TdpCadreLocationInfo model, AssemblyLocalElectionBody model1 " +
    		       " where  " +
    		       " model1.localElectionBody.localElectionBodyId=model.locationValue and model.locationScopeId=7 " +
    		       " and model1.localElectionBody.electionType=5 " +
    		       " and model.type='Today' and model.cadre2016 > 0 ");
      if(stateId != null && stateId.longValue() == 1l){
	  	 queryStr.append(" and model1.constituency.district.districtId > 10 and model1.constituency.state.stateId=1");
	   }else if(stateId != null && stateId.longValue() == 36l){
	  	 queryStr.append(" and model1.constituency.district.districtId < 11 "); 
	   }else if(stateId != null && stateId.longValue() == 0l){
		   queryStr.append(" and model1.constituency.district.districtId <= 23 ");
	   }
	   Query query = getSession().createQuery(queryStr.toString());
      return query.list();
}
public List<Object[]> getTodayLocalElectionBodyStartedDtlsStateWise(Long stateId){
    
    StringBuilder queryStr = new StringBuilder();

    queryStr.append(" select distinct " +
    		       "  model1.constituency.district.districtId," +//0
    		       "  model1.constituency.district.districtName," +//1
    		       "  model1.constituency.constituencyId," +//2
    		       "  model1.constituency.name," +//3
    		       "  model1.localElectionBody.localElectionBodyId," +//4
    		       "  model1.localElectionBody.name," +//5
    		       "  model.cadre2016 " +//6
    		       "  from TdpCadreLocationInfo model, AssemblyLocalElectionBody model1 " +
    		       "  where  " +
    		       "  model1.localElectionBody.localElectionBodyId=model.locationValue and model.locationScopeId=7 " +
    		       "  and model1.localElectionBody.electionType=5 " +
    		       "  and model.type='Today' and model.cadre2016 > 0 ");
      if(stateId != null && stateId.longValue() == 1l){
	  	 queryStr.append(" and model1.constituency.district.districtId > 10 and model1.constituency.state.stateId=1");
	   }else if(stateId != null && stateId.longValue() == 36l){
	  	 queryStr.append(" and model1.constituency.district.districtId < 11 "); 
	   }
      queryStr.append(" group by model1.localElectionBody.localElectionBodyId ");
	   Query query = getSession().createQuery(queryStr.toString());
      return query.list();
}
	public List<Object[]> getConstituencyWiseTodayAndOverAllCounts(String type,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct C.constituencyId," +
					" C.name,model.cadre2016" +
					" from TdpCadreLocationInfo model,Constituency C" +
					" where model.locationScopeId = 4" +
					" and model.locationValue = C.constituencyId");
		if(type != null && !type.trim().isEmpty())
			sb.append(" and model.type = :type");
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and C.district.districtId between 11 and 23");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and C.district.districtId between 1 and 10");
		sb.append(" order by model.cadre2016 desc");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("type", type);
		query.setFirstResult(0);
		query.setMaxResults(20);
		return query.list();
	}
	
	public List<Object[]> getDistrictWiseTodayAndOverAllCounts(String type,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct D.districtId," +
					" D.districtName,model.cadre2016" +
					" from TdpCadreLocationInfo model,District D" +
					" where model.locationScopeId = 3" +
					" and model.locationValue = D.districtId");
		if(type != null && !type.trim().isEmpty())
			sb.append(" and model.type = :type");
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and D.districtId between 11 and 23");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and D.districtId between 1 and 10");
		sb.append(" order by model.cadre2016 desc");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("type", type);
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}
	public List<Object[]> get2014CadreDistWise(List<Long> constituencyIds,String districtName){
		
	       StringBuilder queryStr = new StringBuilder();  
	        queryStr.append("select ");
	        
	       if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
	    	   queryStr.append(" model1.district.districtId,");  
	       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
	    	   queryStr.append(" model2.districtId,");   
	       }
	        queryStr.append(" sum(model.cadre2014) from TdpCadreLocationInfo model ");
	        if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
	         queryStr.append(" ,Constituency model1 where model1.constituencyId=model.locationValue and model1.electionScope.electionScopeId=2 " +
						    "  and model1.deformDate is null ");
	        }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
	        	 queryStr.append(" ,DistrictConstituencies model2 where model2.constituencyId=model.locationValue ");
	        }
		   if(constituencyIds != null && constituencyIds.size() > 0){
	   	     queryStr.append(" and model.locationValue in(:constituencyIds) and model.type = 'Total' ");
	       }
	       queryStr.append(" and model.locationScopeId=:locationScopeId ");
	       if(districtName != null && districtName.equalsIgnoreCase("Adilabad") || districtName.equalsIgnoreCase("Visakhapatnam")){
	    	   queryStr.append(" group by model1.district.districtId ");  
	       }else if(districtName != null && districtName.equalsIgnoreCase("Mancherial") || districtName.equalsIgnoreCase("Visakhapatnam Rural")){
	    	   queryStr.append(" group by model2.districtId "); 
	       }
	       Query query = getSession().createQuery(queryStr.toString());
		   if(constituencyIds != null && constituencyIds.size() > 0){
		    query.setParameterList("constituencyIds", constituencyIds);
		   }
		   query.setParameter("locationScopeId", 4l);
	   return query.list();
}
	
	public Long getTotalCountInConstituencies(String type,List<Long> constIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.cadre2016)" +
					" from TdpCadreLocationInfo model" +
					" where model.locationScopeId = 4");
		if(type != null && !type.trim().isEmpty())
			sb.append(" and model.type = :type");
		if(constIds != null && !constIds.isEmpty())
			sb.append(" and model.locationValue in (:constIds)");
		
		Query query = getSession().createQuery(sb.toString());
		if(type != null && !type.trim().isEmpty())
			query.setParameter("type", type);
		if(constIds != null && !constIds.isEmpty())
			query.setParameterList("constIds", constIds);
		
		return (Long) query.uniqueResult();
	}
	
	public Long getOtherDistTotalCountInConstituencies(String type,List<Long> constIds,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(model.cadre2016)" +
					" from TdpCadreLocationInfo model,Constituency c" +
					" where model.locationValue = c.constituencyId and model.locationScopeId = 4");
		if(stateId != null && stateId.longValue() == 1l)
			sb.append(" and c.district.districtId = 13");
		else if(stateId != null && stateId.longValue() == 36l)
			sb.append(" and c.district.districtId = 1");
		if(type != null && !type.trim().isEmpty())
			sb.append(" and model.type = :type");
		if(constIds != null && !constIds.isEmpty())
			sb.append(" and model.locationValue not in (:constIds)");
		
		Query query = getSession().createQuery(sb.toString());
		if(type != null && !type.trim().isEmpty())
			query.setParameter("type", type);
		if(constIds != null && !constIds.isEmpty())
			query.setParameterList("constIds", constIds);
		
		return (Long) query.uniqueResult();
	}
	public List<Object[]> get2014And2016CadreCountDtls(List<Long> locValueList,Long memberLvl){
	    StringBuilder queryStr = new StringBuilder();
	    
	   queryStr.append(" select distinct ");
	   if(memberLvl.longValue() == 3L){
		   queryStr.append(" model1.district.districtId, " +//0
   		           " model1.district.districtName, ");//1
	   }else if(memberLvl.longValue() == 4L){
		   queryStr.append(" model1.constituencyId, " +//0
   		           " model1.name, ");//1
	   }
	   
	   queryStr.append(" model.cadre2014, " +//2
	   				   " model.cadre2016, " +//3
	   				   " model.newCadre, " +//4
	   				   " model.renewalCadre " +//5
	   		           " from TdpCadreLocationInfo model,Constituency model1 " +
	   		           " where ");
	   if(memberLvl.longValue() == 3L){
		   queryStr.append(" model1.district.districtId=model.locationValue and ");
	   }else if(memberLvl.longValue() == 4L){
		   queryStr.append(" model1.constituencyId=model.locationValue and ");
	   }
	   queryStr.append(" model.locationScopeId = :memberLvl and model.locationValue in (:locValueList) ");
	   queryStr.append(" and model1.deformDate is null and model1.electionScope.electionScopeId=2 and model.type='Total' " +
	   	 	           " and model.cadre2016 is not null and model.cadre2016 > 0 ");
	   
	   Query query = getSession().createQuery(queryStr.toString());
	   query.setParameter("memberLvl", memberLvl);
	   query.setParameterList("locValueList", locValueList);
	   return query.list();
	}
	
	public Long getMemberShipRegistrationsInCadreLocation(String locationtype,Long locationId,Long year,Long constituencyId,List<Long> constituencyIdsList,Long yearId)
	{
		StringBuilder str = new StringBuilder();
		if(yearId != null && yearId.longValue() == 3l){
			if(locationtype.equalsIgnoreCase("Parliament"))
				str.append(" select sum(model.cadre2014) ");
			else
				str.append(" select model.cadre2014 ");	
				
		}else if(yearId != null && yearId.longValue() == 4l){
			if(locationtype.equalsIgnoreCase("Parliament"))
				str.append(" select sum(model.cadre2016) ");
			else
				str.append(" select model.cadre2016 ");
		}
		str.append(" from TdpCadreLocationInfo model where model.type='Total' " );
		
		    if(locationtype.equalsIgnoreCase("Constituency"))
			 str.append(" and model.locationValue =:locationId  and model.locationScopeId = 4 ");
			
			else if(locationtype.equalsIgnoreCase("Mandal"))
				 str.append(" and model.locationValue =:locationId  and model.locationScopeId = 5 ");
			
			
			else if(locationtype.equalsIgnoreCase("Panchayat"))
				 str.append(" and model.locationValue =:locationId  and model.locationScopeId = 6 ");
			
			
			else if(locationtype.equalsIgnoreCase("Booth"))
				 str.append(" and model.locationValue =:locationId  and model.locationScopeId = 9 ");
		    
			else if(locationtype.equalsIgnoreCase("Muncipality"))
			 str.append(" and model.locationValue =:locationId  and model.locationScopeId = 7 ");
		    
			else if(locationtype.equalsIgnoreCase("District"))
			 str.append(" and model.locationValue =:locationId  and model.locationScopeId = 3 ");
		    
			else if(locationtype.equalsIgnoreCase("Parliament"))
			  str.append("  and model.locationValue in (:constituencyIdsList) ");
		    
		 // if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
			//str.append(" and model.locationValue =:constituencyId  and model.locationScopeId = 4 "); 
		
		Query query = getSession().createQuery(str.toString());
		
		
		if(!locationtype.equalsIgnoreCase("Parliament"))
		  query.setParameter("locationId", locationId);
		
		
	  // if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
		//query.setParameter("constituencyId", constituencyId);
	   
	   if(constituencyIdsList != null && locationtype.equalsIgnoreCase("Parliament"))
		  query.setParameterList("constituencyIdsList", constituencyIdsList);  
	   
		return (Long) query.uniqueResult();		
		
	}
	
	public Long getMemberShipRegistrationDtlsInCadreLocation(String locationtype,Long locationId,Long year,Long constituencyId,List<Long> constituencyIdsList,Long yearId)
	{
		
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct TCEY.tdpCadreId) ");
		str.append(" from TdpCadreEnrollmentYear TCEY, TdpCadre TC, UserAddress UA" +
				" where TCEY.tdpCadreId = TC.tdpCadreId and TC.userAddress.userAddressId = UA.userAddressId and TCEY.enrollmentYearId=:yearId and  " +
				" TCEY.isDeleted='N' and  TC.isDeleted ='N' and TC.enrollmentYear=2014 ");

			if(locationtype.equalsIgnoreCase("Mandal"))
				 str.append(" and UA.tehsil.tehsilId =:locationId  ");
			
			else if(locationtype.equalsIgnoreCase("Muncipality"))
			 str.append(" and UA.localElectionBody.localElectionBodyId =:locationId  ");
			
			if(constituencyId != null && constituencyId.longValue()>0L)
				 str.append(" and UA.constituency.constituencyId  = :constituencyId ");
		Query query = getSession().createQuery(str.toString()); 
		
		  query.setParameter("locationId", locationId);
		  if(constituencyId != null && constituencyId.longValue()>0L)
		  query.setParameter("constituencyId", constituencyId);  
	   	  query.setParameter("yearId", yearId);
	   	  
		return (Long) query.uniqueResult();		
		
	}
	
}
