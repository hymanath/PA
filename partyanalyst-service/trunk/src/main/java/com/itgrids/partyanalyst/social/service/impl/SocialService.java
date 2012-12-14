package com.itgrids.partyanalyst.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAddressContactDAO;
import com.itgrids.partyanalyst.dao.IAddressDAO;
import com.itgrids.partyanalyst.dao.IAddressTypeDAO;
import com.itgrids.partyanalyst.dao.ICandidateAddressDAO;
import com.itgrids.partyanalyst.dao.ICandidateCasteDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidatePhoneDAO;
import com.itgrids.partyanalyst.dao.ICandidateWebsiteDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryGroupDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStatewiseDAO;
import com.itgrids.partyanalyst.dao.ICategoryDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPhoneCategoryDAO;
import com.itgrids.partyanalyst.dao.IPhoneNumberDAO;
import com.itgrids.partyanalyst.dao.IPhoneTypeDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SocialNetworkVO;
import com.itgrids.partyanalyst.model.Address;
import com.itgrids.partyanalyst.model.AddressContact;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateAddress;
import com.itgrids.partyanalyst.model.CandidateCaste;
import com.itgrids.partyanalyst.model.CandidatePhone;
import com.itgrids.partyanalyst.model.CandidateWebsite;
import com.itgrids.partyanalyst.model.Caste;
import com.itgrids.partyanalyst.model.CasteStatewise;
import com.itgrids.partyanalyst.model.PhoneNumber;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.dao.IPartySocialDAO;
import com.itgrids.partyanalyst.social.dao.ISocialNetworkSiteDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;
import com.itgrids.partyanalyst.social.service.ISocialService;
import com.itgrids.partyanalyst.utils.IConstants;



public class SocialService implements ISocialService {

private IPartySocialDAO partySocialDAO;
private ICandidateSocialDAO candidateSocialDAO;
private ICandidateDAO candidateDAO;
private ISocialNetworkSiteDAO socialNetworkSiteDAO;
private INominationDAO nominationDAO;
private ICasteCategoryDAO casteCategoryDAO;
private ICasteCategoryGroupDAO casteCategoryGroupDAO;
private ICasteDAO casteDAO;
private ICasteStatewiseDAO casteStatewiseDAO;
private ITehsilDAO tehsilDAO;
private SocialNetworkVO socialNetworkVO; 
private CandidateDetailsVO candidateDetailsVO;
private AddressVO addressVO; 

private IAddressTypeDAO addressTypeDAO;
private IAddressDAO addressDAO;
private ICandidateAddressDAO candidateAddressDAO;
private IPhoneCategoryDAO phoneCategoryDAO;
private IPhoneTypeDAO phoneTypeDAO;
private IPhoneNumberDAO phoneNumberDAO;
private ICandidatePhoneDAO candidatePhoneDAO;
private IStateDAO stateDAO;
private IDistrictDAO districtDAO;
private ICandidateCasteDAO candidateCasteDAO;
private ICandidateWebsiteDAO candidateWebsiteDAO;

private IFileDAO fileDAO;
private TransactionTemplate transactionTemplate;
private IRegionScopesDAO regionScopesDAO;
private INewsImportanceDAO newsImportanceDAO;
private ICategoryDAO categoryDAO; 
private IAddressContactDAO addressContactDAO;
private List<AddressVO> addressList;

private Long casteGroupId;


public Long getCasteGroupId() {
	return casteGroupId;
}
public void setCasteGroupId(Long casteGroupId) {
	this.casteGroupId = casteGroupId;
}
public List<AddressVO> getAddressList() {
	return addressList;
}
public void setAddressList(List<AddressVO> addressList) {
	this.addressList = addressList;
}
public IAddressContactDAO getAddressContactDAO() {
	return addressContactDAO;
}
public void setAddressContactDAO(IAddressContactDAO addressContactDAO) {
	this.addressContactDAO = addressContactDAO;
}
public IDistrictDAO getDistrictDAO() {
	return districtDAO;
}
public void setDistrictDAO(IDistrictDAO districtDAO) {
	this.districtDAO = districtDAO;
}
public IRegionScopesDAO getRegionScopesDAO() {
	return regionScopesDAO;
}
public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
	this.regionScopesDAO = regionScopesDAO;
}
public INewsImportanceDAO getNewsImportanceDAO() {
	return newsImportanceDAO;
}
public void setNewsImportanceDAO(INewsImportanceDAO newsImportanceDAO) {
	this.newsImportanceDAO = newsImportanceDAO;
}
public ICategoryDAO getCategoryDAO() {
	return categoryDAO;
}
public void setCategoryDAO(ICategoryDAO categoryDAO) {
	this.categoryDAO = categoryDAO;
}
public IFileDAO getFileDAO() {
	return fileDAO;
}
public void setFileDAO(IFileDAO fileDAO) {
	this.fileDAO = fileDAO;
}
public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
}
public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
}
public ICandidateWebsiteDAO getCandidateWebsiteDAO() {
	return candidateWebsiteDAO;
}
public void setCandidateWebsiteDAO(ICandidateWebsiteDAO candidateWebsiteDAO) {
	this.candidateWebsiteDAO = candidateWebsiteDAO;
}
public ICandidateCasteDAO getCandidateCasteDAO() {
	return candidateCasteDAO;
}
public void setCandidateCasteDAO(ICandidateCasteDAO candidateCasteDAO) {
	this.candidateCasteDAO = candidateCasteDAO;
}
public IStateDAO getStateDAO() {
	return stateDAO;
}
public void setStateDAO(IStateDAO stateDAO) {
	this.stateDAO = stateDAO;
}
public IPhoneCategoryDAO getPhoneCategoryDAO() {
	return phoneCategoryDAO;
}
public void setPhoneCategoryDAO(IPhoneCategoryDAO phoneCategoryDAO) {
	this.phoneCategoryDAO = phoneCategoryDAO;
}
public IPhoneTypeDAO getPhoneTypeDAO() {
	return phoneTypeDAO;
}
public void setPhoneTypeDAO(IPhoneTypeDAO phoneTypeDAO) {
	this.phoneTypeDAO = phoneTypeDAO;
}
public IPhoneNumberDAO getPhoneNumberDAO() {
	return phoneNumberDAO;
}
public void setPhoneNumberDAO(IPhoneNumberDAO phoneNumberDAO) {
	this.phoneNumberDAO = phoneNumberDAO;
}
public ICandidatePhoneDAO getCandidatePhoneDAO() {
	return candidatePhoneDAO;
}
public void setCandidatePhoneDAO(ICandidatePhoneDAO candidatePhoneDAO) {
	this.candidatePhoneDAO = candidatePhoneDAO;
}
public IAddressTypeDAO getAddressTypeDAO() {
	return addressTypeDAO;
}
public void setAddressTypeDAO(IAddressTypeDAO addressTypeDAO) {
	this.addressTypeDAO = addressTypeDAO;
}
public IAddressDAO getAddressDAO() {
	return addressDAO;
}
public void setAddressDAO(IAddressDAO addressDAO) {
	this.addressDAO = addressDAO;
}
public ICandidateAddressDAO getCandidateAddressDAO() {
	return candidateAddressDAO;
}
public void setCandidateAddressDAO(ICandidateAddressDAO candidateAddressDAO) {
	this.candidateAddressDAO = candidateAddressDAO;
}

