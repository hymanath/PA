package com.itgrids.electoralconnect.web.action;

import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.ResultStatus;
import com.itgrids.electoralconnect.service.IAnnouncementService;
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
	private ResultStatus resultStatus;
	
	private String title;
	private String description;
	private Long announcementType;
	private Date date;
	private String fileName;
	private String fileDescription;
	private File docs;
	private String docsContentType;
	private String docsFileName;
	private Long type;
	private IAnnouncementService announcementService;
	private String fromForm;
	private String resultString;
	 
	
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
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public IAnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(IAnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	public String getFromForm() {
		return fromForm;
	}

	public void setFromForm(String fromForm) {
		this.fromForm = fromForm;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public String execute()
	{
		String filePath=null;
		String fileNames=null;
		String subFolder=null;
		
		try{
		if(announcementType==1){
			subFolder="Notifications";
		}
		else{
			subFolder="Press Notes";
		}
		
		AnnouncementVO announcementVO=new AnnouncementVO();
	
		HttpSession session=request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		
		 announcementVO.setName(title);
		 announcementVO.setDescription(description);
		 announcementVO.setUpdatedDate(date);
		 announcementVO.setAnnouncementType(announcementType);
		
		 if(docsFileName!=null){
		String requestURL= request.getRequestURL().toString();
		System.out.println(requestURL);
		
		String requestFrom = "";
		if(requestURL.contains(IConstants.SERVERLINK))
			requestFrom = IConstants.SERVER;
		else
			requestFrom = IConstants.LOCALHOST;
		
		
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		
		 if(request.getRequestURL().toString().contains(IConstants.SERVERLINK)){
			filePath = IConstants.STATIC_CONTENT_FOLDER_URL + "Uploaded_Documents" + pathSeperator;
		 }
		 else if(request.getRequestURL().toString().contains(IConstants.LOCALHOST))
		 {
			 filePath = context.getRealPath("/")+"Uploaded_Documents\\"; 
		 }
		
        filePath=filePath+subFolder+pathSeperator;
        System.out.println(filePath);
        
        String fileType = null;
		Long systime = System.currentTimeMillis();
		Random random = new Random();
		
		String[] str ;
		str = docsContentType.split(",");
		if(str !=null)
		{
			for(int i=0;i<str.length;i++)
			{
			fileType = str[i].substring(str[i].indexOf("/")+1,str[i].length());
			if(fileType.equalsIgnoreCase("pdf")){
				fileType="pdf";
			}
			else if(fileType.equalsIgnoreCase("msword")){
				fileType="doc";
			}
			else{
				fileType="docx";
			}
			fileNames = systime.toString()+random.nextInt(IConstants.FILE_RANDOM_NO)+"."+fileType;
			}
		}
        
        
        
       
		announcementVO.setFileTitle(title);
		announcementVO.setFileDescription(fileDescription);
		announcementVO.setFileName(docsFileName);
		
		
		if(announcementType!= null)
		{
			String path ;
			if(fileNames!=null||fileNames!="")
			{
				path = IConstants.UPLOADED_DOCS+"/"+subFolder+"/"+fileNames;
				announcementVO.setFilePath(path);
			}
		}
		
		
		File fileToCreate = new File(filePath, fileNames);
		FileUtils.copyFile(docs, fileToCreate);
		}
		 
		resultStatus=announcementService.uploadFile(announcementVO,user);
		if(resultStatus.getResultCode()==0){
			resultString="SUCCESS";
		}
		else{
			resultString="FAILURE";
		}
		}catch (Exception e) {
			System.out.println(e);
			resultString="FAILURE";
		} 
        
		return Action.SUCCESS;
	}

	
}
