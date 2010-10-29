package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.InfluencingPeoplePosition;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
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
	
	
}

