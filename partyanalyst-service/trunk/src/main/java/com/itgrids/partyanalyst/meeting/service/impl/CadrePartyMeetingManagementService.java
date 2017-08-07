package com.itgrids.partyanalyst.meeting.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.dao.IAttendanceTabUserDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMainTypeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingSessionDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dao.ISessionTypeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.meeting.service.ICadrePartyMeetingManagementService;
import com.itgrids.partyanalyst.model.Attendance;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;
import com.itgrids.partyanalyst.model.PartyMeetingAttendanceTabUser;
import com.itgrids.partyanalyst.model.PartyMeetingInvitee;
import com.itgrids.partyanalyst.model.PartyMeetingSession;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class CadrePartyMeetingManagementService implements ICadrePartyMeetingManagementService {

	private static Logger LOG = Logger.getLogger(CadrePartyMeetingManagementService.class);
	private IDistrictDAO districtDAO;
	private ConstituencyDAO constituencyDAO;
	private TransactionTemplate transactionTemplate = null;
	private ITdpCadreDAO tdpCadreDAO;
	private IPartyMeetingDAO partyMeetingDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;
	private IPartyMeetingMainTypeDAO partyMeetingMainTypeDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IPartyMeetingTypeDAO partyMeetingTypeDAO; 
	private DateUtilService dateUtilService;
	private IPartyMeetingLevelDAO partyMeetingLevelDAO; 
	private ISessionTypeDAO sessionTypeDAO; 
	private IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO;
	private ITehsilDAO tehsilDAO;
	private IUserAddressDAO userAddressDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private IAttendanceTabUserDAO attendanceTabUserDAO;
	private IPartyMeetingSessionDAO partyMeetingSessionDAO;
	private IStateDAO stateDAO;
	private IUserDAO userDAO;
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	private  ResultStatus resultStatus;
    private IAttendanceDAO attendanceDAO;
    private ILocalElectionBodyDAO localElectionBodyDAO;
	private IWardDAO wardDAO;
    
	
	 public IWardDAO getWardDAO() {
		return wardDAO;
	}

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IPartyMeetingSessionDAO getPartyMeetingSessionDAO() {
		return partyMeetingSessionDAO;
	}

	public void setPartyMeetingSessionDAO(
			IPartyMeetingSessionDAO partyMeetingSessionDAO) {
		this.partyMeetingSessionDAO = partyMeetingSessionDAO;
	}

	public ConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(ConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IPartyMeetingMainTypeDAO getPartyMeetingMainTypeDAO() {
		return partyMeetingMainTypeDAO;
	}

	public void setPartyMeetingMainTypeDAO(
			IPartyMeetingMainTypeDAO partyMeetingMainTypeDAO) {
		this.partyMeetingMainTypeDAO = partyMeetingMainTypeDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public IPartyMeetingTypeDAO getPartyMeetingTypeDAO() {
		return partyMeetingTypeDAO;
	}

	public void setPartyMeetingTypeDAO(IPartyMeetingTypeDAO partyMeetingTypeDAO) {
		this.partyMeetingTypeDAO = partyMeetingTypeDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IPartyMeetingLevelDAO getPartyMeetingLevelDAO() {
		return partyMeetingLevelDAO;
	}

	public void setPartyMeetingLevelDAO(IPartyMeetingLevelDAO partyMeetingLevelDAO) {
		this.partyMeetingLevelDAO = partyMeetingLevelDAO;
	}

	public ISessionTypeDAO getSessionTypeDAO() {
		return sessionTypeDAO;
	}

	public void setSessionTypeDAO(ISessionTypeDAO sessionTypeDAO) {
		this.sessionTypeDAO = sessionTypeDAO;
	}

	public IPartyMeetingAttendanceTabUserDAO getPartyMeetingAttendanceTabUserDAO() {
		return partyMeetingAttendanceTabUserDAO;
	}

	public void setPartyMeetingAttendanceTabUserDAO(
			IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO) {
		this.partyMeetingAttendanceTabUserDAO = partyMeetingAttendanceTabUserDAO;
	}

	public IPartyMeetingInviteeDAO getPartyMeetingInviteeDAO() {
		return partyMeetingInviteeDAO;
	}

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public IPartyMeetingDAO getPartyMeetingDAO() {
		return partyMeetingDAO;
	}

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}

	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}

	public IAttendanceTabUserDAO getAttendanceTabUserDAO() {
		return attendanceTabUserDAO;
	}

	public void setAttendanceTabUserDAO(IAttendanceTabUserDAO attendanceTabUserDAO) {
		this.attendanceTabUserDAO = attendanceTabUserDAO;
	}

	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	
	public IPartyMeetingAttendanceDAO getPartyMeetingAttendanceDAO() {
		return partyMeetingAttendanceDAO;
	}

	public IAttendanceDAO getAttendanceDAO() {
		return attendanceDAO;
	}

	public void setAttendanceDAO(IAttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}

	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}

	public List<KeyValueVO> getDistrictsBasedOnStateId(Long stateId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>(0);
		try {
		
	  List<Object[]>districtList=districtDAO.getDistrictsForState(stateId);
	 if(districtList != null && !districtList.isEmpty()){
		for (Object[] obj : districtList) {
			KeyValueVO vo = new KeyValueVO();
			vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
			vo.setName(obj[1] != null ? obj[1].toString():"");
			returnList.add(vo);
		 }
	   }
	} catch (Exception e) {
		LOG.error("Error occured getDistrictByStateId() method of MeetingService",e);
	}
		return returnList;
	
	}
	/**
	 * Date : 27/06/2017
	 * Author :pavan kumar
	 * @description : getConstituencyBasedOnDistrictId 
	 * @return  List<KeyValueVO> 
	 */
	
	public List<KeyValueVO> getConstituencyBasedOnDistrictId(Long districtId) {
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>(0);
		try {
	
		List<Object[]>constituencyList=constituencyDAO.getConstituencyBasedOnDistrictId(districtId);
			 if(constituencyList != null && !constituencyList.isEmpty()){
				for (Object[] obj : constituencyList) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				 }
			   }
			} catch (Exception e) {
				LOG.error("Error occured getConstituencyBasedOnDistrictId() method of MeetingService",e);
			}
				return returnList;
			
		}
	

	public String saveInvitiesDetails(final PartyMeetingVO partyMeetingVO,final File file,final Long partyMeetingId) {
		final File fstream=file;
		   String status ="";
		      try {
		        status = (String) transactionTemplate.execute(new TransactionCallback() {
		          
		          public Object doInTransaction(TransactionStatus arg0) {
		            Long rowCount = 1l;
		          try {
		            //FileInputStream fileName = new FileInputStream("D:\\Book11.csv");
		              FileInputStream fstream1 = new FileInputStream(fstream);
		            DataInputStream in = new DataInputStream(fstream1);
		            BufferedReader br = new BufferedReader(new InputStreamReader(in));
		            String strLineStr;
		             
		            Long checkInvitee=0l;
		            List<String> checkInviteeList=new ArrayList<String>();
		            List<String> membershipIdList=new ArrayList<String>();
		            while ((strLineStr = br.readLine()) != null) {
		              String[] cadreIdList = strLineStr.split(",");
		          	  
		              if(cadreIdList != null && cadreIdList.length>0){
		                	if(rowCount>1l){
		                		checkInvitee=0l;
		                		Long onlyNumberColumnCountInEachRow=0l;
		                for (int i = 0; i < cadreIdList.length; i++) {
		                	if(StringUtils.isNumeric(cadreIdList[i])){
		                		onlyNumberColumnCountInEachRow++;
		                	}
		                	String membershipId="";
		                	if(onlyNumberColumnCountInEachRow==2l){
		                		if(cadreIdList[i].length()==7){
			                		membershipId+="0"+cadreIdList[i];
			                		membershipIdList.add(membershipId);
			                	}
			                	else{
			                		membershipId=cadreIdList[i];
			                		membershipIdList.add(membershipId);
			                	}
		                		checkInvitee=(long) i;
		                		System.out.println(i);
		                	}
		                	if(checkInvitee>0l){
		                	if(cadreIdList[(int) (checkInvitee+0l)] == null){
		                		checkInviteeList.add(membershipId);
		                	   }
		                	}
		                    //break;
		                }
		              }
		                	rowCount++;	
		              
		              }
		            }
		            
		           
		           if(partyMeetingId !=null && partyMeetingId > 0l && checkInviteeList.size()>0){
		        	   if(checkInviteeList.size()>0){ // editing invitees 
			            	for(int i=0;i<checkInviteeList.size();i++){
			            		PartyMeetingInvitee newpartyMeetingInvitee=new PartyMeetingInvitee();
		            			newpartyMeetingInvitee.setPartyMeetingId(partyMeetingId);
		            			newpartyMeetingInvitee.setUpdatedById(partyMeetingVO.getInsertedById());
		            			newpartyMeetingInvitee.setTdpCadreId((Long.valueOf(checkInviteeList.get(i))));
		            			newpartyMeetingInvitee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		                		partyMeetingInviteeDAO.save(newpartyMeetingInvitee);
		            			return "successfully saved new invitiees";
			            	}
			            }
		           }
		            
		            else{
		            List<Object[]> tdpCadreList=tdpCadreDAO.getCadreDetailsByMemberShipId(membershipIdList);//7
	                
	                List<String> notAMemberList=new ArrayList<String>();
	                List<String> alreadyMemberList=new ArrayList<String>();
	                
	                List<String> dbMembershipIdList=new ArrayList<String>();
	                
	                for(int i=0;i<tdpCadreList.size();i++){
	                	Object[] objArray=tdpCadreList.get(i);
	                	dbMembershipIdList.add(objArray[0].toString());
	                }
	                for(int i=0;i<membershipIdList.size();i++){
	                	if(dbMembershipIdList.contains(membershipIdList.get(i))){
	                		alreadyMemberList.add(membershipIdList.get(i));
	                	}
	                	else{
	                		notAMemberList.add(membershipIdList.get(i));
	                	}
	                }
	 
	                System.out.println("notAMemberList "+notAMemberList);
	                System.out.println("alreadyMemberList "+alreadyMemberList);
	                if(notAMemberList.size()==0){
	                	for(int i=0;i<alreadyMemberList.size();i++){
	                		DateUtilService dateUtilService=new DateUtilService();
	                		PartyMeetingInvitee partyMeetingInvitee=new PartyMeetingInvitee();
	                		partyMeetingInvitee.setPartyMeetingId(partyMeetingId);
	                		partyMeetingInvitee.setInsertedById(partyMeetingVO.getInsertedById());
	                		partyMeetingInvitee.setTdpCadreId((Long.valueOf(alreadyMemberList.get(i))));
	                		partyMeetingInvitee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
	                		partyMeetingInviteeDAO.save(partyMeetingInvitee);
	                		return "This invities haven been saved successfully";
	                	}
	                }
	                else{
	                	return "This MemeberShipId's are not registered "+notAMemberList;
	                }
		            }
	                
		          }catch(Exception e) {
		            e.printStackTrace();
		            return "failure";
		          }
		          StringBuilder str=new StringBuilder();
		          str.append(rowCount-1);
		          return "No of membership id's saved successfully "+ str.toString();
		        }
		      });
		    }catch (Exception e) {
		      e.printStackTrace();
		      return "failure";
		    }
		  return status;
	}

	/*
	    public PartyMeeting saveMeetingDetails(final PartyMeetingVO inputvo){
	    	PartyMeeting result = (PartyMeeting) transactionTemplate.execute(new TransactionCallback() {
	    		public Object doInTransaction(TransactionStatus status) {
	    			PartyMeeting retunedObj=new PartyMeeting();
	    			try
	    			   {
	    				   	DateUtilService date = new DateUtilService();
	    					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	    					if(inputvo.getPartyMeetingId() == null){
	    						PartyMeeting partymeeting=new PartyMeeting();
		    					partymeeting.setMeetingName(inputvo.getName());
		    					partymeeting.setPartyMeetingTypeId(inputvo.getPartyMeetingTypeId());
		    					partymeeting.setPartyMeetingLevelId(inputvo.getMeetingLevelId());
		    					partymeeting.setLocationValue(inputvo.getLocationValue());
		    					partymeeting.setPartyMeetingOccurrenceId(1L);
		    					partymeeting.setStartDate(inputvo.getStartDate());
		    					partymeeting.setEndDate(inputvo.getEndDate());
		    					
		    				    UserAddress userAddress=new UserAddress();
		    					userAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
		    					userAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
		    					userAddress.setTehsil(tehsilDAO.get(inputvo.getTehsilId()));
		    					userAddress.setPanchayatId(inputvo.getVillageId());
		    					
		    					UserAddress returnedUserAddress=userAddressDAO.save(userAddress);
		    					if(returnedUserAddress!= null && returnedUserAddress.getUserAddressId() != null){
		    						partymeeting.setMeetingAddressId(returnedUserAddress.getUserAddressId());
		    					}
		    					partymeeting.setInsertedById(inputvo.getInsertedById());
		    					
		    					partymeeting.setIsActive("Y");  
		    					partymeeting.setAttendanceEnrolmentYear("ALL");
		    					partymeeting.setInsertedTime(date.getCurrentDateAndTime());
		    					
		    					partymeeting.setLocationValue(getLocationLevelId(inputvo));
	    						retunedObj=partyMeetingDAO.save(partymeeting);
	    						//retunedObj=new PartyMeeting();
		    					//retunedObj.setPartyMeetingId(10l);
	    					}else{
	    						PartyMeeting meeting = partyMeetingDAO.get(inputvo.getPartyMeetingId());
	    						meeting.setMeetingName(inputvo.getName());
	    						meeting.setPartyMeetingTypeId(inputvo.getPartyMeetingTypeId());
	    						meeting.setPartyMeetingLevelId(inputvo.getMeetingLevelId());
	    						meeting.setLocationValue(inputvo.getLocationValue());
	    						meeting.setPartyMeetingOccurrenceId(1L);
	    						meeting.setStartDate(inputvo.getStartDate());
	    						meeting.setEndDate(inputvo.getEndDate());
	    						meeting.setInsertedById(inputvo.getInsertedById());
	    						meeting.setIsActive("Y");
	    						meeting.setAttendanceEnrolmentYear("ALL");
	    						meeting.setUpdatedTime(date.getCurrentDateAndTime());
	    						meeting.setLocationValue(getLocationLevelId(inputvo));
	    						Long meetingAddressId=meeting.getMeetingAddressId();
	    						if(meetingAddressId!=null){
	    							UserAddress dbUserAddress=userAddressDAO.get(meetingAddressId);
	    							if(dbUserAddress!=null){
	    								 UserAddress changedUserAddress=new UserAddress();
	    								 changedUserAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
	    								 changedUserAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
	    								 changedUserAddress.setTehsil(tehsilDAO.get(inputvo.getTehsilId()));
	    								 changedUserAddress.setPanchayatId(inputvo.getVillageId());
	    		    					 userAddressDAO.save(changedUserAddress);
	    							}
	    						}
	    						//retunedObj=new PartyMeeting();
		    					//retunedObj.setPartyMeetingId(10l);
	    						retunedObj=partyMeetingDAO.save(meeting);
	    					}
	    			   }catch(Exception e){
	    				   e.printStackTrace();
	    			   }
					return retunedObj;
	    		}
	    	});
			return result;
      }
	    
	    */
	  
	    public List<PartyMeetingsVO> getMeetingMainType(){
			List<PartyMeetingsVO> finalList=null;
			try{
				List<Object[]> objectList=partyMeetingMainTypeDAO.getMeetingMainType();
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] obj: objectList){
						PartyMeetingsVO vo=new PartyMeetingsVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						finalList.add(vo);
					}
				}
			}catch(Exception e){
				LOG.error("Exception Occured in getMeetingMainType  method, Exception - ",e); 
			}
			return finalList;
		}

	    public List<PartyMeetingsVO> getMeetingSubType(Long partyMeetingMainTypeId) {
			List<PartyMeetingsVO> finalList=null;
			try{
				List<Object[]> objectList=partyMeetingTypeDAO.getPartyMeetingTypeByPartyMeetingMainType(partyMeetingMainTypeId);
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] obj: objectList){
						PartyMeetingsVO vo=new PartyMeetingsVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));
						finalList.add(vo);
					}
			  }
			}catch(Exception e){
				LOG.error("Exception Occured in getMeetingSubType  method, Exception - ",e); 
			}
			return finalList;
	  }
	
	  
	  /*  public List<PartyMeetingsVO> getPartyMeetingLevels(){
			List<PartyMeetingsVO> finalList=null;
			try{
				List<Object[]> objectList=partyMeetingLevelDAO.getPartyMeetingLevels();
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] objcts: objectList){
						PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
						partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));
						partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[1]));
						finalList.add(partyMeetingVO);
					}
			    }
			}catch(Exception e){
				LOG.error("Exception Occured in getPartyMeetingLevels  method, Exception - ",e); 
			}
			return finalList;
		} */
	    
	    /**
	  		 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
	  		 * @Date 28th June,2017
	  		 * @description to  get party meeting detailes
	  		 * @param loctionLevelId, start date end date
	  		 * @return List of PartyMeetingsVO 
	  	 */
	    public List<PartyMeetingsVO> getCadrePartyMeetngDeatils(String startDateStr,String endDateStr,Long meetigLevelId,int startIndex,int maxIndex){
	        List<PartyMeetingsVO> finalList=null;
	        Long totalMeetingCount=0L;
	         List<Long> meetingLevels=null;
	        try{
	          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	          SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	            Date fromDate = null;
	            Date  toDate = null;
	            if(startDateStr != null && endDateStr != null){
	              fromDate = sdf.parse(startDateStr.trim());
	              toDate = sdf.parse(endDateStr.trim());
	            }  
	            meetingLevels=new ArrayList<Long>();
	  	         meetingLevels.add(meetigLevelId);
	            
	           totalMeetingCount= partyMeetingDAO.getCadrePartyMeetngDeatilsCount(fromDate,toDate,meetingLevels);
	        List<Object[]> objectList=partyMeetingDAO.getCadrePartyMeetngDeatils(fromDate,toDate,meetingLevels, startIndex,maxIndex);
	        if(objectList !=null && objectList.size() > 0){
	          finalList=new ArrayList<PartyMeetingsVO>();
	          for(Object[] objcts: objectList){
	            PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
	            partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));// meeting id
	            partyMeetingVO.setMeetingName(commonMethodsUtilService.getStringValueForObject(objcts[1]));// meeting name
	            partyMeetingVO.setRemarks(commonMethodsUtilService.getStringValueForObject(objcts[2]));//meeting level
	            partyMeetingVO.setMeetingType(commonMethodsUtilService.getStringValueForObject(objcts[3]));//meeting type
	            String []startDateArr=commonMethodsUtilService.getStringValueForObject(objcts[4]).split("\\s");
	            partyMeetingVO.setUpdatedTime(startDateArr[0]);// meeting start date
	            partyMeetingVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(objcts[5]));// district name
	            partyMeetingVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objcts[6]));//constincy name
	            partyMeetingVO.setTeshilName(commonMethodsUtilService.getStringValueForObject(objcts[7]));// tesil name
	            partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[8]));// village name.
	            partyMeetingVO.setWardName(commonMethodsUtilService.getStringValueForObject(objcts[10]));//ward Name
	            partyMeetingVO.setMandalTwnDivision(commonMethodsUtilService.getStringValueForObject(objcts[9]));//localelection body
	            partyMeetingVO.setStateName(commonMethodsUtilService.getStringValueForObject(objcts[11]));//state name
	            String []endDate=commonMethodsUtilService.getStringValueForObject(objcts[12]).split("\\s");
	            partyMeetingVO.setMonth(endDate[0]);// end date
	            partyMeetingVO.setIsCondacted(commonMethodsUtilService.getStringValueForObject(objcts[13]));
	            Date currrentdate=sdf2.parse(dateUtilService.getCurrentDateInStringFormat());
	            Long daysCount=dateUtilService.noOfDayBetweenDates(endDate[0],dateUtilService.getDateInStringFormatByDate(currrentdate,"yyyy-MM-dd"));
	            if(daysCount <=2){
	              partyMeetingVO.setFlage("true");// sett flag to set meeting Edit button 
	            }
	            finalList.add(partyMeetingVO);
	          }
	          
	        }
	        if(finalList !=null && finalList.size() >0 ){
	        finalList.get(0).setTotalCount(totalMeetingCount);
	        }
	        }catch(Exception e){
	          LOG.error("Exception Occured in getCadrePartyMeetngDeatils  method, Exception - ",e); 
	        }
	        return finalList;
	      }
	    
	    
	    
	    
		public List<PartyMeetingsVO> getPartyMeetingLevels(Long partyMeetingSubTypeId){
		      List<PartyMeetingsVO> finalList=null;
		      try{
		        List<Object[]> objectList=partyMeetingTypeDAO.getPartyMeetingLevels(partyMeetingSubTypeId);
		        if(objectList !=null && objectList.size() > 0){
		          finalList=new ArrayList<PartyMeetingsVO>();
		          for(Object[] objcts: objectList){
		            PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
		            partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));
		            partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[1]));
		            finalList.add(partyMeetingVO);
		          }
		          }
		      }catch(Exception e){
		        LOG.error("Exception Occured in getPartyMeetingLevels  method, Exception - ",e); 
		      }
		      return finalList;
		    }
		
	    public List<PartyMeetingsVO> getAllSessionType(){
			List<PartyMeetingsVO> finalList=null;
			try{
				List<Object[]> objectList=sessionTypeDAO.getAllSessionType();
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] objcts: objectList){
						PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
						partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));
						partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[1]));
						finalList.add(partyMeetingVO);
					}
			    }
			}catch(Exception e){
				LOG.error("Exception Occured in getAllSessionType  method, Exception - ",e); 
			}
			return finalList;
		}
	    public List<PartyMeetingsVO> getPartyMeetingsTabUserNameByDistrict(){
			List<PartyMeetingsVO> finalList=null;
			try{
				List<Object[]> objectList=attendanceTabUserDAO.getAttendanceTabUserDetailes();
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] objcts: objectList){
						PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
						partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0])); // tab user Id
						partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[1])); // tab user first name
						partyMeetingVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(objcts[2]));// tab user last name
						partyMeetingVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objcts[3])); // tab user mobile number
						partyMeetingVO.setMeetingName(commonMethodsUtilService.getStringValueForObject(objcts[4]));  // tab userName
						
						finalList.add(partyMeetingVO);
					}
			    }
			}catch(Exception e){
				LOG.error("Exception Occured in getPartyMeetingsTabUserNameByDistrict  method, Exception - ",e); 
			}
			return finalList;
		}
	    
	    /**
		 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
		 * @Date 3rd july,2017
		 * @description :party meeting details and invitees and other detailes
		 * @return List of PartyMeetingsVO  contais locations and invetivees cadreId,name,desiganation
	 */
