package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VerifierBoothPercentage;

public interface IVerifierBoothPercentageDAO extends GenericDao<VerifierBoothPercentage,Long>{

	public List<String> getBoothWisePercentage(Long boothId);
	public Long checkForBoothPercentages(Long boothId);
}
