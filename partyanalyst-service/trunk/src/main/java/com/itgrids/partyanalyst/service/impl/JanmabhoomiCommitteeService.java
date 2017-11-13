package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.dao.IJbCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.hibernate.JbCommitteeLevelDAO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.model.JbCommitteeLevel;
import com.itgrids.partyanalyst.service.IJanmabhoomiCommitteeService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class JanmabhoomiCommitteeService implements IJanmabhoomiCommitteeService{
	
	private final static Logger LOG = Logger.getLogger(JanmabhoomiCommitteeService.class);
    private IJbCommitteeDAO  jbCommitteeDAO ;
    private IJbCommitteeLevelDAO jbCommitteeLevelDAO; 
    private CommonMethodsUtilService commonMethodsUtilService;
    
	public IJbCommitteeDAO getJbCommitteeDAO() {
		return jbCommitteeDAO;
	}
	public void setJbCommitteeDAO(IJbCommitteeDAO jbCommitteeDAO) {
		this.jbCommitteeDAO = jbCommitteeDAO;
	}
	
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public IJbCommitteeLevelDAO getJbCommitteeLevelDAO() {
		return jbCommitteeLevelDAO;
	}
	public void setJbCommitteeLevelDAO(IJbCommitteeLevelDAO jbCommitteeLevelDAO) {
		this.jbCommitteeLevelDAO = jbCommitteeLevelDAO;
	}
	public static Logger getLog() {
		return LOG;
	}
	public List<JanmabhoomiCommitteeVO> getDistrictWiseCommitteeDetails(String fromDate,String endDate,String type){
		List<JanmabhoomiCommitteeVO> returnList = new ArrayList<JanmabhoomiCommitteeVO>(); 
		try{
			
			//List<Object[]> list = jbCommitteeDAO.
		}catch (Exception e) {
			LOG.error("Excepting Occured in getDistrictWiseCommitteeDetails() of JanmabhoomiCommitteeService ", e);
		}
		return returnList;
	}
	
	// * @author Swapna
	@Override
	public JanmabhoomiCommitteeVO getJbCommitteeStatusCount(String fromDateStr, String toDateStr) {
		JanmabhoomiCommitteeVO  mainVO = new JanmabhoomiCommitteeVO();
	 try{		 
		    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				Date startDate = null;
				Date endDate = null;
				if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
					startDate = sdf.parse(fromDateStr);
					endDate = sdf.parse(toDateStr);
				} 
		 
	 Map<Long,JanmabhoomiCommitteeVO> locationDtlsMap =new HashMap<Long, JanmabhoomiCommitteeVO>();	 
	 List<Object[]> committeeList = jbCommitteeDAO.getJbCommitteeStatusCount();
	 List<JbCommitteeLevel> committeeLevels= jbCommitteeLevelDAO.getAll();
	 if(commonMethodsUtilService.isListOrSetValid(committeeLevels)){
		 for (JbCommitteeLevel jbCommitteeLevel : committeeLevels) {
			 JanmabhoomiCommitteeVO vo=new JanmabhoomiCommitteeVO();
				 vo.setId(jbCommitteeLevel.getJbCommitteeLevelId());
				 vo.setName(jbCommitteeLevel.getName());
				 locationDtlsMap.put(vo.getId(),vo);
			  }
	 }			 
	 if (committeeList!=null && committeeList.size()>0){
		  for (Object[] objects : committeeList) {
			 JanmabhoomiCommitteeVO vo=null;
			     vo = locationDtlsMap.get((Long)objects[4]);
	    		  if(vo == null){
	    			  vo=new JanmabhoomiCommitteeVO();
	    			  vo.setId(commonMethodsUtilService.getLongValueForObject(objects[4]));
	    			  vo.setName(commonMethodsUtilService.getStringValueForObject(objects[5]).toUpperCase());
	    			  locationDtlsMap.put((Long)objects[4], vo);	
	    		      }
	    			  String confirmed     =    commonMethodsUtilService.getStringValueForObject((String)objects[1]);
	    			  String startdate     =    commonMethodsUtilService.getStringValueForObject((String)objects[2]);
	    			  String completedDate =    commonMethodsUtilService.getStringValueForObject((String)objects[3]);
	    			 
	    			 /* if(confirmed=="N" && startdate!=null){
	    				  vo.setInprogressCommitteeCnt(vo.getInprogressCommitteeCnt()+1);
	    			  }*/
	    			  if(confirmed.equalsIgnoreCase("N") && startdate==""){
	    				  vo.setNotStartedCommitteeCnt(vo.getNotStartedCommitteeCnt()+1);
	    			  }
	    			  if(confirmed.equalsIgnoreCase("Y") && completedDate!=""){
	    				  vo.setTotalApprovedCommitteeCnt(vo.getTotalApprovedCommitteeCnt()+1);
	    			  }	 			  
	    			  		  
	    		      }
	    		  if(commonMethodsUtilService.isMapValid(locationDtlsMap)){
	    			  for(Entry<Long,JanmabhoomiCommitteeVO> entry : locationDtlsMap.entrySet()){ 
	    				  JanmabhoomiCommitteeVO returnVo=entry.getValue(); 
	    				  mainVO.setTotalApprovedCommitteeCnt(mainVO.getTotalApprovedCommitteeCnt()+returnVo.getTotalApprovedCommitteeCnt());
	    				  mainVO.setInprogressCommitteeCnt(mainVO.getInprogressCommitteeCnt()+returnVo.getInprogressCommitteeCnt());
	    				  mainVO.setNotStartedCommitteeCnt(mainVO.getNotStartedCommitteeCnt()+returnVo.getNotStartedCommitteeCnt());
	    				  mainVO.getPositinsList().add(returnVo);
	    			  }			  
	    	   	      }
	                  }
	 
	
	 }catch(Exception e){
			Log.error("Exception raised in JanmabhoomiCommitteeService method of JanmabhoomiCommitteeService"+e);
		}
		return mainVO;
	 }
}