public List<PartyMeetingsVO> getPartyMeetingDeatilesForMeetingEditByMeetingId(Long meetingId){
			
			List<PartyMeetingsVO> finalList=null;
			 SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			try{
				List<IdAndNameVO> inviteeDeatilsVOS= null;
				//get party meeting details
				List<Object[]> objectList=partyMeetingDAO.getPartyMeetingDetailsByPartyMeetingId(meetingId);
				//get meeting invitee mebbershipsnumbers
				List<Object[]> inviteeObjs=partyMeetingInviteeDAO.getPartyMeetingInviteeDetaisByPartyMeetingId(meetingId);
				List<String> memberShipiNoList=null;
				//tdp cadreId set
				Set<Long> tdpCadreIdSet=null;
				if(inviteeObjs != null && inviteeObjs.size() > 0L){
					 tdpCadreIdSet=new HashSet<Long>();
					 memberShipiNoList=new ArrayList<String>();
					for(Object[] objcts: inviteeObjs){
						//tdp cadreId set
						tdpCadreIdSet.add(commonMethodsUtilService.getLongValueForObject(objcts[1]));
						memberShipiNoList.add(commonMethodsUtilService.getStringValueForObject(objcts[2]));
						//positions List
					}
				}  //get the invitee full details with membership numbers
				if(memberShipiNoList != null && memberShipiNoList.size() >0){
					 inviteeDeatilsVOS= getTdpCadreDetailsForInveetMeeting(memberShipiNoList);
				}
					if(objectList !=null && objectList.size() > 0){
						finalList=new ArrayList<PartyMeetingsVO>();
						List<PartyMeetingsVO> invitesList=null;
						for(Object[] objcts: objectList){
							PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
							partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));         // meeting id
							partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[1]));      //meeting name
							String startDate=commonMethodsUtilService.getStringValueForObject(objcts[2]);//.split("\\s");
                            String []endDate=commonMethodsUtilService.getStringValueForObject(objcts[3]).split("\\s");
							partyMeetingVO.setConductedDate(startDate); // start date
							partyMeetingVO.setUpdatedTime(endDate[0]+" "+endDate[1]);		 // end date   
							partyMeetingVO.setMeetingTypeId(commonMethodsUtilService.getLongValueForObject(objcts[4]));    //meeting typeId
							partyMeetingVO.setMandalTwnDivisionId(commonMethodsUtilService.getLongValueForObject(objcts[5])); //meeting levelId
							partyMeetingVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objcts[6]));       // district id
							partyMeetingVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objcts[7]));   // constituencyId
							partyMeetingVO.setTeshilId(commonMethodsUtilService.getLongValueForObject(objcts[8]));         //tehsilId
							partyMeetingVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(objcts[9]));     //villageId
							partyMeetingVO.setMeetingMainTypeId(commonMethodsUtilService.getLongValueForObject(objcts[10]));//meeting main type id
							partyMeetingVO.setStateName(commonMethodsUtilService.getStringValueForObject(objcts[11]));//State NAME
							partyMeetingVO.setIsCondacted(commonMethodsUtilService.getStringValueForObject(objcts[12]));
							Date currrentdate=sdf2.parse(dateUtilService.getCurrentDateInStringFormat());
							Long daysCount=dateUtilService.noOfDayBetweenDates(endDate[0],dateUtilService.getDateInStringFormatByDate(currrentdate,"yyyy-MM-dd"));
							if(daysCount <=1){
								partyMeetingVO.setFlage("true");// set flage for hide the edit  invitees text area and file uploads 
							}
							if(inviteeDeatilsVOS !=null && inviteeDeatilsVOS.size() >0){
								partyMeetingVO.setInvetteList(inviteeDeatilsVOS);//invite details set to vo for UI building
							}
							finalList.add(partyMeetingVO);
						}
					}

			}catch(Exception e){
				LOG.error("Exception Occured in getPartyMeetingDeatilesForMeetingEditByMeetingId  method, Exception - ",e); 
			}
			return finalList;
			
		}
		public List<PartyMeetingsVO>  getPartyMeetingSession(Long partyMeetingSessionId){
			
			List<PartyMeetingsVO> finalList=null;
			try{
				List<Object[]> objectList=sessionTypeDAO.getPartyMeetingSession(partyMeetingSessionId);
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] obj: objectList){
						PartyMeetingsVO vo=new PartyMeetingsVO();
						vo.setStartTime((Time)obj[0]);
						vo.setEndTime((Time)obj[1]);
						vo.setLateTime((Time)obj[2]);
						finalList.add(vo);
					}
				}
			}catch(Exception e){
				LOG.error("Exception Occured in getPartyMeetingSession  method, Exception - ",e); 
			}
			return finalList;
			
		
		}
		
		
		public List<String> getInvitiesDetails(final File file) {
		    final File uploadedFile=file;
		       String status ="";
		          Long rowCount = 1l;
				   List<String> membershipIdList=new ArrayList<String>();
		           try {
		                FileInputStream fstream = new FileInputStream(uploadedFile);
		                DataInputStream in = new DataInputStream(fstream);
		                BufferedReader br = new BufferedReader(new InputStreamReader(in));
		                String strLineStr;
		                 
		               boolean excelValid=true;
		               
		                while (br != null && (strLineStr = br.readLine()) != null) {
		                  String[] cadreIdList = strLineStr.split(",");
		                  
		                  if(cadreIdList != null && cadreIdList.length>0){
		                	  if(rowCount == 1l){
		                		  if(cadreIdList.length == 4 && cadreIdList[0].equalsIgnoreCase("S.N0") && cadreIdList[1].equalsIgnoreCase("Membership ID") && cadreIdList[2].equalsIgnoreCase("Name") && cadreIdList[3].equalsIgnoreCase("Mobile"))
		                		  {
		                			  excelValid=true;
		                		  }
		                		  else{
		                			  excelValid=false;
		                			  membershipIdList.add("Please upload Excel in given Format");
		                			  br = null;
		                		  }
		                	  }
		                	  if(excelValid){
		                      if(rowCount>1l){
		                        int column2=1;
		                    for (int i = 0; i < cadreIdList.length; i++) {
		                      if(column2 == i){
		                    	  String membershipId="";
		                    	  if(cadreIdList[column2].isEmpty() || cadreIdList[column2].contains(" ")){
		                        	  membershipIdList.clear();
			                          membershipIdList.add("Please Enter Membership Ids");
			                          br = null;
			                     }
		                        if(!StringUtils.isNumeric(cadreIdList[column2])){
		                        	  membershipIdList.clear();
			                          membershipIdList.add("Please Enter Membership Ids In Numbers Only");
			                          br = null;
			                     }  
		                      if(!cadreIdList[column2].isEmpty() && !cadreIdList[column2].contains(" ") && StringUtils.isNumeric(cadreIdList[column2])){
			                        if(cadreIdList[i].length()==7){
				                          membershipId+="0"+cadreIdList[i];
				                          membershipIdList.add(membershipId);
				                        }
				                        else{
				                          membershipId=cadreIdList[i];
				                          membershipIdList.add(membershipId);
				                        }
			                      }
		                      }
		                    }
		                  }
		               }
		                      rowCount++;  
		                  
		                  }
		                }
		              
		            
		              }catch(Exception e) {
		                e.printStackTrace();
		              }  
		      return membershipIdList;
		  }
		
		/**
		 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
		 * @Date 3rd july,2017
		 * @description :party meeting details and invitees and other detailes
		 * @return: List of IdAndNameVO  contais tdpCadreid,name,memberShipId,desiganation,mobilNumber 
		            and members Not avaible List
	 */
		public List<IdAndNameVO> getTdpCadreDetailsForInveetMeeting(List<String> inputMemberShipIds){
			Set<String> removeDuplicates=new HashSet<String>(inputMemberShipIds); 
			List<String> memberShipIds=new ArrayList<String>(removeDuplicates);
			List<IdAndNameVO> finalList=null;
			List<String> memberShipNotAvailbleList=null;
			try{
				List<Object[]> cadreDesiganatinsObj=null;
				List<Object[]> tdpCadreObjs=tdpCadreDAO.getTdpCadreDatailsForMeetings(memberShipIds);
				Map<Long,List<String>> cadreIdAndDesigination=null;
				Set<Long> cadreIdSet=null;
				Map<Long,String> cadreIdAndMemberShipId=null;
				Map<Long,String> cadreIdAndname=null;
				Map<Long,String> cadreIdAndImage=null;
				Map<Long,String> cadreIdAndMobile=null;
				Map<Long,String> cadreIdAndMemberShip=null;
				Set<String> memberShipNoSet=null;
				
				if(tdpCadreObjs != null && tdpCadreObjs.size() > 0){
					cadreIdSet=new HashSet<Long>();
					memberShipNoSet=new HashSet<String>();
					
					cadreIdAndImage=new HashMap<Long,String>();
					cadreIdAndname=new HashMap<Long,String>();
					cadreIdAndMobile=new HashMap<Long,String>();
					cadreIdAndMemberShip=new HashMap<Long,String>();
					cadreIdAndMemberShipId=new HashMap<Long,String>();
					for(Object[] objcts:tdpCadreObjs  ){
						memberShipNoSet.add(commonMethodsUtilService.getStringValueForObject(objcts[0]).trim());
						cadreIdSet.add(commonMethodsUtilService.getLongValueForObject(objcts[1]));
						cadreIdAndname.put(commonMethodsUtilService.getLongValueForObject(objcts[1]), commonMethodsUtilService.getStringValueForObject(objcts[2]));
						cadreIdAndMemberShipId.put(commonMethodsUtilService.getLongValueForObject(objcts[1]), commonMethodsUtilService.getStringValueForObject(objcts[0]));
						cadreIdAndImage.put(commonMethodsUtilService.getLongValueForObject(objcts[1]), commonMethodsUtilService.getStringValueForObject(objcts[4]));
						cadreIdAndMobile.put(commonMethodsUtilService.getLongValueForObject(objcts[1]), commonMethodsUtilService.getStringValueForObject(objcts[3]));
						cadreIdAndMemberShip.put(commonMethodsUtilService.getLongValueForObject(objcts[1]), commonMethodsUtilService.getStringValueForObject(objcts[0]));
					}
					 cadreDesiganatinsObj=tdpCadreCandidateDAO.geTdpCadreCandidateDesiganationsByCadreaIds(cadreIdSet);
				}
				if(cadreDesiganatinsObj != null && cadreDesiganatinsObj.size() >0){
					cadreIdAndDesigination=new HashMap<Long,List<String>>();
					List<String> desilist=null;
					for(Object[] objcts: cadreDesiganatinsObj){
						desilist=cadreIdAndDesigination.get(objcts[0]);
						if(desilist != null){
							cadreIdAndDesigination.get(commonMethodsUtilService.getLongValueForObject(objcts[0])).add(commonMethodsUtilService.getStringValueForObject(objcts[2]));
						}else{
							desilist=new ArrayList<String>();
							desilist.add(commonMethodsUtilService.getStringValueForObject(objcts[2]));
							cadreIdAndDesigination.put(commonMethodsUtilService.getLongValueForObject(objcts[0]),desilist);
						}
					}
				}
				if(tdpCadreObjs != null && tdpCadreObjs.size() > 0){
					finalList=new ArrayList<IdAndNameVO>();
					for(Long id : cadreIdSet){
						IdAndNameVO idAndNameVO=new IdAndNameVO();
						idAndNameVO.setId(id);			//tdpcaderId
						idAndNameVO.setMembershipNo(cadreIdAndMemberShipId.get(id));	//meberShip numbers
						idAndNameVO.setName(cadreIdAndname.get(id));		//name
						idAndNameVO.setImagePathStr(cadreIdAndImage.get(id));//image
						idAndNameVO.setMobileNumber(cadreIdAndMobile.get(id)); //MOBILE NO..
						idAndNameVO.setMembershipNo(cadreIdAndMemberShip.get(id));//memberShipNo
						String positions="";
						if(cadreIdAndDesigination !=null && cadreIdAndDesigination.size() >0){
							if(cadreIdAndDesigination.get(id) != null){
								for(String posi : cadreIdAndDesigination.get(id) ){
									if(positions !=null && positions.length() > 0){
										positions=positions+","+posi;
									}else{
										positions=posi;
									}
								}
							}
						}
						
						idAndNameVO.setPartyName(positions);//position name
						finalList.add(idAndNameVO);
					}
				}
					// to check the memberShipIds exist or not
				if(memberShipNoSet !=null && memberShipNoSet.size() >0){
					 memberShipNotAvailbleList=new ArrayList<String>();
						for(String memberShipStr :memberShipIds){
							if(!memberShipNoSet.contains(memberShipStr)){
								memberShipNotAvailbleList.add(memberShipStr);// membership not Availble List
							}
						}
				}else{
					 memberShipNotAvailbleList=new ArrayList<String>();
					 memberShipNotAvailbleList.addAll(memberShipIds);
				}
				if(finalList !=null && finalList.size() > 0 &&memberShipNotAvailbleList != null && memberShipNotAvailbleList.size() > 0){
					finalList.get(0).setEnrollmentYears(memberShipNotAvailbleList);
				}else if(finalList == null && memberShipNotAvailbleList != null && memberShipNotAvailbleList.size() > 0){
					finalList=new ArrayList<IdAndNameVO>();
					IdAndNameVO idAndNameVO=new IdAndNameVO();
					idAndNameVO.setEnrollmentYears(memberShipNotAvailbleList);
					finalList.add(idAndNameVO);
				}
			}catch(Exception e){
				LOG.error("Exception Occured in getTdpCadreDetailsForInveetMeeting  method, Exception - ",e); 
			}
			return finalList;
		}
		
		  public PartyMeeting saveMeetingDetails(final PartyMeetingVO inputvo){
		        PartyMeeting result = (PartyMeeting) transactionTemplate.execute(new TransactionCallback() {
		          public Object doInTransaction(TransactionStatus status) {
		            PartyMeeting retunedObj=new PartyMeeting();
		            DateFormat formatter = new SimpleDateFormat("HH:mm");
		            try
		               {
		                   DateUtilService date = new DateUtilService();
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//dd/MM/yyyy
		  
		                if(inputvo.getPartyMeetingId() == null){
		                  PartyMeeting partymeeting=new PartyMeeting();
		                  partymeeting.setMeetingName(inputvo.getName());
		                  partymeeting.setPartyMeetingTypeId(inputvo.getPartyMeetingTypeId());
		                  partymeeting.setPartyMeetingLevelId(inputvo.getMeetingLevelId());
		                  partymeeting.setLocationValue(inputvo.getLocationValue());
		                  partymeeting.setPartyMeetingOccurrenceId(1L);
		                  partymeeting.setStartDate((new SimpleDateFormat("yyyy-MM-dd").parse(inputvo.getStartDateStr())));
	                      partymeeting.setEndDate((new SimpleDateFormat("yyyy-MM-dd").parse(inputvo.getEndDateStr())));
		                  
		                    UserAddress userAddress=new UserAddress();
		                    if(inputvo.getMeetingLevelId() == 1l){
			                    userAddress.setState(stateDAO.get(inputvo.getStateId()));
			                    }
		                    if(inputvo.getMeetingLevelId() == 2l){ 
		                    userAddress.setState(stateDAO.get(inputvo.getStateId()));
		                    userAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
		                    }
		                    if(inputvo.getMeetingLevelId() == 3l){
		                    	 userAddress.setState(stateDAO.get(inputvo.getStateId()));
				                 userAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
		                    	 userAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
			                    }
		                    if(inputvo.getMeetingLevelId() == 4l || inputvo.getMeetingLevelId() == 5l ||inputvo.getMeetingLevelId() == 6l){
		                    	 userAddress.setState(stateDAO.get(inputvo.getStateId()));
				                 userAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
				                 userAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
				                 String teshilId=inputvo.getTehsilId().toString();
				                 if(teshilId.startsWith("2")){
				                	 userAddress.setTehsil(tehsilDAO.get(Long.valueOf(teshilId.substring(1))));
				                 }
				                 if(teshilId.startsWith("1")){
				                	 userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(teshilId.substring(1))));
				                 }		                    	 
			                    }
		                    if(inputvo.getMeetingLevelId() == 7l ||inputvo.getMeetingLevelId() ==8l){
		                    	 userAddress.setState(stateDAO.get(inputvo.getStateId()));
				                 userAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
		                    	 userAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
		                    	// userAddress.setTehsil(tehsilDAO.get(inputvo.getTehsilId()));
		                    	 //userAddress.setPanchayatId(inputvo.getVillageId());
		                    	 String teshilId=inputvo.getTehsilId().toString();
		                    	 if(teshilId.startsWith("2")){
		                    		 userAddress.setTehsil(tehsilDAO.get(Long.valueOf(teshilId.substring(1))));
				                	 userAddress.setPanchayatId(inputvo.getVillageId());
				                 }
				                 if(teshilId.startsWith("1")){
				                	 userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(teshilId.substring(1)))); 
				                	 userAddress.setWard(constituencyDAO.get(inputvo.getVillageId()));
				                 }	
			                    }
		             
		                  UserAddress returnedUserAddress=userAddressDAO.save(userAddress);
		                  if(returnedUserAddress!= null && returnedUserAddress.getUserAddressId() != null){
		                    partymeeting.setMeetingAddressId(returnedUserAddress.getUserAddressId());
		                  }
		                  partymeeting.setInsertedById(inputvo.getInsertedById());
		                  
		                  partymeeting.setIsActive("Y");  
		                  partymeeting.setAttendanceEnrolmentYear("ALL");
		                  partymeeting.setInsertedTime(date.getCurrentDateAndTime());
		                  
		                  partymeeting.setLocationValue(getLocationLevelId(inputvo));
		                  retunedObj=partyMeetingDAO.save(partymeeting);
						  
						
						  
						  List<Long> sessionIdList=inputvo.getSessionTypeId();
						  List<String> startTimeList=inputvo.getStartTimeList();
						  List<String> endTimeList=inputvo.getEndTimeList();
						  List<String> lateTimeList=inputvo.getLateTimeList();
						  
						  
						  
						  if(sessionIdList.size()>0){
						  for(int i=0;i<sessionIdList.size();i++){
							  PartyMeetingSession partyMeetingSession=new PartyMeetingSession();
							  partyMeetingSession.setSessionTypeId(sessionIdList.get(i));
							  java.sql.Time timeValue = new java.sql.Time(formatter.parse(startTimeList.get(i)).getTime());
							  partyMeetingSession.setStartTime(timeValue);
							  java.sql.Time endTime = new java.sql.Time(formatter.parse(endTimeList.get(i)).getTime());
							  partyMeetingSession.setEndTime(endTime);
							  java.sql.Time lateTime = new java.sql.Time(formatter.parse(lateTimeList.get(i)).getTime());
							  partyMeetingSession.setLateTime(lateTime);  
							  partyMeetingSession.setPartyMeetingId(retunedObj.getPartyMeetingId());  
							  partyMeetingSession.setIsDeleted("N");
							  partyMeetingSession.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							  partyMeetingSession.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							  partyMeetingSessionDAO.save(partyMeetingSession);
						  }
						  }
						
						  
						   List<Long> tdpCadreIdsList=inputvo.getTdpCadreIds();		  
						   if(tdpCadreIdsList !=null && tdpCadreIdsList.size()>0){
		                    for(int i=0;i<tdpCadreIdsList.size();i++){
		                      PartyMeetingInvitee newpartyMeetingInvitee=new PartyMeetingInvitee();
		                      newpartyMeetingInvitee.setPartyMeetingId(retunedObj.getPartyMeetingId());
		                      newpartyMeetingInvitee.setUpdatedById(inputvo.getInsertedById());
		                      newpartyMeetingInvitee.setTdpCadreId((Long.valueOf(tdpCadreIdsList.get(i))));
		                      newpartyMeetingInvitee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		                        partyMeetingInviteeDAO.save(newpartyMeetingInvitee);
		                      
		                    }
		                  
		               }
						   
						   
						   List<Long> attendedList=inputvo.getAttendedList();
						   if(attendedList !=null && attendedList.size()>0){
			                    for(int i=0;i<attendedList.size();i++){
						   PartyMeetingAttendanceTabUser partyMeetingAttendanceTabUser=new PartyMeetingAttendanceTabUser();
						   partyMeetingAttendanceTabUser.setIsDeleted("N");
						   partyMeetingAttendanceTabUser.setPartyMeetingId(retunedObj.getPartyMeetingId());
						   partyMeetingAttendanceTabUser.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						   partyMeetingAttendanceTabUser.setAttendanceTabUserId((Long.valueOf(attendedList.get(i))));
						   partyMeetingAttendanceTabUser.setInsertedBy(userDAO.get(1L));
						   partyMeetingAttendanceTabUserDAO.save(partyMeetingAttendanceTabUser);
						      
			                    }
			                  
			               }
							   
						  
		                  //retunedObj=new PartyMeeting();
		                  //retunedObj.setPartyMeetingId(10l);
						  return retunedObj;
		                }else{
		                	
		                  PartyMeeting meeting = partyMeetingDAO.get(inputvo.getPartyMeetingId());
		                  meeting.setMeetingName(inputvo.getName());
		                  meeting.setPartyMeetingTypeId(inputvo.getPartyMeetingTypeId());
		                  meeting.setPartyMeetingLevelId(inputvo.getMeetingLevelId());
		                  meeting.setLocationValue(inputvo.getLocationValue());
		                  meeting.setPartyMeetingOccurrenceId(1L);
		                  meeting.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(inputvo.getStartDateStr()));
	                     // meeting.setEndDate(inputvo.getEndDate());
		                  meeting.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(inputvo.getEndDateStr()));
		                  meeting.setInsertedById(inputvo.getInsertedById());
		                  meeting.setIsActive("Y");
		                  meeting.setAttendanceEnrolmentYear("ALL");
		                  meeting.setUpdatedTime(date.getCurrentDateAndTime());
		                  meeting.setLocationValue(getLocationLevelId(inputvo));
		                  Long meetingAddressId=meeting.getMeetingAddressId();
		                  if(meetingAddressId!=null){
		                    UserAddress changedUserAddress=userAddressDAO.get(meetingAddressId);
		                    if(changedUserAddress!=null){
		                      // UserAddress changedUserAddress=new UserAddress();
		                    	if(inputvo.getMeetingLevelId() == 1l){
		                    		changedUserAddress.setState(stateDAO.get(inputvo.getStateId()));
				                    }
			                    if(inputvo.getMeetingLevelId() == 2l){ 
			                    	changedUserAddress.setState(stateDAO.get(inputvo.getStateId()));
			                    	changedUserAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
			                    }
			                    if(inputvo.getMeetingLevelId() == 3l){
			                    	changedUserAddress.setState(stateDAO.get(inputvo.getStateId()));
			                    	changedUserAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
			                    	changedUserAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
				                    }
			                    if(inputvo.getMeetingLevelId() == 4l || inputvo.getMeetingLevelId() == 5l ||inputvo.getMeetingLevelId() == 6l){
			                    	changedUserAddress.setState(stateDAO.get(inputvo.getStateId()));
			                    	changedUserAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
			                    	changedUserAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
			                    	 String teshilId=inputvo.getTehsilId().toString();
					                 if(teshilId.startsWith("2")){
					                	 changedUserAddress.setTehsil(tehsilDAO.get(Long.valueOf(teshilId.substring(1))));
					                	 changedUserAddress.setPanchayatId(null);
					                 }
					                 if(teshilId.startsWith("1")){
					                	 changedUserAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(teshilId.substring(1))));
					                	 changedUserAddress.setWard(null);
					                 }		
				                    }
			                    if(inputvo.getMeetingLevelId() == 7l ||inputvo.getMeetingLevelId() ==8l){
			                    	changedUserAddress.setState(stateDAO.get(inputvo.getStateId()));
			                    	 changedUserAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
			                    	 changedUserAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
			                    	 String teshilId=inputvo.getTehsilId().toString();
			                    	 if(teshilId.startsWith("2")){
			                    		 changedUserAddress.setTehsil(tehsilDAO.get(Long.valueOf(teshilId.substring(1))));
			                    		 changedUserAddress.setPanchayatId(inputvo.getVillageId());
					                 }
					                 if(teshilId.startsWith("1")){
					                	 changedUserAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(teshilId.substring(1)))); 
					                	 changedUserAddress.setWard(constituencyDAO.get(inputvo.getVillageId()));
					                 }	
				                    }
		                         userAddressDAO.save(changedUserAddress);
		                    }

		}
		                  //retunedObj=new PartyMeeting();
		                  //retunedObj.setPartyMeetingId(10l);
		                  retunedObj=partyMeetingDAO.save(meeting);
						  
		                  List<Long> sessionIdList=inputvo.getSessionTypeId();
						  List<String> startTimeList=inputvo.getStartTimeList();
						  List<String> endTimeList=inputvo.getEndTimeList();
						  List<String> lateTimeList=inputvo.getLateTimeList();
						  
						  List<Long> partyMeetingSessionIds=new ArrayList<Long>();
						  
						  if(sessionIdList.size()>0){
							  for(int i=0;i<sessionIdList.size();i++){
								  if(sessionIdList.get(i)>4l){
									  PartyMeetingSession dbPartyMeetingSession=partyMeetingSessionDAO.get(sessionIdList.get(i));
									  partyMeetingSessionIds.add(dbPartyMeetingSession.getPartyMeetingSessionId());
									  java.sql.Time timeValue = new java.sql.Time(formatter.parse(startTimeList.get(i)).getTime());
									  dbPartyMeetingSession.setStartTime(timeValue);
									  java.sql.Time endTime = new java.sql.Time(formatter.parse(endTimeList.get(i)).getTime());
									  dbPartyMeetingSession.setEndTime(endTime);
									  java.sql.Time lateTime = new java.sql.Time(formatter.parse(lateTimeList.get(i)).getTime());
									  dbPartyMeetingSession.setLateTime(lateTime);  
									  dbPartyMeetingSession.setPartyMeetingId(retunedObj.getPartyMeetingId());  
									  dbPartyMeetingSession.setIsDeleted("N");
									  //dbPartyMeetingSession.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									  dbPartyMeetingSession.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									  partyMeetingSessionDAO.save(dbPartyMeetingSession);
								  }
								  else{
								  PartyMeetingSession partyMeetingSession=new PartyMeetingSession();
								  partyMeetingSession.setSessionTypeId(sessionIdList.get(i));
								  java.sql.Time timeValue = new java.sql.Time(formatter.parse(startTimeList.get(i)).getTime());
								  partyMeetingSession.setStartTime(timeValue);
								  java.sql.Time endTime = new java.sql.Time(formatter.parse(endTimeList.get(i)).getTime());
								  partyMeetingSession.setEndTime(endTime);
								  java.sql.Time lateTime = new java.sql.Time(formatter.parse(lateTimeList.get(i)).getTime());
								  partyMeetingSession.setLateTime(lateTime);  
								  partyMeetingSession.setPartyMeetingId(retunedObj.getPartyMeetingId()); 
								  partyMeetingSession.setIsDeleted("N");
								  partyMeetingSession.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								  partyMeetingSession.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								  PartyMeetingSession dbPartyMeetingSessionId=partyMeetingSessionDAO.save(partyMeetingSession);
								  partyMeetingSessionIds.add(dbPartyMeetingSessionId.getPartyMeetingSessionId());
								  }
							  }
						  }
						  
						  
						  if(inputvo.getTdpCadreIds() !=null && inputvo.getTdpCadreIds().size() >0){
						    List<Long> tdpCadreIdsList=inputvo.getTdpCadreIds();	
		                    List<Long> dBTdpCadreIdsList=partyMeetingInviteeDAO.getInvitedCadreIdsByPartyMeetingId(retunedObj.getPartyMeetingId());		
							
						   if(tdpCadreIdsList !=null && tdpCadreIdsList.size()>0){  //partyMeetingInviteeId
		                    for(int i=0;i<tdpCadreIdsList.size();i++){
							if(!dBTdpCadreIdsList.contains(tdpCadreIdsList.get(i))){
							  PartyMeetingInvitee newpartyMeetingInvitee=new PartyMeetingInvitee();
		                      newpartyMeetingInvitee.setPartyMeetingId(retunedObj.getPartyMeetingId());
		                      newpartyMeetingInvitee.setUpdatedById(inputvo.getInsertedById());
		                      newpartyMeetingInvitee.setTdpCadreId((Long.valueOf(tdpCadreIdsList.get(i))));
		                      newpartyMeetingInvitee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		                        partyMeetingInviteeDAO.save(newpartyMeetingInvitee);
							}
		                    }
						   }
						  }
						   if(inputvo.getCadreWithComments() !=null && inputvo.getCadreWithComments().size()>0){
							   int updatedCount=0;
							   List<String> cadreWithComments=inputvo.getCadreWithComments();
							   for(String cadreIdWithComment:cadreWithComments){
								   String idAndComment[]=cadreIdWithComment.split(",");
								   if(idAndComment.length >1){
								   Long cadreId=Long.valueOf(idAndComment[0].trim());
								   String comment=idAndComment[1].trim();
								   if(!comment.isEmpty() && !comment.equalsIgnoreCase("\\s+"))
								   updatedCount +=partyMeetingInviteeDAO.updateAbsenteeRemark(cadreId,inputvo.getPartyMeetingId(),comment);
								   }else{
									   updatedCount=0;
								   }
							   }
							}
						   
		              
						  if(inputvo.getAtrPoints() !=null && inputvo.getAtrPoints().size()>0){ //get cadre ids
							   List<String> cadreIds=inputvo.getAtrPoints();
							   for(String cadreId:cadreIds){
								   String[] cadreIdWithSession=cadreId.split(",");
								   Attendance attendance=new Attendance();
								   attendance.setTdpCadreId(Long.valueOf(cadreIdWithSession[0]));
								   attendance.setAttendedTime(new SimpleDateFormat("yyyy-MM-dd").parse(inputvo.getStartDateStr()));
								   attendance.setInsertedTime(date.getCurrentDateAndTime());
								   Attendance savedAttendance=attendanceDAO.save(attendance);
								   if(savedAttendance !=null){
									   Long attendanceId=savedAttendance.getAttendanceId();
									   if(attendanceId !=null){
										   PartyMeetingAttendance partyMeetingAttendance=new PartyMeetingAttendance();
										   partyMeetingAttendance.setPartyMeetingId(inputvo.getPartyMeetingId());
										   partyMeetingAttendance.setAttendance(attendanceDAO.get(attendanceId));
										   partyMeetingAttendance.setInsertedTime(date.getCurrentDateAndTime());
										   if(cadreIdWithSession.length >1){
										   partyMeetingAttendance.setPartyMeetingSessionId(Long.valueOf(cadreIdWithSession[1]));
										   }else{
											   partyMeetingAttendance.setPartyMeetingSessionId(null);
										   }
										   partyMeetingAttendanceDAO.save(partyMeetingAttendance);
									   }
								   }
							   }
							}
						  
						    
						  List<Long> attendedList=inputvo.getAttendedList();
						   List<Long> partyMeetingAttendanceTabUserId=new ArrayList<Long>();
						   List<Long> primaryAttendanceTabUserId=new ArrayList<Long>();
						   List<Long> dBpartyMeetingAttendanceTabUserId=new ArrayList<Long>();
						   if(attendedList !=null && attendedList.size()>0){
							  List<Object[]> partyMeetingAttendanceTabUserDetails= partyMeetingAttendanceTabUserDAO.getTabuserTotaldetailsFromMeetingId(inputvo.getPartyMeetingId(),attendedList);
							  if(partyMeetingAttendanceTabUserDetails != null){
							  for(Object[] obj:partyMeetingAttendanceTabUserDetails){
								  partyMeetingAttendanceTabUserId.add((Long) obj[2]);
								  //primaryAttendanceTabUserId.add((Long) obj[0]);
							  }
							  }
			                 for(int i=0;i<attendedList.size();i++){  
			                	 if(!partyMeetingAttendanceTabUserId.contains((Long.valueOf(attendedList.get(i))))){
			                		 PartyMeetingAttendanceTabUser partyMeetingAttendanceTabUser=new PartyMeetingAttendanceTabUser();
									   partyMeetingAttendanceTabUser.setIsDeleted("N");
									   partyMeetingAttendanceTabUser.setPartyMeetingId(retunedObj.getPartyMeetingId());
									   partyMeetingAttendanceTabUser.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									   partyMeetingAttendanceTabUser.setAttendanceTabUserId((Long.valueOf(attendedList.get(i))));
									   partyMeetingAttendanceTabUser.setInsertedBy(userDAO.get(1L));
									   partyMeetingAttendanceTabUserDAO.save(partyMeetingAttendanceTabUser);
			                	 }     
			                    }
			                 
			                 List<Object[]> dBPartyMeetingAttendanceTabUserDetails=partyMeetingAttendanceTabUserDAO.getPartyMeetingTabUserDetails(retunedObj.getPartyMeetingId());
			                 if(dBPartyMeetingAttendanceTabUserDetails != null){
								  for(Object[] obj:dBPartyMeetingAttendanceTabUserDetails){
									  dBpartyMeetingAttendanceTabUserId.add((Long) obj[0]);
									  primaryAttendanceTabUserId.add((Long) obj[5]);
								  }
								  }
			                 for(int i=0;i<primaryAttendanceTabUserId.size();i++){ 
                				 if(!attendedList.contains((Long.valueOf(dBpartyMeetingAttendanceTabUserId.get(i))))){
                					  PartyMeetingAttendanceTabUser partyMeetingAttendance=partyMeetingAttendanceTabUserDAO.get(primaryAttendanceTabUserId.get(i));
			                		  partyMeetingAttendance.setIsDeleted("Y");
			                		  partyMeetingAttendanceTabUserDAO.save(partyMeetingAttendance);
                				 }
                		     }
						   }
						   else{
							   List<Object[]> dBPartyMeetingAttendanceTabUserDetails=partyMeetingAttendanceTabUserDAO.getPartyMeetingTabUserDetails(retunedObj.getPartyMeetingId());
				                 if(dBPartyMeetingAttendanceTabUserDetails != null){
									  for(Object[] obj:dBPartyMeetingAttendanceTabUserDetails){
										  PartyMeetingAttendanceTabUser partyMeetingAttendance=partyMeetingAttendanceTabUserDAO.get((Long) obj[5]);
				                		  partyMeetingAttendance.setIsDeleted("Y");
				                		  partyMeetingAttendanceTabUserDAO.save(partyMeetingAttendance);
									  }
				                 }
						   }
		                }
		               }catch(Exception e){
		                 e.printStackTrace(); 
		               }
		          return retunedObj;
		          }
		        });
		      return result;
		      }
		  
		  public Long getLocationLevelId(final PartyMeetingVO inputvo){
		    	Long locationLevelId=null;
		    	Long meetingLevelId=inputvo.getMeetingLevelId();
		    	if(meetingLevelId==1l){
		    		locationLevelId=inputvo.getStateId();
		    	}
		    	 if(meetingLevelId==2l){
	            	locationLevelId=inputvo.getDistrictId();
		    	}
		    	 if(meetingLevelId==3l){
	        	   locationLevelId=inputvo.getConstituencyId();
	             }
		    	 if(meetingLevelId==4l || meetingLevelId == 5l || meetingLevelId == 6l){
	            	 locationLevelId=inputvo.getTehsilId();
	              }
		    	 if(meetingLevelId==7l || meetingLevelId==8l){
	            	locationLevelId=inputvo.getVillageId();
	             }
				return locationLevelId;
		    }
		  public List<PartyMeetingsVO> getPartyMeetingTabUserDetails(Long partyMeetingId){
			  List<PartyMeetingsVO> finalList=null;
			  try{
			  List<Object[]> objectList=partyMeetingAttendanceTabUserDAO.getPartyMeetingTabUserDetails(partyMeetingId);
			  
				if(objectList !=null && objectList.size() > 0){
					finalList=new ArrayList<PartyMeetingsVO>();
					for(Object[] obj: objectList){
						PartyMeetingsVO vo=new PartyMeetingsVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(obj[0]));//id
						vo.setName(commonMethodsUtilService.getStringValueForObject(obj[1]));//user name
						vo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(obj[2]));//first name
						vo.setTeshilName(commonMethodsUtilService.getStringValueForObject(obj[3]));//last name
						vo.setRemarks(commonMethodsUtilService.getStringValueForObject(obj[4]));//mobile number
						finalList.add(vo);
					}
					}
				}catch(Exception e){
	                 e.printStackTrace(); 
	               }
			  
			  return finalList;
		  }
		
		/*  public ResultStatus deletePartyMeetingDatails(Long meetingId){
		        ResultStatus  resultStatus = new ResultStatus();
		        try{
		          partyMeetingDAO.updatePartyMeetingDetails(meetingId);
		          List<Object[]> objectLis=partyMeetingSessionDAO.getSessionsDetailsByMeetingId(meetingId);
		          for(Object[] obj: objectLis){
		          partyMeetingSessionDAO.updatePartyMeetingSession(commonMethodsUtilService.getLongValueForObject(obj[1]));
		          }
		           resultStatus.setResultCode(0);
		           resultStatus.setMessage("Success");
		        }catch(Exception e){
		           resultStatus.setResultCode(0);
		           resultStatus.setMessage("Failed");
		          LOG.error("Exception Occured in deletePartyMeetingDatails  method, Exception - ",e); 

		        }
		        return resultStatus;
		      } */
		  
		  public ResultStatus deletePartyMeetingDatails(Long meetingId){
		        ResultStatus  resultStatus = new ResultStatus();
		        try{
		          partyMeetingDAO.updatePartyMeetingDetails(meetingId);
		          List<Object[]> objectLis=partyMeetingSessionDAO.getSessionsDetailsByMeetingId(meetingId);
		          for(Object[] obj: objectLis){
		          partyMeetingSessionDAO.updatePartyMeetingSession(commonMethodsUtilService.getLongValueForObject(obj[1]));
		          }
		          List<Object[]> objectList=partyMeetingAttendanceTabUserDAO.getPartyMeetingTabUserDetails(meetingId);
		          for(Object[] obj: objectLis){
		            partyMeetingAttendanceTabUserDAO.updatePartyMeetingAttendance(commonMethodsUtilService.getLongValueForObject(obj[0]));
		            }
		           resultStatus.setResultCode(0);
		           resultStatus.setMessage("Success");
		        }catch(Exception e){
		           resultStatus.setResultCode(0);
		           resultStatus.setMessage("Failed");
		          LOG.error("Exception Occured in deletePartyMeetingDatails  method, Exception - ",e); 

		        }
		        return resultStatus;
		      }
		  
		  public List<PartyMeetingVO> getSessionsDetailsByMeetingId(Long meetingId){
				List<PartyMeetingVO> finalSessioList=null;
				try{
					List<Object[]> sessionObjList=partyMeetingSessionDAO.getSessionsDetailsByMeetingId(meetingId);
					if(sessionObjList !=null && sessionObjList.size() > 0){
						finalSessioList=new ArrayList<PartyMeetingVO>();
						for(Object[] objcts: sessionObjList){
							PartyMeetingVO vo =new PartyMeetingVO();
							vo.setPartyMeetingId(commonMethodsUtilService.getLongValueForObject(objcts[0]));//meetingId
							vo.setId(commonMethodsUtilService.getLongValueForObject(objcts[1]));//meeetingsessionId
							vo.setLocationId(commonMethodsUtilService.getLongValueForObject(objcts[2]));// session TypeId
							vo.setName(commonMethodsUtilService.getStringValueForObject(objcts[6]));//meeting Type
							vo.setStartDateStr(commonMethodsUtilService.getStringValueForObject(objcts[3]));//start time
							vo.setEndDateStr(commonMethodsUtilService.getStringValueForObject(objcts[4]));//end time
							vo.setMeetingLevelStr(commonMethodsUtilService.getStringValueForObject(objcts[5]));//late time 
							finalSessioList.add(vo);
						}
					}
				}catch(Exception e){
					LOG.error("Exception Occured in getSessionsDetailsByMeetingId  method, Exception - ",e); 
				}
				return finalSessioList;
			}
		    /**
			 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
			 * @Date 28th June,2017
			 * @description to  get party meeting  attendance detailes
			 * @param attend and non attend list
			 * @return  idAndNamevo contaibns two list's attend list and not attend list 
		 */
		  /*
		  public IdAndNameVO getPartyMeetingInviteesDetailsAttendence(Long meetingId){
			  IdAndNameVO idAndNameVO=null;
		        List<String> invetiMeberShipList=null;
		        List<IdAndNameVO> notAttendedFinalList=null;
		        List<IdAndNameVO> attendNonInviteesFinalVOList=null;
		        List<String> notAttendList =null;
		        List<String> attendNonInviteesList=null;
		        List<String> attendedForInvetteeList=null;
		        //get the attendance list membershipi ids
		        List<String> attendedList=partyMeetingAttendanceDAO.getPartyMeetingInviteesDetailsAttendence(meetingId);
		      
		        //get party meeting invitees list by passin meeting id
		      	List<Object[]> inviteeObjs=partyMeetingInviteeDAO.getPartyMeetingInviteeDetaisByPartyMeetingId(meetingId);
		      	if(inviteeObjs !=null && inviteeObjs.size() >0){
		      		invetiMeberShipList=new ArrayList<String>();
			      	for(Object[] objcts: inviteeObjs){    //set the memership ids into list for comparing with attendlist then we get non-attendlist
			      		invetiMeberShipList.add(commonMethodsUtilService.getStringValueForObject(objcts[5]));
			      	}
		      	}
		         if(attendedList !=null && attendedList.size()>0 && invetiMeberShipList !=null && invetiMeberShipList.size() >0){
		        	 notAttendList=new ArrayList<String>();
		        	 for(String invetiMeber:invetiMeberShipList){
		        		 if(!attendedList.contains(invetiMeber)){// comparing attendlist and invettelist membership ids
		        			 notAttendList.add(invetiMeber);//non-attend list
		        		 }
		        	 }
		         }
		         if(attendedList !=null && attendedList.size()>0 && invetiMeberShipList !=null && invetiMeberShipList.size() >0){
		        	 attendNonInviteesList=new ArrayList<String>();
		        	 attendedForInvetteeList=new ArrayList<String>();
		        	 for(String attendMmber:attendedList){
		        		 if(invetiMeberShipList.contains(attendMmber)){
		        			 attendedForInvetteeList.add(attendMmber);
		        		 }else{
		        			 attendNonInviteesList.add(attendMmber);
		        		 }
		        	 }
		         }
		         //get deatails of attend people by passing membershipId
			        List<IdAndNameVO> attendfinalList=  getTdpCadreDetailsForInveetMeeting(attendedForInvetteeList);
		         //get non-attend persons details
		         notAttendedFinalList=  getTdpCadreDetailsForInveetMeeting(notAttendList);
		         attendNonInviteesFinalVOList=getTdpCadreDetailsForInveetMeeting(attendNonInviteesList);
		         //listes are set to VO Class to sent UI
		         if(notAttendedFinalList !=null || attendfinalList !=null){
			         idAndNameVO=new IdAndNameVO();
			         idAndNameVO.setNotAttendanceList(notAttendedFinalList);
			         idAndNameVO.setAttendanceList(attendfinalList);
			         idAndNameVO.setNonInviteeAttendancList(attendNonInviteesFinalVOList);
			         
		         }
		          return idAndNameVO;
		      }*/
		  
		  public IdAndNameVO getPartyMeetingInviteesDetailsAttendence(Long meetingId,Long sessionId){
		        IdAndNameVO idAndNameVO=null;
		        try{
			        List<String> notAttendedList =null;
			        List<IdAndNameVO> notAttendedFinalList=null;
			        List<IdAndNameVO> finalAttendanceList=null;
			        List<String> invitesList=null;
			        Map<String,String> memberShipIdAndCommentMap=null;
			            //get the attendance list membership ids
			            List<String> attendedList=partyMeetingAttendanceDAO.getPartyMeetingInviteesDetailsAttendence(meetingId,sessionId);
			            //get the invitess list membership ids
			            List<Object[]> invitesObjs=partyMeetingInviteeDAO.getPartyMeetingInvitteesMembershipNo(meetingId);
			            if(invitesObjs !=null && invitesObjs.size() > 0L){
			            	invitesList=new ArrayList<String>();
			            	memberShipIdAndCommentMap=new HashMap<String,String>();
			            	for(Object[] inviobj : invitesObjs){
			            		invitesList.add(commonMethodsUtilService.getStringValueForObject(inviobj[0]));
			            		memberShipIdAndCommentMap.put(commonMethodsUtilService.getStringValueForObject(inviobj[0]), commonMethodsUtilService.getStringValueForObject(inviobj[1]));
			            		
			            	}
			            }
			           
			            if(invitesList !=null && invitesList.size()>0){
			                   notAttendedList=new ArrayList<String>();
			                   for(String invitee:invitesList){
			                     if(!attendedList.contains(invitee)){// comparing attendlist and invettelist membership ids
			                       notAttendedList.add(invitee);//non-attend list
			                     }
			                   }
			                   if(attendedList !=null && attendedList.size()>0){
			                     finalAttendanceList= getTdpCadreDetailsForInveetMeeting(attendedList);
			                     if(finalAttendanceList !=null && finalAttendanceList.size() >0L){
				                    for(IdAndNameVO idVo : finalAttendanceList){
				                    	String memerShipNo=idVo.getMembershipNo();
				                    	idVo.setQuestion(memberShipIdAndCommentMap.get(memerShipNo));//comment for attendace
				                    }
			                     }
			                   }
			                   if(notAttendedList !=null && notAttendedList.size()>0){
			                   notAttendedFinalList= getTdpCadreDetailsForInveetMeeting(notAttendedList);
			                 }
			            if(notAttendedList !=null || attendedList !=null){
			                   idAndNameVO=new IdAndNameVO();
			                   idAndNameVO.setNotAttendanceList(notAttendedFinalList);
			                   idAndNameVO.setAttendanceList(finalAttendanceList);
			                   
			                 }
			            }
		      }catch(Exception e){
		        LOG.error("Exception Occured in getPartyMeetingInviteesDetailsAttendence  method, Exception - ",e); 
		      }
		      return idAndNameVO;
		    }
		  
		  /**
			 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
			 * @Date 28th June,2017
			 * @description delete party meeting Seesions
			 * @param partyMeetingSessionOd
			 * @return  result status
		 */
		  public ResultStatus deletePartyMeetingSession(Long meetingSessionId){
				ResultStatus  resultStatus = new ResultStatus();
				try{
					partyMeetingSessionDAO.updatePartyMeetingSession(meetingSessionId);
					 resultStatus.setResultCode(0);
					 resultStatus.setMessage("Success");
				}catch(Exception e){
					 resultStatus.setResultCode(0);
					 resultStatus.setMessage("Failed");
					LOG.error("Exception Occured in updatePartyMeetingSession  method, Exception - ",e); 

				}
				return resultStatus;
			}
		  
		  /**
			 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
			 * @Date 27-07-2017
			 * @description to get invitee Existing members and not existing members full deatils when
			 					we edit meeting invitees
			 * @param List of membership ids and meetingId
			 * @return  idAndNameVO
		 */
		  public IdAndNameVO getInviteeExistingAndNotExistingOfMeetingDetails(List<String> memberShipIds,Long meetingId){
			  IdAndNameVO  finalidAndNameVO=null;
			  List<IdAndNameVO> tdpCaderDetilsList=null;
			  Set<String> invitesList=null;
			  List<String> existingMembersList=null;
			  List<String> notExistingMembersList=null;
			  try{
				  //to get the invitee deatails
				  List<Object[]> invitesObjs=partyMeetingInviteeDAO.getPartyMeetingInvitteesMembershipNo(meetingId);
		            if(invitesObjs !=null && invitesObjs.size() > 0L){
		            	 invitesList=new HashSet<String>();
		            	for(Object[] inviobj : invitesObjs){//set membershipIds
		            		invitesList.add(commonMethodsUtilService.getStringValueForObject(inviobj[0]));
		    	        }
		            }
		            if(invitesList !=null && invitesList.size() >0 && memberShipIds !=null && memberShipIds.size() >0){
		            	existingMembersList=new ArrayList<String>();//comparing membershipIds  existing or Not
		            	notExistingMembersList=new ArrayList<String>();
		            	for(String memberStr: memberShipIds){
		            		if(!invitesList.contains(memberStr)){
		            			notExistingMembersList.add(memberStr);//not exiting membershipIds
		            		}else{
		            			existingMembersList.add(memberStr);	// exiting membershipIds
		            		}
		            	}
		            }
		          // get not exiting membershipIds details
		            if(notExistingMembersList !=null && notExistingMembersList.size() >0){
		            	tdpCaderDetilsList=getTdpCadreDetailsForInveetMeeting(notExistingMembersList);
		            }
		            if(invitesList==null){
		            	tdpCaderDetilsList=getTdpCadreDetailsForInveetMeeting(memberShipIds);
		            }
		            
		            //set the UI
		              finalidAndNameVO =new IdAndNameVO();
		            finalidAndNameVO.setAttendanceList(tdpCaderDetilsList);
		            finalidAndNameVO.setEnrollmentYears(existingMembersList);
			  }catch(Exception e){
					LOG.error("Exception Occured in getInviteeExistingAndNotExistingOfMeetingDetails  method, Exception - ",e); 

			  }
			  return finalidAndNameVO;
		  
		  }
		  
}
