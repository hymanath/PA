package com.itgrids.tpi.rws.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IHabitationDAO;
import com.itgrids.dao.IRwsConstituencyDAO;
import com.itgrids.dao.IRwsDistrictDAO;
import com.itgrids.dao.IRwsTehsilDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.ITressedHabitationDAO;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.dao.impl.NregaComponentServiceDAO;
import com.itgrids.dto.AmsVO;
import com.itgrids.dto.BasicVO;
import com.itgrids.dto.EmailAttributesVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KPIVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregaConsolidatedInputVO;
import com.itgrids.dto.NregaLocationOverviewVO;
import com.itgrids.dto.RangeVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.RwsClickVO;
import com.itgrids.dto.StatusVO;
import com.itgrids.dto.WaterSourceVO;
import com.itgrids.dto.WebserviceHealthVO;
import com.itgrids.model.Habitation;
import com.itgrids.model.TressedHabitation;
import com.itgrids.model.WebserviceCallDetails;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.tpi.rws.service.IRWSNICService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.NREGSCumulativeThread;
import com.itgrids.utils.NREGSCumulativeThreadPerformance;
import com.itgrids.utils.RWSThread;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;
import sun.util.logging.resources.logging;

@Service
@Transactional
public class RWSNICService implements IRWSNICService{
	private static final Logger LOG = Logger.getLogger(RWSNICService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private ITehsilDAO tehsilDAO;
	@Autowired
	private IRwsTehsilDAO rwsTehsilDAO;
	@Autowired
	private IRwsDistrictDAO rwsDistrictDAO;
	@Autowired
	private IRwsConstituencyDAO rwsConstituencyDAO;
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private IWebserviceCallDetailsDAO webserviceCallDetailsDAO;
    @Autowired
    private NregaComponentServiceDAO nregaComponentServiceDAO;
	@Autowired
	private IHabitationDAO habitationDAO;
	
	@Autowired
	private ITressedHabitationDAO tressedHabitationDAO;
	/*
	 * Date : 15/06/2017
	 * Author :Sandeep
	 * @description : getHabitationCoverageByStatusByLocationType
	 */
	public List<LocationVO> getHabitationCoverageByStatusByLocationType(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			if(inputVO!= null){
				inputVO = setFilterVal(inputVO);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getHabitationCoverageByStatusByLocationType");
			
	        String authStringEnc = getAuthenticationString("admin","admin@123");
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);//null;
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		if(inputVO.getLocationType() != null && inputVO.getLocationType().equalsIgnoreCase("mandal")){
	 	    			//build mandal level data
	 	    			buildHabitationCoverageByStatusMandalWise(output,voList);
	 	    		}else{
	 	    			JSONArray finalArray = new JSONArray(output);
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			for(int i=0;i<finalArray.length();i++){
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				if(jObj.length() > 0){
		 	    					LocationVO vo = new LocationVO();
		 	    					if(inputVO.getLocationType().equalsIgnoreCase("state"))
		 	    						vo.setGoNumber("01");
		 	    					else
		 	    						vo.setGoNumber(jObj.getString("locationId"));
		 	    					
		 	    					vo.setLocationName(jObj.getString("locationName"));
			 	    				vo.setStreetHabitationCount(jObj.getLong("stressedHabitationCount"));
			 	    				vo.setTotalCount(jObj.getLong("totalCount"));
			 	    				
			 	    				JSONArray statusListArray = jObj.getJSONArray("statusList");
			 	    				
			 	    				if(statusListArray != null && statusListArray.length() > 0){
			 	    					List<StatusVO> tempList =getStatusSkeleton();
			 	    					for (int j = 0; j < statusListArray.length(); j++) {
			 	    						JSONObject jobj1 = (JSONObject) statusListArray.get(j);
											StatusVO statusVO = getMatchedStatusVO(tempList,jobj1.getString("status"));
											
											if(statusVO != null){
												statusVO.setCount(jobj1.getLong("count"));
												statusVO.setPercentage(jobj1.getDouble("percentage"));
											}
										}
			 	    					vo.setStatusList(tempList);
			 	    				}
			 	    				voList.add(vo);
		 	    				}
		 	    			}
		 	    		}
	 	    		}
	 	    	}
	 	      }
	        
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationCoverageByStatusByLocationType - RuralWaterSupplyDashBoardService service", e);
		}
		
		return voList;
	}
	
	public StatusVO getMatchedStatusVO(List<StatusVO> voList,String status){
		if(voList != null && voList.size() > 0){
			for (StatusVO statusVO : voList) {
				if(statusVO.getStatus().equalsIgnoreCase(status))
					return statusVO;
			}
		}
		return null;
	}
	
	public List<StatusVO> getStatusSkeleton(){
		List<StatusVO> tempList = new ArrayList<StatusVO>(0);
		StatusVO FCVO = new StatusVO();
		FCVO.setStatus("FC");
		tempList.add(0,FCVO);
		StatusVO pc4VO = new StatusVO();
		pc4VO.setStatus("PC4");
		tempList.add(1,pc4VO);
		StatusVO pc3VO = new StatusVO();
		pc3VO.setStatus("PC3");
		tempList.add(2,pc3VO);
		StatusVO pc2VO = new StatusVO();
		pc2VO.setStatus("PC2");
		tempList.add(3,pc2VO);
		StatusVO pc1VO = new StatusVO();
		pc1VO.setStatus("PC1");
		tempList.add(4,pc1VO);
		StatusVO nssVO = new StatusVO();
		nssVO.setStatus("NSS");
		tempList.add(5,nssVO);
		return tempList;
	}
	
	public void buildHabitationCoverageByStatusMandalWise(String output,List<LocationVO> voList){
		try {
			JSONArray finalArray = new JSONArray(output);
			if(finalArray!=null && finalArray.length()>0){
	 			for(int i=0;i<finalArray.length();i++){
	 				JSONObject distObj = (JSONObject)finalArray.get(i);
	 				if(distObj != null&& distObj.length() > 0 && distObj.getJSONArray("subList") != null && distObj.getJSONArray("subList").length() > 0){
		 				JSONArray mandalArr = distObj.getJSONArray("subList");
		 				for (int j = 0; j < mandalArr.length(); j++) {
		 					JSONObject mandalObj = (JSONObject) mandalArr.get(j);
		 					LocationVO vo = new LocationVO();
		 					vo.setGoNumber(mandalObj.getString("locationId"));
	 	    				vo.setLocationName(mandalObj.getString("locationName"));
		 	    			vo.setStreetHabitationCount(mandalObj.getLong("stressedHabitationCount"));
		 	    			vo.setTotalCount(mandalObj.getLong("totalCount"));
		 	    			vo.setParentLocationId(distObj.getString("districtId"));
		 	    			JSONArray statusListArray = mandalObj.getJSONArray("statusList");
		 	    				
		 	    			if(statusListArray != null && statusListArray.length() > 0){
	 	    					List<StatusVO> tempList =getStatusSkeleton();
	 	    					for (int s = 0; s < statusListArray.length(); s++) {
	 	    						JSONObject jobj1 = (JSONObject) statusListArray.get(s);
									StatusVO statusVO = getMatchedStatusVO(tempList,jobj1.getString("status"));
									
									if(statusVO != null){
										statusVO.setCount(jobj1.getLong("count"));
										statusVO.setPercentage(jobj1.getDouble("percentage"));
									}
								}
	 	    					vo.setStatusList(tempList);
	 	    				}
		 	    			
		 	    			voList.add(vo);
						}
		 			}
	 			}
	 		}
		} catch (Exception e) {
			LOG.error("Exceptionr aised while building the data mandal wise habitation status counts", e);
		}
	}
	/*
	 * Date : 15/06/2017
	 * Author :Sandeep
	 * @description : getLabTestDetails
	 */
	public BasicVO getLabTestDetails(InputVO inputVO){
		BasicVO basicVO = null;
		try {

	        WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getLabTestDetails");	        
	        String authStringEnc = getAuthenticationString("admin","admin@123");	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 //output = "{\"physicalTestCount\":13665,\"bacterialTestCount\":27622}";
	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);
	 	    		 basicVO = new BasicVO();
	 	    		basicVO.setPhysicalTestCount(jObj.getLong("physicalTestCount"));
	 	    		basicVO.setBacterialTestCount(jObj.getLong("bacterialTestCount"));
	 	    		
	 	    		basicVO.setTotal((basicVO.getPhysicalTestCount() !=null ? basicVO.getPhysicalTestCount():0l) + (basicVO.getBacterialTestCount() !=null ? basicVO.getBacterialTestCount():0l));
	 	    		
	 	    		basicVO.setPercentage(calculatePercentage(basicVO.getTotal(), basicVO.getPhysicalTestCount()) !=null ?
	 	    				Double.parseDouble(calculatePercentage(basicVO.getTotal(), basicVO.getPhysicalTestCount())):0.0d);//PhysicalTestCount Percentage
	 	    		basicVO.setPercentageOne(calculatePercentage(basicVO.getTotal(), basicVO.getBacterialTestCount()) !=null ? 
	 	    				Double.parseDouble(calculatePercentage(basicVO.getTotal(), basicVO.getBacterialTestCount())):0.0d);//BacterialTestCount Percentage
	 	    	 }
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLabTestDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return basicVO;
	}
	
	/*
	 * Date : 15/06/2017
	 * Author :Balu
	 * @description : getHabitationSupplyDetails
	 */
	public BasicVO getHabitationSupplyDetails(InputVO VO){
		BasicVO finalVO = new BasicVO();
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/gethabitationWatersupplyDetails");
			    String authStringEnc = getAuthenticationString("admin","admin@123");	        
		        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
		        
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	//String output ="{'safeMLD':2.94,'unsafeMLD':0.12}";
	 	    	 
	 	    	 if(output != null && !output.isEmpty()){
	 	    		JSONObject jObj = new JSONObject(output);	 	  
	 	    			if(!jObj.getString("status").equalsIgnoreCase("Failure")){
	 	    				finalVO.setSurfaceWaterSafeMLD(new BigDecimal(jObj.getString("surfaceWaterSafeMLD")));
	 	    				finalVO.setGroundWaterUnSafeMLD(new BigDecimal(jObj.getString("groundWaterUnSafeMLD")));
	 	    				finalVO.setGroundWaterSafeMLD(new BigDecimal(jObj.getString("groundWaterSafeMLD")));
	 	    				finalVO.setSurfaceWaterUnSafeMLD(new BigDecimal(jObj.getString("surfaceWaterUnSafeMLD")));
	 	    				finalVO.setTotalUnSafeWaterInMLD(new BigDecimal(jObj.getString("totalUnSafeWaterInMLD")));
	 	    				finalVO.setTotalSafeWaterInMLD(new BigDecimal(jObj.getString("totalSafeWaterInMLD")));
	 	    				
	 	    			}
	 	    				
	 	    		}
	 	    	}
	 	    	
		} catch (Exception e) {
			LOG.error("Exception raised at getHabitationSupplyDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalVO;
	}
	
	/*
	 * Date : 15/06/2017
	 * Author :Balu
	 * @description : getSchemesDetails
	 */
	public List<BasicVO> getSchemesDetails(InputVO VO){
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		try {
			
			 WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getSchemesDetails");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
		        
	       
	       if(response.getStatus() != 200){
	 	      throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);	 	    	 
	 	    	//String output = "[{'assetType':'PWS','count':1583},{'assetType':'CPWS','count':8},{'assetType':'SCHOOLS','count':3},{'assetType':'SUSTAINABILITY','count':26}]";	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray jsonArray = new JSONArray(output);	 	    
		 	    		if(jsonArray !=null && jsonArray.length()>0){
		 	    			Long total  = 0l;
		 	    			for (int i = 0;i<jsonArray.length();i++) {		 	    				
		 	    				JSONObject jObj = (JSONObject)jsonArray.get(i);		 	    				
		 	    				BasicVO Vo = new BasicVO();	
		 	    				
		 	    				Vo.setAssetType(jObj.getString("assetType"));
		 	    				Vo.setCount(jObj.getLong("count"));		 	    						 	    				 	    			
		 	    				finalList.add(Vo);
		 	    				
		 	    				total = total + jObj.getLong("count");				 	    				
							}
		 	    			
		 	    			finalList.get(0).setTotal(total);
		 	    			
		 	    			for (BasicVO basicVO : finalList) {								
		 	    				basicVO.setPercentage(Double.parseDouble(calculatePercentage(total, basicVO.getCount())));		 	    				
							}
		 	    			
		 	    		}	 	    			
	 	    	}
			
	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemesDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 15/06/2017
	 * Author :Balu
	 * @description : getSchemeWiseWorkDetails
	 */
	public List<BasicVO> getSchemeWiseWorkDetails(InputVO VO){
		List<BasicVO> finalList = new ArrayList<BasicVO>();
		try {
			
			if(VO!= null){
				VO = setFilterVal(VO);
			}
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getSchemeWiseWorkDetails");
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, VO);
			
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    		String output = response.getEntity(String.class);
	 	    	 
			//String output = "[{'assetType':'SUSTAINABILITY','workOngoingCount':10,'workComissionedCount':35,'workCompletedCount':46,'workNotGroundedCount':0},{'assetType':'SCHOOLS','workOngoingCount':6,'workComissionedCount':12,'workCompletedCount':17,'workNotGroundedCount':0},{'assetType':'PWS','workOngoingCount':162,'workComissionedCount':979,'workCompletedCount':1310,'workNotGroundedCount':0},{'assetType':'CPWS','workOngoingCount':25,'workComissionedCount':42,'workCompletedCount':51,'workNotGroundedCount':0}]";
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		if(VO.getType() !=null&& !VO.getType().trim().isEmpty() && VO.getType().trim().equalsIgnoreCase("graph")){
	 	    			setStateSchemeWiseWorkDetails(output,finalList);
	 	    		}else if(VO.getLocationType() !=null&& !VO.getLocationType().trim().isEmpty() && VO.getLocationType().trim().equalsIgnoreCase("mandal")){
	 	    			setMandalSchemeWiseWorkDetails(output,finalList);
	 	    		}else{
	 	    			
	 	    			JSONArray jsonArrayMain = new JSONArray(output);	 
	 	    			if(jsonArrayMain !=null && jsonArrayMain.length()>0){
		 	   				for(int j = 0;j<jsonArrayMain.length();j++){
		 	   					JSONObject jObjMain = (JSONObject)jsonArrayMain.get(j);
		 	   					JSONArray jsonArray = jObjMain.getJSONArray("subList");//type
		 	   					
		 	   					BasicVO mainVO = new BasicVO();
		 	   					
		 	   					mainVO.setGoNumber(jObjMain.getString("locationId"));
		 	   					mainVO.setLocationName(jObjMain.getString("locationName"));
		 	   					
		 	   					if(jsonArray !=null && jsonArray.length()>0){
		 	   						for (int i = 0;i<jsonArray.length();i++){		
		 	   							JSONObject jObj = (JSONObject)jsonArray.get(i);	
		 	   							BasicVO Vo = new BasicVO();
			 	   						Vo.setAssetType(jObj.getString("assetType"));
				 	    				Vo.setWorkOngoingCount(jObj.getLong("workOngoingCount"));
				 	    				Vo.setWorkComissionedCount(jObj.getLong("workComissionedCount"));
				 	    				Vo.setWorkCompletedCount(jObj.getLong("workCompletedCount"));
				 	    				Vo.setWorkNotGroundedCount(jObj.getLong("workNotGroundedCount"));
				 	    				Vo.setCount(Vo.getWorkOngoingCount()+Vo.getWorkComissionedCount()+Vo.getWorkCompletedCount()+Vo.getWorkNotGroundedCount());
				 	    				if(Vo.getCount() > 0l){
				 	    					Vo.setPercentageOne((Vo.getWorkOngoingCount()*100.00)/Vo.getCount());
					 	    				Vo.setPercentageTwo((Vo.getWorkComissionedCount()*100.00)/Vo.getCount());
					 	    				Vo.setPercentageThree((Vo.getWorkCompletedCount()*100.00)/Vo.getCount());
					 	    				Vo.setPercentageFour((Vo.getWorkNotGroundedCount()*100.00)/Vo.getCount());
				 	    				}
		 	   							
				 	    				mainVO.getBasicList().add(Vo);
				 	    				
									}
		 	   					}
		 	   					finalList.add(mainVO);
		 	   					
		 	   				}
	 	    			}
	 	    			
	 	    		}	
	 	    	}
	 	    	
	 	    }
			
		} catch (Exception e) {
			LOG.error("Exception raised at getSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
		}
		
		return finalList;
	}
	public  List<BasicVO> setStateSchemeWiseWorkDetails(String output,List<BasicVO> finalList){
		try {
			JSONArray jsonArray = new JSONArray(output);	 	    
	    		if(jsonArray !=null && jsonArray.length()>0){
	    			for (int i = 0;i<jsonArray.length();i++) {		 	    				
	    				JSONObject jObjMain = (JSONObject)jsonArray.get(i);	
	    				
	    				JSONArray jsonArr = jObjMain.getJSONArray("subList");
	    				
	    				if(jsonArr !=null && jsonArr.length()>0){
			    			for (int j = 0;j<jsonArr.length();j++) {		 	    				
			    				JSONObject jObj = (JSONObject)jsonArr.get(j);	
			    				BasicVO Vo = new BasicVO();		 	    				
			    				Vo.setAssetType(jObj.getString("assetType"));
			    				Vo.setWorkOngoingCount(jObj.getLong("workOngoingCount"));
			    				Vo.setWorkComissionedCount(jObj.getLong("workComissionedCount"));
			    				Vo.setWorkCompletedCount(jObj.getLong("workCompletedCount"));
			    				Vo.setWorkNotGroundedCount(jObj.getLong("workNotGroundedCount"));
			    				Vo.setCount(Vo.getWorkOngoingCount()+Vo.getWorkComissionedCount()+Vo.getWorkCompletedCount()+Vo.getWorkNotGroundedCount());
			    				if(Vo.getCount() > 0l){
			    					Vo.setPercentageOne((Vo.getWorkOngoingCount()*100.00)/Vo.getCount());
		 	    				Vo.setPercentageTwo((Vo.getWorkComissionedCount()*100.00)/Vo.getCount());
		 	    				Vo.setPercentageThree((Vo.getWorkCompletedCount()*100.00)/Vo.getCount());
		 	    				Vo.setPercentageFour((Vo.getWorkNotGroundedCount()*100.00)/Vo.getCount());
			    				}
			    				finalList.add(Vo);
		    				}		    				
	    				}
	    			}
	    		}	
			
		} catch (Exception e) {
			LOG.error("Exception raised at setStateSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalList;
	}
	public List<BasicVO> setMandalSchemeWiseWorkDetails(String output,List<BasicVO> finalList){
		try {
			JSONArray jsonArrayMain = new JSONArray(output);	 
			if(jsonArrayMain !=null && jsonArrayMain.length()>0){
				for(int j = 0;j<jsonArrayMain.length();j++){
					JSONObject jObjMain = (JSONObject)jsonArrayMain.get(j);
					JSONArray jsonArray = jObjMain.getJSONArray("subList");//mandal
					
					if(jsonArray !=null && jsonArray.length()>0){
			    			for (int k = 0;k<jsonArray.length();k++) {		 	    				
			    				JSONObject jObjSub = (JSONObject)jsonArray.get(k);	
			    				
			    				JSONArray jsonSubArray = jObjSub.getJSONArray("subList");//types
			    				
			    				BasicVO mainVo = new BasicVO();
			    				mainVo.setDistrictId(jObjMain.getString("locationId"));//district			    				
			    				mainVo.setName(jObjSub.getString("name"));//distName
			    				mainVo.setGoNumber(jObjSub.getString("locationId"));//mandalId
			    				mainVo.setLocationName(jObjSub.getString("locationName"));//mandalName
			    				
			    				finalList.add(mainVo);
			    				
			    				if(jsonSubArray !=null && jsonSubArray.length()>0){			    					
			    					for (int i = 0; i < jsonSubArray.length(); i++) {			    						
			    						JSONObject jObj = (JSONObject)jsonSubArray.get(i);				    						
			    						BasicVO Vo = new BasicVO();		 	    				
					    				Vo.setAssetType(jObj.getString("assetType"));
					    				Vo.setWorkOngoingCount(jObj.getLong("workOngoingCount"));
					    				Vo.setWorkComissionedCount(jObj.getLong("workComissionedCount"));
					    				Vo.setWorkCompletedCount(jObj.getLong("workCompletedCount"));
					    				Vo.setWorkNotGroundedCount(jObj.getLong("workNotGroundedCount"));
					    				Vo.setCount(Vo.getWorkOngoingCount()+Vo.getWorkComissionedCount()+Vo.getWorkCompletedCount()+Vo.getWorkNotGroundedCount());
					    				if(Vo.getCount() > 0l){ 
					    					Vo.setPercentageOne(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkOngoingCount())));
						    				Vo.setPercentageTwo(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkComissionedCount())));
						    				Vo.setPercentageThree(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkCompletedCount())));
						    				Vo.setPercentageFour(new Double(calculatePercentage(Vo.getCount(),Vo.getWorkNotGroundedCount())));
					    				}
					    				mainVo.getBasicList().add(Vo);
									}
			    				}
			    				
			    				
			    			}
						}
					
				}
			}
			
			
    			
    		
    		
    		
		
	} catch (Exception e) {
			LOG.error("Exception raised at setMandalSchemeWiseWorkDetails - RuralWaterSupplyDashBoardService service", e);
		}
		return finalList;
	}
	
	/*
	 * Date : 15/06/2017
	 * Author :Nagarjuna
	 * @description : getAssetsInfo
	 */
	public List<BasicVO> getAssetsInfo(InputVO vo) {
		List<BasicVO> assetsList = new ArrayList<BasicVO>(0);
		try {
			if(vo!= null){
				vo = setFilterVal(vo);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getAssetsinfo");          
			String authStringEnc = getAuthenticationString("admin","admin@123");          
			ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
       
			if(response.getStatus() != 200){
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
           	}else{
           		String output =response.getEntity(String.class);
           		if(output != null && !output.isEmpty()){
           			JSONObject jobj = new JSONObject(output);
              
           			if(jobj.getString("status").equalsIgnoreCase("Success") && jobj.getJSONArray("assetTypeList") != null && jobj.getJSONArray("assetTypeList").length() > 0){
           				if(vo.getLocationType().equalsIgnoreCase("mandal")){
           					buildAssetsDataForMandalLevel(jobj,assetsList);
           				}else{
           					buildAssetsData(jobj,assetsList);
           				}
           				
           			}
           		}
           	}
     	}catch (Exception e) {
     		LOG.error("Exception raised at getAssetsInfo - RuralWaterSupplyDashBoardService service", e);
     	}
     	return assetsList;
     }
	
	public List<BasicVO> getAssetsSkeleton(JSONArray distinctAssetsList){
		List<BasicVO> voList = new ArrayList<BasicVO>(0);
		try {
			if(distinctAssetsList != null && distinctAssetsList.length() > 0){
				for (int i = 0; i < distinctAssetsList.length(); i++) {
					if(!distinctAssetsList.getString(i).equalsIgnoreCase("SCHOOLS") && !distinctAssetsList.getString(i).equalsIgnoreCase("LAB")){
						BasicVO invo = new BasicVO();
						invo.setAssetType(distinctAssetsList.getString(i));
						voList.add(invo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getAssetsSkeleton - RuralWaterSupplyDashBoardService service", e);
		}
		
		return voList;
	}
	
	public BasicVO getMatchedAssetVO(List<BasicVO> voList,String assetType){
		if(voList != null && voList.size() > 0){
			for (BasicVO basicVO : voList) {
				if(basicVO.getAssetType().equalsIgnoreCase(assetType)){
					return basicVO;
				}
			}
		}
		return null;
	}
	
	public void buildAssetsDataForMandalLevel(JSONObject jobj,List<BasicVO> assetsList){
		try {
			Map<Long,Map<Long,BasicVO>> finalMap = new HashMap<Long, Map<Long,BasicVO>>(0);//Map<distId,Map<mandalId,BasicVO>>
			JSONArray finalArray = jobj.getJSONArray("assetTypeList");
			for(int i=0;i<finalArray.length();i++){
				JSONObject jObj = (JSONObject) finalArray.get(i);
				if(finalMap.get(jObj.getLong("districtId")) == null){
					Map<Long,BasicVO> inMap = new HashMap<Long, BasicVO>(0);
					BasicVO vo = new BasicVO();
					vo.setParentLocationId(jObj.getString("districtId"));
					vo.setGoNumber(jObj.getString("locationId"));
					vo.setName(jObj.getString("locationName"));
					
					vo.setBasicList(getAssetsSkeleton(jobj.getJSONArray("distinctAssetsList")));
					BasicVO matchedAssetVO =  getMatchedAssetVO(vo.getBasicList(),jObj.getString("assetType"));
					if(matchedAssetVO != null)
						matchedAssetVO.setCount(jObj.getLong("count"));
					/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
	   					BasicVO basicVO = new BasicVO();
	   					basicVO.setAssetType(jObj.getString("assetType"));
	   					basicVO.setCount(jObj.getLong("count"));
	   					vo.getBasicList().add(basicVO);
	   				}*/
					
					inMap.put(jObj.getLong("locationId"), vo);
					finalMap.put(jObj.getLong("districtId"),inMap);
				}else{
					Map<Long, BasicVO> distMap = finalMap.get(jObj.getLong("districtId"));
					
					if(distMap.get(jObj.getLong("locationId")) == null){
						BasicVO vo = new BasicVO();
						vo.setParentLocationId(jObj.getString("districtId"));
						vo.setGoNumber(jObj.getString("locationId"));
						vo.setName(jObj.getString("locationName"));
						
						vo.setBasicList(getAssetsSkeleton(jobj.getJSONArray("distinctAssetsList")));
						BasicVO matchedAssetVO =  getMatchedAssetVO(vo.getBasicList(),jObj.getString("assetType"));
						if(matchedAssetVO != null)
							matchedAssetVO.setCount(jObj.getLong("count"));
						/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
		   					BasicVO basicVO = new BasicVO();
		   					basicVO.setAssetType(jObj.getString("assetType"));
		   					basicVO.setCount(jObj.getLong("count"));
		   					vo.getBasicList().add(basicVO);
		   				}*/
						finalMap.get(jObj.getLong("districtId")).put(jObj.getLong("locationId"), vo);
						
					}else{
						BasicVO matchedAssetVO =  getMatchedAssetVO(distMap.get(jObj.getLong("locationId")).getBasicList(),jObj.getString("assetType"));
						if(matchedAssetVO != null)
							matchedAssetVO.setCount(jObj.getLong("count"));
						/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
		   					BasicVO basicVO = new BasicVO();
		   					basicVO.setAssetType(jObj.getString("assetType"));
		   					basicVO.setCount(jObj.getLong("count"));
		   					distMap.get(jObj.getLong("locationId")).getBasicList().add(basicVO);
		   				}*/
					}
				}
			}
			
			if(finalMap != null && finalMap.size() > 0){
				for (Entry<Long, Map<Long, BasicVO>> distEntry : finalMap.entrySet()) {
					Map<Long, BasicVO> manMap = distEntry.getValue();
					if(manMap != null && manMap.size() > 0){
						for (Entry<Long, BasicVO> manEntry : manMap.entrySet()) {
							assetsList.add(manEntry.getValue());
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at buildAssetsDataForMandalLevel - RuralWaterSupplyDashBoardService service", e);
		}
	}
	
	public void buildAssetsData(JSONObject jobj,List<BasicVO> assetsList){
		try {
			Map<Long,BasicVO> finalMap = new HashMap<Long,BasicVO>(0);
			JSONArray finalArray = jobj.getJSONArray("assetTypeList");
	                
	   		for(int i=0;i<finalArray.length();i++){
	   			JSONObject jObj = (JSONObject) finalArray.get(i);
	   			if(finalMap.get(jObj.getLong("locationId")) == null){
	   				BasicVO inVO = new BasicVO();
	   				inVO.setGoNumber(jObj.getString("locationId"));
	   				inVO.setName(jObj.getString("locationName"));
	             
	   				inVO.setBasicList(getAssetsSkeleton(jobj.getJSONArray("distinctAssetsList")));
					BasicVO matchedAssetVO =  getMatchedAssetVO(inVO.getBasicList(),jObj.getString("assetType"));
					if(matchedAssetVO != null)
						matchedAssetVO.setCount(jObj.getLong("count"));
					
	   				/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
	   					BasicVO basicVO = new BasicVO();
	   					basicVO.setAssetType(jObj.getString("assetType"));
	   					basicVO.setCount(jObj.getLong("count"));
	   					inVO.getBasicList().add(basicVO);
	   				}*/
	   						
	   				finalMap.put(jObj.getLong("locationId"), inVO);
	   			}else{
	   				BasicVO matchedAssetVO =  getMatchedAssetVO(finalMap.get(jObj.getLong("locationId")).getBasicList(),jObj.getString("assetType"));
					if(matchedAssetVO != null)
						matchedAssetVO.setCount(jObj.getLong("count"));
	   				/*if(!jObj.getString("assetType").equalsIgnoreCase("SCHOOLS") && !jObj.getString("assetType").equalsIgnoreCase("LAB")){
	   					BasicVO basicVO = new BasicVO();
	   					basicVO.setAssetType(jObj.getString("assetType"));
	   					basicVO.setCount(jObj.getLong("count"));
	   					finalMap.get(jObj.getLong("locationId")).getBasicList().add(basicVO);
	   				}*/
	   			}

	   		}
	   			
	   		if(finalMap.size() > 0)
	   			assetsList.addAll(finalMap.values());
		}catch (Exception e) {
			LOG.error("Exception raised at buildAssetsData - RuralWaterSupplyDashBoardService service", e);
		}		
 	}
		
	public List<StatusVO> getAlertDetailsOfCategoryByStatusWise(InputVO inputVO){
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertDetailsOfCategoryByStatusWise");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getAlertDetailsOfCategoryByStatusWise");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray arr = new JSONArray(output);
					
					if(arr != null && arr.length() > 0){
						StatusVO statusVO = new StatusVO();
						
						Long total =0l;
						
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setColor(jobj.getString("color"));
							vo.setCount(jobj.getLong("count"));
							
							total = total + vo.getCount();
							
							statusVO.getStatusList().add(vo);
						}
						
						Collections.sort(statusVO.getStatusList(), statusAscendingOrder);
						
						statusVO.getStatusList().get(0).setTotal(total);
						
						for (StatusVO statusVO2 : statusVO.getStatusList()) {
							statusVO2.setPercentage(calculatePercentage(total, statusVO2.getCount()) !=null ? Double.parseDouble(calculatePercentage(total, statusVO2.getCount())):0.0d );
						}
						
						voList.add(statusVO);
					}
				}
 	      	}
		} catch (Exception e) {
			LOG.error("Exception raised at getAlertDetailsOfCategoryByStatusWise - RWSNICService service", e);
		}
		return voList;
	}
	public static Comparator<StatusVO> statusAscendingOrder = new Comparator<StatusVO>() {
     	public int compare(StatusVO vo2, StatusVO vo1) {
     	Long id2 = vo2.getId();
     	Long id1 = vo1.getId();
     	 return id2.compareTo(id1);
     	}
      };
	
	public List<StatusVO> getAlertFeedbackStatusDetails(InputVO inputVO){
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertFeedbackStatusDetails");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);

        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray arr = new JSONArray(output);
					
					if(arr != null && arr.length() > 0){						
						Long total=0l;						
						for (int i = 0; i < arr.length(); i++) {
							//id,name,color,count
							JSONObject jobj = (JSONObject)arr.get(i);
							StatusVO vo = new StatusVO();
							vo.setId(jobj.getLong("id"));
							vo.setName(jobj.getString("name"));
							vo.setCount(jobj.getLong("count"));
							voList.add(vo);
							total = total + vo.getCount() ;
						}
						
						voList.get(0).setTotal(total);
						
						for (StatusVO statusVO : voList) {
							statusVO.setPercentage(calculatePercentage(total, statusVO.getCount()) !=null ? Double.parseDouble(calculatePercentage(total, statusVO.getCount())):0.0d);
						}
						
						
					}
				}
 	      	}
		} catch (Exception e) {
			LOG.error("Exception raised at getAlertFeedbackStatusDetails - RWSNICService service", e);
		}
		return voList;
	}
	
	
	/*
	 * Date : 18/06/2017
	 * Author :Nagarjuna
	 * @description : getWaterSourceInfo
	 */
	public WaterSourceVO getWaterSourceInfo(InputVO inputVO) {
		WaterSourceVO waterSourceInfo =new WaterSourceVO();
		try{
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getWaterSourceDeatils");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
			
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				/*output="[{'source' : 'ground','value'  : '10000'},"
						 +"{'source' : 'surface','value'  : '10000'}]";*/
				if(output != null && !output.isEmpty()){
 	    		
					JSONObject jObj = new JSONObject(output);
 	    		
					if(jObj !=null){
 	    				
						if(jObj.getString("status").equalsIgnoreCase("Success")){
							waterSourceInfo.setSafeSurfaceWaterSourceCount(jObj.getLong("safeSurfaceWaterSourceCount"));
							waterSourceInfo.setTotalGroundWaterSourceCount(jObj.getLong("totalGroundWaterSourceCount"));
							waterSourceInfo.setUnSafeGroundWaterSourceCount(jObj.getLong("unSafeGroundWaterSourceCount"));
							waterSourceInfo.setSafeGroundWaterSourceCount(jObj.getLong("safeGroundWaterSourceCount"));
							waterSourceInfo.setUnSafeSurfaceWaterSourceCount(jObj.getLong("unSafeSurfaceWaterSourceCount"));
							waterSourceInfo.setTotalSurfaceWaterSourceCount(jObj.getLong("totalSurfaceWaterSourceCount"));
						}	
	 	    				/*waterSourceInfo.setName(jObj.getString("status"));
	 	    				waterSourceInfo.setGroundWaterSourceTotalMlpdCount(jObj.getLong("groundWaterSourceCount"));
	 	    				waterSourceInfo.setSurfaceWaterSourceTotalMlpdCount(jObj.getLong("surfaceWaterSourceCount"));
	 	    				
	 	    				Long total = waterSourceInfo.getGroundWaterSourceTotalMlpdCount() + waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount();
	 	    				
	 	    				waterSourceInfo.setPercentage(calculatePercentage(total, waterSourceInfo.getGroundWaterSourceTotalMlpdCount())
	 	    						 !=null ? Double.parseDouble(calculatePercentage(total, waterSourceInfo.getGroundWaterSourceTotalMlpdCount())):0.0 );//Ground Percentage
	 	    				
	 	    				waterSourceInfo.setPercentageOne(calculatePercentage(total, waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount())
	 	    						 !=null ? Double.parseDouble(calculatePercentage(total, waterSourceInfo.getSurfaceWaterSourceTotalMlpdCount())):0.0 );//Surface Percentage
	 	    				
	 	    				waterSourceInfo.setCount(total);//total*/
					}
				}
 	    	}
 	    	
		}catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceInfo - RWSNICService service", e);
		}
		
		
		return waterSourceInfo;
	}
	/*
	 * Date : 18/06/2017
	 * Author :R Nagarjuna Gowd
	 * @description : getKeyPerformanceIndicatorsInfo
	 */
	public List<KPIVO> getKeyPerformanceIndicatorsInfo(InputVO inputVO){
		List<KPIVO> voList = new ArrayList<KPIVO>(0);
		try {
			if(inputVO!= null){
				inputVO = setFilterVal(inputVO);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getKpiDeatils");
		    String authStringEnc = getAuthenticationString("admin","admin@123");	        
		    ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
			
	      
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		if(jobj.getString("status").equalsIgnoreCase("Success")){
	 	    			if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
	 	    				buildKPIMandalResult(jobj,voList);
	 	    			}else{
	 	    				buildKPIResult(jobj,voList);
	 	    			}
	 	    		}
	 	    		
	 	    		
	 	    	}
	 	    }
		} catch (Exception e) {
			LOG.error("Exception raised at getKeyPerformanceIndicatorsInfo - RWSNICService service", e);
		}
		return voList;
	}
	
	public void buildKPIMandalResult(JSONObject jobj,List<KPIVO> voList){
		try {
			Map<Long,Map<Long,KPIVO>> finalMap = new HashMap<Long, Map<Long,KPIVO>>(0);
			
			if(jobj.getJSONArray("acheieveMentsData") != null && jobj.getJSONArray("acheieveMentsData").length() > 0){
				JSONArray acvhiementArr = jobj.getJSONArray("acheieveMentsData");
				for (int i = 0; i < acvhiementArr.length(); i++) {
					JSONArray indiArr = acvhiementArr.getJSONArray(i);
					if(finalMap.get(indiArr.getLong(5)) == null){
						KPIVO vo = new KPIVO();
						vo.setParentLocationId(indiArr.getString(5));
						vo.setLocationId(indiArr.getString(0));
						vo.setLocationName(indiArr.getString(1));
						
						if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
							vo.setQaAchivement(Long.parseLong(indiArr.getString(3)));
						}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
								|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
							vo.setPcAchivement(indiArr.getLong(3));
						}
						
						Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
						mandalMap.put(indiArr.getLong(0), vo);
						finalMap.put(indiArr.getLong(5), mandalMap);
					}else{
						Map<Long, KPIVO> mandlMap = finalMap.get(indiArr.getLong(5));
						if(mandlMap.get(indiArr.getLong(0)) == null){
							KPIVO vo = new KPIVO();
							vo.setParentLocationId(indiArr.getString(5));
							vo.setLocationId(indiArr.getString(0));
							vo.setLocationName(indiArr.getString(1));
							
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaAchivement(Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcAchivement(indiArr.getLong(3));
							}
							
							//Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
							mandlMap.put(indiArr.getLong(0), vo);
						}else{
							KPIVO vo = mandlMap.get(indiArr.getLong(0));
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaAchivement(vo.getQaAchivement()+Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcAchivement(vo.getPcAchivement()+indiArr.getLong(3));
							}
						}
					}
				}
			}
			
			
			///
			if(jobj.getJSONArray("targetData") != null && jobj.getJSONArray("targetData").length() > 0){
				JSONArray acvhiementArr = jobj.getJSONArray("targetData");
				for (int i = 0; i < acvhiementArr.length(); i++) {
					JSONArray indiArr = acvhiementArr.getJSONArray(i);
					if(finalMap.get(indiArr.getLong(5)) == null){
						KPIVO vo = new KPIVO();
						vo.setParentLocationId(indiArr.getString(5));
						vo.setLocationId(indiArr.getString(0));
						vo.setLocationName(indiArr.getString(1));
						
						if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
							vo.setQaTarget(Long.parseLong(indiArr.getString(3)));
						}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
								|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
							vo.setPcTarget(indiArr.getLong(3));
						}
						
						Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
						mandalMap.put(indiArr.getLong(0), vo);
						finalMap.put(indiArr.getLong(5), mandalMap);
					}else{
						Map<Long, KPIVO> mandlMap = finalMap.get(indiArr.getLong(5));
						if(mandlMap.get(indiArr.getLong(0)) == null){
							KPIVO vo = new KPIVO();
							vo.setParentLocationId(indiArr.getString(5));
							vo.setLocationId(indiArr.getString(0));
							vo.setLocationName(indiArr.getString(1));
							
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaTarget(Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcTarget(indiArr.getLong(3));
							}
							
							Map<Long,KPIVO> mandalMap = new HashMap<Long, KPIVO>(0);
							mandalMap.put(indiArr.getLong(0), vo);
						}else{
							KPIVO vo = mandlMap.get(indiArr.getLong(0));
							if(indiArr.getString(2).equalsIgnoreCase("NSS") || indiArr.getString(2).equalsIgnoreCase("NC")){
								vo.setQaTarget(vo.getQaTarget()+Long.parseLong(indiArr.getString(3)));
							}else if(indiArr.getString(2).equalsIgnoreCase("PC1") || indiArr.getString(2).equalsIgnoreCase("PC2") 
									|| indiArr.getString(2).equalsIgnoreCase("PC3") || indiArr.getString(2).equalsIgnoreCase("PC4")){
								vo.setPcTarget(vo.getPcTarget()+indiArr.getLong(3));
							}
						}
					}
				}
			}
			
			if(finalMap != null && finalMap.size() > 0){
				for (Entry<Long, Map<Long, KPIVO>> dist : finalMap.entrySet()) {
					Map<Long, KPIVO>  distMap = dist.getValue();
					if(distMap != null && distMap.size() > 0){
						for (Entry<Long, KPIVO> mandalMap : distMap.entrySet()) {
							voList.add(mandalMap.getValue());
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at buildKPIMandalResult - RWSNICService service", e);
		}
	}
	
	public void buildKPIResult(JSONObject jobj,List<KPIVO> voList){
		try {
			Map<String,KPIVO> finalMap = new HashMap<String, KPIVO>(0);
	  		
	  		if(jobj.getJSONArray("acheieveMentsData") != null && jobj.getJSONArray("acheieveMentsData").length() > 0){
	  			JSONArray acvhiementArr = jobj.getJSONArray("acheieveMentsData");
	  			for (int i = 0; i < acvhiementArr.length(); i++) {
						JSONArray indiArr = acvhiementArr.getJSONArray(i);
						if(finalMap.get(indiArr.get(0).toString()) == null){
							KPIVO vo = new KPIVO();
							vo.setLocationId(indiArr.get(0).toString());
							vo.setLocationName(indiArr.get(1).toString());
							if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
								vo.setQaAchivement(Long.parseLong(indiArr.get(3)+""));
							}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
									|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
								vo.setPcAchivement(Long.parseLong(indiArr.get(3)+""));
							}
							finalMap.put(indiArr.get(0).toString(),vo);
						}else{
							KPIVO vo = finalMap.get(indiArr.get(0).toString());
							if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
								vo.setQaAchivement(vo.getQaAchivement()+Long.parseLong(indiArr.get(3)+""));
							}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
									|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
								vo.setPcAchivement(vo.getPcAchivement()+Long.parseLong(indiArr.get(3)+""));
							}
						}
					}
	  		}
	  		
	  		if(jobj.getJSONArray("targetData") != null && jobj.getJSONArray("targetData").length() > 0){
	  			JSONArray targetArr = jobj.getJSONArray("targetData");
	  			for (int i = 0; i < targetArr.length(); i++) {
						JSONArray indiArr = targetArr.getJSONArray(i);
						if(finalMap.get(indiArr.get(0).toString()) == null){
							KPIVO vo = new KPIVO();
							vo.setLocationId(indiArr.get(0).toString());
							vo.setLocationName(indiArr.get(1).toString());
							if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
								vo.setQaTarget(Long.parseLong(indiArr.get(3)+""));
							}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
									|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
								vo.setPcTarget(Long.parseLong(indiArr.get(3)+""));
							}
							finalMap.put(indiArr.get(0).toString(),vo);
						}else{
							KPIVO vo = finalMap.get(indiArr.get(0).toString());
							if(indiArr.get(2).toString().equalsIgnoreCase("NSS") || indiArr.get(2).toString().equalsIgnoreCase("NC")){
								vo.setQaTarget(vo.getQaTarget()+Long.parseLong(indiArr.get(3)+""));
							}else if(indiArr.get(2).toString().equalsIgnoreCase("PC1") || indiArr.get(2).toString().equalsIgnoreCase("PC2") 
									|| indiArr.get(2).toString().equalsIgnoreCase("PC3") || indiArr.get(2).toString().equalsIgnoreCase("PC4")){
								vo.setPcTarget(vo.getPcTarget()+Long.parseLong(indiArr.get(3)+""));
							}
						}
					}
	  		}
	  		
	  		if(finalMap != null && finalMap.size() > 0){
	  			for (Entry<String, KPIVO> entry : finalMap.entrySet()) {
						if(entry.getValue().getPcTarget() > 0l){
							entry.getValue().setPcPercentage((entry.getValue().getPcAchivement()*100.00)/entry.getValue().getPcTarget());
						}
						
						if(entry.getValue().getQaTarget() > 0l){
							entry.getValue().setQaPercentage((entry.getValue().getQaAchivement()*100.00)/entry.getValue().getQaTarget());
						}
					}
	  			voList.addAll(finalMap.values());
	  		}
			
		} catch (Exception e) {
			LOG.error("Exception raised at buildKPIResult - RWSNICService service", e);
		}

	}
	
	/*
	 * Date : 18/06/2017
	 * Author :Nagarjuna
	 * @description : getStressedHabitationsInfoByLocationType
	 */
	public StatusVO getStressedHabitationsInfoByLocationType(InputVO vo) {
		StatusVO statusVO = new StatusVO();					
		try{
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getStressedHabitationInfoInALocation");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
			
			
			if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
 	    	if(output != null && !output.isEmpty()){
 	    		
 	    		JSONObject maonJObj = new JSONObject(output);
 	    		
 	    		if(!maonJObj.getString("status").equalsIgnoreCase("Failure")){
 	    			statusVO.setId(maonJObj.getLong("locationId"));
 	 	    		statusVO.setName(maonJObj.getString("locationName"));
 	 	    		
 	 	    		JSONArray statusArray = maonJObj.getJSONArray("statusList");
 	 	    		
 	 	    		if(statusArray!=null && statusArray.length()>0){
 	 	    			List<StatusVO> tempList =getStatusSkeleton();
 	 	    			for(int i=0;i<statusArray.length();i++){
 	 	    				JSONObject jObj = (JSONObject) statusArray.get(i);
 	 	    				StatusVO subVO = getMatchedStatusVO(tempList,jObj.getString("status"));
 	 	    				if(subVO != null){
 	 	    					subVO.setCount(jObj.getLong("count"));//All Habs
 	 	    					subVO.setStressedCount(jObj.getLong("stressedHabitationCount"));
 	 	    					subVO.setPercentage(jObj.getDouble("percentage"));
 	 	    				}
 	 	    			}
 	 	    			statusVO.getStatusList().addAll(tempList);
 	 	    		}
 	    		}
 	    		
 	    	}
 	    	 
 	    	  
 	      }
			
		}catch (Exception e) {
			LOG.error("Exception raised at getStressedHabitationsInfoByLocationType - RuralWaterSupplyDashBoardService service", e);
		}

		return statusVO;
	}
	
	public List<StatusVO> getPlanofActionForStressedHabitations(InputVO vo){
		List<StatusVO> statusVOList = new ArrayList<StatusVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getStressedKPIDeatils");	        
		     String authStringEnc = getAuthenticationString("admin","admin@123");	        
		     ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
			
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jobj = new JSONObject(output);
	 	    		if(jobj.getString("status").equalsIgnoreCase("Success")){
	 	    			 Map<String,StatusVO> locationsMap = new HashMap<String, StatusVO>(0);
	 	    			if(jobj.getJSONArray("stressedHabtationTargetData") != null && jobj.getJSONArray("stressedHabtationTargetData").length() > 0){
	 	    				JSONArray targetArr = jobj.getJSONArray("stressedHabtationTargetData");
	 	    				for(int i=0;i<targetArr.length();i++){
	 	    					JSONArray indiArr = targetArr.getJSONArray(i);
	 	    					StatusVO locationVO = null;
	 	    					if(locationsMap.get(indiArr.getString(0)) == null){
	 	    						locationVO = new StatusVO();
	 	    						locationVO.setStatus(indiArr.getString(0));
	 	    						locationVO.setName(indiArr.getString(1));
	 	    						locationVO.setTarget(indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setTargetPopulation(indiArr.getLong(4));
	 	    						locationsMap.put(indiArr.getString(0),locationVO);
	 	    					}else{
	 	    						locationVO = locationsMap.get(indiArr.getString(0));
	 	    						locationVO.setTarget(locationVO.getTarget()+indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setTargetPopulation(locationVO.getTargetPopulation()+indiArr.getLong(4));
	 	    					}
	 	    					
	 	    				}
	 	    			}
	 	    			
	 	    			if(jobj.getJSONArray("stressedHabtationAcheieveMentsData") != null && jobj.getJSONArray("stressedHabtationAcheieveMentsData").length() > 0){
	 	    				JSONArray achivemntArr = jobj.getJSONArray("stressedHabtationAcheieveMentsData");
	 	    				for(int i=0;i<achivemntArr.length();i++){
	 	    					JSONArray indiArr = achivemntArr.getJSONArray(i);
	 	    					StatusVO locationVO = null;
	 	    					if(locationsMap.get(indiArr.getString(0)) == null){
	 	    						locationVO = new StatusVO();
	 	    						locationVO.setStatus(indiArr.getString(0));
	 	    						locationVO.setName(indiArr.getString(1));
	 	    						locationVO.setAchived(indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setAchivedPopulation(indiArr.getLong(4));
	 	    						locationsMap.put(indiArr.getString(0),locationVO);
	 	    					}else{
	 	    						locationVO = locationsMap.get(indiArr.getString(0));
	 	    						locationVO.setAchived(locationVO.getAchived()+indiArr.getLong(3));
	 	    						if(vo.getLocationType().equalsIgnoreCase("state"))
	 	    							locationVO.setAchivedPopulation(locationVO.getAchivedPopulation()+indiArr.getLong(4));
	 	    					}
	 	    					
	 	    				}
	 	    			}
	 	    			
	 	    			if(locationsMap != null && locationsMap.size() > 0){
	 	    				for (Entry<String, StatusVO> entry : locationsMap.entrySet()) {
								Long totalHab = entry.getValue().getTarget()+entry.getValue().getAchived(),
										totalPop = entry.getValue().getTargetPopulation()+entry.getValue().getAchivedPopulation();
								
								entry.getValue().setPercentageOne((entry.getValue().getTarget()*100.00)/totalHab);
								entry.getValue().setAchivedHabPerc((entry.getValue().getAchived()*100.00)/totalHab);
								entry.getValue().setTargetPopPerc((entry.getValue().getTargetPopulation()*100.00)/totalPop);
								entry.getValue().setAchivedPopPerc((entry.getValue().getAchivedPopulation()*100.00)/totalPop);
							}
	 	    				statusVOList.addAll(locationsMap.values());
	 	    			}
	 	    		}
	 	    	}

	 	      }
		} catch (Exception e) {
			LOG.error("Exception raised at getPlanofActionForStressedHabitations - RWSNICService service", e);
		}
		return statusVOList;
	}
	
	public List<LocationVO> getLocationWiseAlertStatusCounts(InputVO inputVO){
		List<LocationVO> voList = new ArrayList<LocationVO>(0);
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getLocationWiseAlertStatusCounts");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getLocationWiseAlertStatusCounts");
		     ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, inputVO);
		     
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				LocationVO locationVO = new LocationVO();
	 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
	 	    				
	 	    				locationVO.setLocationLevelId(jobj.getLong("scopeValue"));
	 	    				locationVO.setLocationId(jobj.getLong("id"));
	 	    				locationVO.setLocationName(jobj.getString("name"));
	 	    				
	 	    				JSONArray statusArr = jobj.getJSONArray("list");
	 	    				if(statusArr != null && statusArr.length() > 0){
	 	    					for (int j = 0; j < statusArr.length(); j++) {
									StatusVO statusVO = new StatusVO();
									JSONObject jobj1 = (JSONObject) statusArr.get(j);
									statusVO.setId(jobj1.getLong("id"));
									statusVO.setName(jobj1.getString("name"));
									statusVO.setCount(jobj1.getLong("count"));
									locationVO.getStatusList().add(statusVO);
								}
	 	    				}
	 	    				
	 	    				voList.add(locationVO);
	 	    			}
	 	    		}
	 	    	}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseAlertStatusCounts - RWSNICService service", e);
		}
		return voList;
	}
	
	public List<RangeVO> getLocationBasedOnSelection(InputVO inputVO){
		List<RangeVO> rangeVOList = new ArrayList<RangeVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getLocationBasedOnSelection");
	        
			String authStringEnc = getAuthenticationString("admin","admin@123");
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	    }else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);
					for(int i=0;i<finalArray.length();i++){
						RangeVO vo = new RangeVO();
						JSONObject jobj = (JSONObject)finalArray.get(i);
						
						vo.setId(jobj.getLong("locationId"));
						vo.setName(jobj.getString("locationName"));
						
						rangeVOList.add(vo);
					}
				}
	 	    }
					
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationBasedOnSelection - RWSNICService service", e);
		}
		return rangeVOList;
	}
	
	public String getAuthenticationString(String name,String password){
		try {			
	        String authString = name + ":" + password;
	        return new BASE64Encoder().encode(authString.getBytes());
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAuthenticationString - RWSNICService service", e);
		}
		return null;
	}
	@Override
	public List<StatusVO> getHamletWiseIvrCounts(InputVO vo) {
		List<StatusVO> voList = new ArrayList<StatusVO>(0);
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getHamletWiseIvrStatusCounts");
			//WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://192.168.11.143:8080/PartyAnalyst/WebService/getHamletWiseIvrStatusCounts");
	        
	        /*String jsonInString = new ObjectMapper().writeValueAsString(vo);
	        System.out.println(jsonInString);*/
	        
	        ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
	        
	        if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);//Type Array
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				StatusVO statusVo = new StatusVO();
	 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
	 	    				
	 	    				statusVo.setId(jobj.getLong("id"));
	 	    				statusVo.setName(jobj.getString("name"));
	 	    				
	 	    				JSONArray subListArr  = jobj.getJSONArray("subList1"); // Color Array
	 	    				
	 	    				if(subListArr!=null && subListArr.length()>0){
	 		 	    			for(int j=0;j<subListArr.length();j++){
	 		 	    				
	 		 	    				JSONObject colorJobj = (JSONObject)subListArr.get(j);
	 		 	    				 StatusVO colorVO = new StatusVO();
	 		 	    				colorVO.setName(colorJobj.getString("name"));
	 		 	    				colorVO.setCount(colorJobj.getLong("count"));
	 		 	    				colorVO.setPercentage(colorJobj.getDouble("percentage"));
	 		 	    				statusVo.getStatusList().add(colorVO);
	 		 	    				
	 		 	    			}
	 		 	    		}	 	    				
	 	    				voList.add(statusVo);
	 	    			}
	 	    		}
	 	    	}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getHamletWiseIvrCounts - RWSNICService service", e);
		}
		return voList;
	}
	
	public String calculatePercentage(Long total,Long count)
	{
		try{
			if(total != null && total.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/total.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
	/*
	 * Date : 30/06/2017
	 * Author :Balu
	 * @description : getStressedHabitationsInfoByLocationType
	 */
	public List<AmsVO> getAlertsOfCategoryByStatusWise(InputVO vo) {
		List<AmsVO> finalList = new ArrayList<AmsVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getAlertsOfCategoryByStatusWise");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray finalArray = new JSONArray(output);//Type Array
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				AmsVO amsVo = new AmsVO();
	 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
	 	    				
	 	    				amsVo.setId(jobj.getLong("id"));
	 	    				amsVo.setTitle(jobj.getString("title"));
	 	    				amsVo.setAlertLevel(jobj.getString("alertLevel"));//impact Scope
	 	    				amsVo.setCreatedDate(jobj.getString("createdDate"));
	 	    				amsVo.setUpdatedDate(jobj.getString("updatedDate"));
	 	    				amsVo.setStatusId(jobj.getLong("statusId"));
	 	    				amsVo.setStatus(jobj.getString("status"));
	 	    				amsVo.setSevertyColor(jobj.getString("severtyColor"));
	 	    				amsVo.setStatusColor(jobj.getString("statusColor"));
	 	    				amsVo.setSource(jobj.getString("source"));
	 	    				amsVo.setOfficer(jobj.getString("problem"));
	 	    				finalList.add(amsVo);
	 	    				
	 	    			}
	 	    		}
				}
				
 	      	}
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAlertsOfCategoryByStatusWise() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickWorkDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickWorkDetails");
			String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    				
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("districtName"));
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));		 	    				
		 	    				subVo.setSacntionedAmount(jobj.getString("sacntionedAmount"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				if(jobj.has("completionDate")){
		 	    					subVo.setCompletionDate(jobj.getString("completionDate"));
		 	    				}
		 	    				if(jobj.has("targetDate")){
		 	    					subVo.setCompletionDate(jobj.getString("targetDate"));
		 	    				}
		 	    				if(jobj.has("commssionedDate")){
		 	    					subVo.setCompletionDate(jobj.getString("commssionedDate"));
		 	    				}
		 	    				if(jobj.has("groundingDate")){
		 	    					subVo.setGroundingDate(jobj.getString("groundingDate"));
		 	    				}
		 	    				subVo.setWorkName(jobj.getString("workName"));		 	    				
		 	    				subVo.setWorkId(jobj.getString("workId"));
		 	    				//subVo.setSourceCount(jobj.getString("sourceCount"));
		 	    				
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}
						
					}
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getOnclickWorkDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickTargetsAcheievementsDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickTargetsAcheievementsDetails");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    				
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("districtName"));
		 	    				//subVo.setSourceCount(jobj.getString("sourceCount"));
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				subVo.setTotalCount(jobj.getString("totalCount"));
    				
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}
						
					}
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getOnclickTargetsAcheievementsDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickStressedTargetsAcheievementsDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickStressedTargetsAcheievementsDetails");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    		
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
		 	    				subVo.setToatlPorpualtionCovered(jobj.getString("toatlPorpualtionCovered"));
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}						
					}
					
					
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getOnclickStressedTargetsAcheievementsDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getOnclickHabitationsupplyDetails(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickHabitationsupplyDetails");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONObject jsonObj = new JSONObject(output);
					
					if(jsonObj.getString("status") !="null" && !jsonObj.getString("status").trim().isEmpty() && 
							jsonObj.getString("status").trim().equalsIgnoreCase("Success")){
						
						JSONArray onClickWorksArray =  jsonObj.getJSONArray("onClickWorksList");//Type Array
						
		 	    		if(onClickWorksArray!=null && onClickWorksArray.length()>0){
		 	    			for(int i=0;i<onClickWorksArray.length();i++){
		 	    				
		 	    				RwsClickVO subVo = new RwsClickVO();
		 	    				
		 	    				JSONObject jobj = (JSONObject)onClickWorksArray.get(i);
		 	    		
		 	    				subVo.setMandalName(jobj.getString("mandalName"));
		 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));		 	    				
		 	    				subVo.setDistrictCode(jobj.getString("districtCode"));
		 	    				subVo.setDistrictName(jobj.getString("districtName"));
		 	    				subVo.setLpcd(jobj.getString("lpcd"));
		 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
		 	    				subVo.setMandalCode(jobj.getString("mandalCode"));		 	    			
		 	    				subVo.setHabitationName(jobj.getString("habitationName"));
		 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
		 	    				
		 	    				finalList.add(subVo);
		 	    			}
		 	    		}
					}
					
					
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getOnclickHabitationsupplyDetails() method, Exception - ",e);
		}
		return finalList;
	}
	//01-07-2017 
	
	public List<RwsClickVO> getSchemeDetailsByTypeOfAssestName(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getSchemeDetailsByTypeOfAssestName");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    			
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName"));
			 	    				if(i==0)
			 	    					subVo.setTotalCount(jobj.getString("totalCount"));
			 	    				subVo.setWorkId(jobj.getString("workId"));
			 	    				subVo.setWorkName(jobj.getString("workName"));
			 	    				subVo.setAssetType(jobj.getString("assestType"));
			 	    				
			 	    				finalList.add(subVo);
			 	    			}
		 	    				
		 	    			}
		 	    			
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getSchemeDetailsByTypeOfAssestName() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getAssetDetailsByAssetType(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getAssetDetailsByAssetType");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    			
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName")); 	    
			 	    				subVo.setCoverageStatus(jobj.getString("coverageStatus"));	
			 	    				if(i == 0 && jobj.has("totalCount")){
			 	    					subVo.setTotalCount(jobj.getString("totalCount"));
			 	    				}
			 	    				subVo.setAssestCode(jobj.getString("assestCode"));
			 	    				subVo.setAssestName(jobj.getString("assestName"));
			 	    				subVo.setAssestCost(jobj.getString("assestCost"));
			 	    				subVo.setAssetType(jobj.getString("assestType"));
			 	    				
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAssetDetailsByAssetType() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getHabitationDetailsByStatusByLocationType(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getHabitationDetailsByStatusByLocationType");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					
					JSONArray finalArray = new JSONArray(output);
					
				
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){
		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){
			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();
			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    				
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName")); 	    
			 	    				subVo.setCoverageStatus(jobj.getString("coverageStatus"));			 	    				
			 	    				
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}
					
				}
				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAssetDetailsByAssetType() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<RwsClickVO> getWaterSourceDeatilsLocationWise(InputVO vo){
		List<RwsClickVO> finalList = new ArrayList<RwsClickVO>();
		try {
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getWaterSourceDeatilsLocationWise");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, vo);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){					
					JSONArray finalArray = new JSONArray(output);
					
		 	    		if(finalArray!=null && finalArray.length()>0){
		 	    			
		 	    			JSONObject firstObj = (JSONObject)finalArray.get(0);
		 	    			
		 	    			if(firstObj.getString("status") !="null" && !firstObj.getString("status").trim().isEmpty() && 
									firstObj.getString("status").trim().equalsIgnoreCase("Success")){		 	    				
		 	    				for(int i=0;i<finalArray.length();i++){			 	    				
			 	    				RwsClickVO subVo = new RwsClickVO();			 	    				
			 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
			 	    				
			 	    		        subVo.setDistrictCode(jobj.getString("districtCode"));
			 	    				subVo.setDistrictName(jobj.getString("districtName"));
			 	    				subVo.setConstituencyCode(jobj.getString("constituencyCode"));
			 	    				subVo.setConstituencyName(jobj.getString("constituencyName"));
			 	    				subVo.setMandalCode(jobj.getString("mandalCode"));	
			 	    				subVo.setMandalName(jobj.getString("mandalName"));  
			 	    				subVo.setHabitationCode(jobj.getString("habitationCode"));
			 	    				subVo.setHabitationName(jobj.getString("habitationName"));			 	    				
			 	    				subVo.setAssestCode(jobj.getString("assestCode"));
			 	    				finalList.add(subVo);
			 	    			}		 	    				
		 	    			}		 	    		
		 	    		}				
				}				
 	      	}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getWaterSourceDeatilsLocationWise() method, Exception - ",e);
		}
		return finalList;
	}
	
	public List<KeyValueVO> getAllPrrwsDistricts(){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Object[]> districts = districtDAO.getAllDistricts();
			
			if(districts != null && districts.size() > 0){
				for (Object[] objects: districts) {
					KeyValueVO vo = new KeyValueVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllPrrwsDistricts() method, Exception - ",e);
		}
		return voList;
	}
	
	public List<KeyValueVO> getConstituenciesForDistrict(IdNameVO idNameVO){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Object[]> constituencies = constituencyDAO.getConstituencies(idNameVO.getId());
			
			if(constituencies != null && constituencies.size() > 0){
				for (Object[] objects : constituencies) {
					KeyValueVO vo = new KeyValueVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getConstituenciesForDistrict() method, Exception - ",e);
		}
		return voList;
	}
	
	public List<KeyValueVO> getTehsilsForConstituency(IdNameVO idNameVO){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Object[]> tehsils = tehsilDAO.getTehsilsForConstituency(idNameVO.getId());
			if(tehsils != null && tehsils.size() > 0){
				for (Object[] objects : tehsils) {
					KeyValueVO vo = new KeyValueVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getTehsilsForConstituency() method, Exception - ",e);
		}
		return voList;
	}
	
	public List<WaterSourceVO> getWaterSourceDeatils2(InputVO inputVO){
		List<WaterSourceVO> waterSourceVOList = new ArrayList<WaterSourceVO>(0); 
		try {
			
			if (inputVO != null) {
				inputVO = setFilterVal(inputVO);
			}
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getWaterSourceDeatils2");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
        
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				
				if(output != null && !output.isEmpty()){
					JSONArray mainArr = new JSONArray(output);
					
					if(mainArr != null && mainArr.length() > 0){
						JSONObject jobj = (JSONObject)mainArr.get(0);
						if(jobj.getString("status").equalsIgnoreCase("Success")){
							if(inputVO.getLocationType().equalsIgnoreCase("mandal")){
								buildWaterSourceMandalTableData(mainArr,waterSourceVOList);
							}else{
								buildWaterSourceTableData(mainArr,waterSourceVOList);
							}
						}
					}
				}
 	      	}
		} catch (Exception e) {
			LOG.error("Exception raised at getWaterSourceDeatils2 - RuralWaterSupplyDashBoardService service", e);
		}
		return waterSourceVOList;
	} 
	
	public void buildWaterSourceMandalTableData(JSONArray jsonArr,List<WaterSourceVO> voList){
		try {
			if(jsonArr != null && jsonArr.length() > 0){
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject mainObj = jsonArr.getJSONObject(i);
					if(mainObj.getJSONArray("subList") != null && mainObj.getJSONArray("subList").length() > 0){
						for (int j = 0; j < mainObj.getJSONArray("subList").length(); j++) {
							JSONObject jobj = mainObj.getJSONArray("subList").getJSONObject(j);
							WaterSourceVO vo = new WaterSourceVO();
							vo.setLocationId(jobj.getLong("locationId"));
							vo.setLocationName(jobj.getString("locationName"));
							vo.setSafeSurfaceWaterSourceCount(jobj.getLong("safeSurfaceCount"));
							vo.setUnSafeSurfaceWaterSourceCount(jobj.getLong("unSafeSurfaceCount"));
							vo.setSafeGroundWaterSourceCount(jobj.getLong("safeGroundCount"));
							vo.setUnSafeGroundWaterSourceCount(jobj.getLong("unSafeGroundCount"));
							vo.setTotalSurfaceWaterSourceCount(jobj.getLong("surfaceTotalCount"));
							vo.setTotalGroundWaterSourceCount(jobj.getLong("groundTotalCount"));
							vo.setParentLocationId(mainObj.getLong("locationId"));
							voList.add(vo);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at buildWaterSourceMandalTableData - RuralWaterSupplyDashBoardService service", e);
		}
	}
	
	public void buildWaterSourceTableData(JSONArray jsonArr,List<WaterSourceVO> voList){
		try {
			if(jsonArr != null && jsonArr.length() > 0){
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject jobj = (JSONObject)jsonArr.get(i);
					WaterSourceVO vo = new WaterSourceVO();
					vo.setLocationId(jobj.getLong("locationId"));
					vo.setLocationName(jobj.getString("locationName"));
					vo.setSafeSurfaceWaterSourceCount(jobj.getLong("safeSurfaceCount"));
					vo.setUnSafeSurfaceWaterSourceCount(jobj.getLong("unSafeSurfaceCount"));
					vo.setSafeGroundWaterSourceCount(jobj.getLong("safeGroundCount"));
					vo.setUnSafeGroundWaterSourceCount(jobj.getLong("unSafeGroundCount"));
					vo.setTotalSurfaceWaterSourceCount(jobj.getLong("surfaceTotalCount"));
					vo.setTotalGroundWaterSourceCount(jobj.getLong("groundTotalCount"));
					voList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at buildWaterSourceTableData - RuralWaterSupplyDashBoardService service", e);
		}
	}
	

	@Override
	public List<RwsClickVO> getHamletWiseIvrStatusList(InputVO vo) {
    List<RwsClickVO> ivraHamletList = new ArrayList<RwsClickVO>(0);
    try{
    	WebResource webResource = commonMethodsUtilService.getWebResourceObject("https://mytdp.com/WebService/getHamletWiseIvrStatusList");
    	ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, vo);
        
        if(response.getStatus() != 200){
 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      }else{
			String output = response.getEntity(String.class);
			
			if(output != null && !output.isEmpty()){
				JSONArray finalArray = new JSONArray(output);//Type Array
 	    		if(finalArray!=null && finalArray.length()>0){
 	    			for(int i=0;i<finalArray.length();i++){
 	    				RwsClickVO hamletList = new RwsClickVO();
 	    				JSONObject jobj = (JSONObject)finalArray.get(i);
 	    				
 	    				hamletList.setId(jobj.getLong("stateId"));
 	    				hamletList.setName(jobj.getString("state"));
 	    				hamletList.setDistrictCode(jobj.getString("districtId"));
 	    				hamletList.setDistrictName(jobj.getString("district"));
 	    				hamletList.setConstituencyCode(jobj.getString("constituencyId"));
 	    				hamletList.setConstituencyName(jobj.getString("constituency"));
 	    				hamletList.setMandalCode(jobj.getString("tehsilId"));
 	    				hamletList.setMandalName(jobj.getString("tehsil"));
 	    				hamletList.setPanchayatId(jobj.getLong("panchayatId"));
 	    				hamletList.setPanchayat(jobj.getString("panchayat"));
 	    				hamletList.setHabitationCode(jobj.getString("hamletId"));
 	    				hamletList.setHabitationName(jobj.getString("hamlet"));
 	    				
 	    				ivraHamletList.add(hamletList);
 	    			}
 	    		}
 	    	}
			
		}
	}catch(Exception e){
		LOG.error("Exception Occured in getHamletWiseIvrStatusList() method, Exception - ",e);
    }
		return ivraHamletList;
	}	

	public InputVO setFilterVal(InputVO inputVO){

		try{
			 String filterValue =null;
			 if(inputVO.getFilterType()!= null && inputVO.getFilterValue()!=null && inputVO.getFilterType().trim().length()>0 && 
					 inputVO.getFilterValue().trim().length()>0){
				 if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.MANDAL)){
					 filterValue = rwsTehsilDAO.getRwsCode(Long.valueOf(inputVO.getFilterValue().trim()));
					 inputVO.setFilterValue(filterValue);
				 }else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.DISTRICT)){
					 filterValue = rwsDistrictDAO.getRwsCode(Long.valueOf(inputVO.getFilterValue().trim()));
					 inputVO.setFilterValue(filterValue);
				 }else if(inputVO.getFilterType().trim().equalsIgnoreCase(IConstants.CONSTITUENCY)){
					 filterValue = rwsConstituencyDAO.getRwsCode(Long.valueOf(inputVO.getFilterValue().trim()));
					 inputVO.setFilterValue(filterValue);
				 }
				 
			 }
			 if(inputVO.getDistrictValue()!= null  && inputVO.getDistrictValue().trim().length()>0){
				 String districtValue = rwsDistrictDAO.getRwsCode(Long.valueOf(inputVO.getDistrictValue().trim()));
				 inputVO.setDistrictValue(districtValue);
			 }
			 return inputVO;
		}catch(Exception e){
			LOG.error("Exception Occured in setFilterVal() method, Exception - ",e);
			return inputVO;
		}
	}
	
	public String convertingInputVOToString(InputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromMonth() != null )
				str += "\"fromMonth\" : \""+inputVO.getFromMonth()+"\",";
			if(inputVO.getToMonth() != null)
				str += "\"toMonth\" : \""+inputVO.getToMonth()+"\",";
			if(inputVO.getLocation() != null)
				str += "\"Location\" : \""+inputVO.getLocation()+"\",";
			/*if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";else*/
			 if(inputVO.getLocationId() != null)
				str += "\"locationID\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSubLocation() != null)
				str += "\"subLocation\" : \""+inputVO.getSubLocation()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception Occured in convertingInputVOToString() method, Exception - ",e);
		}
		return str;
	}
	
	/**
	  * @param  InputVO inputVO which contain fromMonth,toMonth,location and locationId
	  * @return NregaLocationOverviewVO
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachaBharat(IHHL) Abstract And Overview Data(MGNREGSTCS Service).
	  * @since 29/7/19
	  */
	public NregaLocationOverviewVO getIHHLOverviewData(InputVO inputVO){
		NregaLocationOverviewVO resultVO = new NregaLocationOverviewVO();
		try {
			
			String str = convertingInputVOToString(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/Home/Overview_IHHLData", str);
			
		    if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray abstArr = jsonObject.getJSONArray("MinisterDashBoardStateOverView");
	 	    		JSONArray overViewArr = jsonObject.getJSONArray("MinisterDashBoardLocOverView");
	 	    		//Abstarct
	 	    		if(abstArr != null && abstArr.length() > 0){
	 	    			resultVO.setSubList1(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<abstArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) abstArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIHHLAbstractData(jObj);
	 	    				resultVO.getSubList1().add(vo);
	 	    			}	
	 	    		}
	 	    		//OverView Data
	 	    		if(overViewArr != null && overViewArr.length() > 0){
	 	    			resultVO.setSubList2(new ArrayList<NregaLocationOverviewVO>());
	 	    			for(int i=0;i<overViewArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) overViewArr.get(i);
	 	    				NregaLocationOverviewVO vo = getIHHLOverviewData(jObj);
	 	    				resultVO.getSubList2().add(vo);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getIHHLOverviewData() method, Exception - ",e);
		}
		return resultVO;
	}
	public NregaLocationOverviewVO getIHHLAbstractData(JSONObject jObj){
		NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
		try {
			    vo.setTarget(jObj.getLong("TARGET"));
				vo.setGrounded(jObj.getLong("GROUNDED"));
				vo.setNoTGrounded(jObj.getLong("NOT_GROUNDED"));
				vo.setCompleted(jObj.getLong("COMPLETED"));
				vo.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		} catch(Exception e){
			LOG.error("Exception Occured in getIHHLAbstractData() method, Exception - ",e);
		}
		return vo;
	}
	public NregaLocationOverviewVO getIHHLOverviewData(JSONObject jObj){
		NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
		try {
			    vo.setType(jObj.getString("TYPE"));
				vo.setGreen(jObj.getLong("GREEN"));
				vo.setRed(jObj.getLong("RED"));
				vo.setOrange(jObj.getLong("ORANGE"));
				vo.setTotal(jObj.getLong("TOTAL"));
		} catch(Exception e){
			LOG.error("Exception Occured in getIHHLOverviewData() method, Exception - ",e);
		}
		return vo;
	}
	/**
	  * @param  InputVO inputVO which contain fromMonth,toMonth,location,locationId and subLocation
	  * @return List<NregaLocationOverviewVO>
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachhABharat(IHHL) location Level wise details(MGNREGSTCS Service).
	  * @since 29/7/19
	  */
	public List<NregaLocationOverviewVO> getIHHLlocationLvlWiseData(InputVO inputVO){
		List<NregaLocationOverviewVO> resultList = new ArrayList<NregaLocationOverviewVO>(0);
		try {
			
			String str = convertingInputVOToString(inputVO);
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/values/Location_IHHLData", str);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray locationDtlsArr = jsonObject.getJSONArray("MinisterDashBoardLocationData");
	 	    		if(locationDtlsArr != null && locationDtlsArr.length() > 0){
	 	    			for(int i=0;i<locationDtlsArr.length();i++){
	 	    				NregaLocationOverviewVO locationVO = new NregaLocationOverviewVO();
	 	    				JSONObject jObj = (JSONObject) locationDtlsArr.get(i);
	 	    				locationVO.setUniqueId(jObj.getString("UNIQUEID"));
	 	    				locationVO.setDistrict(jObj.getString("DISTRICT"));
	 	    				locationVO.setConstituency(jObj.getString("CONSTITUENCY"));
	 	    				locationVO.setMandal(jObj.getString("MANDAL"));
	 	    				locationVO.setPanchayt(jObj.getString("PANCHAYAT"));
	 	    				locationVO.setTarget(jObj.getLong("TARGET"));
	 	    				locationVO.setGrounded(jObj.getLong("GROUNDED"));
	 	    				locationVO.setNoTGrounded(jObj.getLong("NOT_GROUNDED"));
	 	    				locationVO.setCompleted(jObj.getLong("COMPLETED"));
	 	    				//locationVO.setPercentage(new BigDecimal(jObj.getString("PERC")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				if(locationVO.getCompleted() != null && locationVO.getCompleted().longValue() > 0L && locationVO.getTarget() != null && locationVO.getTarget().longValue() > 0L)
	 	    					locationVO.setPercentage(new BigDecimal(locationVO.getCompleted()*100.0/locationVO.getTarget()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	 	    				else
	 	    					locationVO.setPercentage("0.00");
	 	    				resultList.add(locationVO);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getIHHLlocationLvlWiseData() method, Exception - ",e);
		}
		return resultList;
	}
	/**
	  * @param  InputVO inputVO which contain fromDate,toDate,location,locationId and subLocationType
	  * @return NregaLocationOverviewVO
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachaBharat(Payments) Abstract(MGNREGSTCS Service).
	  * @since 30/08/19
	  */
	public NregaLocationOverviewVO getSBPaymentsAbstract(InputVO inputVO){
		NregaLocationOverviewVO resultVO = new NregaLocationOverviewVO();
		try {
			
			String str = cvertingIpVOToStringFrSBPayments(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/PayOverview/GetPayOverviewDetails", str);
			
		    if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray abstArr = jsonObject.getJSONArray("PayOverviewDetailsData");
	 	    		//JSONArray overViewArr = jsonObject.getJSONArray("MinisterDashBoardLocOverView");
	 	    		//Abstarct
	 	    		if(abstArr != null && abstArr.length() > 0){
	 	    			for(int i=0;i<abstArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) abstArr.get(i);
		 	    				resultVO.setTotalFTO(jObj.getString("TOTAL_FTOS"));
		 	    				resultVO.setTotalAmount(convertRupeesIntoLakhes(jObj.getString("TOTAL_AMOUNT")));
		 	    				resultVO.setPaidFTO(jObj.getString("PAID_FTOS"));
		 	    				resultVO.setPaidAmount(convertRupeesIntoLakhes(jObj.getString("PAID_AMOUNT")));
		 	    				resultVO.setPendingFTO(jObj.getString("YETTOBE_PAID_FTOS"));
		 	    				resultVO.setPendingAmount(convertRupeesIntoLakhes(jObj.getString("YETTOBE_PAID_AMOUNT")));
		 	    				
		 	    				resultVO.setTtlAmt(jObj.getDouble("TOTAL_AMOUNT"));
		 	    				resultVO.setPaidAmt(jObj.getDouble("PAID_AMOUNT"));
		 	    				resultVO.setPndgAmt(jObj.getDouble("YETTOBE_PAID_AMOUNT"));
		 	    				resultVO.setTtlFTO(jObj.getDouble("TOTAL_FTOS"));
		 	    				resultVO.setPaiidFTO(jObj.getDouble("PAID_FTOS"));
		 	    				resultVO.setPndgFTO(jObj.getDouble("YETTOBE_PAID_FTOS"));
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getSBPaymentsAbstract() method, Exception - ",e);
		}
		return resultVO;
	}
	
	public String cvertingIpVOToStringFrSBPayments(InputVO inputVO){
		String str = "";
		try {
			/*if(inputVO.getLocationId() != null)
				if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("district")){
					if(inputVO.getLocationId().longValue() > 0l && inputVO.getLocationId().longValue() <= 9l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
					if(inputVO.getLocationId().longValue() > 0l)
						inputVO.setLocationIdStr("0"+inputVO.getLocationId().toString());
				}*/
				
			str = "{";
			
			if(inputVO.getFromDate() != null )
				str += "\"FROMDATE\" : \""+inputVO.getFromDate()+"\",";
			if(inputVO.getToDate() != null)
				str += "\"TODATE\" : \""+inputVO.getToDate()+"\",";
			if(inputVO.getLocation() != null)
				str += "\"Location\" : \""+inputVO.getLocation()+"\",";
			/*if(inputVO.getLocationIdStr() != null)
				str += "\"locationId\" : \""+inputVO.getLocationIdStr()+"\",";else*/
			 if(inputVO.getLocationId() != null)
				str += "\"LocationID\" : \""+inputVO.getLocationId()+"\",";
			if(inputVO.getSubLocation() != null)
				str += "\"SUBLOCATION\" : \""+inputVO.getSubLocation()+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception Occured in cvertingIpVOToStringFrSBPayments() method, Exception - ",e);
		}
		return str;
	}
	public String convertRupeesIntoLakhes(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				returnVal = returnVal+" L";
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhes - NREGSTCSService service", e);
		}
		return returnVal;
	}
	
	public String convertRupeesIntoLakhesStr(String value){
		String returnVal = null;
		try {
			if(value != null){
				returnVal = new BigDecimal(Long.valueOf(value)/100000.00).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				//returnVal = returnVal;
			}
		} catch (Exception e) {
			LOG.error("Exception raised at convertRupeesIntoLakhes - NREGSTCSService service", e);
		}
		return returnVal;
	}
	
	/**
	  * @param  InputVO inputVO which contain fromDate,toDate,location,locationId and subLocationType
	  * @return List<NregaLocationOverviewVO>
	  * @author Nandhini 
	  * @Description :This Service Method is used to get SwachaBharat(Payments) LevelsData(MGNREGSTCS Service).
	  * @since 30/08/19
	  */
	public List<NregaLocationOverviewVO> getSBPaymentsLevelsWiseData(InputVO inputVO){
		List<NregaLocationOverviewVO> returnList = new ArrayList<NregaLocationOverviewVO>();
		try {
			
			String str = cvertingIpVOToStringFrSBPayments(inputVO);
			
			ClientResponse response = webServiceUtilService.callWebService("http://125.17.121.167/rwsapwebapi/api/PayOverview/GetPayOverviewDetails", str);
			
		    if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONObject jsonObject = new JSONObject(output);
	 	    		JSONArray dataArr = jsonObject.getJSONArray("PayOverviewDetailsData");
	 	    		if(dataArr != null && dataArr.length() > 0){
	 	    			for(int i=0;i<dataArr.length();i++){
	 	    				JSONObject jObj = (JSONObject) dataArr.get(i);
	 	    				NregaLocationOverviewVO vo = new NregaLocationOverviewVO();
	 	    				if(inputVO.getSubLocation() != null && !inputVO.getSubLocation().trim().equalsIgnoreCase("state")){
	 	    					vo.setDistrictId(jObj.getString("DID"));
	 	    					vo.setDistrict(jObj.getString("DNAME"));
	 	    				}
	 	    				if(inputVO.getSubLocation() != null && !(inputVO.getSubLocation().trim().equalsIgnoreCase("state") || inputVO.getSubLocation().trim().equalsIgnoreCase("district"))){
	 	    					vo.setConstituencyId(jObj.getString("ACODE"));
	 	    					vo.setConstituency(jObj.getString("ANAME"));
	 	    				}
	 	    				if(inputVO.getSubLocation() != null && !(inputVO.getSubLocation().trim().equalsIgnoreCase("state") || inputVO.getSubLocation().trim().equalsIgnoreCase("district") || inputVO.getSubLocation().trim().equalsIgnoreCase("constituency"))){
	 	    					vo.setMandalId(jObj.getString("MID"));
	 	    					vo.setMandal(jObj.getString("MNAME"));
	 	    				}
	 	    				if(inputVO.getSubLocation() != null && inputVO.getSubLocation().trim().equalsIgnoreCase("panchayat")){
	 	    					vo.setPanchayatId(jObj.getString("PID"));
	 	    					vo.setPanchayt(jObj.getString("GRAMPANCHAYATNAMEENG"));
	 	    				}
	 	    				
	 	    				if(inputVO.getSubLocation() != null && (inputVO.getSubLocation().trim().equalsIgnoreCase("state") || inputVO.getSubLocation().trim().equalsIgnoreCase("district") || inputVO.getSubLocation().trim().equalsIgnoreCase("constituency"))){
	 	    					vo.setTotalAmount(convertRupeesIntoLakhesStr(jObj.getString("TOTAL_AMOUNT")));
	 	    					vo.setPaidAmount(convertRupeesIntoLakhesStr(jObj.getString("PAID_AMOUNT")));
	 	    					vo.setPendingAmount(convertRupeesIntoLakhesStr(jObj.getString("YETTOBE_PAID_AMOUNT")));
	 	    				}
	 	    				else{
	 	    					vo.setTotalAmount(jObj.getString("TOTAL_AMOUNT"));
	 	    					vo.setPaidAmount(jObj.getString("PAID_AMOUNT"));
	 	    					vo.setPendingAmount(jObj.getString("YETTOBE_PAID_AMOUNT"));
	 	    				}
	 	    				vo.setTotalFTO(jObj.getString("TOTAL_FTOS"));
	 	    				vo.setPaidFTO(jObj.getString("PAID_FTOS"));
	 	    				vo.setPendingFTO(jObj.getString("YETTOBE_PAID_FTOS"));
	 	    				/*
	 	    				if(jObj.getString("TOTAL_AMOUNT") != null && jObj.getString("TOTAL_AMOUNT").length() >= 6)
	 	    					vo.setTotalAmount(convertRupeesIntoLakhesStr(jObj.getString("TOTAL_AMOUNT")));
	 	    				else
	 	    					vo.setTotalAmount(jObj.getString("TOTAL_AMOUNT"));
	 	    				vo.setPaidFTO(jObj.getString("PAID_FTOS"));
	 	    				if(jObj.getString("PAID_AMOUNT") != null && jObj.getString("PAID_AMOUNT").length() >= 6)
	 	    					vo.setPaidAmount(convertRupeesIntoLakhesStr(jObj.getString("PAID_AMOUNT")));
	 	    				else
	 	    					vo.setPaidAmount(jObj.getString("PAID_AMOUNT"));
	 	    				vo.setPendingFTO(jObj.getString("YETTOBE_PAID_FTOS"));
	 	    				if(jObj.getString("YETTOBE_PAID_AMOUNT") != null && jObj.getString("YETTOBE_PAID_AMOUNT").length() >= 6)
	 	    					vo.setPendingAmount(convertRupeesIntoLakhesStr(jObj.getString("YETTOBE_PAID_AMOUNT")));
	 	    				else
	 	    					vo.setPendingAmount(jObj.getString("YETTOBE_PAID_AMOUNT"));
	 	    				*/
	 	    				returnList.add(vo);
	 	    			}	
	 	    		}
	 	    	}
	 	      }  
		} catch (Exception e) {
			LOG.error("Exception Occured in getSBPaymentsLevelsWiseData() method, Exception - ",e);
		}
		return returnList;
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.tpi.rws.service.IRWSNICService#getAllData()
	 */
	public void getAllData(){
		try{
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getAllHabitationDetails");
	        String authStringEnc = getAuthenticationString("itgrids","Itgrids@123");
	        InputVO inputVO = new InputVO();
	        inputVO.setYear("2017");
	        
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
        	
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
 	    			JSONArray finalArray = new JSONArray(output);
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			Habitation habitation = null;
	 	    			for(int i=0;i<0;i++){
	 	    				try{
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				if(jObj.length() > 0){
		 	    					habitation = new Habitation();
		 	    					if(jObj.has("dName")){
		 	    						habitation.setDistrictName(commonMethodsUtilService.getStringValueForObject(jObj.getString("dName")));
		 	    					}
		 	    					if(jObj.has("mName")){
		 	    						habitation.setMandalName(commonMethodsUtilService.getStringValueForObject(jObj.getString("mName")));
		 	    					}
		 	    					
		 	    					if(jObj.has("pName")){
		 	    						habitation.setPanchayatName(commonMethodsUtilService.getStringValueForObject(jObj.getString("pName")));
		 	    					}
		 	    					
		 	    					if(jObj.has("vName")){
		 	    						habitation.setVillageName(commonMethodsUtilService.getStringValueForObject(jObj.getString("vName")));
		 	    					}
		 	    					
		 	    					if(jObj.has("panchName")){
		 	    						habitation.setPanchName(commonMethodsUtilService.getStringValueForObject(jObj.getString("panchName")));
		 	    					}
		 	    					
		 	    					if(jObj.has("panchCode")){
		 	    						habitation.setPanchCode(commonMethodsUtilService.getStringValueForObject(jObj.getLong("panchCode")));
		 	    					}
		 	    					
		 	    					if(jObj.has("censusYear")){
		 	    						habitation.setCensusYear(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusYear")));
		 	    					}
		 	    					
		 	    					if(jObj.has("censusPlainPopu")){
		 	    						habitation.setCensusPlainPopulation(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusPlainPopu")));
		 	    					}
		 	    					
		 	    					if(jObj.has("censusSCPopu")){
		 	    						habitation.setCensusScPopulation(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusSCPopu")));
		 	    					}
		 	    					
		 	    					if(jObj.has("censusSTPopu")){
		 	    						habitation.setCensusStPopulation(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusSTPopu")));
		 	    					}
		 	    					
		 	    					
		 	    					habitation.setTotalPopulation(habitation.getCensusPlainPopulation()+habitation.getCensusScPopulation()+habitation.getCensusStPopulation());
		 	    					if(jObj.has("plainPopCovered")){
		 	    						habitation.setPlainPopulationCovered(commonMethodsUtilService.getLongValueForObject(jObj.getLong("plainPopCovered")));
		 	    					}
		 	    					
		 	    					if(jObj.has("statusDate")){
		 	    						habitation.setStatusDate(new Date(commonMethodsUtilService.getLongValueForObject(jObj.getLong("statusDate"))));
		 	    					}
		 	    					
		 	    					if(jObj.has("unSafeLpcd")){
		 	    						habitation.setUnSafeLpcd(commonMethodsUtilService.getDoubleValueForObject(jObj.getDouble("unSafeLpcd")));
		 	    					}
		 	    					
		 	    					if(jObj.has("safeLpcd")){
		 	    						habitation.setSafeLpcd(commonMethodsUtilService.getDoubleValueForObject(jObj.getDouble("safeLpcd")));
		 	    					}
		 	    					
		 	    					if(jObj.has("coverageStatus")){
		 	    						habitation.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(jObj.getString("coverageStatus")));
		 	    					}
		 	    					
		 	    					if(jObj.has("previousYrStatus")){
		 	    						habitation.setPreviousYearStatus(commonMethodsUtilService.getStringValueForObject(jObj.getString("previousYrStatus")));
		 	    					}
		 	    					
		 	    					if(jObj.has("latitude")){
		 	    						habitation.setLatitude(commonMethodsUtilService.getStringValueForObject(jObj.getString("latitude")));
		 	    					}
		 	    					
		 	    					if(jObj.has("longtitude")){
		 	    						habitation.setLongtitude(commonMethodsUtilService.getStringValueForObject(jObj.getString("longtitude")));
		 	    					}
		 	    					
		 	    					if(jObj.has("scPopCovered")){
		 	    						habitation.setScPopulationCovered(commonMethodsUtilService.getLongValueForObject(jObj.getLong("scPopCovered")));
		 	    					}
		 	    					
		 	    					if(jObj.has("dCode")){
		 	    						habitation.setDistrictCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("dCode")));
		 	    					}
		 	    					
		 	    					if(jObj.has("mCode")){
		 	    						habitation.setMandalCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("mCode")));
		 	    					}
		 	    					
		 	    					if(jObj.has("pCode")){
		 	    						habitation.setPanchayatCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("pCode")));
		 	    					}
		 	    					
		 	    					if(jObj.has("vCode")){
		 	    						habitation.setVillageCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("vCode")));
		 	    					}
		 	    					habitation.setIsDeleted("N");
		 	    					habitationDAO.save(habitation);
		 	    				}
		 	    			}catch(Exception e){
		 	    				e.printStackTrace();
		 	    			}
	 	    			}
	 	    		}
	 	    	}
 	      	}
		}catch(Exception e){
			LOG.error("Exception Occured in getAllData() method, Exception - ",e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.tpi.rws.service.IRWSNICService#getStressedHabitationDetailsByStatusByLocationType()
	 */
	public void getStressedHabitationDetailsByStatusByLocationType(){
		try{
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getStressedHabitationDetailsByStatusByLocationType");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, new InputVO());
        	
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
				String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
 	    			JSONArray finalArray = new JSONArray(output);//25848
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			TressedHabitation tressedHabitation = null;
	 	    			
	 	    			for(int i=0;i<0;i++){
	 	    				try{
		 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    				if(jObj.length() > 0){
		 	    					tressedHabitation = new TressedHabitation();
		 	    					if(jObj.has("districtCode")){
		 	    						tressedHabitation.setDistrictCode(commonMethodsUtilService.getLongValueForObject(jObj.getString("districtCode")));
		 	    					}
		 	    					if(jObj.has("districtName")){
		 	    						tressedHabitation.setDistrictName(commonMethodsUtilService.getStringValueForObject(jObj.getString("districtName")));
		 	    					}
		 	    					if(jObj.has("constituencyCode")){
		 	    						tressedHabitation.setConstituencyCode(commonMethodsUtilService.getLongValueForObject(jObj.getString("constituencyCode")));
		 	    					}
		 	    					if(jObj.has("constituencyName")){
		 	    						tressedHabitation.setConstituencyName(commonMethodsUtilService.getStringValueForObject(jObj.getString("constituencyName")));
		 	    					}
		 	    					if(jObj.has("mandalCode")){
		 	    						tressedHabitation.setMandalCode(commonMethodsUtilService.getLongValueForObject(jObj.getString("mandalCode")));
		 	    					}
		 	    					if(jObj.has("mandalName")){
		 	    						tressedHabitation.setMandalName(commonMethodsUtilService.getStringValueForObject(jObj.getString("mandalName")));
		 	    					}
		 	    					if(jObj.has("habitationCode")){
		 	    						tressedHabitation.setHabitationCode(commonMethodsUtilService.getStringValueForObject(jObj.getString("habitationCode")));
		 	    					}
		 	    					if(jObj.has("habitationName")){
		 	    						tressedHabitation.setHabitationName(commonMethodsUtilService.getStringValueForObject(jObj.getString("habitationName")));
		 	    					}
		 	    					if(jObj.has("coverageStatus")){
		 	    						tressedHabitation.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(jObj.getString("coverageStatus")));
		 	    					}
		 	    					tressedHabitation.setIsDeleted("N");
		 	    					tressedHabitationDAO.save(tressedHabitation);
		 	    				}
		 	    			}catch(Exception e){
		 	    				e.printStackTrace();
		 	    			}
	 	    			}
	 	    		}
	 	    	}
 	      	}
		}catch(Exception e){
			LOG.error("Exception Occured in getAllData() method, Exception - ",e);
		}
	}
	
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.tpi.rws.service.IRWSNICService#updateAllHabitationData()
	 */
	public void updateAllHabitationData(){
		try{
			//get all the records from habitation
			List<Object[]> hubList = habitationDAO.getHabitationDataList(new InputVO());
			
			//create a list of Habitation
			List<Habitation> alreadyExistHabitationList = new ArrayList<Habitation>();
			List<Habitation> habitationList = new ArrayList<Habitation>();
			createHabitationList(hubList,habitationList);
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getAllHabitationDetails");
	        String authStringEnc = getAuthenticationString("itgrids","Itgrids@123");
	        InputVO inputVO = new InputVO();
	        //inputVO.setYear("2017");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, inputVO);
        	Set<Long> mandalCode = new HashSet<Long>();
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
 	      		String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
 	    			JSONArray finalArray = new JSONArray(output);//48363
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			Habitation hab = null;
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				if(jObj.length() > 0){
	 	    					mandalCode.add(commonMethodsUtilService.getLongValueForObject(jObj.getLong("mCode")));
		 	    				hab = new Habitation();
		 	    				initializeHabitation(hab,jObj);
		 	    				
	 	    					if(habitationList.contains(hab)){
	 	    						//System.out.println("From list:"+habitationList.get(habitationList.indexOf(hab)).hashCode());
	 	    						//System.out.println("From individual:"+hab.hashCode());
	 	    						alreadyExistHabitationList.add(habitationList.get(habitationList.indexOf(hab)));
	 	    					}else{
	 	    						hab.setTotalPopulation(hab.getCensusPlainPopulation()+hab.getCensusScPopulation()+hab.getCensusStPopulation());
	 	    						hab.setIsDeleted("N");
	 	    						habitationDAO.save(hab);
	 	    					}
	 	    				}
	 	    			}
	 	    		}
				}
 	      	}
        	List<Long> totalId = new ArrayList<Long>();
        	List<Long> existingId = new ArrayList<Long>();
        	if(habitationList != null && alreadyExistHabitationList != null){
        		for(Habitation param : habitationList){
        			totalId.add(param.getHabitationId());
        		}
        		for(Habitation param : alreadyExistHabitationList){
        			existingId.add(param.getHabitationId());
        		}
        		if(totalId != null && existingId != null){
        			for(Long id : existingId){
        				totalId.remove(id);
        			}
        			//System.out.println(totalId);
        		}
        		
        	}
        	if(totalId != null && totalId.size() > 0){
        		habitationDAO.deleteObsolateData(totalId);
        	}
        	
		}catch(Exception e){
			LOG.error("Exception Occured in updateAllHabitationData() method, Exception - ",e);
		}
	}
	/*
	censusPlainPopulation.hashCode());
	censusScPopulation.hashCode());
	censusStPopulation.hashCode());
	censusYear.hashCode());
	coverageStatus.hashCode());
	districtCode.hashCode());
	mandalCode.hashCode());
	panchCode.hashCode());
	panchayatCode.hashCode());
	plainPopulationCovered.hashCode());
	previousYearStatus.hashCode());
	safeLpcd.hashCode());
	scPopulationCovered.hashCode());
	statusDate.hashCode());
	unSafeLpcd.hashCode());
	villageCode.hashCode());
	*/
	public void initializeHabitation(Habitation hab,JSONObject jObj){
		try{
			if(jObj.has("dName")){
				hab.setDistrictName(commonMethodsUtilService.getStringValueForObject(jObj.getString("dName")).trim());
			}
			if(jObj.has("mName")){
				hab.setMandalName(commonMethodsUtilService.getStringValueForObject(jObj.getString("mName")).trim());
			}
			
			if(jObj.has("pName")){
				hab.setPanchayatName(commonMethodsUtilService.getStringValueForObject(jObj.getString("pName")).trim());
			}
			
			if(jObj.has("vName")){
				hab.setVillageName(commonMethodsUtilService.getStringValueForObject(jObj.getString("vName")).trim());
			}
			
			if(jObj.has("panchName")){
				hab.setPanchName(commonMethodsUtilService.getStringValueForObject(jObj.getString("panchName")).trim());
			}
			
			if(jObj.has("panchCode")){
				hab.setPanchCode(commonMethodsUtilService.getStringValueForObject(jObj.getLong("panchCode")).trim());
			}
			
			if(jObj.has("censusYear")){
				hab.setCensusYear(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusYear")));
			}
			
			if(jObj.has("censusPlainPopu")){
				hab.setCensusPlainPopulation(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusPlainPopu")));
			}
			
			if(jObj.has("censusSCPopu")){
				hab.setCensusScPopulation(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusSCPopu")));
			}
			
			if(jObj.has("censusSTPopu")){
				hab.setCensusStPopulation(commonMethodsUtilService.getLongValueForObject(jObj.getLong("censusSTPopu")));
			}
			
			if(jObj.has("plainPopCovered")){
				hab.setPlainPopulationCovered(commonMethodsUtilService.getLongValueForObject(jObj.getLong("plainPopCovered")));
			}
			
			Date date = new Date(commonMethodsUtilService.getLongValueForObject(jObj.getLong("statusDate")));
			
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    format.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		    String dateStr = format.format(date);
		    date = format.parse(dateStr);
			if(jObj.has("statusDate")){
				hab.setStatusDate(date);
			}
			
			if(jObj.has("unSafeLpcd")){
				hab.setUnSafeLpcd(commonMethodsUtilService.getDoubleValueForObject(jObj.getDouble("unSafeLpcd")));
			}
			
			if(jObj.has("safeLpcd")){
				hab.setSafeLpcd(commonMethodsUtilService.getDoubleValueForObject(jObj.getDouble("safeLpcd")));
			}
			
			if(jObj.has("coverageStatus")){
				hab.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(jObj.getString("coverageStatus")).trim());
			}
			
			if(jObj.has("previousYrStatus")){
				hab.setPreviousYearStatus(commonMethodsUtilService.getStringValueForObject(jObj.getString("previousYrStatus")).trim());
			}
			
			if(jObj.has("latitude")){
				hab.setLatitude(commonMethodsUtilService.getStringValueForObject(jObj.getString("latitude")).trim());
			}
			
			if(jObj.has("longtitude")){
				hab.setLongtitude(commonMethodsUtilService.getStringValueForObject(jObj.getString("longtitude")).trim());
			}
			
			if(jObj.has("scPopCovered")){
				hab.setScPopulationCovered(commonMethodsUtilService.getLongValueForObject(jObj.getLong("scPopCovered")));
			}
			
			if(jObj.has("dCode")){
				hab.setDistrictCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("dCode")));
			}
			
			if(jObj.has("mCode")){
				hab.setMandalCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("mCode")));
			}
			
			if(jObj.has("pCode")){
				hab.setPanchayatCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("pCode")));
			}
			
			if(jObj.has("vCode")){
				hab.setVillageCode(commonMethodsUtilService.getLongValueForObject(jObj.getLong("vCode")));
			}
		}catch(Exception e){
			LOG.error("Exception Occured in initializeHabitation() method, Exception - ",e);
		}
	}
	/*
	this.districtCode = districtCode;
	this.districtName = districtName;
	this.constituencyCode = constituencyCode;
	this.constituencyName = constituencyName;
	this.mandalCode = mandalCode;
	this.mandalName = mandalName;
	this.habitationCode = habitationCode;
	this.habitationName = habitationName;
	this.coverageStatus = coverageStatus;*/
	
	public TressedHabitation initializeTressedHabitation(JSONObject jObj){
		try{
			Long districtCode=null;
			String districtName=null;
			Long constituencyCode=null;
			String constituencyName=null;
			Long mandalCode=null;
			String mandalName=null;
			String habitationCode=null;
			String habitationName=null;
			String coverageStatus=null;
			if(jObj.has("districtCode")){
				districtCode=commonMethodsUtilService.getLongValueForObject(jObj.getString("districtCode"));
			}
			if(jObj.has("districtName")){
				districtName=commonMethodsUtilService.getStringValueForObject(jObj.getString("districtName")).trim();
			}
			if(jObj.has("constituencyCode")){
				constituencyCode=commonMethodsUtilService.getLongValueForObject(jObj.getString("constituencyCode"));
			}
			if(jObj.has("constituencyName")){
				constituencyName=commonMethodsUtilService.getStringValueForObject(jObj.getString("constituencyName")).trim();
			}
			if(jObj.has("mandalCode")){
				mandalCode=commonMethodsUtilService.getLongValueForObject(jObj.getString("mandalCode"));
			}
			if(jObj.has("mandalName")){
				mandalName=commonMethodsUtilService.getStringValueForObject(jObj.getString("mandalName")).trim();
			}
			if(jObj.has("habitationCode")){
				habitationCode=commonMethodsUtilService.getStringValueForObject(jObj.getString("habitationCode")).trim();
			}
			if(jObj.has("habitationName")){
				habitationName=commonMethodsUtilService.getStringValueForObject(jObj.getString("habitationName")).trim();
			}
			if(jObj.has("coverageStatus")){
				coverageStatus=commonMethodsUtilService.getStringValueForObject(jObj.getString("coverageStatus")).trim();
			}
			TressedHabitation tressedHabitation = new TressedHabitation(null,districtCode, districtName, constituencyCode, constituencyName, mandalCode, mandalName, habitationCode, habitationName, coverageStatus,null);
			return tressedHabitation;
		}catch(Exception e){
			LOG.error("Exception Occured in initializeTressedHabitation() method, Exception - ",e);
		}
		return null;
	}
	public void createHabitationList(List<Object[]> hubList,List<Habitation> habitationList){
		try{
			Habitation habitation = null;
			if(hubList != null && hubList.size() > 0){
				for(Object[] param : hubList){
					habitation = new Habitation();
					habitation.setHabitationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					
					habitation.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[1]).trim());
 					
 					habitation.setMandalName(commonMethodsUtilService.getStringValueForObject(param[2]).trim());
 					
 					habitation.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[3]).trim());
 					
 					habitation.setVillageName(commonMethodsUtilService.getStringValueForObject(param[4]).trim());
 					
 					habitation.setPanchName(commonMethodsUtilService.getStringValueForObject(param[5]).trim());
 					
 					habitation.setPanchCode(commonMethodsUtilService.getStringValueForObject(param[6]).trim());
 					
 					habitation.setCensusYear(commonMethodsUtilService.getLongValueForObject(param[7]));
 					
 					habitation.setCensusPlainPopulation(commonMethodsUtilService.getLongValueForObject(param[8]));
 					
 					habitation.setCensusScPopulation(commonMethodsUtilService.getLongValueForObject(param[9]));
 					
 					habitation.setCensusStPopulation(commonMethodsUtilService.getLongValueForObject(param[10]));
 					
 					
 					
 					habitation.setPlainPopulationCovered(commonMethodsUtilService.getLongValueForObject(param[11]));
 					
 					Date date =null;
 					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 					format.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
 					if(commonMethodsUtilService.getStringValueForObject(param[12]).trim().length() > 10){
 						date = format.parse(commonMethodsUtilService.getStringValueForObject(param[12]).trim().substring(0, 10));
 					}
 					habitation.setStatusDate(date);
 					
 				
 					habitation.setUnSafeLpcd(commonMethodsUtilService.getDoubleValueForObject(param[13]));
 					
 					habitation.setSafeLpcd(commonMethodsUtilService.getDoubleValueForObject(param[14]));
 					
 					habitation.setCoverageStatus(commonMethodsUtilService.getStringValueForObject(param[15]).trim());
 					
 					habitation.setPreviousYearStatus(commonMethodsUtilService.getStringValueForObject(param[16]).trim());
 					
 					habitation.setLatitude(commonMethodsUtilService.getStringValueForObject(param[17]).trim());
 					
 					habitation.setLongtitude(commonMethodsUtilService.getStringValueForObject(param[18]).trim());
 					
 					habitation.setScPopulationCovered(commonMethodsUtilService.getLongValueForObject(param[19]));
 					
 					habitation.setDistrictCode(commonMethodsUtilService.getLongValueForObject(param[20]));
 					
 					habitation.setMandalCode(commonMethodsUtilService.getLongValueForObject(param[21]));
 				
 					habitation.setPanchayatCode(commonMethodsUtilService.getLongValueForObject(param[22]));
 					
 					habitation.setVillageCode(commonMethodsUtilService.getLongValueForObject(param[23]));
 					
 					habitationList.add(habitation);
 					
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in createHabitationList() method, Exception - ",e);
		}
	}
	public void createTressedHabitationList(List<Object[]> tressedHubList,List<TressedHabitation> tressedHabitationList){
		try{
			Long tressedHabitationId=null;
			Long districtCode=null;
			String districtName=null;
			Long constituencyCode=null;
			String constituencyName=null;
			Long mandalCode=null;
			String mandalName=null;
			String habitationCode=null;
			String habitationName=null;
			String coverageStatus=null;
			TressedHabitation tressedHabitation = null;
			if(tressedHubList != null && tressedHubList.size() > 0){
				for(Object[] param : tressedHubList){
					tressedHabitationId=commonMethodsUtilService.getLongValueForObject(param[0]);
					
					districtCode=commonMethodsUtilService.getLongValueForObject(param[1]);
 				
					districtName=commonMethodsUtilService.getStringValueForObject(param[2]).trim();
 					
					constituencyCode=commonMethodsUtilService.getLongValueForObject(param[3]);
 					
					constituencyName=commonMethodsUtilService.getStringValueForObject(param[4]).trim();
 				
					mandalCode=commonMethodsUtilService.getLongValueForObject(param[5]);
 					
					mandalName=commonMethodsUtilService.getStringValueForObject(param[6]).trim();
 					
					habitationCode=commonMethodsUtilService.getStringValueForObject(param[7]).trim();
 				
					habitationName=commonMethodsUtilService.getStringValueForObject(param[8]).trim();
 					
					coverageStatus=commonMethodsUtilService.getStringValueForObject(param[9]).trim();
					
					tressedHabitation=new TressedHabitation(tressedHabitationId, districtCode, districtName, constituencyCode, constituencyName, mandalCode, mandalName, habitationCode, habitationName, coverageStatus, null);
 					 					
					tressedHabitationList.add(tressedHabitation);
 					
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in createTressedHabitationList() method, Exception - ",e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.tpi.rws.service.IRWSNICService#updateAllTressedHabitationData()
	 */
	public void updateAllTressedHabitationData(){
		try{
			//get all the records from habitation
			List<Object[]> tressedHubList = tressedHabitationDAO.getTressedHabitationDataList(new InputVO());
			//create a list of Habitation
			List<TressedHabitation> alreadyExistTressedHabitationList = new ArrayList<TressedHabitation>();
			List<TressedHabitation> tressedHabitationList = new ArrayList<TressedHabitation>();
			createTressedHabitationList(tressedHubList,tressedHabitationList);
			
			WebResource webResource = commonMethodsUtilService.getWebResourceObject(IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getStressedHabitationDetailsByStatusByLocationType");
	        String authStringEnc = getAuthenticationString("admin","admin@123");
        	ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, new InputVO());
        	//Set<String> hubCodeList = new HashSet<String>();
        	if(response.getStatus() != 200){
 	    		throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
 	      	}else{
 	      		String output = response.getEntity(String.class);
				if(output != null && !output.isEmpty()){
 	    			JSONArray finalArray = new JSONArray(output);//25848
	 	    		if(finalArray!=null && finalArray.length()>0){
	 	    			TressedHabitation tressedHab = null;
	 	    			
	 	    			for(int i=0;i<finalArray.length();i++){
	 	    				JSONObject jObj = (JSONObject) finalArray.get(i);
	 	    				if(jObj.length() > 0){
		 	    				//tressedHab = new TressedHabitation();
	 	    					tressedHab = initializeTressedHabitation(jObj);
	 	    					//hubCodeList.add(commonMethodsUtilService.getStringValueForObject(jObj.getString("habitationCode")).trim());
		 	    				
	 	    					if(tressedHabitationList.contains(tressedHab)){
	 	    						//System.out.println("From list:"+tressedHabitationList.get(tressedHabitationList.indexOf(tressedHab)).hashCode());
	 	    						//System.out.println("From individual:"+tressedHab.hashCode());
	 	    						alreadyExistTressedHabitationList.add(tressedHabitationList.get(tressedHabitationList.indexOf(tressedHab)));
	 	    					}else{
	 	    						tressedHab.setIsDeleted("N");
	 	    						tressedHabitationDAO.save(tressedHab);
	 	    					}
	 	    				}
	 	    			}
	 	    		}
				}
 	      	}
        	List<Long> totalId = new ArrayList<Long>();
        	List<Long> existingId = new ArrayList<Long>();
        	if(tressedHabitationList != null && alreadyExistTressedHabitationList != null){
        		for(TressedHabitation param : tressedHabitationList){
        			totalId.add(param.getTressedHabitationId());
        		}
        		for(TressedHabitation param : alreadyExistTressedHabitationList){
        			existingId.add(param.getTressedHabitationId());
        		}
        		if(totalId != null && existingId != null){
        			for(Long id : existingId){
        				totalId.remove(id);
        			}
        			//System.out.println(totalId);
        		}
        		
        	}
        	if(totalId != null && totalId.size() > 0){
        		tressedHabitationDAO.deleteObsolateData(totalId);
        	}
        	
		}catch(Exception e){
			LOG.error("Exception Occured in updateAllTressedHabitationData() method, Exception - ",e);
		}
	}
	public void stop(){
		System.out.println("stop");
	}
	/*
	constituencyCode.hashCode());
	coverageStatus.hashCode());
	districtCode.hashCode());
	habitationCode.hashCode());
	mandalCode.hashCode());
	*/
	
	/*
	public static void main(String...strings) throws ParseException{
		 Date date = new Date(1490985000000l);
		  DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    format.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		    String dateStr = format.format(date);
		    date = format.parse(dateStr);
		    System.out.println(date);
		 System.out.println(format.format(date));
		
	}
	*/
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.tpi.rws.service.IRWSNICService#getHabitationCoverageStatus(com.itgrids.dto.InputVO)
	 */
	public List<LocationVO> getHabitationCoverageStatus(InputVO inputVO){
		try{
			List<LocationVO> voList = new ArrayList<LocationVO>(0);
			LocationVO vo = null;
			List<StatusVO> tempList = null;
			Set<String> constituencyIdList = new HashSet<String>();
			List<Object[]> conList = null;
			Map<Long,String> constituencyIdAndNameMap = new HashMap<Long,String>();
			if(inputVO!= null){
				inputVO = setFilterVal(inputVO);
			}
			List<Object[]> habDataList = null;
			List<Object[]> tressedHabDataList = null;
			if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("constituency")){
				habDataList = habitationDAO.getHabitationDetailsForConstituency(inputVO,null,null);
				//collect constituencyId and get constituency name.
				if(habDataList != null && habDataList.size() > 0){
					for(Object[] param : habDataList){
						constituencyIdList.add(commonMethodsUtilService.getStringValueForObject(param[0]));
					}
					if(constituencyIdList != null && constituencyIdList.size() > 0){
						conList = rwsConstituencyDAO.getConstituencyList(constituencyIdList); 
					}
					if(conList != null && conList.size() > 0){
						for(Object[] param : conList){
							constituencyIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
						}
					}
					for(Object[] param : habDataList){
						String name = constituencyIdAndNameMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						param[1] = name;
					}
					
				}
		 	    //tressedHabDataList = habitationDAO.getTressedHabitationDetailsForConstituency(inputVO,null,null);
			}else if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
				habDataList = habitationDAO.getHabitationDetailsForMandal(inputVO,null,null);
				
			}else{
				habDataList = habitationDAO.getHabitationDetails(inputVO,null,null);
				if(inputVO.getCallType() != null &&  inputVO.getCallType().trim().equalsIgnoreCase("graph")){
					tressedHabDataList = habitationDAO.getTressedHabitationDetails(inputVO,null,null);
				}
			}
	 	    
			//create two  map for locationId and status and LocationCount , locationId and status and PopulationCount for habitation
			Map<Long,String> locationIdAndName = new HashMap<Long,String>();
			
			Map<Long,Map<String,Long>> locationIdAndStatusAndLocationCount = new HashMap<Long,Map<String,Long>>();
			Map<Long,Map<String,Long>> locationIdAndStatusAndPopulationCount = new HashMap<Long,Map<String,Long>>();
			
			
			Map<Long,Map<Long,Map<String,Long>>> DistIdAndlocationIdAndStatusAndLocationCount = new HashMap<Long,Map<Long,Map<String,Long>>>();
			Map<Long,Map<Long,Map<String,Long>>> DistIdAndlocationIdAndStatusAndPopulationCount = new HashMap<Long,Map<Long,Map<String,Long>>>();
			
			
			Map<Long,Map<Long,String>> distIdAndMandalIdAndNameMap = new HashMap<Long,Map<Long,String>>();
			
			
			if(habDataList != null && habDataList.size() > 0 && inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
				initializeHabitationMapForMandal(DistIdAndlocationIdAndStatusAndLocationCount,DistIdAndlocationIdAndStatusAndPopulationCount,habDataList,distIdAndMandalIdAndNameMap);
			}else{
				initializeHabitationMap(locationIdAndStatusAndLocationCount,locationIdAndStatusAndPopulationCount,habDataList,locationIdAndName);
			}
			
			
			//create two map for locationId and status and LocationCount, locationId and status and PopulationCount for tressed habitation
			
			Map<Long,Map<String,Long>> locationIdAndStatusAndLocationCountForTressedHabitation = new HashMap<Long,Map<String,Long>>();
			Map<Long,Map<String,Long>> locationIdAndStatusAndPopulationCountForTressedHabitation = new HashMap<Long,Map<String,Long>>();
			
			if(tressedHabDataList != null && tressedHabDataList.size() > 0){
				initializeTressedHabitationMap(locationIdAndStatusAndLocationCountForTressedHabitation,locationIdAndStatusAndPopulationCountForTressedHabitation,tressedHabDataList);
			}
			if(inputVO.getLocationType() != null && inputVO.getLocationType().trim().equalsIgnoreCase("mandal")){
				if(DistIdAndlocationIdAndStatusAndLocationCount!=null && DistIdAndlocationIdAndStatusAndLocationCount.size() > 0 && DistIdAndlocationIdAndStatusAndPopulationCount != null && DistIdAndlocationIdAndStatusAndPopulationCount.size() > 0){
					for(Entry<Long,Map<Long,Map<String,Long>>> outerParam : DistIdAndlocationIdAndStatusAndLocationCount.entrySet()){
						Long total = null;
		    			for(Entry<Long,Map<String,Long>> param : outerParam.getValue().entrySet()){
		    				total = new Long(0L);
		    				vo = new LocationVO();
		    				vo.setGoNumber(param.getKey().toString());
		    				vo.setLocationName(commonMethodsUtilService.getStringValueForObject(distIdAndMandalIdAndNameMap.get(outerParam.getKey()).get(param.getKey())));
		    				if(inputVO.getCallType() != null &&  inputVO.getCallType().trim().equalsIgnoreCase("graph")){
		    					tempList = getTotalStatusSkeleton();
		    				}else{
		    					tempList = getStatusSkeleton();
		    				}
		    				
		    				for(Entry<String,Long> innerParam : param.getValue().entrySet()){
		    					StatusVO statusVO = getMatchedStatusVO(tempList,innerParam.getKey());
		    					if(statusVO != null){
		    						statusVO.setCount(innerParam.getValue());
		    						total = total + innerParam.getValue();
		    						if(DistIdAndlocationIdAndStatusAndPopulationCount.get(outerParam.getKey()) != null && DistIdAndlocationIdAndStatusAndPopulationCount.get(outerParam.getKey()).get(param.getKey()) != null && DistIdAndlocationIdAndStatusAndPopulationCount.get(outerParam.getKey()).get(param.getKey()).get(innerParam.getKey()) != null){
		    							statusVO.setPopulation(DistIdAndlocationIdAndStatusAndPopulationCount.get(outerParam.getKey()).get(param.getKey()).get(innerParam.getKey()));
		    						}
		    					}
		    				}
		    				vo.setStatusList(tempList);
		    				vo.setTotalCount(total);
		    				voList.add(vo);
		    			}
					}
				}
			}else{
				if(locationIdAndStatusAndLocationCount!=null && locationIdAndStatusAndLocationCount.size() > 0 && locationIdAndStatusAndPopulationCount != null && locationIdAndStatusAndPopulationCount.size() > 0){
	    			Long total = null;
	    			for(Entry<Long,Map<String,Long>> param : locationIdAndStatusAndLocationCount.entrySet()){
	    				total = new Long(0L);
	    				vo = new LocationVO();
	    				vo.setGoNumber(param.getKey().toString());
	    				vo.setLocationName(commonMethodsUtilService.getStringValueForObject(locationIdAndName.get(param.getKey())));
	    				if(inputVO.getCallType() != null &&  inputVO.getCallType().trim().equalsIgnoreCase("graph")){
	    					tempList = getTotalStatusSkeleton();
	    				}else{
	    					tempList = getStatusSkeleton();
	    				}
	    				
	    				for(Entry<String,Long> innerParam : param.getValue().entrySet()){
	    					StatusVO statusVO = getMatchedStatusVO(tempList,innerParam.getKey());
	    					if(statusVO != null){
	    						statusVO.setCount(innerParam.getValue());
	    						total = total + innerParam.getValue();
	    						if(locationIdAndStatusAndPopulationCount.get(param.getKey()) != null && locationIdAndStatusAndPopulationCount.get(param.getKey()).get(innerParam.getKey()) != null){
	    							statusVO.setPopulation(locationIdAndStatusAndPopulationCount.get(param.getKey()).get(innerParam.getKey()));
	    						}
	    					}
	    				}
	    				vo.setStatusList(tempList);
	    				vo.setTotalCount(total);
	    				voList.add(vo);
	    			}
	    		}
			}
    		
    		if(inputVO.getCallType() != null &&  inputVO.getCallType().trim().equalsIgnoreCase("graph") && locationIdAndStatusAndLocationCountForTressedHabitation!=null && locationIdAndStatusAndLocationCountForTressedHabitation.size() > 0 && locationIdAndStatusAndPopulationCountForTressedHabitation != null && locationIdAndStatusAndPopulationCountForTressedHabitation.size() > 0){
    			for(Entry<Long,Map<String,Long>> param : locationIdAndStatusAndLocationCountForTressedHabitation.entrySet()){
    				vo = getMatchedLocationVO(voList,param.getKey());
    				if(vo != null){
						for(Entry<String,Long> innerParam : param.getValue().entrySet()){
							StatusVO statusVO = getMatchedStatusVO(vo.getStatusList(),innerParam.getKey());
							if(statusVO != null){
								statusVO.setCount(innerParam.getValue());
								if(locationIdAndStatusAndPopulationCountForTressedHabitation.get(param.getKey()) != null && locationIdAndStatusAndPopulationCountForTressedHabitation.get(param.getKey()).get(innerParam.getKey()) != null){
									statusVO.setPopulation(locationIdAndStatusAndPopulationCount.get(param.getKey()).get(innerParam.getKey()));
								}
							}
						}
    				}
    			}
    		}
    		if(voList != null && voList.size() > 0){
    			for(LocationVO param : voList){
    				for(StatusVO stsvo : param.getStatusList()){
    					stsvo.setPercentage(commonMethodsUtilService.calculatePercantage(stsvo.getCount(), param.getTotalCount()));
    				}
    			}
    		}
    		return voList;
		}catch(Exception e){
			LOG.error("Exception Occured in getHabitationCoverageStatus() method, Exception - ",e);
		}
		return null;
	}
	public void initializeHabitationMapForMandal(Map<Long,Map<Long,Map<String,Long>>> distIdAndlocationIdAndStatusAndLocationCount,Map<Long,Map<Long,Map<String,Long>>> distIdAndlocationIdAndStatusAndPopulationCount,List<Object[]> habDataList, Map<Long,Map<Long,String>> distIdAndlocationIdAndName){
		try{
			Map<Long,Map<String,Long>> locationIdAndstatusAndLocationCountMap = null;
			Map<String,Long> statusAndLocationCountMap = null;
			
			Map<Long,Map<String,Long>> locationIdAndstatusAndPopulationCountMap = null;
			Map<String,Long> statusAndPopulationCountMap = null;
			
			Map<Long,String> locationIdAndNameMap = null;
			
			if(habDataList != null && habDataList.size() > 0){
				for(Object[] param : habDataList){
					locationIdAndstatusAndLocationCountMap = distIdAndlocationIdAndStatusAndLocationCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationIdAndstatusAndLocationCountMap == null){
						statusAndLocationCountMap = new HashMap<String,Long>();
						statusAndLocationCountMap.put(commonMethodsUtilService.getStringValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndstatusAndLocationCountMap = new HashMap<Long,Map<String,Long>>();
						locationIdAndstatusAndLocationCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), statusAndLocationCountMap);
						distIdAndlocationIdAndStatusAndLocationCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationIdAndstatusAndLocationCountMap);
					}else{
						statusAndLocationCountMap = locationIdAndstatusAndLocationCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						if(statusAndLocationCountMap == null){
							statusAndLocationCountMap = new HashMap<String,Long>();
							locationIdAndstatusAndLocationCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), statusAndLocationCountMap);
						}
						statusAndLocationCountMap.put(commonMethodsUtilService.getStringValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}
				}
				
				for(Object[] param : habDataList){
					locationIdAndstatusAndPopulationCountMap = distIdAndlocationIdAndStatusAndPopulationCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationIdAndstatusAndPopulationCountMap == null){
						statusAndPopulationCountMap = new HashMap<String,Long>();
						statusAndPopulationCountMap.put(commonMethodsUtilService.getStringValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
						locationIdAndstatusAndPopulationCountMap = new HashMap<Long,Map<String,Long>>();
						locationIdAndstatusAndPopulationCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), statusAndPopulationCountMap);
						distIdAndlocationIdAndStatusAndPopulationCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationIdAndstatusAndPopulationCountMap);
					}else{
						statusAndPopulationCountMap = locationIdAndstatusAndPopulationCountMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						if(statusAndPopulationCountMap == null){
							statusAndPopulationCountMap = new HashMap<String,Long>();
							locationIdAndstatusAndPopulationCountMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), statusAndPopulationCountMap);
						}
						statusAndPopulationCountMap.put(commonMethodsUtilService.getStringValueForObject(param[3]), commonMethodsUtilService.getLongValueForObject(param[4]));
					}
				}
				
				for(Object[] param : habDataList){
					locationIdAndNameMap = distIdAndlocationIdAndName.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationIdAndNameMap == null){
						locationIdAndNameMap = new HashMap<Long,String>();
						distIdAndlocationIdAndName.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationIdAndNameMap);
					}
					locationIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), commonMethodsUtilService.getStringValueForObject(param[2]));
				}
				
			}
			
	
		}catch(Exception e){
			LOG.error("Exception Occured in initializeHabitationMap() method, Exception - ",e);
		}
	}
	public LocationVO getMatchedLocationVO(List<LocationVO> voList,Long locationId){
		try{
			if(voList != null && voList.size() > 0){
				for(LocationVO param : voList){
					if(param.getGoNumber().equalsIgnoreCase(locationId.toString())){
						return param;
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getMatchedLocationVO() method, Exception - ",e);
		}
		return null;
	}
	public List<StatusVO> getTotalStatusSkeleton(){
		List<StatusVO> tempList = new ArrayList<StatusVO>(0);
		StatusVO FCVO = new StatusVO();
		FCVO.setStatus("FC");
		tempList.add(0,FCVO);
		
		StatusVO STFCVO = new StatusVO();
		STFCVO.setStatus("Stressed FC");
		tempList.add(1,STFCVO);
		
		StatusVO pc4VO = new StatusVO();
		pc4VO.setStatus("PC4");
		tempList.add(2,pc4VO);
		
		StatusVO stpc4VO = new StatusVO();
		stpc4VO.setStatus("Stressed PC4");
		tempList.add(3,stpc4VO);
		
		
		StatusVO pc3VO = new StatusVO();
		pc3VO.setStatus("PC3");
		tempList.add(4,pc3VO);
		
		StatusVO stpc3VO = new StatusVO();
		stpc3VO.setStatus("Stressed PC3");
		tempList.add(5,stpc3VO);
		
		
		StatusVO pc2VO = new StatusVO();
		pc2VO.setStatus("PC2");
		tempList.add(6,pc2VO);
		
		StatusVO stpc2VO = new StatusVO();
		stpc2VO.setStatus("Stressed PC2");
		tempList.add(7,stpc2VO);
		
		
		StatusVO pc1VO = new StatusVO();
		pc1VO.setStatus("PC1");
		tempList.add(8,pc1VO);
		
		StatusVO stpc1VO = new StatusVO();
		stpc1VO.setStatus("Stressed PC1");
		tempList.add(9,stpc1VO);
		
		
		StatusVO nssVO = new StatusVO();
		nssVO.setStatus("NSS");
		tempList.add(10,nssVO);
		
		StatusVO stnssVO = new StatusVO();
		stnssVO.setStatus("Stressed NSS");
		tempList.add(11,stnssVO);
		
		return tempList;
	}
	public void initializeHabitationMap(Map<Long,Map<String,Long>> locationIdAndStatusAndLocationCount,Map<Long,Map<String,Long>> locationIdAndStatusAndPopulationCount,List<Object[]> habDataList, Map<Long,String> locationIdAndName){
		try{
			Map<String,Long> statusAndLocationCountMap = null;
			Map<String,Long> statusAndPopulationCountMap = null;
			if(habDataList != null && habDataList.size() > 0){
				for(Object[] param : habDataList){
					if(commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("NC")){
						continue;
					}
					if(locationIdAndName != null){
						locationIdAndName.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
					}
					statusAndLocationCountMap = locationIdAndStatusAndLocationCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == statusAndLocationCountMap){
						statusAndLocationCountMap = new HashMap<String,Long>();
						locationIdAndStatusAndLocationCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusAndLocationCountMap);
					}
					statusAndLocationCountMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
					
					statusAndPopulationCountMap = locationIdAndStatusAndPopulationCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == statusAndPopulationCountMap){
						statusAndPopulationCountMap = new HashMap<String,Long>();
						locationIdAndStatusAndPopulationCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusAndPopulationCountMap);
					}
					statusAndPopulationCountMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[4]));
					
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in initializeHabitationMap() method, Exception - ",e);
		}
	}
	
	public void initializeTressedHabitationMap(Map<Long,Map<String,Long>> locationIdAndStatusAndLocationCount,Map<Long,Map<String,Long>> locationIdAndStatusAndPopulationCount,List<Object[]> habDataList){
		try{
			Map<String,String> statusMap = new HashMap<String,String>();
			statusMap.put("FC", "Stressed FC");
			statusMap.put("PC4", "Stressed PC4");
			statusMap.put("PC3", "Stressed PC3");
			statusMap.put("PC2", "Stressed PC2");
			statusMap.put("PC1", "Stressed PC1");
			statusMap.put("NSS", "Stressed NSS");
			Map<String,Long> statusAndLocationCountMap = null;
			Map<String,Long> statusAndPopulationCountMap = null;
			if(habDataList != null && habDataList.size() > 0){
				for(Object[] param : habDataList){
					if(commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("NC")){
						continue;
					}
					statusAndLocationCountMap = locationIdAndStatusAndLocationCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == statusAndLocationCountMap){
						statusAndLocationCountMap = new HashMap<String,Long>();
						locationIdAndStatusAndLocationCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusAndLocationCountMap);
					}
					statusAndLocationCountMap.put(statusMap.get(commonMethodsUtilService.getStringValueForObject(param[2])), commonMethodsUtilService.getLongValueForObject(param[3]));
					
					statusAndPopulationCountMap = locationIdAndStatusAndPopulationCount.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(null == statusAndPopulationCountMap){
						statusAndPopulationCountMap = new HashMap<String,Long>();
						locationIdAndStatusAndPopulationCount.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusAndPopulationCountMap);
					}
					statusAndPopulationCountMap.put(statusMap.get(commonMethodsUtilService.getStringValueForObject(param[2])), commonMethodsUtilService.getLongValueForObject(param[4]));
					
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in initializeHabitationMap() method, Exception - ",e);
		}
	}
	public Map<Long,IdNameVO> getAllAdminWorksDetails(){
		Map<Long,IdNameVO> assestWorkMap = new LinkedHashMap<Long,IdNameVO>();
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getAllWorkAdminDetails");	        
			String authStringEnc = getAuthenticationString("itgrids","Itgrids@123");	        
			ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);//null;
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
		 	    	if(finalArray!=null && finalArray.length()>0){
		 	    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 	    		Calendar calendar = Calendar.getInstance();
		 	    		
		 	    		for(int i=0;i<finalArray.length();i++){
		 	    			JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    			IdNameVO vo = new IdNameVO();
	 	    				vo.setWorkId(jObj.getLong("workId"));
	 	    				vo.setWorkName(jObj.getString("workName"));
	 	    				vo.setAdminDate(jObj.getString("adminDate"));
	 	    				vo.setAdminNo(jObj.getString("adminNo"));
	 	    				vo.setSanctionAmount(jObj.getLong("sanctionAmount"));
	 	    				vo.setTypeOfAssestName(jObj.getString("typeOfAssestName"));
	 	    				
	 	    				Long groundedDate = jObj.getLong("groundingDate");
	 	    				calendar.setTimeInMillis(groundedDate);
	 	    				vo.setGroundedDate(formatter.format(calendar.getTime()));
	 	    				calendar.clear();
	 	    				
	 	    				Long targetDate = jObj.getLong("targetDateComp");
	 	    				calendar.setTimeInMillis(targetDate);
	 	    				vo.setTargetDate(formatter.format(calendar.getTime()));
	 	    				
	 	    				assestWorkMap.put(vo.getWorkId(),vo);
		 	    		}
		 	    	}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllAdminWorksDetails() method, Exception - ",e);
		}
		return assestWorkMap;
	}
	
	public IdNameVO getExceededTargetWorksDetails(){
		IdNameVO returnvo = new IdNameVO();
		try {
			WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getAllWorkComplitionDetails");	        
			String authStringEnc = getAuthenticationString("itgrids","Itgrids@123");	        
			ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class);
			
			if(response.getStatus() != 200){
	 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	      }else{
	 	    	 String output = response.getEntity(String.class);//null;
	 	    	 
	 	    	if(output != null && !output.isEmpty()){
	 	    		JSONArray finalArray = new JSONArray(output);
		 	    	if(finalArray!=null && finalArray.length()>0){
		 	    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 	    		Calendar completedCal = Calendar.getInstance();
		 	    		Calendar targetCal = Calendar.getInstance();
		 	    		Map<Long,IdNameVO> assesetWorkMap = getAllAdminWorksDetails();
		 	    		
		 	    		String[] templateArr = {"0-30 Days","31-60 Days","61-90 Days","91-180 Days","181-365 Days","More Than 1 Year"};
		 	    		for (int i = 0; i < templateArr.length; i++) {
							IdNameVO vo = new IdNameVO();
							vo.setId((long)i+1L);
							vo.setName(templateArr[i]);
							returnvo.getCompletedList().add(vo);
						}
		 	    		
		 	    		for(int i=0;i<finalArray.length();i++){
		 	    			JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    			Long workId = jObj.getLong("workId");
 	    					IdNameVO assestvo = assesetWorkMap.get(workId);
 	    					String assestName = assestvo.getTypeOfAssestName();
 	    					if(jObj.has("dateOfCompletion")){
		 	    				Long completed = jObj.getLong("dateOfCompletion");
		 	    				Long target = jObj.getLong("targetDateComp");
		 	    				completedCal.setTimeInMillis(completed);
		 	    				targetCal.setTimeInMillis(target);
		 	    				Date completedDate = completedCal.getTime();
		 	    				Date targetDate = targetCal.getTime();
		 	    				Long daysDiff = completedDate.getTime() - targetDate.getTime();
		 	    				String dayStr = "";	
		 	    				
		 	    				if(daysDiff != null && daysDiff >= 0L && daysDiff <= 30L)
		 	    					dayStr = "0-30 Days";
		 	    				else if(daysDiff != null && daysDiff >= 31L && daysDiff <= 60L)
		 	    					dayStr = "31-60 Days";
		 	    				else if(daysDiff != null && daysDiff >= 61L && daysDiff <= 90L)
		 	    					dayStr = "61-90 Days";
		 	    				else if(daysDiff != null && daysDiff >= 91L && daysDiff <= 180L)
		 	    					dayStr = "91-180 Days";
		 	    				else if(daysDiff != null && daysDiff >= 181L && daysDiff <= 365L)
		 	    					dayStr = "180-365 Days";
		 	    				else if(daysDiff != null && daysDiff > 365L)
		 	    					dayStr = "More Than 1 Year";
		 	    				
		 	    				IdNameVO dayvo = getMatchedIdNameVO(returnvo.getCompletedList(), dayStr);
		 	    				if(dayvo != null){
		 	    					if(assestName != null && assestName.trim().equalsIgnoreCase("PWS")){
		 	    						dayvo.setPwsCount(dayvo.getPwsCount()+1L);
		 	    						dayvo.setPwsAmount(dayvo.getPwsAmount()+jObj.getLong("sanctionAmount"));
		 	    					}
		 	    					else if(assestName != null && assestName.trim().equalsIgnoreCase("CPWS")){
		 	    						dayvo.setCpwsCount(dayvo.getCpwsCount()+1L);
		 	    						dayvo.setCpwsAmount(dayvo.getCpwsAmount()+jObj.getLong("sanctionAmount"));
		 	    					}
		 	    				}
		 	    			}
		 	    			else{
		 	    				if(assestName != null && assestName.trim().equalsIgnoreCase("PWS")){
	 	    						returnvo.setOnGoingPwsCount(returnvo.getOnGoingPwsCount()+1L);
	 	    						returnvo.setOnGoingPwsAmount(returnvo.getOnGoingPwsAmount()+jObj.getLong("sanctionAmount"));
	 	    					}
	 	    					else if(assestName != null && assestName.trim().equalsIgnoreCase("CPWS")){
	 	    						returnvo.setOnGoingCpwsCount(returnvo.getOnGoingCpwsCount()+1L);
	 	    						returnvo.setOnGoingCpwsAmount(returnvo.getOnGoingCpwsAmount()+jObj.getLong("sanctionAmount"));
	 	    					}
		 	    			}
		 	    			
		 	    			if(assestName != null && assestName.trim().equalsIgnoreCase("PWS")){
 	    						returnvo.setPwsCount(returnvo.getPwsCount()+1L);
 	    						returnvo.setPwsAmount(returnvo.getPwsAmount()+jObj.getLong("sanctionAmount"));
 	    					}
 	    					else if(assestName != null && assestName.trim().equalsIgnoreCase("CPWS")){
 	    						returnvo.setCpwsCount(returnvo.getCpwsCount()+1L);
 	    						returnvo.setCpwsAmount(returnvo.getCpwsAmount()+jObj.getLong("sanctionAmount"));
 	    					}
		 	    		}
		 	    	}
	 	    	}
	 	      }
		} catch (Exception e) {
			LOG.error("Exception Occured in getExceededTargetWorksDetails() method, Exception - ",e);
		}
		return returnvo;
	}
	
	public IdNameVO getMatchedIdNameVO(List<IdNameVO> voList,String dayStr){
		if(voList != null && voList.size() > 0){
			for (IdNameVO vo : voList) {
				if(vo.getName().equalsIgnoreCase(dayStr))
					return vo;
			}
		}
		return null;
	}
	/**
	 * @author Santosh Kumar Verma
	 * @param InputVO inputVO
	 * @description {This service is used to get Rural Water Supply department work details.}
	 * @return List<IdNameVO>
	 * @Date 31-10-2017
	 */
	
	public List<IdNameVO> getExceededWorkDetailsLocationWise(InputVO inputVO){
		List<IdNameVO> resultList = new ArrayList<>(0);
		try {
			
			Map<String,IdNameVO> workDetailsMap = getWorkDetails();//getting work completed,dateofTarget date
			
			Map<String,ClientResponse> responseMap = new HashMap<>(0);
			List<InputVO> parameterList = getRequiredParamer(inputVO);
			String URL = IConstants.RWS_NIC_DOMINE_IP+"/rwscore/cd/getOnclickWorkDetails";
			if (parameterList != null && parameterList.size() > 0) {
				ExecutorService executor = Executors.newFixedThreadPool(5);
				for (InputVO inptVO : parameterList) {
					if (inptVO.getAssetTypeList() != null && inptVO.getAssetTypeList().size() > 0) {
						for (String workStatus : inptVO.getAssetTypeList()) {
							InputVO input = prepareRequiredInputDetails(workStatus,inptVO.getAssetType(), inputVO);
							String resultType = input.getAssetType() + "-"+ input.getWorkStatus();
							Runnable worker = new RWSThread(URL, responseMap, input, resultType);
							executor.execute(worker);
						}
					}
				}
			    executor.shutdown();
				while(!executor.isTerminated()) {}
				System.out.println("All work has finished "+responseMap.values());
			 }
			
			Map<String,IdNameVO> allWorksDtlsMap = getAssetTypeWiseAllWorkDetails(responseMap,workDetailsMap);
			Map<String,IdNameVO> resultMap = prepareWrokDtlsLocationWise (inputVO,allWorksDtlsMap);
			if (resultMap != null && resultMap.size() > 0 ) {
				resultList.addAll(new ArrayList<>(resultMap.values()));
				calculatingPercentage(resultList);
			}
	 	    }catch(Exception e){
	 	    	 LOG.error("Exception Occured in getExceededWorkDetailsLocationWise() method, Exception - ",e);
	 	    }
		return resultList;
		
	}

	private void calculatingPercentage(List<IdNameVO> resultList) {
		try {
			if (resultList.size() > 0) {
				for (Iterator<IdNameVO> it = resultList.iterator(); it.hasNext();) {
					IdNameVO vo = it.next();
					if (vo.getCount() == 0l) {
						it.remove();
					} else {
						if (vo.getSubList() != null && vo.getSubList().size() > 0) {
							for (IdNameVO assetTypeVO : vo.getSubList()) {

								if (assetTypeVO.getSubList() != null && assetTypeVO.getSubList().size() > 0) {
									for (IdNameVO rangeVO : assetTypeVO .getSubList()) {
										rangeVO.setPercentage(commonMethodsUtilService.calculatePercantage(rangeVO.getCount(),assetTypeVO.getCount()));
									}
								}

							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception Occured in calculatingPercentage() method, Exception - ",e);
		}
	}
   
	private Map<String, IdNameVO> prepareWrokDtlsLocationWise(InputVO inputVO,Map<String, IdNameVO> workDtlsMap) {
		Map<String, IdNameVO> locationMap = new HashMap<>();
		try {
			if (workDtlsMap != null && workDtlsMap.size() > 0) {
				for (Entry<String, IdNameVO> entry : workDtlsMap.entrySet()) {
					String locationIdStr = getLocationIdByLocationType(inputVO.getLocationType(), entry.getValue());
					IdNameVO locationVO = locationMap.get(locationIdStr);// getting locationId based on location type
					if (locationVO == null) {
						locationVO = new IdNameVO();
						locationVO.setLocationIdStr(locationIdStr);
						locationVO.setName(getLocationNameByLocationType(inputVO.getLocationType(), entry.getValue()));// getting locationName based on location type
						locationVO.setCount(0l);
						locationVO.setSanctionAmount(0l);
						locationVO.setSubList(getRequiredTemplate());// getting template
						locationMap.put(locationVO.getLocationIdStr(),locationVO);
					}
					for (IdNameVO assetTypeVO : locationVO.getSubList()) {

						for (IdNameVO workStatus : assetTypeVO.getSubList()) {
							IdNameVO matchVO = getMatchedVO(entry.getValue(),workStatus.getName(),assetTypeVO.getAssetType());
							if (matchVO != null) {
									workStatus.setCount(workStatus.getCount() + 1);
									assetTypeVO.setCount(assetTypeVO.getCount() + 1);
									locationVO.setCount(locationVO.getCount()+1);
									locationVO.setSanctionAmount(locationVO.getSanctionAmount()+ matchVO.getSanctionAmount());
									workStatus.setSanctionAmount(workStatus.getSanctionAmount()+matchVO.getSanctionAmount());
									assetTypeVO.setSanctionAmount(assetTypeVO.getSanctionAmount()+ matchVO.getSanctionAmount());
							}

						}
					}

				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in prepareWrokDtlsLocationWise() method, Exception - ",e);
		}
		return locationMap;
	}
	private IdNameVO getMatchedVO(IdNameVO vo,String name,String assetType){
		try {
			if(vo != null){
				if(vo.getName().equalsIgnoreCase(name) && vo.getAssetType().equalsIgnoreCase(assetType)) {
					return vo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getMatchedVO() method, Exception - ",e);
		}
		return null;
	}
	private String getLocationIdByLocationType(String locationType,IdNameVO workDtlsVO) {
		String locationIdStr="";
		try {
			if (locationType != null && locationType.trim().equalsIgnoreCase("state")){
				locationIdStr="01";
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("district")){
				locationIdStr = workDtlsVO.getDistrictCode();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("constituency")){
				locationIdStr = workDtlsVO.getConstituencyCode();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("mandal")){
				locationIdStr = workDtlsVO.getDistrictCode().concat(workDtlsVO.getMandalCode());
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationIdByLocationType() in RWSNICService class",e);
		}
		return locationIdStr;
	}
	private String getLocationNameByLocationType(String locationType,IdNameVO workDtlsVO) {
		String locationIdName="";
		try {
			if (locationType != null && locationType.trim().equalsIgnoreCase("state")){
				locationIdName = "Andhra Pradesh";
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("district")){
				locationIdName = workDtlsVO.getDistrictName();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("constituency")){
				locationIdName = workDtlsVO.getConstituencyName();
			} else if (locationType != null && locationType.trim().equalsIgnoreCase("mandal")){
				locationIdName = workDtlsVO.getMandalName();
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getLocationNameByLocationType() in RWSNICService class",e);
		}
		return locationIdName;
	}
	
	private Map<String,IdNameVO> getWorkDetails() {
		Map<String,IdNameVO> workDetailsMap = new HashMap<>();
		 try {
			    WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://rwss.ap.nic.in/rwscore/cd/getAllWorkComplitionDetails");	        
				String authStringEnc = getAuthenticationString("itgrids","Itgrids@123");	        
				ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class);
				
				if(response.getStatus() != 200){
		 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
		 	    }else{
		 	    	 String output = response.getEntity(String.class);
		 	    	 if(output != null && !output.isEmpty()){
		 	    		JSONArray finalArray = new JSONArray(output);
		 	    		for(int i=0;i<finalArray.length();i++){
		 	    			JSONObject jObj = (JSONObject) finalArray.get(i);
		 	    			String workId = jObj.getString("workId");
		 	    			IdNameVO datesvo =new IdNameVO();
		 	    			String completed = jObj.has("dateOfCompletion") ? jObj.getString("dateOfCompletion"):null;
		 	    			String target = jObj.has("targetDateComp") ? jObj.getString("targetDateComp"):null;
		 	    			datesvo.setTargetDate(getDateInStringFrormat(target));
			 	    		datesvo.setCompletionDate(getDateInStringFrormat(completed));
			 	    		workDetailsMap.put(workId, datesvo);
		 	    		}
		 	    	 }
		 	  }  
		 } catch (Exception e) {
			 LOG.error("Exception Occured in getWorkDetails() method, Exception - ",e);
		 }
		 return workDetailsMap;
	}

	private Map<String, IdNameVO> getAssetTypeWiseAllWorkDetails(Map<String, ClientResponse> responseMap,Map<String, IdNameVO> workTargetCompletedDateMap) {
		Map<String, IdNameVO> workDetailsMap = new HashMap<>();
		String currentDate = new DateUtilService().getCurrentDateInStringFormatYYYYMMDD();
		try {
			if (responseMap.size() > 0) {
				for (Entry<String, ClientResponse> responseEntry : responseMap.entrySet()) {
					if (responseEntry.getValue().getStatus() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + responseEntry.getValue().getStatus());
					} else {
						String output = responseEntry.getValue().getEntity(String.class);
						String[] assetTypeWrokStatusArr = responseEntry.getKey().split("-");
						String assetType = (assetTypeWrokStatusArr != null && assetTypeWrokStatusArr.length > 0) ? assetTypeWrokStatusArr[0] : "";
						String workStatus = (assetTypeWrokStatusArr != null && assetTypeWrokStatusArr.length > 1) ? assetTypeWrokStatusArr[1] : "";
						if (output != null && !output.isEmpty()) {
							JSONObject jsonObj = new JSONObject(output);
							String status = jsonObj.has("status") ? jsonObj.getString("status") : "";
							if (status.equalsIgnoreCase("Success")) {
								JSONArray finalArray = jsonObj.has("onClickWorksList") ? jsonObj.getJSONArray("onClickWorksList") : null;
								if (finalArray != null && finalArray.length() > 0) {
									for (int i = 0; i < finalArray.length(); i++) {

										JSONObject jObj = (JSONObject) finalArray.get(i);
										IdNameVO workDetailsVO = new IdNameVO();
										workDetailsVO.setAssetType(assetType);
										workDetailsVO.setWorkStatus(workStatus);
										workDetailsVO.setWrokIdStr(jObj.has("workId") ? jObj.getString("workId") : null);
										workDetailsVO.setMandalCode(jObj.has("mandalCode") ? jObj.getString("mandalCode") : null);
										workDetailsVO.setConstituencyCode(jObj.has("constituencyCode") ? jObj.getString("constituencyCode") : null);
										workDetailsVO.setConstituencyName(jObj.has("constituencyName") ? jObj.getString("constituencyName") : null);
										workDetailsVO.setDistrictCode(jObj.has("districtCode") ? jObj.getString("districtCode"): null);
										workDetailsVO.setDistrictName(jObj.has("districtName") ? jObj.getString("districtName"): null);
										workDetailsVO.setMandalName(jObj.has("mandalName") ? jObj.getString("mandalName") : null);
										if (workStatus.equalsIgnoreCase("ongoing")) {
											workDetailsVO.setSanctionAmount(Long.valueOf(commonMethodsUtilService.getStringValueForObject(jObj.getString("sacntionedAmount"))));
											workDetailsVO.setTargetDate( jObj.has("targetDate") ? jObj.getString("targetDate").substring(0, 10) : null);
											workDetailsVO.setCompletionDate(currentDate);
										} else if (workStatus.equalsIgnoreCase("completed")) {
											workDetailsVO.setSanctionAmount(Long.valueOf(commonMethodsUtilService.getStringValueForObject(jObj.getString("assestType"))));
											workDetailsVO.setCompletionDate(jObj.has("completionDate") ? jObj.getString("completionDate").substring(0, 10) : null);
											
											if (workTargetCompletedDateMap != null && workTargetCompletedDateMap.size() > 0) { //setting target date
												IdNameVO workDateDtlsVO = workTargetCompletedDateMap.get(workDetailsVO.getWrokIdStr());
												if (workDateDtlsVO != null) {
													workDetailsVO.setTargetDate(workDateDtlsVO.getTargetDate());
												}
											}
										}
										// calculating noOfDays between two difference date
										workDetailsVO.setNoOfDays(getNoOfDaysDifference(workDetailsVO.getCompletionDate(),workDetailsVO.getTargetDate(),workDetailsVO.getWorkStatus()));
                                        workDetailsVO.setName(getRangeLevelNameBasedOnDays(workDetailsVO.getNoOfDays()));
                                        workDetailsMap.put(workDetailsVO.getWrokIdStr(),workDetailsVO);
									}
								}

							}
						}
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception Occured in getWorkDetails() method, Exception - ",e);
		}
		return workDetailsMap;
	}
	private String getRangeLevelNameBasedOnDays(Long daysDiff) {
		String rangeLevelName="";
		 try {
			 if (daysDiff != null ) {
				 if(daysDiff >= 0L && daysDiff <= 30L) {
					 rangeLevelName = "0-30 Days";
				 }else if(daysDiff >= 31L && daysDiff <= 60L) {
					 rangeLevelName = "31-60 Days";
				 } else if( daysDiff >= 61L && daysDiff <= 90L) {
					 rangeLevelName = "61-90 Days";
				 } else if(daysDiff >= 91L && daysDiff <= 180L) {
					 rangeLevelName = "91-180 Days";
				 } else if(daysDiff >= 181L && daysDiff <= 365L) {
					 rangeLevelName = "181-365 Days";
				 } else if(daysDiff > 365L) {
					 rangeLevelName = "More Than 1 Year";
				 }
			 }
			
		 } catch (Exception e) {
			 LOG.error("Exception Occured in getRangeLevelNameBasedOnDays() method, Exception - ",e);
		 }
		 return rangeLevelName;
	}
	private Long getNoOfDaysDifference(String completionDate,String targetDate,String workStatus) {
		Long diffDays = null;
		 try {
		     if (completionDate != null && completionDate.trim().length() > 0 && targetDate != null && targetDate.trim().length() > 0) {
		    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	  Long completionDateTimeInMiliSecond =  sdf.parse(completionDate).getTime();
					 Long targetDateTimeInMiliSecond =  sdf.parse(targetDate).getTime();
					 if (completionDateTimeInMiliSecond >= targetDateTimeInMiliSecond) {
						 Long  diffTime = completionDateTimeInMiliSecond-targetDateTimeInMiliSecond;
							  diffDays = TimeUnit.MILLISECONDS.toDays(diffTime);
					 } 
		     }
		   
		 } catch (Exception e) {
			 LOG.error("Exception Occured in getNoOfDaysDifference() method, Exception - ",e);
		 }
		 return diffDays;
	}
	private String getDateInStringFrormat(String dateInMilisecondeFormat){
		 try {
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			 sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
	         if (dateInMilisecondeFormat != null && dateInMilisecondeFormat.length() > 0 ) {
	             Date date = new Date(Long.valueOf(dateInMilisecondeFormat));
		         String formatted = sdf.format(date);
		         Date dateFormat = sdf.parse(formatted);
		         String returnDate = sdf1.format(dateFormat);
		         return returnDate;     	 
	         }
	     } catch (Exception e) {
			 LOG.error("Exception Occured in getDateInStringFrormat() method, Exception - ",e);
		 }
		return null;
	}
	
	private List<IdNameVO> getRequiredTemplate() {
		List<IdNameVO> resultList = new ArrayList<>(0);
		String[] templateArr = {"0-30 Days","31-60 Days","61-90 Days","91-180 Days","181-365 Days","More Than 1 Year"};
		try {
			IdNameVO cpwsVO = new IdNameVO();
			cpwsVO.setAssetType("CPWS");
			cpwsVO.setCount(0l);
			cpwsVO.setSanctionAmount(0l);
			cpwsVO.setSubList(new ArrayList<IdNameVO>());
			for (int i = 0; i < templateArr.length; i++) {
				IdNameVO vo = new IdNameVO();
				vo.setId((long)i+1L);
				vo.setName(templateArr[i]);
				vo.setCount(0l);
				vo.setSanctionAmount(0l);
				cpwsVO.getSubList().add(vo);
		   }
			
			IdNameVO pwsVO = new IdNameVO();
			pwsVO.setAssetType("PWS");
			pwsVO.setCount(0l);
			pwsVO.setSanctionAmount(0l);
			pwsVO.setSubList(new ArrayList<IdNameVO>());
			for (int i = 0; i < templateArr.length; i++) {
				IdNameVO vo = new IdNameVO();
				vo.setId((long)i+1L);
				vo.setName(templateArr[i]);
				vo.setCount(0l);
				vo.setSanctionAmount(0l);
				pwsVO.getSubList().add(vo);
		   }
			resultList.add(cpwsVO);
		    resultList.add(pwsVO);
		} catch (Exception e) {
			 LOG.error("Exception Occured in getDateInStringFrormat() method, Exception - ",e);
		}
		return resultList;
	}
   private List<InputVO> getRequiredParamer(InputVO inputVO) {
		List<InputVO> parameterList = new ArrayList<>(0);
		try {
			if (inputVO.getAssetTypeList() != null && inputVO.getAssetTypeList().size() > 0 ) {
				for (String assettype:inputVO.getAssetTypeList()) {
					InputVO vo = new InputVO();
					vo.setAssetType(assettype);
					vo.setAssetTypeList(inputVO.getStatusList());
					parameterList.add(vo);
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getRequiredParamer() method, Exception - ",e);
		}
		return parameterList;
	}
	private InputVO prepareRequiredInputDetails(String workStatus,String assetType,InputVO inputVO){
		InputVO input = new InputVO();
		try {
			input.setAssetType(assetType);
			input.setDistrictValue(inputVO.getDistrictValue());
			input.setFilterType(inputVO.getFilterType());
			input.setFilterValue(inputVO.getFilterValue());
			input.setFromDateStr(inputVO.getFromDateStr());
			input.setToDateStr(inputVO.getToDateStr());
			input.setWorkStatus(workStatus);
			input.setYear(inputVO.getYear());
		} catch (Exception e) {
			LOG.error("Exception Occured in prepareRequiredInputDetails() method, Exception - ",e);
		}
		return input;
	}		
				
	public List<IdNameVO> getWebserviceDetails() {
		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		List<IdNameVO> finalvoList = new ArrayList<>();
		try{
		      //getting input url list
			List<NregaConsolidatedInputVO> urlsList = new ArrayList<NregaConsolidatedInputVO>(0);
			NregaConsolidatedInputVO inputVO = new NregaConsolidatedInputVO();
			inputVO.getComponentIds().addAll(IConstants.COMPONENT_IDS);
			if(inputVO != null && inputVO.getComponentIds() != null && !inputVO.getComponentIds().isEmpty()){
					List<Object[]> list = nregaComponentServiceDAO.getComponentUrlsByComponentIds(inputVO.getComponentIds(), "DATA");
					if(list != null && !list.isEmpty()){
						for (Object[] obj : list) {
							NregaConsolidatedInputVO vo = new NregaConsolidatedInputVO();
							vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							vo.setUrl(obj[2] != null ? obj[2].toString():"");
							vo.setComponentName(obj[1] != null ? obj[1].toString():"");
							vo.setId(Long.valueOf(obj[3]!= null ? obj[3].toString():"0"));
							urlsList.add(vo);
						}
					}
			    }	
			  //sending url list to thread class and saving in database
				List<WebserviceHealthVO> responseList = new ArrayList<WebserviceHealthVO>();
				String str = convertingStaticInputVOToString();
				String faStr = convertingInputVOToStringForFA();
			    if(urlsList != null && !urlsList.isEmpty()){
				  ExecutorService executor = Executors.newFixedThreadPool(30);
					 for (NregaConsolidatedInputVO urlvo : urlsList) {
							 if(urlvo.getId() != null && urlvo.getId().longValue() == 14l){
								Runnable worker = new NREGSCumulativeThreadPerformance(urlvo.getUrl(),urlvo.getId(),responseList,faStr);
								executor.execute(worker);
							   }else{
								Runnable worker = new NREGSCumulativeThreadPerformance(urlvo.getUrl(),urlvo.getId(),responseList,str);
								executor.execute(worker);
							   }
						    }	 
							executor.shutdown();
							while (!executor.isTerminated()) {
									
							}
							System.out.println("Finished all threads"+responseList);
		
					}
					
				if(responseList != null && !responseList.isEmpty()){
					for (WebserviceHealthVO response : responseList) {
			    
							WebserviceCallDetails webserviceCallDetails = new WebserviceCallDetails();
							webserviceCallDetails.setWebserviceId(response.getUrlId());
							webserviceCallDetails.setStatusCode(response.getStatusCode());
							webserviceCallDetails.setCallTime(response.getStartTime());
							webserviceCallDetails.setStatus(response.getStatus());
							webserviceCallDetails.setInputData(str);
							Date startDate =response.getStartTime();
							Date endDate = response.getEndDate();
							Long difference = endDate.getTime()-startDate.getTime();
							webserviceCallDetails.setTimeTaken(difference);
							webserviceCallDetailsDAO.save(webserviceCallDetails);
						
					}
			
				}
				//reterving web service data from database 
				Date endDate =dateUtilService.getCurrentDateAndTime();
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR, -1);
				Date oneHourBack = cal.getTime();
				Date startDate= oneHourBack;
				List<Object[]> webserviceList = webserviceCallDetailsDAO.getWebserviceDetails(startDate, endDate);
						if(webserviceList!= null && webserviceList.size()>0L){
							List<Long> webserviceIdList = new ArrayList<Long>();
								for(Object[] obj : webserviceList){
									Long webserviceId =commonMethodsUtilService.getLongValueForObject(obj[0]);
										if(!(webserviceIdList.contains(webserviceId))){
											Long timeTaken =(Long)obj[4]/1000L;
											if(timeTaken>5L){
												IdNameVO webservicevo = new IdNameVO();
												webservicevo.setId(webserviceId);
												webservicevo.setUrl(commonMethodsUtilService.getStringValueForObject(obj[1]));
												webservicevo.setName(commonMethodsUtilService.getStringValueForObject(obj[2]));
												webservicevo.setCallTime(commonMethodsUtilService.getStringValueForObject(obj[3]));
												webservicevo.setTimeTaken(commonMethodsUtilService.getStringValueForObject(timeTaken.toString()));
												webservicevo.setServiceProviderName(commonMethodsUtilService.getStringValueForObject(obj[5]));
												webservicevo.setWorkStatus(commonMethodsUtilService.getStringValueForObject(obj[6]));
												webserviceIdList.add(webserviceId);
												finalvoList.add(webservicevo);
												}
												
									    }
								}
							EmailAttributesVO vo =buildPDFTable(finalvoList);
							sendEmailWithPdfAttachment(vo);
					}		
				 }catch(Exception e){
					LOG.error("Exception occured in getWebserviceDetails method",e);
				 }
			return finalvoList;
	   }
		
	private String convertingInputVOToStringForFA() {
		String str ="";
		try{
			str = "{";
			
			str += "\"fromDate\" : \"2017-04-31"+"\",";
			str += "\"toDate\" : \"2017-11-31"+"\",";
			str += "\"year\" : \"2017"+"\",";
			str += "\"locationType\" : \"state"+"\",";
			str += "\"locationId\" : \"-1"+"\",";
			str += "\"SublocationType\" : \"mandal"+"\",";
			
		
		if(str.length() > 1)
			str = str.substring(0,str.length()-1);
		
		str += "}";
			
		}catch(Exception e){
	         LOG.error("Exception occured in convertingInputVOToStringForFA method",e);
		}
		return null;
	}

	public  String convertingStaticInputVOToString() {
		String str = "";
		try {
				
			str = "{";
			
				str += "\"fromDate\" : \"2017-04-31"+"\",";
				str += "\"toDate\" : \"2017-11-31"+"\",";
				str += "\"year\" : \"2017"+"\",";
				str += "\"locationType\" : \"state"+"\",";
				str += "\"locationId\" : \"-1"+"\",";
				str += "\"SublocationType\" : \"mandal"+"\",";
				str += "\"program\" : \"-1"+"\",";
			
			if(str.length() > 1)
				str = str.substring(0,str.length()-1);
			
			str += "}";
			
		} catch (Exception e) {
			LOG.error("Exception raised at convertingInputVOToString - NREGSConsolidatedService service", e);
		}
		return str;
		
	}
	/**
	 * @description to build pdf from table 
	 * @param emailAttributesVO
	 * @return ResultStatus 
	*/

	public EmailAttributesVO buildPDFTable(List<IdNameVO> finalvoList){
			try{
				EmailAttributesVO finalvo =new EmailAttributesVO();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mma");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
				String dt = sdf.format(new Date());
				String filename =sdf2.format(new Date());
				//String uniqueName=UUID.randomUUID().toString();
				String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL  + "PDF" ;//change this attr
				String pdfFileName =filename+".pdf";
			     String pdfPath = staticPath + "/"+ pdfFileName;
				 //String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL  +"PDF" + "/" +filename+".pdf";//change this at
				 finalvo.setFilePath(staticPath);
				 finalvo.setDate(dt);
				 finalvo.setFileName(pdfFileName);
				 new File(staticPath).mkdir();
			     FileOutputStream out=new FileOutputStream(pdfPath);
			     Rectangle pageSize = new Rectangle(PageSize.A4);
			     pageSize.setBackgroundColor(new BaseColor(255,255,255));
			    // Document document = new Document( pageSize );
			     Document  document=new Document(pageSize);
			     PdfWriter writer=PdfWriter.getInstance(document,out);
			     Font fontHead = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, BaseColor.WHITE);
			     Font fontBody = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, BaseColor.BLACK);
			     Chunk c = new Chunk("WEBSERVICE TIME INFORMATION", fontBody);
			     //c.setBackground(BaseColor.BLUE);
			     Paragraph paragraph=new Paragraph(c);
		         paragraph.setAlignment(Element.ALIGN_CENTER);
		         
		         Paragraph paragraph1=new Paragraph();
			     paragraph1.add("      DATE:"+dt);
			     paragraph1.setAlignment(Element.ALIGN_CENTER);
			     paragraph1.add(Chunk.NEWLINE);
			     document.open();
			       
			        PdfPTable table = new PdfPTable(5); // 3 columns.
			        table.setWidthPercentage(100); //Width 100%
			        table.setSpacingBefore(10f); //Space before table
			        table.setSpacingAfter(10f); //Space after table
			        
			        //Set Column widths
			        float[] columnWidths = {2f,4f, 6f, 2f,2f};
			        table.setWidths(columnWidths);
			        PdfPCell cell0 = new PdfPCell(new Paragraph("Service Provider Name",fontHead));
			        cell0.setBorderColor(BaseColor.BLACK);
			        cell0.setBackgroundColor(BaseColor.DARK_GRAY);
			        cell0.setPaddingLeft(10);
			        cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell0.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        
			        PdfPCell cell1 = new PdfPCell(new Paragraph("Service Name",fontHead));
			        cell1.setBorderColor(BaseColor.BLACK);
			        cell1.setBackgroundColor(BaseColor.DARK_GRAY);
			        cell1.setPaddingLeft(10);
			        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			 
			        PdfPCell cell2 = new PdfPCell(new Paragraph("Url",fontHead));
			        cell2.setBorderColor(BaseColor.BLACK);
			        cell2.setBackgroundColor(BaseColor.DARK_GRAY);
			        cell2.setPaddingLeft(10);
			        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	
			        PdfPCell cell3 = new PdfPCell(new Paragraph("Time Taken(sec)",fontHead));
			        cell3.setBorderColor(BaseColor.BLACK);
			        cell3.setBackgroundColor(BaseColor.DARK_GRAY);
			        cell3.setPaddingLeft(10);
			        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        
			        PdfPCell cell4 = new PdfPCell(new Paragraph("Status",fontHead));
			        cell4.setBorderColor(BaseColor.BLACK);
			        cell4.setBackgroundColor(BaseColor.DARK_GRAY);
			        cell4.setPaddingLeft(10);
			        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        
			        table.addCell(cell0);
			        table.addCell(cell1);
			        table.addCell(cell2);
			        table.addCell(cell3);
			        table.addCell(cell4);
			 
			      for(int i=0;i<finalvoList.size();i++){
				    	  
					    	  PdfPCell providerName=new PdfPCell(new Phrase(finalvoList.get(i).getServiceProviderName()));
						        providerName.setBorderColor(BaseColor.BLACK);
						        providerName.setBackgroundColor(BaseColor.WHITE);
						        providerName.setPaddingLeft(10);
						        providerName.setHorizontalAlignment(Element.ALIGN_CENTER);
						        providerName.setVerticalAlignment(Element.ALIGN_MIDDLE);
						        table.addCell(providerName);
							        	
						        PdfPCell nameCell=new PdfPCell(new Phrase(finalvoList.get(i).getName()));
						        nameCell.setBorderColor(BaseColor.BLACK);
						        nameCell.setBackgroundColor(BaseColor.WHITE);
						        nameCell.setPaddingLeft(10);
						        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						        nameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						        table.addCell(nameCell);
						 	 
						        PdfPCell urlCell=new PdfPCell(new Phrase(finalvoList.get(i).getUrl()));
						        
						 	     urlCell.setBorderColor(BaseColor.BLACK);
						 	     urlCell.setBackgroundColor(BaseColor.WHITE);
						 	     urlCell.setPaddingLeft(10);
						 	     urlCell.setHorizontalAlignment(Element.ALIGN_CENTER);
						 	     urlCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						 	     table.addCell(urlCell);
						 	   
						 	    PdfPCell timeTakenInsec=new PdfPCell(new Phrase(finalvoList.get(i).getTimeTaken()));
						 	      timeTakenInsec.setBorderColor(BaseColor.BLACK);
						 	     timeTakenInsec.setBackgroundColor(BaseColor.WHITE);
						 	      timeTakenInsec.setPaddingLeft(10);
						 	      timeTakenInsec.setHorizontalAlignment(Element.ALIGN_CENTER);
						 	      timeTakenInsec.setVerticalAlignment(Element.ALIGN_MIDDLE);
						 	     table.addCell(timeTakenInsec);
						 	     
						 	    PdfPCell status=new PdfPCell(new Phrase(finalvoList.get(i).getWorkStatus()));
						 	      status.setBorderColor(BaseColor.BLACK);
						 	      status.setBackgroundColor(BaseColor.WHITE);
						 	      status.setPaddingLeft(10);
						 	      status.setHorizontalAlignment(Element.ALIGN_CENTER);
						 	      status.setVerticalAlignment(Element.ALIGN_MIDDLE);
						 	     table.addCell(status);
				 
				        }
				        
				        document.add(paragraph);
				        document.add(paragraph1);
				        
				        document.add(table);
				 
				        document.close();
				        writer.close();
				        return finalvo;
			  }catch(Exception e){
			LOG.error("exception occured in pdf to table format",e);
	        }
		return null;
    }
	/**
	 * @description to  send Email with pdf attachment 
	 * @param emailAttributesVO
	 * @return ResultStatus 
	*/
	
	public ResultStatus sendEmailWithPdfAttachment(EmailAttributesVO emailAttributesVO){
	      ResultStatus resultStatus = new ResultStatus();
	      try{
		        DateUtilService dateUtilService = new DateUtilService();
		        
		        String host = IConstants.DEFAULT_MAIL_SERVER;
		        Session session = commonMethodsUtilService.getNewSessionObject(host);
			        if(session == null)
			        {
			          //LOG.error("MimeMessage Object is Not Created");  
			          resultStatus.setResultCode(0);
			          return resultStatus;
			        }
		        //sending mail to client  
			        try{        
			        MimeMessage message = new MimeMessage(session);    
			        
			        message.setFrom(new InternetAddress(IConstants.EMAIL_USERNAME));  
			        
			        message.addRecipient(Message.RecipientType.TO, new InternetAddress("accounts@telugudesam.org")); 
			        //message.addRecipient(Message.RecipientType.TO, new InternetAddress("a.dakavaram@itgrids.com"));  
			        message.addRecipient(Message.RecipientType.TO, new InternetAddress("swadhin.lenka@itgrids.com"));    
			    
			        message.setHeader("Return-Path", IConstants.EMAIL_USERNAME);
			        message.setSentDate(dateUtilService.getCurrentDateAndTime());
			        message.setSubject(" This Is webService perfomance Status ");//change this attr
			        Multipart multipart = new MimeMultipart();
			         
			          BodyPart htmlPart = new MimeBodyPart();
			          StringBuilder msgText= new StringBuilder();    
			          
			          msgText.append("Dear Sir,<br><br> Please find the attached  pdf document for webservice performance details for the time:"+emailAttributesVO.getDate()) ;
			       
			          
			          msgText.append("<br><br>Thanks");
			          msgText.append("<br>IT TEAM");
			          htmlPart.setContent(msgText.toString(),"text/html");
			          multipart.addBodyPart(htmlPart);
			          String pdfFileName = emailAttributesVO.getFileName();////change this attr
			          String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL  + "PDF" ;//change this attr
				      String pdfPath = staticPath + "/"+ pdfFileName;
				      DataSource source = new FileDataSource(pdfPath);
			          BodyPart  attachment  = new MimeBodyPart();
			          attachment.setDataHandler(new DataHandler(source));
			          attachment.setFileName(emailAttributesVO.getFileName());////change this attr
			          multipart.addBodyPart(attachment);
			           
			          message.setContent(multipart);
			           
				          if(host.equalsIgnoreCase(IConstants.LOCALHOST))  
				          {
				             Transport transport = session.getTransport("smtp");
				              transport.connect(IConstants.HOST,IConstants.EMAIL_USERNAME,IConstants.EMAIL_PASSWORD);
				              transport.sendMessage(message, message.getAllRecipients());
				              transport.close();
				        }
				        else{
				              Transport.send(message);
				        }
			          resultStatus.setMessage(IConstants.SUCCESS);
			        resultStatus.setResultCode(1); 
			        }catch(Exception e){
		          //LOG.error("Exception in sending mail : ",e);
		          resultStatus.setMessage(IConstants.FAILURE);
		          resultStatus.setResultCode(0);
		        }
		        return resultStatus;
		      }catch(Exception e){
		        resultStatus.setExceptionEncountered(e);
		        resultStatus.setExceptionMsg(e.getMessage());
		        resultStatus.setResultCode(0);
		        return resultStatus;
		      }
	    }
 }
