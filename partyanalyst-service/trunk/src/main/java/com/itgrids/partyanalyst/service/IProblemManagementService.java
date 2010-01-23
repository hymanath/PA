/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IProblemManagementService {

	public ProblemManagementDataVO getProblemsForAHamlet(Long hamletId,String year);
	public ProblemBeanVO saveProblemData(ProblemBeanVO problemBeanVO);
	public List<ProblemBeanVO> getProblemsForUser(Long registrationId);
}
