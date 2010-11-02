package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISocialCategoryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeopleDAO;
import com.itgrids.partyanalyst.dao.hibernate.InfluencingPeoplePositionDAO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementDataVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementInfluenceScopeOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementSubRegionWiseOverviewVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.model.State;
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
	private static final Logger log = Logger.getLogger(InfluencingPeopleService.class);
	private TransactionTemplate transactionTemplate = null;
	private InfluencingPeopleVO influencingPeopleVO;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO;
	private IStaticDataService staticDataService;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IRegionServiceData regionServiceDataImp;

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
			selectOptionVOs.add(new SelectOptionVO(1l,IConstants.STATE_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(2l,IConstants.DISTRICT_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(3l,IConstants.CONSTITUENCY_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(4l,"MUNICIPAL-CORP-GMC"));
			selectOptionVOs.add(new SelectOptionVO(5l,IConstants.MANDAL_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(6l,IConstants.CENSUS_VILLAGE_LEVEL));
			selectOptionVOs.add(new SelectOptionVO(7l,IConstants.WARD));
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
						userAddress.setLocalElectionBody(localElectionBodyDAO.get(new Long(influencingPeopleBeanVO.getMandal().substring(1))));
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
					influencingPeopleBeanVO.setInfluencingRange("1");
					influencingPeopleBeanVO.setInfluencingScopeValue((stateDAO.get(new Long(influScopeValue)).getStateName()));
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("2");
					influencingPeopleBeanVO.setInfluencingScopeValue(districtDAO.get(new Long(influScopeValue)).getDistrictName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("3");
					influencingPeopleBeanVO.setInfluencingScopeValue(constituencyDAO.get(new Long(influScopeValue)).getName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("4");
				    influencingPeopleBeanVO.setInfluencingScopeValue(localElectionBodyDAO.get(new Long(influScopeValue)).getName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.MANDAL_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("5");
					influencingPeopleBeanVO.setInfluencingScopeValue(tehsilDAO.get(new Long(influScopeValue)).getTehsilName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.CENSUS_VILLAGE_LEVEL)){
					influencingPeopleBeanVO.setInfluencingRange("6");
					influencingPeopleBeanVO.setInfluencingScopeValue(hamletDAO.get(new Long(influScopeValue)).getHamletName());
				}
				
				else if(influRange.equalsIgnoreCase(IConstants.WARD)){
					influencingPeopleBeanVO.setInfluencingRange("7");
					influencingPeopleBeanVO.setInfluencingScopeValue(constituencyDAO.get(new Long(influScopeValue)).getName());
				}
				
				if(userAddress.getLocalElectionBody() != null )
				{
					influencingPeopleBeanVO.setAreaType(IConstants.URBAN_TYPE);
					influencingPeopleBeanVO.setMandal(IConstants.URBAN_TYPE+userAddress.getLocalElectionBody().getLocalElectionBodyId().toString());
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
				constituencyManagementDataVO = getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,stateId,true);
			}
			//District Level Access User
			else if(user.getAccessType().equals(IConstants.DISTRICT)){
				Long districtId = new Long(accessValue);
				constituencyManagementDataVO = getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,districtId,true);
			}
			//MP Access User
			else if(user.getAccessType().equals(IConstants.MP)){
				
			}
			//MLA Access User
			else if(user.getAccessType().equals(IConstants.MLA)){
				Long constituencyId = new Long(accessValue);
				constituencyManagementDataVO = getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId, constituencyId, true);
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
	public ConstituencyManagementDataVO getStateRegionAndSubRegionsInfluencingPeopleByUserAndLocation(Long userId,Long stateId,Boolean isScopeData){
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory("INFLUENCING PEOPLE");
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire state
		List infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInState(userId, stateId);
		State state = stateDAO.get(stateId);
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(stateId);
			regionWiseOverview.setRegionType(IConstants.STATE);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(state.getStateName());
			
			//district wise influencing people details
			List<SelectOptionVO> districtsInState = staticDataService.getDistricts(stateId);
			if(districtsInState != null && districtsInState.size() > 0)
			subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(districtsInState,IConstants.DISTRICT);
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
			
				List subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInDistrictsByState(userId, stateId);
				if(subRegionsDetailsMap != null && !subRegionsDetailsMap.isEmpty())
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,subRegionsDetailsMap);
				else
				 subRegionsInfPeopleList = getSubRegionsProcessedDetailsToVO(subRegionsInfPeopleCount,IConstants.DISTRICT);
				
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
					influenceScopeOverviewVOList.add(influenceScopeOverviewVO);
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
			Long userId, Long districtId, Boolean isScopeData) {
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory("INFLUENCING PEOPLE");
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire District
		List infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInDistrict(userId, districtId);
		District district = districtDAO.get(districtId);
		
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(districtId);
			regionWiseOverview.setRegionType(IConstants.DISTRICT);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(district.getDistrictName());
			
			
			//constituencies wise influencing people details
			List<SelectOptionVO> constituenciesInDistrict = staticDataService.getLatestAssemblyConstituenciesInDistrict(districtId);
			if(constituenciesInDistrict != null && constituenciesInDistrict.size() > 0)
			subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(constituenciesInDistrict,IConstants.CONSTITUENCY);
			List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
			
				List subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInConstituenciesByDistrict(userId, districtId);
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
			Long userId, Long constituencyId, Boolean isScopeData) {
		
		ConstituencyManagementDataVO constituencyManagementDataVO = new ConstituencyManagementDataVO();
		constituencyManagementDataVO.setDataCategory("INFLUENCING PEOPLE");
		Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap = null;
		
		//influencing people count in a entire Constituency
		List infPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInConstituency(userId, constituencyId);
		Constituency constituency = constituencyDAO.get(constituencyId);
		
		if(infPeopleCount != null && infPeopleCount.size() > 0){
			ConstituencyManagementRegionWiseOverviewVO regionWiseOverview = new ConstituencyManagementRegionWiseOverviewVO();
			Object totalCount = (Object)infPeopleCount.get(0);
			
			regionWiseOverview.setRegionId(constituencyId);
			regionWiseOverview.setRegionType(IConstants.CONSTITUENCY);
			regionWiseOverview.setCountValue((Long)totalCount);
			regionWiseOverview.setRegionName(constituency.getName());
			
			List<SelectOptionVO> subRegionsList = null;
            List<ConstituencyManagementSubRegionWiseOverviewVO> subRegionsInfPeopleList = null;
            List subRegionsInfPeopleCount = null;
			
			if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN)){
				subRegionsList = regionServiceDataImp.getLocalElectionBodiesInConstituency(constituencyId, IConstants.DELIMITATION_YEAR.toString());
				if(subRegionsList != null && subRegionsList.size() > 0)
					subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList,IConstants.TEHSIL);
				
				subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInTehsilsByConstituency(userId, constituencyId);
				
			}else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL)){
				subRegionsList = regionServiceDataImp.getTehsilsInAConstituency(constituencyId);
				if(subRegionsList != null && subRegionsList.size() > 0)
					subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList,IConstants.LOCAL_BODY_ELECTION);
				
				subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(userId, constituencyId);
				
				
			}else if(constituency.getAreaType().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN)){
				//for tehsils
				subRegionsList = regionServiceDataImp.getLocalElectionBodiesInConstituency(constituencyId, IConstants.DELIMITATION_YEAR.toString());
				if(subRegionsList != null && subRegionsList.size() > 0)
					subRegionsDetailsMap = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList,IConstants.TEHSIL);
				//for local bodys
				Map<Long,ConstituencyManagementSubRegionWiseOverviewVO> subRegionsDetailsMap1 = null;
				List<SelectOptionVO> subRegionsList1 = regionServiceDataImp.getTehsilsInAConstituency(constituencyId);
				if(subRegionsList1 != null && subRegionsList1.size() > 0)
					subRegionsDetailsMap1 = getRegionsDataInitializedMapWithInfluencingPeopleCount(subRegionsList1,IConstants.LOCAL_BODY_ELECTION);
				
				if(subRegionsDetailsMap1 != null && !subRegionsDetailsMap1.isEmpty())
				 subRegionsDetailsMap.putAll(subRegionsDetailsMap1);
				
				subRegionsInfPeopleCount = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInTehsilsByConstituency(userId, constituencyId);
				List subRegionsInfPeopleCount1 = influencingPeopleDAO.getTotalCountOfInfluencingPeopleInLocalBodysByConstituency(userId, constituencyId);
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
	public List<ConstituencyManagementRegionWiseOverviewVO> getRegionsAndSubRegionsInfluencePeopleDetailsByRegionType(
			Long userId, Long regionId, String regionType) {

		List<ConstituencyManagementRegionWiseOverviewVO> constituencyManagementRegionWiseOverviewVOList = new ArrayList<ConstituencyManagementRegionWiseOverviewVO>();
		
		try{
			if(regionType.equalsIgnoreCase(IConstants.STATE)){
				List<SelectOptionVO> districtsInState = staticDataService.getDistricts(regionId);
			    for(SelectOptionVO district:districtsInState){
			    	ConstituencyManagementDataVO constituencyManagementDataVO = getDistrictRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId,district.getId(),false);
			    	if(constituencyManagementDataVO != null)
			    		constituencyManagementRegionWiseOverviewVOList.add(constituencyManagementDataVO.getRegionWiseOverview());
			    }
			}else if(regionType.equalsIgnoreCase(IConstants.DISTRICT)){
				List<SelectOptionVO> constituenciesInDistrict = staticDataService.getLatestAssemblyConstituenciesInDistrict(regionId);
				for(SelectOptionVO consti:constituenciesInDistrict){
					ConstituencyManagementDataVO constituencyManagementDataVO = getConstituencyRegionAndSubRegionsInfluencingPeopleByUserAndLocation(userId, consti.getId(), false);
					if(constituencyManagementDataVO != null)
						constituencyManagementRegionWiseOverviewVOList.add(constituencyManagementDataVO.getRegionWiseOverview());
				}
				
			}else if(regionType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				
			}
			
		}catch(Exception ex){
			log.error(" Exception Raised In Influencing People Retrieval :" + ex);
			ex.printStackTrace();
			ResultStatus rs = new ResultStatus();
			rs.setExceptionEncountered(ex);
			rs.setResultCode(ResultCodeMapper.FAILURE);
		}
		
	 return constituencyManagementRegionWiseOverviewVOList;
	}
}

