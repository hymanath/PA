package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertCandidateDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMeekosamAnnualIncomeDAO;
import com.itgrids.partyanalyst.dao.IMeekosamArgeeCategoryDAO;
import com.itgrids.partyanalyst.dao.IMeekosamCasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IMeekosamOccupationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PetitionerDetailsVO;
import com.itgrids.partyanalyst.model.MeekosamPetitioner;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IMeekosamGrievanceService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

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
						MeekosamPetitioner meekosamPetitioner = new MeekosamPetitioner();
						meekosamPetitioner.setName(inputvo.getName());
						meekosamPetitioner.setRelativeName(inputvo.getRelativeName());
						meekosamPetitioner.setAge(inputvo.getAge());
						//meekosamPetitioner.setDateOfBirth();
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
						meekosamPetitioner.setInsertedBy(inputvo.getUserId());
						meekosamPetitioner.setUpdatedBy(inputvo.getUserId());
						meekosamPetitioner.setInsertedTime(date.getCurrentDateAndTime());
						meekosamPetitioner.setUpdatedTime(date.getCurrentDateAndTime());
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
			String[] tehsilInfo = inputVO.getTehsil().trim().split(",");
			if(tehsilInfo[0].equalsIgnoreCase("0")){
				userAddress.setTehsil(tehsilDAO.get(Long.parseLong(tehsilInfo[1])));
				if(inputVO.getPanchayatId() != null){
					userAddress.setPanchayatId(inputVO.getPanchayatId());
				}
				if(inputVO.getHamletId() != null){
					userAddress.setHamlet(hamletDAO.get(inputVO.getHamletId()));
				}
			}else{
				userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.parseLong(tehsilInfo[1])));
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
					objVO.setId((Long)obj[0]);
					objVO.setName(obj[1].toString() +" " +"Mandal");
					returnList.add(objVO);
				}
			}
			List<Object[]> localbodies = constituencyDAO.getLocalElectionBodiesByDistrict(districtId);
			if(localbodies != null && localbodies.size() > 0)
			{
					for(Object[] obj : localbodies){
						IdNameVO objVO = new IdNameVO();
						objVO.setId((Long)obj[0]);
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
}
