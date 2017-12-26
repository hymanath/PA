package com.itgrids.tpi.rws.service;

import java.util.List;

import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;

public interface IRwsWorksSchedulerService {

	
	public List<IdNameVO> getWorksDataInsertion(InputVO inputVO);
	
	public boolean getWorkDetails2();

	public String getEncworkDataInsertion();
}
