package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.ProblemDetailsVO;
import com.itgrids.partyanalyst.dto.ProblemManagementVO;

public class ConstituencyManagementAction extends ActionSupport implements ServletRequestAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConstituencyManagementVO constituencyManagementVO;
	private List<ProblemDetailsVO> problemDetailsList;

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

public String execute() throws Exception{
		
		System.out.println("In execute of Constituency Management Action ********");
		
		constituencyManagementVO = new ConstituencyManagementVO();
		ProblemManagementVO problemManagementVO = new ProblemManagementVO();
		problemDetailsList = new ArrayList<ProblemDetailsVO>();		
		
		ProblemDetailsVO problemDetailsVO = new ProblemDetailsVO(); 
		problemDetailsVO.setDefinition("Impurity water");
		problemDetailsVO.setDescription("Polluted water is beign supplied");
		problemDetailsVO.setIdentifiedDate("03/04/2009");
		problemDetailsVO.setLocation("Madanapalle");
		problemDetailsVO.setSource("Party Analyst");
		
		ProblemDetailsVO problemDetailsVO1 = new ProblemDetailsVO();
		problemDetailsVO1.setDefinition("No Bus Service");
		problemDetailsVO1.setDescription("Bus service cancelled to village");
		problemDetailsVO1.setIdentifiedDate("01/03/2009");
		problemDetailsVO1.setLocation("Nagavaram");
		problemDetailsVO1.setSource("VIctim");
		
		problemDetailsList.add(problemDetailsVO);
		problemDetailsList.add(problemDetailsVO1);
		
		problemManagementVO.setProblemDetails(problemDetailsList);
		
		constituencyManagementVO.setProblemManagementVO(problemManagementVO);
		return Action.SUCCESS;
	}
}


