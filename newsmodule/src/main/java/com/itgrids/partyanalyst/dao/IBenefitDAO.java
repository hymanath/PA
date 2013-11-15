package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Benefit;

public interface IBenefitDAO extends GenericDao<Benefit, Long>{
	
	public List<Benefit> getBenifitsList();

}
