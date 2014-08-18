package com.itgrids.partyanalyst.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ISurveyCallStatusDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyFinalDataDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.service.ICtpDashBoardService;

public class CtpDashBoardService implements ICtpDashBoardService
{

	@Autowired
	private ISurveyDetailsInfoDAO surveyDetailsInfoDAO;
	
	@Autowired
	private ISurveyCallStatusDAO surveyCallStatusDAO;
	
	@Autowired
	private ISurveyFinalDataDAO surveyFinalDataDAO;
	
	@Autowired
	private IUserVoterDetailsDAO userVoterDetailsDAO;
}
