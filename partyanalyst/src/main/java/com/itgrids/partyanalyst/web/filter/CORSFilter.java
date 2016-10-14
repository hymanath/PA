package com.itgrids.partyanalyst.web.filter;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * @author Srishailam Pittala
 * @ srishailam.pittala@itgrids.com
 * @date 13th Oct, 2016
 * @description: This filter used to give web service access to other domains (other than www.mytdp.com) 
 */

@Provider
public class CORSFilter implements ContainerResponseFilter  {//extends OncePerRequestFilter  {
    	 
	    public ContainerResponse filter(ContainerRequest req, ContainerResponse crunchifyContainerResponse) {
	 
	        ResponseBuilder crunchifyResponseBuilder = Response.fromResponse(crunchifyContainerResponse.getResponse());
	        
	        // *(allow from all servers) OR http://crunchify.com/ OR http://example.com/
	        crunchifyResponseBuilder.header("Access-Control-Allow-Origin", "*")
	        
	        // As a part of the response to a request, which HTTP methods can be used during the actual request.
	        .header("Access-Control-Allow-Methods", "API, CRUNCHIFYGET, GET, POST, PUT, UPDATE, OPTIONS")
	        
	        // How long the results of a request can be cached in a result cache.
	        .header("Access-Control-Max-Age", "151200")
	        
	        // As part of the response to a request, which HTTP headers can be used during the actual request.
	        .header("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
	 
	        String crunchifyRequestHeader = req.getHeaderValue("Access-Control-Request-Headers");
	 
	        if (null != crunchifyRequestHeader && !crunchifyRequestHeader.equals(null)) {
	            crunchifyResponseBuilder.header("Access-Control-Allow-Headers", crunchifyRequestHeader);
	        }
	 
	        crunchifyContainerResponse.setResponse(crunchifyResponseBuilder.build());
	        return crunchifyContainerResponse;
	    }

	/*protected void doFilterInternal(HttpServletRequest request,	HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
			response.addHeader("Access-Control-Allow-Origin", "*");
	       // if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
	            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
	            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	            //response.addHeader("Access-Control-Max-Age", "1");
	        //}
	        filterChain.doFilter(request, response);
	}
	*/
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
   
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        for (int i = 0; i < ALL_HEADERs.length; i++) {
            ArrayList<Object> value = new ArrayList<>();
            value.add(ALL_HEADER_VALUEs[i]);
            response.getHttpHeaders().put(ALL_HEADERs[i], value); //using put method
        }
        return response;
    }
	*/
}