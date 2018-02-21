package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.DocumentVO;
import com.itgrids.dto.GovtMainWorkVO;
import com.itgrids.dto.GovtWorksVO;
import com.itgrids.dto.MobileAppInputVO;
import com.itgrids.dto.MobileAppLoginVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.WorkStatusVO;

public interface IUnderGroundDrainageService {
	public MobileAppLoginVO checkLogin(MobileAppInputVO inputVO);
	//public List<GovtWorksVO> getWorkDetailsOfMobileAppUser(Long mobileAppUserId,Long workTypeId);
	public ResultStatus saveWorkDetails(GovtWorksVO govtWorksVO);
	public ResultStatus updateWorkStatus(List<WorkStatusVO> WorkStatusVOList);
	public List<WorkStatusVO> getAllTheStatusOfGovtWork(Long workId);
	public List<GovtMainWorkVO> getUsersGovtMainWorks(Long userId,Long workTypeId);
	public List<GovtWorksVO> getAllGovtWorksOfGovtMainWork(Long userId,Long mainWorkId);
	public List<DocumentVO> updateWorkStatusDocuments(List<WorkStatusVO> WorkStatusVOList);
	public List<WorkStatusVO> getStatusWiseDayReport(MobileAppInputVO inputVO);
	
	public List<GovtMainWorkVO> getWorkTypeWiseCompletedDetails(MobileAppInputVO inputVO);
}
