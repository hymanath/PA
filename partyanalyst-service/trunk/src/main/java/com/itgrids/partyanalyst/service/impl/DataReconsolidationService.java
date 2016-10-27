package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreTabRecordsStatusDAO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.service.IDataReconsolidationService;

public class DataReconsolidationService implements IDataReconsolidationService {
	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	private ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO;
	
	
  public ICadreTabRecordsStatusDAO getCadreTabRecordsStatusDAO() {
		return cadreTabRecordsStatusDAO;
	}


	public void setCadreTabRecordsStatusDAO(
			ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO) {
		this.cadreTabRecordsStatusDAO = cadreTabRecordsStatusDAO;
	}


public List<CadreTabRecordsStatusVO> dataReConsalationOverView(Long constistuencyId,String fromDateStr,String toDateStr){
    	
    	List<CadreTabRecordsStatusVO> returnList = null;
    	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//SimpleDateFormat returnTime = new SimpleDateFormat("yyyy-MM-dd h:mm a");
		
		Date startDate = null;
		Date endDate = null;
    	try{
    		
    		if(fromDateStr != null && toDateStr != null){
    			startDate = sdf.parse(fromDateStr);
    			endDate = sdf.parse(toDateStr);
    		}
    		
    		List<Object[]> dataReConsalation = cadreTabRecordsStatusDAO.dataReConsalationOverView(constistuencyId,startDate,endDate);
    		
    		if( dataReConsalation != null && dataReConsalation.size() > 0)
    		{
    			returnList = new ArrayList<CadreTabRecordsStatusVO>();
    			
    			for(Object[] obj : dataReConsalation)
    			{
    				CadreTabRecordsStatusVO cadreTabRecordsStatusVO = new CadreTabRecordsStatusVO();
    				
    				cadreTabRecordsStatusVO.setCadreSurveyUserId( obj[0]!= null ? Long.valueOf(obj[0].toString()) : 0);
    				cadreTabRecordsStatusVO.setImeiNo(obj[1]!=null ? obj[1].toString() :"");
    				
    				cadreTabRecordsStatusVO.setName(obj[2]!=null ? obj[2].toString() :"");
    				cadreTabRecordsStatusVO.setTotalRecords(obj[3]!= null ? (Long)obj[3] : 0l);
    				
    				cadreTabRecordsStatusVO.setPending(obj[4]!=null ? (Long)obj[4]:0l);
    				cadreTabRecordsStatusVO.setKafkaPending(obj[5]!=null ? (Long)obj[5]:0l);
    				cadreTabRecordsStatusVO.setServerPending(obj[6]!=null ? (Long)obj[6]:0l);
    				
    				returnList.add(cadreTabRecordsStatusVO);
    			}
    		}
    		
    		
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at dataReConsalationOverView", e);
		}
    	return returnList;
    }
public List<CadreTabRecordsStatusVO> dataReConsalationTotalOverView(Long constistuencyId,String fromDateStr,String toDateStr){
	
	List<CadreTabRecordsStatusVO> returnList = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	Date startDate = null;
	Date endDate = null;
	try{
		
		if(fromDateStr != null && toDateStr != null){
			startDate = sdf.parse(fromDateStr);
			endDate = sdf.parse(toDateStr);
		}
		
		List<Object[]> dataReConsalation = cadreTabRecordsStatusDAO.dataReConsalationTotalOverView(constistuencyId,startDate,endDate);
		
		if( dataReConsalation != null && dataReConsalation.size() > 0)
		{
			returnList = new ArrayList<CadreTabRecordsStatusVO>();
			
			for(Object[] obj : dataReConsalation)
			{
				CadreTabRecordsStatusVO cadreTabRecordsStatusVO = new CadreTabRecordsStatusVO();
				
				cadreTabRecordsStatusVO.setTotalImeiNo(obj[0]!=null ? obj[0].toString() :"");
				cadreTabRecordsStatusVO.setTotalRecords(obj[1]!= null ? (Long)obj[1] : 0l);
				cadreTabRecordsStatusVO.setTotalSyn(obj[2]!=null ? (Long)obj[2]:0l);
				cadreTabRecordsStatusVO.setTotalPending(obj[3]!=null ? (Long)obj[3]:0l);
				returnList.add(cadreTabRecordsStatusVO);
			}
		}
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception raised at dataReConsalationOverView", e);
	}
	return returnList;
}

public List<CadreTabRecordsStatusVO> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,String startDate,String endDate){
	
	
	  List<CadreTabRecordsStatusVO> finalList = new ArrayList<CadreTabRecordsStatusVO>(0);
	   Long totRegCont = 0l;
	   Long totalAmount = 0l;
	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	   Date fromDate = null;
	   Date toDate = null;
	   try{
	   if(startDate !=null){
		   fromDate = sdf.parse(startDate);
	   }
	   if(endDate != null){
		   toDate = sdf.parse(endDate);
	   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	
	try{
	
		LOG.info("Entered into DataReconsolidationService of getCadreSurveyUserWiseRegistrations");
		List<Object[]> cadreRegDetils = cadreTabRecordsStatusDAO.getCadreSurveyUserWiseRegistrations(cadreSrveyUserId,constituencyId,fromDate,toDate);
		if(cadreRegDetils != null && !cadreRegDetils.isEmpty()){
			
			for(Object[] param : cadreRegDetils){
				CadreTabRecordsStatusVO tabStatsVO = new CadreTabRecordsStatusVO();
				tabStatsVO.setSurveyDate(param[0] != null ? sdf.format((Date)param[0]) : "");
				tabStatsVO.setName(param[1] != null ? param[1].toString() : "");
				tabStatsVO.setMobileNo(param[2] != null ? param[2].toString() : "");
				tabStatsVO.setFirstRecord(param[3] != null ? sdf.format((Date)param[3]) : "");
				tabStatsVO.setLastRecord(param[4] != null ? sdf.format((Date)param[4]) : "");
				//tabStatsVO.setTotalRecords(param[5]!=null?(Long.valueOf(param[5].toString())) :0l);
				Long syncRecord = param[6] != null ? (Long)param[6] : 0l;
                 if(syncRecord.longValue() > 0l){
              	   tabStatsVO.setSync(syncRecord);
                 }
                 Long pendingRecord = param[7] != null ? (Long)param[7] :0l;
                 if(pendingRecord.longValue()>0l){
              	   tabStatsVO.setPending(pendingRecord);
                 }
                 totRegCont = param[5] != null ? (Long) param[5]  : 0l;
                 if(totRegCont.longValue()>0l) {
              	   tabStatsVO.setTotalRecords(totRegCont);
                 }
                 
                 if(totalAmount.longValue() > 0l){
              	   totalAmount = totalAmount * 100;
                 }
				tabStatsVO.setTotalAmount(totalAmount);
				finalList.add(tabStatsVO);
			}
		
		}
	}catch(Exception e){
		LOG.error("Exception Occured into DataReconsolidationService of getCadreSurveyUserWiseRegistrations",e);
	}
	return finalList;
}




}
