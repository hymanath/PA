/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 8, 2010
 */
package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;

public interface IProblemManagementService {

	public ProblemManagementDataVO getProblemsForAHamlet(Long hamletId,String year);
}