public AddressVO getAddressVO() {
	return addressVO;
}
public void setAddressVO(AddressVO addressVO) {
	this.addressVO = addressVO;
}
public CandidateDetailsVO getCandidateDetailsVO() {
	return candidateDetailsVO;
}
public void setCandidateDetailsVO(CandidateDetailsVO candidateDetailsVO) {
	this.candidateDetailsVO = candidateDetailsVO;
}
public SocialNetworkVO getSocialNetworkVO() {
	return socialNetworkVO;
}
public void setSocialNetworkVO(SocialNetworkVO socialNetworkVO) {
	this.socialNetworkVO = socialNetworkVO;
}
public ITehsilDAO getTehsilDAO() {
	return tehsilDAO;
}
public void setTehsilDAO(ITehsilDAO tehsilDAO) {
	this.tehsilDAO = tehsilDAO;
}
public ICasteDAO getCasteDAO() {
	return casteDAO;
}
public ICasteStatewiseDAO getCasteStatewiseDAO() {
	return casteStatewiseDAO;
}
public void setCasteStatewiseDAO(ICasteStatewiseDAO casteStatewiseDAO) {
	this.casteStatewiseDAO = casteStatewiseDAO;
}
public void setCasteDAO(ICasteDAO casteDAO) {
	this.casteDAO = casteDAO;
}
public ICasteCategoryDAO getCasteCategoryDAO() {
	return casteCategoryDAO;
}
public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
	this.casteCategoryDAO = casteCategoryDAO;
}
public ISocialNetworkSiteDAO getSocialNetworkSiteDAO() {
	return socialNetworkSiteDAO;
}
public void setSocialNetworkSiteDAO(ISocialNetworkSiteDAO socialNetworkSiteDAO) {
	this.socialNetworkSiteDAO = socialNetworkSiteDAO;
}
public ICandidateDAO getCandidateDAO() {
	return candidateDAO;
}
public void setCandidateDAO(ICandidateDAO candidateDAO) {
	this.candidateDAO = candidateDAO;
}
public ICandidateSocialDAO getCandidateSocialDAO() {
	return candidateSocialDAO;
}
public void setCandidateSocialDAO(ICandidateSocialDAO candidateSocialDAO) {
	this.candidateSocialDAO = candidateSocialDAO;
}

