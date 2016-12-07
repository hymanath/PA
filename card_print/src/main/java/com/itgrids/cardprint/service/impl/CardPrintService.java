package com.itgrids.cardprint.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.cardprint.dao.ICardPrintVendorDAO;
import com.itgrids.cardprint.dao.IConstituencyDAO;
import com.itgrids.cardprint.dao.IConstituencyPrintStatusDAO;
import com.itgrids.cardprint.dao.IConstituencyPrintStatusTrackDAO;
import com.itgrids.cardprint.dao.IPrintStatusDAO;
import com.itgrids.cardprint.dto.BasicVO;
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

	//Business methods
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
	public List<BasicVO>  getAllPrintStatus(){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = printStatusDAO.getAllPrintStatus();
			setBasicDataToVO( dataList , finalList );
			
		}catch(Exception e){
			LOG.error("exception Occurred at getAllPrintStatus() in CardPrintService class ", e); 
		}
		return finalList;
	}
	public List<BasicVO>  getAllAssemblyConstituencies(){
		List<BasicVO> finalList = null;
		try{
			
			List<Object[]> dataList = constituencyDAO.getAllAssemblyConstituencies();
			setBasicDataToVO( dataList , finalList );
			
		}catch(Exception e){
			LOG.error("exception Occurred at getAllAssemblyConstituencies() in CardPrintService class ", e); 
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
	
	public ResultStatus saveConstituencyPrintStatus(final Long constituencyId , final Long printVendorId , final Long printStatusId,final String remarks,final Long userId){
		
		 final ResultStatus rs = new ResultStatus();
		try{
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			        protected void doInTransactionWithoutResult(TransactionStatus arg0) {
			        	
			        	Date currentTime = dateUtilService.getCurrentDateAndTime();
			        	
			        	//Constituency Print Status saving
			        	ConstituencyPrintStatus constituencyPrintStatus = new ConstituencyPrintStatus();
			        	constituencyPrintStatus.setConstituencyId(constituencyId);
			        	constituencyPrintStatus.setPrintVendorId(printVendorId);
			        	constituencyPrintStatus.setPrintStatusId(printStatusId);
			        	if(remarks != null && !remarks.isEmpty()){
			        		constituencyPrintStatus.setRemarks(remarks);
			        	}
			        	constituencyPrintStatus.setUpdatedBy(userId);
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
			        	
			        	rs.setResultCode(1);
			        	rs.setMessage("success");
					}
			   });
		}catch(Exception e){
			LOG.error("exception Occurred at saveConstituencyPrintStatus() in CardPrintService class ", e); 
			rs.setResultCode(0);
			rs.setMessage("failure");
		}
		return rs;
	}
}
