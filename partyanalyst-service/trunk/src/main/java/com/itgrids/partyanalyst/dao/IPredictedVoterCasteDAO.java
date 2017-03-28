package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PredictedVoterCaste;

public interface IPredictedVoterCasteDAO extends GenericDao<PredictedVoterCaste, Long>{
	
	public List<Object[]> getPredictedCasteList();
	
	public List<Long> getVoterIdsFromCastePredictionForACaste(Long casteStateId,Integer firstRecord,Integer maxRecords);
	
	public List<Integer> getVoterIdsFromCastePrediction(Long userId,Long casteStateId,Integer firstRecord,Integer maxRecords);
}
