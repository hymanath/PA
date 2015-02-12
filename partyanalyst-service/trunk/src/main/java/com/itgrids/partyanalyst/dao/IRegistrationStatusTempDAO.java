package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RegistrationStatusTemp;


public interface IRegistrationStatusTempDAO extends GenericDao<RegistrationStatusTemp,Long>{
	public List<Object[]> getLatestMessages(Integer startIndex,Integer maxIndex);
}
