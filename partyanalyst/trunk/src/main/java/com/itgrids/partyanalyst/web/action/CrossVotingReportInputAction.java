package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.web.context.ServletConfigAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import com.itgrids.partyanalyst.dto.SelectOptionVO;


public class CrossVotingReportInputAction extends ActionSupport implements ServletContextAware,ServletRequestAware {

	private List<SelectOptionVO> electionYearList;
	private List<SelectOptionVO> partyList;
	
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public List<SelectOptionVO> getElectionYearList() {
		return electionYearList;
	}

	public void setElectionYearList(List<SelectOptionVO> electionYearList) {
		this.electionYearList = electionYearList;
	}

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public String execute(){
		
		electionYearList = new ArrayList<SelectOptionVO>();
		partyList =  new ArrayList<SelectOptionVO>();
		
		SelectOptionVO eList1 = new SelectOptionVO();
		eList1.setId(new Long(2009));
		eList1.setName("2009");
		
		SelectOptionVO eList2 = new SelectOptionVO();
		eList2.setId(new Long(2004));
		eList2.setName("2004");
		
		electionYearList.add(eList1);
		electionYearList.add(eList2);
		setElectionYearList(electionYearList);
		
		SelectOptionVO pList1 =  new SelectOptionVO();
		pList1.setId(new Long(1));
		pList1.setName("INC");
		
		SelectOptionVO pList2 =  new SelectOptionVO();
		pList2.setId(new Long(2));
		pList2.setName("BJP");
		
		SelectOptionVO pList3 =  new SelectOptionVO();
		pList3.setId(new Long(4));
		pList3.setName("CPI");
		
		SelectOptionVO pList4 =  new SelectOptionVO();
		pList4.setId(new Long(5));
		pList4.setName("CPM");
		
		SelectOptionVO pList5 =  new SelectOptionVO();
		pList5.setId(new Long(9));
		pList5.setName("PRP");
		
		SelectOptionVO pList6 =  new SelectOptionVO();
		pList6.setId(new Long(10));
		pList6.setName("TRS");
		
		SelectOptionVO pList7 =  new SelectOptionVO();
		pList6.setId(new Long(11));
		pList6.setName("TDP");
		
		partyList.add(pList1);
		partyList.add(pList2);
		partyList.add(pList3);
		partyList.add(pList4);
		partyList.add(pList5);
		partyList.add(pList6);
		partyList.add(pList7);
		setPartyList(partyList);
		
		return Action.SUCCESS;
	}
	

}
