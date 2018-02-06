package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dto.AffiliatedMemberVO;
import com.itgrids.partyanalyst.model.AffiliatedMember;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAffiliatedMember;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class AffiliatedMemberImpl implements IAffiliatedMember {
	private static final Logger   LOG = Logger.getLogger(AffiliatedMemberImpl.class);
	
	private IAffiliatedMemberDAO  affiliatedMemberDAO;
	
	private CommonMethodsUtilService commonMethodsUtilService;
	
	private ImageAndStringConverter imageAndStringConverter;

	private IPanchayatDAO panchayatDAO;

	private IStateDAO stateDAO;

	private IUserAddressDAO userAddressDAO;

	private ITehsilDAO tehsilDAO;

	private IDistrictDAO districtDAO;

	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;

	private IConstituencyDAO constituencyDAO;

	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;

	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	private TransactionTemplate transactionTemplate;
	

	public IAffiliatedMemberDAO getAffiliatedMemberDAO() {
		return affiliatedMemberDAO;
	}

	public void setAffiliatedMemberDAO(IAffiliatedMemberDAO affiliatedMemberDAO) {
		this.affiliatedMemberDAO = affiliatedMemberDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public ImageAndStringConverter getImageAndStringConverter() {
		return imageAndStringConverter;
	}

	public void setImageAndStringConverter(ImageAndStringConverter imageAndStringConverter) {
		this.imageAndStringConverter = imageAndStringConverter;
	}
	
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	
	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}

	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public List<AffiliatedMemberVO> searchAffiliatedMemberDetails(String searchType, String searchValue,String locationType, Long locationValues) {
		 List<AffiliatedMemberVO> returnList = new ArrayList<AffiliatedMemberVO>();
		try{  	
			
			List<Object[]> affiliatedDtls = affiliatedMemberDAO.searchAffiliatedMemberDetails(searchType, searchValue, locationType,  locationValues);
			if(searchType.equalsIgnoreCase("votercardno") && affiliatedDtls.size() == 0){
				List<Object[]> affiliatedVoterDtls = affiliatedMemberDAO.searchAffiliatedMemberDetailsthroughVoter(searchType, searchValue);
				affiliatedDtls.addAll(affiliatedVoterDtls);
			}
			//0-cadreId,1-mobileNum,2-memShipNo,3-voterId,4-cardNum,5-releativename,6-houseno,7-castename,8-firstnAMme,9-casteid,10-gender
			if(affiliatedDtls!= null && affiliatedDtls.size() >0){
				for (Object[] objects : affiliatedDtls) {
					AffiliatedMemberVO vo=new AffiliatedMemberVO();
					vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setMembershipNo(commonMethodsUtilService.getStringValueForObject(objects[2]));
					vo.setVoterId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					vo.setVoterIdCardNo(commonMethodsUtilService.getStringValueForObject(objects[4]));
					//vo.setAffiliatedMemberId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setRelativeName(commonMethodsUtilService.getStringValueForObject(objects[5]));
					vo.setHouseNo(commonMethodsUtilService.getStringValueForObject(objects[6]));
					vo.setFullName(commonMethodsUtilService.getStringValueForObject(objects[8]));
					vo.setCasteStateId(commonMethodsUtilService.getLongValueForObject(objects[9]));
					if(commonMethodsUtilService.getStringValueForObject(objects[10]) != null && commonMethodsUtilService.getStringValueForObject(objects[10]).equalsIgnoreCase("M")){
						vo.setGender("MALE");
					}else if(commonMethodsUtilService.getStringValueForObject(objects[10]) != null && commonMethodsUtilService.getStringValueForObject(objects[10]).equalsIgnoreCase("F")){
						vo.setGender("FEMALE");
					}
					if(commonMethodsUtilService.getLongValueForObject(objects[11]) != null && commonMethodsUtilService.getLongValueForObject(objects[11])>0 ){
						vo.setAddressId(commonMethodsUtilService.getLongValueForObject(objects[11]));
					}
					returnList.add(vo);
				}
			}
			Long affiliatedmemberId= affiliatedMemberDAO.getAffliatedMember(searchType, searchValue);
			if(affiliatedmemberId !=null){
				for (AffiliatedMemberVO vo : returnList) {
					if(searchType.equalsIgnoreCase("mebershipno") && vo.getMembershipNo().equalsIgnoreCase(searchValue)){
						vo.setAffiliatedMemberId(affiliatedmemberId);
					}else if(searchType.equalsIgnoreCase("votercardno") && vo.getVoterIdCardNo().equalsIgnoreCase(searchValue)){
						vo.setAffiliatedMemberId(affiliatedmemberId);
					}else if(searchType.equalsIgnoreCase("mobileno") && vo.getMobileNo().equalsIgnoreCase(searchValue)){
						vo.setAffiliatedMemberId(affiliatedmemberId);
					}
				}
			}
			
		}catch(Exception e){
		        LOG.error("Exception rised in searchAffiliatedMemberDetails",e);
        }
	return returnList;
	}

	@Override
	public AffiliatedMemberVO saveAffiliatedMemberDetails(final JSONObject jobj,final String IsActiveUser) {
		final AffiliatedMemberVO finalVo = new AffiliatedMemberVO();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try{
					Date date = new Date();
					String path = IConstants.STATIC_CONTENT_FOLDER_URL+"dalitha_tejam/"+(System.currentTimeMillis())+".png";
					Boolean isSave= imageAndStringConverter.convertBase64StringToImage(jobj.getString("imagePath"), path);
					AffiliatedMember member = new AffiliatedMember();
					member.setEducationId(jobj.has("educationId") ? jobj.getLong("educationId") :0);
					member.setLatitude(jobj.has("latitude") ? jobj.getString("latitude") : null);
				 	member.setImei(jobj.has("imei") ? jobj.getString("imei") : "");
				 	member.setMobileNo(jobj.has("mobileNo") ? jobj.getString("mobileNo") :"");
				 	member.setFullName(jobj.has("fullName") ? jobj.getString("fullName"):"");
			 		member.setInsertedTime(date);
			 		member.setGender(jobj.has("gender") ? jobj.getString("gender"): "");
			 		if(jobj.has("addressId") && String.valueOf(jobj.getLong("addressId")).length() !=0){
			 			member.setAddressId(jobj.getLong("addressId"));
			 		}else if(jobj.has("addressId") && String.valueOf(jobj.getLong("addressId")).length() ==0){
			 			member.setAddressId(setAddress(jobj.getString("locationType"), jobj.getLong("locationValues")));
			 		}
			 		member.setVoterId(jobj.has("voterId") ? jobj.getLong("voterId"): null);
			 		member.setAge(jobj.has("age") ? jobj.getLong("age"): null);
			 		member.setEmailId(jobj.has("emailId") ? jobj.getString("emailId") : null);
			 		member.setCasteStateId(jobj.has("casteStateId") ? jobj.getLong("casteStateId") : null);
			 		member.setLongititude(jobj.has("longititude") ? jobj.getString( "longititude"): null);
			 		member.setIsDeleted("N");
			 		if(jobj.has("tdpCadreId") && jobj.getLong("tdpCadreId") !=0){
			 			member.setTdpCadreId(jobj.getLong("tdpCadreId"));
			 		}
			 		member.setUpdatedTime(date);
			 		member.setAppVersion(jobj.has("appVersion") ? jobj.getString("appVersion"): null );
			 		member.setImagePath(path);
			 		member.setOccupationId(jobj.has("occupationId") ? jobj.getLong("occupationId"): null);
			 		member.setIsSmartPhone(jobj.has("isSmartPhone") ? jobj.getString("isSmartPhone"): null);
			 		member.setIsInterested("Y");
			 		member.setLocationAccuracy(jobj.has("locationAccuracy") ? jobj.getDouble("locationAccuracy"):null);
			 		member.setUnique_key(jobj.has("uniqueKey") ? jobj.getString("uniqueKey") : null);
			 		member.setIsActiveMember(jobj.has("isActiveMember") ? jobj.getString("isActiveMember") :"N");
			 		member.setIsAppliedLoan(jobj.has("isAppliedLoan") ? jobj.getString("isAppliedLoan") : "N");
			 		AffiliatedMember affiliatedMember=affiliatedMemberDAO.save(member);
					if(affiliatedMember != null){
						finalVo.setAffiliatedMemberId(affiliatedMember.getAffiliatedMemberId());
						finalVo.setStatus("Inserted Successfully");
						finalVo.setIsActiveAppUser(IsActiveUser);
					}
				}catch(Exception e){
					finalVo.setStatus("Insertion Failed");
					finalVo.setReason(e.getLocalizedMessage());
					finalVo.setIsActiveAppUser(IsActiveUser);
					LOG.error("Exception rised in saveAffiliatedMemberDetails",e);
				}
			}
		});
		return finalVo;
		
	}

	public Long setAddress(final String locationType,final Long locationValues ){
		Long userAddressId = null ;
		try{
			userAddressId =(Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					UserAddress userAddress = new UserAddress();
					com.itgrids.partyanalyst.model.State state = stateDAO.get(1l);
					userAddress.setState(state);
					if(locationType.equalsIgnoreCase("panchayat")){
						Panchayat panchayat = panchayatDAO.get(locationValues);
						Tehsil tehsil = tehsilDAO.get(panchayat.getTehsil().getTehsilId());
						District distinct = districtDAO.get(tehsil.getDistrict().getDistrictId());
						List<Long> constituencyIds = delimitationConstituencyMandalDAO.getConstituencyIdByMandalID(tehsil.getTehsilId());
						userAddress.setDistrict(distinct);
						if(constituencyIds != null && constituencyIds.size() > 0)
						  userAddress.setConstituency(constituencyDAO.get(constituencyIds.get(0)));
						userAddress.setTehsil(tehsil);
						userAddress.setPanchayatId(panchayat.getPanchayatId());
					}else if(locationType.equalsIgnoreCase("muncipality")){
						List<Long> constituencyIdsList = assemblyLocalElectionBodyDAO.getConstituencyIdByAssemblyLocalEleBodyId(locationValues);
						Long constituencyId = Long.parseLong(constituencyIdsList.get(0).toString());
						
						Constituency constituency = constituencyDAO.get(constituencyId);
						District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
							
						userAddress.setDistrict(distinct);
						userAddress.setConstituency(constituency);
						userAddress.setLocalElectionBody(localElectionBodyDAO.get(locationValues));
					}else if(locationType.equalsIgnoreCase("mandal")){
						Tehsil tehsil = tehsilDAO.get(locationValues);
						District distinct = districtDAO.get(tehsil.getDistrict().getDistrictId());
						List<Long> constituencyIds = delimitationConstituencyMandalDAO.getConstituencyIdByMandalID(locationValues);
						
						userAddress.setDistrict(distinct);
						if(constituencyIds != null && constituencyIds.size() > 0)
						  userAddress.setConstituency(constituencyDAO.get(constituencyIds.get(0)));
						userAddress.setTehsil(tehsil);
					}else if(locationType.equalsIgnoreCase("constituency")){
						Constituency constituency = constituencyDAO.get(locationValues);
						District distinct = districtDAO.get(constituency.getDistrict().getDistrictId());
				
						userAddress.setDistrict(distinct);
						userAddress.setConstituency(constituency);
						
					}else if(locationType.equalsIgnoreCase("district")){
						District distinct = districtDAO.get(locationValues);
						userAddress.setDistrict(distinct);
					}
					userAddress = userAddressDAO.save(userAddress);
					
					return userAddress.getUserAddressId();
					}
				});
			return userAddressId;
		}catch(Exception e){
			LOG.error("Exception rised in saving userAddress",e);
		}
		return userAddressId;
	
		
	
	}
}
