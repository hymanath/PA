package com.itgrids.cardprint.soa.endpoints.impl;

import javax.jws.WebService;

import com.itgrids.cardprint.soa.endpoints.SOAPWebServiceEndPoint;

@WebService(endpointInterface="com.itgrids.cardprint.soa.endpoints.SOAPWebServiceEndPoint",name="SOAPWebServiceEndPoint")
public class SOAPWebServiceEndPointImpl implements SOAPWebServiceEndPoint {
  
	public SOAPWebServiceEndPointImpl() {
		super();
		System.out.println("web object created ===========");
	}
	
}
