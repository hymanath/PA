package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICallTrackingDetailDAO;
import com.itgrids.partyanalyst.dao.ICallTrackingProblemDAO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.model.CallTrackingDetail;
import com.itgrids.partyanalyst.model.CallTrackingProblem;
import com.itgrids.partyanalyst.service.ICallTrackingService;

public class CallTrackingService implements ICallTrackingService {
	
	
	private	ICallTrackingDetailDAO callTrackingDetailDAO;
	private	ICallTrackingProblemDAO callTrackingProblemDAO;
	private TransactionTemplate transactionTemplate = null;
	private static final Logger log = Logger.getLogger(ICallTrackingService.class);
	
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public ICallTrackingDetailDAO getCallTrackingDetailDAO() {
		return callTrackingDetailDAO;
	}

	public void setCallTrackingDetailDAO(
			ICallTrackingDetailDAO callTrackingDetailDAO) {
		this.callTrackingDetailDAO = callTrackingDetailDAO;
	}

	public ICallTrackingProblemDAO getCallTrackingProblemDAO() {
		return callTrackingProblemDAO;
	}

	public void setCallTrackingProblemDAO(
			ICallTrackingProblemDAO callTrackingProblemDAO) {
		this.callTrackingProblemDAO = callTrackingProblemDAO;
	}
	

