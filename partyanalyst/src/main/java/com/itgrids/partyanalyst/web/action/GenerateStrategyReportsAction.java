package com.itgrids.partyanalyst.web.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IStratagicReportsServicePdf;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GenerateStrategyReportsAction  extends ActionSupport implements ServletRequestAware{
	private static final Logger LOG = Logger.getLogger(GenerateStrategyReportsAction.class);
	public String constituencyIds;
	public Long constituencyId;
	public String status;
	private HttpServletRequest request;
	
	@Autowired 
	private IStratagicReportsServicePdf stratagicReportsServicePdf;
	
	
	@Autowired 
	private EntitlementsHelper entitlementsHelper;
	
	public String getConstituencyIds() {
		return constituencyIds;
	}

	public void setConstituencyIds(String constituencyIds) {
		this.constituencyIds = constituencyIds;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String execute(){
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
		String[] constituenciesArray = constituencyIds.split(",");
		for(String consti:constituenciesArray){
			try{
				LOG.info("Started Auto Strategy Report For Constituency With Id : "+consti);
		            stratagicReportsServicePdf.buildAutoStrategy(Long.valueOf(consti.trim()));
		         LOG.info("Completed Auto Strategy Report For Constituency With Id : "+consti);
			}catch(Exception e){
				LOG.error("Exception rised in Auto Strategy Report For Constituency With Id : "+consti+" ",e);
			}
		}
		return Action.SUCCESS;
	}
	
	public String getStrategyReportPath(){
		 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		 String url = "DynamicReports/pdfs/";
		 boolean fileFound = false;
		 Long constiNo = stratagicReportsServicePdf.getConstituencyNo(constituencyId);
		String path = IWebConstants.STATIC_CONTENT_FOLDER_URL+"DynamicReports"+pathSeperator+"pdfs";
		File directory = new File(path);
		String[] fileNames = directory.list();
		if(fileNames!= null && fileNames.length > 0){
			for(String fileName:fileNames){
				String[] splitedArray = fileName.split("_");
				if(splitedArray != null && splitedArray.length > 1){
					if(splitedArray[0].equalsIgnoreCase(constiNo.toString())){
						url = url+fileName;
						status = url;
						fileFound = true;
						break;
					}
				}
			}
		}
		if(!fileFound){
			status="no";
		}
		return Action.SUCCESS;
	}
	
	public String getPrpEffect(){
		try{
		status = stratagicReportsServicePdf.getPrpEffect(constituencyId);
		}catch(Exception e){
			LOG.error(e);
		}
		return Action.SUCCESS;
	}
}
