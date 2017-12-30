package com.itgrids.service;

import java.util.List;
import java.util.Map;

import com.itgrids.dto.CadreRegistrationVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.UserVO;

public interface IPmRequestDetailsService {
	public List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(InputVO inputVO);
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO);
	public List<RepresentationRequestVO> getPetitionReferredMemberDetails(RepresentationRequestVO dataVo);
	public PmRequestEditVO setPmRepresenteeDataToResultView(Long petitionId,String pageType);
	public List<RepresenteeViewVO> getStatusList();
	public UserVO getPmOffceUserDetails(Long userId, UserVO userVO);
	
	public CadreRegistrationVO getRegistrationPersonDetails(Map<String,String> inputMap);
	public RepresenteeViewVO getCompleteOrStatusOverviewDetails(Long userId,String startDate,String endDate);
	public KeyValueVO getDeptIdsListBYUserIds(Long userId);
	public KeyValueVO getPmDeptStatusIdsByUserIdsLst(Long userId);
	public List<RepresenteeViewVO> getLeadWiseOverviewDetails(Long userId,String startDate,String endDate);
	public ResultStatus updatePetitionsStatusDetails(Long userId,List<Long> petitionIdsList, List<Long> subWorkIdsList,String remark,Long statusId);
}
