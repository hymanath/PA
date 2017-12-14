package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.dto.UserVO;

public interface IPmRequestDetailsService {
	public List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(InputVO inputVO);
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO);
	public List<RepresentationRequestVO> getPetitionReferredMemberDetails(RepresentationRequestVO dataVo);
	public PmRequestEditVO setPmRepresenteeDataToResultView(Long petitionId);
	public List<RepresenteeViewVO> getStatusList();
	public UserVO getPmOffceUserDetails(Long userId, UserVO userVO);
}
