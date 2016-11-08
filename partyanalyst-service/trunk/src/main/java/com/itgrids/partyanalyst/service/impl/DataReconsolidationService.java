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

import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreTabRecordsStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.service.IDataReconsolidationService;

public class DataReconsolidationService implements IDataReconsolidationService {
	private static final Logger LOG = Logger.getLogger(FieldMonitoringService.class);
	private ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	
	
	
  public ICadreSurveyUserAssignDetailsDAO getCadreSurveyUserAssignDetailsDAO() {
		return cadreSurveyUserAssignDetailsDAO;
	}


	public void setCadreSurveyUserAssignDetailsDAO(
			ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO) {
		this.cadreSurveyUserAssignDetailsDAO = cadreSurveyUserAssignDetailsDAO;
	}


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
public List<CadreTabRecordsStatusVO> dataReConsalationOverView(Long stateId,Long constistuencyId,String fromDateStr,String toDateStr,Long districtId){
    	
    	List<CadreTabRecordsStatusVO> returnList = null;
    	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Date startDate = null;
		Date endDate = null;
    	try{
    		
    		if(fromDateStr != null && toDateStr != null){
    			startDate = sdf.parse(fromDateStr);
    			endDate = sdf.parse(toDateStr);
    		}
    		
    		List<Object[]> dataReConsalation = cadreTabRecordsStatusDAO.dataReConsalationOverView(stateId,constistuencyId,startDate,endDate,districtId);
    		
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
    				cadreTabRecordsStatusVO.setSync(obj[8]!= null ? (Long)obj[8] : 0l);
    				cadreTabRecordsStatusVO.setMaxRecordTime(obj[9]!= null ?obj[9].toString() : "");
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
    		if(returnList != null && returnList.size() > 0){
    		for(CadreTabRecordsStatusVO vo : returnList){
    		  Long count = actualMap.get(vo.getCadreSurveyUserId());
    		  if(count !=null)
    			  vo.setActualCount(count);
    		}
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
public CadreTabRecordsStatusVO dataReConsalationTotalOverView(Long stateId,Long constistuencyId,String fromDateStr,String toDateStr,Long districtId){
	
	  CadreTabRecordsStatusVO statusvo = null;
	
	   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
			Date startDate = null;
			Date endDate = null;
			
			  try{
				if(fromDateStr != null && toDateStr != null){
					startDate = sdf.parse(fromDateStr);
					endDate = sdf.parse(toDateStr);
				}
				
				List<Object[]> dataReConsalationObjs = cadreTabRecordsStatusDAO.dataReConsalationTotalOverView(stateId,constistuencyId,startDate,endDate,districtId);
		
				Long totalSmartDevics = 0l;
				Long totalRecords = 0l;
				Long totalSync = 0l;
				Long totalPending = 0l;
				if( dataReConsalationObjs != null && dataReConsalationObjs.size() >0)
				{
					
					for(Object[] dataReConsalationObj : dataReConsalationObjs){
			      
			      totalSmartDevics = dataReConsalationObj[0] != null ? totalSmartDevics+(Long)dataReConsalationObj[0] :0l;
			      totalRecords = dataReConsalationObj[1] != null ? totalRecords+(Long)dataReConsalationObj[1] : 0l;
			      totalSync = dataReConsalationObj[2]!=null ?totalSync+(Long)dataReConsalationObj[2] : 0l;
			      totalPending = dataReConsalationObj[3] != null ? totalPending+(Long)dataReConsalationObj[3] : 0l;
					}
				  }
				statusvo = new CadreTabRecordsStatusVO();
				statusvo.setActualCount(totalSmartDevics);
			    statusvo.setTotalRecords(totalRecords);
			    statusvo.setTotalSyn(totalSync);
			    statusvo.setTotalPending(totalPending);
		
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
				String t[] = param[3].toString().split(" ")[1].split(":");
				String t1[] = param[4].toString().split(" ")[1].split(":");
			    tabStatsVO.setFirstRecord(param[3] != null ?  new SimpleDateFormat("hh:mm a").format(new SimpleDateFormat("HH:mm").parse(t[0]+":"+t[1])):"");
				tabStatsVO.setLastRecord(param[4] != null ?  new SimpleDateFormat("hh:mm a").format(new SimpleDateFormat("HH:mm").parse(t1[0]+":"+t1[1])):"");
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

/**
* @param  Long districtId,constituencyId,fromDateStr, toDateStr
* @return  List<IdAndNameVO>
* @author Hymavathi 
* @Description : 
*  @since 08-Nov-2016
*/
public List<IdAndNameVO> getLocationWiseSmartDevicesCount(Long stateId,Long districtId,Long constituencyId,String startDate,String endDate){
	
	
	  List<IdAndNameVO> finalList = new ArrayList<IdAndNameVO>(0);	 
	try{
	
		LOG.info("Entered into DataReconsolidationService of getLocationWiseSmartDevicesCount");
		
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
		   Date fromDate = null;
		   Date toDate = null;
		
		   /*if(startDate !=null && endDate != null){			   
			   fromDate = sdf.parse(startDate);
			   toDate = sdf.parse(endDate);
		   }*/
		 
		List<Object[]> cadreRegDetils = cadreSurveyUserAssignDetailsDAO.getLocationWiseSmartDevicesCount(stateId,districtId,constituencyId,fromDate,toDate);
		if(cadreRegDetils != null && !cadreRegDetils.isEmpty()){
			
			for(Object[] param : cadreRegDetils){
				IdAndNameVO locationVO = new IdAndNameVO();
				locationVO.setId(param[0] != null ? (Long)param[0] : 0l);
				locationVO.setName(param[1] != null ?param[1].toString():"");
				locationVO.setApTotal(param[2] != null ? (Long)param[2] : 0l);
				finalList.add(locationVO);
			}
		
		}
	}catch(Exception e){
		LOG.error("Exception Occured into DataReconsolidationService of getCadreSurveyUserWiseRegistrations",e);
	}
	return finalList;
}


}
