package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ICadreTabRecordsStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.service.IDataReconsolidationService;

public class DataReconsolidationService implements IDataReconsolidationService {
	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	private ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO;
	private ITdpCadreDAO tdpCadreDAO;
	
	
  public ICadreTabRecordsStatusDAO getCadreTabRecordsStatusDAO() {
		return cadreTabRecordsStatusDAO;
	}


	public void setCadreTabRecordsStatusDAO(
			ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO) {
		this.cadreTabRecordsStatusDAO = cadreTabRecordsStatusDAO;
	}
 

public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	/**
	* @param  Long constistuencyId,cadreSrveyUserId,fromDateStr, toDateStr
	* @return  CadreTabRecordsStatusVO
	* @author srujana 
	* @Description : This Service is total tab user wise total Registration count and sync pending   details
	*  @since 27th-October-2016
	*/
public List<CadreTabRecordsStatusVO> dataReConsalationOverView(Long constistuencyId,String fromDateStr,String toDateStr){
    	
    	List<CadreTabRecordsStatusVO> returnList = null;
    	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Date startDate = null;
		Date endDate = null;
    	try{
    		
    		if(fromDateStr != null && toDateStr != null){
    			startDate = sdf.parse(fromDateStr);
    			endDate = sdf.parse(toDateStr);
    		}
    		
    		List<Object[]> dataReConsalation = cadreTabRecordsStatusDAO.dataReConsalationOverView(constistuencyId,startDate,endDate);
    		
    		Set<Long> cadreSurveyUsers = new HashSet<Long>();
    		if( dataReConsalation != null && dataReConsalation.size() > 0)
    		{
    			returnList = new ArrayList<CadreTabRecordsStatusVO>();
    			
    			for(Object[] obj : dataReConsalation)
    			{
    				CadreTabRecordsStatusVO cadreTabRecordsStatusVO = new CadreTabRecordsStatusVO();
    				
    				cadreTabRecordsStatusVO.setCadreSurveyUserId( obj[7]!= null ? Long.valueOf(obj[7].toString()) : 0l);
    				
    				cadreTabRecordsStatusVO.setUserName(obj[0]!= null ?obj[0].toString() : "");
    				cadreTabRecordsStatusVO.setImeiNo(obj[1]!=null ? obj[1].toString() :"");
    				
    				cadreTabRecordsStatusVO.setName(obj[2]!=null ? obj[2].toString() :"");
    				cadreTabRecordsStatusVO.setTotalRecords(obj[3]!= null ? (Long)obj[3] : 0l);
    				
    				cadreTabRecordsStatusVO.setPending(obj[4]!=null ? (Long)obj[4]:0l);
    				cadreTabRecordsStatusVO.setKafkaPending(obj[5]!=null ? (Long)obj[5]:0l);
    				cadreTabRecordsStatusVO.setKafkaSync(obj[6]!=null ? (Long)obj[6]:0l);
    				    				
    				cadreSurveyUsers.add(obj[7]!= null ? Long.valueOf(obj[7].toString()) : 0l);
    				
    				returnList.add(cadreTabRecordsStatusVO);
    			}
    		}
    		
    		Map<Long,Long> actualMap = new HashMap<Long, Long>();
    		List<Object[]> cadreSurveyCountObj =  tdpCadreDAO.getActualCountOfCadreSurveyUser(cadreSurveyUsers);
    		
    		if(cadreSurveyCountObj !=null && cadreSurveyCountObj.size()>0){
    			for (Object[] objects : cadreSurveyCountObj) {
    				actualMap.put((Long)objects[0],objects[1] !=null ? (Long)objects[1]:0l);
				}
    		}
    		
    		for(CadreTabRecordsStatusVO vo : returnList){
    		  Long count = actualMap.get(vo.getCadreSurveyUserId());
    		  if(count !=null)
    			  vo.setActualCount(count);
    		}

    		return returnList;
    		
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at dataReConsalationOverView", e);
		}
    	return returnList;
    }


