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
import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.dao.IUserPrintVendorDAO;
import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.dto.PrintStatusUpdateVO;
import com.itgrids.cardprint.dto.ResultStatus;
import com.itgrids.cardprint.model.ConstituencyPrintStatus;
import com.itgrids.cardprint.model.ConstituencyPrintStatusTrack;
import com.itgrids.cardprint.service.ICardPrintService;
import com.itgrids.cardprint.util.DateUtilService;

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
	
	public ResultStatus saveConstituencyPrintStatus(final PrintStatusUpdateVO inputVO){
		
		ResultStatus status = new ResultStatus();
		try{
				status = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus arg0) {
			        	
			        	Date currentTime = dateUtilService.getCurrentDateAndTime();
			        	ResultStatus rs = new ResultStatus();
			        	 
			        	//Constituency Print Status saving
			        	ConstituencyPrintStatus constituencyPrintStatus = new ConstituencyPrintStatus();
			        	constituencyPrintStatus.setConstituencyId(inputVO.getConstituencyId());
			        	
			        	if(inputVO.getUserId() != null && inputVO.getUserId() > 0l){
			        		List<Long> printVendorsList = userPrintVendorDAO.getPrintVendorIdByUserId(inputVO.getUserId());
			        		if(printVendorsList != null && printVendorsList.size() > 0){
			        			Long vendorId = printVendorsList.get(0);
			        			constituencyPrintStatus.setPrintVendorId(vendorId);
			        		}else{
			        			rs.setResultCode(0);
			        			rs.setExceptionMsg("Vendor Is Not Mapped to This User.");
			        			return rs;
			        		}
			        	}
			        	
			        	constituencyPrintStatus.setPrintStatusId(inputVO.getPrintStatusId());
			        	if(inputVO.getRemarks() != null && !inputVO.getRemarks().isEmpty()){
			        		constituencyPrintStatus.setRemarks(inputVO.getRemarks());
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
			        	
			        	rs.setExceptionMsg("success");
						rs.setResultCode(1);
						return rs;
					}
			   });
		}catch(Exception e){
			LOG.error("exception Occurred at saveConstituencyPrintStatus() in CardPrintService class ", e); 
			status.setResultCode(0);
			status.setExceptionMsg("failure");
		}
		return status;
	}
}
