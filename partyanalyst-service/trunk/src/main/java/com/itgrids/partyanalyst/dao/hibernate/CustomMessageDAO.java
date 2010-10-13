package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomMessageDAO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;

public class CustomMessageDAO extends GenericDaoHibernate<CustomMessage, Long> implements
		ICustomMessageDAO {

	public CustomMessageDAO() {
		super(CustomMessage.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getRelationShipBetweenTheUsers(List<Long> userIds,Long logedUserId){
		StringBuilder query = new StringBuilder();		
		query.append(" select model.recepientId.userId,model.messageType.messageType ");
		query.append(" from CustomMessage model where ");
		query.append(" model.senderId.userId = ? and ");
		query.append(" model.recepientId.userId in (:userIds)");	
				
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0,logedUserId);
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
	}
}
