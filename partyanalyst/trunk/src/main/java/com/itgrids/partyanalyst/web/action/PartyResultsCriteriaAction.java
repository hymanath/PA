
package com.itgrids.partyanalyst.web.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.impl.PartyResultService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author Mohan
 *
 */
public class PartyResultsCriteriaAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private List<SelectOptionVO> partyList;
	//private String[] partyList={"INC","TDP","PRP","TRS","CPI","CPM","MIM"};

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}
	
	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}
		
	public String execute() {	
		System.out.println("IN PartyResultsCriteria action");
		
		List<SelectOptionVO> partyNames=new ArrayList<SelectOptionVO>();
		
		SelectOptionVO partySelectOptionVO1 = new SelectOptionVO();
		partySelectOptionVO1.setId(new Long(24));
		partySelectOptionVO1.setName("INC");
		
		SelectOptionVO partySelectOptionVO2 = new SelectOptionVO();
		partySelectOptionVO2.setId(new Long(15));
		partySelectOptionVO2.setName("BJP");
		
		SelectOptionVO partySelectOptionVO3 = new SelectOptionVO();
		partySelectOptionVO3.setId(new Long(62));
		partySelectOptionVO3.setName("TDP");				
		
		SelectOptionVO partySelectOptionVO4 = new SelectOptionVO();
		partySelectOptionVO4.setId(new Long(43));
		partySelectOptionVO4.setName("PRP");
		
		SelectOptionVO partySelectOptionVO5 = new SelectOptionVO();
		partySelectOptionVO5.setId(new Long(61));
		partySelectOptionVO5.setName("TRS");
		
		partyNames.add(partySelectOptionVO1);
		partyNames.add(partySelectOptionVO2);
		partyNames.add(partySelectOptionVO3);
		partyNames.add(partySelectOptionVO4);
		partyNames.add(partySelectOptionVO5);
		//partyList={"INC","TDP","PRP","TRS","CPI","CPM","MIM"};
		setPartyList(partyNames);		
		return SUCCESS;
		
	}

	
	
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

}
