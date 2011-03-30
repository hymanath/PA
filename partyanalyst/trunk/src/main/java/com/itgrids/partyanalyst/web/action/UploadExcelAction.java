package com.itgrids.partyanalyst.web.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.excel.upload.vo.UploadFormVo;
import com.itgrids.partyanalyst.service.IExcelToDBService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;


public class UploadExcelAction extends ActionSupport implements ServletResponseAware, ServletRequestAware, ServletContextAware {
	private static final long serialVersionUID = 1L;
	private final static Logger log = Logger.getLogger(UploadExcelAction.class);

    private HttpServletResponse response;
    private HttpServletRequest request; 

	private ServletContext context;
	private File inputFile; 
	private String inputFileContentType; 
	private String inputFileFileName;
	private String electionType;
	private String electionScope;
	private String district;
	private String country;
	private String electionYear;
	private UploadFormVo uploadFormVo;
	private String isElectionResults;
	IExcelToDBService excelToDBService;
    
    public String execute() throws JRException {
    	String contextPath = context.getRealPath("/");
		System.out.println("FilePath -->" +inputFile);
		System.out.println("fileName -->" + inputFileFileName);
		System.out.println("ContentType -->" + inputFileContentType);
		 try{
			 BeanUtils.populate(uploadFormVo , request.getParameterMap());
			 System.out.println("InputFileName ="+uploadFormVo.getInputFile());
			 System.out.println("electionType ="+uploadFormVo.getElectionType());
			 System.out.println("electionScope ="+uploadFormVo.getElectionScope());
			 System.out.println("electionYear ="+uploadFormVo.getElectionYear());
			 System.out.println("country "+uploadFormVo.getCountry());
			 System.out.println("district ="+uploadFormVo.getDistrict());
			 Boolean isResults = false;
			 if(isElectionResults.equalsIgnoreCase(IConstants.ELECTION_RESULTS))
				 isResults = true;
			 
			 excelToDBService.readCSVFileAndStoreIntoDB(uploadFormVo,inputFileFileName,inputFile,isResults);
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


	public String getIsElectionResults() {
		return isElectionResults;
	}


	public void setIsElectionResults(String isElectionResults) {
		this.isElectionResults = isElectionResults;
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

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}


	public String getInputFileContentType() {
		return inputFileContentType;
	}


	public void setInputFileContentType(String inputFileContentType) {
		this.inputFileContentType = inputFileContentType;
	}


	public String getInputFileFileName() {
		return inputFileFileName;
	}


	public void setInputFileFileName(String inputFileFileName) {
		this.inputFileFileName = inputFileFileName;
	}

	

}
