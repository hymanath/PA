package com.itgrids.partyanalyst.web.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.excel.upload.vo.UploadFormVo;
import com.itgrids.partyanalyst.service.IExcelToDBService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

public class UploadExcelAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1L;
	private final static Log log = LogFactory.getLog(PartyPerformanceJasperAction.class);

    private HttpServletResponse response;
    private HttpServletRequest request; 

	private ServletContext context;
	//private IStaticDataService staticDataService;
	private String electionType;
	private String electionScope;
	private String inputFile;
	private String district;
	private String country;
	private String electionYear;
	private UploadFormVo uploadFormVo;
	IExcelToDBService excelToDBService;
    
    public String execute() throws JRException {
    	//UploadFormVo uploadFormVo = new UploadFormVo();
		String contextPath = context.getRealPath("/");
		 try{
			 BeanUtils.populate(uploadFormVo , request.getParameterMap());
			 System.out.println("inputFile ="+uploadFormVo.getInputFile());
			 System.out.println("electionType ="+uploadFormVo.getElectionType());
			 System.out.println("electionScope ="+uploadFormVo.getElectionScope());
			 System.out.println("electionYear ="+uploadFormVo.getElectionYear());
			 System.out.println("country "+uploadFormVo.getCountry());
			 System.out.println("district ="+uploadFormVo.getDistrict());
			 excelToDBService.readCSVFileAndStoreIntoDB(uploadFormVo);
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

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getElectionType() {
		return electionType;
	}


	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getElectionScope() {
		return electionScope;
	}


	public void setElectionScope(String electionScope) {
		this.electionScope = electionScope;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getInputFile() {
		return inputFile;
	}


	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getDistrict() {
		return district;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public void setDistrict(String district) {
		this.district = district;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public String getElectionYear() {
		return electionYear;
	}

	@RequiredStringValidator(key="requiredstring" ,shortCircuit=true)
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}


	public IExcelToDBService getExcelToDBService() {
		return excelToDBService;
	}


	public void setExcelToDBService(IExcelToDBService excelToDBService) {
		this.excelToDBService = excelToDBService;
	}


	public UploadFormVo getUploadFormVo() {
		return uploadFormVo;
	}


	public void setUploadFormVo(UploadFormVo uploadFormVo) {
		this.uploadFormVo = uploadFormVo;
	}
}
