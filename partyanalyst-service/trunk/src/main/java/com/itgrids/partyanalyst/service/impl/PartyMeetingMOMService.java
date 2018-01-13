package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteHistoryDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteTrackingDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.MomDashbaordOverViewDtlsVO;
import com.itgrids.partyanalyst.dto.MomDetailsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMCreationDtlsvO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMDtlsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMPointsDtlsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.PartyMeetingDocument;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteHistory;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteTracking;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IPartyMeetingMOMService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingMOMService implements IPartyMeetingMOMService {

	
private static final Logger LOG = Logger.getLogger(PartyMeetingMOMService.class);

	private IPartyMeetingDAO partyMeetingDAO;
	private IPartyMeetingMinuteDAO partyMeetingMinuteDAO;
	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;
	private IPartyMeetingMinuteHistoryDAO partyMeetingMinuteHistoryDAO;
	private IPartyMeetingMinuteTrackingDAO partyMeetingMinuteTrackingDAO;
	private TransactionTemplate transactionTemplate;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	
	private IPanchayatDAO panchayatDAO;
	private IUserAddressDAO userAddressDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IRegionScopesDAO regionScopesDAO;
	
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	
	
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	public void setPartyMeetingMinuteDAO(IPartyMeetingMinuteDAO partyMeetingMinuteDAO) {
		this.partyMeetingMinuteDAO = partyMeetingMinuteDAO;
	}
	public void setPartyMeetingDocumentDAO(IPartyMeetingDocumentDAO partyMeetingDocumentDAO) {
		this.partyMeetingDocumentDAO = partyMeetingDocumentDAO;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setPartyMeetingMinuteHistoryDAO(IPartyMeetingMinuteHistoryDAO partyMeetingMinuteHistoryDAO) {
		this.partyMeetingMinuteHistoryDAO = partyMeetingMinuteHistoryDAO;
	}
	public void setPartyMeetingMinuteTrackingDAO(IPartyMeetingMinuteTrackingDAO partyMeetingMinuteTrackingDAO) {
		this.partyMeetingMinuteTrackingDAO = partyMeetingMinuteTrackingDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public void setDelimitationConstituencyMandalDAO(IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}
	/**
	   * @param Long userAccessLevel
	   * @param List<Long> accessValues
	   * @param String fromDate
	   * @param String toDate
	   * @return ToursBasicVO
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to get party meeting MOM details based on user access level. 
	   * @since 1-DECEMBER-2017
	   */
	public PartyMeetingMOMDtlsVO getPartyMeetingMOMDetails(Long userAccessLevel,List<Long> accessValues,String monthYear) {
		PartyMeetingMOMDtlsVO resultVO = null;
		try {
			Integer[] monthYearArr = getMontYear(monthYear);
			List<Object[]> meetingDtlsObjList = partyMeetingDAO.getPartyMeetingDetailsByUserAccessLevel(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1]);
			List<Object[]> momPointsObjList   = partyMeetingMinuteDAO.getPartyMeetingMomPointsByUserAccessLevel(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1],"summary",null);
			List<Object[]> momDocumentObjList = partyMeetingDocumentDAO.getPartyMeetingMomDocumentByUserAccessLevel(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1],"summary",null);
			Map<Long,Long> momPointsMap  = getRequiredData(momPointsObjList,"mom");
			Map<Long,Long> momDocumentMap  = getRequiredData(momDocumentObjList,"document");
			resultVO = getPartyMeetingDetails(meetingDtlsObjList, momPointsMap, momDocumentMap);
		 } catch (Exception e) {
			 LOG.error("Exception occurred at getPartyMeetingMOMDetails() of PartyMeetingMOMService class ",e);
		 }
		return resultVO;
	}
    public PartyMeetingMOMDtlsVO getPartyMeetingDetails(List<Object[]> objList,Map<Long,Long> momPointsMap,Map<Long,Long> momDocumentMap) {
    	PartyMeetingMOMDtlsVO finalVO = new PartyMeetingMOMDtlsVO(); 
    	try {
    		  if (objList != null && objList.size() > 0) {
    			  finalVO.setSubList(new ArrayList<PartyMeetingMOMDtlsVO>());
    			  PartyMeetingMOMDtlsVO overviewVO = new PartyMeetingMOMDtlsVO();
    			  for (Object[] param : objList) {
    				  PartyMeetingMOMDtlsVO meetingDtlsVO = new PartyMeetingMOMDtlsVO();
    				  meetingDtlsVO.setMeetingLevelId(commonMethodsUtilService.getLongValueForObject(param[0]));
    				  meetingDtlsVO.setMeetingLevel(commonMethodsUtilService.getStringValueForObject(param[1]));
    				  meetingDtlsVO.setMeetingId(commonMethodsUtilService.getLongValueForObject(param[2]));
    				  meetingDtlsVO.setMeetingName(commonMethodsUtilService.getStringValueForObject(param[3]));
    				  meetingDtlsVO.setIsConducted(commonMethodsUtilService.getStringValueForObject(param[4]));
    				  meetingDtlsVO.setStartDate(commonMethodsUtilService.getStringValueForObject(param[5]));
    				  meetingDtlsVO.setEndDate(commonMethodsUtilService.getStringValueForObject(param[6]));
    				  meetingDtlsVO.setMeetingTypeId(commonMethodsUtilService.getLongValueForObject(param[7]));
    				  meetingDtlsVO.setMeetingType(commonMethodsUtilService.getStringValueForObject(param[8]));
    				  meetingDtlsVO.setConductedDate(commonMethodsUtilService.getStringValueForObject(param[9]));
    				  meetingDtlsVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[10]));
    				  meetingDtlsVO.setLocation(commonMethodsUtilService.getStringValueForObject(param[11]));
    				  overviewVO.setTotalMeetingCount(overviewVO.getTotalMeetingCount()+1);
    				  if (momDocumentMap != null && momDocumentMap.get(meetingDtlsVO.getMeetingId()) != null) {
    					  meetingDtlsVO.setDocumentCount(Long.valueOf(momDocumentMap.get(meetingDtlsVO.getMeetingId())));
    					  overviewVO.setDocumentCount(overviewVO.getDocumentCount()+meetingDtlsVO.getDocumentCount());
    				  }
    				  if (momPointsMap != null && momPointsMap.get(meetingDtlsVO.getMeetingId()) != null) {
    					  meetingDtlsVO.setMomPointsCount(Long.valueOf(momPointsMap.get(meetingDtlsVO.getMeetingId())));
    					  overviewVO.setMomPointsCount(overviewVO.getMomPointsCount()+meetingDtlsVO.getMomPointsCount());
    				  }
    				  finalVO.getSubList().add(meetingDtlsVO); 
				 }
    			  finalVO.setOverviewDtls(overviewVO);
    		  } else {
    			  finalVO.setStatus("NO DATA AVAILABLE.");
    		  }
    		
    	 } catch (Exception e) {
    		 LOG.error("Exception occurred at getPartyMeetingDetails() of PartyMeetingMOMService class ",e); 
    	 }
    	return finalVO;
    }
    public Map<Long, Long> getRequiredData(List<Object[]> objList, String type) {
		Map<Long, Long> map = new HashMap<Long, Long>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
						map.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occurred at getRequiredData() of PartyMeetingMOMService class ",e);
		}
		return map;
	}
    /**
	   * @param Long userAccessLevel
	   * @param List<Long> accessValues
	   * @param String fromDate
	   * @param String toDate
	   * @return ToursBasicVO
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to get party meeting MOM document and mom points details by meeting id. 
	   * @since 1-DECEMBER-2017
	   */
	public PartyMeetingMOMPointsDtlsVO getPartyMeetingMOMPointsDocumentDetails(Long userAccessLevel,List<Long> accessValues,String monthYear,Long parytMeetingId) {
		PartyMeetingMOMPointsDtlsVO resultVO = new PartyMeetingMOMPointsDtlsVO();
		try {
			Integer[] monthYearArr = getMontYear(monthYear);
			List<Object[]> momPointsObjList   = partyMeetingMinuteDAO.getPartyMeetingMomPointsByUserAccessLevel(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1],"details",parytMeetingId);
			List<Object[]> momDocumentObjList = partyMeetingDocumentDAO.getPartyMeetingMomDocumentByUserAccessLevel(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1],"details",parytMeetingId);
			Map<Long,List<PartyMeetingMOMPointsDtlsVO>> momPointsMap  = getPartyMeetingMomDetails(momPointsObjList,"mom");
			Map<Long,List<PartyMeetingMOMPointsDtlsVO>> momDocumentMap  = getPartyMeetingMomDetails(momDocumentObjList,"document");
		    resultVO.setDocumentList(momDocumentMap.get(parytMeetingId));
		    resultVO.setMomPointsList(momPointsMap.get(parytMeetingId));
		 } catch (Exception e) {
			 LOG.error("Exception occurred at getPartyMeetingMOMPointsDocumentDetails() of PartyMeetingMOMService class ",e);
		 }
		return resultVO;
	}
	public Map<Long, List<PartyMeetingMOMPointsDtlsVO>> getPartyMeetingMomDetails(List<Object[]> objList, String type) {
		Map<Long, List<PartyMeetingMOMPointsDtlsVO>> map = new HashMap<Long, List<PartyMeetingMOMPointsDtlsVO>>(0);
		try {
			if (objList != null && objList.size() > 0) {
				for (Object[] param : objList) {
					List<PartyMeetingMOMPointsDtlsVO> list = map.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if (list == null) {
						list = new ArrayList<PartyMeetingMOMPointsDtlsVO>();
						map.put(commonMethodsUtilService.getLongValueForObject(param[0]), list);
					}
					PartyMeetingMOMPointsDtlsVO vo = new PartyMeetingMOMPointsDtlsVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					if (type.equalsIgnoreCase("mom")) {
						vo.setMomPoints(commonMethodsUtilService.getStringValueForObject(param[2]));
					} else if (type.equalsIgnoreCase("document")) {
						vo.setFilePath(commonMethodsUtilService.getStringValueForObject(param[2]));
					}
					list.add(vo);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occurred at getPartyMeetingMomDetails() of PartyMeetingMOMService class ",e);
		}
		return map;
	}
	/**
	   * @param Long meetingId
	   * @param Long loginUserId
	   * @param String upDateType
	   * @param String updatedValue
	   * @return String
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used update required field by updateType.. 
	   * @since 1-DECEMBER-2017
	   */
	public String updateMOMMeetingDetails(Long meetingId, String conductedDate,String isConducted,String remarks,Long loginUserId) {
		String status = "";
		try {
				Integer updatedCount = 0;
				Date updateDate = new DateUtilService().getCurrentDateAndTime();
			
				if (conductedDate != null && conductedDate.trim().length() > 0) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						updatedCount = partyMeetingDAO.updateMOMConductedDate(meetingId, sdf.parse(conductedDate), loginUserId,updateDate);
				}
				if (isConducted != null && isConducted.trim().length() > 0) {
					updatedCount = partyMeetingDAO.updateMOMConductedStatus(meetingId, isConducted, loginUserId, updateDate);
				} 
				if (remarks != null && remarks.trim().length() > 0) {
					updatedCount = partyMeetingDAO.updateMOMConductedReason(meetingId, remarks, loginUserId, updateDate);
				}
			
				if (updatedCount > 0) {
					status = "success";
				}

		} catch (Exception e) {
			 status = "fail";
			LOG.error("Exception occurred at updateMOMMeetingDetails() of PartyMeetingMOMService class ",e);
		}
		return status;
	}
	/**
	   * @param Long id
	   * @param Long loginUserId
	   * @param String deletedType
	   * @return String
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used deleted required field by deletedType.. 
	   * @since 1-DECEMBER-2017
	   */
	public String deleteMOMMeetingDetails(Long id, String deletedType,Long loginUserId) { 
		//present this service is not used
		String status = "";
		try {
			  //if deltedType is document then id will contain documentId
			  //if deltedType is minutePoints then id will contain minutesPointId
				Date updateDate = new DateUtilService().getCurrentDateAndTime();
			    if (deletedType != null && deletedType.equalsIgnoreCase("document")) {
			    	Integer updatedCount = partyMeetingDocumentDAO.deletePartyMeetingMOMDocument(id, loginUserId, updateDate);
			    	if (updatedCount > 0) {
			    		status = "success";
			    	}
			    } else if (deletedType != null && deletedType.equalsIgnoreCase("minutesPoints")) {
			    	status = deleteMeetingMinutePoint(id, loginUserId);
			    }
		} catch (Exception e) {
			 status = "fail";
			LOG.error("Exception occurred at updateMOMMeetingDetails() of PartyMeetingMOMService class ",e);
		}
		return status;
	}
	public String deleteMeetingMinutePoint(final Long minuteId,final Long updatedBy){
		String updateStatusString="failed";
		try {
			LOG.info("Entered into deleteMeetingMinutePoint");
			
			updateStatusString = (String) transactionTemplate.execute(new TransactionCallback() 
	    	{
			  public Object doInTransaction(TransactionStatus status) 
			  {
				  String updated = "success";
				  PartyMeetingMinute pmm = partyMeetingMinuteDAO.get(minuteId);
					
					PartyMeetingMinuteHistory pmmh = new PartyMeetingMinuteHistory();
					
					pmmh.setPartyMeetingMinuteId(pmm.getPartyMeetingMinuteId());
					pmmh.setPartyMeetingId(pmm.getPartyMeetingId());
					pmmh.setMinutePoint(pmm.getMinutePoint());
					pmmh.setInsertedById(pmm.getInsertedBy().getUserId());
					pmmh.setUpdatedById(pmm.getUpdatedBy().getUserId());
					pmmh.setInsertedTime(pmm.getInsertedTime());
					pmmh.setUpdatedTime(pmm.getUpdatedTime());
					
					partyMeetingMinuteHistoryDAO.save(pmmh);
					
					Integer deleteStatus = partyMeetingMinuteDAO.deleteMeetingMinutePoint(minuteId,updatedBy,new DateUtilService().getCurrentDateAndTime());
					if(deleteStatus.intValue()==0){
						updated  = "failed";
					}
					return updated;
			  }
	       });
		}catch (Exception e) {
			LOG.error("Exception raised at deleteMeetingMinutePoint", e);
		}
		return updateStatusString;
	}
	
	public Date[] getDates(String fromDate,String toDate,String formate) {
		Date[] dateArr = new Date[2];
		 try {
			 SimpleDateFormat sdf = new SimpleDateFormat(formate);
			 if (fromDate != null && toDate != null) {
				 dateArr[0] = sdf.parse(fromDate);
				 dateArr[1] = sdf.parse(toDate);
			 }
		 } catch (Exception e) {
			 LOG.error("Exception occurred at getDates() of PartyMeetingMOMService class ",e);
		 }
		 return dateArr;
	}
	public Integer[] getMontYear(String monthYear) {
		Integer[] monthYearArr = new Integer[2];
		 try {
			 if (monthYear != null && monthYear.trim().length() > 0) {
				 monthYearArr[0] = Integer.valueOf(monthYear.split("-")[0]);
				 monthYearArr[1] = Integer.valueOf(monthYear.split("-")[1]);
			 }
		 } catch (Exception e) {
			 LOG.error("Exception occurred at getMontYear() of PartyMeetingMOMService class ",e);
		 }
		 return monthYearArr;
	}
	/**
	   * @param PartyMeetingMOMCreationDtlsvO inputVO
	   * @return ResultStatus
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to saving mom details. 
	   * @since 4-DECEMBER-2017
	   */
	public ResultStatus savePartyMeetingMOMDetails(final PartyMeetingMOMCreationDtlsvO inputVO) {
		ResultStatus resultVO = new ResultStatus();
		try {
			LOG.info("Entered into savePartyMeetingMOMDetails in PartyMeetingMOMService class");
			if (inputVO != null) {
				resultVO = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
							public Object doInTransaction(TransactionStatus status) {

								PartyMeetingMinute model = new PartyMeetingMinute();

								model.setPartyMeetingId((inputVO.getPartyMeetingId() != null && inputVO.getPartyMeetingId() > 0) ? inputVO.getPartyMeetingId() : null);
								model.setMinutePoint(inputVO.getMomPoints());
								model.setIsActionable(inputVO.getIsActionable());
								
								if (inputVO.getIsActionable() != null && inputVO.getIsActionable().equalsIgnoreCase("Y")) {
									model.setMomAtrSourceTypeId((inputVO.getMomSourceTypeId() != null && inputVO.getMomSourceTypeId() > 0) ? inputVO.getMomSourceTypeId() : null);
									model.setStatusId(1l);//default status 1 means created
									model.setMomPriorityId((inputVO.getMomPriorityId() != null && inputVO.getMomPriorityId() > 0)?inputVO.getMomPriorityId(): null);
									//Created Location details
									model.setCreatedLocationScopeId((inputVO.getCreatedLocationScopeId() != null && inputVO.getCreatedLocationScopeId() > 0) ? inputVO.getCreatedLocationScopeId():null);
									model.setCreatedLocationValue((inputVO.getCreatedLocationValue() != null && inputVO.getCreatedLocationValue() > 0) ? inputVO.getCreatedLocationValue():null);
									UserAddress createLocationAddress = saveUserAddressByLevelIdAndLevelValue(inputVO.getCreatedLocationScopeId(),inputVO.getCreatedLocationValue());//saving user address and getting saved object
									if (createLocationAddress == null) {
										throw new ArithmeticException();
									}
									model.setCreatedAddressId(createLocationAddress.getUserAddressId());
									//MOM Location details
									model.setLocationScopeId((inputVO.getLocationScopeId() != null && inputVO.getLocationScopeId() > 0) ? inputVO.getLocationScopeId() : null);
									model.setLocationScopeValue((inputVO.getLocationScopeValue() != null && inputVO.getLocationScopeValue() > 0) ? inputVO.getLocationScopeValue() : null);
									UserAddress userAddress = saveUserAddressByLevelIdAndLevelValue(inputVO.getLocationScopeId(),inputVO.getLocationScopeValue());//saving user address and getting saved object
									if (userAddress == null) {
										throw new ArithmeticException();
									}
									model.setLoactionAddressId(userAddress.getUserAddressId());
								}

								model.setItdpAppUserId((inputVO.getItdpAppUserId() != null && inputVO.getItdpAppUserId() > 0) ? inputVO.getItdpAppUserId() : null);
								model.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
								model.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								model.setIsDeleted("N");
								
								/* start saving location details for web dashboard */
								List<Long> partyLevelIdsList = partyMeetingMinuteDAO.getPartyMeetingLevelByRegionScope(model.getCreatedLocationScopeId());
								if(commonMethodsUtilService.isListOrSetValid(partyLevelIdsList)){
									model.setLocationLevel(partyLevelIdsList.get(0));
									model.setLocationValue(model.getCreatedLocationValue());
									model.setUserAddressId(model.getCreatedAddressId());
								}
								
								/* end saving location details for web dashboard */
								
								model = partyMeetingMinuteDAO.save(model);
								//setting into vo to save into tracking table purpose
								inputVO.setPartyMeetingMinuteId(model.getPartyMeetingMinuteId());
								inputVO.setStatusId(model.getStatusId());
								
								String resultStatus = "";
								if (inputVO.getImageBase64Arr() != null && inputVO.getImageBase64Arr().length > 0) {
									resultStatus = savePartyMeetingMOMDocument(inputVO);// saving document
								} else {
									resultStatus = savePartyMeetingMOMTrackingDetails(inputVO);//saving direct tracking details if not document is not uploaded
								}
								if (resultStatus.equalsIgnoreCase("fail")) {
									throw new ArithmeticException();
								}
								ResultStatus resultVO = new ResultStatus();
								resultVO.setResultCode(1);
								resultVO.setMessage("success");
								return resultVO;

							}
						});
			}
		} catch (Exception e) {
			resultVO.setResultCode(2);
			resultVO.setExceptionMsg(e.getLocalizedMessage());
			resultVO.setMessage("fail");
			LOG.error("Exception occurred at savePartyMeetingMOMDetails() of PartyMeetingMOMService class ",e);
		}
		return resultVO;
	}
	private String savePartyMeetingMOMDocument(PartyMeetingMOMCreationDtlsvO inputVO) {
		String status = "";
		try {

			if (inputVO.getImageBase64Arr() != null && inputVO.getImageBase64Arr().length > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_WITH_SECONDS);
				// Folder Creation
				String folderName = createFolderForMOMImages();

				for (String base64Str : inputVO.getImageBase64Arr()) {
					String randomNumberStr = "0";
					while (randomNumberStr.length() < 10) {
						int randomNumber = Math.abs(new Random().nextInt());
						if (String.valueOf(randomNumber).length() > 10){
							randomNumberStr = String.valueOf(randomNumber).substring(0, 10);
						} else {
							randomNumberStr = String.valueOf(randomNumber);
						}
					}

					String currentDate = new DateUtilService().getCurrentDateAndTimeInStringFormat().substring(0,10);

					StringBuilder pathPuBuilder = new StringBuilder();
					pathPuBuilder.append(currentDate).append("/").append(randomNumberStr).append(".jpg");

					String destPath = folderName + "/" + randomNumberStr + ".jpg";
					if (base64Str != null && base64Str.trim().length() > 0) {
						inputVO.setImageBase64String(base64Str.replace("_", "/"));
						inputVO.setImageBase64String(base64Str.replace("-", "+"));
						boolean imageStatus = convertBase64StringToImage(inputVO.getImageBase64String(), destPath);
						if (imageStatus) {
							// model.setImageBase64Str(base64Str);
						} else {
							 status = "fail";
						}
					}
					
					PartyMeetingDocument model = new PartyMeetingDocument();
					model.setPartyMeetingId((inputVO.getPartyMeetingId() != null && inputVO.getPartyMeetingId() > 0) ? inputVO.getPartyMeetingId() : null);
					model.setPath(pathPuBuilder.toString());
					model.setDocumentType("MINUTE");
					model.setDocumentFormat("IMAGE");
					model.setUploadedTime(inputVO.getUploadedTime() != null ? sdf.parse(inputVO.getUploadedTime()) : null);
					model.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					model.setItdpAppUserId((inputVO.getItdpAppUserId() != null && inputVO.getItdpAppUserId() > 0) ? inputVO.getItdpAppUserId() : null);
					model.setPartyMeetingMinuteId((inputVO.getPartyMeetingMinuteId() != null && inputVO.getPartyMeetingMinuteId() > 0) ? inputVO.getPartyMeetingMinuteId() : null);
					model.setIsDeleted("N");
					model = partyMeetingDocumentDAO.save(model);
					inputVO.setPartyMeetingDocumentId(model.getPartyMeetingDocumentId());
					
					status = savePartyMeetingMOMTrackingDetails(inputVO);//saving mom tracking details
					
					
					if (status.equalsIgnoreCase("fail")) {
						throw new ArithmeticException();
					}
				}
			}

		} catch (Exception e) {
			status = "fail";
			LOG.error("Exception occurred at savePartyMeetingMOMDocument() of PartyMeetingMOMService class ",e);
		}
		return status;
	}
   private String savePartyMeetingMOMTrackingDetails(PartyMeetingMOMCreationDtlsvO inputVO) {
	   String status = "";
	   try {
		   if (inputVO != null ) {
			   PartyMeetingMinuteTracking meetingMinuteTracking = new PartyMeetingMinuteTracking();
			   meetingMinuteTracking.setPartyMeetingMinuteId((inputVO.getPartyMeetingMinuteId() != null && inputVO.getPartyMeetingMinuteId() > 0) ? inputVO.getPartyMeetingMinuteId() : null);
			   meetingMinuteTracking.setPartyMeetingMinuteStatusId((inputVO.getStatusId() != null && inputVO.getStatusId() > 0) ? inputVO.getStatusId() :null);
			   meetingMinuteTracking.setComment(inputVO.getComment());
			   meetingMinuteTracking.setPartyMeetingMinuteStatusId((inputVO.getStatusId() != null && inputVO.getStatusId() > 0) ? inputVO.getStatusId():null );
			   
			   meetingMinuteTracking.setAssignedLocationScopeId((inputVO.getAssignedLocationScopeId() != null && inputVO.getAssignedLocationScopeId() > 0) ? inputVO.getAssignedLocationScopeId():null);
			   meetingMinuteTracking.setAssignedLocationValue((inputVO.getAssignedLocationValue() != null && inputVO.getAssignedLocationValue() > 0) ? inputVO.getAssignedLocationValue():null);
			   meetingMinuteTracking.setAssignedAddressId((inputVO.getAssignedAddressId() != null && inputVO.getAssignedAddressId() > 0) ? inputVO.getAssignedAddressId():null);
			   
			   meetingMinuteTracking.setPartyMeetingoDcumentId(inputVO.getPartyMeetingDocumentId());
			   meetingMinuteTracking.setItdpAppUserId((inputVO.getItdpAppUserId() != null && inputVO.getItdpAppUserId() > 0) ? inputVO.getItdpAppUserId() : null);
			   meetingMinuteTracking.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
			   partyMeetingMinuteTrackingDAO.save(meetingMinuteTracking);
			   
			   status = "success";
		   }
	   } catch (Exception e) {
		   status = "fail";
		   LOG.error("Exception occurred at savePartyMeetingMOMTrackingDetails() of PartyMeetingMOMService class ",e);
	   }
	   return status;
   }
      /**
	   * @param PartyMeetingMOMCreationDtlsvO inputVO
	   * @return ResultStatus
	   * @author Santosh Kumar Verma
	   * @Description :This Service is used to assigned mom upper level location and updating status. 
	   * @since 6-DECEMBER-2017
	   */
   public ResultStatus updateMomDetails(final PartyMeetingMOMCreationDtlsvO inputVO) {
	    ResultStatus resultVO = new ResultStatus();
		try {
			LOG.info("Entered into updateMomDetails() of PartyMeetingMOMService class");
			if (inputVO != null) {
				resultVO = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
							public Object doInTransaction(TransactionStatus status) {

								PartyMeetingMinute model = partyMeetingMinuteDAO.get(inputVO.getPartyMeetingMinuteId());
								if (model != null) {
									// getting upper level assigned location
									Long upperLevelLocationId = 0l;
									Long upperLevelLocationValue = 0l;
									if (inputVO.getAssignedType() != null && inputVO.getAssignedType().equalsIgnoreCase("assignToUpperLevel")) {
										  UserAddress assignedAddress = null;
										  Long locationScopeId = 0l;
										 if (model.getAssignedAddressId() == null) { 
											 /*if first time mom has not assigned to any location,
											  * then we are assigning to upper level location by creation scopeId. 
											  */
											 assignedAddress = userAddressDAO.get(model.getCreatedAddressId());
											 locationScopeId = inputVO.getCreatedLocationScopeId();
										 } else {
											 /* But if mom has already assigned then user want to assign once again to upper level location
											  * of current assign location ,then we are taking assigned location scopeId and address 
											  * by that we are assign to upper level of current assign location 
											  */
											 locationScopeId = model.getAssignedLocationScopeId();
											 assignedAddress = userAddressDAO.get(model.getAssignedAddressId());
										 }
										List<Long> uppderLevelLocationIds = regionScopesDAO.getUppderLevelLocationId(locationScopeId);
										if (uppderLevelLocationIds != null && uppderLevelLocationIds.size() > 0) {
											upperLevelLocationId = uppderLevelLocationIds.get(0);
											upperLevelLocationValue = getUpperLevelLocationValue(upperLevelLocationId,assignedAddress); // getting upper level location value
										}
										
									}
									model.setAssignedLocationScopeId(upperLevelLocationId > 0 ? upperLevelLocationId:null);
									model.setAssignedLocationValue(upperLevelLocationValue > 0 ? upperLevelLocationValue:null);
									//assigned Address
									UserAddress assignedAddress = saveUserAddressByLevelIdAndLevelValue(upperLevelLocationId,upperLevelLocationValue);//saving user address and getting saved object
									if (assignedAddress == null) {
										throw new ArithmeticException();
									}
									model.setAssignedAddressId(assignedAddress.getUserAddressId());
									model.setStatusId((inputVO.getStatusId() != null && inputVO.getStatusId() > 0) ? inputVO.getStatusId():null );
									model.setItdpAppUserId((inputVO.getItdpAppUserId() != null && inputVO.getItdpAppUserId() > 0) ? inputVO.getItdpAppUserId() : null);
									model.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									
									//setting assigned details into vo for saving tracking table purpose
									inputVO.setAssignedAddressId(model.getAssignedAddressId());//setting into 
									inputVO.setAssignedLocationScopeId(model.getAssignedLocationScopeId());
									inputVO.setAssignedLocationValue(model.getAssignedLocationValue());
									inputVO.setPartyMeetingMinuteId(model.getPartyMeetingMinuteId());
									inputVO.setPartyMeetingId(model.getPartyMeetingId());
									
								    partyMeetingMinuteDAO.save(model);
											
								}
								
								String resultStatus = "";
								if (inputVO.getImageBase64Arr() != null && inputVO.getImageBase64Arr().length > 0) {
									resultStatus = savePartyMeetingMOMDocument(inputVO);// saving document
								} else {
									resultStatus = savePartyMeetingMOMTrackingDetails(inputVO);//saving direct tracking details if not document is not uploaded
								}
								if (resultStatus.equalsIgnoreCase("fail")) {
									throw new ArithmeticException();
								}
								ResultStatus resultVO = new ResultStatus();
								resultVO.setResultCode(1);
								resultVO.setMessage("success");
								return resultVO;

							}
						});
			}
		} catch (Exception e) {
			resultVO.setResultCode(2);
			resultVO.setExceptionMsg(e.getLocalizedMessage());
			resultVO.setMessage("fail");
			LOG.error("Exception occurred at updateMomDetails() of PartyMeetingMOMService class ",e);
		}
		return resultVO;
   }
   public UserAddress saveUserAddressByLevelIdAndLevelValue(Long levelId,Long levelValue)
	{
		try{
			UserAddress userAddress = null;
			if(levelId == 2l){
				userAddress = new UserAddress();
				userAddress.setState(stateDAO.get(levelValue));
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 3l){
				userAddress = new UserAddress();
				District distinct = districtDAO.get(levelValue);
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 4l){
				userAddress = new UserAddress();
				Constituency constituency = constituencyDAO.get(levelValue);
				District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress.setConstituency(constituency);
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 5l){//mandal
				userAddress = new UserAddress();
				Tehsil tehsil = tehsilDAO.get(levelValue);
				District distinct = districtDAO.get(tehsil.getDistrict().getDistrictId());
				List<Long> constituencyIds = delimitationConstituencyMandalDAO.getConstituencyIdByMandalID(levelValue);
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				if(constituencyIds != null && constituencyIds.size() > 0)
				  userAddress.setConstituency(constituencyDAO.get(constituencyIds.get(0)));
				userAddress.setTehsil(tehsil);
				userAddress = userAddressDAO.save(userAddress);
			}
			
			else if(levelId == 6l){//panchayat
				userAddress = new UserAddress();
				Panchayat panchayat = panchayatDAO.get(levelValue);
				
				Tehsil tehsil = tehsilDAO.get(panchayat.getTehsil().getTehsilId());
				District distinct = districtDAO.get(tehsil.getDistrict().getDistrictId());
				List<Long> constituencyIds = delimitationConstituencyMandalDAO.getConstituencyIdByMandalID(tehsil.getTehsilId());
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				if(constituencyIds != null && constituencyIds.size() > 0)
				  userAddress.setConstituency(constituencyDAO.get(constituencyIds.get(0)));
				userAddress.setTehsil(tehsil);
				userAddress.setPanchayatId(panchayat.getPanchayatId());
				userAddress = userAddressDAO.save(userAddress);
			}
			
			else if(levelId == 7l){//muncipality
				userAddress = new UserAddress();
				List<Long> constituencyIdsList = assemblyLocalElectionBodyDAO.getConstituencyIdByAssemblyLocalEleBodyId(levelValue);
				Long constituencyId = Long.parseLong(constituencyIdsList.get(0).toString());
				
				Constituency constituency = constituencyDAO.get(constituencyId);
				District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
					
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress.setConstituency(constituency);
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(levelValue));
				userAddress = userAddressDAO.save(userAddress);
			}
			
			else if(levelId == 8l ){//ward
				userAddress = new UserAddress();
				Long localEleBodyId = constituencyDAO.get(levelValue).getLocalElectionBody().getLocalElectionBodyId();
				
				List<Long> constituencyIdsList = assemblyLocalElectionBodyDAO.getConstituencyIdByAssemblyLocalEleBodyId(localEleBodyId);
				Long constituencyId = Long.parseLong(constituencyIdsList.get(0).toString());
				Constituency constituency = constituencyDAO.get(constituencyId);
				
				District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
				
				userAddress.setState(stateDAO.get(distinct.getState().getStateId()));
				userAddress.setDistrict(distinct);
				userAddress.setConstituency(constituency);
				userAddress.setWard(constituencyDAO.get(levelValue));
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(localEleBodyId));
				
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 10l){//Parliament
				userAddress = new UserAddress();
				Constituency constituency = constituencyDAO.get(levelValue);
				
				userAddress.setState(stateDAO.get(constituency.getState().getStateId()));
				userAddress.setParliamentConstituency(constituencyDAO.get(levelValue));
				
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 11l){	//MPTC
				userAddress = new UserAddress();
				Constituency constituency = constituencyDAO.get(levelValue);
				
				userAddress.setState(stateDAO.get(constituency.getState().getStateId()));
				userAddress.setDistrict(districtDAO.get(constituency.getDistrict().getDistrictId()));
				userAddress.setConstituency(constituency);
				userAddress.setTehsil(tehsilDAO.get(constituency.getTehsil().getTehsilId()));
				
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 12l){  //ZPTC
				userAddress = new UserAddress();
				Constituency constituency = constituencyDAO.get(levelValue);
				
				userAddress.setState(stateDAO.get(constituency.getState().getStateId()));
				userAddress.setDistrict(districtDAO.get(constituency.getDistrict().getDistrictId()));
				userAddress.setConstituency(constituency);
				userAddress.setTehsil(tehsilDAO.get(constituency.getTehsil().getTehsilId()));
				
				userAddress = userAddressDAO.save(userAddress);
			}
			else if(levelId == 13l || levelId == 14l){  //Corporation||GMC
				userAddress = new UserAddress();
				LocalElectionBody localElectionBody = localElectionBodyDAO.get(levelValue);
				District district = districtDAO.get(localElectionBody.getDistrict().getDistrictId());
				
				userAddress.setState(stateDAO.get(district.getState().getStateId()));
				userAddress.setDistrict(district);
				userAddress.setLocalElectionBody(localElectionBody);
				
				userAddress = userAddressDAO.save(userAddress);
			}
			
			return userAddress;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in saveUserAddress() method, Exception - ",e);
		}
		return null;
	}
  
   private Long getUpperLevelLocationValue(Long upperLevelCommitteeId,UserAddress userAddress) {
		Long uppderLevelLocationValue = 0l;
		try {
			
			if (upperLevelCommitteeId != null && userAddress != null) {
				
				if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_MANDAL_LEVEl_ID)) {
					uppderLevelLocationValue = userAddress.getTehsil().getTehsilId();
				} else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID)) {
					uppderLevelLocationValue = userAddress.getPanchayatId();
				}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID)) {
					uppderLevelLocationValue = userAddress.getLocalElectionBody().getLocalElectionBodyId();
				}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_WARD_LEVEl_ID)) {
					uppderLevelLocationValue = userAddress.getWard().getConstituencyId();
				}else if (upperLevelCommitteeId .equals(IConstants.DIVISION_COMMITTEE_LEVEL_ID)) {
					uppderLevelLocationValue = userAddress.getWard().getConstituencyId();
				}else if (upperLevelCommitteeId.equals(IConstants.STATE_LEVEl_ACCESS_ID)) {
					uppderLevelLocationValue = userAddress.getState().getStateId();
				}else if (upperLevelCommitteeId.equals(IConstants.DISTRICT_LEVEl_ACCESS_ID)) {
					uppderLevelLocationValue = userAddress.getDistrict().getDistrictId();
				}else if (upperLevelCommitteeId.equals( IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID)) {
					uppderLevelLocationValue = userAddress.getConstituency().getConstituencyId();
				}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_COUNTRY_LEVEl_ID)) {
					uppderLevelLocationValue = userAddress.getCountry().getCountryId();
				}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID)) {
					uppderLevelLocationValue = userAddress.getParliamentConstituency().getConstituencyId();
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getUpperLevelLocationValue() of PartyMeetingMOMService class ",e);
		}
		return uppderLevelLocationValue;
	}

