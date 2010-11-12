package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPersonalUserGroupDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IStaticUserGroupDAO;
import com.itgrids.partyanalyst.dao.IStaticUsersDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseCompleteDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementSubRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleDetailsVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.LocalUserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.LocalGroupRegion;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.SocialCategory;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.StaticUserDesignation;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

public class InfluencingPeopleService implements IInfluencingPeopleService{
	
	private IPartyDAO partyDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private IHamletDAO hamletDAO;
	private InfluencingPeopleDAO influencingPeopleDAO;
	private IInfluencingPeoplePositionDAO influencingPeoplePositionDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IUserAddressDAO userAddressDAO;
	private IOccupationDAO occupationDAO;
	private ISocialCategoryDAO socialCategoryDAO;
	private IRegistrationDAO registrationDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private static final Logger log = Logger.getLogger(InfluencingPeopleService.class);
	private TransactionTemplate transactionTemplate = null;
	private InfluencingPeopleVO influencingPeopleVO;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO;
	private IStaticDataService staticDataService;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IRegionServiceData regionServiceDataImp;
	private IPersonalUserGroupDAO personalUserGroupDAO;
	private IStaticUserGroupDAO staticUserGroupDAO;
	private IBoothDAO boothDAO;
	private SmsCountrySmsService smsCountrySmsService;
	private IStaticUsersDAO staticUsersDAO;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public InfluencingPeopleDAO getInfluencingPeopleDAO() {
		return influencingPeopleDAO;
	}

	public void setInfluencingPeopleDAO(InfluencingPeopleDAO influencingPeopleDAO) {
		this.influencingPeopleDAO = influencingPeopleDAO;
	}

