package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.BoothAddressVO;
import com.itgrids.partyanalyst.dto.BoothInchargeDetailsVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UploadDataErrorMessageVO;
import com.itgrids.partyanalyst.excel.CsvException;

public interface IBoothDataValidationService {

	public UploadDataErrorMessageVO readVoterExcelDataAndValidate(
			File filePath, String electionYear, Long stateId,
			Long electionTypeId ,  String publicationDate) throws CsvException;
	public List<BoothInchargeDetailsVO> getLocationLevelWiseBoothCount(InputVO inputVO);
	public List<BoothInchargeDetailsVO> getLocationBasedOnSelection(InputVO inputVO);
	public List<BoothAddressVO> getLocationLevelWiseBoothDetails(InputVO inputVO);
	public List<IdAndNameVO> getBoothInchargeRoles(Long boothId);
}
