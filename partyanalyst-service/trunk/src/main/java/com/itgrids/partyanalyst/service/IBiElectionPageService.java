/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 10, 2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BiElectionDistrictVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionResultsVO;

public interface IBiElectionPageService {

	public MandalAllElectionResultsVO getAllPartiesElectionResultsInAMandal(Long mandalId);
	
	public List<BiElectionDistrictVO> getBiElectionConstituenciesDistrictWise();
	
	public List<MandalAllElectionResultsVO> getAllPartysElectionResultsInAConstituencyMandalWise(Long constituencyId);
}
