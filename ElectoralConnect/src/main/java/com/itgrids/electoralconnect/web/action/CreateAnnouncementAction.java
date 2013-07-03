package com.itgrids.electoralconnect.web.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.util.IConstants;

 
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CreateAnnouncementAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	private static final Logger LOG = Logger.getLogger(CreateAnnouncementAction.class);
	private String task;
	private JSONObject jobj;
	private HttpServletRequest request;
	private ServletContext context;
	
	private String title;
	private String description;
	private Long announcementType;
	private Date date;
	private String fileName;
	private String fileDescription;
	private File docs;
	private String docsContentType;
	private String docsFileName;
	 
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	
	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAnnouncementType() {
		return announcementType;
	}

	public void setAnnouncementType(Long announcementType) {
		this.announcementType = announcementType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	

	public File getDocs() {
		return docs;
	}

	public void setDocs(File docs) {
		this.docs = docs;
	}
	
	public String getDocsContentType() {
		return docsContentType;
	}

	public void setDocsContentType(String docsContentType) {
		this.docsContentType = docsContentType;
	}

	public String getDocsFileName() {
		return docsFileName;
	}

	public void setDocsFileName(String docsFileName) {
		this.docsFileName = docsFileName;
	}

	public String execute()
	{
		String filePath=null;
		System.out.println(context.getContextPath());
		System.out.println(context.getRealPath("/"));
		AnnouncementVO announcementVO=new AnnouncementVO();
		announcementVO.setName(title);
		announcementVO.setDescription(description);
		announcementVO.setUpdatedDate(date);
		announcementVO.setAnnouncementType(announcementType);
		
		System.out.println(docs);
		System.out.println(docsContentType);
		System.out.println(docsFileName);
				
		HttpSession session=request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		
		
		String requestURL= request.getRequestURL().toString();
		System.out.println(requestURL);
		
		String requestFrom = "";
		if(requestURL.contains(IConstants.SERVERLINK))
			requestFrom = IConstants.SERVER;
		else
			requestFrom = IConstants.LOCALHOST;
		
		
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		 if(request.getRequestURL().toString().contains(IConstants.SERVERLINK)){
			filePath = IConstants.STATIC_CONTENT_FOLDER_URL + "Uploaded Files" + pathSeperator;
		 }
		 else if(request.getRequestURL().toString().contains(IConstants.LOCALHOST))
		 {
			 filePath = context.getRealPath("/")+"Uploaded Files\\"; 
		 }
		
        System.out.println(filePath);
        
        if(announcementType==1){
        	filePath=filePath+"Notification"+pathSeperator;
        }
        else{
        	filePath=filePath+"Press Notes"+pathSeperator;
        }
        
        System.out.println(filePath);
		
		return Action.SUCCESS;
	}

	
}
