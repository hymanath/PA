package com.itgrids.service.integration.impl;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.NregaConsolidatedInputVO;

public interface INREGSConsolidatedService {
	
	public List<IdNameVO> getAllConvergenceTypes();
	public List<IdNameVO> getComponentByConvergType(NregaConsolidatedInputVO nregaConsolidatedInputVO);

}
