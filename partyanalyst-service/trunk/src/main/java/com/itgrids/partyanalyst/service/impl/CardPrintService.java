package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyPrintStatusDAO;
import com.itgrids.partyanalyst.dao.IPrintStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCardPrintDAO;
import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.service.ICardPrintService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class CardPrintService implements ICardPrintService{

	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	
	
	private IConstituencyPrintStatusDAO constituencyPrintStatusDAO;
	private DateUtilService dateUtilService;
	private ITdpCadreCardPrintDAO tdpCadreCardPrintDAO;
	private IPrintStatusDAO printStatusDAO;
	
	
	public IPrintStatusDAO getPrintStatusDAO() {
		return printStatusDAO;
	}
	public void setPrintStatusDAO(IPrintStatusDAO printStatusDAO) {
		this.printStatusDAO = printStatusDAO;
	}
	public IConstituencyPrintStatusDAO getConstituencyPrintStatusDAO() {
		return constituencyPrintStatusDAO;
	}
	public void setConstituencyPrintStatusDAO(
			IConstituencyPrintStatusDAO constituencyPrintStatusDAO) {
		this.constituencyPrintStatusDAO = constituencyPrintStatusDAO;
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
					if(vo.getId().longValue() == id.longValue())
						return returnvo;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getMatchedVOById method in CardPrintService", e);
		}
		return returnvo;
	}
}
