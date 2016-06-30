package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;

public interface IAlertService {
	public List<BasicVO> getCandidatesByName(String candidateName);

}
