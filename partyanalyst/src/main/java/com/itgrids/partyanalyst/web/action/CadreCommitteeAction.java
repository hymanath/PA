package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreCommitteeAction   extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1333940914727615961L;
	private static final Logger         		LOG = Logger.getLogger(CadreCommitteeAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private ICadreCommitteeService   		 	cadreCommitteeService;
	private CadreCommitteeVO 					cadreCommitteeVO;
	private Long 								tdpCadreId;
	private ITdpCadreReportService              tdpCadreReportService;
	private List<LocationWiseBoothDetailsVO>    locations;
	private List<BasicVO>                       constituencies;
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	public CadreCommitteeVO getCadreCommitteeVO() {
		return cadreCommitteeVO;
	}

	public void setCadreCommitteeVO(CadreCommitteeVO cadreCommitteeVO) {
		this.cadreCommitteeVO = cadreCommitteeVO;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setCadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
			this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}

	public List<LocationWiseBoothDetailsVO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationWiseBoothDetailsVO> locations) {
		this.locations = locations;
	}

	public List<BasicVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<BasicVO> constituencies) {
		this.constituencies = constituencies;
	}

	public String execute()
	{
		//cadreCommitteeVO = cadreCommitteeService.getCadreDetailsByTdpCadreId(tdpCadreId);
		
		return Action.SUCCESS;
	}
	
	public List<BasicVO> getUserAccessConstituencies(){
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		return tdpCadreReportService.getAccessLocationValues(user.getAccessType(),Long.valueOf(user.getAccessValue().trim()));
	}
	
	public String getCommitteLocations(){
		try{
		   String locationType = request.getParameter("locationType");
		   constituencies = getUserAccessConstituencies();

		   locations = cadreCommitteeService.getLocationsList(constituencies.get(0).getId(),locationType);
		   
		}catch(Exception e){
			LOG.error("Exception rised in getComitteLocations",e);
		}
			return Action.SUCCESS;
		
	}
}
