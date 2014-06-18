package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SoundexVO;

public interface ISoundexService {
	
	public List<SoundexVO> getMappedVoterDetailsByUsingSoundexByPanchayatId(Long panchayatId);


}
