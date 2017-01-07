package com.itgrids.cardprint.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.cardprint.dao.IConstituencyPrintStatusDAO;
import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.dao.IUserPrintVendorDAO;
import com.itgrids.cardprint.dto.PrintStatusVO;
import com.itgrids.cardprint.service.ICardPrintAdminService;

public class CardPrintAdminService implements ICardPrintAdminService {
	
	private static final Logger LOG = Logger.getLogger(CardPrintAdminService.class);
	
	//Attributes
	private IConstituencyPrintStatusDAO constituencyPrintStatusDAO;
	private IUserPrintVendorDAO userPrintVendorDAO;
	private IPrintStatusDAO printStatusDAO;
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
	 
	
}
