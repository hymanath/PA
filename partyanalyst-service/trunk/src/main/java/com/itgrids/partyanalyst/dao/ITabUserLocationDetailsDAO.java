package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserLocationDetails;

public interface ITabUserLocationDetailsDAO extends GenericDao<TabUserLocationDetails, Long>{

	public List<Object[]> getLattitudeLangitudeOfTabUser(Long locationId,Date startDate,Date endDate,String type);
}
