package com.itgrids.service.integration.impl;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LabourBudgetOverViewVO;
import com.itgrids.dto.NregsProjectsVO;

public interface INREGSTCSService {

	public List<NregsProjectsVO> getNREGSProjectsOverview(InputVO inputVO);
	public LabourBudgetOverViewVO getLabourBudgetOverview(InputVO inputVO);
	public List<IdNameVO> getLabourBudgetExpenditure(InputVO inputVO);
}