public IPartySocialDAO getPartySocialDAO() {
	return partySocialDAO;
}
public void setPartySocialDAO(IPartySocialDAO partySocialDAO) {
	this.partySocialDAO = partySocialDAO;
}
public INominationDAO getNominationDAO() {
	return nominationDAO;
}
public void setNominationDAO(INominationDAO nominationDAO) {
	this.nominationDAO = nominationDAO;
}
public ICasteCategoryGroupDAO getCasteCategoryGroupDAO() {
	return casteCategoryGroupDAO;
}
public void setCasteCategoryGroupDAO(
		ICasteCategoryGroupDAO casteCategoryGroupDAO) {
	this.casteCategoryGroupDAO = casteCategoryGroupDAO;
}	
public List<SocialNetworkVO> getPartyNames(){
		
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;
	try
	{
		List<Object[]> result =partySocialDAO.getPartyNames();
		for(Object[] params:result)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setName(params[0].toString());
			socialNetworkVO.setUrl(params[1].toString());
			list.add(socialNetworkVO);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
		}
public List<SocialNetworkVO> getCandidateNames(){
	
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;

	try
	{
		List<Object[]> result=candidateSocialDAO.getCandidateNames();
		for(Object[] params:result)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setName(params[0].toString());
			socialNetworkVO.setUrl(params[1].toString());
			list.add(socialNetworkVO);
		}
		
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
		
}

public List<SocialNetworkVO> getNames(){
	
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;

	try
	{
		List<Object[]> result =partySocialDAO.getPartyNames();
		for(Object[] params:result)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setName(params[0].toString());
			socialNetworkVO.setUrl(params[1].toString());
			list.add(socialNetworkVO);
		}
		List<Object[]> result1=candidateSocialDAO.getCandidateNames();
		for(Object[] params:result1)
		{
			socialNetworkVO = new SocialNetworkVO();
			socialNetworkVO.setCname(params[0].toString());
			socialNetworkVO.setCurl(params[1].toString());
			list.add(socialNetworkVO);
		}
				
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
		}

public List<SocialNetworkVO> getParliamentInfo(String electionType, Long stateId, Long districtId,  Long constituencyId, String status){

	List<SocialNetworkVO> list1=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;
	try{
		
		
	@SuppressWarnings("unchecked")
	List<Object[]> list=casteDAO.getCasteNamesByCasteId(electionType,stateId,districtId,constituencyId,status);
	if(list!=null && list.size() > 0){
		for(Object[] params:list)
		{
		socialNetworkVO = new SocialNetworkVO();
		socialNetworkVO.setCandId((Long)params[0]);
		
		List<CandidateCaste> casteDetails=candidateCasteDAO.getCandidateCasteDetails1(socialNetworkVO.getCandId());
		if(casteDetails!=null && casteDetails.size()>0){
			for(CandidateCaste paramValue:casteDetails){
				if(paramValue.getCasteStatewise() != null && paramValue.getCasteStatewise().getCaste() != null && paramValue.getCasteStatewise().getCaste().getCasteName() != null)
				socialNetworkVO.setCasteName(paramValue.getCasteStatewise().getCaste().getCasteName().toString());
			}
		}
		List<CandidateAddress> addressDetails=candidateAddressDAO.getCandidateAddressDetails(socialNetworkVO.getCandId());
		if(addressDetails!=null && addressDetails.size()>0){
			for(CandidateAddress paramsValue:addressDetails){
				socialNetworkVO.setAddressName(paramsValue.getAddress().getCity());
			}
		}
		if(params[1]!=null)
			
		socialNetworkVO.setLastName(params[1].toString());
		list1.add(socialNetworkVO);
		}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return list1;
	
	
}

public ResultStatus saveSocialInformation(SocialNetworkVO socialVO){
	ResultStatus resultStatus = new ResultStatus();
	try
	{
	CandidateSocial social = new CandidateSocial();
	Long candidateId = candidateDAO.getCandidateByLastName(socialVO.getCname());
	Long socialNetworkId = socialNetworkSiteDAO.getSocialNetworkIdByName(socialVO.getCategory());
	
	social.setProfileId(socialVO.getProfileId());
	if(candidateId != null)
	social.setCandidate(candidateDAO.get(new Long(candidateId)));
	if(socialNetworkId != null)
	social.setSocialNetworkSite(socialNetworkSiteDAO.get(new Long(socialNetworkId)));	
	
	candidateSocialDAO.save(social);
	
	resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	}

catch (Exception e) {
	resultStatus.setExceptionEncountered(e);
	resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	e.printStackTrace();
}
	return resultStatus;
}



public List<SocialNetworkVO> getCasteCategoriesNames(){
	
	List<SocialNetworkVO> list1=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;
	try
	{


	List<Object[]> obj = casteCategoryDAO.getCasteCategoryDetails();
	if(obj!=null)
		for(Object[] params:obj){
			
			socialNetworkVO = new SocialNetworkVO();
			if(params!= null)
				socialNetworkVO.setCasteCategoryId((Long) params[0]);
			socialNetworkVO.setCasteCategoryName(params[1].toString());
			list1.add(socialNetworkVO);
		}
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return list1;


}


public List<SocialNetworkVO> getCasteCategoryGroupNames(Long categoryId){
	
	List<SocialNetworkVO> list1= null;
	
	try
	{
		System.out.println("with in the service");
	List<Object[]> obj = casteCategoryGroupDAO.getCasteCategoryGroupNames(categoryId);
	if(obj!=null && obj.size() >0)
	{
		list1 = new ArrayList<SocialNetworkVO>();
		SocialNetworkVO socialNetworkVO = null;
		
		for(Object[] params:obj){
			socialNetworkVO = new SocialNetworkVO();
			
			socialNetworkVO.setCasteCategoryGroupId((Long)params[0]);	
			socialNetworkVO.setCasteCategoryGroupName(params[1].toString());
			list1.add(socialNetworkVO);
		}
	}
	System.out.println("value in service:"+list1);
	return list1;
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return list1;
	}
	
	
}

public List<SocialNetworkVO> getCasteName(Long casteStateId) {
	
	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;
	try
	{
	
		List<Object[]> obj = casteStatewiseDAO.getStatewiseCastNames(1l);
	if(obj!=null)
		for(Object[] params:obj){
			socialNetworkVO = new SocialNetworkVO();
			if(params!= null)
			socialNetworkVO.setCasteId((Long)params[0]);
			socialNetworkVO.setCasteNames(params[1].toString());
			list.add(socialNetworkVO);
		}
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return list;
}

public List findByTehsilNameAndDistrict(Long districtId){

	List<SocialNetworkVO> list=new ArrayList<SocialNetworkVO>();
	SocialNetworkVO socialNetworkVO = null;
	try
	{
	
		List<Tehsil> obj = tehsilDAO.findByDistrict(districtId);
	if(obj!=null)
	{
	for(Tehsil params : obj)
	{
		socialNetworkVO = new SocialNetworkVO();
		socialNetworkVO.setCandId(params.getTehsilId());
		socialNetworkVO.setCname(params.getTehsilName());
		list.add(socialNetworkVO);
	}
	}
	}
	
		
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return list;
	
	}


public ResultStatus insertAddressDetails(CandidateDetailsVO candidateDetailsVO){
	ResultStatus resultStatus = new ResultStatus();
	CandidateAddress candidateAddress=null;
	List<SocialNetworkVO> socialList=new ArrayList<SocialNetworkVO>();
	Address address=null;
	PhoneNumber phoneNumbers=null;
	try{
		
		if(candidateDetailsVO.getAddressList() != null){
			
			AddressVO addressVo = candidateDetailsVO.getAddressList().get(0);
			
			
			if(candidateDetailsVO.getAddressList().get(0).getAddressId()==null)
				address=new Address();
			else			
				address =  addressDAO.get(candidateDetailsVO.getAddressList().get(0).getAddressId());
			
			saveAddressDetails(addressVo,address);
			
			
			if(candidateDetailsVO.getAddressList().get(0).getCandidateAddressId()==null)
				candidateAddress= new CandidateAddress();
			else			
				candidateAddress = candidateAddressDAO.get(candidateDetailsVO.getAddressList().get(0).getCandidateAddressId());
						
			saveCandidateAddressDetails(candidateDetailsVO,address,candidateAddress);
						
			socialList=addressVo.getPhoneList();
			
		    if(candidateDetailsVO.getAddressList().get(0).getPhoneList().get(0).getPhoneNumberId()==null)
				phoneNumbers=new PhoneNumber();
		    else
				phoneNumbers = phoneNumberDAO.get(candidateDetailsVO.getAddressList().get(0).getPhoneList().get(0).getPhoneNumberId());
		  
		    savePhoneNumberDetails(socialList.get(0) ,phoneNumbers);
			saveCandidatePhoneDetails(candidateDetailsVO,phoneNumbers);
			saveAddressContactDetails(candidateDetailsVO,address,phoneNumbers);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}

	}
	catch (Exception e) {
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		e.printStackTrace();
	}
	return resultStatus;
	
}

public void saveCandidateAddressDetails(CandidateDetailsVO candidateDetailsVO,Address address,CandidateAddress candidateAddress){
	
	  if(candidateDetailsVO.getCandidateId()!=null)
          candidateAddress.setCandidate(candidateDAO.get(new Long(candidateDetailsVO.getCandidateId())));
	   if(candidateDetailsVO.getCandidateId()!=null)
      	candidateAddress.setAddressType(addressTypeDAO.get(new Long(candidateDetailsVO.getAddressList().get(0).getAddressTypeId())));

        candidateAddress.setAddress(address);
	   if(candidateAddress!=null)
       candidateAddressDAO.save(candidateAddress);
	
}

public void saveAddressDetails(AddressVO addressVo,Address address){
	
	if(addressVo.getAddress1()!=null && !addressVo.getAddress1().equalsIgnoreCase(""))
		address.setAddress1(addressVo.getAddress1());
	
	if(addressVo.getAddress2()!=null && !addressVo.getAddress2().equalsIgnoreCase(""))
       address.setAddress2(addressVo.getAddress2());			

	if(addressVo.getMandalId()!=null && addressVo.getMandalId() != 0)
      address.setMandal(addressVo.getMandalId().toString());			
	
	if(addressVo.getCity()!=null && !addressVo.getCity().equalsIgnoreCase(""))
      address.setCity(addressVo.getCity());
	
	if(addressVo.getStateId()!=null && addressVo.getStateId()!= 0)
      address.setState(addressVo.getStateId().toString());
	
	if(addressVo.getPincode()!=null && !addressVo.getPincode().equals(""))
      address.setPincode(addressVo.getPincode());
	
	if(addressVo.getDistrictId()!=null)
		address.setDistrict(addressVo.getDistrictId().toString());
	
	address = addressDAO.save(address);	
	
}

public void savePhoneNumberDetails(SocialNetworkVO socialNetworkVO,PhoneNumber phoneNumbers){
	
	if(socialNetworkVO.getPhoneType()!=null)
		  phoneNumbers.setPhoneType(phoneTypeDAO.get(socialNetworkVO.getPhoneType()));
		
		if(socialNetworkVO.getPhoneCategory()!=null)
		  phoneNumbers.setPhoneCategory(phoneCategoryDAO.get(socialNetworkVO.getPhoneCategory()));
		
		if(socialNetworkVO.getPhoneNumber()!=null)
		  phoneNumbers.setNumber(socialNetworkVO.getPhoneNumber().toString());
		
		phoneNumbers=phoneNumberDAO.save(phoneNumbers);
			
	
}

public void saveCandidatePhoneDetails(CandidateDetailsVO candidateDetailsVO ,PhoneNumber phoneNumbers){
	
	CandidatePhone candidatePhone=null;
	
	
	if(candidateDetailsVO.getAddressList().get(0).getPhoneList().get(0).getCandidatePhoneId()==null)
		candidatePhone=new CandidatePhone();
	else
		candidatePhone = candidatePhoneDAO.get(candidateDetailsVO.getAddressList().get(0).getPhoneList().get(0).getCandidatePhoneId());
	
	
	if(candidateDetailsVO.getCandidateId()!=null)
		candidatePhone.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));
	
	if(phoneNumbers!=null)
		candidatePhone.setPhoneNumber(phoneNumbers);
		
		candidatePhone = candidatePhoneDAO.save(candidatePhone);
	
}

public void saveAddressContactDetails(CandidateDetailsVO candidateDetailsVO,Address address ,PhoneNumber phoneNumbers){
	
	AddressContact addressContact=null;
	
	if(candidateDetailsVO.getAddressList().get(0).getAddressContactId()==null)
		addressContact = new AddressContact();
	else
		addressContact = addressContactDAO.get(candidateDetailsVO.getAddressList().get(0).getAddressContactId());
	
	
	addressContact.setAddress(address);
	addressContact.setPhoneNumber(phoneNumbers);
	
	addressContactDAO.save(addressContact);
	
}


public ResultStatus insertCasteDetails(CandidateDetailsVO candidateDetailsVO){
	ResultStatus resultStatus = new ResultStatus();
	CandidateCaste candidateCaste=null;
	CasteStatewise casteStatewise=null;
	Caste caste=null;
	
	try{
		
		if(candidateDetailsVO.getCandidatecasteId()==null)
			candidateCaste=new CandidateCaste();
		else
			candidateCaste = candidateCasteDAO.get(candidateDetailsVO.getCandidatecasteId());
		
		
		if(candidateDetailsVO.getCasteStateId()==null)
			casteStatewise=new CasteStatewise();
		else
			casteStatewise = casteStatewiseDAO.get(candidateDetailsVO.getCasteStateId());
		
		
		if(candidateDetailsVO.getCasteId()==null ||candidateDetailsVO.getCasteId()==0 )
			caste=new Caste();
		else		
			caste = casteDAO.get(candidateDetailsVO.getCasteId());
		
		
		if(candidateDetailsVO.getCasteId()!=null && candidateDetailsVO.getCasteId() > 0)			
		   casteStatewise.setCaste(casteDAO.get(candidateDetailsVO.getCasteId()));
		
		casteStatewise.setState(stateDAO.get(candidateDetailsVO.getAddressList().get(0).getStateId()));

		
			if(candidateDetailsVO.getNewCaste()!=null && !candidateDetailsVO.getNewCaste().equalsIgnoreCase("")){
			   List<Object[]> list=casteDAO.getCasteNames();
			
					for(Object[] cast:list){
						
							if(!candidateDetailsVO.getNewCaste().equals("")){
								
								if(cast[1].equals(candidateDetailsVO.getNewCaste()))					
									casteStatewise.setCaste(casteDAO.get((Long)cast[0]));							
								else{									
									caste.setCasteName(candidateDetailsVO.getNewCaste());
									caste=casteDAO.save(caste);
									casteStatewise.setCaste(caste);						
								}
						    }
						
					}
		  }
	
		
		if(candidateDetailsVO.getCasteCategoryId()==3 || candidateDetailsVO.getCasteCategoryId()==4){
			casteGroupId=casteCategoryGroupDAO.getCasteNamesOfCategories(candidateDetailsVO.getCasteCategoryId());
			casteStatewise.setCasteCategoryGroup(casteCategoryGroupDAO.get(casteGroupId));
		}else{
			if(candidateDetailsVO.getCasteGroupId() != null && candidateDetailsVO.getCasteGroupId()!=0)
			casteStatewise.setCasteCategoryGroup(casteCategoryGroupDAO.get(candidateDetailsVO.getCasteGroupId()));
		}
	   casteStatewise=casteStatewiseDAO.save(casteStatewise);
	
	candidateCaste.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));
	candidateCaste.setCasteStatewise(casteStatewise);
		
	candidateCasteDAO.save(candidateCaste);
	
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	}catch (Exception e) {
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		e.printStackTrace();
	}	
	return resultStatus;
	
}



public ResultStatus insertOtherDetails(CandidateDetailsVO candidateDetailsVO){
	ResultStatus resultStatus = new ResultStatus();
	
	try{
		
		saveFaceBookIdDetails(candidateDetailsVO);
		saveTwitterIdDetails(candidateDetailsVO);	
		saveWebsiteAddressDetails(candidateDetailsVO);
			
		candidateDAO.findEmailInsertionInCandidate(candidateDetailsVO.getEmailId(),candidateDetailsVO.getCandidateId());		
		candidateDAO.findNameInsertionInCandidate(candidateDetailsVO.getCandidateName(),candidateDetailsVO.getCandidateId());
		
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	}catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			e.printStackTrace();
	}	
	return resultStatus;
	
}

