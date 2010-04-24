package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;

public interface IInfluencingPeoplePositionDAO extends GenericDao<InfluencingPeoplePosition, Long>{

	List<InfluencingPeoplePosition> findByStaticPosition(String staticPosition);
	
	List<InfluencingPeoplePosition> getPositionNameAndId();

}
