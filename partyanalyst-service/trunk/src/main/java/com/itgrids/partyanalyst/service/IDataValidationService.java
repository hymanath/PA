package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DataVerificationVO;
import com.itgrids.partyanalyst.excel.booth.DataValidationVO;

public interface IDataValidationService {
	
	public List<DataValidationVO> getHamletsAssignedValidation(Long constituencyId,Long publicationDateId,Long userId);
	
	public DataVerificationVO validateVotersBasicInfo(Long constituencyId,Long publicationId,Long userId);

}
