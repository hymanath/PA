package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBloodDonationAgeGroupDAO;
import com.itgrids.partyanalyst.dao.IBloodDonationDAO;
import com.itgrids.partyanalyst.dao.IBloodDonorInfoDAO;
import com.itgrids.partyanalyst.dao.IEducationalQualificationsDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.BloodBankDashBoardVO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.BloodDonation;
import com.itgrids.partyanalyst.model.BloodDonationAgeGroup;
import com.itgrids.partyanalyst.model.BloodDonorInfo;
import com.itgrids.partyanalyst.model.EducationalQualifications;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.service.IBloodBankService;
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

	@Override
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

	@Override
	public List<BloodBankVO> getEducationalQualificationsList() {
		
		List<BloodBankVO> eductnQulfctnLst=new ArrayList<BloodBankVO>(0);
		try{
		 List<EducationalQualifications> rtrnEdctnQulfctnList=educationalQualificationsDAO.getAll();
		 if(rtrnEdctnQulfctnList!=null && rtrnEdctnQulfctnList.size()>0){
			 for (EducationalQualifications edctnlQlfctns : rtrnEdctnQulfctnList) {
				 BloodBankVO edctnQulfctnVO=new BloodBankVO();
				  edctnQulfctnVO.setId(edctnlQlfctns.getId());
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
			//0.bloodDonorInfo,1.donationsInBloodBank,2.donationsInOtherPlaces,3.lastDonationDate,4.bloodComponentId,5.component
			//,6.emergencyDonation,7.willingToCallDonation,8.remarks,9.donorAge
			Object[] cadreDetails = bloodDonationDAO.getCadreDetailsOfRegistered(memberShipNO);
			
			if(cadreDetails !=null && cadreDetails.length>0){				
				setCadreDetailsOfDonor(cadreDetails,cadreDtlsVO);				
			}else{
				
				Object[] obj=tdpCadreDAO.getCadreDetailsByMmbrShpId(memberShipNO);
				
				  if(obj!=null && obj.length>0){
					  cadreDtlsVO.setId((Long)obj[0]);
					  String firstName=obj[1]!=null?obj[1].toString():" ";
					  String lastName=obj[2]!=null?obj[2].toString():" ";
					  cadreDtlsVO.setName(firstName+" "+lastName);
					  cadreDtlsVO.setRelativeName(obj[3]!=null?obj[3].toString():" ");
					  cadreDtlsVO.setMobile(obj[4]!=null?obj[4].toString():" ");
					  cadreDtlsVO.setEmail(obj[5]!=null?obj[5].toString():" ");
					  cadreDtlsVO.setSex(obj[6]!=null?obj[6].toString():" ");
					  cadreDtlsVO.setAge(obj[7]!=null?(Long)obj[7]:0l);
					  cadreDtlsVO.setDob(obj[8]!=null?obj[8].toString():" ");
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
				BloodDonorInfo bloodDonorInfo = (BloodDonorInfo)cadre[0];				
				cadreDtlsVO.setName(bloodDonorInfo.getDonorName() !=null ? bloodDonorInfo.getDonorName().toString():"");
				cadreDtlsVO.setRelativeName(bloodDonorInfo.getRelativeName() !=null ? bloodDonorInfo.getRelativeName().toString():"");
				cadreDtlsVO.setSex(bloodDonorInfo.getGender() !=null ? bloodDonorInfo.getGender().toString():"");
				cadreDtlsVO.setAge(bloodDonorInfo.getAge() !=null ? bloodDonorInfo.getAge():0l);
				cadreDtlsVO.setDob(bloodDonorInfo.getDateOfBirth() !=null ? bloodDonorInfo.getDateOfBirth():"");
				cadreDtlsVO.setMarried(bloodDonorInfo.getMaritalStatus() !=null ? bloodDonorInfo.getMaritalStatus():"");
				cadreDtlsVO.setMobile(bloodDonorInfo.getMobileNo() !=null ? bloodDonorInfo.getMobileNo():"");
				cadreDtlsVO.setEmail(bloodDonorInfo.getEmail() !=null ? bloodDonorInfo.getEmail():"");
				cadreDtlsVO.setAddress(bloodDonorInfo.getAddress() !=null ? bloodDonorInfo.getAddress():"");
				cadreDtlsVO.setEducationId(bloodDonorInfo.getEducationId() !=null ? bloodDonorInfo.getEducationId():0l);
				cadreDtlsVO.setOccupationId(bloodDonorInfo.getOccupationId() !=null ? bloodDonorInfo.getOccupationId():0l);
				cadreDtlsVO.setDonationsInBloodBank(cadre[1] !=null ? (Long)cadre[1]:0l);
				cadreDtlsVO.setDonationsInOtherPlaces(cadre[2] !=null ? cadre[2].toString():"");
				cadreDtlsVO.setLastDonation(cadre[3] !=null ? cadre[3].toString():"");
				cadreDtlsVO.setBloodComponentId(cadre[4] !=null ? (Long)cadre[4]:0l);//Donation Block
				cadreDtlsVO.setBloodComponent(cadre[5] !=null ? cadre[5].toString():"");//Donation Block
				cadreDtlsVO.setWillingEmergencyDonation(cadre[6] !=null ? cadre[6].toString():"");
				cadreDtlsVO.setWillingToCallDonation(cadre[7] !=null ? cadre[7].toString():"");
				cadreDtlsVO.setRemarks(cadre[8] !=null ? cadre[8].toString():"");
				cadreDtlsVO.setDonorAge(cadre[9] !=null ? (Long)cadre[9]:0l);			
				cadreDtlsVO.setAlreadyDonated(true);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private ResultStatus saveBloodBankCadreDetails(final BloodBankVO bloodBanKVO,final Long userId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			
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
				    bloodDonorInfo.setDateOfBirth(bloodBanKVO.getDob());
				    bloodDonorInfo.setMaritalStatus(bloodBanKVO.getMarried());
				    bloodDonorInfo.setMobileNo(bloodBanKVO.getMobile());
				    bloodDonorInfo.setEmail(bloodBanKVO.getEmail());
				    bloodDonorInfo.setEducationId(bloodBanKVO.getEducationId());
				    bloodDonorInfo.setOccupationId(bloodBanKVO.getOccupationId());
				    bloodDonorInfo.setAddress(bloodBanKVO.getAddress());				    
				   Long tdpCadreId = tdpCadreDAO.getCadreIdByMemberShip(bloodBanKVO.getMembershipNo());				    
				    bloodDonorInfo.setTdpCadreId(tdpCadreId);				    
				    bloodDonorInfo.setInsertedTime(presentDate.getCurrentDateAndTime());
				    bloodDonorInfo.setUpdatedTime(presentDate.getCurrentDateAndTime());
				    bloodDonorInfo.setInsertedBy(userId);
				    bloodDonorInfo.setUpdatedBy(userId);
				    
				    BloodDonorInfo donorInfo = bloodDonorInfoDAO.save(bloodDonorInfo);
				    
				    //Saving Donor Blood Donation Details
				    
				    BloodDonation bloodDonation = new BloodDonation();
				    
				    
				    bloodDonation.setBloodDonationCampId(bloodBanKVO.getBloodDonationCampId());
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
				    bloodDonation.setLastDonationDate(bloodBanKVO.getLastDonation());
				    bloodDonation.setBloodComponentId(bloodBanKVO.getBloodComponentId());			
				    bloodDonation.setWillingToCallDonation(bloodBanKVO.getWillingToCallDonation());
				    bloodDonation.setEmergencyDonation(bloodBanKVO.getWillingEmergencyDonation());
				    bloodDonation.setRemarks(bloodBanKVO.getRemarks());
				    bloodDonation.setAcceptanceStatusId(1l);
				    bloodDonation.setInsertedTime(presentDate.getCurrentDateAndTime());
				    bloodDonation.setUpdatedTime(presentDate.getCurrentDateAndTime());
				    bloodDonation.setInsertedBy(userId);
				    bloodDonation.setUpdatedBy(userId);
				    
				    bloodDonationDAO.save(bloodDonation);
				    
					return rs;
					 
				 }
				});
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultStatus;
	}
	
	public BloodBankDashBoardVO getBloodDonarsSummary(){
		try {
			
		} catch (Exception e) {
			LOG.error("Exception riased at getBloodDonarsSummary", e);
		}
		return null;
	}
}
