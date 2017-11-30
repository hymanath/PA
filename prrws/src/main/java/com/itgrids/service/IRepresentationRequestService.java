package com.itgrids.service;

import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;


public interface IRepresentationRequestService {
	public ResponseVO saveRepresentRequestDetails(RepresentationRequestVO dataVO);
}
