package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DemoRequestVO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IMarketingManagementService {
	
	public ResultStatus saveDemoRequestData(EmailDetailsVO emailDetailsVO);
	
	public List<DemoRequestVO> getDemoRequestData();
	
	public DemoRequestVO getSelectedDemoRequestData(Long demoRequestId);
	
	public ResultStatus saveDemoRequestActionsData(Long demoRequestId,String type,String content,String response,Long userId);
	
	public List<SelectOptionVO> getDemoRequestActionTypes();
	
	public ResultStatus deleteDemoRequestData(Long demoRequestId);
	
	public ResultStatus upDateDemoRequestDetails(DemoRequestVO demoRequestVO);
	

}