public void saveFaceBookIdDetails(CandidateDetailsVO candidateDetailsVO){
	CandidateSocial candidateSocial=null;
	
	
	if(candidateDetailsVO.getCandidateSocialFacebookId()==null ){
		
		candidateSocial = candidateSocialDAO.get(candidateDetailsVO.getCandidateSocialFacebookId());
		candidateSocial.setSocialNetworkSite(socialNetworkSiteDAO.get(IConstants.FACEBOOK_ID));
		candidateSocial.setProfileId(candidateDetailsVO.getFacebookUrl());		
	    candidateSocial.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));
	
	    candidateSocialDAO.save(candidateSocial);
	}else if(candidateDetailsVO.getFacebookUrl() != null && !candidateDetailsVO.getFacebookUrl().equalsIgnoreCase("")){
		
		candidateSocial=new CandidateSocial();
		candidateSocial = candidateSocialDAO.get(candidateDetailsVO.getCandidateSocialFacebookId());
		candidateSocial.setSocialNetworkSite(socialNetworkSiteDAO.get(IConstants.FACEBOOK_ID));
		candidateSocial.setProfileId(candidateDetailsVO.getFacebookUrl());		
	    candidateSocial.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));
	
	    candidateSocialDAO.save(candidateSocial);
		
	}
}

