package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryNewsAction extends ActionSupport implements ServletRequestAware,ServletContextAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse responce;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private List<FileVO> galleriesDetails;
	private List<GallaryVO> gallaryVOLIst;
	private ICandidateDetailsService candidateDetailsService;
	private Long galleryId;
	
	
	
	public List<GallaryVO> getGallaryVOLIst() {
		return gallaryVOLIst;
	}

	public void setGallaryVOLIst(List<GallaryVO> gallaryVOLIst) {
		this.gallaryVOLIst = gallaryVOLIst;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public List<FileVO> getGalleriesDetails() {
		return galleriesDetails;
	}

	public void setGalleriesDetails(List<FileVO> galleriesDetails) {
		this.galleriesDetails = galleriesDetails;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public void setServletResponse(HttpServletResponse responce) {
		this.responce = responce;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Long getGalleryId() {
		return galleryId;
	}

	public void setGalleryId(Long galleryId) {
		this.galleryId = galleryId;
	}
	
	public String execute()
	{
        session = request.getSession();
        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
        if(user == null)
        return ERROR;
        
        //getSelectdGalleryDetails();
		return Action.SUCCESS;
	}
	
	
	

	public String getSelectdGalleryDetails()
	{
		try {
			session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
	        if(user == null)
	         return ERROR;
	       // jObj = new JSONObject(getTask());
	        Long galleryId = Long.parseLong(request.getParameter("gallaryId"));
	        int startIndex =  Integer.parseInt(request.getParameter("startIndex"));
	        int maxIndex =  Integer.parseInt(request.getParameter("endIndex"));
	        galleriesDetails = candidateDetailsService.getSelectedGallaryDetails(startIndex,maxIndex,galleryId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}
	
	public String getAllGalaroyesDetails()
	{
		try {
			session = request.getSession();
	        RegistrationVO user = (RegistrationVO)session.getAttribute("USER"); 
	        if(user == null)
	         return ERROR;
	        gallaryVOLIst = candidateDetailsService.getAllGalariyes();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Action.SUCCESS;
	}

}
