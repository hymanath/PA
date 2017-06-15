package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertMeekosamPetitioner;

public interface IAlertMeekosamPetitionerDAO extends
		GenericDao<AlertMeekosamPetitioner, Long> {
	public List<Object[]> getMeekosamPetitionerDetails(Long alertId);

}
