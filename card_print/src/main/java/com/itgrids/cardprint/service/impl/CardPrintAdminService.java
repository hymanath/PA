package com.itgrids.cardprint.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.cardprint.dao.IConstituencyPrintStatusDAO;
import com.itgrids.cardprint.dao.IMaxPrintDetailsDAO;
import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.dao.IUserPrintVendorDAO;
import com.itgrids.cardprint.dao.IZebraPrintDetailsDAO;
import com.itgrids.cardprint.dto.PrintStatusVO;
import com.itgrids.cardprint.service.ICardPrintAdminService;
import com.itgrids.cardprint.util.IConstants;

public class CardPrintAdminService implements ICardPrintAdminService {
	
	private static final Logger LOG = Logger.getLogger(CardPrintAdminService.class);
	
	//Attributes
	private IConstituencyPrintStatusDAO constituencyPrintStatusDAO;
	private IUserPrintVendorDAO userPrintVendorDAO;
	private IPrintStatusDAO printStatusDAO;
	private IMaxPrintDetailsDAO maxPrintDetailsDAO;
	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	
	//setters
	public void setConstituencyPrintStatusDAO(
			IConstituencyPrintStatusDAO constituencyPrintStatusDAO) {
		this.constituencyPrintStatusDAO = constituencyPrintStatusDAO;
	}
	
	public void setUserPrintVendorDAO(IUserPrintVendorDAO userPrintVendorDAO) {
		this.userPrintVendorDAO = userPrintVendorDAO;
	}
	
	public void setPrintStatusDAO(IPrintStatusDAO printStatusDAO) {
		this.printStatusDAO = printStatusDAO;
	}
	
