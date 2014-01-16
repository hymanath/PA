package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CandidatePartyDestinationVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsEditVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.INewsMonitoringService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EditNewsAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware,ServletContextAware,ModelDriven<NewsEditVO>, Preparable   {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8907488490865391523L;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
    private ServletContext context;
    private NewsEditVO news;
    private Long fileId;
    private List<FileVO> fileList;
    private INewsMonitoringService newsMonitoringService;
	private List<SelectOptionVO> districtsList,parlConstiList,assemConstiList,keywordsList;
	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService;
	private ICandidateDetailsService candidateDetailsService;
	
	private List<SelectOptionVO> districtsList1;
	private List<SelectOptionVO> parlConstiList1;
	private List<SelectOptionVO> assemConstiList1;
	private List<AddressVO> userDetailsVO;
	
	
	public List<AddressVO> getUserDetailsVO() {
		return userDetailsVO;
	}

	public void setUserDetailsVO(List<AddressVO> userDetailsVO) {
		this.userDetailsVO = userDetailsVO;
	}

	public List<SelectOptionVO> getDistrictsList1() {
		return districtsList1;
	}

	public void setDistrictsList1(List<SelectOptionVO> districtsList1) {
		this.districtsList1 = districtsList1;
	}

	public List<SelectOptionVO> getParlConstiList1() {
		return parlConstiList1;
	}

	public void setParlConstiList1(List<SelectOptionVO> parlConstiList1) {
		this.parlConstiList1 = parlConstiList1;
	}

	public List<SelectOptionVO> getAssemConstiList1() {
		return assemConstiList1;
	}

	public void setAssemConstiList1(List<SelectOptionVO> assemConstiList1) {
		this.assemConstiList1 = assemConstiList1;
	}

	public List<SelectOptionVO> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<SelectOptionVO> keywordsList) {
		this.keywordsList = keywordsList;
	}

	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}

	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}

	public List<SelectOptionVO> getParlConstiList() {
		return parlConstiList;
	}

	public void setParlConstiList(List<SelectOptionVO> parlConstiList) {
		this.parlConstiList = parlConstiList;
	}

	public List<SelectOptionVO> getAssemConstiList() {
		return assemConstiList;
	}

	public void setAssemConstiList(List<SelectOptionVO> assemConstiList) {
		this.assemConstiList = assemConstiList;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	 public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public List<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(List<FileVO> fileList) {
		this.fileList = fileList;
	}
	public NewsEditVO getNews() {
		return news;
	}
	public void setNews(NewsEditVO news) {
		this.news = news;
	}
	public INewsMonitoringService getNewsMonitoringService() {
		return newsMonitoringService;
	}
	public void setNewsMonitoringService(
			INewsMonitoringService newsMonitoringService) {
		this.newsMonitoringService = newsMonitoringService;
	}
	public NewsEditVO getModel() {
	        return news;
	    }
	 public void prepare() throws Exception {
		 	session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
	  
	        news = newsMonitoringService.getInfoForFile(user.getUserAccessType(),new Long(request.getParameter("fileId")),user.getRegistrationID());
		}   
	 public String execute(){
		 session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(news != null){
			//candidateDetailsService.testgetCandidatesListByPartyIdsList();
			if (registrationVO != null) 
			{
			// if("Admin".equalsIgnoreCase(registrationVO.getUserType()) || "subuser".equalsIgnoreCase(registrationVO.getUserType())  )
				 keywordsList = candidateDetailsService.getTotalKeyWords();
				 if("Admin".equalsIgnoreCase(registrationVO.getUserAccessType()) || "subuser".equalsIgnoreCase(registrationVO.getUserAccessType())  )
				 {
					 
					if("Admin".equalsIgnoreCase(registrationVO.getUserAccessType()) || registrationVO.getAccessType().equalsIgnoreCase("STATE")){
						 ConstituencyInfoVO constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,1L);
						 ConstituencyInfoVO parliamantConstis = staticDataService.getConstituenciesByElectionTypeAndStateId(1L,1L);
						 districtsList =  staticDataService.getDistricts(1l);
						 parlConstiList = parliamantConstis.getConstituencies();
						 assemConstiList = constituencyInfoVO.getConstituencies();
					 }else{
						 List<Object> assessLocs = null;
						 if(registrationVO.getAccessType().equalsIgnoreCase("DISTRICT")){
							 assessLocs = regionServiceDataImp.getAllAccessLocByDistrict(Long.valueOf(registrationVO.getAccessValue().trim()));	 
						 }else if(registrationVO.getAccessType().equalsIgnoreCase("MLA")){
							 assessLocs =  regionServiceDataImp.getAllAccessLocByAssConsti(Long.valueOf(registrationVO.getAccessValue().trim()));
						 }else if(registrationVO.getAccessType().equalsIgnoreCase("MP")){
							 assessLocs = regionServiceDataImp.getAllAccessLocByParlConsti(Long.valueOf(registrationVO.getAccessValue().trim()));
						 }
						 if(assessLocs != null && assessLocs.size() == 3){
							 districtsList =  (List<SelectOptionVO>)assessLocs.get(0);
							 parlConstiList = (List<SelectOptionVO>)assessLocs.get(1);
							 assemConstiList =(List<SelectOptionVO>)assessLocs.get(2);
						 }
					 }
					
					 ConstituencyInfoVO constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,1L);
					 ConstituencyInfoVO parliamantConstis = staticDataService.getConstituenciesByElectionTypeAndStateId(1L,1L);
					 districtsList1 =  staticDataService.getDistricts(1l);
					 parlConstiList1 = parliamantConstis.getConstituencies();
				     assemConstiList1 = constituencyInfoVO.getConstituencies();
				     
				     userDetailsVO = new ArrayList<AddressVO>();
				     userDetailsVO =  staticDataService.getUserLocationScopeDetilsByUserid(registrationVO.getRegistrationID(),registrationVO.getAccessType(),registrationVO.getAccessValue());
					
			//  return Action.SUCCESS;
				 }
			 else
				 return "error";
			 }
			}else{
				 return "error";
			}
			return Action.SUCCESS;
	 }
	 public String editUploadedFilesForPartyAndCandidatesKeywords()
		{
		try {
			 session = request.getSession();
			  RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			  if(regVO == null)
				  return Action.ERROR;
			  NewsEditVO editFileVO = new NewsEditVO();
			  FileVO fileVO = new FileVO();
			  
			  if(news.getFileTitle().length() != request.getParameter("news.fileTitle").length()){
				  fileVO.setTitle(request.getParameter("news.fileTitle").toString() !=null?escapeUnicode(StringEscapeUtils.escapeJava(request.getParameter("news.fileTitle"))):null);
				  
				  
			  }
			  
			  String existingFileDate = news.getDay()+"/"+news.getMonth()+"/"+news.getYear()+"";
			  String newfileDate = request.getParameter("fileDate");			  
			  
			  if(!existingFileDate.equalsIgnoreCase(newfileDate))
				  fileVO.setFileDate(newfileDate);			  
			 
			  Long newNewsImp = Long.valueOf(request.getParameter("newsimportance").toString());
			  if(newNewsImp != news.getNewsimportance())
				 fileVO.setNewsImportanceId(newNewsImp);
			  
			  if(request.getParameter("titleCheckBox") != null){
				  fileVO.setEenadu(true);
			  }
			  String newsDescription = request.getParameter("fileDescription");
			  
			  if(news.getSourceVOList() != null && news.getSelectOptionVOList().size() >0 ){				 
				  	for (CandidatePartyDestinationVO param :news.getSourceVOList()) {						
						for (CandidatePartyDestinationVO destiParam : param.getDestinationVOList()) {
							
						}
					}
			  }
			  
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(" Exception Occured in editUploadedFilesForPartyAndCandidatesKeywords() method, Exception - "+e);
		}
		return Action.SUCCESS;
		}
	 public String escapeUnicode(String input) {
		  StringBuilder b = new StringBuilder(input.length());
		  Formatter f = new Formatter(b);
		  for (char c : input.toCharArray()) {
		    if (c < 128) {
		      b.append(c);
		    } else {
		      f.format("\\u%04x", (int) c);
		    }
		  }
		  return b.toString();
		}
}
	