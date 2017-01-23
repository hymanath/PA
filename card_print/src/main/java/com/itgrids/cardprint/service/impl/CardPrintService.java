package com.itgrids.cardprint.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.cardprint.dao.ICardPrintVendorDAO;
import com.itgrids.cardprint.dao.IConstituencyDAO;
import com.itgrids.cardprint.dao.IConstituencyPrintStatusDAO;
import com.itgrids.cardprint.dao.IConstituencyPrintStatusTrackDAO;
import com.itgrids.cardprint.dao.IMaxPrintDetailsDAO;
import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.dao.ITdpCadreCardPrintDAO;
import com.itgrids.cardprint.dao.IUserPrintVendorDAO;
import com.itgrids.cardprint.dao.IZebraPrintDetailsDAO;
import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.dto.CardPrintVO;
import com.itgrids.cardprint.dto.CardPrintingDispatchVO;
import com.itgrids.cardprint.dto.PrintStatusUpdateVO;
import com.itgrids.cardprint.dto.PrintUpdateDetailsStatusVO;
import com.itgrids.cardprint.dto.PrintVO;
import com.itgrids.cardprint.dto.ResultStatus;
import com.itgrids.cardprint.model.ConstituencyPrintStatus;
import com.itgrids.cardprint.model.ConstituencyPrintStatusTrack;
import com.itgrids.cardprint.model.TdpCadreCardPrint;
import com.itgrids.cardprint.service.ICardPrintService;
import com.itgrids.cardprint.util.DateUtilService;
import com.itgrids.cardprint.util.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CardPrintService implements ICardPrintService{

	private static final Logger LOG = Logger.getLogger(CardPrintService.class);
	
	//Attributes
	private ICardPrintVendorDAO cardPrintVendorDAO;
	private IPrintStatusDAO printStatusDAO;
    private DateUtilService dateUtilService;
    private TransactionTemplate transactionTemplate;
	private IConstituencyPrintStatusDAO constituencyPrintStatusDAO;
	private IConstituencyPrintStatusTrackDAO constituencyPrintStatusTrackDAO;
	private IConstituencyDAO constituencyDAO ;
	private IUserPrintVendorDAO userPrintVendorDAO;
	private ITdpCadreCardPrintDAO tdpCadreCardPrintDAO;
	private IMaxPrintDetailsDAO maxPrintDetailsDAO;
	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	
    //setters and getters
	public void setCardPrintVendorDAO(ICardPrintVendorDAO cardPrintVendorDAO) {
		this.cardPrintVendorDAO = cardPrintVendorDAO;
	}
	
	public void setPrintStatusDAO(IPrintStatusDAO printStatusDAO) {
		this.printStatusDAO = printStatusDAO;
	}
	
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setConstituencyPrintStatusDAO(
			IConstituencyPrintStatusDAO constituencyPrintStatusDAO) {
		this.constituencyPrintStatusDAO = constituencyPrintStatusDAO;
	}

	public void setConstituencyPrintStatusTrackDAO(
			IConstituencyPrintStatusTrackDAO constituencyPrintStatusTrackDAO) {
		this.constituencyPrintStatusTrackDAO = constituencyPrintStatusTrackDAO;
	}
	
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public void setUserPrintVendorDAO(IUserPrintVendorDAO userPrintVendorDAO) {
		this.userPrintVendorDAO = userPrintVendorDAO;
	}
	
	public void setTdpCadreCardPrintDAO(ITdpCadreCardPrintDAO tdpCadreCardPrintDAO) {
		this.tdpCadreCardPrintDAO = tdpCadreCardPrintDAO;
	}
	
	public void setMaxPrintDetailsDAO(IMaxPrintDetailsDAO maxPrintDetailsDAO) {
		this.maxPrintDetailsDAO = maxPrintDetailsDAO;
	}

	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}

	//Business methods
	public List<BasicVO>  getAllVendors(){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = cardPrintVendorDAO.getAllVendors();
			finalList = setBasicDataToVO( dataList );
			
		}catch(Exception e){
			LOG.error("exception Occurred at getAllVendors() in CardPrintService class ", e); 
		}
		return finalList;
	}
	public List<BasicVO>  getAllPrintStatus(){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = printStatusDAO.getAllPrintStatus();
			finalList = setBasicDataToVO( dataList);
			
		}catch(Exception e){
			LOG.error("exception Occurred at getAllPrintStatus() in CardPrintService class ", e); 
		}
		return finalList;
	}
	public List<BasicVO>  getAllAssemblyConstituencies(){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = constituencyDAO.getAllAssemblyConstituencies();
			finalList = setBasicDataToVO(dataList);
			
		}catch(Exception e){
			LOG.error("exception Occurred at getAllAssemblyConstituencies() in CardPrintService class ", e); 
		}
		return finalList;
	}
	public List<BasicVO>  getConstituenciesByPrintVendor(Long printVendorId){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = constituencyPrintStatusDAO.getConstituenciesByPrintVendor(printVendorId);
			finalList = setBasicDataToVO(dataList);
			
		}catch(Exception e){
			LOG.error("exception Occurred at getConstituenciesByPrintVendor() in CardPrintService class ", e); 
		}
		return finalList;
	}
	
	public BasicVO getVendorIdAndConstituenciesByLoggedInUser(Long userId){
		
		 BasicVO  basicVO  = new BasicVO();
		try{
			
			  List<Long> list = userPrintVendorDAO.getPrintVendorIdByUserId(userId);
			  Long cardPrintVendorId = null;
			  if(list != null && list.size() > 0){
				  cardPrintVendorId = list.get(0);
			  }
			  if(cardPrintVendorId != null && cardPrintVendorId > 0l){
				  
				  basicVO.setId(cardPrintVendorId);
				  
				  List<BasicVO> constituenciesList = getConstituenciesByPrintVendor(cardPrintVendorId);
				  if(constituenciesList != null && constituenciesList.size() > 0){
					  basicVO.setSubList(constituenciesList);
				  }
			  }
			  
		}catch (Exception e){
			LOG.error("exception Occurred at getConstituenciesBasedonLoggedInUser() in CardPrintService class ", e);
		}
		return basicVO;
	}
	
	
	public List<BasicVO> setBasicDataToVO(List<Object[]> dataList){
		List<BasicVO> finalList = null;
		try{
			 if(dataList != null && dataList.size() > 0){
				 finalList = new ArrayList<BasicVO>();
				 for(Object[] obj : dataList){
					 BasicVO VO = new BasicVO();
					 VO.setId(obj[0]!=null ?(Long)obj[0]:0l);
					 VO.setName(obj[1]!=null ?obj[1].toString():"");
					 finalList.add(VO);
				 }
			 }
		}catch(Exception e){
			LOG.error("exception Occurred at setBasicDataToVO() in CardPrintService class ", e); 
		}
		return finalList;
	}
	
	 /**  
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  Updating Card Print Status to Constituency.
	  *  @since 07-JANUARY-2017
	  */
	public ResultStatus saveConstituencyPrintStatus(final PrintStatusUpdateVO inputVO){
		
		ResultStatus status = new ResultStatus();
		try{
			    final Date currentTime = dateUtilService.getCurrentDateAndTime();
				status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus arg0) {
			        	
			        	ResultStatus rs = new ResultStatus();
			        	
			        	//Check Data Is Allocated Or NOT FOR GIVEN CONSTITUENCY And VENDOR.
			        	List<Long> constituencyPrintStatusIdsList = constituencyPrintStatusDAO.getConstituencyPrintStatusIds(inputVO.getPrintVendorId() , inputVO.getConstituencyId()); 
			        	Long constituencyPrintStatusId = null;
			        	if(constituencyPrintStatusIdsList != null && constituencyPrintStatusIdsList.size() > 0){
			        		constituencyPrintStatusId = constituencyPrintStatusIdsList.get(0);
			        	}
			        	
			        	ConstituencyPrintStatus constituencyPrintStatus = null;
			        	if(constituencyPrintStatusId != null && constituencyPrintStatusId.longValue() > 0l){
			        		constituencyPrintStatus = constituencyPrintStatusDAO.get(constituencyPrintStatusId);
			        	}else{
			        		rs.setResultCode(0);
		        			rs.setExceptionMsg("Data Is Not Allocated For Given Constituency and Vendor..");
		        			return rs;
			        	}
			        	
			        	constituencyPrintStatus.setPrintVendorId(inputVO.getPrintVendorId());
			        	constituencyPrintStatus.setPrintStatusId(inputVO.getPrintStatusId());
			        	if(inputVO.getRemarks() != null && !inputVO.getRemarks().isEmpty()){
			        		constituencyPrintStatus.setRemarks(inputVO.getRemarks());
			        	}else{
			        		constituencyPrintStatus.setRemarks(null);
			        	}
			        	constituencyPrintStatus.setUpdatedBy(inputVO.getUserId());
			        	constituencyPrintStatus.setUpdatedTime(currentTime);
			        	
			        	constituencyPrintStatus = constituencyPrintStatusDAO.save(constituencyPrintStatus);
			        	
			        	//Track Saving
			        	ConstituencyPrintStatusTrack constituencyPrintStatusTrack = new ConstituencyPrintStatusTrack();
			        	constituencyPrintStatusTrack.setConstituencyPrintStatusId(constituencyPrintStatus.getConstituencyPrintStatusId());
			        	constituencyPrintStatusTrack.setConstituencyId(constituencyPrintStatus.getConstituencyId());
			        	constituencyPrintStatusTrack.setPrintVendorId(constituencyPrintStatus.getPrintVendorId());
			        	constituencyPrintStatusTrack.setPrintStatusId(constituencyPrintStatus.getPrintStatusId());
			        	constituencyPrintStatusTrack.setRemarks(constituencyPrintStatus.getRemarks());
			        	constituencyPrintStatusTrack.setUpdatedBy(constituencyPrintStatus.getUpdatedBy());
			        	constituencyPrintStatusTrack.setUpdatedTime(constituencyPrintStatus.getUpdatedTime());
			        	constituencyPrintStatusTrackDAO.save(constituencyPrintStatusTrack);
			        	
			        	rs.setMessage("Updating To Local DataBase Success..");
						rs.setResultCode(1);
						return rs;
					}
			   });
				
				//UPDATE TO LIVE TABLE.
				if(status != null && status.getResultCode() == 1){
					
					inputVO.setCurrentDate(currentTime);
					int resultCode = updateConstituencyPrintStatusToLiveTable(inputVO);
						
					if(resultCode == 0){
						status.setId(0L);
						status.setExceptionMsg("Updating To Live DataBase Failure..");
					}else if(resultCode == 1){
						status.setId(1L);
						status.setExceptionMsg("Updating To Live DataBase Success..");
					}
					
				}
				
		}catch(Exception e){
			LOG.error("exception Occurred at saveConstituencyPrintStatus() in CardPrintService class ", e); 
			status.setResultCode(0);
			status.setExceptionMsg("Exception Occurred.Try Later..");
		}
		return status;
	}
	public  int  updateConstituencyPrintStatusToLiveTable(PrintStatusUpdateVO inputVO){
		int resulCode = 0;
		try{
			
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			    
			Client client = Client.create(clientConfig);
			
			//String UrlPath = "http://localhost:8080/PartyAnalyst/WebService/cadre/syncConstituencyPrintStatus";
			String UrlPath = "http://www.mytdp.com/WebService/cadre/syncConstituencyPrintStatus";
	        WebResource resource = client.resource(UrlPath);
	        PrintUpdateDetailsStatusVO reponse = resource.accept("application/json").type("application/json").post(PrintUpdateDetailsStatusVO.class, inputVO);
	        resulCode = reponse.getResultCode();
	        
		}catch(Exception e){
			resulCode = 0;
			LOG.error("exception Occurred at updateConstituencyPrintStatusToLiveTable() in CardPrintService class ", e); 
		}
		return resulCode;
	}
	
	
	 /**  
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  UPDATING PRINT DETAILS TO TdpCadreCardPrint from ZebraPrintDetails or from MaxPrintDetails by constituencyId.
	  *  @since 21-DECEMBER-2016
	  */
	public ResultStatus updatePrintDetailsToTdpCadreCardPrint(final Long cardPrintVendorId , final Long constituencyId){
		ResultStatus status = new ResultStatus();
		try{ 
			 if(cardPrintVendorId != null && cardPrintVendorId.longValue() > 0l){
				 
				 List<Object[]> data = null;
				 if(cardPrintVendorId.longValue() == IConstants.MAX_PRINT_VENDOR_ID.longValue()){
					  
					 data = maxPrintDetailsDAO.getPrintDetailsByConstituencyId(constituencyId);
					 
				 }else if(cardPrintVendorId.longValue() == IConstants.ZEBRA_PRINT_VENDOR_ID.longValue()){
					 
					 data =  zebraPrintDetailsDAO.getPrintDetailsByConstituencyId(constituencyId);
				 }
				 
				 final List<Object[]> dataList = data;
				 
				 //UPDATE TO LOCAL TABLE.
				 status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus arg0) {
						
						 ResultStatus rs = new ResultStatus();
						
						 if(dataList != null && dataList.size() > 0)
						 {
							 int count = 0;
							 for(Object[] obj : dataList)
							 {
								 count++;
								 
								 Long tdpCadreCardPrintId = obj[0] != null ? (Long)obj[0] : null;
								 if(tdpCadreCardPrintId != null)
								 {
									 
									 TdpCadreCardPrint cardPrint = tdpCadreCardPrintDAO.get(tdpCadreCardPrintId);
									 if(cardPrint != null){
										 
										 if(obj[1] != null && obj[1].toString().trim().length() > 0){
											 cardPrint.setPrintTime((Date)obj[1]);
										 }
										 
										 if(obj[2] != null && !obj[2].toString().isEmpty()){
											 cardPrint.setSerialNumber( obj[2].toString());
										 }
										 
										 if(obj[3] != null && !obj[3].toString().isEmpty()){
											 cardPrint.setPrintStatus( obj[3].toString());
										 }
										 
										 if(obj[4] != null && !obj[4].toString().isEmpty()){
											 cardPrint.setPrintCode( obj[4].toString());
										 }
										 
										 if(obj[5] != null && !obj[5].toString().isEmpty()){
											 cardPrint.setPrintDesc( obj[5].toString());
										 }
										 
										 if(obj[6] != null && !obj[6].toString().isEmpty()){
											 cardPrint.setPrinterSerialNumber( obj[6].toString());
										 }
										 
										 if(obj[7] != null && !obj[7].toString().isEmpty()){
											 cardPrint.setBoxNo( obj[7].toString());
										 }
										 
										 if(obj[8] != null && !obj[8].toString().isEmpty()){
											 cardPrint.setPcNo( obj[8].toString());
										 }
										 
										 if(obj[9] != null && !obj[9].toString().isEmpty()){
											 cardPrint.setOuterBoxNo( obj[9].toString());
										 }
										 
										 tdpCadreCardPrintDAO.save(cardPrint);
									 }
								 }
								 
								 if(count % 500 == 0){ 
				    			       //flush a batch of inserts and release memory:
									 tdpCadreCardPrintDAO.flushAndclearSession();
				    			  }
								 
							 }
						 }
						 rs.setMessage("Updating To Local DataBase Success..");
						 rs.setResultCode(1);
						 return rs;
				    }
		       });
				 
				//UPDATE TO LIVE TABLE.
				if(status != null && status.getResultCode() == 1){
					
					ResultStatus liveStatus = updateToLiveTable(dataList);
					if(liveStatus != null){
						
						if(liveStatus.getResultCode() == 0){
							status.setId(0L);
							status.setExceptionMsg("Updating To Live DataBase Failure..");
						}else if(liveStatus.getResultCode() == 1){
							status.setId(1L);
							status.setExceptionMsg("Updating To Live DataBase Success..");
						}
					}
					
				}
	     }
			 
		}catch(Exception e){
			status.setResultCode(0);
			status.setMessage("Exception Occurred");
			LOG.error("exception Occurred at updatePrintDetailsToTdpCadreCardPrint() in CardPrintService class ", e); 
		}
		return status;
	}
	
	public ResultStatus updateToLiveTable( List<Object[]> dataList ){
		
		Date currentDate = dateUtilService.getCurrentDateAndTime();
		
		ResultStatus rs = new ResultStatus();
		
		try{
			  int noOfRecordsUpdate = IConstants.noOfRecordsUpdate;
			  
			  if(dataList != null && dataList.size() > 0){
				  
				  int totalCount =  dataList.size();
				  
				    if(totalCount <=  noOfRecordsUpdate)
			        {
				    	List<PrintVO> printList = convertObjectArrayToList(dataList,currentDate);
				    	int resulCode = callWebServiceForPrintUpdation(printList);
				    	rs.setResultCode(resulCode);
				    	
				    }else
				    {	
				    	int quotient = (int) (totalCount / noOfRecordsUpdate);
				    	int checkCount = quotient + 1;
				    	
				    	for(int i=0 ; i<checkCount ; i++){
				    		
				    		int fromIndex = i * noOfRecordsUpdate;
				    		int toIndex = 0;
				    		if(i == checkCount - 1){
				    			toIndex = fromIndex + (totalCount % noOfRecordsUpdate );
				    		}else{
				    			toIndex = fromIndex + noOfRecordsUpdate;
				    		}
				    		
				    		System.out.println(fromIndex + " - " +toIndex );
				    		List<Object[]> sublist = dataList.subList(fromIndex, toIndex);
				    		if(sublist != null && sublist.size() > 0){
			    				List<PrintVO> printList = convertObjectArrayToList(sublist,currentDate);
			    				int resulCode = callWebServiceForPrintUpdation(printList);
			    				rs.setResultCode(resulCode);
			    				if(resulCode == 0){
			    					break;
			    				}
			    			}
				    	}
				    }
			  }
		}catch(Exception e){
			LOG.error("exception Occurred at updateToLiveTable() in CardPrintService class ", e); 
		}
		return rs;
	}
	
	public  int  callWebServiceForPrintUpdation(List<PrintVO> inputList){
		int resulCode = 0;
		try{
			
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			    
			Client client = Client.create(clientConfig);
			
			 //String UrlPath = "http://localhost:8080/PartyAnalyst/WebService/cadre/updatePrintDetailsToTdpCadreCardPrint";
			 String UrlPath = "http://www.mytdp.com/WebService/cadre/updatePrintDetailsToTdpCadreCardPrint";
	        WebResource resource = client.resource(UrlPath);
	        PrintUpdateDetailsStatusVO reponse = resource.accept("application/json").type("application/json").post(PrintUpdateDetailsStatusVO.class, inputList);
	        resulCode = reponse.getResultCode();
	        
		}catch(Exception e){
			resulCode = 0;
			e.printStackTrace();
		}
		return resulCode;
	}
	
	public List<PrintVO> convertObjectArrayToList(List<Object[]> dataList ,Date currentDate){
		

		List<PrintVO> list = new ArrayList<PrintVO>(0);
		
		try{
			 if(dataList != null && dataList.size() > 0)
			 {
				for(Object[] obj :dataList)
				{
					Long tdpCadreCardPrintId = obj[0] != null ? (Long)obj[0] : null;
					 if(tdpCadreCardPrintId != null)
					 {
						 PrintVO VO = new PrintVO();
						 
						 VO.setTdpCadreCardPrintId(tdpCadreCardPrintId);
						 
						 if(obj[1] != null && obj[1].toString().trim().length() > 0){
							 VO.setPrintTime((Date)obj[1]);
						 }
						 
						 if(obj[2] != null && !obj[2].toString().isEmpty()){
							 VO.setSerialNumber( obj[2].toString());
						 }
						 
						 if(obj[3] != null && !obj[3].toString().isEmpty()){
							 VO.setPrintStatus( obj[3].toString());
						 }
						 
						 if(obj[4] != null && !obj[4].toString().isEmpty()){
							 VO.setPrintCode( obj[4].toString());
						 }
						 
						 if(obj[5] != null && !obj[5].toString().isEmpty()){
							 VO.setPrintDesc( obj[5].toString());
						 }
						 
						 if(obj[6] != null && !obj[6].toString().isEmpty()){
							 VO.setPrinterSerialNumber( obj[6].toString());
						 }
						 
						 if(obj[7] != null && !obj[7].toString().isEmpty()){
							 VO.setBoxNo( obj[7].toString());
						 }
						 
						 if(obj[8] != null && !obj[8].toString().isEmpty()){
							 VO.setPcNo( obj[8].toString());
						 }
						 
						 if(obj[9] != null && !obj[9].toString().isEmpty()){
							 VO.setOuterBoxNo( obj[9].toString());
						 }
						 
						 VO.setUpdatedTime(currentDate);
						 
						 list.add(VO);
					 }
				}
			 }
			
		}catch(Exception e){
			LOG.error("exception Occurred at convertObjectArrayToList() in CardPrintService class ", e); 
		}
		return list;
	}
	
	public List<CardPrintVO> getDstrListByVendor(Long vendorId){
		
		List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
		try{
			
			  ClientConfig clientConfig = new DefaultClientConfig();
			  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			    
			  Client client = Client.create(clientConfig);
			
			  //String UrlPath = "http://localhost:8080/PartyAnalyst/WebService/cadre/getDistrictList/"+vendorId;
			  String UrlPath = "http://www.mytdp.com/WebService/cadre/getDistrictList/"+vendorId;
	          WebResource resource = client.resource(UrlPath);
	          returnList  = resource.accept("application/json").get(new GenericType<List<CardPrintVO>>(){});
	          
		}catch(Exception e){
			LOG.error("Exception raised in getDstrListByVendor() in CardPrintService ",e);
		}
		return returnList;
	}
	
	public List<CardPrintVO> getConstListByVendor(Long vendorId,Long districtId){
		List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
		try{
			  ClientConfig clientConfig = new DefaultClientConfig();
			  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			    
			  Client client = Client.create(clientConfig);
			
			  //String UrlPath = "http://localhost:8080/PartyAnalyst/WebService/cadre/getConstencyList/"+vendorId+"/"+districtId;
			  String UrlPath = "http://www.mytdp.com/WebService/cadre/getConstencyList/"+vendorId+"/"+districtId;
	          WebResource resource = client.resource(UrlPath);
	          returnList  = resource.accept("application/json").get(new GenericType<List<CardPrintVO>>(){});
			
		}catch(Exception e){
			LOG.error("Exception raised in getConstListByVendor() in CardPrintService ",e);
		}
		return returnList;
	}
	
	public CardPrintingDispatchVO getPrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId){
		CardPrintingDispatchVO finalVO = new CardPrintingDispatchVO();
		try{
			 ClientConfig clientConfig = new DefaultClientConfig();
			  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			    
			  Client client = Client.create(clientConfig);
			
			  //String UrlPath = "http://localhost:8080/PartyAnalyst/WebService/cadre/getPrintingDispatchDetails/"+vendorId+"/"+districtId+"/"+constituencyId;
			  String UrlPath = "http://www.mytdp.com/WebService/cadre/getPrintingDispatchDetails/"+vendorId+"/"+districtId+"/"+constituencyId;
	          WebResource resource = client.resource(UrlPath);
	          finalVO  = resource.accept("application/json").get(CardPrintingDispatchVO.class);
			
		}catch (Exception e) {
			LOG.error("Exception raised in getPrintingDispatchDetails() in CardPrintService ",e);
		}
		return finalVO;
	}
}
