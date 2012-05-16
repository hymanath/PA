package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialPageAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = -957791701984246754L;
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ResultStatus result;
	private HttpSession session;
	private List<SpecialPageVO> specialPageList;
	private JSONObject jObj;
	private String task = null;
	private Long specialPageId;
	private List<String> descriptions;
	private SpecialPageVO specialPageVO;
	private List<FileVO> fileVOList;
	private ISpecialPageService specialPageService;
	private List<CustomPageVO> customPages;
	private MetaInfoVO metaInfoVO;
	private Long contentId;
	private String isAdmin;
	
	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public MetaInfoVO getMetaInfoVO() {
		return metaInfoVO;
	}

	public void setMetaInfoVO(MetaInfoVO metaInfoVO) {
		this.metaInfoVO = metaInfoVO;
	}

	public List<CustomPageVO> getCustomPages() {
		return customPages;
	}

	public void setCustomPages(List<CustomPageVO> customPages) {
		this.customPages = customPages;
	}

	public void setFileVOList(List<FileVO> fileVOList) {
		this.fileVOList = fileVOList;
	}

	public List<FileVO> getFileVOList() {
		return fileVOList;
	}

	public void setSpecialPageVO(SpecialPageVO specialPageVO) {
		this.specialPageVO = specialPageVO;
	}

	public SpecialPageVO getSpecialPageVO() {
		return specialPageVO;
	}

	public Long getSpecialPageId() {
		return specialPageId;
	}

	public void setSpecialPageId(Long specialPageId) {
		this.specialPageId = specialPageId;
	}
	
	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public List<SpecialPageVO> getSpecialPageList() {
		return specialPageList;
	}

	public void setSpecialPageList(List<SpecialPageVO> specialPageList) {
		this.specialPageList = specialPageList;
	}

	public ISpecialPageService getSpecialPageService() {
		return specialPageService;
	}

	public void setSpecialPageService(ISpecialPageService specialPageService) {
		this.specialPageService = specialPageService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String execute() {
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		
		if(regVO != null)
			isAdmin = regVO.getIsAdmin();
		
		descriptions  = specialPageService.getSpecialPageDescription(specialPageId);
		specialPageVO = specialPageService.getSpecialPageBasicDetails(specialPageId);
		fileVOList    = specialPageService.getVideoGalleryBasedOnSpecialPageId(specialPageId, 0, 20);
		customPages   = specialPageService.getCustomPagesOfASpecialPage(specialPageId);
		metaInfoVO    = specialPageService.getMetaInfoForASpecialPage(specialPageId);
	return SUCCESS;
	}

	public String setEmailAlertsForEvent() {
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session
				.getAttribute("USER");*/
		String email = jObj.getString("emailId");

		result = specialPageService.subScribeEmailAlertForAEvent(email, jObj
				.getLong("specialPageId"));
		return Action.SUCCESS;
	}
	
	
	public String ajaxCallHandlerForSpecialPage(){
		
		try {
			jObj = new JSONObject(getTask());
			
			if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplay"))
			{
				fileVOList = specialPageService.getNewsGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),jObj.getString("queryType"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getNewsToDisplayForPagination"))
			{
				fileVOList = specialPageService.getNewsGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),Integer.parseInt(request.getParameter("startIndex")),Integer.parseInt(request.getParameter("resultsCount")),jObj.getString("queryType"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoGallaryDetail"))
			{
				fileVOList = specialPageService.getPhotoGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"));
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPhotoGallaryWithOutGallerySizeZero"))
			{
				fileVOList = specialPageService.getSpecialPageGallaryDetailWithOutGallerySizeZero(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.PHOTO_GALLARY);
			}
			else if(jObj.getString("task").equalsIgnoreCase("videoGalleriesForASpecialPage")){
				
				fileVOList = specialPageService.getVideoGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"));
			}
	        else if(jObj.getString("task").equalsIgnoreCase("getSpecialPageGallaryDetail")){
				
	        	fileVOList = specialPageService.getSpecialPageGallaryDetail(jObj.getLong("specialPageId"),jObj.getInt("startRecord"),jObj.getInt("maxRecord"),IConstants.VIDEO_GALLARY);
			}
	        else if(jObj.getString("task").equalsIgnoreCase("getFirstThreePhotoRecords"))
			{
	        	fileVOList = specialPageService.getFirstThreePhotoGallaryRecords(jObj.getLong("specialPageId"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
		
	}

	public String getLatestVideosForSpecialPage(){
		
		fileVOList = specialPageService.getVideoGalleryBasedOnSpecialPageId(jObj.getLong("specialPageId"),jObj.getInt("startIndex"),jObj.getInt("maxRecords"));
		
		return Action.SUCCESS;
	}
}