public void saveTwitterIdDetails(CandidateDetailsVO candidateDetailsVO){
	
	CandidateSocial candidateSocial1=null;
	
	if(candidateDetailsVO.getCandidateSocialTwitterId() != null){
		
		candidateSocial1 = candidateSocialDAO.get(candidateDetailsVO.getCandidateSocialTwitterId());

		candidateSocial1.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));		
	    candidateSocial1.setSocialNetworkSite(socialNetworkSiteDAO.get(IConstants.TWITTER_ID));
		candidateSocial1.setProfileId(candidateDetailsVO.getTwitterUrl());
	
		candidateSocialDAO.save(candidateSocial1);
	}else if(candidateDetailsVO.getTwitterUrl() != null && !candidateDetailsVO.getTwitterUrl().equalsIgnoreCase("")){
		
		candidateSocial1=new CandidateSocial();
		
		candidateSocial1.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));		
	    candidateSocial1.setSocialNetworkSite(socialNetworkSiteDAO.get(IConstants.TWITTER_ID));
		candidateSocial1.setProfileId(candidateDetailsVO.getTwitterUrl());
	
		candidateSocialDAO.save(candidateSocial1);
		
		
	}
	
	
	
}

public void saveWebsiteAddressDetails(CandidateDetailsVO candidateDetailsVO){
	
	CandidateWebsite candidateWebsite=null;
	
	if(candidateDetailsVO.getWebsiteId() != null){
		candidateWebsite = candidateWebsiteDAO.get(candidateDetailsVO.getWebsiteId());
	
		candidateWebsite.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));
		candidateWebsite.setWebsiteAddress(candidateDetailsVO.getWebsiteAddress());
		
		candidateWebsiteDAO.save(candidateWebsite);
	}else if(candidateDetailsVO.getWebsiteAddress() != null && !candidateDetailsVO.getWebsiteAddress().equalsIgnoreCase("")){
		candidateWebsite=new CandidateWebsite();
		
		candidateWebsite.setCandidate(candidateDAO.get(candidateDetailsVO.getCandidateId()));
		candidateWebsite.setWebsiteAddress(candidateDetailsVO.getWebsiteAddress());
		
		candidateWebsiteDAO.save(candidateWebsite);
		
	}
	
}

	


