package com.itgrids.partyanalyst.web.action;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreIVRVO;
import com.itgrids.partyanalyst.dto.CadreRegAmountUploadVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyTransactionVO;
import com.itgrids.partyanalyst.dto.TdpCadreLocationWiseReportVO;
import com.itgrids.partyanalyst.dto.ZebraPrintDetailsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.service.impl.CadreDashBoardService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.RandomGenaration;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.CadreIVRResponseVO;

public class TdpCadreReportAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(TdpCadreReportAction.class);
	
	private HttpServletRequest request;
	
	private String 								task;
	private JSONObject                  		jobj;
	
	private ITdpCadreReportService  tdpCadreReportService;
	private TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO = new TdpCadreLocationWiseReportVO();
	private SurveyTransactionVO surveyTransactionVO;
	private ZebraPrintDetailsVO zebraPrintDetailsVO;
	private List<CadreRegistrationVO> registrationVOList = new ArrayList<CadreRegistrationVO>();
	private EntitlementsHelper 					entitlementsHelper;
	private List<ZebraPrintDetailsVO> zebraPrintDetails;
	private ResultStatus result;
	private String mobileNumber;
	private String trNo;
	private String membership;
	private List<String> jobCodes;
	private List<BasicVO> resultList;
	private File file;
	private CadreIVRVO cadreIVRVO;
	private List<SelectOptionVO> constituenciesList;
	private IStaticDataService staticDataService;
	private List<CadreIVRVO> ivrVOList;
	private CadreIVRResponseVO cadreIVRResponseVO;
	
	
	public List<CadreIVRVO> getIvrVOList() {
		return ivrVOList;
	}

	public void setIvrVOList(List<CadreIVRVO> ivrVOList) {
		this.ivrVOList = ivrVOList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public List<SelectOptionVO> getConstituenciesList() {
		return constituenciesList;
	}

	public void setConstituenciesList(List<SelectOptionVO> constituenciesList) {
		this.constituenciesList = constituenciesList;
	}

	public CadreIVRVO getCadreIVRVO() {
		return cadreIVRVO;
	}

	public void setCadreIVRVO(CadreIVRVO cadreIVRVO) {
		this.cadreIVRVO = cadreIVRVO;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTrNo() {
		return trNo;
	}

	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public List<String> getJobCodes() {
		return jobCodes;
	}

	public void setJobCodes(List<String> jobCodes) {
		this.jobCodes = jobCodes;
	}

	public List<ZebraPrintDetailsVO> getZebraPrintDetails() {
		return zebraPrintDetails;
	}

	public void setZebraPrintDetails(List<ZebraPrintDetailsVO> zebraPrintDetails) {
		this.zebraPrintDetails = zebraPrintDetails;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public List<CadreRegistrationVO> getRegistrationVOList() {
		return registrationVOList;
	}

	public void setRegistrationVOList(List<CadreRegistrationVO> registrationVOList) {
		this.registrationVOList = registrationVOList;
	}

	public ZebraPrintDetailsVO getZebraPrintDetailsVO() {
		return zebraPrintDetailsVO;
	}

	public void setZebraPrintDetailsVO(ZebraPrintDetailsVO zebraPrintDetailsVO) {
		this.zebraPrintDetailsVO = zebraPrintDetailsVO;
	}

	public SurveyTransactionVO getSurveyTransactionVO() {
		return surveyTransactionVO;
	}

	public void setSurveyTransactionVO(SurveyTransactionVO surveyTransactionVO) {
		this.surveyTransactionVO = surveyTransactionVO;
	}

	public TdpCadreLocationWiseReportVO getTdpCadreLocationWiseReportVO() {
		return tdpCadreLocationWiseReportVO;
	}

	public void setTdpCadreLocationWiseReportVO(
			TdpCadreLocationWiseReportVO tdpCadreLocationWiseReportVO) {
		this.tdpCadreLocationWiseReportVO = tdpCadreLocationWiseReportVO;
	}

	public ITdpCadreReportService getTdpCadreReportService() {
		return tdpCadreReportService;
	}

	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}
		
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}

	public ResultStatus getResult() {
		return result;
	}

	public void setResult(ResultStatus result) {
		this.result = result;
	}	

	public List<BasicVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<BasicVO> resultList) {
		this.resultList = resultList;
	}

	public CadreIVRResponseVO getCadreIVRResponseVO() {
		return cadreIVRResponseVO;
	}

	public void setCadreIVRResponseVO(CadreIVRResponseVO cadreIVRResponseVO) {
		this.cadreIVRResponseVO = cadreIVRResponseVO;
	}

	public String execute()
	{
		return Action.SUCCESS;
	}
	
	
	
	
	public String getLocationWiseDetailsForExcelReport(){
		try{
			jobj = new JSONObject(getTask());
			String constiIds       = jobj.getString("constituencyIds");
			List<Long> constituencyIdsList = new ArrayList<Long>();
			String[] constituency       = constiIds.split(",");
			for (String cosntituencyId : constituency) 
			{
				cosntituencyId = cosntituencyId.trim().replace("[","").replace("]", "").trim(); 
						
				if(cosntituencyId.trim().length()>0)
				{
					constituencyIdsList.add(Long.valueOf(cosntituencyId));
				}
				
			}			
			tdpCadreLocationWiseReportVO = tdpCadreReportService.generateExcelReportForTdpCadre(constituencyIdsList);
		}
		catch (Exception e) {
			LOG.info("Entered into getLocationWiseAsOfNowDetails() in getLocationWiseDetailsForExcelReport class");
		}
		return Action.SUCCESS;
	}
	
	public String memberShipCardReportStatus()
	{
		try
		{
		  HttpSession session=request.getSession();
		  RegistrationVO regVO=(RegistrationVO)session.getAttribute("USER");
		  if(regVO==null)
			return Action.INPUT;
		
		}
		catch(Exception e)
		{
			LOG.info("Entered into memberShipCardReportStatus() in getLocationWiseDetailsForExcelReport class");
		}
		return Action.SUCCESS;
	}
	public String getMemberShipCardDetails()
	{
		try
		{
		    jobj = new JSONObject(getTask());
			String constiIds       = jobj.getString("constituencyIds");
			List<Long> constituencyIdsList = new ArrayList<Long>();
			String[] constituency       = constiIds.split(",");
			for (String cosntituencyId : constituency) 
			{
				cosntituencyId = cosntituencyId.trim().replace("[","").replace("]", "").trim(); 
						
				if(cosntituencyId.trim().length()>0)
				{
					constituencyIdsList.add(Long.valueOf(cosntituencyId));
				}
				
			}
			String fromDate=jobj.getString("fromDate");
			String  toDate=jobj.getString("toDate");
			Long stateTyleId = jobj.getLong("stateTyleId");
			String searchType = jobj.getString("searchType");
			
			zebraPrintDetailsVO =	tdpCadreReportService.getMemberShipCardPrintDetails(searchType,stateTyleId,constituencyIdsList,fromDate,toDate);
		}
		catch(Exception e)
		{
			LOG.info("Entered into getMemberShipCardDetails() in getLocationWiseDetailsForExcelReport class");
		}
	
		return Action.SUCCESS;	
	}
	
	public String getConstituencyDetailsInDistricts()
	{
		try
		{
		    jobj = new JSONObject(getTask());

			String districtIds       = jobj.getString("districtIds");
			List<Long> districtIdList = new ArrayList<Long>();
			String[] district       = districtIds.split(",");
			for (String districtId : district) 
			{
				districtId = districtId.trim().replace("[","").replace("]", "").trim(); 
						
				if(districtId.trim().length()>0)
				{
					districtIdList.add(Long.valueOf(districtId));
				}
				
			}
			surveyTransactionVO =	tdpCadreReportService.getConstituencyDetailsInDistricts(districtIdList);
		}
		catch(Exception e)
		{
			LOG.info("Entered into getMemberShipCardDetails() in getLocationWiseDetailsForExcelReport class");
		}
	
		return Action.SUCCESS;	
	}
	
	public String tdpCadreCardsPrintingDashBoard()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			zebraPrintDetailsVO = tdpCadreReportService.dashBoardForPrintingCardsDetails(user.getAccessType(),user.getAccessValue(),0L);
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			jobj = new JSONObject(getTask());
			
			zebraPrintDetailsVO = tdpCadreReportService.getCadreDetailsByStatus(jobj.getLong("Id"),jobj.getString("status"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String getPrintingStatusDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			jobj = new JSONObject(getTask());
			Long stateTyleId = jobj.getLong("stateTyleId");
			String locType = jobj.getString("type");
			zebraPrintDetailsVO = tdpCadreReportService.createDashBoardForPrintingCardsDetails(user.getAccessType(),user.getAccessValue(),stateTyleId,locType,0l,"");
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPrintDetailsInfoByConstituency()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			jobj = new JSONObject(getTask());
			
			String searchType = jobj.getString("searchType");
			Long stateTypeId = jobj.getLong("stateTypeId");
			Long selectedLocationId = jobj.getLong("locationId");
			String statusType = jobj.getString("statusType");
			zebraPrintDetailsVO = tdpCadreReportService.createDashBoardForPrintingCardsDetails(user.getAccessType(),user.getAccessValue(),stateTypeId,searchType,selectedLocationId,statusType);
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreDetailsInTeluguByMembershipId()
	{
		try {
			
			jobj = new JSONObject(getTask());			
			String membershipId = jobj.getString("membershipId");
			registrationVOList = tdpCadreReportService.getCadreDetailsInTeluguByMembershipId(membershipId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in printingDashBoard method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String memberShipCardSampleDetails()
	{
		try
		{
		  HttpSession session=request.getSession();
		  RegistrationVO regVO=(RegistrationVO)session.getAttribute("USER");
		  if(regVO==null)
			return Action.INPUT;

		  if(entitlementsHelper.checkForEntitlementToViewReport(regVO,"CADRE_2014_CARD_SAMPLE") || entitlementsHelper.checkForEntitlementToViewReport(regVO,"CADRE_2014_CARD_SAMPLE_GROUP"))
			{
				return Action.SUCCESS;
			}
		  
		}
		catch(Exception e)
		{
			LOG.info("Entered into memberShipCardSampleDetails() in getLocationWiseDetailsForExcelReport class");
		}
		return Action.SUCCESS;
	}
	public String getDayWiseCardPrintedCount()
	{
		try{
			jobj = new JSONObject(getTask());
			Long stateId = 0l;
			if(!jobj.getString("type").equalsIgnoreCase("Parliament"))
			zebraPrintDetails = tdpCadreReportService.getDayWiseCardPrintedCountInfo(jobj.getString("type"),jobj.getString("status"),jobj.getLong("Id"),stateId);
			else
				zebraPrintDetails = tdpCadreReportService.getDayWiseCardPrintedCountInfoForParlment(jobj.getString("status"),jobj.getLong("Id"),stateId);	
		}
		catch(Exception e)
		{
			LOG.info("Entered into getDayWiseCardPrintedCount() in getLocationWiseDetailsForExcelReport class");	
		}
		return Action.SUCCESS;
	}
	
	public String callCenterMembershipCardStatus()
	{
		try
		{
		  HttpSession session=request.getSession();
		  RegistrationVO regVO=(RegistrationVO)session.getAttribute("USER");
		  //if(regVO==null)
		 //return Action.INPUT;
		
		}
		catch(Exception e)
		{
			LOG.info("Error in callCenterMembershipCardStatus()");
		}
		return Action.SUCCESS;
	}
	
	public String getCadreDetailsForCallCenter()
	{
		try {
			
			jobj = new JSONObject(getTask());			
			String membershipNo = jobj.getString("membershipNo");
			String trNumber = jobj.getString("trNumber");
			String mobileNo = jobj.getString("mobileNo");
			registrationVOList = tdpCadreReportService.getMembershipCardDetailsForCallCenter(mobileNo,trNumber,membershipNo);
			
		} catch (Exception e) {
			LOG.info("Exception raised in getCadreDetailsForCallCenter ",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveCallCenterFeedback(){
		try {
			
			jobj = new JSONObject(getTask());
			
			TdpCadreLocationWiseReportVO vo = new TdpCadreLocationWiseReportVO();
			
			String comments = jobj.getString("comments");
			 if(comments != null && comments.length() > 0){
	    		  List<Long> commentsList = new ArrayList<Long>();
	    		  String[] commentValues = comments.split(",");
	    		  for(String value:commentValues){
	    			  commentsList.add(Long.parseLong(value));
	    		  }
	    		  vo.setComments(commentsList);	
	    	  }
	    	  			
			vo.setId(jobj.getLong("tdpCadreId"));
			vo.setName(jobj.getString("name"));
			vo.setRemarks(jobj.getString("remarks"));
			result = tdpCadreReportService.saveCallCenterFeedbackForCardStatus(vo);
			
		} catch (Exception e) {
				
				e.printStackTrace();
			}
			return SUCCESS;
		}
	
	
	public String getJobCodesByLocationWise()
	{
		try{
			jobj = new JSONObject(getTask());
			Long stateId = 0l;
			zebraPrintDetails = tdpCadreReportService.getJobCodesByLocationWise(jobj.getString("type"),jobj.getLong("Id"));	
		}
		catch(Exception e)
		{
			LOG.info("Entered into getDayWiseCardPrintedCount() in getLocationWiseDetailsForExcelReport class");	
		}
		return Action.SUCCESS;
	}
	
	public String getAllFeedbackDetails()
	{
		try{
			jobj = new JSONObject(getTask());
		
			resultList = tdpCadreReportService.getfeedbackDetails();	
		}
		catch(Exception e){
			LOG.info("Entered into getAllFeedbackDetails()");	
		}
		return Action.SUCCESS;
	}
	public String tdpCadreSmsStatusExe()
	{
		try{
			
		}
		catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String uploadCadreSMSFile()
	{
	try{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		RandomGenaration random = new RandomGenaration();
		String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
		String fileName = Integer.valueOf(random.randomGenerator(10)).toString()+".xls";
		String path = IWebConstants.STATIC_CONTENT_FOLDER_URL+IConstants.CADRE_SMS_STATUS_FILES+pathSeperator+fileName;
		FileUtils.copyFile(file,new File(path));
		ResultStatus resultStatus = tdpCadreReportService.insertTdpCadreSmsStatusFromExcel(path);
		 addActionMessage("Upload successfully completed");
		return SUCCESS;
	}catch (Exception e) {
		LOG.error(e);
		 addActionError("Error while uploading: " + e.getMessage());
         return ERROR;
		
	}
	}
	public String cadreIvrReportExe()
	{
		try{
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				boolean noaccess = false;
				if(regVO==null){
					return "input";
				}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADREIVRDASHBOARD")){
					noaccess = true ;
				}
				if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
					noaccess = false;
				}
				if(noaccess){
					return "error";
				}
			jobCodes = tdpCadreReportService.getIvrDates();
			Long electionTypeId = 2l;
			Long stateId = 1l;
			constituenciesList = staticDataService.getConstituenciesByElectionTypeAndStateId(electionTypeId,stateId).getConstituencies();
			if(constituenciesList!=null && constituenciesList.size()>1){
				SelectOptionVO selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(0l);
				selectOptionVO.setName("Select Constituency");
				constituenciesList.add(0,selectOptionVO);
			}
		}
		catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	public String getCadreIvrReport()
	{
		try{
			jobj = new JSONObject(getTask());
			String date = jobj.getString("date");
			Long Id = jobj.getLong("Id");
			if(jobj.getString("task").equalsIgnoreCase("count"))
				cadreIVRVO = tdpCadreReportService.getCadreIvrCount(date,Id);
			else
				cadreIVRVO = tdpCadreReportService.getCadreIvrReport(date,Id,jobj.getInt("strIndex"),jobj.getInt("maxIndex"),jobj.getString("searchType"));			
		}
		catch(Exception e){
			LOG.info("Entered into getCadreIvrReport()",e);	
		}
		return Action.SUCCESS;
	}
	
	public String getCadreIVRBasicInfo()
	{
		try{
			jobj = new JSONObject(getTask());
			if(jobj.getString("task").equalsIgnoreCase("basicCnt"))
				cadreIVRVO = tdpCadreReportService.getIvrDashBoardBasicInfo(jobj.getString("state"));
			
		}
		catch(Exception e){
			LOG.info("Entered into getCadreIvrReport()",e);	
		}
		return Action.SUCCESS;
	}
	public String getCadreIVRCount()
	{
		try{
			jobj = new JSONObject(getTask());
			if(jobj.getString("task").equalsIgnoreCase("datewiseBasicCnt"))
				ivrVOList = tdpCadreReportService.getIvrDashBoardCountsByDate(jobj.getString("fromdate"),jobj.getString("todate"),jobj.getString("state"));
		}
		catch(Exception e){
			LOG.info("Entered into getCadreIvrReport()",e);	
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseIVR()
	{
		try{
			jobj = new JSONObject(getTask());
			ivrVOList = tdpCadreReportService.getConstituencyWiseIVR();			
		}
		catch(Exception e){
			LOG.info("Entered into getConstituencyWiseIVR()",e);	
		}
		return Action.SUCCESS;
	}
	
	public String getPanchayatWiseIVR()
	{
		try{
			jobj = new JSONObject(getTask());
			cadreIVRResponseVO = tdpCadreReportService.getPanchayatWiseCadreDispatchStatus(jobj.getLong("range"), jobj.getString("state"));			
		}
		catch(Exception e){
			LOG.info("Entered into getPanchayatWiseIVR()",e);	
		}
		return Action.SUCCESS;
	}
	
	public String getTehsilWiseIVR()
	{
		try{
			jobj = new JSONObject(getTask());
			cadreIVRResponseVO = tdpCadreReportService.getTehsilWiseCadreDispatchStatus(jobj.getLong("range"));			
		}
		catch(Exception e){
			LOG.info("Entered into getTehsilWiseIVR()",e);	
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWisePercInfo(){
		try{
			jobj = new JSONObject(getTask());
			String location = jobj.getString("locationType");
			List<Long> locationIds = new ArrayList<Long>();
			if(location.equalsIgnoreCase("district")){
				String state =  jobj.getString("state");
				if(state.equalsIgnoreCase("AP")){
					for(long i=11l;i<=23l;i++){
						locationIds.add(i);
					}
				}else if(state.equalsIgnoreCase("TG")){
					for(long i=1l;i<=10l;i++){
						locationIds.add(i);
					}
				}else{
					for(long i=1l;i<=23l;i++){
						locationIds.add(i);
					}
				}
			}else{
				String[] locationArray = jobj.getString("locations").split(",");
				for(String loc:locationArray){
					locationIds.add(Long.valueOf(loc.trim()));
				}
			}
			 Date startDate = null;
			 Date endDate = null;
			 String fromDate = jobj.getString("fromDate");
			 String toDate = jobj.getString("toDate");
			 try{
				 SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
				 if(fromDate.trim().length() > 0){
					 startDate = format.parse(fromDate.trim());
				 }
				 if(toDate.trim().length() > 0){
				     endDate = format.parse(toDate.trim());
				 }
			 }
			 catch(Exception e)
			 {
				 LOG.error("Exception rised in date convert()  ",e);	 
			 }
			cadreIVRResponseVO = tdpCadreReportService.getLocationWisePercInfo(location,locationIds,startDate,endDate);			
		}
		catch(Exception e){
			LOG.info("Entered into getLocationWisePercInfo()",e);	
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWisePercInfoErrorInfo(){
		try{
			jobj = new JSONObject(getTask());
			String location = jobj.getString("locationType");
			
			 Date startDate = null;
			 Date endDate = null;
			 String fromDate = jobj.getString("fromDate");
			 String toDate = jobj.getString("toDate");
			 try{
				 SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
				 if(fromDate.trim().length() > 0){
					 startDate = format.parse(fromDate.trim());
				 }
				 if(toDate.trim().length() > 0){
				     endDate = format.parse(toDate.trim());
				 }
			 }
			 catch(Exception e)
			 {
				 LOG.error("Exception rised in date convert1() ",e);	 
			 }
			cadreIVRResponseVO = tdpCadreReportService.getLocationWisePercInfoErrorInfo(location, jobj.getLong("constituencyId"),startDate,endDate);			
		}
		catch(Exception e){
			LOG.info("Entered into getLocationWisePercInfoErrorInfo()",e);	
		}
		return Action.SUCCESS;
	}
}
