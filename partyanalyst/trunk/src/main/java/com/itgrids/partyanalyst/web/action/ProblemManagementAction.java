package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemManagementAction extends ActionSupport implements ServletRequestAware{
	
	private final static Logger log = Logger.getLogger(ProblemManagementAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> problemSources;
	private ProblemBeanVO problemBeanVO;
	private HttpServletRequest request; 
	
	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<SelectOptionVO> getProblemSources() {
		return problemSources;
	}

	public void setProblemSources(List<SelectOptionVO> problemSources) {
		this.problemSources = problemSources;
	}
	

public String execute() throws Exception{
		
		
	SelectOptionVO probSource1 = new SelectOptionVO(1L, "Party Analyst");
	SelectOptionVO probSource2 = new SelectOptionVO(2L, "Call Center");
	SelectOptionVO probSource3 = new SelectOptionVO(3L, "User");
	SelectOptionVO probSource4 = new SelectOptionVO(4L, "External Person");
	problemSources = new ArrayList<SelectOptionVO>();
	problemSources.add(probSource1);
	problemSources.add(probSource2);
	problemSources.add(probSource3);
	problemSources.add(probSource4);
	log.debug("Values Entered In the Form");
	try{
		 //problemBeanVO = new ProblemBeanVO();
		 BeanUtils.populate(problemBeanVO , request.getParameterMap());
		 /*
		 System.out.println(problemBeanVO.getProblem());
		 System.out.println(problemBeanVO.getDescription());
		 System.out.println(problemBeanVO.getLocation());
		 System.out.println(problemBeanVO.getReportedDate());
		 System.out.println(problemBeanVO.getExistingFrom());
		 System.out.println(problemBeanVO.getProbSource());
		 System.out.println(problemBeanVO.getName());
		 System.out.println(problemBeanVO.getMobile());
		 System.out.println(problemBeanVO.getPhone());
		 System.out.println(problemBeanVO.getEmail());
		 System.out.println(problemBeanVO.getAddress());
		 */
		
		 log.debug(problemBeanVO.getProblem());
		 log.debug(problemBeanVO.getDescription());
		 log.debug(problemBeanVO.getLocation());
		 log.debug(problemBeanVO.getReportedDate());
		 log.debug(problemBeanVO.getExistingFrom());
		 log.debug(problemBeanVO.getProbSource());
		 log.debug(problemBeanVO.getName());
		 log.debug(problemBeanVO.getMobile());
		 log.debug(problemBeanVO.getPhone());
		 log.debug(problemBeanVO.getEmail());
		 log.debug(problemBeanVO.getAddress());
		 
	}catch(Exception exception){
		 exception.printStackTrace();
	 } 
	
	
	
	
	return Action.SUCCESS;
	}




}
