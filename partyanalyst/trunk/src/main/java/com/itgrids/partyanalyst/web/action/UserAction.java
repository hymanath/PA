package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
public class UserAction extends ActionSupport implements ServletRequestAware {
	
	
	private HttpServletRequest request;
	private HttpSession session;
	private List<String> type = new ArrayList<String>();
	private List<String> gender = new ArrayList<String>();
	private List<SelectOptionVO> dobDay;
	private List<SelectOptionVO> dobYear;
	private List<SelectOptionVO> dobMonth;
	private List<SelectOptionVO> parties;
	private IStaticDataService staticDataService;
	private List<String> userType = new ArrayList<String>();
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		session = request.getSession();
	}
	
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}
	
	public List<String> getGender() {
		return gender;
	}
	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	public List<SelectOptionVO> getDobDay() {
		return dobDay;
	}
	public void setDobDay(List<SelectOptionVO> dobDay) {
		this.dobDay = dobDay;
	}
	public List<SelectOptionVO> getDobYear() {
		return dobYear;
	}
	public void setDobYear(List<SelectOptionVO> dobYear) {
		this.dobYear = dobYear;
	}
	public List<SelectOptionVO> getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(List<SelectOptionVO> dobMonth) {
		this.dobMonth = dobMonth;
	}

	public List<SelectOptionVO> getParties() {
		return parties;
	}
	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}	
	
	public List<String> getUserType() {
		return userType;
	}

	public void setUserType(List<String> userType) {
		this.userType = userType;
	}

	public String registration(){
		if(type.size()==0){
			type.add("COUNTRY");
			type.add("STATE");
			type.add("DISTRICT");
			type.add("MLA");
			type.add("MP");			
		}
		if(userType.size() == 0)
		{
			userType.add("Party");
			userType.add("Politician");			
		}
		if(gender.size() == 0){
			gender.add("Male");
			gender.add("Female");
		}		
		
		dobDay = new ArrayList<SelectOptionVO>();
		for(int i=1;i<=31;i++)
		{
			SelectOptionVO dayElement = new SelectOptionVO();
			dayElement.setId(new Long(i));
			dayElement.setName(i+"");
			dobDay.add(dayElement);
		}
		
		
		dobYear = new ArrayList<SelectOptionVO>();
		for(int i=1950;i<=2010;i++)
		{
			SelectOptionVO yearElement = new SelectOptionVO();
			yearElement.setId(new Long(i));
			yearElement.setName(i+"");
			dobYear.add(yearElement);
		}
		
		dobMonth = new ArrayList<SelectOptionVO>();
		
		SelectOptionVO janVO = new SelectOptionVO();
		janVO.setId(new Long(1));
		janVO.setName("January");		
		SelectOptionVO febVO = new SelectOptionVO();
		febVO.setId(new Long(2));
		febVO.setName("February");
		SelectOptionVO marVO = new SelectOptionVO();
		marVO.setId(new Long(3));
		marVO.setName("March");
		SelectOptionVO aprVO = new SelectOptionVO();
		aprVO.setId(new Long(4));
		aprVO.setName("April");
		SelectOptionVO mayVO = new SelectOptionVO();
		mayVO.setId(new Long(5));
		mayVO.setName("May");
		SelectOptionVO junVO = new SelectOptionVO();
		junVO.setId(new Long(6));
		junVO.setName("June");
		SelectOptionVO julVO = new SelectOptionVO();
		julVO.setId(new Long(7));
		julVO.setName("July");
		SelectOptionVO augVO = new SelectOptionVO();
		augVO.setId(new Long(8));
		augVO.setName("August");
		SelectOptionVO sepVO = new SelectOptionVO();
		sepVO.setId(new Long(9));
		sepVO.setName("September");
		SelectOptionVO octVO = new SelectOptionVO();
		octVO.setId(new Long(10));
		octVO.setName("October");
		SelectOptionVO novVO = new SelectOptionVO();
		novVO.setId(new Long(11));
		novVO.setName("November");
		SelectOptionVO decVO = new SelectOptionVO();
		decVO.setId(new Long(12));
		decVO.setName("December");
		
		dobMonth.add(janVO);
		dobMonth.add(febVO);
		dobMonth.add(marVO);
		dobMonth.add(aprVO);
		dobMonth.add(mayVO);
		dobMonth.add(junVO);
		dobMonth.add(julVO);
		dobMonth.add(augVO);
		dobMonth.add(sepVO);
		dobMonth.add(octVO);
		dobMonth.add(novVO);
		dobMonth.add(decVO);
		parties = staticDataService.getStaticParties();
		session = request.getSession();
		session.setAttribute("type", type);
		session.setAttribute("userType", userType);
		session.setAttribute("gender", gender);
		session.setAttribute("parties", parties);
		session.setAttribute("dobDay",dobDay);
		session.setAttribute("dobYear",dobYear);
		session.setAttribute("dobMonth", dobMonth);
		
		return Action.SUCCESS;
	}
	
}
