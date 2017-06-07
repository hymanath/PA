package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerNewDAO;
import com.itgrids.partyanalyst.dao.IAlertAssignedOfficerTrackingNewDAO;
import com.itgrids.partyanalyst.dao.IAlertCommentDAO;
import com.itgrids.partyanalyst.dao.IAlertDAO;
import com.itgrids.partyanalyst.dao.IAlertMeekosamIssueFieldRelationDataDAO;
import com.itgrids.partyanalyst.dao.IAlertMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.dao.IAlertMeekosamReferalDetailsDAO;
import com.itgrids.partyanalyst.dao.IAlertTrackingDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IGovtAlertDepartmentLocationNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentDesignationOfficerDetailsNewDAO;
import com.itgrids.partyanalyst.dao.IGovtDepartmentMeekosamIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMeekosamAnnualIncomeDAO;
import com.itgrids.partyanalyst.dao.IMeekosamArgeeCategoryDAO;
import com.itgrids.partyanalyst.dao.IMeekosamCasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldRelationDAO;
import com.itgrids.partyanalyst.dao.IMeekosamIssueFieldRelationDataDAO;
import com.itgrids.partyanalyst.dao.IMeekosamIssueTypeDAO;
import com.itgrids.partyanalyst.dao.IMeekosamOccupationDAO;
import com.itgrids.partyanalyst.dao.IMeekosamPetitionerDAO;
import com.itgrids.partyanalyst.dao.IMeekosamPetitionerLandDetailsDAO;
import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeTypeRelationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MeekosamDynamicVO;
import com.itgrids.partyanalyst.dto.MeekosamGrievanceVO;
import com.itgrids.partyanalyst.dto.MeekosamLandDetailsVO;
import com.itgrids.partyanalyst.dto.PetitionerDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;
import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;
import com.itgrids.partyanalyst.model.AlertComment;
import com.itgrids.partyanalyst.model.AlertMeekosamIssueFieldRelationData;
import com.itgrids.partyanalyst.model.AlertMeekosamPetitioner;
import com.itgrids.partyanalyst.model.AlertMeekosamReferalDetails;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.MeekosamPetitioner;
import com.itgrids.partyanalyst.model.MeekosamPetitionerLandDetails;
import com.itgrids.partyanalyst.model.MeekosamPublicRepresentativeType;
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
	private IGovtDepartmentMeekosamIssueTypeDAO govtDepartmentMeekosamIssueTypeDAO;
	private IMeekosamIssueTypeDAO meekosamIssueTypeDAO;
	private IMeekosamIssueFieldRelationDAO meekosamIssueFieldRelationDAO;
	private IMeekosamPetitionerDAO meekosamPetitionerDAO;
	private IMeekosamIssueFieldRelationDataDAO meekosamIssueFieldRelationDataDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IBoothDAO boothDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IAlertDAO alertDAO;
	private IAlertMeekosamPetitionerDAO alertMeekosamPetitionerDAO;
	private IMeekosamPetitionerLandDetailsDAO meekosamPetitionerLandDetailsDAO;
	private IAlertMeekosamIssueFieldRelationDataDAO alertMeekosamIssueFieldRelationDataDAO;
	private IAlertCommentDAO alertCommentDAO;
	private IAlertTrackingDAO alertTrackingDAO;
	private IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO;
	private IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO;
	private IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO;
	private IMeekosamPublicRepresentativeTypeDAO meekosamPublicRepresentativeTypeDAO;
	private IMeekosamPublicRepresentativeTypeRelationDAO meekosamPublicRepresentativeTypeRelationDAO;
	private IAlertMeekosamReferalDetailsDAO alertMeekosamReferalDetailsDAO;
	private IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO;
	
	
	public IGovtAlertDepartmentLocationNewDAO getGovtAlertDepartmentLocationNewDAO() {
		return govtAlertDepartmentLocationNewDAO;
	}
	public void setGovtAlertDepartmentLocationNewDAO(
			IGovtAlertDepartmentLocationNewDAO govtAlertDepartmentLocationNewDAO) {
		this.govtAlertDepartmentLocationNewDAO = govtAlertDepartmentLocationNewDAO;
	}
	public IAlertMeekosamReferalDetailsDAO getAlertMeekosamReferalDetailsDAO() {
		return alertMeekosamReferalDetailsDAO;
	}
	public void setAlertMeekosamReferalDetailsDAO(
			IAlertMeekosamReferalDetailsDAO alertMeekosamReferalDetailsDAO) {
		this.alertMeekosamReferalDetailsDAO = alertMeekosamReferalDetailsDAO;
	}
	public IMeekosamPublicRepresentativeTypeRelationDAO getMeekosamPublicRepresentativeTypeRelationDAO() {
		return meekosamPublicRepresentativeTypeRelationDAO;
	}
	public void setMeekosamPublicRepresentativeTypeRelationDAO(
			IMeekosamPublicRepresentativeTypeRelationDAO meekosamPublicRepresentativeTypeRelationDAO) {
		this.meekosamPublicRepresentativeTypeRelationDAO = meekosamPublicRepresentativeTypeRelationDAO;
	}
	public IMeekosamPublicRepresentativeTypeDAO getMeekosamPublicRepresentativeTypeDAO() {
		return meekosamPublicRepresentativeTypeDAO;
	}
	public void setMeekosamPublicRepresentativeTypeDAO(
			IMeekosamPublicRepresentativeTypeDAO meekosamPublicRepresentativeTypeDAO) {
		this.meekosamPublicRepresentativeTypeDAO = meekosamPublicRepresentativeTypeDAO;
	}
	public IAlertAssignedOfficerNewDAO getAlertAssignedOfficerNewDAO() {
		return alertAssignedOfficerNewDAO;
	}
	public void setAlertAssignedOfficerNewDAO(
			IAlertAssignedOfficerNewDAO alertAssignedOfficerNewDAO) {
		this.alertAssignedOfficerNewDAO = alertAssignedOfficerNewDAO;
	}
	public IAlertAssignedOfficerTrackingNewDAO getAlertAssignedOfficerTrackingNewDAO() {
		return alertAssignedOfficerTrackingNewDAO;
	}
	public void setAlertAssignedOfficerTrackingNewDAO(
			IAlertAssignedOfficerTrackingNewDAO alertAssignedOfficerTrackingNewDAO) {
		this.alertAssignedOfficerTrackingNewDAO = alertAssignedOfficerTrackingNewDAO;
	}
	public IGovtDepartmentDesignationOfficerDetailsNewDAO getGovtDepartmentDesignationOfficerDetailsNewDAO() {
		return govtDepartmentDesignationOfficerDetailsNewDAO;
	}
	public void setGovtDepartmentDesignationOfficerDetailsNewDAO(
			IGovtDepartmentDesignationOfficerDetailsNewDAO govtDepartmentDesignationOfficerDetailsNewDAO) {
		this.govtDepartmentDesignationOfficerDetailsNewDAO = govtDepartmentDesignationOfficerDetailsNewDAO;
	}
	public IAlertTrackingDAO getAlertTrackingDAO() {
		return alertTrackingDAO;
	}
	public void setAlertTrackingDAO(IAlertTrackingDAO alertTrackingDAO) {
		this.alertTrackingDAO = alertTrackingDAO;
	}
	public IAlertCommentDAO getAlertCommentDAO() {
		return alertCommentDAO;
	}
	public void setAlertCommentDAO(IAlertCommentDAO alertCommentDAO) {
		this.alertCommentDAO = alertCommentDAO;
	}
	public IAlertMeekosamIssueFieldRelationDataDAO getAlertMeekosamIssueFieldRelationDataDAO() {
		return alertMeekosamIssueFieldRelationDataDAO;
	}
	public void setAlertMeekosamIssueFieldRelationDataDAO(
			IAlertMeekosamIssueFieldRelationDataDAO alertMeekosamIssueFieldRelationDataDAO) {
		this.alertMeekosamIssueFieldRelationDataDAO = alertMeekosamIssueFieldRelationDataDAO;
	}
	public IMeekosamPetitionerLandDetailsDAO getMeekosamPetitionerLandDetailsDAO() {
		return meekosamPetitionerLandDetailsDAO;
	}
	public void setMeekosamPetitionerLandDetailsDAO(
			IMeekosamPetitionerLandDetailsDAO meekosamPetitionerLandDetailsDAO) {
		this.meekosamPetitionerLandDetailsDAO = meekosamPetitionerLandDetailsDAO;
	}
	public IAlertMeekosamPetitionerDAO getAlertMeekosamPetitionerDAO() {
		return alertMeekosamPetitionerDAO;
	}
	public void setAlertMeekosamPetitionerDAO(
			IAlertMeekosamPetitionerDAO alertMeekosamPetitionerDAO) {
		this.alertMeekosamPetitionerDAO = alertMeekosamPetitionerDAO;
	}
	public IAlertDAO getAlertDAO() {
		return alertDAO;
	}
	public void setAlertDAO(IAlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IMeekosamIssueFieldRelationDataDAO getMeekosamIssueFieldRelationDataDAO() {
		return meekosamIssueFieldRelationDataDAO;
	}
	public void setMeekosamIssueFieldRelationDataDAO(
			IMeekosamIssueFieldRelationDataDAO meekosamIssueFieldRelationDataDAO) {
		this.meekosamIssueFieldRelationDataDAO = meekosamIssueFieldRelationDataDAO;
	}
	public IMeekosamIssueFieldRelationDAO getMeekosamIssueFieldRelationDAO() {
		return meekosamIssueFieldRelationDAO;
	}
	public void setMeekosamIssueFieldRelationDAO(
			IMeekosamIssueFieldRelationDAO meekosamIssueFieldRelationDAO) {
		this.meekosamIssueFieldRelationDAO = meekosamIssueFieldRelationDAO;
	}
	public IMeekosamIssueTypeDAO getMeekosamIssueTypeDAO() {
		return meekosamIssueTypeDAO;
	}
	public void setMeekosamIssueTypeDAO(IMeekosamIssueTypeDAO meekosamIssueTypeDAO) {
		this.meekosamIssueTypeDAO = meekosamIssueTypeDAO;
	}
	public IGovtDepartmentMeekosamIssueTypeDAO getGovtDepartmentMeekosamIssueTypeDAO() {
		return govtDepartmentMeekosamIssueTypeDAO;
	}
	public void setGovtDepartmentMeekosamIssueTypeDAO(
			IGovtDepartmentMeekosamIssueTypeDAO govtDepartmentMeekosamIssueTypeDAO) {
		this.govtDepartmentMeekosamIssueTypeDAO = govtDepartmentMeekosamIssueTypeDAO;
	}
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
	
	public List<KeyValueVO> getMeekosamIssueTypeListByDept(Long departmentId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = govtDepartmentMeekosamIssueTypeDAO.getMeekosamIssueTypeListByDept(departmentId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getMeekosamIssueTypeListByDept() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getMeekosamSubIssueTypeListForParentIssueType(Long parentIssueTypeId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = meekosamIssueTypeDAO.getMeekosamSubIssueTypeListForParentIssueType(parentIssueTypeId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getMeekosamSubIssueTypeListForParentIssueType() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
	
	public List<MeekosamDynamicVO> getAllDynamicFieldsAndDataForIsueType(Long issueTypeId){
		List<MeekosamDynamicVO> returnList = new ArrayList<MeekosamDynamicVO>();
		try {
			Set<Long> issueFieldRelationIds = new HashSet<Long>();
			
			Long parentIssueTypeId = meekosamIssueTypeDAO.getParentIssueType(issueTypeId);
			if(parentIssueTypeId != null && parentIssueTypeId.longValue() > 0l){
				List<Object[]> list = meekosamIssueFieldRelationDAO.getDynamicFieldsForIssueType(parentIssueTypeId);
				if(list != null && !list.isEmpty()){
					MeekosamDynamicVO vo = new MeekosamDynamicVO();
					for (Object[] obj : list) {
						vo.setIssueTypeId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
						vo.setIssueType(obj[2] != null ? obj[2].toString():"");
						
						MeekosamDynamicVO subvo = new MeekosamDynamicVO();
						Long relationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						issueFieldRelationIds.add(relationId);
						subvo.setId(relationId);
						subvo.setIssueFieldId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
						subvo.setIssueField(obj[4] != null ? obj[4].toString():"");
						subvo.setIssueFieldTypeId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
						subvo.setIssueFielsType(obj[6] != null ? obj[6].toString():"");
						subvo.setIssueRelationId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.getSubList().add(subvo);
					}
					returnList.add(vo);
				}
			}
			
			List<Object[]> list2 = meekosamIssueFieldRelationDAO.getDynamicFieldsForIssueType(issueTypeId);
			if(list2 != null && !list2.isEmpty()){
				MeekosamDynamicVO vo = new MeekosamDynamicVO();
				for (Object[] obj : list2) {
					vo.setIssueTypeId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
					vo.setIssueType(obj[2] != null ? obj[2].toString():"");
					
					MeekosamDynamicVO subvo = new MeekosamDynamicVO();
					Long relationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					issueFieldRelationIds.add(relationId);
					subvo.setId(relationId);
					subvo.setIssueFieldId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
					subvo.setIssueField(obj[4] != null ? obj[4].toString():"");
					subvo.setIssueFieldTypeId(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					subvo.setIssueFielsType(obj[6] != null ? obj[6].toString():"");
					subvo.setIssueRelationId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.getSubList().add(subvo);
				}
				returnList.add(vo);
			}
			
			if(issueFieldRelationIds != null && !issueFieldRelationIds.isEmpty()){
				List<Object[]> list1 = meekosamIssueFieldRelationDataDAO.getAllDataForRelationIds(new ArrayList<Long>(issueFieldRelationIds));
				if(list1 != null && !list1.isEmpty()){
					for (Object[] obj : list1) {
						Long relationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						MeekosamDynamicVO vo = getMatchedVO(returnList.get(0).getSubList(), relationId);
						if(vo != null){
							MeekosamDynamicVO datavo = new MeekosamDynamicVO();
							datavo.setId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
							datavo.setName(obj[2] != null ? obj[2].toString():"");
							datavo.setIssueRelationDataId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
							vo.getSubList().add(datavo);
						}
						else if(vo == null && returnList.size() > 1){
							MeekosamDynamicVO vo1 = getMatchedVO(returnList.get(1).getSubList(), relationId);
							if(vo1 != null){
								MeekosamDynamicVO datavo = new MeekosamDynamicVO();
								datavo.setId(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));
								datavo.setName(obj[2] != null ? obj[2].toString():"");
								datavo.setIssueRelationDataId(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
								vo1.getSubList().add(datavo);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getAllDynamicFieldsAndDataForIsueType() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
	
	public MeekosamDynamicVO getMatchedVO(List<MeekosamDynamicVO> list,Long id){
		try{
			   if(list == null || list.size() == 0)
				   return null;
			   for(MeekosamDynamicVO vo:list){
				   if(vo.getId().equals(id)){
					   return vo;
				   }
			   }
		   }catch(Exception e){
			   LOG.error("Error occured getMatchedVO() method of MeekosamGrievanceService{}",e);  
		   }
		   return null;
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
	
	public ResultStatus saveMeekosamGrievance(final MeekosamGrievanceVO inputvo,final Long userId){
		ResultStatus resultStatus = new ResultStatus();
		try {
			resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					ResultStatus status = new ResultStatus();
					Alert alert = new Alert();
					
					alert.setAlertTypeId(2l);		//Govt
					alert.setTitle(inputvo.getGrievanceTitle());
					alert.setDescription(inputvo.getGrievanceDesc());
					alert.setAlertStatusId(2l);		//Notified
					alert.setAlertSeverityId(2l);	//Medium
					alert.setImpactLevelId(inputvo.getGrievanceImpactLevel());
					alert.setImpactLevelValue(inputvo.getGrievanceLevelValue());
					
					UserAddress alertAddress = new UserAddress();
					alertAddress.setState(stateDAO.get(1L));
					alertAddress.setDistrict(districtDAO.get(inputvo.getGrievanceDistrictId()));
					if(inputvo.getGrievanceTehsilId() != null && inputvo.getGrievanceTehsilId().longValue() > 0l && inputvo.getGrievanceTehsilId().toString().substring(0, 1).equalsIgnoreCase("1")){
						List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(Long.valueOf(inputvo.getGrievanceTehsilId().toString().substring(1)));
						if(list != null && !list.isEmpty())
							alertAddress.setConstituency(list.get(0));
						alertAddress.setTehsil(tehsilDAO.get(Long.valueOf(inputvo.getGrievanceTehsilId().toString().substring(1))));
						if(inputvo.getGrievancePanchayatId() != null && inputvo.getGrievancePanchayatId().longValue() > 0l)
							alertAddress.setPanchayatId(inputvo.getGrievancePanchayatId());
						//alertAddress.setHamlet(hamletDAO.get(inputvo.getgri));
					}
					else if(inputvo.getGrievanceTehsilId() != null && inputvo.getGrievanceTehsilId().longValue() > 0l && inputvo.getGrievanceTehsilId().toString().substring(0, 1).equalsIgnoreCase("2")){
						List<Constituency> list = boothDAO.getConstituencyIdByLebId(Long.valueOf(inputvo.getGrievanceTehsilId().toString().substring(1)));
						if(list != null && !list.isEmpty())
							alertAddress.setConstituency(list.get(0));
						alertAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(inputvo.getGrievanceTehsilId().toString().substring(1))));
						if(inputvo.getGrievancePanchayatId() != null && inputvo.getGrievancePanchayatId().longValue() > 0l)
							alertAddress.setWard(constituencyDAO.get(inputvo.getGrievancePanchayatId()));
					}
					alertAddress = userAddressDAO.save(alertAddress);
					
					UserAddress petitionerAddress = new UserAddress();
					petitionerAddress.setState(stateDAO.get(1L));
					petitionerAddress.setDistrict(districtDAO.get(inputvo.getPetitionerDistrictId()));
					if(inputvo.getPetitionerTehsilId().toString().substring(0, 1).equalsIgnoreCase("1")){
						List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(Long.valueOf(inputvo.getPetitionerTehsilId().toString().substring(1)));
						if(list != null && !list.isEmpty())
							petitionerAddress.setConstituency(list.get(0));
						petitionerAddress.setTehsil(tehsilDAO.get(Long.valueOf(inputvo.getPetitionerTehsilId().toString().substring(1))));
						petitionerAddress.setPanchayatId(inputvo.getPetitionerPanchayatId());
						//alertAddress.setHamlet(hamletDAO.get(inputvo.getgri));
					}
					else if(inputvo.getPetitionerTehsilId().toString().substring(0, 1).equalsIgnoreCase("2")){
						List<Constituency> list = boothDAO.getConstituencyIdByLebId(Long.valueOf(inputvo.getPetitionerTehsilId().toString().substring(1)));
						if(list != null && !list.isEmpty())
							petitionerAddress.setConstituency(list.get(0));
						petitionerAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(inputvo.getPetitionerTehsilId().toString().substring(1))));
						petitionerAddress.setWard(constituencyDAO.get(inputvo.getPetitionerPanchayatId()));
					}
					petitionerAddress = userAddressDAO.save(petitionerAddress);
					
					alert.setAddressId(alertAddress.getUserAddressId());
					alert.setCreatedBy(userId);
					alert.setCreatedTime(dateUtilService.getCurrentDateAndTime());
					alert.setUpdatedBy(userId);
					alert.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					alert.setIsDeleted("N");
					alert.setAlertSourceId(inputvo.getCategoryId() + 1l);
					alert.setAlertCategoryId(inputvo.getCategoryId());
					if(inputvo.getGrievanceImpactLevel() != null && inputvo.getGrievanceImpactLevel().longValue() == 3l)
						alert.setImpactScopeId(2l);
					else if(inputvo.getGrievanceImpactLevel() != null && inputvo.getGrievanceImpactLevel().longValue() == 5l)
						alert.setImpactScopeId(5l);
					else if(inputvo.getGrievanceImpactLevel() != null && inputvo.getGrievanceImpactLevel().longValue() == 7l)
						alert.setImpactScopeId(12l);
					else if(inputvo.getGrievanceImpactLevel() != null && inputvo.getGrievanceImpactLevel().longValue() == 6l)
						alert.setImpactScopeId(6l);
					else if(inputvo.getGrievanceImpactLevel() != null && inputvo.getGrievanceImpactLevel().longValue() == 8l)
						alert.setImpactScopeId(9l);
					alert.setGovtDepartmentId(inputvo.getSubDeptId());
					alert.setIsMultiple("N");
					if(inputvo.getCategoryId() != null && inputvo.getCategoryId().longValue() == 6l)
						alert.setMondayGrievanceTypeId(1l);
					if(inputvo.getCategoryId() != null && inputvo.getCategoryId().longValue() == 7l)
						alert.setJanmabhoomiTypeId(1l);
					if(inputvo.getCategoryId() != null && inputvo.getCategoryId().longValue() == 8l)
						alert.setSpecialGrievanceTypeId(1l);
					if(inputvo.getCategoryId() != null && inputvo.getCategoryId().longValue() == 9l)
						alert.setGeneralGrievanceTypeId(1l);
					alert = alertDAO.save(alert);
					
					AlertComment alertComment = new AlertComment();
					alertComment.setComments(inputvo.getGrievanceDesc().toString());
					alertComment.setAlertId(alert.getAlertId());
					alertComment.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertComment.setIsDeleted("N");
					alertComment.setInsertedBy(userId);
					alertComment = alertCommentDAO.save(alertComment);
					
					AlertTracking alertTracking = new AlertTracking();
					alertTracking.setAlertId(alert.getAlertId());
					alertTracking.setAlertStatusId(2l);
					alertTracking.setAlertCommentId(alertComment.getAlertCommentId());
					alertTracking.setAlertTrackingActionId(IConstants.ALERT_ACTION_STATUS_CHANGE);
					alertTracking.setInsertedBy(userId);
					alertTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertTracking.setAlertSourceId(alert.getAlertSourceId());
					alertTracking = alertTrackingDAO.save(alertTracking);
					
					MeekosamPetitioner petitioner = new MeekosamPetitioner();
					if(inputvo.getMeekosamPetitionerId() != null && inputvo.getMeekosamPetitionerId().longValue() > 0l)
						petitioner = meekosamPetitionerDAO.get(inputvo.getMeekosamPetitionerId());
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					petitioner.setName(inputvo.getPetitionerName());
					petitioner.setRelativeName(inputvo.getPetitionerRelativeName());
					petitioner.setAge(inputvo.getPetitionerAge());
					Date dob = null;
					if(inputvo.getPetitionerDOB() != null && !inputvo.getPetitionerDOB().isEmpty())
						try {
							dob = sdf.parse(inputvo.getPetitionerDOB());
						} catch (ParseException e) {
							e.printStackTrace();
						}
					petitioner.setDateOfBirth(dob);
					petitioner.setGender(inputvo.getPetitionerGender());
					
					petitioner.setUserAddressId(petitionerAddress.getUserAddressId());
					petitioner.setHouseNo(inputvo.getPetitionerHouseNO());
					petitioner.setAadharNo(inputvo.getPetitionerAadharCardNo());
					petitioner.setVoterCardNo(inputvo.getPetitionerVoterCardNo());
					petitioner.setMobileNo(inputvo.getPetitionerMobileNo());
					petitioner.setEmailId(inputvo.getPetitionerEmailId());
					petitioner.setMeekosamOccupationId(inputvo.getPetitionerOccupation());
					petitioner.setMeekosamCasteCategoryId(inputvo.getPetitionerCaste());
					petitioner.setMeekosamArgeeCategoryId(inputvo.getPetitionerArgeeCategory());
					petitioner.setMeekosamAnnualIncomeId(inputvo.getPetitionerAnnulaIncome());
					petitioner.setInsertedBy(userId);
					petitioner.setUpdatedBy(userId);
					petitioner.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					petitioner.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					petitioner.setIsDeleted("N");
					petitioner = meekosamPetitionerDAO.save(petitioner);
					
					AlertMeekosamPetitioner alertMeekosamPetitioner = new AlertMeekosamPetitioner();
					alertMeekosamPetitioner.setAlertId(alert.getAlertId());
					alertMeekosamPetitioner.setMeekosamPetitionerId(petitioner.getMeekosamPetitionerId());
					alertMeekosamPetitioner = alertMeekosamPetitionerDAO.save(alertMeekosamPetitioner);
					
					if(inputvo.getDepartmentId() != null && inputvo.getDepartmentId().longValue() == 33l && inputvo.getLandDetailsList() != null && !inputvo.getLandDetailsList().isEmpty()){
						for (MeekosamLandDetailsVO landDetailsvo : inputvo.getLandDetailsList()) {
							MeekosamPetitionerLandDetails petitionerLandDetails = new MeekosamPetitionerLandDetails();
							petitionerLandDetails.setAlertId(alert.getAlertId());
							petitionerLandDetails.setMeekosamPetitionerId(petitioner.getMeekosamPetitionerId());
							
							UserAddress landAddress = new UserAddress();
							landAddress.setState(stateDAO.get(1L));
							landAddress.setDistrict(districtDAO.get(landDetailsvo.getDistrictId()));
							if(landDetailsvo.getMandalId().toString().substring(0, 1).equalsIgnoreCase("1")){
								List<Constituency> list = boothDAO.getConstituencyIdByTehsilId(Long.valueOf(landDetailsvo.getMandalId().toString().substring(1)));
								if(list != null && !list.isEmpty())
									landAddress.setConstituency(list.get(0));
								landAddress.setTehsil(tehsilDAO.get(Long.valueOf(landDetailsvo.getMandalId().toString().substring(1))));
								if(landDetailsvo.getVillageId() != null && landDetailsvo.getVillageId().longValue() > 0l)
									landAddress.setPanchayatId(landDetailsvo.getVillageId());
								//alertAddress.setHamlet(hamletDAO.get(inputvo.getgri));
							}
							else if(landDetailsvo.getMandalId().toString().substring(0, 1).equalsIgnoreCase("2")){
								List<Constituency> list = boothDAO.getConstituencyIdByLebId(Long.valueOf(landDetailsvo.getMandalId().toString().substring(1)));
								if(list != null && !list.isEmpty())
									landAddress.setConstituency(list.get(0));
								landAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(landDetailsvo.getMandalId().toString().substring(1))));
								if(landDetailsvo.getVillageId() != null && landDetailsvo.getVillageId().longValue() > 0l)
									landAddress.setWard(constituencyDAO.get(landDetailsvo.getVillageId()));
							}
							landAddress = userAddressDAO.save(landAddress);
							
							petitionerLandDetails.setUserAddressId(landAddress.getUserAddressId());
							petitionerLandDetails.setSurveyNo(landDetailsvo.getSurveyNO());
							petitionerLandDetails.setLandInAcres(landDetailsvo.getLandInAcres());
							petitionerLandDetails.setLandInCents(landDetailsvo.getLandInCents());
							petitionerLandDetails = meekosamPetitionerLandDetailsDAO.save(petitionerLandDetails);
						}
					}
					
					if(inputvo.getDynamicDataList() != null && !inputvo.getDynamicDataList().isEmpty()){
						for (MeekosamDynamicVO dynamicVO : inputvo.getDynamicDataList()) {
							AlertMeekosamIssueFieldRelationData relationData = new AlertMeekosamIssueFieldRelationData();
							relationData.setAlertId(alert.getAlertId());
							relationData.setMeekosamIssueFieldRelationId(dynamicVO.getIssueRelationId());
							if(dynamicVO.getIssueRelationDataId() != null && dynamicVO.getIssueRelationDataId().longValue() > 0l)
								relationData.setMeekosamIssueFieldRelationDataId(dynamicVO.getIssueRelationDataId());
							relationData.setData(dynamicVO.getIssueDataStr());
							relationData.setIsDeleted("N");
							relationData = alertMeekosamIssueFieldRelationDataDAO.save(relationData);
						}
					}
					
					/*Long referalRelationId = 0l;
					List<Long> referelList = meekosamPublicRepresentativeTypeRelationDAO.getMeekosamPublicRepresentativeTypeRelationId(inputvo.getReferalTypeId(), inputvo.getReferalDistrictId(), inputvo.getReferalNameId());
					if(referelList != null && !referelList.isEmpty())
						referalRelationId = referelList.get(0);
					
					AlertMeekosamReferalDetails alertMeekosamReferalDetails = new AlertMeekosamReferalDetails();
					alertMeekosamReferalDetails.setAlertId(alert.getAlertId());
					alertMeekosamReferalDetails.setMeekosamPublicRepresentativeTypeRelationId(referalRelationId);
					alertMeekosamReferalDetails.setIsDeleted("N");
					alertMeekosamReferalDetails = alertMeekosamReferalDetailsDAO.save(alertMeekosamReferalDetails);*/
					/*if(inputvo.getReferCadreIds() != null && !inputvo.getReferCadreIds().isEmpty()){
						for (Long cadreId : inputvo.getReferCadreIds()) {
							
						}
					}*/
					
					Long desigOfficerId = null;
					List<Long> designationOfficerIds = govtDepartmentDesignationOfficerDetailsNewDAO.getNewDesignationOfficerIdsNew(inputvo.getAssignLevelId(),
																							inputvo.getAssignLevelValue(), inputvo.getDesignationId(), inputvo.getOfficerId());
					if(designationOfficerIds != null && !designationOfficerIds.isEmpty())
						desigOfficerId = designationOfficerIds.get(0);
					
					AlertAssignedOfficerNew alertAssignedOfficer = new AlertAssignedOfficerNew();
					alertAssignedOfficer.setAlertId(alert.getAlertId());
					alertAssignedOfficer.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficer.setGovtOfficerId(inputvo.getOfficerId() !=null ? (Long)inputvo.getOfficerId():null);
					alertAssignedOfficer.setInsertedBy(userId);
					alertAssignedOfficer.setUpdatedBy(userId);
					alertAssignedOfficer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertAssignedOfficer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					alertAssignedOfficer.setAlertStatusId(2l);
					alertAssignedOfficer.setIsDeleted("N");
					alertAssignedOfficer.setIsApproved("Y");
					alertAssignedOfficer.setGovtDepartmentId(inputvo.getSubDeptId());
					alertAssignedOfficer = alertAssignedOfficerNewDAO.save(alertAssignedOfficer);

					//Officer Assigning Tracking
					AlertAssignedOfficerTrackingNew alertAssignedOfficerTracking = new AlertAssignedOfficerTrackingNew();
					alertAssignedOfficerTracking.setAlertAssignedOfficerId(alertAssignedOfficer.getAlertAssignedOfficerId());
					alertAssignedOfficerTracking.setAlertId(alert.getAlertId());
					alertAssignedOfficerTracking.setGovtDepartmentDesignationOfficerId(desigOfficerId);
					alertAssignedOfficerTracking.setGovtOfficerId(inputvo.getOfficerId());
					alertAssignedOfficerTracking.setInsertedBy(userId);
					alertAssignedOfficerTracking.setUpdatedBy(userId);
					alertAssignedOfficerTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					alertAssignedOfficerTracking.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					alertAssignedOfficerTracking.setAlertStatusId(2l);
					alertAssignedOfficerTracking.setGovtAlertActionTypeId(1l);
					alertAssignedOfficerTracking.setIsApproved("Y");
					alertAssignedOfficerTracking.setAlertSeviorityId(alert.getAlertSeverityId());
					alertAssignedOfficerTracking.setGovtDepartmentId(inputvo.getSubDeptId());
					alertAssignedOfficerTracking = alertAssignedOfficerTrackingNewDAO.save(alertAssignedOfficerTracking);
					
					status.setResultCode(0);
					status.setMessage("success");
					return status;
				}
			}); 
		} catch (Exception e) {
			resultStatus.setResultCode(1);
			resultStatus.setMessage("failure");
			LOG.error("Error occured saveMeekosamGrievance() method of MeekosamGrievanceService",e);
		}
		return resultStatus;
	}
	
	public List<KeyValueVO> getAllPublicRepresentativeTypes(){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<MeekosamPublicRepresentativeType> list = meekosamPublicRepresentativeTypeDAO.getAll();
			if(list != null && !list.isEmpty()){
				for (MeekosamPublicRepresentativeType modal : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(modal.getMeekosamPublicRepresentativeTypeId());
					vo.setName(modal.getType());
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getAllPublicRepresentativeTypes() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getPublicReresentativesByTypeAndDistrict(Long typeId,Long districtId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = meekosamPublicRepresentativeTypeRelationDAO.getReferalNamesByTypeAndDist(typeId, districtId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getPublicReresentativesByTypeAndDistrict() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
	
	public List<Long> getUserDepartments(Long userId){
		List<Long> returnList = new ArrayList<Long>();
		try {
			List<Object[]> list = govtAlertDepartmentLocationNewDAO.getDeptIdAndNameForUserAccessLevel(userId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					returnList.add(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getPublicReresentativesByTypeAndDistrict() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getDistrictForGrievanceRequest(Long stateId){
		List<KeyValueVO> returnList = new ArrayList<KeyValueVO>();
		try {
			List<Object[]> list = districtDAO.getDistrictForGrievanceRequest(stateId);
			if(list != null && !list.isEmpty()){
				for (Object[] obj : list) {
					KeyValueVO vo = new KeyValueVO();
					vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
					vo.setName(obj[1] != null ? obj[1].toString():"");
					//vo.setCount(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Error occured getDistrictForGrievanceRequest() method of MeekosamGrievanceService",e);
		}
		return returnList;
	}
}