public List<SelectOptionVO> getCasteNamesByAutoPopulate(Long stateId,String letters){
	
	List<SelectOptionVO> casteNamesAndIds = new ArrayList<SelectOptionVO>();
	List<Object[]> castNames =casteStatewiseDAO.getCasteNamesByAutoPopulate(stateId,letters);
	
	for(Object[] cast:castNames){
	   if(cast[0]!= null && cast[1] != null)
		casteNamesAndIds.add(new SelectOptionVO((Long)cast[0], cast[1].toString().trim().toUpperCase()));
	}

	return casteNamesAndIds;	
}


public CandidateDetailsVO getcandidateFullInformation(Long candidateId){
	CandidateDetailsVO candidateDetailsVO=new CandidateDetailsVO();
	
	try{
		
		getCandidateEmailDetails(candidateId,candidateDetailsVO);		
		getWebsiteAddressDetails(candidateId,candidateDetailsVO);
		getCandidateSocialUrlDetails(candidateId,candidateDetailsVO);
		getCandidateAddressDetails(candidateId,candidateDetailsVO);
		getCandidatePhoneDetails(candidateId,candidateDetailsVO );
		getCandidateCasteDetails(candidateId,candidateDetailsVO);
				
	}catch (Exception e) {
		System.out.println(e);
	}
	
	return candidateDetailsVO;
}


public void getCandidateEmailDetails(Long candidateId,CandidateDetailsVO candidateDetailsVO){
	
	List<Candidate> emailDetails=candidateDAO.getEmailInfo(candidateId);
	if(emailDetails.size()>0){
		for(Candidate params:emailDetails){
			candidateDetailsVO.setEmailId(params.getEmailAddress());
		}
	}
	
}

public void getWebsiteAddressDetails(Long candidateId ,CandidateDetailsVO candidateDetailsVO){
	List<CandidateWebsite> websiteDetails=candidateWebsiteDAO.getCandidateWebsiteDetails1(candidateId);
	if(websiteDetails.size()>0){
		for(CandidateWebsite param2:websiteDetails){
			candidateDetailsVO.setWebsiteId(param2.getCandidateWebsiteId());
			candidateDetailsVO.setWebsiteAddress(param2.getWebsiteAddress());
		}
	}

	
}

public void getCandidateSocialUrlDetails(Long candidateId ,CandidateDetailsVO candidateDetailsVO){
	
	List<CandidateSocial> UrlDetails=candidateSocialDAO.getCandidateUrlDetails(candidateId);

	
	if(UrlDetails.size()>0){
		//isSaved=true;
		
	for(CandidateSocial param3:UrlDetails){
		if(param3.getSocialNetworkSite()!=null)
		
			if(param3.getSocialNetworkSite().getSocialNetworkId()==1){
				candidateDetailsVO.setCandidateSocialTwitterId(param3.getCandidateSocialId());
				candidateDetailsVO.setTwitterUrl(param3.getProfileId());
			}
		if(param3.getSocialNetworkSite()!=null)
			if(param3.getSocialNetworkSite().getSocialNetworkId()==2){
				candidateDetailsVO.setCandidateSocialFacebookId(param3.getCandidateSocialId());
				candidateDetailsVO.setFacebookUrl(param3.getProfileId());
			}
		}
	}
	
	
}


