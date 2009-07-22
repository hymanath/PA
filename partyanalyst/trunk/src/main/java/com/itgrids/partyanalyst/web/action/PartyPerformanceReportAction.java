/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.PartyPerformanceReportVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IPartyService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action class for PartyPerformance Report
 * 
 * @author Sujatha Boddu
 */

public class PartyPerformanceReportAction extends ActionSupport implements ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(PartyPerformanceJasperAction.class);

    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext context;
  
	private IPartyService partyService;
	private PartyPerformanceReportVO reportVO;
	
    public PartyPerformanceReportVO getStateData() {
		return reportVO;
	}

	public void setStateData(PartyPerformanceReportVO stateData) {
		this.reportVO = stateData;
	}

	public void setPartyService(IPartyService partyService) {
        this.partyService = partyService;
    }

    public IPartyService gePartyrService() {
		return partyService;
	}
    
    public String execute() {
		
		log.debug("action started...");
		
        
		reportVO = gePartyrService().getStateLevelPartyReport("A.P", "CONGRESS", "2009");
		
		Map<Integer, Integer> positions = reportVO.getPositionDistribution();
		
		positions.put(1, reportVO.getTotalSeatsWon());
		
		session = request.getSession();
        String chartName = context.getRealPath("/") + "charts\\partyPositionsChart_"+session.getId()+".png";
       
		ChartProducer.createPie3DChart(positions, chartName, "Party Positions");
		
		session.setAttribute("reportVO", reportVO);
		return SUCCESS;
    }

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
}
