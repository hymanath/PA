package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PoliticalFeedBackVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPoliticalFeedBackService {

	
	public ResultStatus savePoliticalFeedBack(PoliticalFeedBackVO politicalFeedBackVO);
	
	public List<SelectOptionVO> getAssemblyListByPCId(Long pcId);
	
	public List<SelectOptionVO> getAccessParliments(Long userId);
	
	public List<PoliticalFeedBackVO> getSelectedPolitialFeedBackDetails(String date,Long pcId);
	
	public List<SelectOptionVO> getAllParlimentConstituencys();
}
