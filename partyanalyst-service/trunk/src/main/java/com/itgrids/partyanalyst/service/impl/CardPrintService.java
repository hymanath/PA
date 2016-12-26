package com.itgrids.partyanalyst.service.impl;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICardPrintValidationDAO;
import com.itgrids.partyanalyst.dao.ICardPrintVendorDAO;
import com.itgrids.partyanalyst.dao.IConstituencyPrintStatusDAO;
import com.itgrids.partyanalyst.dao.IPrintStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCardPrintDAO;
import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.dto.CardPrintingDispatchVO;
import com.itgrids.partyanalyst.dto.PrintUpdateDetailsStatusVO;
import com.itgrids.partyanalyst.dto.PrintVO;
import com.itgrids.partyanalyst.model.CardPrintVendor;
import com.itgrids.partyanalyst.model.TdpCadreCardPrint;
import com.itgrids.partyanalyst.service.ICardPrintService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class CardPrintService implements ICardPrintService{

	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	
	private ICardPrintVendorDAO cardPrintVendorDAO;
	private IConstituencyPrintStatusDAO constituencyPrintStatusDAO;
	private DateUtilService dateUtilService;
	private ITdpCadreCardPrintDAO tdpCadreCardPrintDAO;
	private IPrintStatusDAO printStatusDAO;
	private ICardPrintValidationDAO cardPrintValidationDAO;
	private TransactionTemplate transactionTemplate;
	
	
	public ICardPrintValidationDAO getCardPrintValidationDAO() {
		return cardPrintValidationDAO;
	}
	public void setCardPrintValidationDAO(
			ICardPrintValidationDAO cardPrintValidationDAO) {
		this.cardPrintValidationDAO = cardPrintValidationDAO;
	}
	public IPrintStatusDAO getPrintStatusDAO() {
		return printStatusDAO;
	}
	public void setPrintStatusDAO(IPrintStatusDAO printStatusDAO) {
		this.printStatusDAO = printStatusDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public ITdpCadreCardPrintDAO getTdpCadreCardPrintDAO() {
		return tdpCadreCardPrintDAO;
	}
	public void setTdpCadreCardPrintDAO(ITdpCadreCardPrintDAO tdpCadreCardPrintDAO) {
		this.tdpCadreCardPrintDAO = tdpCadreCardPrintDAO;
	}
	public ICardPrintVendorDAO getCardPrintVendorDAO() {
		return cardPrintVendorDAO;
	}
	public void setCardPrintVendorDAO(ICardPrintVendorDAO cardPrintVendorDAO) {
		this.cardPrintVendorDAO = cardPrintVendorDAO;
	}
	public IConstituencyPrintStatusDAO getConstituencyPrintStatusDAO() {
		return constituencyPrintStatusDAO;
	}
	public void setConstituencyPrintStatusDAO(IConstituencyPrintStatusDAO constituencyPrintStatusDAO) {
		this.constituencyPrintStatusDAO = constituencyPrintStatusDAO;
	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public CardPrintVO getStatusWisePrintingConstituencyDetails(Long stateId,Long vendorId,String startDateStr,String endDateStr){
		CardPrintVO returnvo = new CardPrintVO();
		try {
			Map<Long,CardPrintVO> statusWiseMap = new LinkedHashMap<Long, CardPrintVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date fromDate = null;
			Date toDate = null;
			
			if(startDateStr != null && endDateStr != null){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(endDateStr);
			}
			
			List<Object[]> statusList = printStatusDAO.getAllPrintStatus();
			List<CardPrintVO> tempList = getAllPrintStatus(statusList);
			if(tempList != null && !tempList.isEmpty()){
				for (CardPrintVO vo : tempList) {
					statusWiseMap.put(vo.getId(), vo);
				}
			}
			
			List<Object[]> list = constituencyPrintStatusDAO.getStatusWisePrintingConstituencyDetails(stateId, vendorId, fromDate, toDate);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] !=null ? obj[1].toString():"0");
					String name = obj[2] != null ? obj[2].toString():"";
					
					if(id != null && (id.longValue() == 1l || id.longValue() == 2l || id.longValue() == 3l)){
						id = 1l;
						name = "Print Data Ready";
					}
						
					CardPrintVO vo = statusWiseMap.get(id);
					if(vo == null){
						vo = new CardPrintVO();
						vo.setId(id);
						if(id.longValue() == 4l)
							vo.setName("Print Pending");
						else
							vo.setName(name);
						vo.setCount(count);
						
						statusWiseMap.put(id, vo);
					}
					else{
						vo.setCount(vo.getCount()+count);
					}
				}
			}
			List<Object[]> cardsCountList = tdpCadreCardPrintDAO.getStatusWisePrintingCardsCounts(stateId, vendorId, fromDate, toDate);
			if(cardsCountList != null && !cardsCountList.isEmpty()){
				for (Object[] obj : cardsCountList) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] !=null ? obj[1].toString():"0");
					
					if(id != null && (id.longValue() == 1l || id.longValue() == 2l || id.longValue() == 3l))
						id = 1l;
											
					CardPrintVO vo = statusWiseMap.get(id);
					if(vo != null)
						vo.setCardsCount(vo.getCardsCount()+count);
				}
			}
			
			if(statusWiseMap != null){
				returnvo.setOverAllList(new ArrayList<CardPrintVO>(statusWiseMap.values()));
				statusWiseMap = new LinkedHashMap<Long, CardPrintVO>();
			}
			
			List<CardPrintVO> tempListForToday = getAllPrintStatus(statusList);
			if(tempListForToday != null && !tempListForToday.isEmpty()){
				for (CardPrintVO vo : tempListForToday) {
					statusWiseMap.put(vo.getId(), vo);
				}
			}
			fromDate = dateUtilService.getCurrentDateAndTime();
			toDate = dateUtilService.getCurrentDateAndTime();
			
			List<Object[]> list1 = constituencyPrintStatusDAO.getStatusWisePrintingConstituencyDetails(stateId, vendorId, fromDate, toDate);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] !=null ? obj[1].toString():"0");
					String name = obj[2] != null ? obj[2].toString():"";
					
					if(id != null && (id.longValue() == 1l || id.longValue() == 2l || id.longValue() == 3l)){
						id = 1l;
						name = "Print Data Ready";
					}
						
					CardPrintVO vo = statusWiseMap.get(id);
					if(vo == null){
						vo = new CardPrintVO();
						vo.setId(id);
						if(id.longValue() == 4l)
							vo.setName("Print Pending");
						else
							vo.setName(name);
						vo.setCount(count);
						
						statusWiseMap.put(id, vo);
					}
					else{
						vo.setCount(vo.getCount()+count);
					}
				}
			}
			List<Object[]> cardsCountList1 = tdpCadreCardPrintDAO.getStatusWisePrintingCardsCounts(stateId, vendorId, fromDate, toDate);
			if(cardsCountList1 != null && !cardsCountList1.isEmpty()){
				for (Object[] obj : cardsCountList1) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long count = Long.valueOf(obj[1] !=null ? obj[1].toString():"0");
					
					if(id != null && (id.longValue() == 1l || id.longValue() == 2l || id.longValue() == 3l))
						id = 1l;
											
					CardPrintVO vo = statusWiseMap.get(id);
					if(vo != null)
						vo.setCardsCount(vo.getCardsCount()+count);
				}
			}
			
			returnvo.setTodayDate(dateUtilService.getCurrentDateInStringFormat());
			if(statusWiseMap != null)
				returnvo.setTodayList(new ArrayList<CardPrintVO>(statusWiseMap.values()));
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getStatusWisePrintingConstituencyDetails method in CardPrintService", e);
		}
		return returnvo;
	}
	
	public CardPrintVO getDistrictWiseStatusWiseConstituenciesCounts(Long vendorId,String fromDateStr,String toDateStr){
		CardPrintVO returnvo = new CardPrintVO();
		try {
			Map<Long,CardPrintVO> districtMap = new LinkedHashMap<Long, CardPrintVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date fromDate = null;
			Date toDate = null;
			
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> statusList = printStatusDAO.getAllPrintStatus();
			
			List<Object[]> list = constituencyPrintStatusDAO.getStatusWiseDistrictWisePrintingConstituencyDetails(1l, vendorId, fromDate, toDate);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long districtId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String name = obj[1] != null ? obj[1].toString():"";
					Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					if(statusId != null && (statusId.longValue() == 1l || statusId.longValue() == 2l || statusId.longValue() == 3l))
						statusId = 1l;
					
					CardPrintVO vo = districtMap.get(districtId);
					if(vo == null){
						vo = new CardPrintVO();
						vo.setId(districtId);
						vo.setName(name);
						vo.setOverAllList(getAllPrintStatus(statusList));
						CardPrintVO statusvo = getMatchedVOById(statusId, vo.getOverAllList());
						if(statusvo != null)
							statusvo.setCount(count);
						districtMap.put(districtId, vo);
					}
					else{
						CardPrintVO statusvo = getMatchedVOById(statusId, vo.getOverAllList());
						if(statusvo != null)
							statusvo.setCount(statusvo.getCount()+count);
					}
				}
			}
			
			if(districtMap != null){
				returnvo.setOverAllList(new ArrayList<CardPrintVO>(districtMap.values()));
				districtMap = new LinkedHashMap<Long, CardPrintVO>();
			}
			
			List<Object[]> list1 = constituencyPrintStatusDAO.getStatusWiseDistrictWisePrintingConstituencyDetails(36l, vendorId, fromDate, toDate);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long districtId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					String name = obj[1] != null ? obj[1].toString():"";
					Long statusId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					Long count = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					
					if(statusId != null && (statusId.longValue() == 1l || statusId.longValue() == 2l || statusId.longValue() == 3l))
						statusId = 1l;
					
					CardPrintVO vo = districtMap.get(districtId);
					if(vo == null){
						vo = new CardPrintVO();
						vo.setId(districtId);
						vo.setName(name);
						vo.setOverAllList(getAllPrintStatus(statusList));
						CardPrintVO statusvo = getMatchedVOById(statusId, vo.getOverAllList());
						if(statusvo != null)
							statusvo.setCount(count);
						districtMap.put(districtId, vo);
					}
					else{
						CardPrintVO statusvo = getMatchedVOById(statusId, vo.getOverAllList());
						if(statusvo != null)
							statusvo.setCount(statusvo.getCount()+count);
					}
				}
			}
			
			if(districtMap != null)
				returnvo.setTodayList(new ArrayList<CardPrintVO>(districtMap.values()));
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getDistrictWiseStatusWiseConstituenciesCounts method in CardPrintService", e);
		}
		return returnvo;
	}
	
	public CardPrintVO getVendorWiseStatusWiseConstituenciesDetails(Long stateId,String fromDateStr,String toDateStr){
		CardPrintVO returnvo = new CardPrintVO();
		try {
			Map<Long,CardPrintVO> vendorMap = new LinkedHashMap<Long, CardPrintVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date fromDate = null;
			Date toDate = null;
			
			if(fromDateStr != null && toDateStr != null){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> statusList = printStatusDAO.getAllPrintStatus();
			
			List<CardPrintVendor> vendorList = cardPrintVendorDAO.getAll();
			if(vendorList != null && !vendorList.isEmpty()){
				for (CardPrintVendor cardPrintVendor : vendorList) {
					CardPrintVO vo = new CardPrintVO();
					vo.setId(cardPrintVendor.getCardPrintVendorId());
					vo.setName(cardPrintVendor.getVendorName());
					vo.setOverAllList(getAllPrintStatus(statusList));
					vendorMap.put(vo.getId(), vo);
				}
			}
			
			List<Object[]> list = constituencyPrintStatusDAO.getStatusWiseVendorWiseConstituencyDetails(stateId, fromDate, toDate);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					Long vendorId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					if(statusId != null && (statusId.longValue() == 1l || statusId.longValue() == 2l || statusId.longValue() == 3l))
						statusId = 1l;
					
					CardPrintVO vo = vendorMap.get(vendorId);
					if(vo != null){
						CardPrintVO statusvo = getMatchedVOById(statusId, vo.getOverAllList());
						if(statusvo != null)
							statusvo.setCount(statusvo.getCount()+count);
					}
				}
			}
			
			if(vendorMap != null){
				returnvo.setOverAllList(new ArrayList<CardPrintVO>(vendorMap.values()));
				vendorMap = new LinkedHashMap<Long, CardPrintVO>();
			}
			
			fromDate = dateUtilService.getCurrentDateAndTime();
			toDate = dateUtilService.getCurrentDateAndTime();
			
			if(vendorList != null && !vendorList.isEmpty()){
				for (CardPrintVendor cardPrintVendor : vendorList) {
					CardPrintVO vo = new CardPrintVO();
					vo.setId(cardPrintVendor.getCardPrintVendorId());
					vo.setName(cardPrintVendor.getVendorName());
					vo.setOverAllList(getAllPrintStatus(statusList));
					vendorMap.put(vo.getId(), vo);
				}
			}
			
			List<Object[]> list1 = constituencyPrintStatusDAO.getStatusWiseVendorWiseConstituencyDetails(stateId, fromDate, toDate);
			if(list1 != null && !list1.isEmpty()){
				for (Object[] obj : list1) {
					Long vendorId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					Long statusId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long count = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
					if(statusId != null && (statusId.longValue() == 1l || statusId.longValue() == 2l || statusId.longValue() == 3l))
						statusId = 1l;
					
					CardPrintVO vo = vendorMap.get(vendorId);
					if(vo != null){
						CardPrintVO statusvo = getMatchedVOById(statusId, vo.getOverAllList());
						if(statusvo != null)
							statusvo.setCount(statusvo.getCount()+count);
					}
				}
			}
			
			returnvo.setTodayDate(dateUtilService.getCurrentDateInStringFormat());
			if(vendorMap != null)
				returnvo.setTodayList(new ArrayList<CardPrintVO>(vendorMap.values()));
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getVendorWiseStatusWiseConstituenciesDetails method in CardPrintService", e);
		}
		return returnvo;
	}
	
	public List<CardPrintVO> getAllPrintStatus(List<Object[]> statusList){
		List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
		try {
			if(statusList != null && !statusList.isEmpty()){
				for (Object[] obj : statusList) {
					Long id = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					if(id != null && id.longValue() != 2l && id.longValue() != 3l){
						CardPrintVO vo = new CardPrintVO();
						vo.setId(id);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						if(id.longValue() == 1l)
							vo.setName("Print Data Ready");
						else if(id.longValue() == 4l)
							vo.setName("Print Pending");
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getAllPrintStatus method in CardPrintService", e);
		}
		return returnList;
	}
	
	public CardPrintVO getMatchedVOById(Long id,List<CardPrintVO> list){
		CardPrintVO returnvo = null;
		try {
			if(id != null && id.longValue() > 0l && list != null && !list.isEmpty()){
				for (CardPrintVO vo : list) {
					if(vo.getId().equals(id))
						return vo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getMatchedVOById method in CardPrintService", e);
		}
		return returnvo;
	}	
public List<CardPrintVO> getVendorNames(){
		List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
		try{
			List<Object[]> vendorList = cardPrintVendorDAO.getVendorNames();
			for (Object[] objects : vendorList) {
				CardPrintVO vo = new CardPrintVO();
				vo.setId(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
				vo.setName(objects[1] != null ? objects[1].toString():"");
				returnList.add(vo);
			}
			
		}catch(Exception e){
			LOG.error("Exception raised in getVendorNames() in CardPrintService",e);
		}
		return returnList;
	}
	
public List<CardPrintVO> getConstListByVendor(Long vendorId,Long districtId){
	List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
	try{
		List<Object[]> constuncyList = constituencyPrintStatusDAO.getConstListByVendorId(vendorId,districtId);
		for (Object[] objects : constuncyList) {
			CardPrintVO vo = new CardPrintVO();
			vo.setId(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
			vo.setName(objects[1] != null ? objects[1].toString():"");
			returnList.add(vo);
		}
	}catch(Exception e){
		LOG.error("Exception raised in getConstListByVendor() in CardPrintService ",e);
	}
	return returnList;
}
public List<CardPrintVO> getDstrListByVendor(Long vendorId){
	List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
	try{
		List<Object[]> distrList = constituencyPrintStatusDAO.getDstrListByVendorId(vendorId);
		for (Object[] objects : distrList) {
			CardPrintVO vo = new CardPrintVO();
			vo.setId(Long.valueOf(objects[0] != null ? objects[0].toString():"0"));
			vo.setName(objects[1] != null ? objects[1].toString():"");
			returnList.add(vo);
		}
	}catch(Exception e){
		LOG.error("Exception raised in getDstrListByVendor() in CardPrintService ",e);
	}
	return returnList;
}

	public List<CardPrintingDispatchVO> getPrintingDispatchDetails(Long vendorId,Long districtId,Long constituencyId){
		List<CardPrintingDispatchVO> returnList = new ArrayList<CardPrintingDispatchVO>();
		try {
			List<String> boxNos = new ArrayList<String>();
			
			List<Object[]> list = tdpCadreCardPrintDAO.getBoxWisePrintingDispatchDetails(vendorId, districtId, constituencyId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					CardPrintingDispatchVO vo = new CardPrintingDispatchVO();
					
					vo.setBoxNo(obj[0] != null ? obj[0].toString():"");
					vo.setMandalId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
					vo.setMandalName(obj[2] != null ? obj[2].toString():"");
					if(vo.getMandalId() == null || vo.getMandalId().longValue() == 0l){
						vo.setMandalId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
						vo.setMandalName(obj[4] != null ? obj[4].toString():"");
					}
					vo.setPanchayatId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setPanchayatName(obj[6] != null ? obj[6].toString():"");
					if(vo.getPanchayatId() == null || vo.getPanchayatId().longValue() == 0l){
						vo.setPanchayatId(Long.valueOf(obj[7] != null ? obj[7].toString():"0"));
						vo.setPanchayatName(obj[8] != null ? obj[8].toString():"");
					}
					vo.setNoOfCards(Long.valueOf(obj[9] != null ? obj[9].toString():"0"));
					
					boxNos.add(vo.getBoxNo());
					returnList.add(vo);
				}
			}
			
			if(boxNos != null && !boxNos.isEmpty()){
				List<Object[]> list1 = cardPrintValidationDAO.getValidatedCardsCountsForBoxNos(boxNos);
				if(list1 != null && !list1.isEmpty()){
					for (Object[] obj : list1) {
						String boxNo = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						CardPrintingDispatchVO vo = getMatchedVOByString(boxNo, returnList);
						if(vo != null)
							vo.setValidatedCardsCount(count);
					}
				}
				
				List<Object[]> list2 = cardPrintValidationDAO.getErrorCardsCountsForBoxNos(boxNos);
				if(list2 != null && !list2.isEmpty()){
					for (Object[] obj : list2) {
						String boxNo = obj[0] != null ? obj[0].toString():"";
						Long count = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						CardPrintingDispatchVO vo = getMatchedVOByString(boxNo, returnList);
						if(vo != null){
							vo.setErrorCount(count);
							vo.setErrorPerc((new BigDecimal((vo.getErrorCount() * 100.0)/vo.getValidatedCardsCount()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
							float fl_perc = Float.parseFloat(vo.getErrorPerc());
							if(fl_perc < 50f){
								vo.setIsQAPassed("YES");
								vo.setStatus("READY TO DISPATCH");
							}
							else{
								vo.setIsQAPassed("NO");
								vo.setStatus("RE-PRINT REQUIRED");
							}
						}
					}
				}
 			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getPrintingDispatchDetails() in CardPrintService ",e);
		}
		return returnList;
	}
	
	public CardPrintingDispatchVO getMatchedVOByString(String boxNo,List<CardPrintingDispatchVO> list){
		CardPrintingDispatchVO returnvo = null;
		try {
			if(boxNo != null && boxNo.trim().length() > 0l && list != null && !list.isEmpty()){
				for (CardPrintingDispatchVO vo : list) {
					if(vo.getBoxNo().trim().equalsIgnoreCase(boxNo.trim()))
						return vo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedVOByString() in CardPrintService ",e);
		}
		return returnvo;
	}
	
	
	/////////
	/**  
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  UPDATING PRINT DETAILS TO TdpCadreCardPrint from ZebraPrintDetails or from MaxPrintDetails by constituencyId.
	  *  @since 21-DECEMBER-2016
	  */
	public PrintUpdateDetailsStatusVO updatePrintDetailsToTdpCadreCardPrint(final List<PrintVO> printList){
		PrintUpdateDetailsStatusVO status = new PrintUpdateDetailsStatusVO();
		try{
			 
			 if(printList != null && printList.size() > 0l){
				
				 status = (PrintUpdateDetailsStatusVO)transactionTemplate.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus arg0) {
						
						PrintUpdateDetailsStatusVO rs = new PrintUpdateDetailsStatusVO();
						
							 int count = 0;
							 long tempStartTime = System.currentTimeMillis();
							 
							 for(PrintVO printVO : printList){
								 count++;
								 
								 Long tdpCadreCardPrintId = printVO.getTdpCadreCardPrintId();
								 if(tdpCadreCardPrintId != null){
									
									//UPDATE DETAILS BASED ON query. 
									Integer updateCount =  tdpCadreCardPrintDAO.updateAppntmntStatusById(printVO);
									
									/* 
									   //UPDATE DETAILS BASED ON save() method. 
									 TdpCadreCardPrint cardPrint = tdpCadreCardPrintDAO.get(tdpCadreCardPrintId);
									 if(cardPrint != null){
										 
										 if(printVO.getPrintTime() != null && printVO.getPrintTime().toString().trim().length() > 0){
											 cardPrint.setPrintTime(printVO.getPrintTime());
										 }
										 
										 if(printVO.getSerialNumber() != null && !printVO.getSerialNumber().isEmpty()){
											 cardPrint.setSerialNumber( printVO.getSerialNumber() );
										 }
										 
										 if(printVO.getPrintStatus() != null && !printVO.getPrintStatus().isEmpty()){
											 cardPrint.setPrintStatus( printVO.getPrintStatus() );
										 }
										 
										 if(printVO.getPrintCode() != null && !printVO.getPrintCode().isEmpty()){
											 cardPrint.setPrintCode( printVO.getPrintCode() );
										 }
										 
										 if(printVO.getPrintDesc() != null && !printVO.getPrintDesc().isEmpty()){
											 cardPrint.setPrintDesc( printVO.getPrintDesc());
										 }
										 
										 if(printVO.getPrinterSerialNumber() != null && !printVO.getPrinterSerialNumber().isEmpty()){
											 cardPrint.setPrinterSerialNumber( printVO.getPrinterSerialNumber());
										 }
										 
										 if(printVO.getBoxNo() != null && !printVO.getBoxNo().isEmpty()){
											 cardPrint.setBoxNo( printVO.getBoxNo() );
										 }
										 
										 if(printVO.getPcNo() != null && !printVO.getPcNo().isEmpty()){
											 cardPrint.setPcNo( printVO.getPcNo());
										 }
										 
										 tdpCadreCardPrintDAO.save(cardPrint);
									 }*/
								 }
							    
							 }
							 Double tempSeconds = (System.currentTimeMillis() - tempStartTime)/1000.0;
							 System.out.println(tempSeconds);
							 
						 rs.setMessage("success");
						 rs.setResultCode(1);
						 return rs;
				    }
		       });
				
	     }
			 
		}catch(Exception e){
			 status.setMessage("failure");
			 status.setResultCode(0);
			LOG.error("exception Occurred at updatePrintDetailsToTdpCadreCardPrint() in CardPrintService class ", e); 
		}
		return status;
	}
	
	
}
