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
		return null;
		
		
	}
}
