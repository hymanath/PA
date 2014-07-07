package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;

public class CandidateUpdationDetailsService implements ICandidateUpdationDetailsService{

private static final Logger LOG = Logger.getLogger(CandidateUpdationDetailsService.class);
	
	@Autowired
	private INominationDAO nominationDAO;
	
	public CandidateVO getCandidateDetailsForElection(Long electionId,Long districtId)
	{
		CandidateVO resultVo = new CandidateVO();
		
		
		
		
		return resultVo;
	}
	
}
