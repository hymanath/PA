package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PRPWeighteges;

public interface IPRPWeightegesDAO extends GenericDao<PRPWeighteges, Long>{
	public List<?> getPRPWeightegeDetails();
	
	public List<?> getPRPWeightageDetailsByConstiIds(List<Long> constiIds);
	
	public Double getPRPWeightageByConstiId(Long constiId);
}
