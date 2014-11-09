package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICadreRegAmountDetailsService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomGenaration;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegAmountUploadAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -4620729281316958397L;
	private static final Logger LOG = Logger.getLogger(CadreRegAmountUploadAction.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private File file;
	private String date;
	private ICadreRegAmountDetailsService cadreRegAmountDetailsService;
	DateUtilService dateUtilService = new DateUtilService();

	public ICadreRegAmountDetailsService getCadreRegAmountDetailsService() {
		return cadreRegAmountDetailsService;
	}

	public void setCadreRegAmountDetailsService(
			ICadreRegAmountDetailsService cadreRegAmountDetailsService) {
		this.cadreRegAmountDetailsService = cadreRegAmountDetailsService;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext arg0) {

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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String execute() {
		try{
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in execute() in CadreRegAmountUploadAction");
		}
		return Action.SUCCESS;
	}
	
	public String uploadFile()
	{
		try{
			session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			/*if(user == null)
				return ERROR;*/
			RandomGenaration random = new RandomGenaration();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date uploadedDate = sdf.parse(date);
			Date uploadedTime = dateUtilService.getCurrentDateAndTime();
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			String fileName = Integer.valueOf(random.randomGenerator(10)).toString()+".xls";
			String path = IWebConstants.STATIC_CONTENT_FOLDER_URL+IConstants.CADRE_REG_AMOUNT_FILES_FOLDER+pathSeperator+fileName;
			FileUtils.copyFile(file,new File(path));
			
			CadreRegAmountUploadVO uploadVO = new CadreRegAmountUploadVO();
			uploadVO.setUserId(1l);
			uploadVO.setFileName(fileName);
			uploadVO.setPath(path);
			uploadVO.setUploadedDate(uploadedDate);
			uploadVO.setUploadedTime(uploadedTime);
			
			ResultStatus resultStatus = cadreRegAmountDetailsService.uploadCadreRegAmountDetails(uploadVO);
			return Action.SUCCESS;
		}catch (Exception e) {
			LOG.error(e);
			return Action.ERROR;
		}
	}
}
