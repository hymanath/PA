package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CustomVoter;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Voter;

public class CustomVoterDAO extends GenericDaoHibernate<CustomVoter,Long> implements ICustomVoterDAO {

	public CustomVoterDAO()
	{
		super(CustomVoter.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterGroupNamesByVoterIdsList(List<Long> voterIdsList)
	{
		Query query = getSession().createQuery(" select model.voter.voterId, model.customVoterGroup.name from CustomVoter model " +
				" where model.voter.voterId in (:voterIdsList)");
		query.setParameterList("voterIdsList", voterIdsList);
		return query.list();
	}
	
	public void removeCustomVoterDetails(Long customerVoterId)
	{
		Query query = getSession().createQuery("delete from CustomVoter model where model.customVoterId = :customerVoterId");
		
		query.setParameter("customerVoterId", customerVoterId);
		query.executeUpdate();
		
	}
	
	public List<CustomVoter> getCustomVoterByVoterIdAndUserId(Long voterId , Long userId)
	{
		Query query = getSession().createQuery("select model from CustomVoter model where model.voter.voterId = ?" +
				" and model.customVoterGroup.user.userId = ?");
		
		query.setParameter(0, voterId);
		query.setParameter(1, userId);
		
		return query.list();
		
	}
	
	public List<Long> getCustomGroupIdByVoterIdAndUserId(Long voterId , Long userId)
	{
		Query query = getSession().createQuery("select model.customVoterGroup.customVoterGroupId from " +
				"CustomVoter model where customVoterGroup.user.userId = :userId and model.voter.voterId = :voterId");
		
		query.setParameter("userId", userId);
		query.setParameter("voterId", voterId);
		
		return query.list();
		
	}
	
	public List<Long> getCustomVoterIdByVoterIdAndUserId(Long voterId , Long userId)
	{
		Query query = getSession().createQuery("select model.customVoterId from " +
				"CustomVoter model where customVoterGroup.user.userId = :userId and model.voter.voterId = :voterId");
		
		query.setParameter("userId", userId);
		query.setParameter("voterId", voterId);
		
		return query.list();
		
	}
	
	public List<Object[]> getAllVotersGroups(List<Long> voterIds , Long userId)
	{
		Query query = getSession().createQuery("select model.voter.voterId , model.customVoterGroup.customVoterGroupId from " +
				" CustomVoter model where model.customVoterGroup.user.userId = :userId and model.voter.voterId in(:voterIds)");
		
		query.setParameter("userId", userId);
		query.setParameterList("voterIds", voterIds);
		
		return query.list();
		
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersInfoBycustomVoterGroupId(Long customVoterGroupId,Long userId)
	{
		Query query = getSession().createQuery("select model.voter.voterId from CustomVoter model where model.customVoterGroup.customVoterGroupId = :customVoterGroupId and " +
				" model.customVoterGroup.user.userId = :userId");
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteWiseCustomVotersCount(Long customVoterGroupId, Long userId)
	{
		Query query = getSession().createQuery(" select count (distinct CV.voter.voterId),CV.voter.gender,UVD.casteState.caste.casteName,UVD.casteState.casteCategoryGroup.casteCategory.categoryName,UVD.casteState.casteStateId,UVD.casteState.caste.casteId from CustomVoter CV, UserVoterDetails UVD where " +
				" UVD.voter.voterId = CV.voter.voterId and CV.customVoterGroup.customVoterGroupId =:customVoterGroupId and UVD.user.userId =:userId group by UVD.casteState.caste.casteId,CV.voter.gender order by UVD.casteState.caste.casteName ");
		
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Long> getCountBycustomvoterGroupId(Long customVoterGroupId,Long userId,Long publicationDateId)
	{
		Query query = getSession().createQuery("select  CV.voter.voterId from BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and BPV.voter.voterId = CV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();
	}
	
	
	
	/** this method is used get voter data By CustomVoterGroupId **/
	@SuppressWarnings("unchecked")
	public List<Object[]> getVotersInfoBycustomVoterGroupId(Long customVoterGroupId,Long userId,Integer startIndex,
			Integer maxRecords, String order, String columnName,Long publicationDateId)
	{
		Query query = null;
	
		if(columnName.equalsIgnoreCase("initial"))
		{
	/*	query = getSession().createQuery("select model.voter.voterId, model.voter.name,model.voter.gender,model.voter.age,model.voter.voterIDCardNo,model.voter.houseNo,model.voter.relativeName from CustomVoter model where model.customVoterGroup.customVoterGroupId = :customVoterGroupId and " +
							" model.customVoterGroup.user.userId = :userId order by model.voter.name ");	*/
			
			query = getSession().createQuery("select CV.voter.voterId, CV.voter.name,CV.voter.gender,CV.voter.age,CV.voter.voterIDCardNo,CV.voter.houseNo,CV.voter.relativeName,BPV.serialNo,BPV.booth.partNo,CV.voter.mobileNo from BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and BPV.voter.voterId = CV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by cast(BPV.booth.partNo , int),BPV.serialNo,BPV.voter.houseNo") ;
		}
		else if(columnName.equalsIgnoreCase("serialNo"))
		{

			query = getSession().createQuery("select CV.voter.voterId, CV.voter.name,CV.voter.gender,CV.voter.age,CV.voter.voterIDCardNo,CV.voter.houseNo,CV.voter.relativeName,BPV.serialNo,BPV.booth.partNo,CV.voter.mobileNo from BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and BPV.voter.voterId = CV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by BPV.serialNo  "+order) ; 
		 
		}
		else if(columnName.equalsIgnoreCase("partNo"))
		{

			query = getSession().createQuery("select CV.voter.voterId, CV.voter.name,CV.voter.gender,CV.voter.age,CV.voter.voterIDCardNo,CV.voter.houseNo,CV.voter.relativeName,BPV.serialNo,BPV.booth.partNo,CV.voter.mobileNo from BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and BPV.voter.voterId = CV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by cast(BPV.booth.partNo , int) "+order) ; 
		 
		}
		else
		{
	/*query = getSession().createQuery("select model.voter.voterId, model.voter.name,model.voter.gender,model.voter.age,model.voter.voterIDCardNo,model.voter.houseNo,model.voter.relativeName from CustomVoter model where model.customVoterGroup.customVoterGroupId = :customVoterGroupId and " +
				" model.customVoterGroup.user.userId = :userId order by model.voter."+columnName+" "+order);*/
			
			query = getSession().createQuery("select CV.voter.voterId, CV.voter.name,CV.voter.gender,CV.voter.age,CV.voter.voterIDCardNo,CV.voter.houseNo,CV.voter.relativeName,BPV.serialNo,BPV.booth.partNo,CV.voter.mobileNo from BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and BPV.voter.voterId = CV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationDateId order by CV.voter."+columnName+" "+order);
		
		}
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomVotersCount(Long customVoterGroupId, Long userId,Long publicationId)
	{
		Query query = getSession().createQuery(" select count (distinct CV.voter.voterId),CV.voter.gender from BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and BPV.voter.voterId = CV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId group by CV.voter.gender");
		
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		query.setParameter("publicationId", publicationId);
		
		return query.list();
	}
	
 public List<Long> getInfluencingPeopleCountByCustomVoter(Long userId,Long publicationId,Long customVoterGroupId)
	{
	Query query = getSession().createQuery("select count(model.influencingPeopleId) from InfluencingPeople model ,BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and model.voter.voterId = CV.voter.voterId and CV.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId");
	query.setParameter("customVoterGroupId", customVoterGroupId);
	query.setParameter("userId", userId);
	query.setParameter("publicationId", publicationId);
	
	return query.list();
	}
 
 public List<Long> getPoliticianCountByCustomVoter(Long userId,Long publicationId,Long customVoterGroupId)
	{
	Query query = getSession().createQuery("select count(model.candidateId) from Candidate model,BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and model.voter.voterId = CV.voter.voterId and CV.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId");
	query.setParameter("customVoterGroupId", customVoterGroupId);
	query.setParameter("userId", userId);
	query.setParameter("publicationId", publicationId);
	
	return query.list();
	}
 
 public List<Long> getCadrePeopleCountByCustomVoter(Long userId,Long publicationId,Long customVoterGroupId)
	{
	Query query = getSession().createQuery("select count(model.cadreId) from Cadre model,BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and model.voter.voterId = CV.voter.voterId and CV.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId");
	query.setParameter("customVoterGroupId", customVoterGroupId);
	query.setParameter("userId", userId);
	query.setParameter("publicationId", publicationId);
	
	return query.list();
	}
 
 
 public List<Object[]> getCadreDetails(Long userId,Long publicationId,Long customVoterGroupId,Integer startIndex,Integer maxIndex,String order,String columnName)
 {
	  StringBuffer queryString = new StringBuffer();
	 
	  queryString.append("select model.voter.voterId,model.voter.name,model.voter.gender,model.voter.age,model.voter.houseNo,model.voter.relativeName,model.voter.mobileNo,model.voter.voterIDCardNo from Cadre model,BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and model.voter.voterId = CV.voter.voterId and CV.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId");
	  if(columnName.equalsIgnoreCase("voterId"))
	  {
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("gender"))
	  {
		  columnName = "gender";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("houseNo"))
	  {
		  columnName = "houseNo";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
	  {
		  columnName = "relativeName";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else
	  {
		  columnName = "name";
		  
	  }
	Query query = getSession().createQuery(queryString.toString());
	query.setParameter("customVoterGroupId", customVoterGroupId);
	query.setFirstResult(startIndex);
	query.setMaxResults(maxIndex);
	query.setParameter("userId", userId);
	query.setParameter("publicationId", publicationId);
	return query.list(); 
 }
 /**
  * This DAO is Used For getting The Influencing People Details For  Selected Level.
 
  * @param Long userId
  * @param Integer startIndex
  * @param Integer maxIndex
  * @param String order
  * @param String columnName
  * @return List<Long>
  */
 public List<Object[]> getInfluencingPeopleDetails(Long userId,Long publicationId,Long customVoterGroupId,Integer startIndex,Integer maxIndex,String order,String columnName)
 {
	  StringBuffer queryString = new StringBuffer();
	  queryString.append("select model.voter.voterId,model.voter.name,model.voter.gender,model.voter.age,model.voter.houseNo,model.voter.relativeName,model.voter.mobileNo,model.voter.voterIDCardNo,model.influencingScope,model.influencingScopeValue from InfluencingPeople model ,BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and model.voter.voterId = CV.voter.voterId and CV.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId");
	  if(columnName.equalsIgnoreCase("voterId"))
	  {
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("gender"))
	  {
		  columnName = "gender";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("houseNo"))
	  {
		  columnName = "houseNo";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
	  {
		  columnName = "relativeName";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else
	  {
		  columnName = "name";
		  
	  }
	Query query = getSession().createQuery(queryString.toString());
	query.setParameter("customVoterGroupId", customVoterGroupId);
	query.setFirstResult(startIndex);
	query.setMaxResults(maxIndex);
	query.setParameter("userId", userId);
	query.setParameter("publicationId", publicationId);
	return query.list(); 
 }
 /**
  * This DAO is Used For getting The Politicans Details For  Selected Level.
  
  * @param Integer startIndex
  * @param Integer maxIndex
  * @param String order
  * @param String columnName
  * @return List<Long>
  */
 public List<Object[]> getPoliticanDetails(Long userId,Long publicationId,Long customVoterGroupId,Integer startIndex,Integer maxIndex,String order,String columnName)
 {
	  StringBuffer queryString = new StringBuffer();
	  queryString.append("select model.voter.voterId,model.voter.name,model.voter.gender,model.voter.age,model.voter.houseNo,model.voter.relativeName,model.voter.mobileNo,model.voter.voterIDCardNo from Candidate model,BoothPublicationVoter BPV,CustomVoter CV where CV.customVoterGroup.customVoterGroupId = :customVoterGroupId and" +
					" CV.customVoterGroup.user.userId = :userId and model.voter.voterId = CV.voter.voterId and CV.voter.voterId = BPV.voter.voterId and BPV.booth.publicationDate.publicationDateId = :publicationId");
	  if(columnName.equalsIgnoreCase("voterId"))
	  {
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("gender"))
	  {
		  columnName = "gender";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("houseNo"))
	  {
		  columnName = "houseNo";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else if(columnName.equalsIgnoreCase("relativeFirstName"))
	  {
		  columnName = "relativeName";
		  queryString.append(" order by model.voter."+columnName+" "+order);
	  }
	  else
	  {
		  columnName = "name";
		  
	  }
	Query query = getSession().createQuery(queryString.toString());
	query.setParameter("customVoterGroupId", customVoterGroupId);
	query.setFirstResult(startIndex);
	query.setMaxResults(maxIndex);
	query.setParameter("userId", userId);
	query.setParameter("publicationId", publicationId);
	return query.list(); 
 }
 
	@SuppressWarnings("unchecked")
	public List<Object[]> getCustomGroupWiseVotersDetailsForCaste(Long userId, String areaType, Long locationValue)
	{
		Query query = getSession().createQuery(" select count (distinct CV.voter.voterId),CV.customVoterGroup.customVoterGroupId,CV.customVoterGroup.name,UVD.casteState.casteStateId,UVD.casteState.caste.casteName,CV.voter.gender,UVD.casteState.casteCategoryGroup.casteCategory.categoryName,UVD.casteState.caste.casteId " +
				" from CustomVoter CV,UserVoterDetails UVD where UVD.voter.voterId = CV.voter.voterId and UVD.user.userId =:userId and CV.customVoterGroup.areaType.type =:type and CV.customVoterGroup.locationValue =:locationValue group by CV.customVoterGroup.customVoterGroupId,UVD.casteState.caste.casteId," +
				" CV.voter.gender order by CV.customVoterGroup.name,UVD.casteState.caste.casteName ");
		
		query.setParameter("userId", userId);
		query.setParameter("type", areaType);
		query.setParameter("locationValue", locationValue);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Voter> getCasteWiseCustomVoterDetails(Long casteStateId,Long casteId,Long customVoterGroupId,Long userId)
	{
		Query query = getSession().createQuery("select CV.voter from CustomVoter CV,UserVoterDetails UVD where UVD.voter.voterId = CV.voter.voterId and UVD.user.userId =:userId and " +
				" CV.customVoterGroup.customVoterGroupId =:customVoterGroupId and UVD.casteState.caste.casteId =:casteId and UVD.casteState.casteStateId =:casteStateId order by CV.voter.name ");
		
		query.setParameter("casteStateId", casteStateId);
		query.setParameter("casteId", casteId);
		query.setParameter("customVoterGroupId", customVoterGroupId);
		query.setParameter("userId", userId);
		return query.list();
	}

}