	/**
	* @param  Long constistuencyId,fromDateStr, toDateStr
	* @return  CadreTabRecordsStatusVO
	* @author Krishna 
	* @Description : This Service is user to get the day wise total Registration and sum of pending counts
	*  @since 27th-October-2016
	*/
public CadreTabRecordsStatusVO dataReConsalationTotalOverView(Long constistuencyId,String fromDateStr,String toDateStr){
	
	  CadreTabRecordsStatusVO statusvo = null;
	
	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
			Date startDate = null;
			Date endDate = null;
			
			  try{
				if(fromDateStr != null && toDateStr != null){
					startDate = sdf.parse(fromDateStr);
					endDate = sdf.parse(toDateStr);
				}
				
		  Object[] dataReConsalationObj = cadreTabRecordsStatusDAO.dataReConsalationTotalOverView(constistuencyId,startDate,endDate);
		
				if( dataReConsalationObj != null)
				{
			      statusvo = new CadreTabRecordsStatusVO();
	
					statusvo.setTotalImeiNo(dataReConsalationObj[0] != null ? dataReConsalationObj[0].toString() :"");
				    statusvo.setTotalRecords(dataReConsalationObj[1] != null ? (Long)dataReConsalationObj[1] : 0l);
				    statusvo.setTotalSyn(dataReConsalationObj[2]!=null ? (Long)dataReConsalationObj[2] : 0l);
				    statusvo.setTotalPending(dataReConsalationObj[3] != null ? (Long)dataReConsalationObj[3] : 0l);
				  }
		
	   }catch(Exception e){
		 e.printStackTrace();
		   LOG.error("Exception raised at dataReConsalationOverView", e);
	  }
	   return statusvo;
}


/**
* @param  Long constistuencyId,cadreSrveyUserId,fromDateStr, toDateStr
* @return  CadreTabRecordsStatusVO
* @author Krishna 
* @Description : This Service is user to get the Survey  user wise  Registration count  details and sync pending count
*  @since 27th-October-2016
*/
public List<CadreTabRecordsStatusVO> getCadreSurveyUserWiseRegistrations(Long cadreSrveyUserId,Long constituencyId,String startDate,String endDate){
	
	
	  List<CadreTabRecordsStatusVO> finalList = new ArrayList<CadreTabRecordsStatusVO>(0);	 
	try{
	
		LOG.info("Entered into DataReconsolidationService of getCadreSurveyUserWiseRegistrations");
		
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 SimpleDateFormat outputFormat = new SimpleDateFormat("KK:mm a");
		 SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
	
		   Date fromDate = null;
		   Date toDate = null;
		
		   if(startDate !=null && endDate != null){			   
			   fromDate = sdf.parse(startDate);
			   toDate = sdf.parse(endDate);
		   }
		 
		List<Object[]> cadreRegDetils = cadreTabRecordsStatusDAO.getCadreSurveyUserWiseRegistrations(cadreSrveyUserId,constituencyId,fromDate,toDate);
		if(cadreRegDetils != null && !cadreRegDetils.isEmpty()){
			
			for(Object[] param : cadreRegDetils){
				CadreTabRecordsStatusVO tabStatsVO = new CadreTabRecordsStatusVO();
				tabStatsVO.setSurveyDate(param[0] != null ? format1.format((Date)param[0]) : "");
				tabStatsVO.setName(param[1] != null ? param[1].toString() : "");
				tabStatsVO.setMobileNo(param[2] != null ? param[2].toString() : "");
				tabStatsVO.setFirstRecord(param[3] != null ? outputFormat.format(inputFormat.parse(param[3].toString())) : "");
				tabStatsVO.setLastRecord(param[4] != null ?  outputFormat.format(inputFormat.parse(param[4].toString())) : "");
				tabStatsVO.setTotalRecords(param[5]!=null?(Long.valueOf(param[5].toString())) :0l);				
				tabStatsVO.setSync(param[6] != null ? (Long)param[6] : 0l);
				tabStatsVO.setPending(param[7] != null ? (Long)param[7] : 0l);
				tabStatsVO.setKafkaPending(param[8] != null ? (Long)param[8] : 0l);
				tabStatsVO.setKafkaSync(param[9] != null ? (Long)param[9] : 0l);
				tabStatsVO.setTabUserInfoId(param[10] != null ? (Long)param[10] : 0l);
				tabStatsVO.setImagePath(param[11] != null ?param[11].toString():"");

                 if(tabStatsVO.getTotalRecords() !=null && tabStatsVO.getTotalRecords().longValue()>0l) {
              	   	 tabStatsVO.setTotalAmount(tabStatsVO.getTotalRecords() * 100);
                 }
				finalList.add(tabStatsVO);
			}
		
		}
	}catch(Exception e){
		LOG.error("Exception Occured into DataReconsolidationService of getCadreSurveyUserWiseRegistrations",e);
	}
	return finalList;
}




}
