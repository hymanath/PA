package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PsychometricTest;

public interface IPsychometricTestDAO extends GenericDao<PsychometricTest, Long>{

	public List<Object[]> getThomousUrls();
	public Integer  updateExistingRecordForPsychometricTestId(Long  psymetricTestId);
}
