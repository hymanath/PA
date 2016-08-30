package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VolunteersCadreDetails;

public interface IVolunteersCadreDetailsDAO extends GenericDao<VolunteersCadreDetails, Long>{
	public Long getVolunteerCountDetails(Long tdpCadreId);
	public List<Object[]> getVolunteerDetailsInfo(Long tdpCadreId);

}
