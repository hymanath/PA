package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ApplicationStatus;

public interface IApplicationStatusDAO extends GenericDao<ApplicationStatus, Long>{

	public List<Object[]> getAllApplicationStatusList();
}
