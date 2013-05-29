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
}