	public SmsCountrySmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(SmsCountrySmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public IStaticUsersDAO getStaticUsersDAO() {
		return staticUsersDAO;
	}

	public void setStaticUsersDAO(IStaticUsersDAO staticUsersDAO) {
		this.staticUsersDAO = staticUsersDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IStaticUserGroupDAO getStaticUserGroupDAO() {
		return staticUserGroupDAO;
	}

	public void setStaticUserGroupDAO(IStaticUserGroupDAO staticUserGroupDAO) {
		this.staticUserGroupDAO = staticUserGroupDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public ISocialCategoryDAO getSocialCategoryDAO() {
		return socialCategoryDAO;
	}

	public void setSocialCategoryDAO(ISocialCategoryDAO socialCategoryDAO) {
		this.socialCategoryDAO = socialCategoryDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public IPersonalUserGroupDAO getPersonalUserGroupDAO() {
		return personalUserGroupDAO;
	}

	public void setPersonalUserGroupDAO(IPersonalUserGroupDAO personalUserGroupDAO) {
		this.personalUserGroupDAO = personalUserGroupDAO;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IOccupationDAO getOccupationDAO() {
		return occupationDAO;
	}

	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public InfluencingPeopleBeanVO getInfluencingPeopleBeanVO() {
		return influencingPeopleBeanVO;
	}

	public void setInfluencingPeopleBeanVO(
			InfluencingPeopleBeanVO influencingPeopleBeanVO) {
		this.influencingPeopleBeanVO = influencingPeopleBeanVO;
	}

	public IInfluencingPeoplePositionDAO getInfluencingPeoplePositionDAO() {
		return influencingPeoplePositionDAO;
	}

	public void setInfluencingPeoplePositionDAO(
			IInfluencingPeoplePositionDAO influencingPeoplePositionDAO) {
		this.influencingPeoplePositionDAO = influencingPeoplePositionDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public Long saveInfluencePeople(InfluencingPeopleVO infPeopleVO){
		influencingPeopleVO = infPeopleVO;
		try{
			if(log.isDebugEnabled()){
				log.debug("Entered in to saveInfluencePeople() method..");
			}
			Long SUCCESS=1l;
						
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {	
				InfluencingPeople influencingPeople = new InfluencingPeople();
				influencingPeople.setFirstName(influencingPeopleVO.getPersonName());
				influencingPeople.setLastName(influencingPeopleVO.getLastName());
				influencingPeople.setGender(influencingPeopleVO.getGender());
				influencingPeople.setPhoneNo(influencingPeopleVO.getContactNumber());
				influencingPeople.setEmail(influencingPeopleVO.getEmail());
				influencingPeople.setHamlet(hamletDAO.get(influencingPeopleVO.getHamletId()));
				influencingPeople.setParty(partyDAO.get(influencingPeopleVO.getPartyId()));
				influencingPeople.setCaste(influencingPeopleVO.getCast());
				influencingPeople.setOccupation(influencingPeopleVO.getOccupation());
				influencingPeople.setInfluencingScope(influencingPeopleVO.getInfluencingRange());
				if(influencingPeopleVO.getPosition() instanceof String){
					InfluencingPeoplePosition influencingPeoplePosition = new InfluencingPeoplePosition();						
					List<InfluencingPeoplePosition> influencePeop = influencingPeoplePositionDAO.getAll();
					int flag = 0,pos=0,counter=0;
					Long count = 0l;
					for(InfluencingPeoplePosition InfluencingPeople : influencePeop){		
						pos++;
						if(InfluencingPeople.getPosition().toString().equalsIgnoreCase(influencingPeopleVO.getPosition())){
							flag = 1;
						}else{					
						}		
						if(flag==1){
							counter++;
							if(counter==1){
								count = InfluencingPeople.getInfluencingPeoplePositionId();
							}
						}
					}			
					if(flag==1){						
						influencingPeople.setInfluencingPeoplePosition(influencingPeoplePositionDAO.get(count));
					}else{							
						influencingPeoplePosition.setPosition(influencingPeopleVO.getPosition().toUpperCase());
						influencingPeoplePosition = influencingPeoplePositionDAO.save(influencingPeoplePosition);
						influencingPeople.setInfluencingPeoplePosition(influencingPeoplePosition);	
					}
							
				}else{
					influencingPeople.setInfluencingPeoplePosition(influencingPeoplePositionDAO.get(new Long(influencingPeopleVO.getPosition())));
				}			
				influencingPeople = influencingPeopleDAO.save(influencingPeople);
			}
			});
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;
		}
	}
	
	public List<SelectOptionVO> getAllInfluencePeoplePositions(){
		try{
			List<SelectOptionVO> selectOptionVO = new ArrayList<SelectOptionVO>();
			List<InfluencingPeoplePosition>  result = influencingPeoplePositionDAO.getAll();
			SelectOptionVO selectOptionVo = new SelectOptionVO();
			selectOptionVo.setId(0l);
			selectOptionVo.setName("Select Position");
			selectOptionVO.add(selectOptionVo);
			for(InfluencingPeoplePosition influencingPeoplePosition : result){
				SelectOptionVO selectOption = new SelectOptionVO();
				selectOption.setId(influencingPeoplePosition.getInfluencingPeoplePositionId());
				selectOption.setName(influencingPeoplePosition.getPosition());
				selectOptionVO.add(selectOption);
			}
			return selectOptionVO;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<SelectOptionVO> getInfluenceRange(){
		try{
			List<SelectOptionVO> selectOptionVOs = new ArrayList<SelectOptionVO>();			
			selectOptionVOs.add(new SelectOptionVO(2l,IConstants.STATE_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(3l,IConstants.DISTRICT_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(4l,IConstants.CONSTITUENCY_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(5l,"MUNICIPAL-CORP-GMC"));
			selectOptionVOs.add(new SelectOptionVO(6l,IConstants.MANDAL_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(7l,IConstants.CENSUS_VILLAGE_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(8l,IConstants.WARD));
			selectOptionVOs.add(new SelectOptionVO(9l,IConstants.BOOTH));
			return selectOptionVOs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void hamletIdsQuery(){
	//	getHamletIdBasedOnDistrictNameMandalIdAndTownship(String districtName,String mandalName,String townshipName,String hamletName);
	}
	public void readAndSaveInfluencePeopleDataIntoDB(final File file){
		System.out.println("===============");
		System.out.println("Inside Service...........");
		System.out.println("File Path is ..."+file);
		System.out.println("===============");		
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {	
				try {
					Workbook workbook = Workbook.getWorkbook(file);
						int totalSheets = workbook.getNumberOfSheets();
						for(int totSheet=0;totSheet<totalSheets;totSheet++){
							Sheet sheet = workbook.getSheet(totSheet);
							Cell[] cells = sheet.getRow(1);
							for(int k = 0;k<=cells.length;k++){
								System.out.println(cells);
							}
							List hamletId = hamletDAO.getHamletIdBasedOnDistrictNameMandalIdAndTownship(cells[2].getContents(),cells[1].getContents(),cells[0].getContents(),cells[4].getContents());
							int rowCount = sheet.getRows();
							for(int i=4;i<=rowCount;i++){
								InfluencingPeople influencingPeople = new InfluencingPeople();
								Cell[] cell = sheet.getRow(i);
								influencingPeople.setFirstName(cell[4].getContents());
								influencingPeople.setLastName(cells[5].getContents());
								influencingPeople.setGender(cells[11].getContents());
								influencingPeople.setPhoneNo(cells[10].getContents());
								influencingPeople.setEmail(cells[12].getContents());
								influencingPeople.setHamlet(hamletDAO.get(new Long(hamletId.get(0).toString())));
								influencingPeople.setParty(partyDAO.get(influencingPeopleVO.getPartyId()));
								influencingPeople.setCaste(influencingPeopleVO.getCast());
								influencingPeople.setOccupation(influencingPeopleVO.getOccupation());
								influencingPeople.setInfluencingScope(influencingPeopleVO.getInfluencingRange());
								System.out.print(cells[0].getContents()+"\t");
							}			
						}
				} catch (BiffException e) {					
					e.printStackTrace();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
			});		
	}

	private Map<String, Long> influRangeAndValueMap;
	
	public InfluencingPeopleBeanVO saveInfluencePeopleInfo(InfluencingPeopleBeanVO influencingPeopleBeanVO1,
			Map<String, Long> influRangeAndValueMap1) {
		this.influencingPeopleBeanVO = influencingPeopleBeanVO1;
		this.influRangeAndValueMap = influRangeAndValueMap1;
		
		InfluencingPeopleBeanVO obj = (InfluencingPeopleBeanVO)transactionTemplate.execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus status) {
				
				InfluencingPeopleBeanVO influencingPeopleVO = new InfluencingPeopleBeanVO();
				InfluencingPeople influencingPeople;
				UserAddress userAddress;
				
				if(influencingPeopleBeanVO.getWindowTask().equalsIgnoreCase("edit"))
				{
					 influencingPeople = influencingPeopleDAO.get(new Long(influencingPeopleBeanVO.getInfluencingPersonId().toString()));
					 userAddress = userAddressDAO.get(influencingPeople.getUserAddress().getUserAddressId());
				}
				else
				{
				 influencingPeople = new InfluencingPeople();
				 userAddress = new UserAddress();
				}
				ResultStatus resultStatus = new ResultStatus();
				try{
					influencingPeople.setRegistration(registrationDAO.get(new Long(influencingPeopleBeanVO.getRegistrationId())));
					influencingPeople.setFirstName(influencingPeopleBeanVO.getFirstName());
					influencingPeople.setLastName(influencingPeopleBeanVO.getLastName());
					influencingPeople.setMiddleName(influencingPeopleBeanVO.getMiddleName());
					influencingPeople.setFatherOrSpouseName(influencingPeopleBeanVO.getFatherOrSpouseName());
					influencingPeople.setGender(influencingPeopleBeanVO.getGender());
					influencingPeople.setPhoneNo(influencingPeopleBeanVO.getMobile());
					influencingPeople.setEmail(influencingPeopleBeanVO.getEmail());
					
					influencingPeople.setParty(partyDAO.get(new Long(influencingPeopleBeanVO.getParty())));
					influencingPeople.setCaste(influencingPeopleBeanVO.getCast());
					influencingPeople.setOccupation(influencingPeopleBeanVO.getOccupation());
					influencingPeople.setInfluencingPeoplePosition(influencingPeoplePositionDAO.get(new Long(influencingPeopleBeanVO.getPosition())));
										
					userAddress.setCountry(countryDAO.get(new Long(1L)));
					userAddress.setState(stateDAO.get(new Long(influencingPeopleBeanVO.getState())));
					userAddress.setDistrict(districtDAO.get(new Long(influencingPeopleBeanVO.getDistrict())));
					userAddress.setConstituency(constituencyDAO.get(new Long(influencingPeopleBeanVO.getConstituency())));
					userAddress.setHouseNo(influencingPeopleBeanVO.getHouseNo());
					userAddress.setStreet(influencingPeopleBeanVO.getStreetName());
					userAddress.setPinCode(influencingPeopleBeanVO.getPincode());
										
					influencingPeople.setInfluencingScope(influencingPeopleBeanVO.getInfluencingRange());
					influencingPeople.setInfluencingScopeValue(influencingPeopleBeanVO.getInfluencingScopeValue());
					
					if (IConstants.URBAN_TYPE.equals(influencingPeopleBeanVO.getMandal().substring(0,1)))
					{
						List localElectionBodies = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.parseLong(influencingPeopleBeanVO.getMandal().substring(1)));
						userAddress.setLocalElectionBody(localElectionBodyDAO.get((Long)(localElectionBodies.get(0))));
						userAddress.setWard(constituencyDAO.get(new Long(influencingPeopleBeanVO.getWardOrHamlet().substring(1))));
						userAddress.setTehsil(null);
						userAddress.setHamlet(null);
						influencingPeople.setHamlet(null);
					}
					
					if (IConstants.RURAL_TYPE.equals(influencingPeopleBeanVO.getMandal().substring(0,1)))
					{
						userAddress.setTehsil(tehsilDAO.get(new Long(influencingPeopleBeanVO.getMandal().substring(1))));
						userAddress.setHamlet(hamletDAO.get(new Long(influencingPeopleBeanVO.getWardOrHamlet().substring(1))));
						influencingPeople.setHamlet(hamletDAO.get(new Long(influencingPeopleBeanVO.getWardOrHamlet().substring(1))));
						userAddress.setLocalElectionBody(null);
						userAddress.setWard(null);
					}
										
					userAddress = userAddressDAO.save(userAddress);
					
					influencingPeople.setUserAddress(userAddress);
					
					influencingPeople = influencingPeopleDAO.save(influencingPeople);
					
					influencingPeopleVO.setFirstName(influencingPeople.getFirstName());
					influencingPeopleVO.setLastName(influencingPeople.getLastName());
					
					resultStatus.setResultPartial(false);
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}catch(Exception ex){
					influencingPeopleVO.setExceptionEncountered(ex);
					ex.printStackTrace();
					resultStatus.setExceptionEncountered(ex);
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				}
					return influencingPeopleVO;		
			}
		});
		
		return obj;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getDetailsByInfluencingPersonId(java.lang.Long)
	 */
	public InfluencingPeopleBeanVO getDetailsByInfluencingPersonId(Long influencingPersonId)
	{
		try{
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			
			List<Object[]> influPerson = influencingPeopleDAO.getDetailsByInfluencingPersonId(influencingPersonId);
			
			for(int i=0;i<influPerson.size();i++){
				Object[] parms = (Object[])influPerson.get(i);
				
				influencingPeopleBeanVO.setFirstName(parms[0]!=null?parms[0].toString():"");
				influencingPeopleBeanVO.setMiddleName(parms[1]!=null?parms[1].toString():"");
				influencingPeopleBeanVO.setLastName(parms[2]!=null?parms[2].toString():"");
				influencingPeopleBeanVO.setFatherOrSpouseName(parms[3]!=null?parms[3].toString():"");
				influencingPeopleBeanVO.setGender(parms[4]!=null?parms[4].toString():"");
				influencingPeopleBeanVO.setMobile(parms[5]!=null?parms[5].toString():"");
				influencingPeopleBeanVO.setEmail(parms[6]!=null?parms[6].toString():"");
				influencingPeopleBeanVO.setOccupation(parms[7]!=null?parms[7].toString():"");
				influencingPeopleBeanVO.setOccupationType(occupationDAO.get(new Long(parms[7].toString())).getOccupation());
				influencingPeopleBeanVO.setParty(parms[8]!=null?parms[8].toString():"");
				influencingPeopleBeanVO.setPartyName(partyDAO.get(new Long(parms[8].toString())).getShortName());
				influencingPeopleBeanVO.setCast(parms[9]!=null?parms[9].toString():"");
				influencingPeopleBeanVO.setCastType(socialCategoryDAO.get(new Long(parms[9].toString())).getCategory());
				influencingPeopleBeanVO.setPosition(parms[10]!=null?parms[10].toString():"");
				influencingPeopleBeanVO.setPositionType(influencingPeoplePositionDAO.get(new Long(parms[10].toString())).getPosition());
				
		
				UserAddress userAddress = userAddressDAO.get(Long.parseLong(parms[13].toString()));
				
				influencingPeopleBeanVO.setState(userAddress.getState().getStateId().toString());
				influencingPeopleBeanVO.setStateName(userAddress.getState().getStateName().toString());
				influencingPeopleBeanVO.setDistrict(userAddress.getDistrict().getDistrictId().toString());
				influencingPeopleBeanVO.setDistrictName(userAddress.getDistrict().getDistrictName().toString());
				influencingPeopleBeanVO.setConstituency(userAddress.getConstituency().getConstituencyId().toString());
				influencingPeopleBeanVO.setConstituencyName(userAddress.getConstituency().getName().toString());
				influencingPeopleBeanVO.setHouseNo(userAddress.getHouseNo());
				influencingPeopleBeanVO.setStreetName(userAddress.getStreet());
				influencingPeopleBeanVO.setPincode(userAddress.getPinCode());
				
				String influRange = (parms[11]!=null?parms[11].toString():"");
				String influScopeValue = (parms[12]!=null?parms[12].toString():"");
				
				influencingPeopleBeanVO.setInfluencingRangeScope(influRange);
				
				if(influRange.equalsIgnoreCase(IConstants.STATE_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("2");
					influencingPeopleBeanVO.setInfluencingScopeValue((stateDAO.get(new Long(influScopeValue)).getStateName()));
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("3");
					influencingPeopleBeanVO.setInfluencingScopeValue(districtDAO.get(new Long(influScopeValue)).getDistrictName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("4");
					influencingPeopleBeanVO.setInfluencingScopeValue(constituencyDAO.get(new Long(influScopeValue)).getName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("5");
				    influencingPeopleBeanVO.setInfluencingScopeValue(localElectionBodyDAO.get(new Long(influScopeValue)).getName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.MANDAL_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("6");
					influencingPeopleBeanVO.setInfluencingScopeValue(tehsilDAO.get(new Long(influScopeValue)).getTehsilName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.CENSUS_VILLAGE_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("7");
					influencingPeopleBeanVO.setInfluencingScopeValue(hamletDAO.get(new Long(influScopeValue)).getHamletName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.WARD)){
					influencingPeopleBeanVO.setInfluencingRange("8");
					influencingPeopleBeanVO.setInfluencingScopeValue(constituencyDAO.get(new Long(influScopeValue)).getName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.BOOTH)){
					influencingPeopleBeanVO.setInfluencingRange("9");
					influencingPeopleBeanVO.setInfluencingScopeValue(boothDAO.get(new Long(influScopeValue)).getPartName());
				}
				
				if(userAddress.getLocalElectionBody() != null )
				{
					influencingPeopleBeanVO.setAreaType(IConstants.URBAN_TYPE);
					Long MandalId = userAddress.getLocalElectionBody().getLocalElectionBodyId();
					influencingPeopleBeanVO.setMandal(IConstants.URBAN_TYPE+assemblyLocalElectionBodyDAO.getAssemblyLocalElectionBodyId(MandalId).get(0).toString());
					influencingPeopleBeanVO.setMandalName(userAddress.getLocalElectionBody().getName().toString());
					influencingPeopleBeanVO.setWardOrHamlet(userAddress.getWard().getConstituencyId().toString());
					influencingPeopleBeanVO.setWardOrHamletName(userAddress.getWard().getName());
				}
				
				if(userAddress.getTehsil() != null)
				{
					influencingPeopleBeanVO.setAreaType(IConstants.RURAL_TYPE);
					influencingPeopleBeanVO.setMandal(IConstants.RURAL_TYPE+userAddress.getTehsil().getTehsilCode().toString());
					influencingPeopleBeanVO.setMandalName(userAddress.getTehsil().getTehsilName().toString());
					influencingPeopleBeanVO.setWardOrHamlet(IConstants.RURAL_TYPE+userAddress.getHamlet().getHamletCode().toString());
					influencingPeopleBeanVO.setWardOrHamletName(userAddress.getHamlet().getHamletName().toString());
				}
										
			}
		 return influencingPeopleBeanVO; 	
		}
		catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;	
		}	
	}
	
	public Integer deleteInfluencingPeople(Long influencingPeopleId){
		
		InfluencingPeople  influencingPeople = influencingPeopleDAO.get(influencingPeopleId);
		Long UserAddressId = influencingPeople.getUserAddress().getUserAddressId();
		Integer deletedRows = influencingPeopleDAO.deleteInfluencingPeopleById(influencingPeopleId);
		userAddressDAO.deleteInfluencingPeopleById(UserAddressId);
		return deletedRows;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getInfluencingPeopleOverviewDetails(java.lang.Long)
	 * Influencing People Regionwise and Influence Scopewise Complete Overview Retrieval For A User
	 */
	public ConstituencyManagementDataVO getInfluencingPeopleOverviewDetails(
			Long userId) {
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		if(log.isDebugEnabled())
			log.debug(" Getting Complete Overview Details Of Influencing People ..");
		
		try{
			Registration user = registrationDAO.get(userId);
			String accessValue = user.getAccessValue();
			
			//State Level Access User
			if(user.getAccessType().equals(IConstants.STATE)){
				Long stateId = new Long(accessValue);
				constituencyManagementDataVO = getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,stateId,true,IConstants.INFLUENCING_PEOPLE,0L,"");
			}
			//District Level Access User
			else if(user.getAccessType().equals(IConstants.DISTRICT)){
				Long districtId = new Long(accessValue);
				constituencyManagementDataVO = getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,districtId,true,IConstants.INFLUENCING_PEOPLE,0L,"");
			}
			//MP Access User
			else if(user.getAccessType().equals(IConstants.MP)){
				
			}
			//MLA Access User
			else if(user.getAccessType().equals(IConstants.MLA)){
				Long constituencyId = new Long(accessValue);
				constituencyManagementDataVO = getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId, constituencyId, true,IConstants.INFLUENCING_PEOPLE,0L,"");
			}
			
			
		}catch(Exception ex){
			log.error("Exception Raised In Influencing People Retrieval :" + ex);
			ex.printStackTrace();
			constituencyManagementDataVO.setExceptionEncountered(ex);
			constituencyManagementDataVO.setResultCode(ResultCodeMapper.FAILURE);
			constituencyManagementDataVO.setExceptionMsg(ex.getMessage());
		}
			
	 return constituencyManagementDataVO;
	}
	
	/*
	 * Influencing People Details In Entire State and its consecutive sub regions
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyManagementDataVO getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long stateId,Boolean isScopeData,String moduleType,Long categoryId,String categoryType){
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory(moduleType);
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire state
		List infPeopleCount = null;
		if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE)){
		   infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInState(userId, stateId);
		}else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS)){
			infPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInState(userId, stateId, categoryId);
		}
		
		State state = stateDAO.get(stateId);
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(stateId);
			regionWiseOverview.setRegionType(IConstants.STATE);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(state.getStateName());
			regionWiseOverview.setRegionTitle(categoryType);
			regionWiseOverview.setRegionTitleId(categoryId);
			
			//district wise influencing people details
			List<SelectOptionVO> districtsInState = staticDataService.getDistricts(stateId);
			if(districtsInState != null && districtsInState.size() > 0)
			subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(districtsInState,IConstants.DISTRICT);
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
			
			 List subRegionsInfPeopleCount = null;
			if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE)){
			    subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInDistrictsByState(userId, stateId);
			}else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS)){
				subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInDistrictsByState(userId, stateId, categoryId);
			}
				if(subRegionsDetailsMap != null && !subRegionsDetailsMap.isEmpty())
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,subRegionsDetailsMap);
				else
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,IConstants.DISTRICT);
				
				if(subRegionsInfPeopleList != null && subRegionsInfPeopleList.size() > 0)
				 regionWiseOverview.setSubRegionWiseOverview(subRegionsInfPeopleList);
			
			constituencyManagementDataVO.setRegionWiseOverview(regionWiseOverview);
			
			//To get Influencing people details by scope
			if(isScopeData){
				 constituencyManagementDataVO.setInfluenceScopeOverview(getInfluencingPeopleByInfluenceScope(userId));
			}
		}
		
	 return constituencyManagementDataVO;
	}
	
	/*
	 * Map That Initializes With Region wise Influencing people count
	 */
	public Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> getRegionsDataInitializedMapWithInfluencingPeopleCount(List<SelectOptionVO> locationDetails,String regionType){
		
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		if(locationDetails != null && locationDetails.size() > 0){
			
			subRegionsDetailsMap = new HashMap<Long,ConstituencyManagementSubRegionWiseOverviewVO>();
			for(SelectOptionVO loc:locationDetails){
				ConstituencyManagementSubRegionWiseOverviewVO subRegionDetailsVO = new ConstituencyManagementSubRegionWiseOverviewVO();
				subRegionDetailsVO.setSubRegionId(loc.getId());
				subRegionDetailsVO.setSubRegionName(loc.getName());
				subRegionDetailsVO.setSubRegionType(regionType);
				subRegionDetailsVO.setCountValue(0L);
				
				subRegionsDetailsMap.put(loc.getId(), subRegionDetailsVO);
			}
		}
		
	 return subRegionsDetailsMap;
	}
	
	/*
	 * Sub Regions Processed Data To VO
	 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyManagementSubRegionWiseOverviewVO> getSubRegionsProcessedDetailsToVO(List subRegionsData,Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> totSubRegionsMap){
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> constituencyManagementSubRegionWiseList = null;
		if(subRegionsData != null && totSubRegionsMap != null && !totSubRegionsMap.isEmpty()){
			Iterator lstItr = subRegionsData.listIterator();
			while(lstItr.hasNext()){
				Object[] params = (Object[])lstItr.next();
				Long countVal = (Long)params[0];
				Long regionId = (Long)params[1];
				
				if(totSubRegionsMap.containsKey(regionId)){
					ConstituencyManagementSubRegionWiseOverviewVO subRegionWiseOverviewVO = totSubRegionsMap.get(regionId);
					subRegionWiseOverviewVO.setCountValue(countVal);
				}
			}
			
			//Process the map finally to get end result
			if(!totSubRegionsMap.isEmpty()){
				constituencyManagementSubRegionWiseList = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
				Set<Long> regionIds = totSubRegionsMap.keySet();
				for(Long region:regionIds){
					ConstituencyManagementSubRegionWiseOverviewVO subRegionWiseOverviewVO = totSubRegionsMap.get(region);
					constituencyManagementSubRegionWiseList.add(subRegionWiseOverviewVO);
				}
			}
			
		}
		
	 return constituencyManagementSubRegionWiseList;
	}
	
	/*
	 * Sub Regions Processed Data To VO
	 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyManagementSubRegionWiseOverviewVO> getSubRegionsProcessedDetailsToVO(List subRegionsData,String regionType){
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> constituencyManagementSubRegionWiseList = null;
		if(subRegionsData != null){
			
			constituencyManagementSubRegionWiseList = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
			Iterator lstItr = subRegionsData.listIterator();
			while(lstItr.hasNext()){
				Object[] params = (Object[])lstItr.next();
				Long countVal = (Long)params[0];
				Long regionId = (Long)params[1];
				
				ConstituencyManagementSubRegionWiseOverviewVO subRegionWiseOverviewVO = new ConstituencyManagementSubRegionWiseOverviewVO();
				subRegionWiseOverviewVO.setSubRegionId(regionId);
				subRegionWiseOverviewVO.setSubRegionName(getRegionNameBasedOnScope(regionType,regionId.toString()));
				subRegionWiseOverviewVO.setSubRegionType(regionType);
				subRegionWiseOverviewVO.setCountValue(countVal);
				
				constituencyManagementSubRegionWiseList.add(subRegionWiseOverviewVO);
			}
								
		}
		
	 return constituencyManagementSubRegionWiseList;
	}
	
	/*
	 * Get Influencing People Details By Influence Scope
	 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getInfluencingPeopleByInfluenceScope(Long userId){
		
		List<ConstituencyManagementInfluenceScopeOverviewVO> influenceScopeOverviewVOList = null;
		boolean stateFlag = false;
		if(userId != null){
			
			List scopeDetailsList = influencingPeopleDAO.getTotalInfluencingPeopleCountByInfluencingScope(userId);
			
			if(scopeDetailsList != null && scopeDetailsList.size() > 0){
				influenceScopeOverviewVOList = new ArrayList<ConstituencyManagementInfluenceScopeOverviewVO>();
				
				for(int i=0;i<scopeDetailsList.size();i++){
					
					ConstituencyManagementInfluenceScopeOverviewVO influenceScopeOverviewVO = new ConstituencyManagementInfluenceScopeOverviewVO();
					Object[] params = (Object[])scopeDetailsList.get(i);
					String scope = (String)params[1];
					Long countVal = (Long)params[0];
					
					influenceScopeOverviewVO.setInfluenceScope(scope);
					influenceScopeOverviewVO.setCountValue(countVal);
					
					List scopeRegionsList  = influencingPeopleDAO.getTotalInfluencingPeopleCountByInfluencingScope(userId, scope);
					if(scopeRegionsList != null && scopeRegionsList.size() > 0)
						influenceScopeOverviewVO.setInfluenceScopeDetails(getInfluencingScopeRegionDetails(scopeRegionsList,scope));
					if(scope.equalsIgnoreCase(IConstants.STATE)){
					    influenceScopeOverviewVOList.add(0,influenceScopeOverviewVO);
					    stateFlag = true;
					}
					else {
						if(stateFlag == true && scope.equalsIgnoreCase(IConstants.DISTRICT))
							influenceScopeOverviewVOList.add(1,influenceScopeOverviewVO);
						else if(stateFlag == false && scope.equalsIgnoreCase(IConstants.DISTRICT))
						    influenceScopeOverviewVOList.add(0,influenceScopeOverviewVO);
						else
							influenceScopeOverviewVOList.add(influenceScopeOverviewVO);
					}
				}
			}
		}
		
	 return influenceScopeOverviewVOList;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ConstituencyManagementInfluenceScopeDetailsVO> getInfluencingScopeRegionDetails(List scopeRegionsList,String infScope){
		
		List<ConstituencyManagementInfluenceScopeDetailsVO> influenceScopeDetailsVO = null;
		if(scopeRegionsList != null && scopeRegionsList.size() > 0){
			
			influenceScopeDetailsVO = new ArrayList<ConstituencyManagementInfluenceScopeDetailsVO>();
			Iterator lstItr = scopeRegionsList.listIterator();
			while(lstItr.hasNext()){
				
				ConstituencyManagementInfluenceScopeDetailsVO influenceScopeDetails = new ConstituencyManagementInfluenceScopeDetailsVO();
				Object[] values = (Object[])lstItr.next();
				Long countVal = (Long)values[0];
				String regionId = (String)values[1];
				
				influenceScopeDetails.setCountValue(countVal);
				influenceScopeDetails.setInfluenceScopeRegionId(new Long(regionId));
				influenceScopeDetails.setInfluenceScopeRegion(getRegionNameBasedOnScope(infScope,regionId));
				
				influenceScopeDetailsVO.add(influenceScopeDetails);
			}
		}
		
	 return influenceScopeDetailsVO;
	}
	
	public String getRegionNameBasedOnScope(String infScope,String regionId){
		
		if(infScope.equalsIgnoreCase(IConstants.STATE)){
			State state = stateDAO.get(new Long(regionId));
			return state.getStateName();
		}else if(infScope.equalsIgnoreCase(IConstants.DISTRICT)){
			District district = districtDAO.get(new Long(regionId));
			return district.getDistrictName();
		}else if(infScope.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			Constituency constituency = constituencyDAO.get(new Long(regionId));
			return constituency.getName();
		}else if(infScope.equalsIgnoreCase("MUNCIPALITY/CORPORATION") || infScope.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
			LocalElectionBody localBody = localElectionBodyDAO.get(new Long(regionId));
			String localBodyName = localBody.getName() + " (" + localBody.getElectionType().getElectionType() + " )";
			return localBodyName;
		}else if(infScope.equalsIgnoreCase(IConstants.MANDAL) || infScope.equalsIgnoreCase(IConstants.TEHSIL)){
			Tehsil tehsil = tehsilDAO.get(new Long(regionId));
			return tehsil.getTehsilName();
		}else if(infScope.equalsIgnoreCase(IConstants.VILLAGE) || infScope.equalsIgnoreCase(IConstants.HAMLET)){
			Hamlet hamlet = hamletDAO.get(new Long(regionId));
			return hamlet.getHamletName();
		}else if(infScope.equalsIgnoreCase(IConstants.WARD)){
			Constituency constituency = constituencyDAO.get(new Long(regionId));
			return constituency.getName();
		}
	 return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(java.lang.Long, java.lang.Long, java.lang.Boolean)
	 * Influencing People Details In Entire District and its consecutive sub regions
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyManagementDataVO getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(
			Long userId, Long districtId, Boolean isScopeData,String moduleType,Long categoryId,String categoryType) {
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory(moduleType);
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire District
		List infPeopleCount = null;
		if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
		    infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInDistrict(userId, districtId);
		else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
			infPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInDistrict(userId, districtId, categoryId);
		
		District district = districtDAO.get(districtId);
		
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(districtId);
			regionWiseOverview.setRegionType(IConstants.DISTRICT);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(district.getDistrictName());
			regionWiseOverview.setRegionTitle(categoryType);
			regionWiseOverview.setRegionTitleId(categoryId);
			
			
			//constituencies wise influencing people details
			List<SelectOptionVO> constituenciesInDistrict = staticDataService.getLatestAssemblyConstituenciesInDistrict(districtId);
			if(constituenciesInDistrict != null && constituenciesInDistrict.size() > 0)
			subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(constituenciesInDistrict,IConstants.CONSTITUENCY);
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
			
			List subRegionsInfPeopleCount = null;
			    if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				    subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInConstituenciesByDistrict(userId, districtId);
			    else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
			    	subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInConstituencysByDistrict(userId, districtId, categoryId);
			    	
				if(subRegionsDetailsMap != null && !subRegionsDetailsMap.isEmpty())
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,subRegionsDetailsMap);
				else
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,IConstants.CONSTITUENCY);	
				
				if(subRegionsInfPeopleList != null && subRegionsInfPeopleList.size() > 0)
				 regionWiseOverview.setSubRegionWiseOverview(subRegionsInfPeopleList);
			
			constituencyManagementDataVO.setRegionWiseOverview(regionWiseOverview);
			
			//To get Influencing people details by scope
			if(isScopeData)
				constituencyManagementDataVO.setInfluenceScopeOverview(getInfluencingPeopleByInfluenceScope(userId));
		}
		
	 return constituencyManagementDataVO;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(java.lang.Long, java.lang.Long, java.lang.Boolean)
	 * Influencing People Details In Entire Constituency and its consecutive sub regions
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyManagementDataVO getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(
			Long userId, Long constituencyId, Boolean isScopeData,String moduleType,Long categoryId,String categoryType) {
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory(moduleType);
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire Constituency
		List infPeopleCount = null;
		if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
		    infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInConstituency(userId, constituencyId);
		else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
			infPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInConstituency(userId, constituencyId, categoryId);
		
		Constituency constituency = constituencyDAO.get(constituencyId);
		
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(constituencyId);
			regionWiseOverview.setRegionType(IConstants.CONSTITUENCY);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(constituency.getName());
			regionWiseOverview.setRegionTitle(categoryType);
			regionWiseOverview.setRegionTitleId(categoryId);
			
			List<SelectOptionVO> subRegionsList = null;
            List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
            List subRegionsInfPeopleCount = null;
			
            //for urban constituency
			if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
				subRegionsList = regionServiceDataImp.getLocalElectionBodiesInConstituency(constituencyId, IConstants.DELIMITATION_YEAR.toString());
				if(subRegionsList != null && subRegionsList.size() > 0)
					subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList,IConstants.LOCAL_BODY_ELECTION);
				
				if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				    subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(userId, constituencyId);
				else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
					subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInLocalBodysByConstituency(userId, constituencyId, categoryId);
				
			}//for rural constituency
			else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
				subRegionsList = regionServiceDataImp.getTehsilsInAConstituency(constituencyId);
				if(subRegionsList != null && subRegionsList.size() > 0)
					subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList,IConstants.TEHSIL);
				
				if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				    subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInTehsilsByConstituency(userId, constituencyId);
				else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
					subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInTehsilsByConstituency(userId, constituencyId, categoryId);
				
				
			}//for urban rural constituency
			else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
				//for tehsils
				subRegionsList = regionServiceDataImp.getLocalElectionBodiesInConstituency(constituencyId, IConstants.DELIMITATION_YEAR.toString());
				if(subRegionsList != null && subRegionsList.size() > 0)
					subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList,IConstants.LOCAL_BODY_ELECTION);
				//for local bodys
				Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap1 = null;
				List<SelectOptionVO> subRegionsList1 = regionServiceDataImp.getTehsilsInAConstituency(constituencyId);
				if(subRegionsList1 != null && subRegionsList1.size() > 0)
					subRegionsDetailsMap1 = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList1,IConstants.TEHSIL);
				
				if(subRegionsDetailsMap1 != null && !subRegionsDetailsMap1.isEmpty())
				 subRegionsDetailsMap.putAll(subRegionsDetailsMap1);
				
				if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				    subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInTehsilsByConstituency(userId, constituencyId);
				else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
					subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInTehsilsByConstituency(userId, constituencyId, categoryId);
				
				List subRegionsInfPeopleCount1 = null;
				if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				   subRegionsInfPeopleCount1 = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(userId, constituencyId);
				else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
				   subRegionsInfPeopleCount1 = personalUserGroupDAO.getTotalCountOfLocalGroupsInLocalBodysByConstituency(userId, constituencyId, categoryId);
					
				if(subRegionsInfPeopleCount1 != null && subRegionsInfPeopleCount1.size() > 0)
				 subRegionsInfPeopleCount.addAll(subRegionsInfPeopleCount1);
			}
			
		    if(subRegionsDetailsMap != null && !subRegionsDetailsMap.isEmpty())
			 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,subRegionsDetailsMap);
			else
			 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,"");	
			
			if(subRegionsInfPeopleList != null && subRegionsInfPeopleList.size() > 0)
			 regionWiseOverview.setSubRegionWiseOverview(subRegionsInfPeopleList);
		
		    constituencyManagementDataVO.setRegionWiseOverview(regionWiseOverview);
			
			//To get Influencing people details by scope
			if(isScopeData)
				constituencyManagementDataVO.setInfluenceScopeOverview(getInfluencingPeopleByInfluenceScope(userId));
		}
		
	 return constituencyManagementDataVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public ConstituencyManagementRegionWiseCompleteDataVO getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(
			Long userId, Long regionId, String regionType,String moduleType,Long categoryId,String categoryType) {

		ConstituencyManagementRegionWiseCompleteDataVO constituencyManagementRegionWiseCompleteDataVO = new ConstituencyManagementRegionWiseCompleteDataVO();
		List<ConstituencyManagementRegionWiseOverviewVO> constituencyManagementRegionWiseOverviewVOList = new ArrayList<ConstituencyManagementRegionWiseOverviewVO>();
		List<ConstituencyManagementSubRegionWiseOverviewVO> regionsList = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		
		try{
			if(regionType.equalsIgnoreCase(IConstants.STATE)){
				List<SelectOptionVO> districtsInState = staticDataService.getDistricts(regionId);
			    for(SelectOptionVO district:districtsInState){
			    	ConstituencyManagementDataVO constituencyManagementDataVO = getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,district.getId(),false,moduleType,categoryId,categoryType);
			    	if(constituencyManagementDataVO != null)
			    		constituencyManagementRegionWiseOverviewVOList.add(constituencyManagementDataVO.getRegionWiseOverview());
			    }
			    regionsList.addAll(getRegionsListData(districtsInState,IConstants.DISTRICT));
			}else if(regionType.equalsIgnoreCase(IConstants.DISTRICT)){
				List<SelectOptionVO> constituenciesInDistrict = staticDataService.getLatestAssemblyConstituenciesInDistrict(regionId);
				for(SelectOptionVO consti:constituenciesInDistrict){
					ConstituencyManagementDataVO constituencyManagementDataVO = getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId, consti.getId(), false,moduleType,categoryId,categoryType);
					if(constituencyManagementDataVO != null)
						constituencyManagementRegionWiseOverviewVOList.add(constituencyManagementDataVO.getRegionWiseOverview());
				}
				regionsList.addAll(getRegionsListData(constituenciesInDistrict,IConstants.CONSTITUENCY));
			}else if(regionType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				
				Constituency constituency  = constituencyDAO.get(regionId);
				List<SelectOptionVO> mandalsInConstituency = null;
				List<SelectOptionVO> localBodysInConstituency = null;
				
				if(constituency.getAreaType() == null || constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL) || constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
					mandalsInConstituency = regionServiceDataImp.getTehsilsInAConstituency(regionId);
					if(mandalsInConstituency != null){
						for(SelectOptionVO subRegions:mandalsInConstituency){
							ConstituencyManagementDataVO constituencyManagementDataVO = getMandalRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,subRegions.getId(),false,moduleType,categoryId,categoryType);
							if(constituencyManagementDataVO != null)
								constituencyManagementRegionWiseOverviewVOList.add(constituencyManagementDataVO.getRegionWiseOverview());
						}
					}
					regionsList.addAll(getRegionsListData(mandalsInConstituency,IConstants.TEHSIL));
				}if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN) || constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
					localBodysInConstituency = regionServiceDataImp.getLocalElectionBodiesInConstituency(regionId, IConstants.DELIMITATION_YEAR.toString());
					if(localBodysInConstituency != null){
						for(SelectOptionVO subRegions:localBodysInConstituency){
							ConstituencyManagementDataVO constituencyManagementDataVO = getLocalELectionBodyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,subRegions.getId(),false,moduleType,categoryId,categoryType);
							if(constituencyManagementDataVO != null)
								constituencyManagementRegionWiseOverviewVOList.add(constituencyManagementDataVO.getRegionWiseOverview());
						}
					}
					regionsList.addAll(getRegionsListData(localBodysInConstituency,IConstants.LOCAL_BODY_ELECTION));
				}
				
			}
			constituencyManagementRegionWiseCompleteDataVO.setRegionsList(regionsList);
			constituencyManagementRegionWiseCompleteDataVO.setRegionWiseOverview(constituencyManagementRegionWiseOverviewVOList);
			
		}catch(Exception ex){
			log.error(" Exception Raised In Influencing People Retrieval :" + ex);
			ex.printStackTrace();
			ResultStatus rs = new ResultStatus();
			rs.setExceptionEncountered(ex);
			rs.setResultCode(ResultCodeMapper.FAILURE);
		}
		
	 return constituencyManagementRegionWiseCompleteDataVO;
	}
	
	public List<ConstituencyManagementSubRegionWiseOverviewVO> getRegionsListData(List<SelectOptionVO> regionsListOption,String regionType){
		
		List<ConstituencyManagementSubRegionWiseOverviewVO> regionList = new ArrayList<ConstituencyManagementSubRegionWiseOverviewVO>();
		for(SelectOptionVO option:regionsListOption){
			ConstituencyManagementSubRegionWiseOverviewVO regionVO = new ConstituencyManagementSubRegionWiseOverviewVO();
			regionVO.setSubRegionId(option.getId());
			regionVO.setSubRegionName(option.getName());
			regionVO.setSubRegionType(regionType);
			
			regionList.add(regionVO);
		}
		
	 return regionList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getInfluencingPeopleDetailsByRegion(java.lang.Long, java.lang.String)
	 * Influencing People Details By Location/Region
	 */
	@SuppressWarnings("unchecked")
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByRegion(Long userId,
			Long regionId, String regionType) {
		
		log.debug("Getting influencing People Details Region Wise ...");
		List<InfluencingPeopleDetailsVO> influencingPeopleDetailsList = new ArrayList<InfluencingPeopleDetailsVO>();
		try{
			
			List influencingPeopleList = null;
			List influencingPeopleAddress = null;
			
			if(regionType.equalsIgnoreCase(IConstants.STATE)){
				
				State state = stateDAO.get(new Long(regionId));
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInState(userId, state.getStateId());
				influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInState(userId, state.getStateId());
				
				//Process and set details to VO
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
					influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.STATE,IConstants.DISTRICT,0L,"","");	
				
			}else if(regionType.equalsIgnoreCase(IConstants.DISTRICT)){
				
				District district = districtDAO.get(new Long(regionId));
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInDistrict(userId, district.getDistrictId());
				influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInDistrict(userId, district.getDistrictId());
				
				//Process and set details to VO
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
					influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.DISTRICT,IConstants.CONSTITUENCY,0L,"","");
				
			}else if(regionType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				
				Constituency constituency = constituencyDAO.get(new Long(regionId));
				
				if(constituency.getAreaType() == null || constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
				 influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInConstituency(userId, constituency.getConstituencyId());
				 influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInConstituency(userId, constituency.getConstituencyId());
				
					//Process and set details to VO
					if(influencingPeopleList != null && influencingPeopleList.size() > 0)
						influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.CONSTITUENCY,IConstants.TEHSIL,0L,"","");
				}
				else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
				 influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInConstituencyByLocalBody(userId, constituency.getConstituencyId());
				 influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInConstituencyByLocalBody(userId, constituency.getConstituencyId());
					
					//Process and set details to VO
					if(influencingPeopleList != null && influencingPeopleList.size() > 0)
						influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.CONSTITUENCY,IConstants.LOCAL_BODY_ELECTION,0L,"","");
				}
				else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
					
					//rural areas
					influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInConstituency(userId, constituency.getConstituencyId());
					influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInConstituency(userId, constituency.getConstituencyId());
					
					//Process and set details to VO
					if(influencingPeopleList != null && influencingPeopleList.size() > 0)
						influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.CONSTITUENCY,IConstants.TEHSIL,0L,"","");
					
					//for urban
                    List influencingPeopleList1 = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInConstituencyByLocalBody(userId, constituency.getConstituencyId());
					List influencingPeopleAddress1 = influencingPeopleDAO.getTotalInfluencingPeopleAddressInConstituencyByLocalBody(userId, constituency.getConstituencyId());
					//Process and set details to VO
					if(influencingPeopleList1 != null && influencingPeopleList1.size() > 0){
						List<InfluencingPeopleDetailsVO> influencingPeopleDetailsList1 = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList1,influencingPeopleAddress1,IConstants.CONSTITUENCY,IConstants.LOCAL_BODY_ELECTION,0L,"","");
						influencingPeopleDetailsList.addAll(influencingPeopleDetailsList1);
					}
				}
				
			}else if(regionType.equalsIgnoreCase("MUNCIPALITY/CORPORATION") || regionType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
				
				LocalElectionBody localBody = localElectionBodyDAO.get(new Long(regionId));
				String localBodyName = localBody.getName() + " (" + localBody.getElectionType().getElectionType() + " )";
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInLocalBodys(userId, localBody.getLocalElectionBodyId());
				influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInLocalBodys(userId, localBody.getLocalElectionBodyId());
				
				//Process and set details to VO
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
					influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.LOCAL_BODY_ELECTION,IConstants.WARD,0L,"","");
				
			}else if(regionType.equalsIgnoreCase(IConstants.MANDAL) || regionType.equalsIgnoreCase(IConstants.TEHSIL)){
				
				Tehsil tehsil = tehsilDAO.get(new Long(regionId));
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInTehsil(userId, tehsil.getTehsilId());
				influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInTehsil(userId, tehsil.getTehsilId());
				
				//Process and set details to VO
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
					influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.TEHSIL,IConstants.VILLAGE,0L,"","");
				
			}else if(regionType.equalsIgnoreCase(IConstants.VILLAGE) || regionType.equalsIgnoreCase(IConstants.HAMLET)){
				
				Hamlet hamlet = hamletDAO.get(new Long(regionId));
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInVillage(userId, hamlet.getHamletId());
				influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInVillage(userId, hamlet.getHamletId());
				
				//Process and set details to VO
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
					influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.HAMLET,"",0L,"","");
				
			}else if(regionType.equalsIgnoreCase(IConstants.WARD)){
				
				Constituency constituency = constituencyDAO.get(new Long(regionId));
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsInWard(userId, constituency.getConstituencyId());
				influencingPeopleAddress = influencingPeopleDAO.getTotalInfluencingPeopleAddressInWard(userId, constituency.getConstituencyId());
				
				//Process and set details to VO
				if(influencingPeopleList != null && influencingPeopleList.size() > 0)
					influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddress,IConstants.WARD,"",0L,"","");
				
			}
			
	    }catch(Exception ex){
			log.error("Exception Raised In Influencing People Details Retrieval :" + ex);
			ex.printStackTrace();
			InfluencingPeopleDetailsVO rs = new InfluencingPeopleDetailsVO();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			influencingPeopleDetailsList.add(rs);
		}
	
	 return influencingPeopleDetailsList;
	}
	
