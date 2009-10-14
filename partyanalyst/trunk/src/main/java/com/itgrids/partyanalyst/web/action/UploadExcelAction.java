package com.itgrids.partyanalyst.web.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.service.IExcelToDBService;
import com.itgrids.partyanalyst.web.action.vo.UploadProcessFormVo;
import com.opensymphony.xwork2.ActionSupport;

public class UploadExcelAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(PartyPerformanceJasperAction.class);

    private HttpServletResponse response;
    private HttpServletRequest request; 

	private ServletContext context;
	//private IStaticDataService staticDataService;
	private String elecType;
	private String elecScope;
	private String elecYear;
	
	IExcelToDBService excelToDBService;
    
    public String execute() throws JRException {
		
		log.debug("excute started...");
		String contextPath = context.getRealPath("/");
		String csvFileName = request.getParameter("uploadFile");
		
		 String elecType  = request.getParameter("elecType");
		 String elecScope = request.getParameter("elecScope");
		 String elecYear = request.getParameter("elecYear");
		 String country = request.getParameter("country");
		 String district = request.getParameter("district");
		 try{
			 //String excelFilePath,String countryName,String stateName,String districtName, String typeOfElection, String electionYear
		excelToDBService.readCSVFileAndStoreIntoDB(csvFileName,country,elecScope,district,elecType,elecYear);
		 }catch(Exception exception){
			 exception.printStackTrace();
		 }
		
		return SUCCESS;
	 
    }
    
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	public void setExcelToDBService(IExcelToDBService excelToDBService) {
		this.excelToDBService = excelToDBService;
	}


	public IExcelToDBService getExcelToDBService() {
		return excelToDBService;
	}
	
	
	public void readAndValidate(HttpServletRequest httpServletRequest){
		Map requestParametersMap=httpServletRequest.getParameterMap();
		
		Field fields[]=UploadProcessFormVo.class.getDeclaredFields();
		
		for (Field field : fields) {
			String paravValue=(String)requestParametersMap.get(field.getName());
			
			Method methods[]=UploadProcessFormVo.class.getDeclaredMethods();
			for (Method method : methods) {
				
			}
			
		}
		
		
	}
}
