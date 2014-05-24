package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IMahaNaduService {
	public List<SelectOptionVO> getBoothsInAConstituency(Long constituencyId,Long publicationID);
}
