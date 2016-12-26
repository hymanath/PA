package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreRegUserVO;
import com.itgrids.partyanalyst.dto.DataMonitoringOverviewVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
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
		  private HttpSession session;
		  private List<IdAndNameVO> idAndNameVOList;
		  private IdNameVO idNameVO;
		  private List<IdNameVO> idNameVOs;
		  private List<List<IdNameVO>> listofIdNameVOs;  
		  private DataMonitoringOverviewVO resultVO;
		  private List<DataMonitoringOverviewVO> resultList;
		  private ResultStatus resultStatus;
		  private List<CadreRegUserVO> cadreRegUserList;
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
		  
		public HttpSession getSession() {
			return session;
		}

		public void setSession(HttpSession session) {
			this.session = session;
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
		public ResultStatus getResultStatus() {
			return resultStatus;
		}
		public List<CadreRegUserVO> getCadreRegUserList() {
			return cadreRegUserList;
		}
		public void setCadreRegUserList(List<CadreRegUserVO> cadreRegUserList) {
			this.cadreRegUserList = cadreRegUserList;
		}

		
		//Business methods
		public String execute(){
			
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<String> entitlements = null;
			boolean noaccess = false;
			
			if(regVO == null)
				return Action.INPUT;
			
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(!(entitlements.contains("CADRE_DATA_MONITORING".trim())))
				 {
				        noaccess = true ;  
				 }
			}
			return Action.SUCCESS;
		}
		
		public String getUserAssignedConstituencies(){
			
			try {
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				List<String> entitlements = null;
				Long userId = regVO.getRegistrationID();
				jObj = new JSONObject(getTask());
				Long districtId = jObj.getLong("districtId");
				String userType = null;
				if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
				{
					entitlements = regVO.getEntitlements();
					 if(entitlements.contains("CADRE_DATA_MONITORING_DASHBOARD".trim()) || entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim()))
					 {
					       userType = "dashboard";
					 }
				}
				cadreRegUserList = dataMonitoringService.getCadreRegUserAssignedConstituencies(userId,userType,districtId);
			} catch (Exception e) {
				LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
		
		public String getCadreRegUserAssignedDistricts(){
			
			try {
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				List<String> entitlements = null;
				Long userId = regVO.getRegistrationID();
				jObj = new JSONObject(getTask());
				//Long districtId = jObj.getLong("districtId");
				String userType = null;
				if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
				{
					entitlements = regVO.getEntitlements();
					 if(entitlements.contains("CADRE_DATA_MONITORING_DASHBOARD".trim()) || entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim()))
					 {
					       userType = "dashboard";
					 }
				}
				
				cadreRegUserList = dataMonitoringService.getCadreRegUserAssignedDistricts(userId,userType);
			} catch (Exception e) {
				LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
			}
		
		    return Action.SUCCESS;
		}
		
		public String getUserAssignedUsers(){
			
			try {
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				List<String> entitlements = null;
				Long userId = regVO.getRegistrationID();
				jObj = new JSONObject(getTask());
				/*JSONArray constArr = jObj.getJSONArray("constituencyId");  
				List<Long> constIds = new ArrayList<Long>();
				for( int i=0;i<constArr.length();i++){
					constIds.add(Long.valueOf(constArr.getString(i)));
				}*/
				Long constituencyId = jObj.getLong("constituencyId");
				String userType = null;
				if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
				{
					entitlements = regVO.getEntitlements();
					 if(entitlements.contains("CADRE_DATA_MONITORING_DASHBOARD".trim()) || entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD".trim()))
					 {
					       userType = "dashboard";
					 }
				}
				cadreRegUserList = dataMonitoringService.getCadreRegUserAssignedUsers(userId,constituencyId,userType);
			} catch (Exception e) {
				LOG.error("Exception raised at getVendors()  of FieldMonitoringAction", e);
			}
		
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
		
	    public String dataMonitoringDashboard()
	    {
	    	session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<String> entitlements = null;
			boolean noaccess = false;
			
			if(regVO == null)
				return Action.INPUT;
			
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(!(entitlements.contains("CADRE_DATA_MONITORING_DASHBOARD".trim())))
				 {
				        noaccess = true ;  
				 }
			}
			return Action.SUCCESS;
	    }
	    
	    public String dataMonitoringDashboardNew(){
	    	session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			List<String> entitlements = null;
			boolean noaccess = false;
			
			if(regVO == null)
				return Action.INPUT;
			
			if(regVO != null && regVO.getEntitlements() != null && regVO.getEntitlements().size()>0)
			{
				entitlements = regVO.getEntitlements();
				 if(!(entitlements.contains("CADRE_DATA_MONITORING_DASHBOARD".trim())))
				 {
				        noaccess = true ;  
				 }
			}
	    	return Action.SUCCESS;
	    }
	    
	   public String getDataMonitoringOverViewDetails(){
	        try{
	        	jObj = new JSONObject(getTask());
	          String fromDate = jObj.getString("fromDate");
	          String toDate =jObj.getString("toDate");
	          Long stateId = jObj.getLong("stateId");
	          resultVO = dataMonitoringService.getDataMonitoringOverViewDetails(fromDate,toDate,stateId);
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
	   
	   public String getTotalRegCdrVendorWiseNew(){
			
			try {
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				
				Long loginUserId = 0l;
				if(regVO != null)
					loginUserId = regVO.getRegistrationID();
				jObj = new JSONObject(getTask());
				
				Long userId = jObj.getLong("userId");
				Long constituencyId = jObj.getLong("constituencyId");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				idNameVO = dataMonitoringService.getTotalRegCdrVendorWiseNew(loginUserId, userId, constituencyId, startDate, endDate);
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
	   
	   public String getTotalRegCdrVendorAndTabUserWiseNew(){
			
			try {
				session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				
				Long loginUserId = 0l;
				if(regVO != null)
					loginUserId = regVO.getRegistrationID();
				jObj = new JSONObject(getTask());
				Long userId = jObj.getLong("userId");
				Long constituencyId = jObj.getLong("constituencyId");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				idNameVOs = dataMonitoringService.getTotalRegCdrVendorAndTabUserWiseNew(loginUserId, userId, constituencyId, startDate, endDate);
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
				String dataSourceType = jObj.getString("dataSourceType");
				Long stateId = jObj.getLong("stateId"); 
				
				listofIdNameVOs = dataMonitoringService.getVerifiedDtls(surveyUserId, tabUserId, webUserId, startDate, endDate,minValue,maxValue,resultType,verificationStatus,dataSourceType,stateId);  
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
		        Long  stateId = jObj.getLong("stateId");
		        resultList = dataMonitoringService.getRegistrationDetailsUserWise(fromDate, toDate,dataSourceType,verificationStatus,stateId);
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
			   session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				
				Long userId = 0l;
				if(regVO != null)
					userId = regVO.getRegistrationID();
				
			   Long cadreId = 0l;
			   Long reasonId = 0l;
			   Long constitunecyId =0l;
			   Long districtId =0l;
			   Long cadreUserId =0l;
			   Long tabUserInfoId = 0l;
			   //Long userId = 0l;
			   IdNameVO idNameVO = new IdNameVO();
			   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			   jObj = new JSONObject(getTask());
			   JSONArray jsonArray = (JSONArray) jObj.get("data");
			   for(int i = 0 ; i < jsonArray.length() ; i++){
				   idNameVO = new IdNameVO();
				   jObj = jsonArray.getJSONObject(i);
				   cadreId = jObj.getLong("cadreId");
				   reasonId = jObj.getLong("reasonId");
				   constitunecyId = jObj.getLong("constitunecyId");
				   districtId = jObj.getLong("districtId");
				   cadreUserId = jObj.getLong("cadreUserId");
				   tabUserInfoId = jObj.getLong("tabUserInfoId");
				   //userId = jObj.getLong("userId");
				   idNameVO.setCadreId(cadreId);
				   idNameVO.setRejectedCount(reasonId);
				   idNameVO.setId(userId);
				   idNameVO.setConstitunecyId(constitunecyId);
				   idNameVO.setDistrictId(districtId);
				   idNameVO.setCadreUserId(cadreUserId);
				   idNameVO.setTabUserId(tabUserInfoId);
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
			   session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				
				Long userId = 0l;
				if(regVO != null)
					userId = regVO.getRegistrationID();
			   Long cadreId = 0l;
			   Long constitunecyId =0l;
			   Long districtId =0l;
			   Long cadreUserId =0l;
			   Long tabUserInfoId = 0l;
			   //Long userId = 0l;
			   IdNameVO idNameVO = new IdNameVO();
			   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			   jObj = new JSONObject(getTask());
			   JSONArray jsonArray = (JSONArray) jObj.get("data");
			   for(int i = 0 ; i < jsonArray.length() ; i++){
				   idNameVO = new IdNameVO();
				   jObj = jsonArray.getJSONObject(i);
				   cadreId = jObj.getLong("cadreId");
				   constitunecyId = jObj.getLong("constitunecyId");
				   districtId = jObj.getLong("districtId");
				   cadreUserId = jObj.getLong("cadreUserId");
				   tabUserInfoId = jObj.getLong("tabUserInfoId");
				   //userId = jObj.getLong("userId");
				   idNameVO.setCadreId(cadreId);
				   idNameVO.setConstitunecyId(constitunecyId);
				   idNameVO.setDistrictId(districtId);
				   idNameVO.setCadreUserId(cadreUserId);
				   idNameVO.setTabUserId(tabUserInfoId);
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
	   
	   public String getTabUserImages(){  
		   try{
			   task = dataMonitoringService.getTabUserImages();
		   }catch(Exception e){
			   e.printStackTrace();
			   LOG.error("Exception raised at getDataRejectReason()  of DataMonitoringAction", e);
		   }
		   return Action.SUCCESS;
	   }
	   public String changeImageByVoterImage(){    
		   try{
			   session = request.getSession();
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				
				Long userId = 0l;
				if(regVO != null)
					userId = regVO.getRegistrationID();
				
			   Long cadreId = 0l;
			   String status = null;
			   Long constitunecyId =0l;
			   Long districtId =0l;
			   Long cadreUserId =0l;
			   Long tabUserInfoId = 0l;
			   //Long userId = 0l;
			   IdNameVO idNameVO = new IdNameVO();
			   List<IdNameVO> idNameVOs = new ArrayList<IdNameVO>();
			   jObj = new JSONObject(getTask());
				   //idNameVO = new IdNameVO();
				   cadreId = jObj.getLong("cadreId");
				   status = jObj.getString("status");
				   constitunecyId = jObj.getLong("constitunecyId");
				   districtId = jObj.getLong("districtId");
				   cadreUserId = jObj.getLong("cadreUserId");
				   tabUserInfoId = jObj.getLong("tabUserInfoId");
				   //userId = jObj.getLong("userId");
				   idNameVO.setCadreId(cadreId);
				   idNameVO.setStatus(status);
				   idNameVO.setId(userId);
				   idNameVO.setConstitunecyId(constitunecyId);
				   idNameVO.setDistrictId(districtId);
				   idNameVO.setCadreUserId(cadreUserId);
				   idNameVO.setTabUserId(tabUserInfoId);
				   idNameVOs.add(idNameVO);
			   
			   resultStatus = dataMonitoringService.changeImageByVoterImage(idNameVOs);    
		   }catch(Exception e){
			   e.printStackTrace();
			   LOG.error("Exception raised at getDataRejectReason()  of DataMonitoringAction", e);
		   }
		   return Action.SUCCESS;
	   }
	   
}
