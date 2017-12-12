package com.itgrids.service;

import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.ResponseVO;

public interface IPmRequestDetailsService {
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO);
}
