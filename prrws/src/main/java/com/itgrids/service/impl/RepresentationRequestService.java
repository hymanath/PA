package com.itgrids.service.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.service.IRepresentationRequestService;

@Service
@Transactional
public class RepresentationRequestService implements IRepresentationRequestService{
	private static final Logger LOG = Logger.getLogger(RepresentationRequestService.class);
	
	public ResponseVO saveRepresentRequestDetails(final RepresentationRequestVO dataVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			
		} catch (Exception e) {
			responseVO.setResponseCode("1");
			responseVO.setMessage("Problem Occured while updation Details.");
			LOG.error("Exception Occured in RepresentationRequestService "+responseVO.getMessage());
		}
		return responseVO;
	}
}
