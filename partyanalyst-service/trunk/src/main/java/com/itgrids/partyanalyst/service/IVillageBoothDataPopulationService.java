package com.itgrids.partyanalyst.service;

import java.io.File;

import com.itgrids.partyanalyst.dto.VillageBoothElectionVO;

public interface IVillageBoothDataPopulationService {

	public VillageBoothElectionVO readExcelAndInsertData(File fPath, Long elecId, Boolean isValidate);
	
}
