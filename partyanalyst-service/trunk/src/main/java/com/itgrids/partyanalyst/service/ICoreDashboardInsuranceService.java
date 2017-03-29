package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ComplaintMasterVO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.dto.InsuranceLagDaysVO;
import com.itgrids.partyanalyst.dto.InsuranceSimpleVO;

public interface ICoreDashboardInsuranceService {

	public List<CoreDashboardInsuranceVO> getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(Long activityMemberId,Long cadreYearId,Long stateId,
			String fromDateStr,String toDateStr);
	public List<ComplaintMasterVO> getInsuraceStatusWiseComplaintsDetails(Long activityMemberId,Long cadreYearId,Long stateId,
			String fromDateStr,String toDateStr,String status,Long companyId,String issueType);
	
	public List<List<UserTypeVO>> getUserTypeWiseTotalCadreInsuranceComplainctCnt(Long activityMemberId,Long userId,Long userTypeId,Long stateId,Long cadreEnrollmentYearId,String fromDateStr,String toDateStr);
	public InsuranceLagDaysVO getLagDaysInsuranceComplaintsCounts(Long activityMemberId,Long cadreYearId,Long stateId,String status,Long companyId,String issueType);
	public InsuranceSimpleVO getStatusTrackingDetailsOfInsuranceByComplaint(Long complaintId);
	public ComplaintMasterVO getComplaintScanCopyDetails(Long complaintId);
	public CoreDashboardInsuranceVO getRemarksByComplaint(Long complaitnId);
	public CoreDashboardInsuranceVO getComplaintResponsesByComplaint(Long complaintId);
	
	public List<UserTypeVO> getSelectedChildMemberCadreInsuranceComplainctCnt(Long parentActivityMemberId,List<Long> childUserTypeIds,String reportType,Long stateId,Long cadreEnrollmentYearId,String fromDateStr,String toDateStr);
	public List<UserTypeVO> getCandiateWiseCadreInsurencaeDtls(Long activityMemberId,Long stateId,Long cadreEnrollmentYearId,String fromDateStr,String toDateStr);
}
