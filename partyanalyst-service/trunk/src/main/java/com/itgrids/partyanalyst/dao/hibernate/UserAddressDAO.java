package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.model.UserAddress;

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
	  
}