public void getCandidateAddressDetails(Long candidateId ,CandidateDetailsVO candidateDetailsVO){
	
	List<AddressVO> addressList = new ArrayList<AddressVO>();
	List<AddressVO> addressList1 = new ArrayList<AddressVO>();
	AddressVO addressVO=new AddressVO();
	
	List<CandidateAddress> details=candidateAddressDAO.getCandidateAddressDetails(candidateId);
	if(details.size()>0){
			for(CandidateAddress params:details){
				
				//if(params.getAddress().getAddress1()!=null)
					
				addressVO.setCandidateAddressId(params.getCandidateAddressId());
				
				addressVO.setAddressId(params.getAddress().getAddressId());
				addressVO.setAddress1(params.getAddress().getAddress1());
				addressVO.setAddress2(params.getAddress().getAddress2());
				addressVO.setAddressContactId(addressVO.getAddressId());
				
				List<SelectOptionVO> addressTypeList=getAllAddressTypes();
				addressVO.setAddressTypeList(addressTypeList);
				
				addressVO.setAddressTypeId(params.getAddressType().getAddressTypeId());
				addressVO.setCity(params.getAddress().getCity());
				addressVO.setStateId(new Long(params.getAddress().getState()));
				
				
				List<SelectOptionVO> districtList = getAllDistrictDetails(addressVO.getStateId());
				addressVO.setDistrictList(districtList);
				
				if(params.getAddress().getDistrict() != null)
				addressVO.setDistrictId(new Long(params.getAddress().getDistrict()));
				
				
				List<SelectOptionVO> tehsilList = getAllTehsilDetails(addressVO.getDistrictId());
				addressVO.setTehsilList(tehsilList);
				
				
				if(params.getAddress().getMandal() != null)
					addressVO.setMandalId(new Long(params.getAddress().getMandal()));
					addressVO.setPincode(params.getAddress().getPincode());
					addressList.add(addressVO);
					candidateDetailsVO.setAddressList(addressList);
				
			}
   }
	
}

public void getCandidatePhoneDetails(Long candidateId,
			CandidateDetailsVO candidateDetailsVO) {
	
	SocialNetworkVO socialNetworkVO=new SocialNetworkVO();
	List<SocialNetworkVO> candidatePhoneList=new ArrayList<SocialNetworkVO>();
	AddressVO addressVO = new AddressVO();
    List<AddressVO> addressList = new ArrayList<AddressVO>();
	
	List<CandidatePhone> phoneDetails=candidatePhoneDAO.getCandidatePhoneDetails(candidateId);
			if(phoneDetails.size()>0){
				for(CandidatePhone param:phoneDetails){
					
					socialNetworkVO.setCandidatePhoneId(new Long(param.getCandidatePhoneId()));
					
					List<SelectOptionVO> phoneTypeList=getAllPhoneTypes();
					socialNetworkVO.setPhoneTypeList(phoneTypeList);
					
					List<SelectOptionVO> phoneCategoryList=getAllPhoneCategorys();
					socialNetworkVO.setPhoneCategoryList(phoneCategoryList);
					
					
					socialNetworkVO.setPhoneNumberId(param.getPhoneNumber().getPhoneNumberId());
					
					if(param.getPhoneNumber().getPhoneType()!=null)
						socialNetworkVO.setPhoneType(param.getPhoneNumber().getPhoneType().getPhoneTypeId());
					if(param.getPhoneNumber().getNumber()!=null)
						socialNetworkVO.setPhoneNumber(new Long(param.getPhoneNumber().getNumber()));
					if(param.getPhoneNumber().getPhoneCategory()!=null && param.getPhoneNumber().getNumber() != null)	
						socialNetworkVO.setPhoneCategory(new Long(param.getPhoneNumber().getNumber()));
						
						
					candidatePhoneList.add(socialNetworkVO);
						
				}
				
				   
				/*addressVO.setPhoneList(candidatePhoneList);
				addressVO.setAddressContactId(addressContactDAO.getAddressContactId(addressVO.getAddressId(), socialNetworkVO.getPhoneNumberId()));
				addressList1.add(addressVO);
				candidateDetailsVO.setAddressList(addressList1);*/
				
			if(candidateDetailsVO.getAddressList() != null && candidateDetailsVO.getAddressList().size() >0)
				candidateDetailsVO.getAddressList().get(0).setPhoneList(candidatePhoneList);
			else{
				addressVO.setPhoneList(candidatePhoneList);
				addressList.add(addressVO);
				candidateDetailsVO.setAddressList(addressList);
				
			}
					
		}
				
}


public void getCandidateCasteDetails(Long candidateId ,CandidateDetailsVO candidateDetailsVO){
	
	List<CandidateCaste> casteDetails=candidateCasteDAO.getCandidateCasteDetails1(candidateId);
	if(casteDetails.size()>0){
		for(CandidateCaste param1:casteDetails){
			
			candidateDetailsVO.setCasteStateId(param1.getCasteStatewise().getCasteStateId());
			
			candidateDetailsVO.setCandidatecasteId(param1.getCandidateCasteId());
			if(param1.getCasteStatewise() != null && param1.getCasteStatewise().getCaste()!=null && param1.getCasteStatewise().getCaste().getCasteId() != null)
				candidateDetailsVO.setCasteId(new Long(param1.getCasteStatewise().getCaste().getCasteId()));
			if(param1.getCasteStatewise().getCaste()!=null)
			candidateDetailsVO.setCasteName(param1.getCasteStatewise().getCaste().getCasteName());
			if(param1.getCasteStatewise().getCasteCategoryGroup()!=null && param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategoryGroupId() != null)
				candidateDetailsVO.setCasteGroupId(new Long(param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategoryGroupId()));
			
			if(param1.getCasteStatewise() != null && param1.getCasteStatewise().getCasteCategoryGroup() != null && param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategory()!=null)
				candidateDetailsVO.setCasteCategory1(param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategory().getCategoryName());
			
			if(param1.getCasteStatewise() != null && param1.getCasteStatewise().getCasteCategoryGroup() != null && param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategory()!=null)
				candidateDetailsVO.setCasteCategoryId(param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategory().getCasteCategoryId());
		
			List<SelectOptionVO> casteGroupList = getAllCasteCategoryGroupDetails(candidateDetailsVO.getCasteCategoryId());
			candidateDetailsVO.setCasteGroupList(casteGroupList);
			
			if(param1.getCasteStatewise().getCasteCategoryGroup()!=null)
				candidateDetailsVO.setCasteGroupId(param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategoryGroupId());
			
			List<SelectOptionVO> casteList = getAllCasteDetails();
			casteList.add(0, new SelectOptionVO(0l,"others"));
			candidateDetailsVO.setCasteGroupNameList(casteList);
			
			if(param1.getCasteStatewise().getCasteCategoryGroup()!=null)
				candidateDetailsVO.setCasteGroupName(param1.getCasteStatewise().getCasteCategoryGroup().getCasteCategoryGroupName());
		}
	}
	
}


