package com.itgrids.tpi.rws.service;

import com.itgrids.dto.InputVO;

public interface IRwsWorksSchedulerService {

	
	public String getWorksDataInsertion(InputVO inputVO);
	
	public String getEncworkDataInsertion();

	public String getWorksDataDeletion();

	public boolean getWorkDetails();

	public void insertENCWorkHabs();
}
