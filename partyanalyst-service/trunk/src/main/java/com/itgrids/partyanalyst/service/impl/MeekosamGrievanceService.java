package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMeekosamAnnualIncomeDAO;
import com.itgrids.partyanalyst.dao.IMeekosamArgeeCategoryDAO;
import com.itgrids.partyanalyst.dao.IMeekosamCasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IMeekosamOccupationDAO;
import com.itgrids.partyanalyst.dao.IMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PetitionerDetailsVO;
import com.itgrids.partyanalyst.model.MeekosamPetitioner;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IMeekosamGrievanceService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class MeekosamGrievanceService implements IMeekosamGrievanceService{

	private final static Logger LOG =  Logger.getLogger(MeekosamGrievanceService.class);
	private TransactionTemplate transactionTemplate = null;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IUserAddressDAO userAddressDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IConstituencyDAO constituencyDAO;
	private IHamletDAO hamletDAO;
	private IMeekosamOccupationDAO meekosamOccupationDAO;
	private IMeekosamCasteCategoryDAO meekosamCasteCategoryDAO;
	private IMeekosamArgeeCategoryDAO meekosamArgeeCategoryDAO;
	private IMeekosamAnnualIncomeDAO meekosamAnnualIncomeDAO;
	private IMeekosamPetitionerDAO meekosamPetitionerDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	public void setMeekosamPetitionerDAO(
			IMeekosamPetitionerDAO meekosamPetitionerDAO) {
		this.meekosamPetitionerDAO = meekosamPetitionerDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
	public void setMeekosamOccupationDAO(
			IMeekosamOccupationDAO meekosamOccupationDAO) {
		this.meekosamOccupationDAO = meekosamOccupationDAO;
	}
	public void setMeekosamCasteCategoryDAO(
			IMeekosamCasteCategoryDAO meekosamCasteCategoryDAO) {
		this.meekosamCasteCategoryDAO = meekosamCasteCategoryDAO;
	}
	public void setMeekosamArgeeCategoryDAO(
			IMeekosamArgeeCategoryDAO meekosamArgeeCategoryDAO) {
		this.meekosamArgeeCategoryDAO = meekosamArgeeCategoryDAO;
	}
	public void setMeekosamAnnualIncomeDAO(
			IMeekosamAnnualIncomeDAO meekosamAnnualIncomeDAO) {
		this.meekosamAnnualIncomeDAO = meekosamAnnualIncomeDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#saveMeekosamPetitionerDetails(com.itgrids.partyanalyst.dto.PetitionerDetailsVO)
	 */
	public String saveMeekosamPetitionerDetails(final PetitionerDetailsVO inputvo){
		String status = null;
		try {
			status = (String)transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					String status = null;
					try{
						DateUtilService date = new DateUtilService();
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						MeekosamPetitioner meekosamPetitioner = new MeekosamPetitioner();
						meekosamPetitioner.setName(inputvo.getName());
						meekosamPetitioner.setRelativeName(inputvo.getRelativeName());
						meekosamPetitioner.setAge(inputvo.getAge());
						Date dob = null;
						if(inputvo.getDateOfBirth() != null && !inputvo.getDateOfBirth().trim().isEmpty() && inputvo.getDateOfBirth().trim().length() > 0){
							dob = sdf.parse(inputvo.getDateOfBirth().trim());
						}
						meekosamPetitioner.setDateOfBirth(dob);
						meekosamPetitioner.setGender(inputvo.getGender());
						UserAddress userAddress = saveUserAddress(inputvo);
						meekosamPetitioner.setUserAddressId(userAddress.getUserAddressId());
						meekosamPetitioner.setHouseNo(inputvo.getHouseNo());
						meekosamPetitioner.setAadharNo(inputvo.getAadharNo());
						meekosamPetitioner.setVoterCardNo(inputvo.getVoterCardNo());
						meekosamPetitioner.setMobileNo(inputvo.getMobileNo());
						meekosamPetitioner.setEmailId(inputvo.getEmailId());
						meekosamPetitioner.setMeekosamOccupationId(inputvo.getMeekosamOccupationId());
						meekosamPetitioner.setMeekosamCasteCategoryId(inputvo.getMeekosamCasteCategoryId());
						meekosamPetitioner.setMeekosamArgeeCategoryId(inputvo.getMeekosamArgeeCategoryId());
						meekosamPetitioner.setMeekosamAnnualIncomeId(inputvo.getMeekosamAnnualIncomeId());
						meekosamPetitioner.setDuration(inputvo.getDuration());
						meekosamPetitioner.setInsertedBy(inputvo.getUserId());
						meekosamPetitioner.setUpdatedBy(inputvo.getUserId());
						meekosamPetitioner.setInsertedTime(date.getCurrentDateAndTime());
						meekosamPetitioner.setUpdatedTime(date.getCurrentDateAndTime());
						meekosamPetitioner.setIsDeleted("N");
						meekosamPetitionerDAO.save(meekosamPetitioner);
						status = "susscess";
						return status;
					}catch(Exception e){
						e.printStackTrace();
						status = "fail";
						return status;
					}
				}
			});
		} catch (Exception e) {
			LOG.error("Error occured saveMeekosamPetitionerDetails() method of MeekosamGrievanceService",e);
		}
		return status;
	}
	public UserAddress saveUserAddress(final PetitionerDetailsVO inputVO){	
		try{
			UserAddress userAddress = new UserAddress();
			userAddress.setState(stateDAO.get(1L));
			userAddress.setDistrict(districtDAO.get(inputVO.getDistrictId()));
			String flag = inputVO.getTehsil().trim().substring(0, 1);
			if(flag.equalsIgnoreCase("1")){
				userAddress.setTehsil(tehsilDAO.get(Long.parseLong(inputVO.getTehsil().trim().substring(1))));
				if(inputVO.getPanchayatId() != null){
					userAddress.setPanchayatId(inputVO.getPanchayatId());
				}
				if(inputVO.getHamletId() != null){
					userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
				}
			}else{
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.parseLong(inputVO.getTehsil().trim().substring(1))));
				if(inputVO.getPanchayatId() != null){
					userAddress.setWard(constituencyDAO.get(inputVO.getPanchayatId()));
				}
			}
			userAddress = userAddressDAO.save(userAddress);
			return userAddress;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#getAllMandalsByDistrictID(java.lang.Long)
	 */
	public List<IdNameVO> getAllMandalsByDistrictID(Long districtId){
		try{
			List<IdNameVO> returnList = new ArrayList<IdNameVO>();
			List<Object[]> mandals = constituencyDAO.getTehsilsByDistrict(districtId);
			if(mandals != null && mandals.size() > 0)
			{
				for(Object[] obj : mandals){
					IdNameVO objVO = new IdNameVO();
					String tehsilId = "1"+obj[0].toString();
					objVO.setId(Long.parseLong(tehsilId));
					objVO.setName(obj[1].toString() +" " +"Mandal");
					returnList.add(objVO);
				}
			}
			List<Object[]> localbodies = constituencyDAO.getLocalElectionBodiesByDistrict(districtId);
			if(localbodies != null && localbodies.size() > 0)
			{
					for(Object[] obj : localbodies){
						IdNameVO objVO = new IdNameVO();
						String locEleBodyId = "2"+obj[0].toString();
						objVO.setId(Long.parseLong(locEleBodyId));
						objVO.setName(obj[1].toString());
						returnList.add(objVO);
					}
			}
			return returnList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAllMandalsByDistrictID() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#getAllHamletByPanchayatID(java.lang.Long)
	 */
	public List<IdNameVO> getAllHamletByPanchayatID(Long panchayatId){
		try{
			List<IdNameVO> returnList = new ArrayList<IdNameVO>();
			List<Object[]> hamlets = constituencyDAO.getHamletByPanchayat(panchayatId);
			if(hamlets != null && hamlets.size() > 0)
			{
				for(Object[] obj : hamlets){
					IdNameVO objVO = new IdNameVO();
					objVO.setId((Long)obj[0]);
					objVO.setName(obj[1].toString());
					returnList.add(objVO);
				}
			}
			return returnList;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getAllHamletByPanchayatID() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#getMeekosamOccupationList()
	 */
	public List<IdAndNameVO> getMeekosamOccupationList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = meekosamOccupationDAO.getMeekosamOccupationList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMeekosamOccupationList() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#getMeekosamCasteCategoryList()
	 */
	public List<IdAndNameVO> getMeekosamCasteCategoryList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = meekosamCasteCategoryDAO.getMeekosamCasteCategoryList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMeekosamCasteCategoryList() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#getMeekosamArgeeCategoryList()
	 */
	public List<IdAndNameVO> getMeekosamArgeeCategoryList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = meekosamArgeeCategoryDAO.getmeekosamArgeeCategoryList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getmeekosamArgeeCategoryList() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#getMeekosamAnnualIncomeList()
	 */
	public List<IdAndNameVO> getMeekosamAnnualIncomeList(){   
		try{
			List<IdAndNameVO> resultList = new ArrayList<IdAndNameVO>();
			List<Object[]> rtrnObjList = meekosamAnnualIncomeDAO.getMeekosamAnnualIncomeList();
			setDataToList(rtrnObjList,resultList);
			return resultList;
		 }catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured getMeekosamAnnualIncomeList() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	public void setDataToList(List<Object[]> objList,List<IdAndNameVO> resultList){
    	try{
    		if(objList != null && objList.size() > 0){  
				for(Object[] param : objList){
				  IdAndNameVO	idAndNameVO = new IdAndNameVO();
					idAndNameVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					idAndNameVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					resultList.add(idAndNameVO);
				}
			}
    	}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured setDataToList() method of MeekosamGrievanceService",e);
    	}
    }
	/*
	 * Swadhin K Lenka
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IMeekosamGrievanceService#searchPetitionerDetailsByVoterNoAadharNoMobileNo(java.lang.String, java.lang.String)
	 */
	public List<PetitionerDetailsVO> searchPetitionerDetailsByVoterNoAadharNoMobileNo(String cardNo, String type){
		try{
			List<PetitionerDetailsVO> detailsVOs = new ArrayList<PetitionerDetailsVO>();
			if(type != null && !type.trim().isEmpty() && type.trim().equalsIgnoreCase("voter")){
				serchByVoterCardNo(detailsVOs,cardNo,type);
			}else{
				serchByMobileNoAadharNo(detailsVOs,cardNo,type);
			}
			return detailsVOs;
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error occured searchPetitionerDetailsByVoterNoAadharNoMobileNo() method of MeekosamGrievanceService",e);
		}
		return null;
	}
	public void serchByMobileNoAadharNo(List<PetitionerDetailsVO> detailsVOs,String cardNo,String type){
		try{
			List<Object[]> petitionerDetailsList = meekosamPetitionerDAO.getPetitionerDetailsByCardNoMobileNoAadharNo(cardNo, type);
			prepareFinalList(petitionerDetailsList,detailsVOs);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void serchByVoterCardNo(List<PetitionerDetailsVO> detailsVOs,String cardNo,String type){
		try{
			PetitionerDetailsVO detailsVO = null;
			List<Object[]> petitionerDetailsList = meekosamPetitionerDAO.getPetitionerDetailsByCardNoMobileNoAadharNo(cardNo, type);
			if(petitionerDetailsList != null && petitionerDetailsList.size() > 0){
				prepareFinalList(petitionerDetailsList,detailsVOs);
			}else{
				petitionerDetailsList = boothPublicationVoterDAO.getVoterIdDetailsByPublicationIdAndCardNo(cardNo,IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
				if(petitionerDetailsList != null && petitionerDetailsList.size() > 0){
					for(Object[] param : petitionerDetailsList){
						detailsVO = new PetitionerDetailsVO();
						detailsVO.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
						detailsVO.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[8]));
						detailsVO.setGender(commonMethodsUtilService.getStringValueForObject(param[6]));
						detailsVO.setHouseNo(commonMethodsUtilService.getStringValueForObject(param[9]));
						detailsVO.setAadharNo(commonMethodsUtilService.getStringValueForObject(param[27]));
						detailsVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[25]));
						detailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[26]));
						detailsVO.setEmailId(commonMethodsUtilService.getStringValueForObject(param[28]));
						detailsVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
						if(param[17] != null){
							String tehsilId = "1"+commonMethodsUtilService.getStringValueForObject(param[17]);
							detailsVO.setTehsil(tehsilId);
						}else if(param[22] != null){
							String tehsilId = "2"+commonMethodsUtilService.getStringValueForObject(param[22]);
							detailsVO.setTehsil(tehsilId);
						}
						if(param[15] != null){
							detailsVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[15]));
						}else if(param[23] != null){
							detailsVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[23]));
						}
						detailsVOs.add(detailsVO);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void prepareFinalList(List<Object[]> petitionerDetailsList,List<PetitionerDetailsVO> detailsVOs){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			PetitionerDetailsVO detailsVO = null;
			if(petitionerDetailsList != null && petitionerDetailsList.size() > 0){
				for(Object[] param : petitionerDetailsList){
					detailsVO = new PetitionerDetailsVO();
					detailsVO.setPetitionerId(commonMethodsUtilService.getLongValueForObject(param[0]));
					detailsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					detailsVO.setAge(commonMethodsUtilService.getLongValueForObject(param[21]));
					detailsVO.setRelativeName(commonMethodsUtilService.getStringValueForObject(param[2]));
					Date dob = sdf.parse(commonMethodsUtilService.getStringValueForObject(param[3]));
					detailsVO.setDateOfBirth(sdf1.format(dob));
					detailsVO.setGender(commonMethodsUtilService.getStringValueForObject(param[4]));
					detailsVO.setHouseNo(commonMethodsUtilService.getStringValueForObject(param[5]));
					detailsVO.setAadharNo(commonMethodsUtilService.getStringValueForObject(param[6]));
					detailsVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[7]));
					detailsVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[8]));
					detailsVO.setEmailId(commonMethodsUtilService.getStringValueForObject(param[9]));
					detailsVO.setMeekosamOccupationId(commonMethodsUtilService.getLongValueForObject(param[10]));
					detailsVO.setMeekosamCasteCategoryId(commonMethodsUtilService.getLongValueForObject(param[11]));
					detailsVO.setMeekosamArgeeCategoryId(commonMethodsUtilService.getLongValueForObject(param[12]));
					detailsVO.setMeekosamAnnualIncomeId(commonMethodsUtilService.getLongValueForObject(param[13]));
					detailsVO.setDuration(commonMethodsUtilService.getStringValueForObject(param[14]));
					detailsVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[15]));
					if(param[16] != null){
						String tehsilId = "1"+commonMethodsUtilService.getStringValueForObject(param[16]);
						detailsVO.setTehsil(tehsilId);
					}else if(param[17] != null){
						String tehsilId = "2"+commonMethodsUtilService.getStringValueForObject(param[17]);
						detailsVO.setTehsil(tehsilId);
					}
					if(param[18] != null){
						detailsVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[18]));
					}else if(param[19] != null){
						detailsVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[19]));
					}
					if(param[20] != null){
						detailsVO.setHamletId(commonMethodsUtilService.getLongValueForObject(param[20]));
					}
					
					detailsVOs.add(detailsVO);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