public List<SelectOptionVO> getAllCasteDetails(){
	
	List<SelectOptionVO> castesList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> casteList = casteDAO.getCasteNames();
	
	for(Object[] values:casteList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) values[0]);
	option.setName((String) values[1]);
	
	castesList.add(option);
	
	}
	
	return castesList;
	
}

public List<SelectOptionVO> getAllCasteInfoDetails(){
	
	List<SelectOptionVO> castesList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> casteList = casteDAO.getCasteNames();
	
	for(Object[] values:casteList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) values[0]);
	option.setName((String) values[1]);
	
	castesList.add(option);
	
	}
	
	return castesList;
	
}

public List<SelectOptionVO> getAllCasteCategoryDetails(){
List<SelectOptionVO> casteCategorysList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> casteCategoryList = casteCategoryDAO.getAllCasteCategoryDetails();
	
	for(Object[] castevalues:casteCategoryList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) castevalues[0]);
	option.setName((String) castevalues[1]);
	casteCategorysList.add(option);
	}
	return casteCategorysList;
}

public List<SelectOptionVO> getAllCasteCategoryGroupDetails(Long casteCategoryId){
List<SelectOptionVO> casteGroupsList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> casteCategoryGroupList = casteCategoryGroupDAO.getAllCasteCategoryGroupDetails(casteCategoryId);
	
	for(Object[] casteGroupvalues:casteCategoryGroupList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) casteGroupvalues[0]);
	option.setName((String) casteGroupvalues[1]);
	casteGroupsList.add(option);
	}
	return casteGroupsList;
}


public List<SelectOptionVO> getAllCasteCategoryGroupInfoDetails(){
List<SelectOptionVO> casteGroupsList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> casteCategoryGroupList = casteCategoryGroupDAO.getAllCasteCategoryGroupInfoDetails();
	
	for(Object[] casteGroupvalues:casteCategoryGroupList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) casteGroupvalues[0]);
	option.setName((String) casteGroupvalues[1]);
	casteGroupsList.add(option);
	}
	return casteGroupsList;
}
public List<SelectOptionVO> getAllStateDetails(){
List<SelectOptionVO> statesList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> stateList = stateDAO.getAllStateDetails();
	
	for(Object[] statevalues:stateList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) statevalues[0]);
	option.setName((String) statevalues[1]);
	statesList.add(option);
	}
	return statesList;
}
public List<SelectOptionVO> getAllDistrictInfoDetails(){
List<SelectOptionVO> districtsList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> districtList = districtDAO.getAllDistrictInfoDetails();
	
	for(Object[] districtvalues:districtList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) districtvalues[0]);
	option.setName((String) districtvalues[1]);
	districtsList.add(option);
	}
	return districtsList;
}

public List<SelectOptionVO> getAllDistrictDetails(Long stateId){
List<SelectOptionVO> districtsList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> districtList = districtDAO.getAllDistrictDetails(stateId);
	
	for(Object[] districtvalues:districtList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) districtvalues[0]);
	option.setName((String) districtvalues[1]);
	districtsList.add(option);
	}
	return districtsList;
}

public List<SelectOptionVO> getAllPhoneCategorys(){
List<SelectOptionVO> phoneCategoryList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> phoneCategoryLists = phoneCategoryDAO.getAllPhoneCategorys();
	
	for(Object[] phoneCategoryvalues:phoneCategoryLists){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) phoneCategoryvalues[0]);
	option.setName((String) phoneCategoryvalues[1]);
	phoneCategoryList.add(option);
	}
	return phoneCategoryList;
}
public List<SelectOptionVO> getAllPhoneTypes(){
List<SelectOptionVO> phoneList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> phoneLists = phoneTypeDAO.getAllPhoneTypes();
	
	for(Object[] phonevalues:phoneLists){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) phonevalues[0]);
	option.setName((String) phonevalues[1]);
	phoneList.add(option);
	}
	return phoneList;
}
public List<SelectOptionVO> getAllAddressTypes(){
List<SelectOptionVO> addressList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> addressLists = addressTypeDAO.getAllAddressTypes();
	
	for(Object[] addressvalues:addressLists){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) addressvalues[0]);
	option.setName((String) addressvalues[1]);
	addressList.add(option);
	}
	return addressList;
}

public List<SelectOptionVO> getAllTehsilInfoDetails(){
List<SelectOptionVO> tehsilsList = new ArrayList<SelectOptionVO>();
	
	List<Object[]> tehsilList = tehsilDAO.getAllTehsilInfoDetails();
	
	for(Object[] tehsilvalues:tehsilList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) tehsilvalues[0]);
	option.setName((String) tehsilvalues[1]);
	tehsilsList.add(option);
	}
	return tehsilsList;
}

public List<SelectOptionVO> getAllTehsilDetails(Long districtId){
List<SelectOptionVO> tehsilsList = new ArrayList<SelectOptionVO>();
	

	List<Object[]> tehsilList = tehsilDAO.getAllTehsilDetails(districtId);
	
	
	for(Object[] tehsilvalues:tehsilList){
		SelectOptionVO option = new SelectOptionVO();
	option.setId((Long) tehsilvalues[0]);
	option.setName((String) tehsilvalues[1]);
	tehsilsList.add(option);
	}
	return tehsilsList;
}

/*boolean isSaved = false;
if(tehsilList.size() >0){
	
	isSaved = true;
	
}*/

}