	public void setMaxPrintDetailsDAO(IMaxPrintDetailsDAO maxPrintDetailsDAO) {
		this.maxPrintDetailsDAO = maxPrintDetailsDAO;
	}

	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}
	
	//business methods
	public Long getPrintVendorIdByLoggedInUser(Long userId){
		Long cardPrintVendorId = null;
		try{
			  List<Long> list = userPrintVendorDAO.getPrintVendorIdByUserId(userId);
			  if(list != null && list.size() > 0){
				  cardPrintVendorId = list.get(0);
			  }
		}catch(Exception e) {
			LOG.error("exception Occurred at getPrintVendorIdByLoggedInUser() in CardPrintAdminService class ", e); 
		}
		return cardPrintVendorId;
	}
	
	/////////1
	public List<PrintStatusVO> getPrintStatusWiseConstitCountByLoggedUser(Long cardPrintVendorId){
		
		List<PrintStatusVO> finalList = null;
		
		try{
			  Map<Long , PrintStatusVO> finalMap = new LinkedHashMap<Long , PrintStatusVO>(0);
			  List<Object[]> statusList = printStatusDAO.getAllPrintStatus();
			  if(statusList != null && statusList.size() > 0){
				  for(Object[] obj :statusList){
					  if(obj[0]!= null){
						  finalMap.put((Long)obj[0], new PrintStatusVO((Long)obj[0] , obj[1]!=null?obj[1].toString() :""));
					  }
				  }
			  }
			  if(cardPrintVendorId != null && cardPrintVendorId > 0l)
			  {  
				  List<Object[]> dataList = constituencyPrintStatusDAO.getPrintStatusWiseConstituenciesCount(cardPrintVendorId);
				  if(dataList != null && dataList.size() > 0)
				  {
					 for(Object[] obj : dataList){
						 if(obj[0]!=null){
							 PrintStatusVO statusVO = finalMap.get((Long)obj[0]);
							 if(statusVO != null){
								 statusVO.setCount(obj[1]!=null ? (Long)obj[1] :0l);
							 }
						 }
					 }
				  }
			  }
			
			  if(finalMap != null && finalMap.size() > 0){
				  finalList = new ArrayList<PrintStatusVO>(finalMap.values());
			  }
		}catch(Exception e){
			LOG.error("exception Occurred at getPrintStatusWiseConstitCountByLoggedUser() in CardPrintAdminService class ", e); 
		}
		return finalList;
	}
	
     /////////2
	 public List<PrintStatusVO> getPrintStatusWiseRecordCountByLoggedUSer(Long cardPrintVendorId){
		 List<PrintStatusVO> finalList = null;
		 try{
			 
			 Map<String,PrintStatusVO>	finalMap = new LinkedHashMap<String, PrintStatusVO>(0);
			 
			 String statusString = "E-Verification Failed , Y-Printed , N-Not Printed ,F-Print Failed , D-Allocated  "  ;
			 
			 for(String status : statusString.split(",")){
				 PrintStatusVO statusVO = new  PrintStatusVO();
				 String[] names = status.split("-");
				 statusVO.setStatus(names[0].trim());
				 statusVO.setName(names[1].trim());
				 finalMap.put(statusVO.getStatus(), statusVO);
			 }
			   
			   
			   List<Object[]> data = null;
			   
			   if(cardPrintVendorId.longValue() == IConstants.MAX_PRINT_VENDOR_ID.longValue()){
					  
					 data = maxPrintDetailsDAO.getPrintStatusWiseRecordsCount();
					 
				 }else if(cardPrintVendorId.longValue() == IConstants.ZEBRA_PRINT_VENDOR_ID.longValue()){
					 
					 data =  zebraPrintDetailsDAO.getPrintStatusWiseRecordsCount();
				 }
			   
			   if(data != null && data.size() > 0){
				   for(Object[] obj :data){
					   if(obj[0]!=null){
						   PrintStatusVO statusVO = finalMap.get(obj[0].toString());
						   if(statusVO != null){
							   statusVO.setCount(obj[1]!=null ? (Long)obj[1] :0l); 
						   }
					   }
				   }
			   }
			   
			   if(finalMap != null && finalMap.size() > 0){
				   finalList = new ArrayList<PrintStatusVO>(finalMap.values());
			   }
		}catch (Exception e){
			LOG.error("exception Occurred at getPrintStatusWiseRecordCountByLoggedUSer() in CardPrintAdminService class ", e); 
		}
		 return finalList;
	 }
	 
	 //////3
	 public List<PrintStatusVO> constWisePrintStatusWiseRecordCountByLoggedUSer(Long cardPrintVendorId){
		  List<PrintStatusVO> finalList = null;
		 try{
			 
			  String statusString = "E-Verification Failed , Y-Printed , N-Not Printed ,F-Print Failed , D-Allocated  " ;
			  
			  Map<Long ,PrintStatusVO>	constituencyMap = new LinkedHashMap<Long, PrintStatusVO>(0);
			  
			  List<Object[]> data = null;
			  
			   if(cardPrintVendorId.longValue() == IConstants.MAX_PRINT_VENDOR_ID.longValue()){
					  
				 data = maxPrintDetailsDAO.getConstWisePrintStatusWiseRecordsCount();
				 
			   }else if(cardPrintVendorId.longValue() == IConstants.ZEBRA_PRINT_VENDOR_ID.longValue()){
				 
			 	 data =  zebraPrintDetailsDAO.getConstWisePrintStatusWiseRecordsCount();
			   }
			     
			   if(data != null && data.size() > 0){
				   for(Object[] obj : data){
					   if(obj[0]!=null){//constituencyId
						   PrintStatusVO constituencyVO = constituencyMap.get((Long)obj[0]);
						   if(constituencyVO == null){
							   constituencyVO = new PrintStatusVO();
							   constituencyVO.setId((Long)obj[0]);
							   constituencyVO.setName(obj[1]!=null?obj[1].toString() :"");
							   constituencyVO.setDistrictName(obj[3]!=null?obj[3].toString():"");
							   //add status.
							   addAllStatusVO(statusString , constituencyVO);
							   constituencyMap.put((Long)obj[0], constituencyVO);
						   }
						   constituencyVO = constituencyMap.get((Long)obj[0]);
						   if(obj[4]!=null){//status
							   Map<String,PrintStatusVO> statusMap = constituencyVO.getSubMap();
							   PrintStatusVO statusVO = statusMap.get(obj[4].toString());
							   if(statusVO != null){
								   statusVO.setCount(obj[5]!=null ? (Long)obj[5] : 0l);
							   }
						   }
					   }
				   }
			   }
			   
			   //conv
			   if(constituencyMap != null && constituencyMap.size() > 0){
				   
				   for (Map.Entry<Long, PrintStatusVO> constEntry : constituencyMap.entrySet())
				   {
					   if(constEntry.getValue() != null && constEntry.getValue().getSubMap() != null && constEntry.getValue().getSubMap().size() > 0)
					   {
						   constEntry.getValue().setSubList(new ArrayList<PrintStatusVO>(constEntry.getValue().getSubMap().values()));
						   constEntry.getValue().getSubMap().clear(); 
					   }
				   }
				   finalList = new ArrayList<PrintStatusVO>(constituencyMap.values());
			   }
			   
		}catch(Exception e) {
			LOG.error("exception Occurred at constWisePrintStatusWiseRecordCountByLoggedUSer() in CardPrintAdminService class ", e);
		}
		 return finalList;
	 }
	 
	 public void addAllStatusVO(String statusString,PrintStatusVO constituencyVO){
		 
		 Map<String,PrintStatusVO>	finalMap = new LinkedHashMap<String, PrintStatusVO>(0);
		 for(String status : statusString.split(",")){
			 PrintStatusVO statusVO = new  PrintStatusVO();
			 String[] names = status.split("-");
			 statusVO.setStatus(names[0].trim());
			 statusVO.setName(names[1].trim());
			 finalMap.put(statusVO.getStatus(), statusVO);
		 }
		 constituencyVO.setSubMap(finalMap);
	 }
}
