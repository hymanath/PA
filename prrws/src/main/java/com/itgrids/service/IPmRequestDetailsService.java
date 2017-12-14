package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;

public interface IPmRequestDetailsService {
	public List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(InputVO inputVO);
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO);
	public List<RepresentationRequestVO> getPetitionReferredMemberDetails(RepresentationRequestVO dataVo);
	public PmRequestVO setPmRepresenteeDataToResultView(Long petitionId);
}
