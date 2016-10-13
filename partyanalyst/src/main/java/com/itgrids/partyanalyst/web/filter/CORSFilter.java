package com.itgrids.partyanalyst.web.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * @author Srishailam Pittala
 * @ srishailam.pittala@itgrids.com
 * @date 13th Oct, 2016
 * @description: This filter used to give web service access to other domains (other than www.mytdp.com) 
 */
public class CORSFilter implements ContainerResponseFilter {
    
	public ContainerResponse filter(ContainerRequest request,ContainerResponse response) {

        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHttpHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
        response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHttpHeaders().add("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS, HEAD");
        return response;
    }
	
	/*
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";

    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";

    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With, Accept";

    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = "GET, POST, PUT, DELETE, OPTIONS, HEAD";

    public static final String[] ALL_HEADERs = {
            ACCESS_CONTROL_ALLOW_ORIGIN,
            ACCESS_CONTROL_ALLOW_CREDENTIALS,
            ACCESS_CONTROL_ALLOW_HEADERS,
            ACCESS_CONTROL_ALLOW_METHODS
    };
    public static final String[] ALL_HEADER_VALUEs = {
            ACCESS_CONTROL_ALLOW_ORIGIN_VALUE,
            ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE,
            ACCESS_CONTROL_ALLOW_HEADERS_VALUE,
            ACCESS_CONTROL_ALLOW_METHODS_VALUE
    };
    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        for (int i = 0; i < ALL_HEADERs.length; i++) {
            ArrayList<Object> value = new ArrayList<>();
            value.add(ALL_HEADER_VALUEs[i]);
            response.getHttpHeaders().put(ALL_HEADERs[i], value); //using put method
        }
        return response;
    }
	 * */
}
