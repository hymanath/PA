package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.model.VoterTag;

public class VoterTagDAO extends GenericDaoHibernate<VoterTag, Long> implements IVoterTagDAO{
	
	public VoterTagDAO()
	{
		super(VoterTag.class);
	}
	
	public VoterTag getVoterTagByVoterIdAndUniqueCode(Long voterId,String uniqueCode)
	{
		Query query = getSession().createQuery("Select model from VoterTag model where model.voter.voterId = :voterId and model.uniqueCode = :uniqueCode");
		query.setParameter("voterId", voterId);
		query.setParameter("uniqueCode", uniqueCode);
		return (VoterTag) query.uniqueResult();
	}
	
	
	  public Long getTotalTaggedVoters()
		 {
			 Query query = getSession().createQuery("select distinct count(model.voter.voterId) from VoterTag model where model.tags is not null and model.tags !='' ");
			 return (Long) query.uniqueResult();
		 }
		 public Long getVotersByType(String type)
		 {
			StringBuilder str = new StringBuilder();
			str.append("select distinct count(model.voter.voterId) from VoterTag model");
			 if(type.equalsIgnoreCase("cadre"))
			 str.append(" where model.isCadre = 'Y' "); 
			 else if(type.equalsIgnoreCase("influencePeople"))
				 str.append(" where model.isInfluencingPeople = 'Y' ");  
			 Query query = getSession().createQuery(str.toString());
			 return (Long) query.uniqueResult();
		 }
		 
		  public Long getTotalInsertedTaggedVoters(String status)
			 {
			  Query query  = null;
			  if(status.equalsIgnoreCase("inserted"))
				query = getSession().createQuery("select distinct count(model.voter.voterId) from VoterTag model where model.isTaggedInserted = 'Y' and model.tags is not null and model.tags !='' ");
			  else
				  query = getSession().createQuery("select distinct count(model.voter.voterId) from VoterTag model where model.isTaggedInserted = 'N' and model.tags is not null and model.tags !='' ");  
				 return (Long) query.uniqueResult();
			 } 
		  public Long getInsertedVotersByType(String type,String status)
			 {
				StringBuilder str = new StringBuilder();
				str.append("select distinct count(model.voter.voterId) from VoterTag model");
				 if(type.equalsIgnoreCase("cadre"))
				 {
					if(status.equalsIgnoreCase("inserted"))
				 str.append(" where model.isCadre = 'Y' and model.isCadreInserted = 'Y' "); 
					else
						str.append(" where model.isCadre = 'Y' and model.isCadreInserted = 'N' ");	
				 }
				 else if(type.equalsIgnoreCase("influencePeople"))
				 {
					 if(status.equalsIgnoreCase("inserted"))
					 str.append(" where model.isInfluencingPeople = 'Y' and model.isInfluenceInserted = 'Y' ");
					 else
						 str.append(" where model.isInfluencingPeople = 'Y' and model.isInfluenceInserted = 'N' "); 
				 }
				 Query query = getSession().createQuery(str.toString());
				 return (Long) query.uniqueResult();
			 }
		  
		  public List<Object[]> getTotalTaggedVoterDetails(String isType,String typeOfData)
			 {
				    StringBuilder str = new StringBuilder();
					str.append("select distinct model.voter.voterId,BPV.booth.constituency.constituencyId,BPV.booth.constituency.name," +
					 		"model.voter.name,model.voter.gender,model.voter.age,model.voter.mobileNo,model.voter.voterIDCardNo from VoterTag model,BoothPublicationVoter BPV" +
					 		" where BPV.voter.voterId = model.voter.voterId");
					
					if(isType.equalsIgnoreCase("tagged") && typeOfData.equalsIgnoreCase("total"))	
						str.append(" and model.tags is not null and model.tags !='' ");
					
				   else if(isType.equalsIgnoreCase("tagged") && (typeOfData.equalsIgnoreCase("inserted") || typeOfData.equalsIgnoreCase("notInserted")))
				   {
					   if(typeOfData.equalsIgnoreCase("inserted"))
						   str.append(" and model.isTaggedInserted = 'Y' and model.tags is not null and model.tags !='' ");
					   else
						   str.append(" and model.isTaggedInserted = 'N' and model.tags is not null and model.tags !='' ");	   
				   }
					
				   else if(isType.equalsIgnoreCase("cadre") && typeOfData.equalsIgnoreCase("total"))
					   str.append(" and model.isCadre = 'Y' "); 
					
				   else if(isType.equalsIgnoreCase("cadre") && (typeOfData.equalsIgnoreCase("inserted") || typeOfData.equalsIgnoreCase("notInserted")))
				   {
					  if(typeOfData.equalsIgnoreCase("inserted"))
					   str.append(" and model.isCadre = 'Y' and model.isCadreInserted = 'Y' ");
					   else
					   str.append(" and model.isCadre = 'Y' and model.isCadreInserted = 'N' ");  
				   }
					
					else if(isType.equalsIgnoreCase("influencePeople") && typeOfData.equalsIgnoreCase("total"))
						   str.append(" and model.isInfluencingPeople = 'Y' "); 
						
					else if(isType.equalsIgnoreCase("influencePeople")&& (typeOfData.equalsIgnoreCase("inserted") || typeOfData.equalsIgnoreCase("notInserted")))
					{
						 if(typeOfData.equalsIgnoreCase("inserted"))
						   str.append(" and model.isInfluencingPeople = 'Y' and model.isInfluenceInserted = 'Y' ");
						 else
							 str.append(" and model.isInfluencingPeople = 'Y' and model.isInfluenceInserted = 'N' ");	 
					 } 
						
						Query query = getSession().createQuery(str.toString());
						return query.list();
				
			 }
		  
		  public Long getVoterTagId(Long voterId)
		  {
			Query query = getSession().createQuery("select model.voterTagId from VoterTag model where model.voter.voterId = :voterId");  
			query.setParameter("voterId", voterId);
			return (Long) query.uniqueResult();
		  }
		  
		  
}