	/*
	 * Processed Influencing People Results To VO
	 */
	@SuppressWarnings("unchecked")
	public List<InfluencingPeopleDetailsVO> getProcessedInfluencingPeopleDetailsToVO(List influencingPeopleList,List influencingPeopleAddress,String regionType,String subRegionType,Long id,String scopeRegion,String scopeType){
		
		List<InfluencingPeopleDetailsVO> influencingPeopleDetailsVO = null;
		Map<Long,InfluencingPeopleDetailsVO> influencePeopleMap = new HashMap<Long,InfluencingPeopleDetailsVO>();
		
		if(influencingPeopleList != null && influencingPeopleList.size() > 0){
			
			influencingPeopleDetailsVO = new ArrayList<InfluencingPeopleDetailsVO>();
			int i=0;
			Iterator lstItr = influencingPeopleList.listIterator();
			while(lstItr.hasNext()){
				
				InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
								
				Object[] values = (Object[])lstItr.next();
				
				Long infPersonId = (Long)values[0];
				String fname = (String)values[1];
				String mname = (String)values[3];
				String lname = (String)values[2];
				
				influencingPeopleBeanVO.setInfluencingPersonId(infPersonId.toString());
				influencingPeopleBeanVO.setFirstName(fname = fname != null ? fname : "");
				influencingPeopleBeanVO.setMiddleName(mname = mname != null ? mname : "");
				influencingPeopleBeanVO.setLastName(lname = lname != null ? lname : "");
				influencingPeopleBeanVO.setInfluencingRangeScope((String)values[4]);
				influencingPeopleBeanVO.setInfluencingScopeValue(getRegionNameBasedOnScope((String)values[4],(String)values[5]));
				influencingPeopleBeanVO.setPartyName((String)values[7]);
				String casteCategoryId = (String)values[8];
				String occupationId = (String)values[9];
				
				SocialCategory caste = socialCategoryDAO.get(new Long(casteCategoryId));
				Occupation occupation = occupationDAO.get(new Long(occupationId));
				
				influencingPeopleBeanVO.setCast(caste.getCategory());
				influencingPeopleBeanVO.setOccupation(occupation.getOccupation());
				influencingPeopleBeanVO.setMobile((String)values[10]);
				influencingPeopleBeanVO.setGender((String)values[11]);
				influencingPeopleBeanVO.setEmail((String)values[12]);
				influencingPeopleBeanVO.setFatherOrSpouseName((String)values[13]);
				influencingPeopleBeanVO.setPosition((String)values[15]);
				
				State state = null;
				District district = null;
				Constituency constituency = null;
				Tehsil tehsil = null;
				LocalElectionBody localBody = null;
				Hamlet hamlet  = null;
				Constituency ward = null;
				
				Object infAddress = (Object)influencingPeopleAddress.get(i++);
				UserAddress userAddr = (UserAddress)infAddress;
				if(userAddr.getState() != null)
				state = userAddr.getState();
				if(userAddr.getDistrict() != null)
				district = userAddr.getDistrict();
				if(userAddr.getConstituency() != null)
				constituency = userAddr.getConstituency();
				if(userAddr.getTehsil() != null)
				tehsil = userAddr.getTehsil();
				if(userAddr.getLocalElectionBody() != null)
				localBody  = userAddr.getLocalElectionBody();
				if(userAddr.getHamlet() != null)
				hamlet = userAddr.getHamlet();
				if(userAddr.getWard() != null)
				ward = userAddr.getWard();
				
				influencingPeopleBeanVO.setState(state.getStateName());
				influencingPeopleBeanVO.setDistrict(district.getDistrictName());
				influencingPeopleBeanVO.setConstituency(constituency.getName());
				if(tehsil != null && tehsil.getTehsilId() != null){
					influencingPeopleBeanVO.setMandal(tehsil.getTehsilName());
					influencingPeopleBeanVO.setWardOrHamlet(hamlet.getHamletName());
				}else if(localBody != null && localBody.getLocalElectionBodyId() != null){
					String localBodyName = localBody.getName() + " (" +localBody.getElectionType().getElectionType()+" )"; 
					influencingPeopleBeanVO.setMandal(localBodyName);
					influencingPeopleBeanVO.setWardOrHamlet(ward.getName());
				}
				
				InfluencingPeopleDetailsVO infPeopleDetailsVO = new InfluencingPeopleDetailsVO();
				List<InfluencingPeopleBeanVO> infPeopleDetails = new ArrayList<InfluencingPeopleBeanVO>();
				
				if(subRegionType.equalsIgnoreCase(IConstants.DISTRICT)){
					
					Long districtId = district.getDistrictId();
                    if(influencePeopleMap.isEmpty() || !influencePeopleMap.containsKey(districtId)){
                    	infPeopleDetailsVO.setRegionId(districtId);
                    	infPeopleDetailsVO.setRegionName(district.getDistrictName());
                    	infPeopleDetailsVO.setRegionType(IConstants.DISTRICT);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(districtId, infPeopleDetailsVO);
					}else if(influencePeopleMap.containsKey(districtId)){
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(districtId);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(districtId, infPeopleDetailsVO1);
					}
									
				}else if(subRegionType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
                    
					Long constiId = constituency.getConstituencyId();
					if(influencePeopleMap.isEmpty() || !influencePeopleMap.containsKey(constiId)){
						infPeopleDetailsVO.setRegionId(constiId);
                    	infPeopleDetailsVO.setRegionName(constituency.getName());
                    	infPeopleDetailsVO.setRegionType(IConstants.CONSTITUENCY);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(constiId,infPeopleDetailsVO);
					}else if(influencePeopleMap.containsKey(constiId)){
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(constiId);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(constiId, infPeopleDetailsVO1);
					}
                    
               	}else if(subRegionType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) || subRegionType.equalsIgnoreCase("MUNCIPALITY/CORPORATION")){
               		
               		Long localBodyId = localBody.getLocalElectionBodyId();
               		String type = localBody.getElectionType().getElectionType();
                    if(influencePeopleMap.isEmpty() || !influencePeopleMap.containsKey(localBodyId)){
                    	infPeopleDetailsVO.setRegionId(localBodyId);
                    	infPeopleDetailsVO.setRegionName(localBody.getName());
                    	infPeopleDetailsVO.setRegionType(type);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(localBodyId, infPeopleDetailsVO);
					}else if(influencePeopleMap.containsKey(localBodyId)){
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(localBodyId);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(localBodyId, infPeopleDetailsVO1);
					}
					
				}else if(subRegionType.equalsIgnoreCase(IConstants.TEHSIL) || subRegionType.equalsIgnoreCase(IConstants.MANDAL)){
					
					Long tehsilId = tehsil.getTehsilId();
                    if(influencePeopleMap.isEmpty() || !influencePeopleMap.containsKey(tehsilId)){
                    	infPeopleDetailsVO.setRegionId(tehsilId);
                    	infPeopleDetailsVO.setRegionName(tehsil.getTehsilName());
                    	infPeopleDetailsVO.setRegionType(IConstants.TEHSIL);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(tehsilId,infPeopleDetailsVO);
					}else if(influencePeopleMap.containsKey(tehsilId)){
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(tehsilId);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(tehsilId, infPeopleDetailsVO1);
					}
                    
				}else if(subRegionType.equalsIgnoreCase(IConstants.HAMLET) || subRegionType.equalsIgnoreCase(IConstants.VILLAGE)){
					
					Long hamletId = hamlet.getHamletId();
                    if(influencePeopleMap.isEmpty() || !influencePeopleMap.containsKey(hamletId)){
                    	infPeopleDetailsVO.setRegionId(hamletId);
                    	infPeopleDetailsVO.setRegionName(hamlet.getHamletName());
                    	infPeopleDetailsVO.setRegionType(IConstants.VILLAGE);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(hamletId, infPeopleDetailsVO);
					}else if(influencePeopleMap.containsKey(hamletId)){
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(hamletId);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(hamletId, infPeopleDetailsVO1);
					}
				}else if(subRegionType.equalsIgnoreCase(IConstants.WARD)){
					
					Long wardId = ward.getConstituencyId();
                    if(influencePeopleMap.isEmpty() || !influencePeopleMap.containsKey(wardId)){
                    	infPeopleDetailsVO.setRegionId(wardId);
                    	infPeopleDetailsVO.setRegionName(ward.getName());
                    	infPeopleDetailsVO.setRegionType(IConstants.WARD);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(wardId, infPeopleDetailsVO);
					}else if(influencePeopleMap.containsKey(wardId)){
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(wardId);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(wardId, infPeopleDetailsVO1);
					}
				}else if(!scopeType.equalsIgnoreCase("") || subRegionType.equalsIgnoreCase("")){
					if(influencePeopleMap.isEmpty()){
						infPeopleDetailsVO.setRegionId(id);
                    	infPeopleDetailsVO.setRegionName(scopeRegion);
                    	infPeopleDetailsVO.setRegionType(scopeType);
                    	infPeopleDetails.add(influencingPeopleBeanVO);
                    	infPeopleDetailsVO.setInfluencingPeopleDetails(infPeopleDetails);
                    	influencePeopleMap.put(id, infPeopleDetailsVO);
					}else{
						InfluencingPeopleDetailsVO infPeopleDetailsVO1 = influencePeopleMap.get(id);
						infPeopleDetailsVO1.getInfluencingPeopleDetails().add(influencingPeopleBeanVO);
						influencePeopleMap.put(id, infPeopleDetailsVO1);
					}
				}
				
			}
			
			//Process The Map And Set to List
			Set<Long> keys = influencePeopleMap.keySet();
			for(Long regId:keys){
				influencingPeopleDetailsVO.add(influencePeopleMap.get(regId));
			}
		}
		
