package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;
import com.itgrids.partyanalyst.model.CustomVoterGroup;

public class CustomVoterGroupDAO extends GenericDaoHibernate<CustomVoterGroup,Long> implements ICustomVoterGroupDAO {

	
	public CustomVoterGroupDAO() {
		super(CustomVoterGroup.class);
	}
	
	public List<Object[]> getCustomVoterGroupsByLocationTypeAndLocationValue(Long userId ,Long locationTypeId ,Long locationValue)
	{
		
		Query query  = getSession().createQuery("select model.customVoterGroupId,model.name from CustomVoterGroup model " +
				"where model.user.userId = ? and model.locationLevelId = ? and model.locationValue = ? ");
		
		query.setParameter(0, userId);
		query.setParameter(1, locationTypeId);
		query.setParameter(2, locationValue);
		return query.list();
		
		
	}
	
	public List<Object[]> checkDuplicateGroupName(Long userId,Long locationValue,String name)
	{
		Query query = getSession().createQuery("select model.customVoterGroupId,model.name from CustomVoterGroup model where model.user.userId =:userId and model.locationValue =:locationValue and model.name =:name ");
		query.setParameter("userId", userId);
		query.setParameter("locationValue", locationValue);
		query.setParameter("name", name);
		return query.list();
		
	}
	public List<Object[]> getCustomVoterGroupsByLocationValue(Long userId ,List<Long> locationValues)
	{
		
		Query query  = getSession().createQuery("select model.customVoterGroupId,model.name from CustomVoterGroup model " +
				"where model.user.userId = :userId  and model.locationValue in(:locationValues) ");
		
		query.setParameter("userId", userId);
		query.setParameterList("locationValues", locationValues);
		
		return query.list();
		
	}
	
	public List<Object[]> getCustomVoterGroupsByLocationValueAndAreaTypeAndConstituencyId(Long userId ,List<Long> locationValues, String areaType,Long constituencyId)
	{
		
		Query query  = getSession().createQuery("select model.customVoterGroupId,model.name from CustomVoterGroup model " +
				"where model.user.userId = :userId  and model.locationValue in(:locationValues) and " +
				"model.constituency.constituencyId = :constituencyId");
		
		query.setParameter("userId", userId);
		query.setParameterList("locationValues", locationValues);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
		
	}
	
	
	public List<Object[]> getCustomVoterGroupsByLocationValueAndAreaType(Long userId ,List<Long> locationValues , String areaType)
	{
		
		Query query  = getSession().createQuery("select model.customVoterGroupId,model.name from CustomVoterGroup model " +
				"where model.user.userId = :userId  and model.locationValue in(:locationValues) and model.areaType.type = :areaType");
		
		query.setParameter("userId", userId);
		query.setParameterList("locationValues", locationValues);
		query.setParameter("areaType", areaType);
		
		return query.list();
		
	}
	
	public List<Object[]> getVotersCountBasedOnAgeInGroup(Long userId,Long customGroupId, String age,Long publicationDate){
		
		StringBuilder query=new StringBuilder();
		query.append("select count(*),model.voter.gender from CustomVoter model,BoothPublicationVoter model1 where " +
				" model.customVoterGroup.customVoterGroupId = :customGroupId and model.voter.voterId=model1.voter.voterId and " +
				" model1.booth.publicationDate.publicationDateId =:publicationDate ");
		
		if(age.equalsIgnoreCase("18to25")){
			query.append(" and model.voter.age>=18 and model.voter.age<=25 ");
		}
		else if(age.equalsIgnoreCase("26to35")){
			query.append("and model.voter.age>=26 and model.voter.age<=35 ");
		}
		else if(age.equalsIgnoreCase("36to45")){
			query.append("and model.voter.age>=36 and model.voter.age<=45 ");
		}
		else if(age.equalsIgnoreCase("46to60")){
			query.append("and model.voter.age>=46 and model.voter.age<=60 ");
		}
		else if(age.equalsIgnoreCase("60Above")){
			query.append("and model.voter.age>=61 ");
		}
		query.append(" group by model.voter.gender");
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setParameter("customGroupId", customGroupId);
		queryObject.setParameter("publicationDate", publicationDate);
		
		return queryObject.list();
	}
	
	public String getCustomVoterGroupNameByGroupId(Long customVoterGroupId)
	{
		Query query = getSession().createQuery("select model.name from CustomVoterGroup model where model.customVoterGroupId =:customVoterGroupId ");
		query.setParameter("customVoterGroupId", customVoterGroupId);
		return (String) query.uniqueResult();
	}
}
