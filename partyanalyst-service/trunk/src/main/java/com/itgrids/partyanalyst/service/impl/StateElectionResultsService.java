/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IStateElectionResultsService;

public class StateElectionResultsService implements
		IStateElectionResultsService {

	private ICandidateResultObjectsDAO candidateResultObjectsDAO;
	private INominationDAO nominationDAO;
	
	public ICandidateResultObjectsDAO getCandidateResultObjectsDAO() {
		return candidateResultObjectsDAO;
	}
	public void setCandidateResultObjectsDAO(
			ICandidateResultObjectsDAO candidateResultObjectsDAO) {
		this.candidateResultObjectsDAO = candidateResultObjectsDAO;
	}
	
   public INominationDAO getNominationDAO() {
		return nominationDAO;
	}
	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
    @SuppressWarnings("unchecked")
	public StateElectionResultsVO getStateElectionResults(Long electionId){
		
		List<PartyResultsVO> partyResultsVO = null;
		StateElectionResultsVO stateElectionResults = new StateElectionResultsVO();
		Long partyId = new Long(0);
		String partyName = "";
		Long seatsWon=new Long(0);
		
		List elecResults = nominationDAO.findElectionDataByElectionId(electionId);
		if(elecResults != null){
		partyResultsVO = new ArrayList<PartyResultsVO>(0);
		for(int i=0; i<elecResults.size(); i++){
			Object[] params = (Object[])elecResults.get(i);
			partyId  = (Long)params[0];
			partyName = (String)params[1];
			seatsWon = (Long)params[2];
			
			PartyResultsVO partyResults = new PartyResultsVO();
			partyResults.setPartyId(partyId);
			partyResults.setPartyName(partyName);
			partyResults.setTotalSeatsWon(seatsWon);
			
			partyResultsVO.add(partyResults);
		}
		}
		stateElectionResults.setPartyResultsVO(partyResultsVO);
				  
	return stateElectionResults;
	}

}
