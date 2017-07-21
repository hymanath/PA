package com.itgrids.service.integration.impl;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.NregaConsolidatedDataVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.dto.NregsProjectsVO;

public interface INREGSConsolidatedService {
	
	public List<IdNameVO> getAllConvergenceTypes();
	public List<IdNameVO> getComponentByConvergType(NregaConsolidatedInputVO nregaConsolidatedInputVO);
	public List<NregsProjectsVO> getNREGSConsolidatedAbstrct(NregaConsolidatedInputVO inputVO);
	public List<NregaConsolidatedDataVO> getNREGSLevelWiseConsolidatedReport(NregaConsolidatedInputVO inputVO);
}
