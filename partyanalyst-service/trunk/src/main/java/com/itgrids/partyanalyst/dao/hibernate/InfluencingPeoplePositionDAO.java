package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.utils.IConstants;

public class InfluencingPeoplePositionDAO extends GenericDaoHibernate<InfluencingPeoplePosition, Long> implements IInfluencingPeoplePositionDAO{
	
	public  InfluencingPeoplePositionDAO(){
		super(InfluencingPeoplePosition.class);
	}

	@SuppressWarnings("unchecked")
	public List<InfluencingPeoplePosition> findByStaticPosition(
			String staticPosition,Long userId) {
		Query query = getSession().createQuery("from InfluencingPeoplePosition model where model.position =:position and (model.user.userId =:userId " +
				" or model.isGlobal =:isGlobal) ");
		query.setParameter("position", staticPosition);
		query.setParameter("userId", userId);
		query.setParameter("isGlobal", IConstants.TRUE);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeoplePosition> getPositionNameAndId(){
		return getHibernateTemplate().find("select distinct model.influencingPeoplePositionId,model.position from InfluencingPeoplePosition model");
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeoplePosition> getPositionNameByUserId(Long userId)
	{
		Query query = getSession().createQuery(" from InfluencingPeoplePosition model where model.userId =:userId or model.isGlobal =:isGlobal ");
		query.setParameter("userId", userId);
		query.setParameter("isGlobal", IConstants.TRUE);
		return query.list();
	}

}