	 return influencingPeopleDetailsVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getInfluencingPeopleDetailsByScope(java.lang.Long, java.lang.Long, java.lang.String)
	 * Influencing People Details By Influence Scope
	 */
	@SuppressWarnings("unchecked")
	public List<InfluencingPeopleDetailsVO> getInfluencingPeopleDetailsByScope(Long userId,
			Long regionId, String regionType) {
		
		log.debug("Getting Influencing People Details Scope Wise ...");
		List<InfluencingPeopleDetailsVO> influencingPeopleDetailsList = new ArrayList<InfluencingPeopleDetailsVO>();
		try{
			List influencingPeopleList = null;
			List influencingPeopleAddrList = null;
			String regionName = "";
			String scopeType = getRegionTypeMatchingString(regionType);
			
			if(regionId.equals(0L)){
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsByInfluencingScope(userId, scopeType);
				influencingPeopleAddrList = influencingPeopleDAO.getTotalInfluencingPeopleAddressByInfluencingScope(userId, scopeType);
			}else{
				influencingPeopleList = influencingPeopleDAO.getTotalInfluencingPeopleDetailsByInfluencingScope(userId, scopeType,regionId.toString());
				influencingPeopleAddrList = influencingPeopleDAO.getTotalInfluencingPeopleAddressByInfluencingScope(userId, scopeType,regionId.toString());
				regionName = getRegionNameBasedOnScope(regionType,regionId.toString());
			}
			
			if(influencingPeopleList != null && influencingPeopleList.size() > 0){
				influencingPeopleDetailsList = getProcessedInfluencingPeopleDetailsToVO(influencingPeopleList,influencingPeopleAddrList,"","",regionId,regionName,scopeType);
			}
		}catch(Exception ex){
			log.error("Exception Raised In Influencing People Details Retrieval :" + ex);
			ex.printStackTrace();
			InfluencingPeopleDetailsVO rs = new InfluencingPeopleDetailsVO();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			influencingPeopleDetailsList.add(rs);
		}
		
	 return influencingPeopleDetailsList;
	}
	
