package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IKeywordsService {
	public SelectOptionVO getkeywords(String searchStr,Integer startIndex,Integer maxIndex);
}
