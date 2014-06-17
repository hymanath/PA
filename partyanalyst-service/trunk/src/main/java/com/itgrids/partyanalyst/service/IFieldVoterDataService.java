package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.FieldVoterDataVO;

public interface IFieldVoterDataService {

	public List<FieldVoterDataVO> getFieldVoterDataByBoothId(Long boothId);

}
