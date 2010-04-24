package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;

public class InfluencingPeoplePositionDAO extends GenericDaoHibernate<InfluencingPeoplePosition, Long> implements IInfluencingPeoplePositionDAO{
	
	public  InfluencingPeoplePositionDAO(){
		super(InfluencingPeoplePosition.class);
	}

	@SuppressWarnings("unchecked")
	public List<InfluencingPeoplePosition> findByStaticPosition(
			String staticPosition) {
		return getHibernateTemplate().find("from InfluencingPeoplePosition model where model.position = ?", staticPosition);
	}
	
	@SuppressWarnings("unchecked")
	public List<InfluencingPeoplePosition> getPositionNameAndId(){
		return getHibernateTemplate().find("select distinct model.influencingPeoplePositionId,model.position from InfluencingPeoplePosition model");
	}

}