	public List<CallTrackingVO> saveCallTrackingProblem(final CallTrackingVO callTrackingVO){
		List<CallTrackingVO> result = new ArrayList<CallTrackingVO>(); 
		CallTrackingVO callTracVO = new CallTrackingVO();
		try{
			
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					CallTrackingProblem callTrackingProblem = new CallTrackingProblem();
					CallTrackingDetail callTrackingDetail = new CallTrackingDetail();
					callTrackingProblem.setProblemPurpose(callTrackingVO.getProblemPurpose());
					callTrackingProblem.setReferenceNo(callTrackingVO.getReferenceNo());
					callTrackingProblem = callTrackingProblemDAO.save(callTrackingProblem);
					callTrackingDetail.setCallTrackingProblem(callTrackingProblem);
					callTrackingDetail.setName(callTrackingVO.getName());
					callTrackingDetail.setMobile(callTrackingVO.getMobile());
					callTrackingDetail.setVillageOrTown(callTrackingVO.getVillageOrTown());
					System.out.println(getCurrentDateAndTime());
					callTrackingDetail.setProblemAddedDate(getCurrentDateAndTime());
					callTrackingDetailDAO.save(callTrackingDetail);
				}
			});
		callTracVO.setStatus(1L);
		result.add(callTracVO);
		return result ;
		}
		catch(Exception e){
			e.printStackTrace();
			callTracVO.setStatus(0L);
			result.add(callTracVO);
			return result ;
		}
	}
	public Date getCurrentDateAndTime(){
		try {
		java.util.Date now = new java.util.Date();
        String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        String strDateNew = sdf.format(now);        
			now = sdf.parse(strDateNew);
			return now;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<CallTrackingVO> getCurrentDayCallTrackingProblem(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date today = getCurrentDateAndTime(); 
		List<CallTrackingVO> callTracVo = new ArrayList<CallTrackingVO>();
		CallTrackingVO callTrackingVO = null;
		
		List<Object[]> result = callTrackingProblemDAO.getProblemDetailbyTodayDate(today);
        
		for(Object[] param : result){
			callTrackingVO = new CallTrackingVO();
			callTrackingVO.setProblemId((Long)param[0]);
			callTrackingVO.setProblemPurpose(param[1] != null ? param[1].toString() : "");
			callTrackingVO.setReferenceNo(param[2] != null ? param[2].toString() : "");
			callTrackingVO.setName(param[3] != null ? param[3].toString() : "");
			callTrackingVO.setMobile(param[4] != null ? param[4].toString() : "");
			callTrackingVO.setProblemAddedDate(param[5] != null ?(sdf.format((Date)param[5])):"");
			callTrackingVO.setVillageOrTown(param[6] != null ? param[6].toString() : "");
			callTracVo.add(callTrackingVO);
		}
		
		return callTracVo;
	}
	public List<CallTrackingVO> getCurrentDayProblemCount(){
		Date today = getCurrentDateAndTime(); 
		List<CallTrackingVO> callTracVO = new ArrayList<CallTrackingVO>();
		List<Object[]> result = callTrackingProblemDAO.getProblemDetailbyTodayDate(today);
		CallTrackingVO callTrackingVO = new CallTrackingVO();
		callTrackingVO.setCount((long)(result.size()));
		callTracVO.add(callTrackingVO);
		return callTracVO;
	}
	
	public CallTrackingVO getCallTrackingProblemByMobile(String mobile){
		
		
		return null;
	}
	
	
	public List<CallTrackingVO> updateCallTrackingProblem(final CallTrackingVO callTrackingVO){
		List<CallTrackingVO> result = new ArrayList<CallTrackingVO>(); 
		CallTrackingVO callTracVO = new CallTrackingVO();
		try{
			
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					CallTrackingProblem callTrackingProblem = null;
					CallTrackingDetail callTrackingDetail = null;
	
					callTrackingProblem = callTrackingProblemDAO.get(callTrackingVO.getProblemId());
					callTrackingProblem.setProblemPurpose(callTrackingVO.getProblemPurpose());
					callTrackingProblem.setReferenceNo(callTrackingVO.getReferenceNo());
					callTrackingProblem = callTrackingProblemDAO.save(callTrackingProblem);
					
					callTrackingDetail = callTrackingDetailDAO.getCallTrackingDetailByCallTrackingProblemId(callTrackingVO.getProblemId()).get(0);
					callTrackingDetail.setCallTrackingProblem(callTrackingProblem);
					callTrackingDetail.setName(callTrackingVO.getName());
					callTrackingDetail.setMobile(callTrackingVO.getMobile());
					callTrackingDetail.setVillageOrTown(callTrackingVO.getVillageOrTown());
					
					callTrackingDetailDAO.save(callTrackingDetail);
				}
			});
		callTracVO.setStatus(1L);
		result.add(callTracVO);
		return result ;
		}
		catch(Exception e){
			e.printStackTrace();
			callTracVO.setStatus(0L);
			result.add(callTracVO);
			return result ;
		}
	}
	
	public ProblemBeanVO getCallTrackingProblemByProblemId(Long problemId){
		Object[] result = callTrackingProblemDAO.getProblemDetailbyProblemId(problemId).get(0);
		ProblemBeanVO problemBeanVO = new ProblemBeanVO();
		    
		problemBeanVO.setProblem(result[1] != null ?result[1].toString(): "");
		problemBeanVO.setName(result[1] != null ?result[3].toString(): "");
		problemBeanVO.setMobile(result[1] != null ?result[4].toString(): "");
		problemBeanVO.setVillage(result[1] != null ?result[6].toString(): "");
		
		return problemBeanVO;
	}
 

   public List<CallTrackingVO> searchCallTrackingProblem(final CallTrackingVO callTrackVO){
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date today = getCurrentDateAndTime(); 
		List<CallTrackingVO> callTracVo = new ArrayList<CallTrackingVO>();
		CallTrackingVO callTrackingVO = null; 
		
		Date addDate = null;
		if(callTrackVO.getProblemAddedDate()!=null){
		  String strAddDate = sdf.format(callTrackVO.getProblemAddedDate());        
		   try{
		   addDate = sdf.parse(strAddDate);
		   }catch(Exception e){e.printStackTrace();}
		}
		List<Object[]> result = callTrackingProblemDAO.searchCallTrackingProblem(callTrackVO, addDate);
	    int count = result.size();
	   for(Object[] param : result){
			callTrackingVO = new CallTrackingVO();
			callTrackingVO.setProblemId((Long)param[0]);
			callTrackingVO.setProblemPurpose(param[1] != null ? param[1].toString() : "");
			callTrackingVO.setReferenceNo(param[2] != null ? param[2].toString() : "");
			callTrackingVO.setName(param[3] != null ? param[3].toString() : "");
			callTrackingVO.setMobile(param[4] != null ? param[4].toString() : "");
			callTrackingVO.setProblemAddedDate(param[5] != null ?(sdf.format((Date)param[5])):"");
			callTrackingVO.setVillageOrTown(param[6] != null ? param[6].toString() : "");
			callTrackingVO.setCount((long)count);
			callTracVo.add(callTrackingVO);
		}
	   return callTracVo;
   }
}
