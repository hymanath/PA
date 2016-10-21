package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DataMonitoringOverviewVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
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
		  private IdNameVO idNameVO;
		  private List<IdNameVO> idNameVOs;
		  private List<List<IdNameVO>> listofIdNameVOs;  
		  private DataMonitoringOverviewVO resultVO;
		  private List<DataMonitoringOverviewVO> resultList;
		  private ResultStatus resultStatus;
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
	            
		   public DataMonitoringOverviewVO getResultVO() {
				return resultVO;
			}

			public void setResultVO(DataMonitoringOverviewVO resultVO) {
				this.resultVO = resultVO;
			}

			public IdNameVO getIdNameVO() {
				return idNameVO;
			}
	
			public void setIdNameVO(IdNameVO idNameVO) {
				this.idNameVO = idNameVO;
			}
		   public List<DataMonitoringOverviewVO> getResultList() {
				return resultList;
			}

			public void setResultList(List<DataMonitoringOverviewVO> resultList) {
				this.resultList = resultList;
			}
		public List<IdNameVO> getIdNameVOs() {
			return idNameVOs;
		}

		public void setIdNameVOs(List<IdNameVO> idNameVOs) {
			this.idNameVOs = idNameVOs;
		}
		
		public List<List<IdNameVO>> getListofIdNameVOs() {
			return listofIdNameVOs;
		}

		public void setListofIdNameVOs(List<List<IdNameVO>> listofIdNameVOs) {
			this.listofIdNameVOs = listofIdNameVOs;
		}
		public void setResultStatus(ResultStatus resultStatus) {
			this.resultStatus = resultStatus;
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
	    public String dataMonitoringDashboard(){
	     return Action.SUCCESS;
	    }
	    
	   public String getDataMonitoringOverViewDetails(){
	        try{
	        	jObj = new JSONObject(getTask());
	          String fromDate = jObj.getString("fromDate");
	          String toDate =jObj.getString("toDate");
	          resultVO = dataMonitoringService.getDataMonitoringOverViewDetails(fromDate,toDate);
	        }catch(Exception e){
	          LOG.error("Exception Occured into  DataMonitoringAction of getDataMonitoringOverViewDetails",e);
	        }
	        return Action.SUCCESS;
	      }
	   
	   public String getTotalRegCdrVendorWise(){
			
			try {
				jObj = new JSONObject(getTask());
				Long stateId = jObj.getLong("stateId");  
				Long vendorId = jObj.getLong("vendorId");
				Long districtId = jObj.getLong("districtId");
				Long constituencyId = jObj.getLong("constituencyId");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				idNameVO = dataMonitoringService.getTotalRegCdrVendorWise(stateId, vendorId, districtId, constituencyId, startDate, endDate);
			} catch (Exception e) {  
				LOG.error("Exception raised at getVendorConstituencies()  of DataMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
	   public String getTotalRegCdrVendorAndTabUserWise(){
			
			try {
				jObj = new JSONObject(getTask());
				Long stateId = jObj.getLong("stateId");  
				Long vendorId = jObj.getLong("vendorId"); 
				Long districtId = jObj.getLong("districtId");
				Long constituencyId = jObj.getLong("constituencyId");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				idNameVOs = dataMonitoringService.getTotalRegCdrVendorAndTabUserWise(stateId, vendorId, districtId, constituencyId, startDate, endDate);
			} catch (Exception e) {  
				LOG.error("Exception raised at getVendorConstituencies()  of DataMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}  
		public String getVerifiedDtls(){
			
			try {
				jObj = new JSONObject(getTask());
				Long surveyUserId = jObj.getLong("surveyUserId");  
				Long tabUserId = jObj.getLong("tabUserId"); 
				Long webUserId = jObj.getLong("webUserId");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				Integer minValue = jObj.getInt("minValue");
				Integer maxValue = jObj.getInt("maxValue");
				String resultType = jObj.getString("resultType");
				String verificationStatus = jObj.getString("verificationStatus");
				listofIdNameVOs = dataMonitoringService.getVerifiedDtls(surveyUserId, tabUserId, webUserId, startDate, endDate,minValue,maxValue,resultType,verificationStatus);  
			} catch (Exception e) {  
				LOG.error("Exception raised at getVendorConstituencies()  of DataMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
	   public String getRegistrationDetailsUserWise(){
		      try {
		        jObj = new JSONObject(getTask());
		        String dataSourceType = jObj.getString("dataSourceType");  
		        String verificationStatus = jObj.getString("verificationStatus");
		        String  fromDate = jObj.getString("fromDate");
		        String toDate = jObj.getString("toDate");
		        resultList = dataMonitoringService.getRegistrationDetailsUserWise(fromDate, toDate,dataSourceType,verificationStatus);
		      } catch (Exception e) {  
		        LOG.error("Exception raised at getRegistrationDetailsUserWise()  of DataMonitoringAction", e);
		      }
		        return Action.SUCCESS;
		    }
	   public String getDataRejectReason(){  
		   try{
			   idNameVOs = dataMonitoringService.getDataRejectReason();
		   }catch(Exception e){
			   e.printStackTrace();
			   LOG.error("Exception raised at getDataRejectReason()  of DataMonitoringAction", e);
		   }
		   return Action.SUCCESS;
	   }
	   public String updateRejectList(){    
		   try{
			   Long cadreId = 0l;
			   Long reasonId = 0l;
			   Long userId = 0l;
			   IdNameVO idNameVO = new IdNameVO();
			   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			   jObj = new JSONObject(getTask());
			   JSONArray jsonArray = (JSONArray) jObj.get("data");
			   for(int i = 0 ; i < jsonArray.length() ; i++){
				   idNameVO = new IdNameVO();
				   jObj = jsonArray.getJSONObject(i);
				   cadreId = jObj.getLong("cadreId");
				   reasonId = jObj.getLong("reasonId");
				   userId = jObj.getLong("userId");
				   idNameVO.setCadreId(cadreId);
				   idNameVO.setRejectedCount(reasonId);
				   idNameVO.setId(userId);
				   idNameVOs.add(idNameVO);
			   }
			   resultStatus = dataMonitoringService.updateRejectDtls(idNameVOs);    
		   }catch(Exception e){
			   e.printStackTrace();
			   LOG.error("Exception raised at getDataRejectReason()  of DataMonitoringAction", e);
		   }
		   return Action.SUCCESS;
	   }
	   public String updateApproveList(){    
		   try{
			   Long cadreId = 0l;
			   Long userId = 0l;
			   IdNameVO idNameVO = new IdNameVO();
			   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			   jObj = new JSONObject(getTask());
			   JSONArray jsonArray = (JSONArray) jObj.get("data");
			   for(int i = 0 ; i < jsonArray.length() ; i++){
				   idNameVO = new IdNameVO();
				   jObj = jsonArray.getJSONObject(i);
				   cadreId = jObj.getLong("cadreId");
				   userId = jObj.getLong("userId");
				   idNameVO.setCadreId(cadreId);
				   idNameVO.setId(userId);
				   idNameVOs.add(idNameVO);  
			   }
			   resultStatus = dataMonitoringService.updateApproveDtls(idNameVOs);    
		   }catch(Exception e){
			   e.printStackTrace();  
			   LOG.error("Exception raised at updateApproveList()  of DataMonitoringAction", e);
		   }
		   return Action.SUCCESS;
	   }
}
