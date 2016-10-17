package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.IDataMonitoringService;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DataMonitoringAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(DataMonitoringAction.class);


         //instance variables
		  private HttpServletRequest request;
		  private JSONObject jObj;
		  private String task;
		  private List<IdAndNameVO> idAndNameVOList;
		
		//Attributes
		   private IDataMonitoringService dataMonitoringService ;
		   private IFieldMonitoringService fieldMonitoringService;
		
		 //implementation methods
		   public void setServletRequest(HttpServletRequest request) {
				this.request = request;
			}
		   
		//setters and getters.
		  
		   public JSONObject getjObj() {
			   return jObj;
		   }
		   public void setjObj(JSONObject jObj) {
				this.jObj = jObj;
		   }
		   public String getTask() {
				return task;
		   }
		   public void setTask(String task) {
			  this.task = task;
		   }
			
	
		public List<IdAndNameVO> getIdAndNameVOList() {
			return idAndNameVOList;
		}

		public void setIdAndNameVOList(List<IdAndNameVO> idAndNameVOList) {
			this.idAndNameVOList = idAndNameVOList;
		}


		public void setDataMonitoringService(IDataMonitoringService dataMonitoringService) {
			this.dataMonitoringService = dataMonitoringService;
		}

		public void setFieldMonitoringService(IFieldMonitoringService fieldMonitoringService) {
			this.fieldMonitoringService = fieldMonitoringService;
		}

		//Business methods
		public String execute(){
			return Action.SUCCESS;
		}
		
		public String getVendors(){
			
			try {
				jObj = new JSONObject(getTask());
				Long stateId = jObj.getLong("stateId");
				idAndNameVOList =fieldMonitoringService.getVendors(stateId);
			} catch (Exception e) {
				LOG.error("Exception raised at getVendors()  of DataMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
		
		public String getVendorDistricts(){
			
			try {
				jObj = new JSONObject(getTask());
				Long stateId = jObj.getLong("stateId");
				Long vendorId = jObj.getLong("vendorId");
				idAndNameVOList =fieldMonitoringService.getVendorDistricts(stateId,vendorId);
			} catch (Exception e) {
				LOG.error("Exception raised at getVendorDistricts()  of DataMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
		
		public String getVendorConstituencies(){
			
			try {
				jObj = new JSONObject(getTask());
				Long vendorId = jObj.getLong("vendorId");
				Long districtId = jObj.getLong("districtId");
				idAndNameVOList =fieldMonitoringService.getVendorConstituencies(vendorId,districtId);
			} catch (Exception e) {
				LOG.error("Exception raised at getVendorConstituencies()  of DataMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
}
