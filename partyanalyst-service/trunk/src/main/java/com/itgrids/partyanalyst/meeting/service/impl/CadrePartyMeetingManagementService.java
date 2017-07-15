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

import com.itgrids.partyanalyst.dao.IAttendanceTabUserDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
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
import com.itgrids.partyanalyst.dao.hibernate.ConstituencyDAO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SessionVO;
import com.itgrids.partyanalyst.meeting.service.ICadrePartyMeetingManagementService;
import com.itgrids.partyanalyst.model.PartyMeeting;
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
	private  ResultStatus resultStatus;

	
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
	/*
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
	
	@Override  
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
			try{
			  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		      Date fromDate = null;
		      Date	toDate = null;
		      if(startDateStr != null && endDateStr != null){
		        fromDate = sdf.parse(startDateStr.trim());
		        toDate = sdf.parse(endDateStr.trim());
		      }			
		     totalMeetingCount= partyMeetingDAO.getCadrePartyMeetngDeatilsCount(fromDate,toDate,meetigLevelId);
			List<Object[]> objectList=partyMeetingDAO.getCadrePartyMeetngDeatils(fromDate,toDate,meetigLevelId, startIndex,maxIndex);
			if(objectList !=null && objectList.size() > 0){
				finalList=new ArrayList<PartyMeetingsVO>();
				for(Object[] objcts: objectList){
					PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
					partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));// meeting id
					partyMeetingVO.setMeetingName(commonMethodsUtilService.getStringValueForObject(objcts[1]));// meeting name
					partyMeetingVO.setRemarks(commonMethodsUtilService.getStringValueForObject(objcts[2]));//meeting level
					partyMeetingVO.setMeetingType(commonMethodsUtilService.getStringValueForObject(objcts[3]));//meeting type
					partyMeetingVO.setUpdatedTime(commonMethodsUtilService.getStringValueForObject(objcts[4]));// meeting date
					partyMeetingVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(objcts[5]));// district nam 
					partyMeetingVO.setConstituencyName(commonMethodsUtilService.getStringValueForObject(objcts[6]));//constincy name
					partyMeetingVO.setTeshilName(commonMethodsUtilService.getStringValueForObject(objcts[7]));// tesil name
					partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[8]));// village name.
					finalList.add(partyMeetingVO);
				}
				
			}
			if(totalMeetingCount != null ){
			finalList.get(0).setTotalCount(totalMeetingCount);
			}
			}catch(Exception e){
				LOG.error("Exception Occured in getCadrePartyMeetngDeatils  method, Exception - ",e); 
			}
			return finalList;
		}
	    public List<PartyMeetingsVO> getPartyMeetingLevels(){
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
			try{
				List<Object[]> objectList=partyMeetingDAO.getPartyMeetingDetailsByPartyMeetingId(meetingId);
				List<Object[]> inviteeObjs=partyMeetingInviteeDAO.getPartyMeetingInviteeDetaisByPartyMeetingId(meetingId);
				Map<Long,List<String>> caderIdPositionMap=null;
				Map<Long,String>tdpcadreIdName=null;
				List<String> positionList=null;
				Map<Long,String> tdpcadreIdAndMemberShipIdMap=null;
				Map<Long,String> tdpcadreIdAndPhoneNumber=null;
				Map<Long,Long> tdpCadreIdAndInviteeId=null;
				//tdp cadreId set
				Set<Long> tdpCadreIdSet=null;
				if(inviteeObjs != null && inviteeObjs.size() > 0L){
					tdpCadreIdAndInviteeId=new HashMap<Long,Long>();
					caderIdPositionMap=new HashMap<Long,List<String>>(0);
					tdpcadreIdName=new HashMap<Long,String>();
					tdpcadreIdAndMemberShipIdMap=new HashMap<Long,String>();
					tdpcadreIdAndPhoneNumber=new HashMap<Long,String>();
					 tdpCadreIdSet=new HashSet<Long>();
					for(Object[] objcts: inviteeObjs){
						//tdp cadreId set
						tdpCadreIdSet.add(commonMethodsUtilService.getLongValueForObject(objcts[1]));
						//positions List
						positionList=caderIdPositionMap.get(objcts[1]);
						tdpcadreIdName.put(commonMethodsUtilService.getLongValueForObject(objcts[1]),commonMethodsUtilService.getStringValueForObject(objcts[3]));
						tdpCadreIdAndInviteeId.put(commonMethodsUtilService.getLongValueForObject(objcts[1]),commonMethodsUtilService.getLongValueForObject(objcts[7]));
						tdpcadreIdAndMemberShipIdMap.put(commonMethodsUtilService.getLongValueForObject(objcts[1]),commonMethodsUtilService.getStringValueForObject(objcts[5]));
						tdpcadreIdAndPhoneNumber.put(commonMethodsUtilService.getLongValueForObject(objcts[1]),commonMethodsUtilService.getStringValueForObject(objcts[6]));
						if(positionList!=null){
							caderIdPositionMap.get(objcts[1]).add(commonMethodsUtilService.getStringValueForObject(objcts[4]));
						}else{
							positionList=new ArrayList<String>();
							positionList.add(commonMethodsUtilService.getStringValueForObject(objcts[4]));
							caderIdPositionMap.put(commonMethodsUtilService.getLongValueForObject(objcts[1]),positionList);
						}
					}
				}
					if(objectList !=null && objectList.size() > 0){
						finalList=new ArrayList<PartyMeetingsVO>();
						List<PartyMeetingsVO> invitesList=null;
						for(Object[] objcts: objectList){
							PartyMeetingsVO partyMeetingVO=new PartyMeetingsVO();
							partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(objcts[0]));         // meeting id
							partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(objcts[1]));      //meeting name
							
							//partyMeetingVO.setConductedDate(commonMethodsUtilService.getStringValueForObject(objcts[2])); // start date
							//partyMeetingVO.setUpdatedTime(commonMethodsUtilService.getStringValueForObject(objcts[3]));    // end date
							String startDate=commonMethodsUtilService.getStringValueForObject(objcts[2]);
                            String endDate=commonMethodsUtilService.getStringValueForObject(objcts[3]);
                            System.out.println(dateUtilService.getDateByStringAndFormat(startDate,"yyy-dd-mm"));
							partyMeetingVO.setStartDate(dateUtilService.getDateByStringAndFormat(startDate,"yyy-dd-mm")); // start date
							partyMeetingVO.setEndDate(dateUtilService.getDateByStringAndFormat(endDate,"yyy-dd-mm"));;    // end date   
							partyMeetingVO.setMeetingTypeId(commonMethodsUtilService.getLongValueForObject(objcts[4]));    //meeting typeId
							partyMeetingVO.setMandalTwnDivisionId(commonMethodsUtilService.getLongValueForObject(objcts[5])); //meeting levelId
							partyMeetingVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objcts[6]));       // district id
							partyMeetingVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objcts[7]));   // constituencyId
							partyMeetingVO.setTeshilId(commonMethodsUtilService.getLongValueForObject(objcts[8]));         //tehsilId
							partyMeetingVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(objcts[9]));     //villageId
							partyMeetingVO.setMeetingMainTypeId(commonMethodsUtilService.getLongValueForObject(objcts[10]));//meeting main type id
							partyMeetingVO.setStateName(commonMethodsUtilService.getStringValueForObject(objcts[11]));//State NAME
						if(inviteeObjs != null && inviteeObjs.size() >0L){
							 invitesList=new ArrayList<PartyMeetingsVO>();
							for(Long id: tdpCadreIdSet){
								PartyMeetingsVO inviteeVO=new PartyMeetingsVO();
								inviteeVO.setId(id);                       // cadreId id
								inviteeVO.setName(tdpcadreIdName.get(id)); //candidate name
								inviteeVO.setMandalTwnDivision(tdpcadreIdAndMemberShipIdMap.get(id));// memberShihId
								inviteeVO.setRemarks(tdpcadreIdAndPhoneNumber.get(id));// mobile number
								inviteeVO.setInviteeId(tdpCadreIdAndInviteeId.get(id));  // inviteeId  
								List<String> positionTypeList=null;
								positionTypeList=new ArrayList<String>();
								for(String type: caderIdPositionMap.get(id)){
									positionTypeList.add(type);   //to set desiganations
								}
								inviteeVO.setInviteeList(positionTypeList);
								invitesList.add(inviteeVO);
								}
						}
							partyMeetingVO.setSubList(invitesList);
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
		                 
		               
		                while ((strLineStr = br.readLine()) != null) {
		                  String[] cadreIdList = strLineStr.split(",");
		                  
		                  if(cadreIdList != null && cadreIdList.length>0){
		                      if(rowCount>1l){
		                        Long readNumberColums=0l;
		                    for (int i = 0; i < cadreIdList.length; i++) {
		                      if(StringUtils.isNumeric(cadreIdList[i])){
		                        readNumberColums++;
		                      }
		                      String membershipId="";
		                      if(readNumberColums==2l){
		                        if(cadreIdList[i].length()==7){
		                          membershipId+="0"+cadreIdList[i];
		                          membershipIdList.add(membershipId);
		                        }
		                        else{
		                          membershipId=cadreIdList[i];
		                          membershipIdList.add(membershipId);
		                        }
		                      }
		                        //break;
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
		public List<IdAndNameVO> getTdpCadreDetailsForInveetMeeting(List<String> memberShipIds){
			List<IdAndNameVO> finalList=null;
			List<String> memberShipNotAvailbleList=null;
			try{
				List<Object[]> tdpCadreObjs=tdpCadreCandidateDAO.geTdpCadreCandidateDetailsByMemberShipIds(memberShipIds);
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
					cadreIdAndDesigination=new HashMap<Long,List<String>>();
					List<String> desilist=null;
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
						desilist=cadreIdAndDesigination.get(objcts[1]);
						if(desilist != null){
							cadreIdAndDesigination.get(commonMethodsUtilService.getLongValueForObject(objcts[1])).add(commonMethodsUtilService.getStringValueForObject(objcts[5]));
						}else{
							desilist=new ArrayList<String>();
							desilist.add(commonMethodsUtilService.getStringValueForObject(objcts[5]));
							cadreIdAndDesigination.put(commonMethodsUtilService.getLongValueForObject(objcts[1]),desilist);
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
						for(String posi : cadreIdAndDesigination.get(id) ){
							if(positions !=null && positions.length() > 0){
								positions=positions+","+posi;
							}else{
								positions=posi;
							}
						}
						
						idAndNameVO.setPartyName(positions);//position name
					
						finalList.add(idAndNameVO);
					}
					// to cheack the memberShipIds exist or not
					 memberShipNotAvailbleList=new ArrayList<String>();
						for(String memberShipStr :memberShipIds){
							if(!memberShipNoSet.contains(memberShipStr)){
								memberShipNotAvailbleList.add(memberShipStr);// membership not Availble List
							}
						}
				}
				if(memberShipNotAvailbleList != null && memberShipNotAvailbleList.size() > 0){
					finalList.get(0).setEnrollmentYears(memberShipNotAvailbleList);
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
		                    	 userAddress.setTehsil(tehsilDAO.get(inputvo.getTehsilId()));
			                    }
		                    if(inputvo.getMeetingLevelId() == 7l ||inputvo.getMeetingLevelId() ==8l){
		                    	 userAddress.setState(stateDAO.get(inputvo.getStateId()));
				                 userAddress.setDistrict(districtDAO.get(inputvo.getDistrictId()));
		                    	 userAddress.setConstituency(constituencyDAO.get(inputvo.getConstituencyId()));
		                    	 userAddress.setTehsil(tehsilDAO.get(inputvo.getTehsilId()));
		                    	 userAddress.setPanchayatId(inputvo.getVillageId());
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
						   partyMeetingAttendanceTabUser.setIsDeleted("Y");
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
						  
						  
						  List<SessionVO> partyMeetingSessionVoList=inputvo.getSessionVOList();
						  
						  for(int i=0;i<partyMeetingSessionVoList.size();i++){
							  SessionVO sessionVO=partyMeetingSessionVoList.get(i);
						  PartyMeetingSession partyMeetingSession=partyMeetingSessionDAO.get(sessionVO.getPartyMeetingSessionId());
						  partyMeetingSession.setSessionTypeId(sessionVO.getSessionTypeId());
						 // partyMeetingSession.setStartTime(Time.valueOf(sessionVO.getStartTime()));
						  //partyMeetingSession.setEndTime(Time.valueOf(sessionVO.getEndTime()));
						 // partyMeetingSession.setLateTime(Time.valueOf(sessionVO.getLateTime()));  

						  partyMeetingSession.setPartyMeetingId(retunedObj.getPartyMeetingId());  
						  partyMeetingSessionDAO.save(partyMeetingSession);
						  }
						  
					
						  
						  
						  
						    List<Long> tdpCadreIdsList=inputvo.getTdpCadreIds();	
							
		                    List<Long> dBTdpCadreIdsList=partyMeetingInviteeDAO.getInvitedCadreIdsByPartyMeetingId(retunedObj.getPartyMeetingId());		

		                  /*   
		                  List<Long> cadreIdList=new ArrayList<Long>();
		                  
		                  for(int i=0;i<dBTdpCadreIdsList.size();i++){
		                    Object[] objArray=dBTdpCadreIdsList.get(i);
		                    cadreIdList.add((Long.valueOf(objArray[0])));
		                  }  */

							
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
		  public ResultStatus deletePartyMeetingDatails(Long meetingId){
				ResultStatus  resultStatus = new ResultStatus();
				try{
					partyMeetingDAO.updatePartyMeetingDetails(meetingId);
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
		  
}
