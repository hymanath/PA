/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.ConstituencyPositionDetailVO;
import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.dto.PositionType;
import com.itgrids.partyanalyst.helper.JasperProducer;
import com.itgrids.partyanalyst.service.IPartyService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action class for PartyPerformance Jasper Report
 * 
 * @author <a href="mailto:sujatha.boddu@gmail.com">Sujatha Boddu</a>
 */

public class PartyPerformanceJasperAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(PartyPerformanceJasperAction.class);

    private HttpServletResponse response;
    private HttpServletRequest request; 

    private PartyPerformanceReportVO partyReportVO;
	private ServletContext context;

   /* public void setStaticDataService(IStaticDataService staticDataService) {
        this.staticDataService = staticDataService;
    }

    public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
    
    public void setStates(List<State> states) {
		this.states = states;
	}

	public void setStatesMap(HashMap<Long, String> statesMap) {
		this.statesMap = statesMap;
	}

	public List<State> getStates() {
        return states;
    }*/
    
    
	public String execute() throws JRException {
		
		log.debug("excute started...");
		String contextPath = context.getRealPath("/");
		
       	String jasperXML = contextPath + request.getParameter("jasperFile");	
       	log.debug(jasperXML);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REPORT_DIR", "charts\\partyPositionsChart_" + request.getSession().getId() + ".png");
		params.put("CONTEXT_PATH", contextPath);
		params.put("REPORT_TYPE", request.getParameter("type"));
		
		partyReportVO = (PartyPerformanceReportVO) request.getSession().getAttribute("reportVO");
		byte[] report = JasperProducer.createJasperReport(jasperXML, params, partyReportVO);
	   	response.reset();
	   	response.setContentType("application/pdf");
	   	response.setHeader("Content-Disposition", "inline; filename=\"new_report.pdf\"");
	    response.setBufferSize(report.length);
	    OutputStream out = null;
	    
	   	try {
			out  = response.getOutputStream();
			out.write(report);
		   	out.flush();
		   	response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	 
    }

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

}
