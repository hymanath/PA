package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserPartyRelationDAO;
import com.itgrids.partyanalyst.model.UserPartyRelation;

public class UserPartyRelationDAO extends GenericDaoHibernate< UserPartyRelation, Long> implements IUserPartyRelationDAO{
	public UserPartyRelationDAO() {
		super(UserPartyRelation.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getPartiesByUser(Long userId)
	{
		return getHibernateTemplate().find("select model.party.partyId,model.party.longName,model.party.shortName from UserPartyRelation model where model.user.userId = ? order by model.party.longName",userId);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserPartyRelationDetails(Long userId)
	{
		return getHibernateTemplate().find("select model.userPartyRelationId,model.party.partyId," +
				" model.party.longName from UserPartyRelation model where model.user.userId=?",userId);
	}
	
	public void deleteUserPartyRelation(Long userPartyRelationId)
	{
		StringBuilder query = new StringBuilder();
		query.append("delete from UserPartyRelation model where model.userPartyRelationId=:userPartyRelationId");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("userPartyRelationId", userPartyRelationId);
		queryObject.executeUpdate();
	}
	public Long checkPartyForUser(Long userId,Long partyId)
	{
		StringBuilder query = new StringBuilder();
		query.append("select count(userPartyRelationId) from UserPartyRelation model where model.user.userId =:userId and model.party.partyId =:partyId");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong("userId", userId);
		queryObject.setLong("partyId", partyId);
		return (Long)queryObject.uniqueResult();
		
	}
	

}
