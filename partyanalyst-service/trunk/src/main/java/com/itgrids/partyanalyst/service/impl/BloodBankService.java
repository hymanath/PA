package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAcceptanceStatusDAO;
import com.itgrids.partyanalyst.dao.IBloodBagQuantityDAO;
import com.itgrids.partyanalyst.dao.IBloodBagTypeDAO;
import com.itgrids.partyanalyst.dao.IBloodComponentDAO;
import com.itgrids.partyanalyst.dao.IBloodDonationAgeGroupDAO;
import com.itgrids.partyanalyst.dao.IBloodDonationCampDAO;
import com.itgrids.partyanalyst.dao.IBloodDonationDAO;
import com.itgrids.partyanalyst.dao.IBloodDonorInfoDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.BloodBankDashBoardVO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.dto.DonationsInBloodBankVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AcceptanceStatus;
import com.itgrids.partyanalyst.model.BloodBagQuantity;
import com.itgrids.partyanalyst.model.BloodBagType;
import com.itgrids.partyanalyst.model.BloodComponent;
import com.itgrids.partyanalyst.model.BloodDonation;
import com.itgrids.partyanalyst.model.BloodDonationAgeGroup;
import com.itgrids.partyanalyst.model.BloodDonorInfo;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.service.IBloodBankService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class BloodBankService implements IBloodBankService{

	private static final Logger   LOG = Logger.getLogger(BloodBankService.class);

	private IOccupationDAO occupationDAO;
	private IEducationalQualificationsDAO educationalQualificationsDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private IBloodDonationDAO bloodDonationDAO;
	private TransactionTemplate transactionTemplate;
	private IBloodDonorInfoDAO bloodDonorInfoDAO;
	private IBloodDonationAgeGroupDAO bloodDonationAgeGroupDAO;
	private IBloodDonationCampDAO bloodDonationCampDAO;
	private IAcceptanceStatusDAO acceptanceStatusDAO;
	private IBloodBagTypeDAO bloodBagTypeDAO;
	private IBloodBagQuantityDAO bloodBagQuantityDAO;
	private IBloodComponentDAO bloodComponentDAO;
	private CommonMethodsUtilService commonMethodsUtilService; 
	private IDistrictDAO districtDAO;
	private DateUtilService dateUtilService;
	
	
	
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public void setBloodDonationAgeGroupDAO(
			IBloodDonationAgeGroupDAO bloodDonationAgeGroupDAO) {
		this.bloodDonationAgeGroupDAO = bloodDonationAgeGroupDAO;
	}

	public void setBloodDonorInfoDAO(IBloodDonorInfoDAO bloodDonorInfoDAO) {
		this.bloodDonorInfoDAO = bloodDonorInfoDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setBloodDonationDAO(IBloodDonationDAO bloodDonationDAO) {
		this.bloodDonationDAO = bloodDonationDAO;
	}

	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}

	public IEducationalQualificationsDAO getEducationalQualificationsDAO() {
		return educationalQualificationsDAO;
	}

	public void setEducationalQualificationsDAO(
			IEducationalQualificationsDAO educationalQualificationsDAO) {
		this.educationalQualificationsDAO = educationalQualificationsDAO;
	}
	
	public IBloodDonationCampDAO getBloodDonationCampDAO() {
		return bloodDonationCampDAO;
	}

	public void setBloodDonationCampDAO(IBloodDonationCampDAO bloodDonationCampDAO) {
		this.bloodDonationCampDAO = bloodDonationCampDAO;
	}

	public IAcceptanceStatusDAO getAcceptanceStatusDAO() {
		return acceptanceStatusDAO;
	}

	public void setAcceptanceStatusDAO(IAcceptanceStatusDAO acceptanceStatusDAO) {
		this.acceptanceStatusDAO = acceptanceStatusDAO;
	}

	public IBloodBagTypeDAO getBloodBagTypeDAO() {
		return bloodBagTypeDAO;
	}

	public void setBloodBagTypeDAO(IBloodBagTypeDAO bloodBagTypeDAO) {
		this.bloodBagTypeDAO = bloodBagTypeDAO;
	}
	

	public IBloodBagQuantityDAO getBloodBagQuantityDAO() {
		return bloodBagQuantityDAO;
	}

	public void setBloodBagQuantityDAO(IBloodBagQuantityDAO bloodBagQuantityDAO) {
		this.bloodBagQuantityDAO = bloodBagQuantityDAO;
	}
	
   public IBloodComponentDAO getBloodComponentDAO() {
		return bloodComponentDAO;
	}

	public void setBloodComponentDAO(IBloodComponentDAO bloodComponentDAO) {
		this.bloodComponentDAO = bloodComponentDAO;
	}
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public List<BloodBankVO> getOccupationList() {
		
		List<BloodBankVO> occuList=new ArrayList<BloodBankVO>(0);
		try{
		  List<Occupation> rtrnOccuptnLst=occupationDAO.getAll();
		  if(rtrnOccuptnLst!=null && rtrnOccuptnLst.size()>0){
			  for (Occupation occupation : rtrnOccuptnLst) {
				BloodBankVO occupationVO=new BloodBankVO();
				occupationVO.setId(occupation.getOccupationId());
				occupationVO.setName(occupation.getOccupation());
				occuList.add(occupationVO);
			}
		  }
		}catch(Exception e){
		LOG.info("Error raised at getOccupationList() BloodBankService in ",e);	
		}
		return occuList;
	}

	public List<BloodBankVO> getEducationalQualificationsList() {
		
		List<BloodBankVO> eductnQulfctnLst=new ArrayList<BloodBankVO>(0);
		try{
		 List<EducationalQualifications> rtrnEdctnQulfctnList=educationalQualificationsDAO.getAll();
		 if(rtrnEdctnQulfctnList!=null && rtrnEdctnQulfctnList.size()>0){
			 for (EducationalQualifications edctnlQlfctns : rtrnEdctnQulfctnList) {
				 BloodBankVO edctnQulfctnVO=new BloodBankVO();
				  edctnQulfctnVO.setId(edctnlQlfctns.getEduQualificationId());
				  edctnQulfctnVO.setName(edctnlQlfctns.getQualification());
				  eductnQulfctnLst.add(edctnQulfctnVO);
			}
		 }
		}catch(Exception e) {
			LOG.info("Error raised at getEducationalQualificationsList() BloodBankService in ",e);			
		}
		return eductnQulfctnLst;
	}
	
	public BloodBankVO getCadreDetails(String memberShipNO){
          
		BloodBankVO cadreDtlsVO=new BloodBankVO();
		try {			
			String memberShipId=memberShipNO.trim();
            //Checking tdpCadreId avilable or not
		  Long tdpCadreId = tdpCadreDAO.getCadreIdByMemberShip(memberShipId);
		   if(tdpCadreId == null){
			 cadreDtlsVO.setStatus("not exist");
			 return cadreDtlsVO;
			}
			//0.bloodDonorInfo,1.donationsInBloodBank,2.donationsInOtherPlaces,3.lastDonationDate,4.bloodComponentId,5.component
			//,6.emergencyDonation,7.willingToCallDonation,8.remarks,9.donorAge,10.bloodDonationId
			List<Object[]> cadreDetailsObj = bloodDonationDAO.getCadreDetailsOfRegistered(memberShipId);
			
			if(cadreDetailsObj !=null && cadreDetailsObj.size()>0){			
				Object[] cadreDetails=cadreDetailsObj.get(0);
				setCadreDetailsOfDonor(cadreDetails,cadreDtlsVO);				
			}else{
				
				Object[] obj=tdpCadreDAO.getCadreDetailsByMmbrShpId(memberShipId);
				
				  if(obj!=null && obj.length>0){
					  
					  SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
					  
					  cadreDtlsVO.setId((Long)obj[0]);
					  String firstName=obj[1]!=null?obj[1].toString():" ";
					  String lastName=obj[2]!=null?obj[2].toString():" ";
					  cadreDtlsVO.setName(firstName+" "+lastName);
					  cadreDtlsVO.setRelativeName(obj[3]!=null?obj[3].toString():" ");
					  cadreDtlsVO.setMobile(obj[4]!=null?obj[4].toString():" ");
					  cadreDtlsVO.setEmail(obj[5]!=null?obj[5].toString():" ");
					  cadreDtlsVO.setSex(obj[6]!=null?obj[6].toString():" ");
					  cadreDtlsVO.setAge(obj[7]!=null?(Long)obj[7]:0l);
					  if(obj[8]!=null){
						  cadreDtlsVO.setDob(sdf.format((Date)obj[8]));
					  }
					  cadreDtlsVO.setEducationId(obj[9]!=null? (Long)obj[9]:0l);
					  cadreDtlsVO.setOccupationId(obj[10]!=null? (Long)obj[10]:0l);
					  
					  //cadre address For Address 
					  cadreDtlsVO.getAddressVO().setStateId(obj[11]!=null?(Long)obj[11]:0l);
					  cadreDtlsVO.getAddressVO().setStateName(obj[12]!=null?obj[12].toString():"");
					  cadreDtlsVO.getAddressVO().setDistrictId(obj[13]!=null?(Long)obj[13]:0l);
					  cadreDtlsVO.getAddressVO().setDistrictName(obj[14]!=null?obj[14].toString():" ");
					  cadreDtlsVO.getAddressVO().setConstituencyId(obj[15]!=null?(Long)obj[15]:0l);
					  cadreDtlsVO.getAddressVO().setConstituencyName(obj[16]!=null?obj[16].toString():" ");
					  if(obj[17]!=null){
						   String  tehsilId="1"+obj[17].toString();
						  cadreDtlsVO.getAddressVO().setTehsilId(Long.valueOf(tehsilId));
					  }
					  cadreDtlsVO.getAddressVO().setTehsilName(obj[18]!=null?obj[18].toString():" ");
					  cadreDtlsVO.getAddressVO().setWardId(obj[19]!=null?(Long)obj[19]:0l);
					  cadreDtlsVO.getAddressVO().setWardName(obj[20]!=null?obj[20].toString():" ");
					  cadreDtlsVO.getAddressVO().setPanchaytId(obj[21]!=null?(Long)obj[21]:0l);
					  cadreDtlsVO.getAddressVO().setPanchayatName(obj[22]!=null?obj[22].toString():" ");
					  if(obj[23]!=null){
						  String localElectionBodyId="2"+obj[23].toString();
						  cadreDtlsVO.getAddressVO().setLocalElectionBodyId(Long.valueOf(localElectionBodyId));
					  }
					  cadreDtlsVO.getAddressVO().setLocalElectionBodyName(obj[24]!=null?obj[24].toString():" ");					  
					  
					  cadreDtlsVO.setAddress(obj[12].toString()+ " State , " + obj[14].toString()+ " District ," +obj[16].toString() + " Constituency ");
					  cadreDtlsVO.setAlreadyDonated(false);

				  }
				
			}
			
		} catch (Exception e) {
			LOG.info("Error raised at getCadreDetails() BloodBankService in ",e);	
		}
		return cadreDtlsVO;
	}
	
	public void setCadreDetailsOfDonor(Object[] cadre,BloodBankVO cadreDtlsVO){
		try{
			
			//0.bloodDonorInfo,1.donationsInBloodBank,2.donationsInOtherPlaces,3.lastDonationDate,4.bloodComponentId,5.component
			//,6.emergencyDonation,7.willingToCallDonation,8.remarks,9.donorAge
						
			if(cadre !=null && cadre.length>0){
			
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				 
				BloodDonorInfo bloodDonorInfo = (BloodDonorInfo)cadre[0];				
				cadreDtlsVO.setName(bloodDonorInfo.getDonorName() !=null ? bloodDonorInfo.getDonorName().toString():"");
				cadreDtlsVO.setRelativeName(bloodDonorInfo.getRelativeName() !=null ? bloodDonorInfo.getRelativeName().toString():"");
				cadreDtlsVO.setSex(bloodDonorInfo.getGender() !=null ? bloodDonorInfo.getGender().toString():"");
				cadreDtlsVO.setAge(bloodDonorInfo.getAge() !=null ? bloodDonorInfo.getAge():0l);
				if(bloodDonorInfo.getDateOfBirth()!=null){
					cadreDtlsVO.setDob(sdf.format(bloodDonorInfo.getDateOfBirth()));
				}
				cadreDtlsVO.setMarried(bloodDonorInfo.getMaritalStatus() !=null ? bloodDonorInfo.getMaritalStatus():"");
				cadreDtlsVO.setMobile(bloodDonorInfo.getMobileNo() !=null ? bloodDonorInfo.getMobileNo():"");
				cadreDtlsVO.setEmail(bloodDonorInfo.getEmail() !=null ? bloodDonorInfo.getEmail():"");
				cadreDtlsVO.setAddress(bloodDonorInfo.getAddress() !=null ? bloodDonorInfo.getAddress():"");
				cadreDtlsVO.setEducationId(bloodDonorInfo.getEducationId() !=null ? bloodDonorInfo.getEducationId():0l);
				cadreDtlsVO.setOccupationId(bloodDonorInfo.getOccupationId() !=null ? bloodDonorInfo.getOccupationId():0l);
				cadreDtlsVO.setDonationsInBloodBank(cadre[1] !=null ? (Long)cadre[1]:0l);
				cadreDtlsVO.setDonationsInOtherPlaces(cadre[2] !=null ? cadre[2].toString():"");
				if(cadre[3] !=null){
					cadreDtlsVO.setLastDonation(sdf.format((Date)cadre[3]));
				}
				cadreDtlsVO.setBloodComponentId(cadre[4] !=null ? (Long)cadre[4]:0l);//Donation Block
				cadreDtlsVO.setBloodComponent(cadre[5] !=null ? cadre[5].toString():"");//Donation Block
				cadreDtlsVO.setWillingEmergencyDonation(cadre[6] !=null ? cadre[6].toString():"");
				cadreDtlsVO.setWillingToCallDonation(cadre[7] !=null ? cadre[7].toString():"");
				cadreDtlsVO.setRemarks(cadre[8] !=null ? cadre[8].toString():"");
				cadreDtlsVO.setDonorAge(cadre[9] !=null ? (Long)cadre[9]:0l);	
				cadreDtlsVO.setBloodDonationId(cadre[10] !=null ? (Long)cadre[10]:0l);
				cadreDtlsVO.setAlreadyDonated(true);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ResultStatus saveBloodBankCadreDetails(final BloodBankVO bloodBanKVO,final Long userId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			String memberShipId=bloodDonationDAO.isTdpCadreExistOrNot(bloodBanKVO.getMembershipNo().trim(),bloodBanKVO.getId());
		      if(memberShipId!=null){
		    	  resultStatus.setExceptionMsg("exist");
		    	  return resultStatus;
		      }
			  final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			resultStatus = (ResultStatus)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					
				    ResultStatus rs = new ResultStatus();
				    
				    DateUtilService presentDate = new DateUtilService();
			
				    //Saving Donor Formal Information
				    BloodDonorInfo bloodDonorInfo = new BloodDonorInfo();
				    
				    bloodDonorInfo.setDonorName(bloodBanKVO.getName());
				    bloodDonorInfo.setRelativeName(bloodBanKVO.getRelativeName());
				    bloodDonorInfo.setGender(bloodBanKVO.getSex());
				    bloodDonorInfo.setAge(bloodBanKVO.getAge());
				    if(bloodBanKVO.getDob()!=null && !bloodBanKVO.getDob().trim().isEmpty()){
				    	  try {
							bloodDonorInfo.setDateOfBirth(sdf.parse(bloodBanKVO.getDob()));
						} catch (ParseException e) {
							 e.printStackTrace();
						}
				    }
				    if(bloodBanKVO.getMarried() !=null && !bloodBanKVO.getMarried().trim().isEmpty() && bloodBanKVO.getMarried().trim() !="0"){
				    	bloodDonorInfo.setMaritalStatus(bloodBanKVO.getMarried());
				    }
				    
				    bloodDonorInfo.setMobileNo(bloodBanKVO.getMobile());
				    if(bloodBanKVO.getEmail() !=null && !bloodBanKVO.getEmail().trim().isEmpty())
				    	bloodDonorInfo.setEmail(bloodBanKVO.getEmail());
				    if(bloodBanKVO.getEducationId()!=null && bloodBanKVO.getEducationId()>0l){
				    	  bloodDonorInfo.setEducationId(bloodBanKVO.getEducationId());
				    }
				    if(bloodBanKVO.getOccupationId()!=null && bloodBanKVO.getOccupationId()>0l){
				    	  bloodDonorInfo.setOccupationId(bloodBanKVO.getOccupationId());
				    }
				    if(bloodBanKVO.getAddress() !=null && !bloodBanKVO.getAddress().trim().isEmpty()){
				    	bloodDonorInfo.setAddress(bloodBanKVO.getAddress());
				    }
				    
				    Long tdpCadreId = tdpCadreDAO.getCadreIdByMemberShip(bloodBanKVO.getMembershipNo().trim());				    
				    bloodDonorInfo.setTdpCadreId(tdpCadreId);				    
				    bloodDonorInfo.setInsertedTime(presentDate.getCurrentDateAndTime());
				    bloodDonorInfo.setUpdatedTime(presentDate.getCurrentDateAndTime());
				    bloodDonorInfo.setInsertedBy(userId);
				    bloodDonorInfo.setUpdatedBy(userId);
				    bloodDonorInfo.setIsDeleted("N");
				    BloodDonorInfo donorInfo = bloodDonorInfoDAO.save(bloodDonorInfo);
				    
				    //Saving Donor Blood Donation Details
				    
				    BloodDonation bloodDonation = new BloodDonation();
				    
				    
				    bloodDonation.setBloodDonationCampId(1l);
				    bloodDonation.setBloodDonorInfoId(donorInfo.getBloodDonorInfoId());
				    bloodDonation.setDonorAge(bloodBanKVO.getAge());
				    
				 //Blood Donation Age Group
				 List<BloodDonationAgeGroup> ageGroupObj =   bloodDonationAgeGroupDAO.getAllAgeGroups();
				 
					 if(ageGroupObj !=null && ageGroupObj.size()>0){
						 for (BloodDonationAgeGroup bloodDonationAgeGroup : ageGroupObj) {						 
							 Long minAge  = bloodDonationAgeGroup.getMainAge();
							 Long maxAge = bloodDonationAgeGroup.getMaxAge();						 
							 if(bloodBanKVO.getAge() !=null && bloodBanKVO.getAge()>=minAge && bloodBanKVO.getAge()<=maxAge ){							 
								 bloodDonation.setBloodDonationAgeGroup(bloodDonationAgeGroup);
							 }						 
						}
					 }
				 
				    bloodDonation.setDonationsInBloodBank(bloodBanKVO.getDonationsInBloodBank());
				    bloodDonation.setDonationsInOtherPlaces(bloodBanKVO.getDonationsInOtherPlaces());
				    if(bloodBanKVO.getLastDonation()!=null && !bloodBanKVO.getLastDonation().trim().isEmpty() && bloodBanKVO.getLastDonation().trim() !=""){
				    	 try {
							bloodDonation.setLastDonationDate(sdf.parse(bloodBanKVO.getLastDonation()));
						} catch (ParseException e) {
							e.printStackTrace();
						}
				    }
				    if(bloodBanKVO.getBloodComponentId()!=null && bloodBanKVO.getBloodComponentId()>0l){
				    	  bloodDonation.setBloodComponentId(bloodBanKVO.getBloodComponentId());			
				    }
				    bloodDonation.setWillingToCallDonation(bloodBanKVO.getWillingToCallDonation());
				    bloodDonation.setEmergencyDonation(bloodBanKVO.getWillingEmergencyDonation());
				    if(bloodBanKVO.getRemarks() !=null && !bloodBanKVO.getRemarks().trim().isEmpty()){
				    	bloodDonation.setRemarks(bloodBanKVO.getRemarks());
				    }
				    
				    bloodDonation.setAcceptanceStatusId(1l);
				  /*  String dateArr[]=presentDate.getCurrentDateInStringFormatYYYYMMDD().split("-");
				    Long year = Long.valueOf(dateArr[0]);
					Long month = Long.valueOf(dateArr[1]);
					Long date = Long.valueOf(dateArr[2]);
					 String dateFormat = month + "/" + date +"/"+year;
				  	try {
				  	bloodDonation.setDonationDate(sdf.parse(dateFormat));
					} catch (ParseException e) {
					    e.printStackTrace();
					}*/
				     bloodDonation.setInsertedTime(presentDate.getCurrentDateAndTime());
				    bloodDonation.setUpdatedTime(presentDate.getCurrentDateAndTime());
				    bloodDonation.setInsertedBy(userId);
				    bloodDonation.setUpdatedBy(userId);
				    
				   bloodDonation =  bloodDonationDAO.save(bloodDonation);
				   
				    rs.setExceptionMsg("success");
				    rs.setResultCode(0);
				    rs.setResultState(bloodDonation.getBloodDonationId());
					return rs;
					 
				 }
				});
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultStatus;
	}
	
	public ResultStatus updatePrintstatus(Long id,Long userId)
	{
		ResultStatus rs = new ResultStatus();
		try{
			DateUtilService presentDate = new DateUtilService();
			BloodDonation bloodDonation = bloodDonationDAO.get(id);
			bloodDonation.setPrintStatus("Y");
			bloodDonation.setPrintTime(presentDate.getCurrentDateAndTime());
			bloodDonation.setUpdatedBy(userId);
			bloodDonation.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			
			bloodDonationDAO.save(bloodDonation);
			rs.setResultCode(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			rs.setResultCode(1);
		}
		return rs;
	}
	
	/*public BloodBankDashBoardVO getBloodDonarsSummary(Long campId){
		try {
			bloodDonationCampDAO.getCampDates(campId);
		} catch (Exception e) {
			LOG.error("Exception riased at getBloodDonarsSummary", e);
		}
		return null;
	}*/
	
	public BloodBankDashBoardVO gettotalCollectedBloodDetails(Long campId){
		BloodBankDashBoardVO returnvo = new BloodBankDashBoardVO();
		try {
			Long totalBlood = 0l;
			Map<String,BloodBankDashBoardVO> dateMap = new LinkedHashMap<String, BloodBankDashBoardVO>();
			Object[] campDates = bloodDonationCampDAO.getCampDates(campId);
			if(campDates != null){
				
				List<Date> betweenDates= commonMethodsUtilService.getBetweenDates((Date)campDates[0], (Date)campDates[1]);
				if(betweenDates != null){
					for(Date date :betweenDates){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						BloodBankDashBoardVO vo1 = new BloodBankDashBoardVO();
						String dateStr = sdf.format(date);
						vo1.setDate(dateStr);
						dateMap.put(dateStr, vo1);
					}
				}
				
				//0.bloodCount,1.date
				List<Object[]> list = bloodDonationDAO.gettotalCollectedBloodDetails((Date)campDates[0], (Date)campDates[1]);
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						Long collectedBlood = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						String date = obj[1] != null ? obj[1].toString():"";
						
						BloodBankDashBoardVO vo = dateMap.get(date);
						if(vo == null){
							vo = new BloodBankDashBoardVO();
							vo.setTotalBlood(collectedBlood);
							//vo.setDate(date);
							//dateMap.put(date, vo);
						}
						else{
							vo.setTotalBlood(collectedBlood);
							//vo.setDate(date);
						}
						totalBlood = totalBlood+collectedBlood;
						//returnvo.getSubList().add(vo);
					}
				}
				
				returnvo.getSubList().addAll(dateMap.values());
				returnvo.setTotalBlood(totalBlood);
			}
		} catch (Exception e) {
			LOG.error("Exception riased at gettotalCollectedBloodDetails", e);
		}
		return returnvo;
	}
	
	public Long getBloodDonatedOtherThanBloodBank(){
		Long donatedInOtherBanks = 0l;
		try {
			donatedInOtherBanks = bloodDonationDAO.getBloodDonatedOtherThanBloodBank();
		} catch (Exception e) {
			LOG.error("Exception riased at getBloodDonatedOtherThanBloodBank", e);
		}
		return donatedInOtherBanks;
	}
	
	public Long getBloodDonorInEmergency(){
		Long emergencyDonors = 0l;
		try {
			emergencyDonors = bloodDonationDAO.getBloodDonarCountInEmergency();
		} catch (Exception e) {
			LOG.error("Exception riased at getBloodDonorInEmergency", e);
		}
		return emergencyDonors;
	}
	
	public Long getCalledForDonationCount(){
		Long count = 0l;
		try {
			count = bloodDonationDAO.getCalledForDonationCount();
		} catch (Exception e) {
			LOG.error("Exception riased at getCalledForDonationCount", e);
		}
		return count;
	}
	
	public Map<Long,BloodBankDashBoardVO> getBloodBankQuantityMap(){
		Map<Long,BloodBankDashBoardVO> quantityMap = new LinkedHashMap<Long, BloodBankDashBoardVO>();
		List<BloodBagQuantity> quantityList = bloodBagQuantityDAO.getAll();
		if(quantityList != null && quantityList.size() > 0){
			for (BloodBagQuantity bloodBagQuantity : quantityList) {
				BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
				
				vo.setBloodBagQuantityId(bloodBagQuantity.getBloodBagQuantityId());
				vo.setQuantityType(bloodBagQuantity.getType());
				
				quantityMap.put(bloodBagQuantity.getBloodBagQuantityId(), vo);
			}
		}
		return quantityMap;
	}
	
	public BloodBankDashBoardVO gettotalCollectedBloodBagsInfo(Long campId){
		BloodBankDashBoardVO returnvo = new BloodBankDashBoardVO();
		try {
			
			Map<Long,Map<Long,BloodBankDashBoardVO>> finalMap = new LinkedHashMap<Long, Map<Long,BloodBankDashBoardVO>>(0);
			
			List<BloodBagType> bagTypeList = bloodBagTypeDAO.getAll();
			if(bagTypeList != null && bagTypeList.size() > 0){
				for (BloodBagType bloodBagType : bagTypeList) {
					finalMap.put(bloodBagType.getBloodBagTypeId(), getBloodBankQuantityMap());
				}
			}
			//0.count,1.bloodBagTypeId,2.bagType,3.bloodBagQuantityId,4.quantityType,5.quantity
			List<Object[]> list = bloodDonationDAO.gettotalCollectedBloodBagsInfo(campId);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					Long bagTypeId = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
					Long bagQuantityId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
					Map<Long,BloodBankDashBoardVO> quantityMap = finalMap.get(bagTypeId);
					/*if(quantityMap == null){
						quantityMap = new LinkedHashMap<Long, BloodBankDashBoardVO>();
						List<BloodBagQuantity> quantityList = bloodBagQuantityDAO.getAll();
						if(quantityList != null && quantityList.size() > 0){
							for (BloodBagQuantity bloodBagQuantity : quantityList) {
								BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
								
								vo.setBloodBagQuantityId(bloodBagQuantity.getBloodBagQuantityId());
								vo.setQuantityType(bloodBagQuantity.getType());
								
								quantityMap.put(bloodBagQuantity.getBloodBagQuantityId(), vo);
							}
						}
						
						BloodBankDashBoardVO vo = quantityMap.get(bagQuantityId);
						if(vo != null){
							vo.setBloodBagTypeId(bagTypeId);
							vo.setBagType(obj[2] != null ? obj[2].toString():"");
							vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						}
						finalMap.put(bagTypeId, quantityMap);
					}*/
					if(quantityMap != null){
						BloodBankDashBoardVO vo = quantityMap.get(bagQuantityId);
						if(vo != null){
							vo.setBloodBagTypeId(bagTypeId);
							vo.setBagType(obj[2] != null ? obj[2].toString():"");
							vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						}
					}
				}
			}
			
			if(finalMap != null && finalMap.size() > 0){
				for (Long typeId : finalMap.keySet()){
					Map<Long,BloodBankDashBoardVO> quanMap = finalMap.get(typeId);
					List<BloodBankDashBoardVO> quanList = new ArrayList<BloodBankDashBoardVO>(quanMap.values());
					if(typeId == 1l)
						returnvo.getSingleBagList().addAll(quanList);
					else if(typeId == 2l)
						returnvo.getDoubleBagList().addAll(quanList);
					else if(typeId == 3l)
						returnvo.getTripleBagList().addAll(quanList);
					else if(typeId == 4l)
						returnvo.getQuadrupleList().addAll(quanList);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at gettotalCollectedBloodBagsInfo", e);
		}
		return returnvo;
	}
	
	public List<BloodBankDashBoardVO> setDatesList(Date fromDate, Date toDate){
		List<BloodBankDashBoardVO> datesList = new ArrayList<BloodBankDashBoardVO>();
		List<Date> betweenDates= commonMethodsUtilService.getBetweenDates(fromDate, toDate);
		if(betweenDates != null){
			for(Date date :betweenDates){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
				vo.setDate(sdf.format(date));
				datesList.add(vo);
			}
		}
		return datesList;
	}
	
	public List<BloodBankDashBoardVO> getBloodDonorDetailsByAgeGroupingInfo(Long campId){
		List<BloodBankDashBoardVO> returnList = new ArrayList<BloodBankDashBoardVO>();
		//List<BloodBankDashBoardVO> ageLst = new ArrayList<BloodBankDashBoardVO>();
		try {
			Map<String,List<BloodBankDashBoardVO>> ageGroupMap = new LinkedHashMap<String, List<BloodBankDashBoardVO>>();
			Object[] campDates = bloodDonationCampDAO.getCampDates(campId);
			List<BloodDonationAgeGroup> ageGrps = bloodDonationAgeGroupDAO.getAllAgeGroups();
			if(ageGrps != null && ageGrps.size() >0){
				for(BloodDonationAgeGroup agGrp : ageGrps){
					/*BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
					vo.setAgeGroupId(agGrp != null ? agGrp.getBloodDonationAgeGroupId().longValue() : 0l);
					vo.setAgeGroup(agGrp != null ? agGrp.getAgeGroup().toString() : "");
					vo.setCount(0l);
					ageLst.add(vo);*/
					//List<BloodBankDashBoardVO> voList = null;
					ageGroupMap.put(agGrp.getAgeGroup(), setDatesList((Date)campDates[0], (Date)campDates[1]));
				}
			}
			/*List<Date> betweenDates= commonMethodsUtilService.getBetweenDates((Date)campDates[0], (Date)campDates[1]);
			if(betweenDates != null){
				for(Date date :betweenDates){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					List<BloodBankDashBoardVO> voList = null;
					dateMap.put(sdf.format(date), voList);
				}
			}*/
			
			if(campDates != null){
				
				//0.count,1.ageGroupId,2.ageGroup,3.date
				List<Object[]> list = bloodDonationDAO.getBloodDonorDetailsByAgeGroupingInfo((Date)campDates[0], (Date)campDates[1]);
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						String ageGroup = obj[2] != null ? obj[2].toString():"0";
						String date = obj[3] != null ? obj[3].toString():"0";
						List<BloodBankDashBoardVO> voList = ageGroupMap.get(ageGroup);
						BloodBankDashBoardVO vo = getMatchedVOForAgeGroup(voList,date);
						if(vo!=null){
							vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							//returnList.add(vo);
						}
						/*if(voList == null){
							voList = new ArrayList<BloodBankDashBoardVO>();
							BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
							
							vo.setAgeGroupId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
							vo.setAgeGroup(obj[2] != null ? obj[2].toString():"");
							vo.setDate(date);
							vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							
							voList.add(vo);
							ageGroupMap.put(ageGroup, voList);
						}
						else{
							BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
							
							vo.setAgeGroupId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
							vo.setAgeGroup(obj[2] != null ? obj[2].toString():"");
							vo.setDate(date);
							vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
							
							voList.add(vo);
						}*/
					}
				}
				
				if(ageGroupMap != null && ageGroupMap.size() > 0){
					for (String ageGroupStr : ageGroupMap.keySet()){
						BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
						vo.setDate(ageGroupStr);
						vo.setSubList(ageGroupMap.get(ageGroupStr));
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at getBloodDonorDetailsByAgeGroupingInfo", e);
		}
		return returnList;
	}
	
	
	public List<IdNameVO> getAcceptanceStatus(){
		List<AcceptanceStatus> acceptanceStatusList = null;
		List<IdNameVO> idNameList = new ArrayList<IdNameVO>();
		IdNameVO idNameVO = null;
		try{
			LOG.info("Entered into getAcceptanceStatus() method of BloodBankService class");
			acceptanceStatusList = acceptanceStatusDAO.getAll();
			if(acceptanceStatusList!=null && acceptanceStatusList.size()>0){
				for(AcceptanceStatus acceptanceStatus:acceptanceStatusList){
					idNameVO = new IdNameVO();
					idNameVO.setId(acceptanceStatus.getAcceptanceStatusId());
					idNameVO.setName(acceptanceStatus.getStatus());
					idNameList.add(idNameVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception riased at getAcceptanceStatus() method of BloodBankService class", e);
		}
		return idNameList;
	}
	public List<IdNameVO> getBloodBagType(){
		List<BloodBagType> bloodBagTypeList = null;
		List<IdNameVO> idNameList = new ArrayList<IdNameVO>();
		IdNameVO idNameVO = null;
		try{
			LOG.info("Entered into getBloodBagType() method of BloodBankService class");
			bloodBagTypeList = bloodBagTypeDAO.getAll();
			if(bloodBagTypeList!=null && bloodBagTypeList.size()>0){
				for(BloodBagType bloodBagType:bloodBagTypeList){
					idNameVO = new IdNameVO();
					idNameVO.setId(bloodBagType.getBloodBagTypeId());
					idNameVO.setName(bloodBagType.getBagType());
					idNameList.add(idNameVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception riased at getBloodBagType() method of BloodBankService class", e);
		}
		return idNameList;
	}
	public List<IdNameVO> getBloodBagQuantity(){
		List<BloodBagQuantity> bloodBagQuantityList = null;
		List<IdNameVO> idNameList = new ArrayList<IdNameVO>();
		IdNameVO idNameVO = null;
		try{
			LOG.info("Entered into getBloodBagQuantity() method of BloodBankService class");
			bloodBagQuantityList = bloodBagQuantityDAO.getAll();
			if(bloodBagQuantityList!=null && bloodBagQuantityList.size()>0){
				for(BloodBagQuantity bloodBagQuantity:bloodBagQuantityList){
					idNameVO = new IdNameVO();
					idNameVO.setId(bloodBagQuantity.getBloodBagQuantityId());
					idNameVO.setName(bloodBagQuantity.getType());
					idNameList.add(idNameVO);
				}
			}
		}catch(Exception e){
			LOG.error("Exception riased at getBloodBagQuantity() method of BloodBankService class", e);
		}
		return idNameList;
	}
	public List<BloodBankVO> getBleedingCadreDetails(List<Long> statusIds,Long campId,String dates){
		
		List<BloodBankVO> finalList = new ArrayList<BloodBankVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			//0.tdpCadreId,1.memberShipNo,2.donorName,3.mobileNo,4.acceptanceStatusId,5.status,6.bagNo,
			//7.bloodBagTypeId,8.bagType,9.bloodBagQuantityId,10.type,11.quantity,12.remarks
			List<Date> datesList = new ArrayList<Date>(0);
			if(dates != null && !dates.trim().isEmpty()){
				String[] s = dates.split(",");

				if(s != null && s.length > 0){
					for(int i=0;i<s.length;i++){
						if(!s[i].equalsIgnoreCase("0")){
							datesList.add(sdf.parse(s[i]));
						}
					}
				}
			}
			
			
			List<Object[]> cadreObjList = bloodDonationDAO.getBleedingCadreDetails(statusIds, campId, datesList);			
			if(cadreObjList !=null && cadreObjList.size()>0){
				for (Object[] obj : cadreObjList) {					
					BloodBankVO vo = new BloodBankVO();					
					vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
					vo.setMembershipNo(obj[1] !=null ? obj[1].toString():"");
					vo.setName(obj[2] !=null ? obj[2].toString():"");
					vo.setMobile(obj[3] !=null ? obj[3].toString():"");
					vo.setStatusId(obj[4] !=null ? (Long)obj[4]:0l);
					vo.setStatus(obj[5] !=null ? obj[5].toString():"");
					vo.setBagNo(obj[6] !=null ? obj[6].toString():"");
					vo.setBagTypeId(obj[7] !=null ? (Long)obj[7]:0l);
					vo.setBagType(obj[8] !=null ? obj[8].toString():"");
					vo.setBloodBankQuantityId(obj[9] !=null ? (Long)obj[9]:0l);
					vo.setQuantityType(obj[10] !=null ? obj[10].toString():"");
					vo.setQuantity(obj[11] !=null ? (Long)obj[11]:0l);
					vo.setRemarks(obj[12] !=null ? obj[12].toString():"");
					vo.setRegistrationNo(obj[13]!=null?obj[13].toString():"");
					vo.setDistrictId(obj[14] !=null ? (Long)obj[14]:0l);
					vo.setDistrictName(obj[15]!=null?obj[15].toString():"");
					
					finalList.add(vo);
				}
			}
						
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finalList;
	}
	
	public BloodBankDashBoardVO getMatchedVOForAcceptanceStatus(List<BloodBankDashBoardVO> resultList,Long id)
	{
		if(resultList != null && resultList.size() > 0){
			for(BloodBankDashBoardVO vo : resultList){
				if(vo.getId().longValue() == id.longValue()){
					return vo;
				}
			}
		 }
		 return null;
	}
	
	public BloodBankDashBoardVO getBloodDonarsCountsSummary(Long campId){
		
		BloodBankDashBoardVO bldDntnVO = new BloodBankDashBoardVO();
		List<BloodBankDashBoardVO> mainList=new ArrayList<BloodBankDashBoardVO>();
		bldDntnVO.setBloodBankDashBoardVO(mainList);
		try {
			Object[] campDates = bloodDonationCampDAO.getCampDates(campId);
			if(campDates != null && campDates.length>0){
					Date campFromDate = (Date)(campDates[0]);
					Date campToDate = (Date)(campDates[1]);
					List<Date> betweenDates= commonMethodsUtilService.getBetweenDates(campFromDate, campToDate);
					List<AcceptanceStatus> accStatusList = acceptanceStatusDAO.getAll();
					if(accStatusList != null && accStatusList.size() > 0){
						for (AcceptanceStatus acceptanceStatus : accStatusList) {
							BloodBankDashBoardVO Vo = new BloodBankDashBoardVO();
							Vo.setId(acceptanceStatus.getAcceptanceStatusId());
							Vo.setTotalCount(0l);
							Vo.setBloodBankDashBoardVO(setCampDates(betweenDates));
							mainList.add(Vo);
						}
					}
					
				List<Object[]> bldDnrCntsList = bloodDonationDAO.getBloodDonorCounts(campFromDate, campToDate);
				if(bldDnrCntsList != null && bldDnrCntsList.size()>0){
					for (Object[] obj : bldDnrCntsList) {
						Long id = Long.valueOf(obj[1] != null ? obj[1].toString():"0");
						Long count = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						BloodBankDashBoardVO Vo = getMatchedVOForAcceptanceStatus(mainList, id);
						if(Vo != null)
							Vo.setTotalCount(count);
						//Vo.setId(obj[1] != null ?(Long)obj[1]:0l);
						//Vo.setBloodBankDashBoardVO(setCampDates(betweenDates));
						//mainList.add(Vo);
					}
				}
				
				List<Object[]> dayWiseList = bloodDonationDAO.getBloodDonorDayWiseCounts(campFromDate, campToDate);
				if(dayWiseList != null && dayWiseList.size()>0){
					
					for (Object[] obj : dayWiseList) {
							BloodBankDashBoardVO Vo = getMatchedVO(mainList,(Long)obj[2]);
							if(Vo != null)
							{
								//BloodBankDashBoardVO dayVO = new BloodBankDashBoardVO();
								//dayVO.setDaySatus(obj[1] != null ? obj[1].toString() : "");
								BloodBankDashBoardVO dayVO = getMatchedVOForDate(Vo.getBloodBankDashBoardVO(),obj[1].toString());
								if(dayVO!=null){
								dayVO.setTotalCount(obj[0] != null ?(Long)obj[0]:0l);
								}
								//Vo.getBloodBankDashBoardVO().add(dayVO);
							}
					    }
				    
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getBloodDonarsCountsSummary", e);
		}
		return bldDntnVO;
	}
	public List<BloodBankDashBoardVO> setCampDates(List<Date> betweenDates){
		List<BloodBankDashBoardVO> list = new ArrayList<BloodBankDashBoardVO>();
		if(betweenDates != null){
			for(Date date : betweenDates){
				BloodBankDashBoardVO dayVO = new BloodBankDashBoardVO();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				dayVO.setDaySatus(sdf.format(date));
				list.add(dayVO);
			}
		}
		return list;
	}
	
	public BloodBankDashBoardVO getMatchedVOForDate(List<BloodBankDashBoardVO> resultList,String id)
	{
		if(resultList != null && resultList.size() > 0){
			for(BloodBankDashBoardVO vo : resultList){
				if(vo.getDaySatus().toString().equalsIgnoreCase(id)){
					return vo;
				}
			}
		 }
		 return null;
	}
	public BloodBankDashBoardVO getMatchedVO(List<BloodBankDashBoardVO> resultList,Long id)
	{
		if(resultList != null && resultList.size() > 0){
			for(BloodBankDashBoardVO vo : resultList){
				if(vo.getId().equals(id)){
					return vo;
				}
			}
		 }
		 return null;
	}
	public BloodBankDashBoardVO getMatchedVOForAgeGroup(List<BloodBankDashBoardVO> resultList,String id)
	{
		if(resultList != null && resultList.size() > 0){
			for(BloodBankDashBoardVO vo : resultList){
				if(vo.getDate().toString().equalsIgnoreCase(id.toString())){
					return vo;
				}
			}
		 }
		 return null;
	}
	public ResultStatus saveBleedingDetails(BloodBankVO bloodBankVO){
		ResultStatus finalStatus = new ResultStatus();
		try{			
			
			Long bloodDonationId = bloodDonationDAO.getBloodDonationIdByMemberShip(bloodBankVO.getMembershipNo());
			
			if(bloodDonationId !=null){
				bloodBankVO.setId(bloodDonationId);
			}
			
			int updatedCount = bloodDonationDAO.updateBloodDonationDetailsByMemberShip(bloodBankVO);	
			 if(updatedCount>0){
				 finalStatus.setMessage("success");
			 }
			
		}catch (Exception e) {
			LOG.info("Error raised at saveBleedingDetails() BloodBankService in ",e);	
			finalStatus.setMessage("error");
		}
		return finalStatus;
	}

	public List<BloodBankVO> getBloodComponentList() {
		
		List<BloodBankVO> bloodComponentList=new ArrayList<BloodBankVO>(0);
		try{
			List<BloodComponent> rtrnBldCmpnntLst=bloodComponentDAO.getAll();
			if(rtrnBldCmpnntLst!=null && rtrnBldCmpnntLst.size()>0){
				for (BloodComponent bloodComponent : rtrnBldCmpnntLst) {
					 BloodBankVO vo=new BloodBankVO();
					  vo.setId(bloodComponent.getBloodComponentId());
					  vo.setName(bloodComponent.getComponent());
					  bloodComponentList.add(vo);
				}
			}
		}catch (Exception e) {
			LOG.info("Error raised at getBloodComponentList() BloodBankService in ",e);	
		}
		return bloodComponentList;
	}
	
	public DonationsInBloodBankVO getNumberOfTimesCollectedBlood(Long campId){
		DonationsInBloodBankVO finalVO = new DonationsInBloodBankVO();
		try{	
			
			List<Object[]> collectedBlood = bloodDonationDAO.getNumberOfTimesCollectedBlood(campId);
			if(collectedBlood != null && collectedBlood.size() > 0){
				for(Object[] obj : collectedBlood){
					Long collectedcount = obj[1]!=null?(Long)obj[1]:0l;
					
					if(collectedcount >=1 && collectedcount<=2 ){
						finalVO.setZerototwo(finalVO.getZerototwo()+1);
					}else if (collectedcount >=3 && collectedcount<=5){
						finalVO.setThreetofive(finalVO.getThreetofive()+1);
					}else if(collectedcount >=6 && collectedcount<=10){
						finalVO.setSixtoten(finalVO.getSixtoten()+1);
					}else if(collectedcount >=11 && collectedcount<=15){
						finalVO.setEleventofiftheen(finalVO.getEleventofiftheen()+1);
					}else if(collectedcount >=16 && collectedcount<=30){
						finalVO.setSixteentothirteen(finalVO.getSixteentothirteen()+1);
					}else if(collectedcount >=31 && collectedcount<=50){
						finalVO.setThirtyonetofifthy(finalVO.getThirtyonetofifthy()+1);
					}
					//finalVO.setTdpcadreId(obj[0]!=null?(Long)obj[0]:0l);
				}
				
			}	
					
		}catch (Exception e) {
			e.printStackTrace();
		}
		return finalVO;
	}
	public List<BloodBankDashBoardVO> setAgeList(){
		List<BloodBankDashBoardVO> ageLst = new ArrayList<BloodBankDashBoardVO>();
		List<BloodDonationAgeGroup> ageGrps = bloodDonationAgeGroupDAO.getAllAgeGroups();
		if(ageGrps != null && ageGrps.size() >0){
			for(BloodDonationAgeGroup agGrp : ageGrps){
				BloodBankDashBoardVO vo = new BloodBankDashBoardVO();
				vo.setAgeGroupId(agGrp != null ? agGrp.getBloodDonationAgeGroupId().longValue() : 0l);
				vo.setAgeGroup(agGrp != null ? agGrp.getAgeGroup().toString() : "");
				vo.setCount(0l);
				ageLst.add(vo);
			}
		}
		return ageLst;
	}
	
	public BloodBankDashBoardVO getDistrictWiseBloodDonorCounts(Long campId,Long stateId,String type){
		BloodBankDashBoardVO finalResult = new BloodBankDashBoardVO();
		
		try{
			List<BloodBankDashBoardVO> districtwisebloodcountlist = new ArrayList<BloodBankDashBoardVO>(0);
			List<Object[]> allDistrictList = districtDAO.getDistrictIdAndNameByStateForRegion(stateId,type.toString());		
						
			if(allDistrictList !=null && allDistrictList.size()>0){
				for (Object[] objects : allDistrictList) {
					BloodBankDashBoardVO VO = new BloodBankDashBoardVO();
					
					VO.setId(objects[0] !=null ? (Long)objects[0]:0l);
					VO.setName(objects[1] !=null ? objects[1].toString():"");
					VO.setCount(0l);
					districtwisebloodcountlist.add(VO);
				}
			}

			List<Object[]> bloodCountList = bloodDonationDAO.getDistrictWiseBloodDonorCounts(campId);
				if(bloodCountList !=null && bloodCountList.size()>0){
					for (Object[] objects : bloodCountList) {
						BloodBankDashBoardVO VO = getMatchedVO(districtwisebloodcountlist,(Long)objects[0]);
						if(VO != null)
						{	
							VO.setCount(objects[2] !=null ? (Long)objects[2]:0l);
						}
						/*}else{
							BloodBankDashBoardVO VO1 = new BloodBankDashBoardVO();							
							VO1.setCount(objects[2] !=null ? (Long)objects[2]:0l);
							districtwisebloodcountlist.add(VO1);
						}*/
						
						
					}
					
				}
			
				//finalResult.getSubList().addAll(districtwisebloodcountlist);
				finalResult.setSubList(districtwisebloodcountlist);
				
		}catch (Exception e) {
			LOG.info("Error raised at getDistrictWiseBloodDonorCounts() BloodBankService in ",e);	
		}
		return finalResult;
	}
public List<BloodBankVO> getPrePopulateDataDetails(String searchType,Long statusId,String date){
		
		List<BloodBankVO> returnList = new ArrayList<BloodBankVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try{
			
			LOG.info(" Enterd into  getPrePopulateDataDetails() BloodBankService in ");
			
			List<Date> datesList = new ArrayList<Date>(0);
			if(date != null && !date.trim().isEmpty()){
				String[] s = date.split(",");
				
				for(int i=0;i<s.length;i++){
					if(!s[i].equalsIgnoreCase("0")){
						datesList.add(sdf.parse(s[i]));
					}
				}
			}
			
				
		List<Object[]> prepopulateLst = bloodDonationDAO.getThePrePopulateData(searchType,statusId,datesList);
			if(prepopulateLst != null && prepopulateLst.size()>0){
				for (Object[] obj : prepopulateLst) {
					BloodBankVO vo = new BloodBankVO();
					vo.setMembershipNo(obj[0] !=null ? obj[0].toString():"");
					vo.setName(obj[1] !=null ? obj[1].toString():"");
					vo.setMobile(obj[2] !=null ? obj[2].toString():"");
					vo.setStatusId(obj[3] !=null ? (Long)obj[3]:0l);
					vo.setBagNo(obj[4] !=null ? obj[4].toString():"");
					vo.setBagTypeId(obj[5] !=null ? (Long)obj[5]:0l);
					vo.setBloodBankQuantityId(obj[6] !=null ? (Long)obj[6]:0l);
					vo.setQuantity(obj[7] !=null ? (Long)obj[7]:0l);
					vo.setRegistrationNo(obj[8]!=null ? obj[8].toString():"");
					vo.setRemarks(obj[9]!=null ? obj[9].toString():"");
					vo.setDistrictId(obj[10] !=null ? (Long)obj[10]:0l);
					vo.setDistrictName(obj[11] !=null ? obj[11].toString():"");
					
					returnList.add(vo);					
				}
			}
						
		}catch (Exception e) {
			e.printStackTrace();
			LOG.info(" Exception raised at getPrePopulateDataDetails() BloodBankService in ",e);	
		}
		return returnList;
	}
	
	public List<IdNameVO> getBloodBankCampDates(Long campId){
		List<IdNameVO> voList = new ArrayList<IdNameVO>(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Object[] objArr = bloodDonationCampDAO.getCampDates(campId);
			if(objArr != null && objArr.length > 0){
				List<Date> datesList = commonMethodsUtilService.getBetweenDates((Date)objArr[0],(Date)objArr[1]);
				if(datesList != null && datesList.size() > 0){
					StringBuilder sb = new StringBuilder();
					for (int i=0;i<datesList.size();i++) {
						if(sdf.format(datesList.get(i)).split("-")[0].equalsIgnoreCase(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[0]) ){
							if(sdf.format(datesList.get(i)).split("-")[2].equalsIgnoreCase(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[2])){
								IdNameVO vo = new IdNameVO();
								vo.setName(sb.append(sdf.format(datesList.get(i))+",").toString());
								vo.setPercentage("toDay");
								voList.add(vo);
							} 
							else{
								IdNameVO vo = new IdNameVO();
								vo.setName(sb.append(sdf.format(datesList.get(i))+",").toString());
								voList.add(vo);
							}
						}else if(Long.parseLong(sdf.format(datesList.get(i)).split("-")[0])<Long.parseLong(sdf.format(dateUtilService.getCurrentDateAndTime()).split("-")[0])){
							IdNameVO vo = new IdNameVO();
							vo.setName(sb.append(sdf.format(datesList.get(i))+",").toString());
							if(i==0){
								vo.setPercentage("toDay");
							}
							voList.add(vo);
						}
					}
				}
							
			}
			
		} catch (Exception e) {
			LOG.error("Exception riased at getBloodBankCampDates", e);
		}
		return voList;
	}
}
