package com.itgrids.partyanalyst.service.impl;



import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICardPrintStatusDAO;
import com.itgrids.partyanalyst.dao.ICardPrintValidationDAO;
import com.itgrids.partyanalyst.dao.ICardPrintVendorDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyPrintStatusDAO;
import com.itgrids.partyanalyst.dao.IPrintStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCardPrintDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreValidateVO;
import com.itgrids.partyanalyst.dto.CardPrintStatusVO;
import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.dto.CardPrintingDispatchVO;
import com.itgrids.partyanalyst.dto.PrintUpdateDetailsStatusVO;
import com.itgrids.partyanalyst.dto.PrintVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SmallVO;
import com.itgrids.partyanalyst.model.CardPrintVendor;
import com.itgrids.partyanalyst.service.ICardPrintService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class CardPrintService implements ICardPrintService{

	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	
	private ICardPrintVendorDAO cardPrintVendorDAO;
	private IConstituencyPrintStatusDAO constituencyPrintStatusDAO;
	private DateUtilService dateUtilService;
	private ITdpCadreCardPrintDAO tdpCadreCardPrintDAO;
	private IPrintStatusDAO printStatusDAO;
	private ICardPrintValidationDAO cardPrintValidationDAO;
	private TransactionTemplate transactionTemplate;
	private ICardPrintStatusDAO cardPrintStatusDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private IConstituencyDAO constituencyDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	
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
	
	public void setCardPrintStatusDAO(ICardPrintStatusDAO cardPrintStatusDAO) {
		this.cardPrintStatusDAO = cardPrintStatusDAO;
	}
	
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
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
	
	/**  
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  Get Card Print Status Based on District wise Or constituency wise.
	  *  @since 30-DECEMBER-2016
	  */
	public List<CardPrintStatusVO> cardPrinStatusByLocation(String type , Long stateId ){
		List<CardPrintStatusVO> finalList = null;
		try{
			
			List<Object[]> statusList = cardPrintStatusDAO.getAllCardPrintStatus();
			Map<Long,CardPrintStatusVO> finalMap = new LinkedHashMap<Long, CardPrintStatusVO>(0);
			
			if(type != null && !type.isEmpty()){
				
				List<Object[]> list = null;
				List<Object[]> totalCountsList = null;
				
				if(type.equalsIgnoreCase("district")){
					
				   list = tdpCadreDAO.getDistrictWiseCardPrintStatusCounts(stateId);
				   totalCountsList = tdpCadreDAO.getDistrictWiseCadreCounts(stateId);
				   
				}else if(type.equalsIgnoreCase("constituency")){
					
					list = tdpCadreDAO.getConstituencyWiseCardPrintStatusCounts(stateId);	
					totalCountsList = tdpCadreDAO.getConstituencyWiseCadreCounts(stateId);
				}
				
				if(totalCountsList != null && totalCountsList.size() > 0){
					for(Object[] obj : totalCountsList){
						if(obj[0] != null){
							Long locationId  = (Long)obj[0];
							CardPrintStatusVO locationVO = finalMap.get(locationId);
							if(locationVO == null){
								locationVO = new CardPrintStatusVO();
								locationVO.setId((Long)obj[0]);
								locationVO.setName(obj[1]!=null ? obj[1].toString() :"");
								locationVO.setCount(obj[2] != null ? (Long)obj[2] : 0l);
								setStatusListToVO(statusList , locationVO);
								
								if(type.equalsIgnoreCase("district")){
									if(locationId.longValue() >=1 && locationId.longValue() <= 10){
										locationVO.setState("TS");
									}else if(locationId.longValue() >=11 && locationId.longValue() <= 23){
										locationVO.setState("AP");
									}
								}
								if(type.equalsIgnoreCase("constituency")){
									
									locationVO.setSuperId(obj[3] != null ? (Long)obj[3] : 0l);
									locationVO.setSuperName(obj[4]!=null ? obj[4].toString() :"");
									
									if(locationVO.getSuperId() >=1 && locationVO.getSuperId() <= 10){
										locationVO.setState("TS");
									}else if(locationVO.getSuperId() >=11 && locationVO.getSuperId() <= 23){
										locationVO.setState("AP");
									}
								}
								
								finalMap.put(locationId, locationVO);
							}
							
						}
					}
				}
				
				if(list != null && list.size() > 0){
					for(Object[] obj : list){
						if(obj[0] != null){
							Long locationId  = (Long)obj[0];
							CardPrintStatusVO locationVO = finalMap.get(locationId);
							if(locationVO != null){
								Map<Long,CardPrintStatusVO> subMap = locationVO.getSubMap();
								if(subMap != null && obj[1] != null){
									CardPrintStatusVO statusVO = subMap.get((Long)obj[1]);
									if(statusVO != null){
										statusVO.setCount(obj[2]!=null ? (Long)obj[2] :0l);
									}
								}
							}
						}
					}
				}
				
				
				if(finalMap != null && finalMap.size() > 0){
					for (Map.Entry<Long,CardPrintStatusVO> locationEntry : finalMap.entrySet())
					{
						CardPrintStatusVO locationVO = locationEntry.getValue();
						if(locationVO != null && locationVO.getSubMap() != null && locationVO.getSubMap().size() > 0){
							locationVO.setSubList(new ArrayList<CardPrintStatusVO>(locationVO.getSubMap().values()));
							locationVO.getSubMap().clear();
						}
					}
					finalList = new ArrayList<CardPrintStatusVO>(finalMap.values());
				}
			}
			
		}catch(Exception e){
			LOG.error("exception Occurred at cardPrinStatusByLocation() in CardPrintService class ", e); 
		}
		return finalList;
	}
	
	public void setStatusListToVO(List<Object[]> statusList , CardPrintStatusVO VO){
		try{
			 
			if(statusList != null && statusList.size() > 0){
				Map<Long , CardPrintStatusVO> map = new LinkedHashMap<Long , CardPrintStatusVO>(0);
				for(Object[] obj : statusList){
					CardPrintStatusVO statusVO = new CardPrintStatusVO();
					statusVO.setId((Long)obj[0]);
					statusVO.setName(obj[1]!=null ?obj[1].toString():"");
					map.put(statusVO.getId() , statusVO);
				}
				VO.setSubMap(map);
			}
		}catch (Exception e){
			LOG.error("exception Occurred at setStatusListToVO() in CardPrintService class ", e); 
		}
	}
	
	public List<CardPrintVO> getEnrollmentDetailsByConstituency(){
		List<CardPrintVO> returnList = new ArrayList<CardPrintVO>();
		try{
			LOG.info("Enterd into cardPrintService of getEnrollmentDetailsByConstituency");
			List<Object[]> objList = constituencyDAO.getConstituencyByStateDetails();
			if(objList != null && !objList.isEmpty()){
				CardPrintVO vos = null;
				for(Object[] param : objList){
					vos = new CardPrintVO();
					vos.setId(param[0]!= null ?(Long)param[0]:0l);
					vos.setName(param[1] != null ?param[1].toString():"");
					returnList.add(vos);
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured into cardPrintService of getEnrollmentDetailsByConstituency", e);
		}
		return returnList;
	}
	
	
	
	/**  
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  get Constituency NotVerfied CardPrint Status Cadre and validate based on telugu names missed , special characters, images....
	  *  @since 02-JANUUARY-2017
	  */
	
	public CadreValidateVO getConstNotVerfiedCardPrintStatusCadreAndValidate(Long constituencyId){
		
		CadreValidateVO finalVO = new CadreValidateVO();
		try{
			
			  //1.GETTING SUMMARY
			  Long totalCadreCount = tdpCadreDAO.getConstituencyCadreCount(constituencyId);
			  if(totalCadreCount != null && totalCadreCount.longValue() > 0l){
				  finalVO.setTotalCadreCount(totalCadreCount); 
			  }
			  Long beforeVerifiedCount = tdpCadreDAO.getConstituencyCardPrintVerifiedCount(constituencyId);
			  if(beforeVerifiedCount != null && beforeVerifiedCount.longValue() > 0l){
				  finalVO.setBeforeVerifiedCount(beforeVerifiedCount);
			  }
			  
			  //2.VALIDATION PART
			  Long verifPassed = IConstants.CARD_PRINT_STATUS_VERIFICATION_PASSED;
			  Long verifFailed = IConstants.CARD_PRINT_STATUS_VERIFICATION_FAILED;
			  Map<Long , Long> successMap = new HashMap<Long, Long>(0);
			  Map<Long , Long> failureMap = new HashMap<Long, Long>(0);
					  
			  List<Object[]> cadreList =   tdpCadreDAO.getConstNotVerfiedCardPrintStatusCadre(constituencyId);
			  if(cadreList != null && cadreList.size() > 0){
				  
				  //make all objects as passed first , later if fail mark them as fail.
				  for(Object[] obj : cadreList){
					  successMap.put((Long)obj[0],verifPassed);
				  }
					  
				  ResultStatus missingTeluguNamesResultStatus = teluguNamesMissedScenarioChecking(cadreList , finalVO , successMap , failureMap);
				  if(missingTeluguNamesResultStatus != null && missingTeluguNamesResultStatus.getResultCode() == 0){
					  finalVO.setResultStatus(missingTeluguNamesResultStatus);
					  return finalVO;
				  }
				  
				  ResultStatus specialCharsResultStatus = specialCharactersExistInNameScenarioChecking(cadreList , finalVO , successMap , failureMap);
				  if(specialCharsResultStatus != null && specialCharsResultStatus.getResultCode() == 0){
					  finalVO.setResultStatus(specialCharsResultStatus);
					  return finalVO;
				  }
				  
				  ResultStatus imagesResultStatus = imagesExistingScenarioChecking(cadreList , finalVO , successMap , failureMap);
				  if(imagesResultStatus != null && imagesResultStatus.getResultCode() == 0){
					  finalVO.setResultStatus(imagesResultStatus);
					  return finalVO;
				  }
				 
				  
				  finalVO.setNowVerifiedCount((long)cadreList.size());
			  }
			  
			  //3.UPDATE CARD PRINT STATUS IN DB TABLE.
			  if(successMap != null && successMap.size() > 0){
				  List<Long> successList = new ArrayList<Long>(successMap.keySet());
				  finalVO.setApprovedCount((long)successList.size());
				  
				  ResultStatus verifPassedResultStatus = updateCardPrintStatusToTable( successList , verifPassed);
				  if(verifPassedResultStatus != null && verifPassedResultStatus.getResultCode() == 0){
					  finalVO.setResultStatus(verifPassedResultStatus);
					  return finalVO;
				  }
				  
			  }
			  
			  if(failureMap != null && failureMap.size() > 0){
				  List<Long> failureList = new ArrayList<Long>(failureMap.keySet());
				  finalVO.setRejectedCount((long)failureList.size());
				  
				  ResultStatus verifFailedResultStatus =  updateCardPrintStatusToTable( failureList , verifFailed);
				  if(verifFailedResultStatus != null && verifFailedResultStatus.getResultCode() == 0){
					  finalVO.setResultStatus(verifFailedResultStatus);
					  return finalVO;
				  }
				  
			  }
			  
			  
			  //4.GET CARD PRINT STATUS WISE COUNTS FOR GIVEN CONSTITUENCY.
			  List<CadreValidateVO> statusCountsList = getConstituencyCadreCardPrintStatusCounts(constituencyId);
			  if(statusCountsList != null && statusCountsList.size() > 0){
				  finalVO.setStatusCountsList(statusCountsList);
			  }
			  
			  ResultStatus rs = new ResultStatus();
			  rs.setResultCode(1);
			  finalVO.setResultStatus(rs);
			  
		}catch(Exception e){
			 ResultStatus rs = new ResultStatus();
			 rs.setResultCode(0);
			 rs.setExceptionMsg("Exception Occurred .. Pls Try Later..");
			 finalVO.setResultStatus(rs);
			  
			LOG.error("Exception Occured into getConstNotVerfiedCardPrintStatusCadreAndValidate() of CardPrintService class", e);
		}
		return finalVO;
	}
	
	
	public ResultStatus teluguNamesMissedScenarioChecking(List<Object[]> cadreList , CadreValidateVO finalVO , Map<Long,Long> successMap , Map<Long,Long> failureMap){
		
		ResultStatus rs = new ResultStatus();
		
		try{
			  if(cadreList != null && cadreList.size() > 0){
				  
				  for(Object[] obj :cadreList){
					  
						   
						  if(obj[2] != null && (Long)obj[2] > 0l){ // Enrolled With own voterId 
							  
							  if( !(obj[9] != null && obj[9].toString().trim().length() > 0) ){
								  
								//mark this cadre has failed in verification.
								  if(successMap.containsKey((Long)obj[0])){
									  successMap.remove((Long)obj[0]);
								  }
								  failureMap.put((Long)obj[0], IConstants.CARD_PRINT_STATUS_VERIFICATION_FAILED);
								  
								  
								  CadreValidateVO cadreVO = new CadreValidateVO();
								  cadreVO.setValidationMessage("missing telugu name");
								  setTdpCadreDataToVO(obj , cadreVO);
								  
								  if(finalVO.getTeluguNamesMissedList() == null){
									  finalVO.setTeluguNamesMissedList(new ArrayList<CadreValidateVO>(0));
								  }
								  finalVO.getTeluguNamesMissedList().add(cadreVO);
							  }
						  }
						  else if(obj[3] != null && (Long)obj[3] > 0l){// Enrolled With family voterId
							  
							  if( !(obj[10] != null && obj[10].toString().trim().length() > 0)){
								  
								//mark this cadre has failed in verification.
								  if(successMap.containsKey((Long)obj[0])){
									  successMap.remove((Long)obj[0]);
								  }
								  failureMap.put((Long)obj[0], IConstants.CARD_PRINT_STATUS_VERIFICATION_FAILED);
								  
								  
								  CadreValidateVO cadreVO = new CadreValidateVO();
								  cadreVO.setValidationMessage("missing telugu name");
								  
								  setTdpCadreDataToVO(obj , cadreVO);
								  
								  if(finalVO.getTeluguNamesMissedList() == null){
									  finalVO.setTeluguNamesMissedList(new ArrayList<CadreValidateVO>(0));
								  }
								  finalVO.getTeluguNamesMissedList().add(cadreVO);
							  }
						  }  
						 
				  }
			  }
			
			  rs.setResultCode(1);
			  
		}catch(Exception e){
			 LOG.error("Exception Occured into teluguNamesMissingChecking() of CardPrintService class", e);
			 rs.setResultCode(0);
			 rs.setExceptionMsg(" Exception Occurred In Checking Missing Telugu Names.. ");
		}
		return rs;
	}
	
	public ResultStatus specialCharactersExistInNameScenarioChecking(List<Object[]> cadreList , CadreValidateVO finalVO , Map<Long,Long> successMap , Map<Long,Long> failureMap ){
		
		ResultStatus rs = new ResultStatus();
		
		try{
			
			if(cadreList != null && cadreList.size() > 0){
				  
				  for(Object[] obj :cadreList){
					   
					  if(obj[2] != null && (Long)obj[2] > 0l){ // Enrolled With own voterId 
						  
						  if(obj[9] != null && obj[9].toString().trim().length() > 0){
							  
							  if(commonMethodsUtilService.isNameHaveSpecialChars(obj[9].toString().trim())){
								  
								//mark this cadre has failed in verification.
								  if(successMap.containsKey((Long)obj[0])){
									  successMap.remove((Long)obj[0]);
								  }
								  failureMap.put((Long)obj[0], IConstants.CARD_PRINT_STATUS_VERIFICATION_FAILED);
								  
								  CadreValidateVO cadreVO = new CadreValidateVO();
								  cadreVO.setValidationMessage("special Character Exists");
								  
								  setTdpCadreDataToVO(obj , cadreVO);
								  
								  if(finalVO.getSpecialCharactersList() == null){
									  finalVO.setSpecialCharactersList(new ArrayList<CadreValidateVO>(0));
								  }
								  finalVO.getSpecialCharactersList().add(cadreVO);
							  }
							  
						  }
					  }
					  else if(obj[3] != null && (Long)obj[3] > 0l){// Enrolled With family voterId
						  
						  if(obj[10] != null && obj[10].toString().trim().length() > 0){
							  
							  if(commonMethodsUtilService.isNameHaveSpecialChars(obj[10].toString().trim())){
								  
								//mark this cadre has failed in verification.
								  if(successMap.containsKey((Long)obj[0])){
									  successMap.remove((Long)obj[0]);
								  }
								  failureMap.put((Long)obj[0], IConstants.CARD_PRINT_STATUS_VERIFICATION_FAILED);
								  
								  CadreValidateVO cadreVO = new CadreValidateVO();
								  cadreVO.setValidationMessage("special Character Exists");
								  
								  setTdpCadreDataToVO(obj , cadreVO);
								  
								  if(finalVO.getSpecialCharactersList() == null){
									  finalVO.setSpecialCharactersList(new ArrayList<CadreValidateVO>(0));
								  }
								  finalVO.getSpecialCharactersList().add(cadreVO);
							  }
						  }
					  }
					  
				  }
			}
			
			rs.setResultCode(1);
			  
		}catch(Exception e){
			 rs.setResultCode(0);
			 rs.setExceptionMsg(" Exception Occurred In Checking Special Characters In Telugu Names ");
			 
			LOG.error("Exception Occured into specialCharactersExistInNameScenarioChecking() of CardPrintService class", e);
		}
		return rs;
	}

	public ResultStatus imagesExistingScenarioChecking(List<Object[]> cadreList , CadreValidateVO finalVO ,  Map<Long,Long> successMap , Map<Long,Long> failureMap ){
		
		ResultStatus rs = new ResultStatus();
		try{
			
			if(cadreList != null && cadreList.size() > 0){
				  
				  for(Object[] obj :cadreList){
					   
					  if(obj[8] != null && obj[8].toString().trim().length() > 0){ 
						  
						  File cadreFile = new File("/mnt/tdp-img/cadre_images/2014/"+obj[8].toString().trim());
						  if(!cadreFile.exists()){
							  
							  //mark this cadre has failed in verification.
							  if(successMap.containsKey((Long)obj[0])){
								  successMap.remove((Long)obj[0]);
							  }
							  failureMap.put((Long)obj[0], IConstants.CARD_PRINT_STATUS_VERIFICATION_FAILED);
							  
							  CadreValidateVO cadreVO = new CadreValidateVO();
							  cadreVO.setValidationMessage("missing image");
							  
							  setTdpCadreDataToVO(obj , cadreVO);
							  
							  if(finalVO.getImagesMissedList() == null){
								  finalVO.setImagesMissedList(new ArrayList<CadreValidateVO>(0));
							  }
							  finalVO.getImagesMissedList().add(cadreVO);
						  }
					  }
				  }
			}
			rs.setResultCode(1);
		}catch(Exception e){
			 rs.setResultCode(0);
			 rs.setExceptionMsg(" Exception Occurred In Checking Images Exist Or Not ");
			 
			LOG.error("Exception Occured into imagesExistingScenarioChecking() of CardPrintService class", e);
		}
		return rs;
	}

	public void setTdpCadreDataToVO(Object[] obj , CadreValidateVO cadreVO){
		 try{
			 
			 cadreVO.setTdpCadreId(obj[0] != null ? (Long)obj[0] : 0l);
			  cadreVO.setMemberShipId(obj[1] != null ? obj[1].toString() : "");
			  
			  if(obj[2] != null){
				  cadreVO.setVoterId((Long)obj[2]);
			  }else if(obj[3] !=null){
				  cadreVO.setFamilyVoterId((Long)obj[3]);
			  }
			  
			  if(obj[4] != null){
				  cadreVO.setFirstName(obj[4].toString().trim());
			  }
			  if(obj[5] != null && (Long)obj[5] > 0l){
				  cadreVO.setAge((Long)obj[5]);
			  }
			  if(obj[6] != null){
				  cadreVO.setGender(obj[6].toString());
			  }
			  cadreVO.setMobileNo(obj[7] != null ? obj[7].toString() : "");
			  cadreVO.setImage(obj[8] != null ? obj[8].toString() : "");
			  
			  if(obj[9] != null && obj[9].toString().trim().length() > 0){
				  cadreVO.setVoterName(obj[9].toString().trim());
			  }
			  if(obj[10] != null && obj[10].toString().trim().length() > 0){
				  cadreVO.setCadreName(obj[10].toString().trim());
			  }
			  
		}catch (Exception e){
			LOG.error("Exception Occured into setTdpCadreDataToVO() of CardPrintService class", e);
		}
	}
	
	public ResultStatus updateCardPrintStatusToTable( List<Long> dataList , Long cardPrintStatusId){
			
			ResultStatus rs = new ResultStatus();
			
			try{
				  int noOfRecordsUpdate = IConstants.noOfRecordsUpdate;
				  
				  if(dataList != null && dataList.size() > 0){
					  
					  int totalCount =  dataList.size();
					  
					    if(totalCount <=  noOfRecordsUpdate)
				        {	
					    	int noOfRecords = tdpCadreDAO.updateCardPrintStatusByTdpCadreIds(dataList, cardPrintStatusId);
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
					    		List<Long> sublist = dataList.subList(fromIndex, toIndex);
					    		if(sublist != null && sublist.size() > 0){
					    			int noOfRecords = tdpCadreDAO.updateCardPrintStatusByTdpCadreIds(sublist, cardPrintStatusId);
				    			}
					    	}
					    }
				  }
				 rs.setResultCode(1);
			}catch(Exception e){
				 rs.setResultCode(0);
				 rs.setExceptionMsg(" Exception Occurred While Updating Card Print Status To Cadre ");
				LOG.error("exception Occurred at updateCardPrintStatusToTable() in CardPrintService class ", e); 
			}
			return rs;
		}

	
	public List<CadreValidateVO>  getConstituencyCadreCardPrintStatusCounts(Long constituencyId){
		List<CadreValidateVO> finalList = null;
		try{
			
			List<Object[]> statusList = cardPrintStatusDAO.getAllCardPrintStatus();
			  Map<Long,CadreValidateVO> stausCountsMap = new LinkedHashMap<Long, CadreValidateVO>(0);
			  if(statusList != null && statusList.size() > 0){
				  for(Object[] obj : statusList){
					  if(obj[0] != null){
						  CadreValidateVO statusVO = new CadreValidateVO();
						  statusVO.setTdpCadreId((Long)obj[0]);
						  statusVO.setFirstName(obj[1] != null ? obj[1].toString() : "");
						  stausCountsMap.put(statusVO.getTdpCadreId(), statusVO);
					  }
				  }
			  }
			  List<Object[]> statusCountsList = tdpCadreDAO.getConstituencyCadreCardPrintStatusCounts(constituencyId);
			  if(statusCountsList != null && statusCountsList.size() > 0){
				  for(Object[] obj : statusCountsList){
					  if(obj[0] != null){
						  CadreValidateVO statusVO =  stausCountsMap.get((Long)obj[0]);
						  if(statusVO != null){
							  statusVO.setTotalCadreCount(obj[1] != null ? (Long)obj[1] : 0l);
						  }
					  }
				  }
			  }
			  if(stausCountsMap != null && stausCountsMap.size() > 0){
				  finalList = new ArrayList<CadreValidateVO>(stausCountsMap.values());
			  }
			
		}catch(Exception e){
			LOG.error("Exception Occured into getConstituencyCadreCardPrintStatusCounts() of CardPrintService class", e);
		}
		return finalList;
	}
	
	
	public List<SmallVO> getPrintPushedConstituencies(){
		List<SmallVO> smallVOList = new ArrayList<SmallVO>();
		try{
			
			List<Object[]> data = tdpCadreCardPrintDAO.getPrintPushedConstituencies();
			if(data != null && data.size() > 0){
				for(Object[] obj : data){
					if(obj[0] != null){
						smallVOList.add( new SmallVO((Long)obj[0] , obj[1]!=null?obj[1].toString() :""));
					}
				}
			}
		}catch (Exception e) {
			LOG.error("Exception Occured into getPrintPushedConstituencies() of CardPrintService class", e);
		}
		return smallVOList;
	}
	
	/**  
	  * @author <a href="mailto:sreedhar.itgrids.hyd@gmail.com">SREEDHAR</a>
	  *  Constituency Wise post verification of cadre Data for printing.
	  *  @since 05-JANUUARY-2017
	  */
	public List<String> postVerificationCadreData(Long constituencyId){
		
		List<String> finalList = new ArrayList<String>();
		try{
			   List<Object[]> dataList = tdpCadreCardPrintDAO.postVerificationCadreData(constituencyId);
			   
			   if(dataList != null && dataList.size() >0){
				   
				   for(Object[] obj : dataList){
					   
					   StringBuilder sb = null;
					   
					   
					   //validate tdpCadreId
					   if(obj[1] == null){
						   sb = builderExist( sb , (Long)obj[0]);  
						   sb.append(" , TdpCadreId Not Exist");
					   }
					   
					   //validate MemberShipId
					   if(obj[2] == null){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb = sb.append(" , MemberShipId Not Exist");
					   }else if(obj[2].toString().trim().length() !=8){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , MemberShipId DoesNot Have 8 Chars");
					   }
					   
					   //validate CadreName
					   if(obj[3] == null || obj[3].toString().isEmpty()){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , CadreName Not Exist");
					   }else if(commonMethodsUtilService.isNameHaveSpecialChars(obj[3].toString().trim())){
						       sb = builderExist( sb , (Long)obj[0]);
							   sb.append(" , CadreName Has Special Character");
					   }
					   
					   
					   //validate image
					   if(obj[4] == null || obj[4].toString().isEmpty()){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , Image is empty ");
					   }else{
						   File cadreFile = new File("/mnt/tdp-img/cadre_images/2014/"+obj[4].toString().trim());//OBJ CREATION...
						   if(!cadreFile.exists()){
							   sb = builderExist( sb , (Long)obj[0]);
							   sb.append(" , Image Does Not Exist ");
						   }
					   }
					   
					   //Validate MobileNo
					   if(obj[5] == null || obj[5].toString().isEmpty()){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" ,  MobileNo DoesNot Exist ");
					   }else if(obj[5].toString().trim().length() !=10){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , MobileNo DoesNot Have 10 Digits");
					   }
					   
					 //Validate locationType
					  if(obj[6] == null || obj[6].toString().isEmpty()){
						  sb = builderExist( sb , (Long)obj[0]);
						  sb.append(" ,  LocatiopnType Not Exists ");
					  }
					  
					 //Validate districtName
					  if(obj[7] == null || obj[7].toString().isEmpty()){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , DistrictName Not Exists ");
					   }else if(commonMethodsUtilService.isNameHaveSpecialChars(obj[7].toString().trim())){
						       sb =  builderExist( sb , (Long)obj[0]);
							   sb.append(" , DistrictName Has Special Character");
					   }
					  
					//Validate ConstituencyName
					  if(obj[8] == null || obj[8].toString().isEmpty()){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , ConstituencyName Not Exists ");
					  }else if(commonMethodsUtilService.isNameHaveSpecialChars(obj[8].toString().trim())){
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , ConstituencyName Has Special Character");
					  }
					  
					  //validate Mandal / Muncpality Name.
					  if( (obj[9] != null && !obj[9].toString().isEmpty()) || (obj[10] != null && !obj[10].toString().isEmpty()) ){
						  
						   if(obj[9] != null && !obj[9].toString().isEmpty()){
							   if(commonMethodsUtilService.isNameHaveSpecialChars(obj[9].toString().trim())){
								   sb = builderExist( sb , (Long)obj[0]);
								   sb.append(" , Mandal Name Has Special Character");
							   }
						   }else if(commonMethodsUtilService.isNameHaveSpecialChars(obj[10].toString().trim())){
							   sb = builderExist( sb , (Long)obj[0]);
							   sb.append(" , Muncipality Name Has Special Character");
						   }
					  }else{
						   sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , Mandal/Muncipality Name Not Exists");
					  }
					  
					//validate Panchayat / Ward Name
					  if( (obj[11] != null && !obj[11].toString().isEmpty()) || (obj[12] != null && !obj[12].toString().isEmpty()) ){
						  
						   if(obj[11] != null && !obj[11].toString().isEmpty()){
							   if(commonMethodsUtilService.isNameHaveSpecialChars(obj[11].toString().trim())){
								   sb = builderExist( sb , (Long)obj[0]);
								   sb.append(" , Panchayat Name Has Special Character");
							   }
						   }else if(commonMethodsUtilService.isNameHaveSpecialChars(obj[12].toString().trim())){
							   //sb = builderExist( sb , (Long)obj[0]);
							   //sb.append(" , Ward Name Has Special Character");
						   }
					  }else{
						  sb = builderExist( sb , (Long)obj[0]);
						   sb.append(" , Panchayat/Ward Name Not Exists");
					  }
					  
					//validate Panchayat / Ward Name
					  if(obj[13] == null || obj[13].toString().isEmpty()){
						  sb = builderExist( sb , (Long)obj[0]);
						  sb.append(" ,  ConstituencyType Not Exists ");
					  }
					  
					  if(sb != null){
						  finalList.add(sb.toString());
					  }
				   }
			   }
			
		}catch(Exception e){
			LOG.error("Exception Occured into postVerificationCadreData() of CardPrintService class", e);
		}
		return finalList;
	}
	
	public StringBuilder builderExist(StringBuilder sb , Long tdpCadreCardPrintId){
		 if(sb==null){
			 sb = new StringBuilder();
			 sb.append("Id - " +tdpCadreCardPrintId);
		 }
		 return sb;
	}
	
	
}
