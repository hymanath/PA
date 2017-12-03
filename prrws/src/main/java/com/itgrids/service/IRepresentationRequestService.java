package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;


public interface IRepresentationRequestService {
	public ResponseVO saveRepresentRequestDetails(RepresentationRequestVO dataVO);
	public List<RepresentationRequestVO> getPetitionReferredMemberDetails(RepresentationRequestVO dataVo );
	public List<RepresentationRequestVO> getRepresentativeSearchWiseDetails(InputVO inputVO);
	public RepresentationRequestVO getRepresentationRequestDetailsByRepresentationRequestId(Long representationMemberId);
}
