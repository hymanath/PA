package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreRegUserVO;
import com.itgrids.partyanalyst.dto.DataMonitoringOverviewVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

import com.itgrids.partyanalyst.dto.IdNameVO;

public interface IDataMonitoringService {
	public IdNameVO getTotalRegCdrVendorWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate);
	public List<IdNameVO> getTotalRegCdrVendorAndTabUserWise(Long stateId, Long vendorId, Long distId, Long constId, String startDate, String endDate);
	public DataMonitoringOverviewVO getDataMonitoringOverViewDetails(String fromDateStr,String toDateStr,Long stateId);
	public List<List<IdNameVO>> getVerifiedDtls(Long surveyUserId, Long tabUserId, Long webUserId, String startDate, String endDate,Integer minValue,Integer maxValue,String resultType,String verificationStatus,String dataSourceType,Long stateId);
	public List<DataMonitoringOverviewVO> getRegistrationDetailsUserWise(String fromDateStr,String toDateStr,String dataSourceType,String verificationStatus,Long stateId);
	public List<IdNameVO> getDataRejectReason();
	public ResultStatus updateRejectDtls(List<IdNameVO> idNameVOs);
	public ResultStatus updateApproveDtls(List<IdNameVO> idNameVOs);
	//public List<CadreRegUserVO> getCadreRegUserAssignedConstituencies(Long userId);
	public List<CadreRegUserVO> getCadreRegUserAssignedConstituencies(Long userId,String userType,Long districtId);
	//public List<CadreRegUserVO> getCadreRegUserAssignedUsers(Long userId,Long constituencyId);
	public List<CadreRegUserVO> getCadreRegUserAssignedUsers(Long userId,Long constituencyId,String userType);
	public IdNameVO getTotalRegCdrVendorWiseNew(Long loginUserId, Long userId, Long constId, String startDate, String endDate);
	public List<IdNameVO> getTotalRegCdrVendorAndTabUserWiseNew(Long loginUserId, Long userId, Long constId, String startDate, String endDate);
	public String getTabUserImages();
	public List<CadreRegUserVO> getCadreRegUserAssignedDistricts(Long userId,String userType);
	public ResultStatus changeImageByVoterImage(List<IdNameVO> idNameVOs);
}
