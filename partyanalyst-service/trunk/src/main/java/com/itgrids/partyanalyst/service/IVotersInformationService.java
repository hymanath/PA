package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AgeRangeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IVotersInformationService {

public ResultStatus	gettingVotersInfoService(String constituencyId,String publicationId,String ageOrCasteRadio,List<String> ageRanges,boolean pId,String path);
	
}