	public String getRegionTypeMatchingString(String type){
		if(type.equalsIgnoreCase(IConstants.STATE))
			return IConstants.STATE;
		else if(type.equalsIgnoreCase(IConstants.DISTRICT))
			return IConstants.DISTRICT;
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			return IConstants.CONSTITUENCY;
		else if(type.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) || type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
			return "MUNCIPALITY/CORPORATION";
		else if(type.equalsIgnoreCase(IConstants.TEHSIL) || type.equalsIgnoreCase(IConstants.MANDAL))
			return IConstants.MANDAL;
		else if(type.equalsIgnoreCase(IConstants.VILLAGE) || type.equalsIgnoreCase(IConstants.HAMLET))
			return IConstants.VILLAGE;
		else if(type.equalsIgnoreCase(IConstants.WARD))
			return IConstants.WARD;
		
	 return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getLocalELectionBodyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(java.lang.Long, java.lang.Long, java.lang.Boolean)
	 * Influencing People Details In Entire Local Election Body and its consecutive sub regions
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyManagementDataVO getLocalELectionBodyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(
			Long userId, Long localBodyId, Boolean isScopeData,String moduleType,Long categoryId,String categoryType) {

		if(log.isDebugEnabled())
        	log.debug("Getting Mandal And Sub Regions Influencing People/Local Groups Details ..");
       
        ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory(moduleType);
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire District
		List infPeopleCount = null;
		if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
		     infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInLocalBodys(userId, localBodyId);
		else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
			 infPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInLocalBodys(userId, localBodyId, categoryId);
			
		LocalElectionBody localBody = localElectionBodyDAO.get(localBodyId);
		
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(localBodyId);
			regionWiseOverview.setRegionType("MUNCIPALITY/CORPORATION");
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(localBody.getName());
			regionWiseOverview.setRegionTitle(categoryType);
			regionWiseOverview.setRegionTitleId(categoryId);
			
			//Villages wise influencing people details
			List<SelectOptionVO> wardsInLocalBody = regionServiceDataImp.getWardsInALocalElectionBody(localBodyId);
			if(wardsInLocalBody != null && wardsInLocalBody.size() > 0)
			subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(wardsInLocalBody,IConstants.WARD);
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
			
			List subRegionsInfPeopleCount = null;
			if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInWardsByLocalBody(userId, localBodyId);
			else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
				subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInWardsByLocalBodys(userId, localBodyId, categoryId);
				
				if(subRegionsDetailsMap != null && !subRegionsDetailsMap.isEmpty())
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,subRegionsDetailsMap);
				else
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,IConstants.WARD);	
				
