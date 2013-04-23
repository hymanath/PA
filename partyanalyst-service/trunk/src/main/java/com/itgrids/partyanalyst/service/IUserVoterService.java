package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IUserVoterService {
	public List<SelectOptionVO> getUserVoterCategoryList(List<Long> userId);
}
