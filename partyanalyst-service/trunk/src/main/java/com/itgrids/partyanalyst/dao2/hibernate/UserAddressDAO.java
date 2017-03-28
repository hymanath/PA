package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserAddressDAO extends GenericDaoHibernate<UserAddress, Long> implements IUserAddressDAO {

	public UserAddressDAO() {
		super(UserAddress.class);		
	}
	public Integer deleteInfluencingPeopleById(Long userAddressId){
		Query queryObject = getSession().createQuery("delete from UserAddress model where model.userAddressId = ?");
		queryObject.setParameter(0, userAddressId);
		return queryObject.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAddress> getUserAddressList()
	{
		return getHibernateTemplate().find(" from UserAddress model ");
	}
	
	public List<UserAddress> getUserAddressByUserAddressId(Long userAddressId){
		Query query = getSession().createQuery(" select model from UserAddress model where model.userAddressId =:userAddressId ");
		query.setParameter("userAddressId", userAddressId);
		
		return query.list();
	}
	
	public List<Object[]> getUserAddressDetails(List<Long> candidateIdsList){
		Query query = getSession().createQuery(" select model.district.districtId,model.constituency.constituencyId,model.tehsil.tehsilId,model.hamlet.hamletId,model.parliamentConstituency.constituencyId" +
				" from UserAddress model where model.userAddressId in(:candidateIdsList) ");
		query.setParameterList("candidateIdsList", candidateIdsList);
		return query.list();
	}
	  public List<Object[]> getGrievanceStatusCountsByTypeOfIssue(Long id,String searchType){
		   StringBuilder str = new StringBuilder();
		   
		   str.append(" select model.type_of_issue," +
		   		"count(Complaint_id) " +
		   		"from complaint_master model " +
		   		"where");
		   
		   if(searchType.equalsIgnoreCase("district"))
			   str.append(" model.district_id =:id and  ");
		   
		   else if(searchType.equalsIgnoreCase("assembly"))
			   str.append(" model.assembly_id = :id  and ");
		   
		   else if(searchType.equalsIgnoreCase("parliament"))
			   str.append(" model.parliament_id= :id and ");
		   
		   str.append(" type_of_issue != 'Insurance' group by model.type_of_issue ");
		   
		   Query query = getSession().createSQLQuery(str.toString());
		   query.setParameter("id", id);
		   return query.list();
	   }
	  /*public List<Object[]> getGrievanceStatusWiseCountsByTypeOfIssueAndStatus(Long id,String searchType){
		  
		  StringBuilder str = new StringBuilder();
		  Query query = getSession().createSQLQuery(str.toString());
		  
		  str.append(" select model.type_of_issue," +
		  			"model.Completed_Status," +
			   		"count(Complaint_id) " +
			   		"from complaint_master model " +
			   		"where");
			   
			   if(searchType.equalsIgnoreCase("district"))
				   str.append(" model.district_id =:id and  ");
			   
			   else if(searchType.equalsIgnoreCase("assembly"))
				   str.append(" model.assembly_id = :id  and ");
			   
			   else if(searchType.equalsIgnoreCase("parliament"))
				   str.append(" model.parliament_id= :id and ");
			   
			   str.append(" type_of_issue != 'Insurance' group by model.type_of_issue,model.Completed_Status ");
			   query.setParameter("id", id);
		return query.list();
		  
	  }*/
public List<Object[]> getGrievanceStatusWiseCountsByTypeOfIssueAndStatus(Long id,String searchType){
		  
		  StringBuilder str = new StringBuilder();
		  str.append(" select model.type_of_issue," +
		  			"model.Completed_Status," +
			   		"count(Complaint_id) " +
			   		"from complaint_master model " +
			   		"where");
			   
			   if(searchType.equalsIgnoreCase("district"))
				   str.append(" model.district_id =:id and  ");
			   
			   else if(searchType.equalsIgnoreCase("assembly"))
				   str.append(" model.assembly_id = :id  and ");
			   
			   else if(searchType.equalsIgnoreCase("parliament"))
				   str.append(" model.parliament_id= :id and ");
			   
			   str.append(" type_of_issue != 'Insurance' and  model.subject !='' and " +
				" (model.delete_status !='0' or model.delete_status is null) group by model.type_of_issue,model.Completed_Status ");
			   Query query = getSession().createSQLQuery(str.toString());
			   query.setParameter("id", id);
		  
		return query.list();
		  
	  }
	  public List<String> getCompletedStatus(){
		  
		 /* Query query = getSession().createSQLQuery(" select distinct Completed_Status from complaint_master where Completed_Status is not  null and " +
		  		" type_of_issue in ('Party','Govt','Welfare') ");*/
		  Query query = getSession().createSQLQuery("select distinct Completed_Status from complaint_master where type_of_issue in ('Party','Govt','Welfare')");
		  return query.list();
	  }
public List<Object[]> getGrievanceRequestCountsByTypeOfIssue(Long id,String searchType){
		  
		  StringBuilder str = new StringBuilder();
		  
		  str.append(" select model.Completed_Status," +
		  		"count(distinct model.Complaint_id)" +
		  		"  from complaint_master model" +
		  		" where ");
		  if(searchType.equalsIgnoreCase("district"))
			   str.append(" model.district_id =:id and  ");
		   
		   else if(searchType.equalsIgnoreCase("assembly"))
			   str.append(" model.assembly_id = :id  and ");
		   
		   else if(searchType.equalsIgnoreCase("parliament"))
			   str.append(" model.parliament_id= :id and ");
		  
		  str.append(" type_of_issue != 'Insurance'");
		  str.append(" and (delete_status !='0' or delete_status is null )" +
		  		" and model.state_id_cmp in(1,2) and ");
		  str.append(" model.Subject != '' ");
		  str.append(" and ((model.issue_type ='Personal' and  model.expected_amount is not null and model.expected_amount !='' )" +
		  		" or (model.issue_type ='CM Relief' and model.expected_amount is not null and model.expected_amount != '') or " +
		  		" (model.issue_type ='Health' and model.health_amount is not null and model.health_amount != '') or " +
		  		" (model.issue_type ='Financial Support' and model.expected_amount is not null and model.expected_amount !='')) ");
		  str.append(" group by model.Completed_Status ");
			   Query query = getSession().createSQLQuery(str.toString());
			   query.setParameter("id", id);
		  
		return query.list();
		  
	  }
	  
   public Long getLocalElectionBodyByUserAddress(Long userAddressId){
	   
	   Query query = getSession().createQuery(" select model.localElectionBody.localElectionBodyId from UserAddress model where model.userAddressId = :userAddressId ");
	   query.setParameter("userAddressId",userAddressId);
	   return (Long) query.uniqueResult();
	   
   }
@SuppressWarnings("unchecked")
public List<Object[]> getUserAddressDetailsByMinuteId(Long userAddressId){
	   
	   Query query = getSession().createSQLQuery(" select model.state_id as stateId,model.district_id as districtId,model.constituency_id as constituencyId" +
	   		",model.tehsil_id as tehsilId," +
	   		" model.local_election_body as localElectionBodyId,model.ward as ward,model.panchayat_id as panchayatId from user_address model " +
	   		" where model.user_address_id = :userAddressId ")
	   		.addScalar("stateId",Hibernate.LONG)
	   		.addScalar("districtId",Hibernate.LONG)
	   		.addScalar("constituencyId",Hibernate.LONG)
	   		.addScalar("tehsilId",Hibernate.LONG)
	   		.addScalar("localElectionBodyId",Hibernate.LONG)
	   		.addScalar("ward",Hibernate.LONG)
	   		.addScalar("panchayatId",Hibernate.LONG);
	   
	   query.setParameter("userAddressId",userAddressId);
	   return query.list();
	   
   }

public List<Object[]> getUserTypeWiseLocationName(Long stateId,Long userType){
	
	StringBuilder queryStr = new StringBuilder();
	   
	   queryStr.append(" select distinct ");
	  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
   	      queryStr.append(" model.district.districtId,");
   	      queryStr.append(" model.district.districtName "); 
       }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
     	  || userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID 
     	  || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID || userType.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID){
   	 	  queryStr.append(" model.constituencyId,");
	       queryStr.append(" model.name "); 
	   }
		queryStr.append(" from Constituency model where model.electionScope.electionScopeId=2 and model.deformDate is null ");
		
		
	   if(stateId != null && stateId.longValue() > 0){
			 if(stateId != null && stateId.longValue() > 0){
				   if(stateId.longValue()==1l){
						queryStr.append(" and model.district.districtId > 10 and  model.state.stateId = 1 ");
					}else if(stateId.longValue()==36l){
						queryStr.append(" and  model.district.districtId < 11 ");
					}
			 } 
	   }
	   Query query = getSession().createQuery(queryStr.toString());
	   
	   return query.list();
}
public List<Object[]> getLocationTypeWiseLocationName(Long stateId,String LocationType,Long accessLevelId,List<Long> accessLevelValue,String isKuppamExcluded){
	
	StringBuilder queryStr = new StringBuilder();
	   
	   queryStr.append(" select distinct ");
	  if(LocationType != null && LocationType.equalsIgnoreCase("District")){
   	      queryStr.append(" model.district.districtId,");
   	      queryStr.append(" model.district.districtName "); 
       }else if(LocationType != null && LocationType.equalsIgnoreCase("Constituency")){
   	 	  queryStr.append(" model.constituencyId,");
	      queryStr.append(" model.name"); 
	   }
		queryStr.append(" from Constituency model where model.electionScope.electionScopeId=2 and model.deformDate is null ");
	   if(stateId != null && stateId.longValue() > 0){
			 if(stateId != null && stateId.longValue() > 0){
				   if(stateId.longValue()==1l){
						queryStr.append(" and model.district.districtId > 10 and  model.state.stateId = 1 ");
					}else if(stateId.longValue()==36l){
						queryStr.append(" and  model.district.districtId < 11 ");
					}
			 } 
	   }
	   if(stateId != null && stateId.longValue()==1l && isKuppamExcluded != null && isKuppamExcluded.equalsIgnoreCase("True")){
		  queryStr.append(" and model.constituencyId not in(282) ");  
	   }
	   if(accessLevelId != null && accessLevelId==IConstants.DISTRICT_LEVEl_ACCESS_ID && accessLevelValue != null && accessLevelValue.size() > 0){
		    queryStr.append(" and  model.district.districtId in (:accessLevelValue) ");
	   }
	    Query query = getSession().createQuery(queryStr.toString());
	    if(accessLevelId != null && accessLevelId==IConstants.DISTRICT_LEVEl_ACCESS_ID && accessLevelValue != null && accessLevelValue.size() > 0 ){
	      query.setParameterList("accessLevelValue", accessLevelValue);
	    }
	    return query.list();
}
public List<Object[]> getConstituencyIdAndName(Long stateId,String LocationType){
	
	StringBuilder queryStr = new StringBuilder();
	   
	   if(LocationType != null && LocationType.equalsIgnoreCase("Constituency")){
   	 	  queryStr.append(" select distinct model.constituencyId,");
	      queryStr.append(" model.name"); 
	   }
		queryStr.append(" from Constituency model where model.electionScope.electionScopeId=2 and model.deformDate is null ");
	   if(stateId != null && stateId.longValue() > 0){
			 if(stateId != null && stateId.longValue() > 0){
				   if(stateId.longValue()==1l){
						queryStr.append(" and model.district.districtId > 10 and  model.state.stateId = 1 ");
					}else if(stateId.longValue()==36l){
						queryStr.append(" and  model.district.districtId < 11 ");
					}
			 } 
	   }
	
	    Query query = getSession().createQuery(queryStr.toString());
	  
	    return query.list();
}
}
