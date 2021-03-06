package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAffiliatedMemberDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
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
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAffiliatedMemberService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class AffiliatedMemberService implements IAffiliatedMemberService {
	private static final Logger   LOG = Logger.getLogger(AffiliatedMemberService.class);
	
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
	
	private DateUtilService dateUtilService = new DateUtilService();
	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	private IBoothDAO boothDAO;

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

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
			List<Object[]> affliatedDataList = new ArrayList<Object[]>();
			Long memberListSize=null;
			
			if(searchType != null && searchType.equalsIgnoreCase("mebershipno"))
			{
				List<Object[]> memberlist = affiliatedMemberDAO.searchAffiliatedMemberDetails(searchType, searchValue, locationType, locationValues);
				
				memberListSize = Long.valueOf(memberlist.size());
				if(commonMethodsUtilService.isListOrSetValid(memberlist)){
					affliatedDataList.addAll(memberlist);
				}else{
					List <Object[]> memberDataThroughTClist = affiliatedMemberDAO.searchAffiliatedMemberDetailsThroughTC(searchType, searchValue, locationType, locationValues);
					affliatedDataList.addAll(memberDataThroughTClist);
				}
				
			}else if(searchType != null && searchType.equalsIgnoreCase("votercardno")){
				List <Object[]> memberlist =affiliatedMemberDAO.searchAffiliatedMemberDetails(searchType, searchValue, locationType, locationValues);
				
				memberListSize=Long.valueOf(memberlist.size());
				if(commonMethodsUtilService.isListOrSetValid(memberlist)){
					affliatedDataList.addAll(memberlist);
				}else{
					List <Object[]> memberDataThroughTClist =affiliatedMemberDAO.searchAffiliatedMemberDetailsThroughTC(searchType, searchValue, locationType, locationValues);
					if(commonMethodsUtilService.isListOrSetValid(memberlist)){
						affliatedDataList.addAll(memberDataThroughTClist);
					}else{
						List <Object[]> memberDataThroughVoterlist =affiliatedMemberDAO.searchAffiliatedMemberDetailsThroughVoter(searchType, searchValue, locationType, locationValues);
						affliatedDataList.addAll(memberDataThroughVoterlist);
					}
				}
			}else if(searchType != null && searchType.equalsIgnoreCase("mobileno"))
			{
				List <Object[]> memberlist = affiliatedMemberDAO.searchAffiliatedMemberDetailsForMobile(searchType, searchValue, locationType, locationValues);
				List<Long> tdpCadreIds = new ArrayList<Long>(0);
				
				memberListSize=Long.valueOf(memberlist.size());
				if(commonMethodsUtilService.isListOrSetValid(memberlist))
				{
					affliatedDataList.addAll(memberlist);
					for(Object[] params : memberlist)
						if(params[0] != null)
							tdpCadreIds.add((Long)params[0]);
				}
				List <Object[]> memberDataThroughTClist =affiliatedMemberDAO.searchAffiliatedMemberDetailsThroughTC(searchType, searchValue, locationType, locationValues);
				if(commonMethodsUtilService.isListOrSetValid(memberDataThroughTClist))
				{
					for(Object[] params : memberDataThroughTClist)
						if(!tdpCadreIds.contains((Long)params[0]))
							affliatedDataList.add(params);
				}
			}
			
			if(affliatedDataList!= null && affliatedDataList.size() >0){
				for (Object[] objects : affliatedDataList) {
					AffiliatedMemberVO vo=new AffiliatedMemberVO();
					vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setMembershipNo(commonMethodsUtilService.getStringValueForObject(objects[2]));
					vo.setVoterId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					vo.setVoterIdCardNo(commonMethodsUtilService.getStringValueForObject(objects[4]));
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
					if(vo.getTdpCadreId() !=null && vo.getTdpCadreId().longValue()>0l && memberListSize==0l){
						vo.setAffiliatedMemberId(affiliatedMemberDAO.getAffiliatedMemberId(commonMethodsUtilService.getLongValueForObject(objects[0]),"cadre"));
					}else if(vo.getVoterId()!=null && (vo.getTdpCadreId() == null || vo.getTdpCadreId().longValue()== 0l) && vo.getVoterId().longValue()>0l && memberListSize==0l){
						vo.setAffiliatedMemberId(affiliatedMemberDAO.getAffiliatedMemberId(commonMethodsUtilService.getLongValueForObject(objects[3]),"voter"));
					}else{
						vo.setAffiliatedMemberId(commonMethodsUtilService.getLongValueForObject(objects[12]));
					}
					returnList.add(vo);
				}
			}
		}catch(Exception e){
		        LOG.error("Exception rised in searchAffiliatedMemberDetails",e);
        }
	return returnList;
	}

	@Override
	public AffiliatedMemberVO saveAffiliatedMemberDetails(final JSONObject jobj,final String isActiveUser) 
	{
		final AffiliatedMemberVO finalVo = new AffiliatedMemberVO();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				try{
					Long affiliatedMemberId = null;
					Long tdpCadreId = commonMethodsUtilService.getLongObjectFromJson(jobj,"tdpCadreId");
					Long voterId = commonMethodsUtilService.getLongObjectFromJson(jobj,"voterId");
					
					if(tdpCadreId != null)
						affiliatedMemberId = affiliatedMemberDAO.getAffiliatedMemberId(tdpCadreId,"cadre");
					else if(jobj.has("voterId") && jobj.getLong("voterId")>0){
						affiliatedMemberId = affiliatedMemberDAO.getAffiliatedMemberId(voterId,"voter");
					}
					
					if(affiliatedMemberId != null){
						finalVo.setStatus("Member Already Registered");
						finalVo.setReason("Member Already Registered with Id:"+affiliatedMemberId);
						finalVo.setIsActiveAppUser(isActiveUser);
					}else{
						AffiliatedMember member = new AffiliatedMember();
						member.setVoterId(voterId);
						member.setTdpCadreId(tdpCadreId);
						member.setFullName(jobj.has("fullName") ? jobj.getString("fullName").trim() : "");
						member.setGender(jobj.has("gender") ? jobj.getString("gender").trim() : "");
						member.setAge(commonMethodsUtilService.getLongObjectFromJson(jobj,"age"));
						member.setMobileNo(jobj.has("mobileNo") ? jobj.getString("mobileNo").trim() :"");
						member.setEmailId(jobj.has("emailId") ? jobj.getString("emailId").trim() : null);
						member.setIsSmartPhone(jobj.has("isSmartPhone") ? jobj.getString("isSmartPhone").trim() : null);
						member.setIsInterested("Y");
						member.setIsActiveMember(jobj.has("isActiveMember") ? jobj.getString("isActiveMember").trim() :null);
				 		member.setIsAppliedLoan(jobj.has("isAppliedLoan") ? jobj.getString("isAppliedLoan").trim() : null);
						member.setEducationId(commonMethodsUtilService.getLongObjectFromJson(jobj,"educationId"));
						member.setOccupationId(commonMethodsUtilService.getLongObjectFromJson(jobj,"occupationId"));
						member.setCasteStateId(commonMethodsUtilService.getLongObjectFromJson(jobj,"casteStateId"));
						member.setAffiliateMemberTypeId(1L);
						member.setIsDeleted("N");
						member.setLatitude(jobj.has("latitude") ? jobj.getString("latitude").trim() : null);
						member.setLongititude(jobj.has("longititude") ? jobj.getString( "longititude").trim() : null);
						member.setLocationAccuracy((jobj.has("locationAccuracy") && jobj.getString("locationAccuracy").trim().length() > 0)  ? jobj.getDouble("locationAccuracy") : null);
					 	member.setImei(jobj.has("imei") ? jobj.getString("imei").trim() : null);
					 	member.setAppVersion(jobj.has("appVersion") ? jobj.getString("appVersion").trim() : null );
					 	member.setUniqueKey(jobj.has("uniqueKey") ? jobj.getString("uniqueKey").trim() : null);
				 		member.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				 		member.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				 		member.setInsertedById(commonMethodsUtilService.getLongObjectFromJson(jobj,"appuserId"));
				 		member.setUpdatedById(commonMethodsUtilService.getLongObjectFromJson(jobj,"appuserId"));
				 		
				 		Long addressId = commonMethodsUtilService.getLongObjectFromJson(jobj,"addressId");
				 		UserAddress userAddress = null;
				 		
				 		if(addressId != null)
				 			userAddress = userAddressDAO.get(addressId);
				 		
				 		else if(addressId == null && voterId != null && voterId.longValue() > 0)
				 		{
				 			List<Long> list = boothPublicationVoterDAO.getBoothIdOfAVoter(voterId,IConstants.DALITHA_TEJAM_PUBLICATION_ID);
				 			
				 			if(list != null && list.size() > 0)
				 				userAddress = userAddressDAO.get(setAddress("booth",list.get(0)));
				 		}
				 			
				 		String constituencyIdPath = "";
				 		
				 		if(userAddress != null)
				 		{
				 			member.setAddressId(userAddress.getUserAddressId());
				 			if(userAddress.getConstituency() != null && userAddress.getConstituency().getConstituencyId().longValue() > 0)
				 				constituencyIdPath = userAddress.getConstituency().getConstituencyId().toString()+"/";
				 		}
				 		
				 		String imgName = System.currentTimeMillis()+".jpg";
				 		String imgPath = constituencyIdPath + imgName;
				 		String savePath = IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.DALITHA_TEJAM_IMG_FOLDER+"/"+imgPath;
				 		Boolean isSave = false;
				 		
				 		if(jobj.has("imagePath") && jobj.getString("imagePath").trim().length() > 0)
				 			isSave = imageAndStringConverter.convertBase64StringToImage(jobj.getString("imagePath").trim(),savePath);
						
						if(isSave)
							member.setImagePath(imgPath);
				 		
				 		AffiliatedMember affiliatedMember=affiliatedMemberDAO.save(member);
				 		
						if(affiliatedMember != null){
							finalVo.setAffiliatedMemberId(affiliatedMember.getAffiliatedMemberId());
							finalVo.setStatus("Inserted Successfully");
							finalVo.setIsActiveAppUser(isActiveUser);
						}
					}
					
				}catch(Exception e){
					finalVo.setStatus("Insertion Failed");
					finalVo.setReason(e.getLocalizedMessage());
					finalVo.setIsActiveAppUser(isActiveUser);
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
					else if(locationType.equalsIgnoreCase("booth"))
					{
						Booth booth = boothDAO.get(locationValues);
						userAddress.setBooth(booth);
						if(booth.getPanchayat() != null)
						{
							userAddress.setPanchayatId(booth.getPanchayat().getPanchayatId());
							userAddress.setTehsil(booth.getPanchayat().getTehsil() != null ? (tehsilDAO.get(booth.getPanchayat().getTehsil().getTehsilId())) : null);
						}
						else if(booth.getLocalBody() != null)
						{
							userAddress.setLocalElectionBody(localElectionBodyDAO.get(booth.getLocalBody().getLocalElectionBodyId()));
							if(booth.getLocalBodyWard() != null)
								userAddress.setWard(constituencyDAO.get(booth.getLocalBodyWard().getConstituencyId()));	
						}
						
						if(booth.getConstituency() != null)
						{
							userAddress.setConstituency(constituencyDAO.get(booth.getConstituency().getConstituencyId()));
							userAddress.setDistrict(districtDAO.get(booth.getConstituency().getDistrict().getDistrictId()));
							userAddress.setState(stateDAO.get(booth.getConstituency().getDistrict().getState().getStateId()));
						}
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
