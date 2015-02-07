package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RegistrationStatus;

public interface IRegistrationStatusDAO  extends GenericDao<RegistrationStatus,Long>{
	public List<Object[]> getAllBoothsInfo();
	public List<Object[]> getAllKnownBoothsInfo();
	public List<Object[]> getAllUnKnownBoothsInfo();
}