				if(subRegionsInfPeopleList != null && subRegionsInfPeopleList.size() > 0)
				 regionWiseOverview.setSubRegionWiseOverview(subRegionsInfPeopleList);
			
			constituencyManagementDataVO.setRegionWiseOverview(regionWiseOverview);
			
			//To get Influencing people details by scope
			if(isScopeData)
				constituencyManagementDataVO.setInfluenceScopeOverview(getInfluencingPeopleByInfluenceScope(userId));
		}
		
	 return constituencyManagementDataVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getMandalRegionAndSubRegionsInfluencingPeopleByUserAndLocation(java.lang.Long, java.lang.Long, java.lang.Boolean)
	 * Influencing People Details In Entire Mandal and its consecutive sub regions
	 */
	@SuppressWarnings("unchecked")
	public ConstituencyManagementDataVO getMandalRegionAndSubRegionsInfluencingPeopleByUserAndLocation(
			Long userId, Long mandalId, Boolean isScopeData,String moduleType,Long categoryId,String categoryType) {
		
		if(log.isDebugEnabled())
        	log.debug("Getting Mandal And Sub Regions Influencing People/Local Group Details ..");
       
        ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory(moduleType);
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire District
		List infPeopleCount = null;
		if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
		    infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInTehsil(userId, mandalId);
		else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
			infPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInTehsil(userId, mandalId, categoryId);
		
		Tehsil tehsil = tehsilDAO.get(mandalId);
		
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(mandalId);
			regionWiseOverview.setRegionType(IConstants.TEHSIL);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(tehsil.getTehsilName());
			regionWiseOverview.setRegionTitle(categoryType);
			regionWiseOverview.setRegionTitleId(categoryId);
			
			//Villages wise influencing people details
			List<SelectOptionVO> villagesInMandal = regionServiceDataImp.getHamletsInATehsil(mandalId);
			if(villagesInMandal != null && villagesInMandal.size() > 0)
			subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(villagesInMandal,IConstants.VILLAGE);
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
			List subRegionsInfPeopleCount = null;
			
			if(moduleType.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInVillagesByTehsil(userId, mandalId);
			else if(moduleType.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
				subRegionsInfPeopleCount = personalUserGroupDAO.getTotalCountOfLocalGroupsInVillagesByTehsil(userId, mandalId, categoryId);
				
				if(subRegionsDetailsMap != null && !subRegionsDetailsMap.isEmpty())
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,subRegionsDetailsMap);
				else
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,IConstants.VILLAGE);	
				
				if(subRegionsInfPeopleList != null && subRegionsInfPeopleList.size() > 0)
				 regionWiseOverview.setSubRegionWiseOverview(subRegionsInfPeopleList);
			
			constituencyManagementDataVO.setRegionWiseOverview(regionWiseOverview);
			
			//To get Influencing people details by scope
			if(isScopeData)
				constituencyManagementDataVO.setInfluenceScopeOverview(getInfluencingPeopleByInfluenceScope(userId));
		}
		
	 return constituencyManagementDataVO;
	}

	
    /*
     * (non-Javadoc)
     * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getLocalUserGroupOverviewDetails(java.lang.Long)
     * Local User Groups Regionwise and Scopewise Complete Overview Retrieval For A User
     */
	@SuppressWarnings("unchecked")
	public ConstituencyManagementDataVO getLocalUserGroupOverviewDetails(Long userId) {
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		List<ConstituencyManagementRegionWiseOverviewVO> localGroupsList = new ArrayList<ConstituencyManagementRegionWiseOverviewVO>();
		if(log.isDebugEnabled())
			log.debug(" Getting Complete Overview Details Of Influencing People Local User Groups ..");
		
		try{
			Registration user = registrationDAO.get(userId);
			String accessValue = user.getAccessValue();
			List<SelectOptionVO> categorysList = getLocalGroupCategoriesForAUser(userId);
			if(categorysList == null || categorysList.size() == 0)
				return constituencyManagementDataVO;
			
				
			//State Level Access User
			if(user.getAccessType().equals(IConstants.STATE)){
				Long stateId = new Long(accessValue);
				constituencyManagementDataVO = getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,stateId,true,IConstants.LOCAL_USER_GROUPS,categorysList.get(0).getId(),categorysList.get(0).getName());
				for(int i=1;i<categorysList.size();i++){
					localGroupsList.add(getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,stateId,false,IConstants.LOCAL_USER_GROUPS,categorysList.get(i).getId(),categorysList.get(i).getName()).getRegionWiseOverview());
				}
			}
			//District Level Access User
			else if(user.getAccessType().equals(IConstants.DISTRICT)){
				Long districtId = new Long(accessValue);
				constituencyManagementDataVO = getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,districtId,true,IConstants.LOCAL_USER_GROUPS,categorysList.get(0).getId(),categorysList.get(0).getName());
				for(int i=1;i<categorysList.size();i++){
					localGroupsList.add(getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,districtId,false,IConstants.LOCAL_USER_GROUPS,categorysList.get(i).getId(),categorysList.get(i).getName()).getRegionWiseOverview());
				}
			}
			//MP Access User
			else if(user.getAccessType().equals(IConstants.MP)){
				
			}
			//MLA Access User
			else if(user.getAccessType().equals(IConstants.MLA)){
				Long constituencyId = new Long(accessValue);
				constituencyManagementDataVO = getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId, constituencyId, true,IConstants.LOCAL_USER_GROUPS,categorysList.get(0).getId(),categorysList.get(0).getName());
				for(int i=1;i<categorysList.size();i++){
					localGroupsList.add(getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId, constituencyId, false,IConstants.LOCAL_USER_GROUPS,categorysList.get(i).getId(),categorysList.get(i).getName()).getRegionWiseOverview());
				}
			}
			
			//Add Other Category Results
			constituencyManagementDataVO.setCategoryListOverview(localGroupsList);
			
			
		}catch(Exception ex){
			log.error("Exception Raised In Local User Groups Retrieval :" + ex);
			ex.printStackTrace();
			constituencyManagementDataVO.setExceptionEncountered(ex);
			constituencyManagementDataVO.setResultCode(ResultCodeMapper.FAILURE);
			constituencyManagementDataVO.setExceptionMsg(ex.getMessage());
		}
			
	 return constituencyManagementDataVO;
	}
	 
	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getLocalUserGroupByInfluenceScope(java.lang.Long)
	 * Local User Groups Retrieval By Scope Wise
	 */
	public List<ConstituencyManagementInfluenceScopeOverviewVO> getLocalUserGroupByInfluenceScope(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getLocalGroupCategoriesForAUser(Long userId){
		
		List<SelectOptionVO> groupCategoriesList = null;
		
		List groupCategorys = personalUserGroupDAO.getDistinctLocalGroupCategorysForAUser(userId);
		
		if(groupCategorys != null && groupCategorys.size() > 0){
			groupCategoriesList = new ArrayList<SelectOptionVO>();
			Iterator lstItr = groupCategorys.listIterator();
			while(lstItr.hasNext()){
				Object[] values = (Object[])lstItr.next();
				SelectOptionVO option = new SelectOptionVO((Long)values[0],(String)values[1]);
				
				groupCategoriesList.add(option);
			}
		}
	 return groupCategoriesList;
	}
	
	/*
	 * Method To Get Local User Group Location Information
	 */
	public SelectOptionVO getLocalUserGroupLocationDetails(LocalGroupRegion groupRegion){
		
		SelectOptionVO location = null;
		if(groupRegion != null){
						
			if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.STATE)){
				
				State state = groupRegion.getState();
				if(state != null)
					location = new SelectOptionVO(state.getStateId(),state.getStateName());
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.DISTRICT)){
				
				District district = groupRegion.getDistrict();
				if(district != null)
					location = new SelectOptionVO(district.getDistrictId(),district.getDistrictName());
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				
				Constituency constituency = groupRegion.getConstituency();
				if(constituency != null)
					location = new SelectOptionVO(constituency.getConstituencyId(),constituency.getName());
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.TEHSIL) || groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.MANDAL)){
				
				Tehsil tehsil = groupRegion.getTehsil();
				if(tehsil != null)
					location = new SelectOptionVO(tehsil.getTehsilId(),tehsil.getTehsilName());
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) || groupRegion.getGroupRegionScope().equalsIgnoreCase("MUNCIPALITY/CORPORATION")){
				
				LocalElectionBody localBody = groupRegion.getLocalBody();
				if(localBody != null){
					String localBodyName = localBody.getName().concat(" (").concat(localBody.getElectionType().getElectionType()).concat(")");
					location = new SelectOptionVO(localBody.getLocalElectionBodyId(),localBodyName);
				}
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.HAMLET) || groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.VILLAGE)){
				
				Hamlet hamlet = groupRegion.getHamlet();
				if(hamlet != null)
					location = new SelectOptionVO(hamlet.getHamletId(),hamlet.getHamletName());
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase(IConstants.WARD)){
				
				Constituency ward = groupRegion.getWard();
				if(ward != null)
					location = new SelectOptionVO(ward.getConstituencyId(),ward.getName());
			}else if(groupRegion.getGroupRegionScope().equalsIgnoreCase("BOOTH")){
				
				log.debug("Booth Under Process ..");
			}
		}
		
	 return location;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#getLocalUserGroupDetails(java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.String)
	 * To Get Local User Group Complete Details By Region
	 */
	@SuppressWarnings("unchecked")
	public List<LocalUserGroupDetailsVO> getLocalUserGroupDetails(Long userId,
			Long regionId, String regionType, Long categoryId,
			String categoryType) {
		
		if(log.isDebugEnabled())
			log.debug("Getting Local Group Details ..");
		
		List<LocalUserGroupDetailsVO> localUserGroupDetailsVO = new ArrayList<LocalUserGroupDetailsVO>();
		try{
			
			List localUserGroupsList = null;
			
			if(regionType.equalsIgnoreCase(IConstants.STATE)){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInState(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,IConstants.STATE);
				
			}else if(regionType.equalsIgnoreCase(IConstants.DISTRICT)){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInDistrict(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,IConstants.DISTRICT);
				
			}else if(regionType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInConstituency(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,IConstants.CONSTITUENCY);
				
			}else if(regionType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) || regionType.equalsIgnoreCase("MUNCIPALITY/CORPORATION")){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInLocalElectionBody(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,"MUNCIPALITY/CORPORATION");
				
			}else if(regionType.equalsIgnoreCase(IConstants.TEHSIL) || regionType.equalsIgnoreCase(IConstants.MANDAL)){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInTehsil(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,IConstants.MANDAL);
				
			}else if(regionType.equalsIgnoreCase(IConstants.HAMLET) || regionType.equalsIgnoreCase(IConstants.VILLAGE)){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInVillage(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,IConstants.VILLAGE);
				
			}else if(regionType.equalsIgnoreCase(IConstants.WARD)){
				
				localUserGroupsList = personalUserGroupDAO.getLocalGroupDetailsInWard(userId, regionId,categoryId);
				if(localUserGroupsList != null && localUserGroupsList.size() > 0)
					getProcessedLocalUserGroupDetailsToVO(localUserGroupsList,localUserGroupDetailsVO,IConstants.WARD);
			}
			
			
			
		}catch(Exception ex){
			log.error("Exception Raised In Local User Group Details Retrieval :" + ex);
			ex.printStackTrace();
			LocalUserGroupDetailsVO localGroup = new LocalUserGroupDetailsVO();
			ResultStatus rs = new ResultStatus();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setExceptionClass(ex.getClass().toString());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			rs.setResultPartial(true);
			
			localGroup.setResultStatus(rs);
			localUserGroupDetailsVO.add(localGroup);
		}
			
	 return localUserGroupDetailsVO;
	}
	
	@SuppressWarnings("unchecked")
	public void getProcessedLocalUserGroupDetailsToVO(List localGroupsList,List<LocalUserGroupDetailsVO> localUserGroupDetailsVO,String type){
		
		if(localGroupsList != null && localGroupsList.size() > 0){
			Iterator lstItr = localGroupsList.listIterator();
			while(lstItr.hasNext()){
				
				Date createdDate = null;
				String createDateStr = "";
				LocalUserGroupDetailsVO groupDetails = new LocalUserGroupDetailsVO();
				Object[] values  = (Object[])lstItr.next();
				
				groupDetails.setLocalUserGroupId((Long)values[0]);
				groupDetails.setLocalUserGroupName((String)values[1]);
				groupDetails.setGroupDesc((String)values[2]);
				
				createdDate = (Date)values[3];
				groupDetails.setCreatedDate(createDateStr = createdDate != null ? createdDate.toString() : "");
				groupDetails.setGroupCategoryId((Long)values[4]);
				groupDetails.setGroupCategoryType((String)values[5]);
				
				//LocalGroupRegion localGroupRegion = (LocalGroupRegion)values[7];
				//SelectOptionVO locationDetails = getLocalUserGroupLocationDetails(localGroupRegion);
				String locName = (String)values[8];
				groupDetails.setGroupLocationId((Long)values[7]);
				groupDetails.setGroupLocation(locName.concat(" (").concat(type).concat(")"));
				
				List<UserGroupMembersVO> groupMemberDetails = getUserGroupMemberDetailsForAGroup((Long)values[0]);
				groupDetails.setGroupMembersCount(new Long(groupMemberDetails.size()));
				groupDetails.setGroupMemberDetails(groupMemberDetails);
								
				localUserGroupDetailsVO.add(groupDetails);
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupMembersVO> getUserGroupMemberDetailsForAGroup(Long groupId){
		
		//Members count in a group
		List<UserGroupMembersVO> userGroupMembers = new ArrayList<UserGroupMembersVO>();
		List memberDetails = staticUserGroupDAO.getMemberDetailsOfLocalUserGroup(groupId);
		
		if(memberDetails != null){
			Iterator lstItr = memberDetails.listIterator();
			while(lstItr.hasNext()){
				
				UserGroupMembersVO groupMember = new UserGroupMembersVO();
				Object[] values = (Object[])lstItr.next();
				
				groupMember.setGroupMemberId((Long)values[0]);
				groupMember.setName((String)values[1]);
				groupMember.setMobileNumber((String)values[2]);
				groupMember.setEmailId((String)values[3]);
				groupMember.setAddress((String)values[4]);
				groupMember.setLocation((String)values[5]);
				
				StaticUserDesignation userDesignation = (StaticUserDesignation)values[6];
				if(userDesignation != null)
					groupMember.setDesignation(userDesignation.getDesignationType());
				
				userGroupMembers.add(groupMember);
			}
		}
	 return userGroupMembers;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#sendSMSToInfluencingPersons(java.util.List)
	 * Send SMS To Influencing Persons By InfluencingPersonIds as List<Long> ids
	 */
	@SuppressWarnings("unchecked")
	public SmsResultVO sendSMSToInfluencingPersons(Long userId,
			List<Long> influencingPersonIds,String message,Boolean includeName,String module,String senderName) {
		
		if(log.isDebugEnabled())
			log.debug("Sending Message TO Influencing Persons ..");
		SmsResultVO smsResultVO = new SmsResultVO();
						
		try{
			
			Long remainingSMS = smsCountrySmsService.getRemainingSmsLeftForUser(userId) - influencingPersonIds.size();
						
			if (remainingSMS < 0) {
				smsResultVO.setStatus(1l);
				smsResultVO.setTotalSmsSent(0l);
				smsResultVO.setRemainingSmsCount(0l);
			}else{
				
				List inflencinfPeopleList = null;
				if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				   inflencinfPeopleList = influencingPeopleDAO.getInfluencingPeopleNameAndMobileNOByIds(influencingPersonIds);
				else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS)){
					if(includeName)
					    inflencinfPeopleList = staticUsersDAO.findGroupMembersNameAndMobileNosByMemberIds(influencingPersonIds);
					if(!includeName)
						inflencinfPeopleList = staticUsersDAO.findGroupMembersMobileNosByMemberIds(influencingPersonIds);
				}
					
				String[] phoneNos = new String[inflencinfPeopleList.size()];
				int i = 0;
				
				//Without Person Name
				if(!includeName){
					
					String[] mobileNos = new String[inflencinfPeopleList.size()];
					
					if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
					    mobileNos = getInfPeopleMobileNos(inflencinfPeopleList);
					else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
						mobileNos = getLocalGroupMembersMobileNos(inflencinfPeopleList);
					
					if(mobileNos != null && mobileNos.length > 0)
					smsCountrySmsService.sendSms(message, true, userId,
							module, mobileNos);
					
					phoneNos = mobileNos;
				}
				//With Name
				else if(includeName){
					
					List<UserGroupMembersVO> influencingPeopleNos = null;
					
					if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
					    influencingPeopleNos = getInfPeopleMobileNosAndNames(inflencinfPeopleList);
					else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
						influencingPeopleNos = getLocalGroupMembersMobileNosAndNames(inflencinfPeopleList);
							
					for(UserGroupMembersVO mobile:influencingPeopleNos){
						
						StringBuilder cadreMessage = new StringBuilder(
								IConstants.SMS_DEAR);
						cadreMessage.append(IConstants.SPACE).append(mobile.getName())
								.append(IConstants.SPACE).append(message);
						if(!senderName.equalsIgnoreCase(""))
							cadreMessage.append(" - ").append(senderName);
						smsCountrySmsService.sendSms(cadreMessage.toString(), true,
								userId, module, mobile.getMobileNumber());
						
						phoneNos[i++] = mobile.getMobileNumber();
					}
				}
				
				//To Track The SMS Sent Records
				//smsCountrySmsService.saveSmsData(message, userId, "Influencing People", phoneNos);
				if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
				   smsCountrySmsService.saveSmsData(message, userId, "Influencing People", phoneNos);
				else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
				   smsCountrySmsService.saveSmsData(message, userId, "User Groups", phoneNos);
					
				smsResultVO.setStatus(0l);
				smsResultVO.setTotalSmsSent(Long.parseLong(new Integer(inflencinfPeopleList.size())
						.toString()));
				smsResultVO.setRemainingSmsCount(remainingSMS);
			}
			
			
		}catch(Exception ex){
			log.error("Exception Raised In Sending SMS TO Influencing People :" + ex);
			ex.printStackTrace();
			ResultStatus rs = new ResultStatus();
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setExceptionClass(ex.getClass().toString());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			smsResultVO.setResultStatus(rs);
		}
		
	 return smsResultVO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IInfluencingPeopleService#sendSMSToInfluencingPersons(java.lang.String)
	 * Send SMS To Influencing Persons By InfluencingPersonIds as String of ids
	 */
	@SuppressWarnings("unchecked")
	public SmsResultVO sendSMSToInfluencingPersons(Long userId,String influencingPersonIds,String message,Boolean includeName,String module,String senderName) {
		
		if(log.isDebugEnabled())
			log.debug("Sending Message TO Influencing Persons ..");
		SmsResultVO smsResultVO = new SmsResultVO();
						
		try{
			List inflencinfPeopleList = null;
			
			if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
			    inflencinfPeopleList = influencingPeopleDAO.getInfluencingPeopleNameAndMobileNOByIds(influencingPersonIds);
			else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS)){
				if(includeName)
				    inflencinfPeopleList = staticUsersDAO.findGroupMembersNameAndMobileNosByMemberIds(influencingPersonIds);
				if(!includeName)
					inflencinfPeopleList = staticUsersDAO.findGroupMembersMobileNosByMemberIds(influencingPersonIds);
			}
					
			Long remainingSMS = smsCountrySmsService.getRemainingSmsLeftForUser(userId) - inflencinfPeopleList.size();
						
			if (remainingSMS < 0) {
				smsResultVO.setStatus(1l);
				smsResultVO.setTotalSmsSent(0l);
				smsResultVO.setRemainingSmsCount(0l);
			}else{
			
				String[] phoneNos = new String[inflencinfPeopleList.size()];
				int i = 0;
				
				//Without Person Name
				if(!includeName){
					
					String[] mobileNos = new String[inflencinfPeopleList.size()];
										
					if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
					 	mobileNos = getInfPeopleMobileNos(inflencinfPeopleList);
					else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
						mobileNos = getLocalGroupMembersMobileNos(inflencinfPeopleList);
					
					if(mobileNos != null && mobileNos.length > 0)
					smsCountrySmsService.sendSms(message, true, userId,
							module, mobileNos);
					
					phoneNos = mobileNos;
				}
				//With Name
				else if(includeName){
					List<UserGroupMembersVO> influencingPeopleNos = null;
					
					if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
						influencingPeopleNos = getInfPeopleMobileNosAndNames(inflencinfPeopleList);
					else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
						influencingPeopleNos = getLocalGroupMembersMobileNosAndNames(inflencinfPeopleList);
					
					for(UserGroupMembersVO mobile:influencingPeopleNos){
						
						StringBuilder cadreMessage = new StringBuilder(
								IConstants.SMS_DEAR);
						cadreMessage.append(IConstants.SPACE).append(mobile.getName())
								.append(IConstants.SPACE).append(message);
						if(!senderName.equalsIgnoreCase(""))
							cadreMessage.append(" - ").append(senderName);
						smsCountrySmsService.sendSms(cadreMessage.toString(), true,
								userId, module, mobile.getMobileNumber());
						
						phoneNos[i++] = mobile.getMobileNumber();
					}
				}
				
				//To Track The SMS Sent Records
				//smsCountrySmsService.saveSmsData(message, userId, "Influencing People", phoneNos);
				if(module.equalsIgnoreCase(IConstants.INFLUENCING_PEOPLE))
					   smsCountrySmsService.saveSmsData(message, userId, "Influencing People", phoneNos);
				else if(module.equalsIgnoreCase(IConstants.LOCAL_USER_GROUPS))
					   smsCountrySmsService.saveSmsData(message, userId, "User Groups", phoneNos);
				
				smsResultVO.setStatus(0l);
				smsResultVO.setTotalSmsSent(Long.parseLong(new Integer(inflencinfPeopleList.size())
						.toString()));
				smsResultVO.setRemainingSmsCount(remainingSMS);
			}
			
			
		}catch(Exception ex){
			log.error("Exception Raised In Sending SMS TO Influencing People :" + ex);
			ex.printStackTrace();
			ResultStatus rs = new ResultStatus();
			
			smsResultVO.setStatus(1L);
			smsResultVO.setTotalSmsSent(0L);
			smsResultVO.setRemainingSmsCount(0L);
			
			rs.setExceptionEncountered(ex);
			rs.setExceptionMsg(ex.getMessage());
			rs.setExceptionClass(ex.getClass().toString());
			rs.setResultCode(ResultCodeMapper.FAILURE);
			
			smsResultVO.setResultStatus(rs);
		}
		
	 return smsResultVO;
	}

		
	@SuppressWarnings("unchecked")
	public String[] getInfPeopleMobileNos(List membersList){
		
		String[] mobileNos  = new String[membersList.size()];
		if(membersList != null && membersList.size() > 0){
			
			int i=0;
			Iterator lstItr = membersList.listIterator();
			while(lstItr.hasNext()){
				Object[] values = (Object[])lstItr.next();
				String mobileNO = (String)values[2];
				
				mobileNos[i++] = mobileNO;
			}
			
		}
	 return mobileNos;
	}
	
	@SuppressWarnings("unchecked")
	public String[] getLocalGroupMembersMobileNos(List membersList){
		
		String[] mobileNos  = new String[membersList.size()];
		if(membersList != null && membersList.size() > 0){
			
			int i=0;
			Iterator lstItr = membersList.listIterator();
			while(lstItr.hasNext()){
				Object[] values = (Object[])lstItr.next();
				String mobileNO = (String)values[0];
				
				mobileNos[i++] = mobileNO;
			}
			
		}
	 return mobileNos;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupMembersVO> getLocalGroupMembersMobileNosAndNames(List membersList){
		
		List<UserGroupMembersVO> membersVO = new ArrayList<UserGroupMembersVO>();
        if(membersList != null && membersList.size() > 0){
			
			Iterator lstItr = membersList.listIterator();
			while(lstItr.hasNext()){
				UserGroupMembersVO member = new UserGroupMembersVO();
				
				Object[] values = (Object[])lstItr.next();
				
				String fname = (String)values[0];
				String mobileNO = (String)values[1];
				
				member.setName(fname);
				member.setMobileNumber(mobileNO);
				
				membersVO.add(member);
			}
			
		}
     return membersVO;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupMembersVO> getInfPeopleMobileNosAndNames(List membersList){
		
		List<UserGroupMembersVO> membersVO = new ArrayList<UserGroupMembersVO>();
        if(membersList != null && membersList.size() > 0){
			
			Iterator lstItr = membersList.listIterator();
			while(lstItr.hasNext()){
				UserGroupMembersVO member = new UserGroupMembersVO();
				
				String personName = "";
				Object[] values = (Object[])lstItr.next();
				
				String fname = (String)values[0];
				String lname = (String)values[1];
				String mobileNO = (String)values[2];
				
				if(fname != null && lname != null)
					personName = fname + " " + lname;
				else if(fname != null)
					personName = fname;
				else 
					personName = lname;
				
				member.setName(personName);
				member.setMobileNumber(mobileNO);
				
				membersVO.add(member);
			}
			
		}
     return membersVO;
	}
}

