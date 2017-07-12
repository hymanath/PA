package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothDataValidationService {

	public UploadDataErrorMessageVO readVoterExcelDataAndValidate(
			File filePath, String electionYear, Long stateId,
			Long electionTypeId ,  String publicationDate) throws CsvException;
	public List<BoothInchargeDetailsVO> getLocationLevelWiseBoothCount(InputVO inputVO);
}
