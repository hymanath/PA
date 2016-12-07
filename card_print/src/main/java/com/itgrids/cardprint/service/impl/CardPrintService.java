package com.itgrids.cardprint.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.cardprint.dao.ICardPrintVendorDAO;
import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.service.ICardPrintService;

public class CardPrintService implements ICardPrintService{

	private static final Logger LOG = Logger.getLogger(CardPrintService.class);
	
	private ICardPrintVendorDAO cardPrintVendorDAO;

	public void setCardPrintVendorDAO(ICardPrintVendorDAO cardPrintVendorDAO) {
		this.cardPrintVendorDAO = cardPrintVendorDAO;
	}
	
	public List<BasicVO>  getAllVendors(){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = cardPrintVendorDAO.getAllVendors();
			setBasicDataToVO( dataList , finalList );
			
		}catch(Exception e){
			LOG.error("exception Occurred at getAllVendors() in CardPrintService class ", e); 
		}
		return finalList;
	}
	
	public void setBasicDataToVO(List<Object[]> dataList , List<BasicVO> finalList){
		
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
	}
}