public static String createFolderForMOMImages()
{
	try {
		LOG.debug(" in FolderForDocument ");
  		String date= new DateUtilService().getCurrentDateAndTimeInStringFormat().substring(0, 10);
  		String targetDirpath = IConstants.STATIC_CONTENT_FOLDER_URL+""+IConstants.PARTY_MEETING_MOM_DOCUMENT+"/"+date;
		
		File requriredDir = new File(targetDirpath);
		
		if(!requriredDir.exists())
			requriredDir.mkdirs();
		
		return requriredDir.getAbsolutePath();
		 
	} catch (Exception e) {
		LOG.error(" Failed to createFolderForMOMImages");  
		return "FAILED";
	}
}
public boolean convertBase64StringToImage(String imageDataString,String imagePath)
{
	 try{
		 byte[] imageByteArray = Base64.decodeBase64(imageDataString);
		 FileOutputStream imageOutFile = new FileOutputStream(imagePath);
		 imageOutFile.write(imageByteArray);
		 imageOutFile.close();
		 return true;
	 }catch(Exception e)
	 {
		 LOG.error(e);
		 return false;
	 }
}

/**
 * @param Long partyMeetingMOMId
 * @return MomDashbaordOverViewDtlsVO
 * @author Santosh Kumar Verma
 * @Description :This Service is used get mom DashBoard overview details. 
 * @since 6-DECEMBER-2017
 */
 public MomDashbaordOverViewDtlsVO getMomDashboardOverviewDtls(Long userAccessLevel,List<Long> accessValues,String monthYear) {
	 MomDashbaordOverViewDtlsVO resultVO = new MomDashbaordOverViewDtlsVO();
	  try {
		  Integer[] monthYearArr = getMontYear(monthYear);
		  List<Object[]> momPriorityObjList = partyMeetingMinuteDAO.getPartyMeetingMomDtls(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "momPriorityWise");
		  resultVO.setSubList1(getMomStatusPriorityWiseCountDetails(momPriorityObjList));
		  List<Object[]> momStatusObjList = partyMeetingMinuteDAO.getPartyMeetingMomDtls(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "statusWise");
		  resultVO.setSubList2(getMomStatusPriorityWiseCountDetails(momStatusObjList));
		  
		  Long momCreatedByYourLocationCount = partyMeetingMinuteDAO.getMomCreatedByYourLocation(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "");
		  Long momAtYourLocationOnlyCount = 0L;//partyMeetingMinuteDAO.getMomCreatedByYourLocation(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "atYourLocationOnly");;
		  Long assignedToOtherCount = partyMeetingMinuteDAO.getMomCreatedByYourLocation(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "assignedToOther");
		  Long assignedToYourLocationCount = partyMeetingMinuteDAO.getMomAssignedToYourLocation(userAccessLevel, accessValues,monthYearArr[0], monthYearArr[1]);
		  Long TtotalMomInYourLocation =0L;
		  if(momCreatedByYourLocationCount == null || momCreatedByYourLocationCount.longValue()==0L){// if not created atleast one MOM
			  momCreatedByYourLocationCount=0L;
			  if(assignedToYourLocationCount != null && assignedToYourLocationCount.longValue()>0L) // all assigned MOMs to my location comes under momAtYourLocationOnlyCount
				  momAtYourLocationOnlyCount = assignedToYourLocationCount;
		  }else{
			  if(momCreatedByYourLocationCount != null){// removind assinged to others MOMs
				  if(assignedToOtherCount != null && assignedToOtherCount.longValue()>0L)
					  momAtYourLocationOnlyCount = momCreatedByYourLocationCount - assignedToOtherCount;
				  else
					  momAtYourLocationOnlyCount=momCreatedByYourLocationCount;
			  }
			  // momAtYourLocationOnlyCount = created by me (excluding assigned to others) + assigned to me
			  if(momAtYourLocationOnlyCount != null && momAtYourLocationOnlyCount.longValue()>0L){ 
				  momAtYourLocationOnlyCount = momAtYourLocationOnlyCount+assignedToYourLocationCount;
			  }
		  }
		  
		  if(momCreatedByYourLocationCount != null && momCreatedByYourLocationCount.longValue() >0L){
			  TtotalMomInYourLocation = momCreatedByYourLocationCount;
			  if(assignedToOtherCount != null && assignedToOtherCount.longValue()>0L){
				  TtotalMomInYourLocation = momCreatedByYourLocationCount+assignedToYourLocationCount;// created in my location + assigned to my location
			  }
		  }
		  else if(assignedToOtherCount != null && assignedToOtherCount.longValue()>0L){
			  TtotalMomInYourLocation = momCreatedByYourLocationCount+assignedToYourLocationCount;// created in my location + assigned to my location
		  }
		  
		  //setting into final VO
		  resultVO.setMomCreatedByYourLocation(momCreatedByYourLocationCount);
		  resultVO.setMomAtYourLocationOnly(momAtYourLocationOnlyCount);
		  resultVO.setAssignedToOther(assignedToOtherCount);
		  resultVO.setAssignedToYourLocation(assignedToYourLocationCount);
		  resultVO.setTotalMomInYourLocation(TtotalMomInYourLocation);
		  
		/*  if (resultVO.getSubList2() != null && resultVO.getSubList2().size() > 0) {
			  resultVO.setTotalMomInYourLocation(resultVO.getSubList2().get(0).getTotalMomInYourLocation());
			  resultVO.getSubList2().get(0).setTotalMomInYourLocation(0l);
		  }*/
		  
	  } catch (Exception e) {
		  LOG.error("Exception occurred at getMomDashboardOverviewDtls() of PartyMeetingMOMService class ",e);
	  }
	  return resultVO;
 }
  public List<MomDashbaordOverViewDtlsVO> getMomStatusPriorityWiseCountDetails(List<Object[]> objList) {
	  List<MomDashbaordOverViewDtlsVO> finalList = new ArrayList<MomDashbaordOverViewDtlsVO>();
	  try {
		    if (objList != null && objList.size() > 0) {
		    	Long totalMonCount = 0l;
		    	for (Object[] param : objList) {
					MomDashbaordOverViewDtlsVO vo = new MomDashbaordOverViewDtlsVO();
					 vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					 vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					 vo.setCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					 totalMonCount = totalMonCount + vo.getCount();
					 finalList.add(vo);
				}
		    	finalList.get(0).setTotalMomInYourLocation(totalMonCount);
		    }
		    
     } catch (Exception e) {
		   LOG.error("Exception occurred at getMomStatusPriorityWiseCountDetails() of PartyMeetingMOMService class ",e);
	   }
	  return finalList;
  }
  /**
   * @param Long partyMeetingMOMId
   * @return MomDashbaordOverViewDtlsVO
   * @author Santosh Kumar Verma
   * @Description :This Service is used get mom DashBoard overview details. 
   * @since 6-DECEMBER-2017
   */
   public List<MomDetailsVO> getMomDetailsBySelectedType(Long userAccessLevel,List<Long> accessValues,String monthYear,String type) {
  	 List<MomDetailsVO> resultList = new ArrayList<MomDetailsVO>(0);
  	  try {
  		  //Date[] dateArr = getDates(fromDate, toDate, "dd-MM-yyyy");
  		   Integer[] monthYearArr = getMontYear(monthYear);
  		   List<Object[]> momCreatedLocObjList = partyMeetingMinuteDAO.getMomCreationLocation(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1]);
		   if (type != null && type.equalsIgnoreCase("totalMom")) {
			   
  			  List<Object[]> momDtlsObjList = partyMeetingMinuteDAO.getTotalMomDetailsByLocation(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1]);
  			  Set<Long> momIdSet = getMomIdSet(momDtlsObjList);
  			  List<Object[]> momAssignedLocObjList = partyMeetingMinuteDAO.getMomAssignedLocation(momIdSet,monthYearArr[0], monthYearArr[1]);
  		 	  resultList = getMomDetails(momDtlsObjList,userAccessLevel,accessValues,momCreatedLocObjList,momAssignedLocObjList);
  	  	  
		   } else if (type != null && type.equalsIgnoreCase("yourLocationCreatedMom")) {
			   
  	 		  List<Object[]> objList = partyMeetingMinuteDAO.getMomDetailsByType(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "","createdMom");
  	 		  Set<Long> momIdSet = getMomIdSet(objList);
  			  List<Object[]> momAssignedLocObjList = partyMeetingMinuteDAO.getMomAssignedLocation(momIdSet,monthYearArr[0], monthYearArr[1]);
  			  resultList = getMomDetails(objList,userAccessLevel,accessValues,momCreatedLocObjList,momAssignedLocObjList);
  	 	 
		   } else if (type != null && type.equalsIgnoreCase("atYourLocationOnly")) {
			   
  	 		  List<Object[]> objList = partyMeetingMinuteDAO.getMomDetailsByType(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "atYourLocationOnly","createdMom");
  	 		  Set<Long> momIdSet = getMomIdSet(objList);
  			  List<Object[]> momAssignedLocObjList = partyMeetingMinuteDAO.getMomAssignedLocation(momIdSet,monthYearArr[0], monthYearArr[1]);
  			  resultList = getMomDetails(objList,userAccessLevel,accessValues,momCreatedLocObjList,momAssignedLocObjList);
  	 	 
		   } else if (type != null && type.equalsIgnoreCase("assignedToOther")) {
  	 		  List<Object[]> objList = partyMeetingMinuteDAO.getMomDetailsByType(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "assignedToOther","createdMom");
  	 		  Set<Long> momIdSet = getMomIdSet(objList);
  			  List<Object[]> momAssignedLocObjList = partyMeetingMinuteDAO.getMomAssignedLocation(momIdSet,monthYearArr[0], monthYearArr[1]);
  			  resultList = getMomDetails(objList,userAccessLevel,accessValues,momCreatedLocObjList,momAssignedLocObjList);
  			  
  	 	  } else if (type != null && type.equalsIgnoreCase("yourAssignedMom")) {
  	 		  
  	 		  List<Object[]> objList = partyMeetingMinuteDAO.getMomDetailsByType(userAccessLevel, accessValues, monthYearArr[0], monthYearArr[1], "","assignedMom");
 	 		  Set<Long> momIdSet = getMomIdSet(objList);
 			  List<Object[]> momAssignedLocObjList = partyMeetingMinuteDAO.getMomAssignedLocation(momIdSet,monthYearArr[0], monthYearArr[1]);
 			  resultList = getMomDetails(objList,userAccessLevel,accessValues,momCreatedLocObjList,momAssignedLocObjList);
 			  
  	 	  }
  	  } catch (Exception e) {
  		  LOG.error("Exception occurred at getMomDetailsBySelectedType() of PartyMeetingMOMService class ",e);
  	  }
  	  return resultList;
   }
   public List<MomDetailsVO> getMomDetails(List<Object[]> objList,Long userAccessLevel,List<Long> accessValues,List<Object[]> momCreatedLocObjList,List<Object[]> momAssignedLocObjList) {
	   List<MomDetailsVO> finalList = new ArrayList<MomDetailsVO>(0);
	    try {
	    	if (objList != null && objList.size() > 0) {
	    		
	    		for (Object[] param : objList) {
	    			MomDetailsVO momDetailsVO = new MomDetailsVO();
	    			momDetailsVO.setMeetingId(commonMethodsUtilService.getLongValueForObject(param[0]));
	    			momDetailsVO.setMeetingName(commonMethodsUtilService.getStringValueForObject(param[1]));
	    			momDetailsVO.setPriority(commonMethodsUtilService.getStringValueForObject(param[2]));
	    			momDetailsVO.setStatus(commonMethodsUtilService.getStringValueForObject(param[3]));
	    			momDetailsVO.setDate(commonMethodsUtilService.getStringValueForObject(param[4]));
	    			momDetailsVO.setMomPoints(commonMethodsUtilService.getStringValueForObject(param[5]));
	    			momDetailsVO.setMomPointsId(commonMethodsUtilService.getLongValueForObject(param[6]));
	    			momDetailsVO.setIsEditable("false");
	    			Long creationScopeId = commonMethodsUtilService.getLongValueForObject(param[7]);
	    			Long creationScopeValue = commonMethodsUtilService.getLongValueForObject(param[8]);
	    			Long assignedScopeId = commonMethodsUtilService.getLongValueForObject(param[9]);
	    			Long assignedScopeValue = commonMethodsUtilService.getLongValueForObject(param[10]);
	    			if (userAccessLevel != null && userAccessLevel.equals(creationScopeId) && accessValues != null && accessValues.contains(creationScopeValue)) {
	    				momDetailsVO.setIsEditable("true");
	    			}
		            if (userAccessLevel != null && userAccessLevel.equals(assignedScopeId) && accessValues != null && accessValues.contains(assignedScopeValue)) {
		        	   momDetailsVO.setIsEditable("true");
	    			}
		        	momDetailsVO.setCreatedLocation(getRequiredLocationBasedOnLocationLevel(creationScopeId, getMatchObjectArr(momCreatedLocObjList, momDetailsVO.getMomPointsId())));//setting mom creation location name
	    			momDetailsVO.setAssignedLocation(getRequiredLocationBasedOnLocationLevel(assignedScopeId, getMatchObjectArr(momAssignedLocObjList, momDetailsVO.getMomPointsId())));//setting mom assigned location name
	    			finalList.add(momDetailsVO);
				}
	    	}
	    	
	    } catch (Exception e) {
	    	LOG.error("Exception occurred at getMomDetails() of PartyMeetingMOMService class ",e);
	    }
	    return finalList;
   }
    public Set<Long> getMomIdSet(List<Object[]> momCreatedLocObjList) {
    	Set<Long> momIdSet = new HashSet<Long>();
    	 try {
    		 if (momCreatedLocObjList != null && momCreatedLocObjList.size() > 0) {
    			  for (Object[] param : momCreatedLocObjList) {
    				  momIdSet.add(commonMethodsUtilService.getLongValueForObject(param[6]));
				}
    		 }
    		 
    	 } catch (Exception e) {
    		 LOG.error("Exception occurred at getMomIdSet() of PartyMeetingMOMService class ",e);
    	 }
    	 return momIdSet;
    }
	public Object[] getMatchObjectArr(List<Object[]> objList,Long partyMeetingMinuteId) {
		try {
			if (objList == null || objList.size() == 0)
				return null;
			for (Object[] param : objList) {
				Long momId = commonMethodsUtilService.getLongValueForObject(param[0]);
				if (momId.equals(partyMeetingMinuteId)) {
					return param;
				}
			}

		} catch (Exception e) {
			LOG.error("Exception occurred at getMatchObjectArr() of PartyMeetingMOMService class ",e);
		}
		return null;
	}
   private String getRequiredLocationBasedOnLocationLevel(Long locationLevelId,Object[] locationObj) {
		String  locationName = "";
		try {
			if (locationLevelId != null && locationObj != null) {
				
				if (locationLevelId.equals(IConstants.REGIONSCOPE_MANDAL_LEVEl_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[5]) + "Mandal";
				} else if (locationLevelId.equals(IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[6]) + " Village";
				}else if (locationLevelId.equals(IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[7]) + "Local Election Body";
				}else if (locationLevelId.equals(IConstants.REGIONSCOPE_WARD_LEVEl_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[8]) + " Ward";
				}else if (locationLevelId .equals(IConstants.DIVISION_COMMITTEE_LEVEL_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[8]) + " Division";
				}else if (locationLevelId.equals(IConstants.STATE_LEVEl_ACCESS_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[1])+ " State";
				}else if (locationLevelId.equals(IConstants.DISTRICT_LEVEl_ACCESS_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[2])+ " District";
				}else if (locationLevelId.equals( IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[4])+ " Constituency";
				}else if (locationLevelId.equals(IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID)) {
					locationName = commonMethodsUtilService.getStringValueForObject(locationObj[3])+ " Parliament ";
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occurred at getRequiredLocationBasedOnLocationLevel() of PartyMeetingMOMService class ",e);
		}
		return locationName;
	}
   /**
    * @param Long partyMeetingMOMId
    * @return MomDetailsVO
    * @author Santosh Kumar Verma
    * @Description :This Service is used get complete mom details 
    * @since 6-DECEMBER-2017
    */
   public MomDetailsVO getMomCompletedDetails(Long partyMeetingMOMId) {
   	MomDetailsVO resultVO = new MomDetailsVO();
   	 try {
   		  
   		 PartyMeetingMinute model = partyMeetingMinuteDAO.get(partyMeetingMOMId);
   		  if (model != null) {
   			  SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_WITH_SECONDS);
   			  resultVO.setMeetingId(model.getPartyMeeting() != null ? model.getPartyMeeting().getPartyMeetingId():0l);
   			  resultVO.setMeetingName(model.getPartyMeeting() != null ? model.getPartyMeeting().getMeetingName():"");
   			  resultVO.setPriority(model.getMomPriority() != null ? model.getMomPriority().getPriority(): "");
   			  resultVO.setStatus(model.getPartyMeetingMinuteStatus() != null ? model.getPartyMeetingMinuteStatus().getStatus():null);
   			  resultVO.setDate(model.getInsertedTime() != null ? sdf.format(model.getInsertedTime()) : null);
   			  resultVO.setMomPoints(model.getMinutePoint());
   			  resultVO.setMomPointsId(model.getPartyMeetingMinuteId());
   			  resultVO.setCreatedLocationScopeId(model.getCreatedLocationScopeId());
   			  resultVO.setCreatedLocationScopeValue(model.getCreatedLocationValue());
   			  resultVO.setAssignedLocationScopeId(model.getAssignedLocationScopeId());
   			  resultVO.setAssignedLocationScopeValue(model.getAssignedLocationValue());
   			  resultVO.setCreatedLocation(getLocationName(model.getCreatedLocationScopeId(),model.getCreatedAddress()));
   			  resultVO.setAssignedLocation(getLocationName(model.getAssignedLocationScopeId(), model.getAssignedAddress()));
   		  }
   		  List<String> docuemntList = partyMeetingDocumentDAO.getMomDocuments(partyMeetingMOMId);
   		  if (docuemntList != null) {
   			  resultVO.setDocumentList(docuemntList);
   		  }
   		  List<Object[]> commentObjList = partyMeetingMinuteTrackingDAO.getPartyMeetingMomComments(partyMeetingMOMId);
   		   if (commentObjList != null && commentObjList.size() > 0) {
   			   resultVO.setCommentList(new ArrayList<MomDetailsVO>(0));
   			   for (Object[] param : commentObjList) {
   				MomDetailsVO commentDtlsVO = new MomDetailsVO();
   				 commentDtlsVO.setDate(commonMethodsUtilService.getStringValueForObject(param[0]));
   				 commentDtlsVO.setComment(commonMethodsUtilService.getStringValueForObject(param[1]));
   				 resultVO.getCommentList().add(commentDtlsVO);
   			}
   		   }
   		 
   	 } catch (Exception e) {
   		 LOG.error("Exception occurred at getMomCompletedDetails() of PartyMeetingMOMService class ",e); 
   	 }
   	 return resultVO;
    }
   private String getLocationName(Long upperLevelCommitteeId,UserAddress userAddress) {
   	String  locationName = "";
   	
   	try {
   		
   		if (upperLevelCommitteeId != null && userAddress != null) {
   			
   			if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_MANDAL_LEVEl_ID)) {
   				locationName = userAddress.getTehsil().getTehsilName() + "Mandal";
   			} else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_VILLAGE_LEVEl_ID)) {
   				locationName = userAddress.getPanchayat().getPanchayatName()+ " Village";
   			}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_MUNCIPLITY_LEVEl_ID)) {
   				locationName = userAddress.getLocalElectionBody().getName() + "Local Election Body";
   			}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_WARD_LEVEl_ID)) {
   				locationName = userAddress.getWard().getName() + " Ward";
   			}else if (upperLevelCommitteeId .equals(IConstants.DIVISION_COMMITTEE_LEVEL_ID)) {
   				locationName = userAddress.getWard().getName() + " Division";
   			}else if (upperLevelCommitteeId.equals(IConstants.STATE_LEVEl_ACCESS_ID)) {
   				
   				locationName = userAddress.getState().getStateName()+ " State";
   			}else if (upperLevelCommitteeId.equals(IConstants.DISTRICT_LEVEl_ACCESS_ID)) {
   				locationName = userAddress.getDistrict().getDistrictName()+ " District";
   			}else if (upperLevelCommitteeId.equals( IConstants.REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID)) {
   				locationName = userAddress.getConstituency().getName()+ " Constituency";
   			}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_COUNTRY_LEVEl_ID)) {
   				locationName = userAddress.getCountry().getCountryName()+ " Country";
   			}else if (upperLevelCommitteeId.equals(IConstants.REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID)) {
   				locationName = userAddress.getParliamentConstituency().getName()+ " Parliament ";
   			}
   		}
   		
   	} catch (Exception e) {
   		LOG.error("Exception occurred at getLocationName() of PartyMeetingMOMService class ",e);
   	}
   	return locationName;
   }
}