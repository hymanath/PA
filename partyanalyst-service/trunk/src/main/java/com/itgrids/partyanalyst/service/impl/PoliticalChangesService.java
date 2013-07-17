package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.IInformationSourceDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.hibernate.AssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.hibernate.LocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.hibernate.PoliticalChangesDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.InformationSource;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PoliticalChanges;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.utils.IConstants;

public class PoliticalChangesService implements IPoliticalChangesService {

	private final static Logger log =  Logger.getLogger(PoliticalChangesService.class);
	
	private TransactionTemplate transactionTemplate = null;
	private PoliticalChangesVO politicalChangesVO;
	private IUserDAO userDAO;
	private IPartyDAO partyDAO;
	private PoliticalChangesDAO politicalChangesDAO;
	private ProblemExternalSourceDAO problemExternalSourceDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ITownshipDAO townshipDAO;
	private IHamletDAO hamletDAO;
	private IInformationSourceDAO informationSourceDAO;
	private IBoothDAO boothDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
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

	public ITownshipDAO getTownshipDAO() {
		return townshipDAO;
	}

	public void setTownshipDAO(ITownshipDAO townshipDAO) {
		this.townshipDAO = townshipDAO;
	}

	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ProblemExternalSourceDAO getProblemExternalSourceDAO() {
		return problemExternalSourceDAO;
	}
	public void setProblemExternalSourceDAO(
			ProblemExternalSourceDAO problemExternalSourceDAO) {
		this.problemExternalSourceDAO = problemExternalSourceDAO;
	}
	public PoliticalChangesDAO getPoliticalChangesDAO() {
		return politicalChangesDAO;
	}
	public void setPoliticalChangesDAO(PoliticalChangesDAO politicalChangesDAO) {
		this.politicalChangesDAO = politicalChangesDAO;
	}
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}
	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	public PoliticalChangesVO getPoliticalChangesVO() {
		return politicalChangesVO;
	}
	public void setPoliticalChangesVO(PoliticalChangesVO politicalChangesVO) {
		this.politicalChangesVO = politicalChangesVO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}		
	
	public IInformationSourceDAO getInformationSourceDAO() {
		return informationSourceDAO;
	}

	public void setInformationSourceDAO(IInformationSourceDAO informationSourceDAO) {
		this.informationSourceDAO = informationSourceDAO;
	}
	
	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	/**
	 * This method get the Data from the User and saves the data into database.
	 * 
	 */
	public ResultStatus savePoliticalChangeDataReceivedFromUser(final PoliticalChangesVO politicalChangesVo, final String task){
		politicalChangesVO = politicalChangesVo;
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(log.isDebugEnabled()){
				log.debug("Entered in to savePoliticalChangeDataReceivedFromUser() method..");
			}					
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				PoliticalChanges politicalChanges = null;
					if(politicalChangesVo.getLocalPoliticalChangeId() != null && politicalChangesVo.getLocalPoliticalChangeId() > 0)
					{
						politicalChanges = politicalChangesDAO.get(politicalChangesVo.getLocalPoliticalChangeId());
					}
					else
					{
						politicalChanges = new PoliticalChanges();
					}
					politicalChanges.setTitle(politicalChangesVO.getTitle());
					if(politicalChangesVO.getDescription()!=null){
						politicalChanges.setDescription(politicalChangesVO.getDescription());	
					}									
					politicalChanges.setEffectedRange(politicalChangesVO.getRange());
					politicalChanges.setEffectedLocation(politicalChangesVO.getRangeId());				
					
					try {	
						if(!(politicalChangesVO.getReportedDate().equalsIgnoreCase(""))){
							politicalChanges.setIdentifiedDate(sdf.parse(politicalChangesVO.getReportedDate()));
						}
						if(!(politicalChangesVO.getOccuredDate().equalsIgnoreCase(""))){
							politicalChanges.setOccuredDate(sdf.parse(politicalChangesVO.getOccuredDate()));
						}						
						java.util.Date now = new java.util.Date();
				        String DATE_FORMAT = "dd/MM/yyyy";
				        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				        String strDateNew = sdf.format(now);				        
						politicalChanges.setUpdatedDate(sdf.parse(strDateNew));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					//politicalChanges.setSourceOfInformation(politicalChangesVO.getSourceOfInformation());
					politicalChanges.setParty(partyDAO.get(politicalChangesVO.getParty()));
					politicalChanges.setUser(userDAO.get(politicalChangesVO.getUserId()));
					InformationSource informationSource = informationSourceDAO.get(new Long(politicalChangesVO.getInformationSource())); 
					if(informationSource.getInformationSource().equalsIgnoreCase(IConstants.CALL_CENTER) || informationSource.getInformationSource().equalsIgnoreCase(IConstants.EXTERNAL_PERSON)){
						ProblemExternalSource problemExternalSource = new ProblemExternalSource();
						problemExternalSource.setName(politicalChangesVO.getName());
						problemExternalSource.setMobile(politicalChangesVO.getMobile());
						if(politicalChangesVO.getTelephoneNo()!=null){
							problemExternalSource.setTelePhone(politicalChangesVO.getTelephoneNo());
						}
						if(politicalChangesVO.getEmail()!=null){
							problemExternalSource.setEmail(politicalChangesVO.getEmail());
						}
						if(politicalChangesVO.getAddress()!=null){
							problemExternalSource.setAddress(politicalChangesVO.getAddress());
						}						
						politicalChanges.setExternalSource(problemExternalSource);
					}
					politicalChanges.setPoliticalChangesInformationSource(informationSource);
					politicalChanges = politicalChangesDAO.save(politicalChanges);
					
					/*if(task.equalsIgnoreCase(IConstants.EDIT)){
						PoliticalChanges editPoliticalChanges = new PoliticalChanges();						
						//editPoliticalChanges = politicalChangesDAO.get(politicalChangesVO.getLocalPoliticalChangeId());
						editPoliticalChanges.setIsDelete(IConstants.TRUE);
						editPoliticalChanges = politicalChangesDAO.save(editPoliticalChanges);
					}*/
			}});
			resultStatus.setResultPartial(false);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.debug("Exception raised.");
		}
		return resultStatus;
	}
	
	/**
	 *	This method retrieves all the political changes that are entered by the user,
	 *  and sets it in to a Value Object so that it can be used to display in the
	 *  ConsituencyManagement Page.
	 * */
	public List<PoliticalChangesVO> getAllPoliticalChanges(Long userId){
		List<PoliticalChangesVO> allPoliticalChanges = new ArrayList<PoliticalChangesVO>();		
		List localPoliticalChanges = politicalChangesDAO.getAllPoliticalChangesByUserId(userId);
		String locationName = null;
		Long locationId;
		try{			
			for(int i=0;i<localPoliticalChanges.size();i++){
				Object[] parms = (Object[])localPoliticalChanges.get(i);
				PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();	
				politicalChangesVO.setLocalPoliticalChangeId(Long.parseLong(parms[8].toString()));
				politicalChangesVO.setTitle(parms[0]!=null?parms[0].toString():"");
				politicalChangesVO.setDescription(parms[1]!=null?parms[1].toString():"");
				if(parms[2]!=null){
			        SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			        String strDateNew = sdf.format((Date)parms[2]);
			        politicalChangesVO.setOccuredDate(strDateNew);	
			        
				}else{
					politicalChangesVO.setOccuredDate("");
				}
				if(parms[9]!=null){
			        SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			        String identifiedDate = sdf.format((Date)parms[9]);
			        politicalChangesVO.setIdentifiedDate(identifiedDate);
				}else{
					politicalChangesVO.setIdentifiedDate("");
				}
				politicalChangesVO.setPartyName(parms[10].toString());				
				politicalChangesVO.setRange(parms[11]!=null?parms[11].toString():"");
				locationId = Long.parseLong(parms[12].toString());
				if(parms[11]!=null){
					if(parms[11].toString().equalsIgnoreCase(IConstants.STATE_LEVEL)){
						locationName = stateDAO.get(locationId).getStateName();
					}else if(parms[11].toString().equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
						locationName = districtDAO.get(locationId).getDistrictName();
					}else if(parms[11].toString().equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
						locationName = constituencyDAO.get(locationId).getName();
					}else if(parms[11].toString().equalsIgnoreCase(IConstants.TEHSIL)){
						locationName = tehsilDAO.get(locationId).getTehsilName();
					}else if(parms[11].toString().equalsIgnoreCase(IConstants.VILLAGE)){
						locationName = townshipDAO.get(locationId).getTownshipName();
					}else if(parms[11].toString().equalsIgnoreCase(IConstants.HAMLET_LEVEL)){
						locationName = hamletDAO.get(locationId).getHamletName();
					}
					else if(parms[11].toString().equalsIgnoreCase(IConstants.BOOTH))
					{
						locationName = "BOOTH - "+ boothDAO.get(locationId).getPartNo();
					}
					else if(parms[11].toString().equalsIgnoreCase(IConstants.WARD))
					{
						locationName = constituencyDAO.get(locationId).getName();
					}
					else if(parms[11].toString().equalsIgnoreCase(IConstants.MUNICIPAL_CORP_GMC))
					{
						Long localBodyId = assemblyLocalElectionBodyDAO.get(locationId).getLocalElectionBody().getLocalElectionBodyId();
						locationName = localElectionBodyDAO.get(localBodyId).getName() + "Muncipality";
					}
				}
				politicalChangesVO.setLocationName(locationName);
				politicalChangesVO.setRangeId(parms[12]!=null?Long.parseLong(parms[12].toString()):0l);				
				politicalChangesVO.setName(parms[3]!=null?parms[3].toString():"");
				politicalChangesVO.setSelectedPersonId(parms[5]!=null?Long.parseLong(parms[5].toString()):0l);
				politicalChangesVO.setSourceOfInformation(parms[7]!=null?parms[7].toString():"");
				allPoliticalChanges.add(politicalChangesVO);
			}	
			return allPoliticalChanges;		
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;
		}		
	}
	/**
	 * This method removes a record from user when he wants to remove a political change.
	 * 
	 * @param userId
	 * @param politicalChangeId
	 */
	public void deltePoliticalChangeBasedOnUser(final Long politicalChangeId){
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
						PoliticalChanges politicalChanges = new PoliticalChanges();
						politicalChanges = politicalChangesDAO.get(politicalChangeId);
						politicalChanges.setIsDelete(IConstants.TRUE);
						politicalChanges = politicalChangesDAO.save(politicalChanges);
				}});
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
		}
		
	}	
	
	/**
	 * This method gets details of the external person and sets it in to a ValueObject and it 
	 * is passed to action class for proper displaying of data.
	 * 
	 * @param politicalChangeId
	 * @return
	 */
	public PoliticalChangesVO getExternalPersonDetails(Long politicalChangeId){
		try{
			List<ProblemExternalSource> result = problemExternalSourceDAO.getExternalPersonDetails(politicalChangeId);
			PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
			for(ProblemExternalSource resultIterator : result){
				politicalChangesVO.setName(resultIterator.getName());
				politicalChangesVO.setMobile(resultIterator.getMobile());
				politicalChangesVO.setTelephoneNo(resultIterator.getTelePhone());
				politicalChangesVO.setAddress(resultIterator.getAddress());
				politicalChangesVO.setEmail(resultIterator.getEmail());
			}
			return politicalChangesVO;
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;	
		}			
	}
	
	/**
	 * This method retrives all the data by politicalChangeId and sets them in PoliticalChangesVO.
	 * @param politicalChangeId
	 * @return
	 */
	public PoliticalChangesVO getDetailsBylocalPoliticalChangeId(Long politicalChangeId){
		try{
			PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();
			String range;
			Long rangeId;
			List localPoliticalChanges = politicalChangesDAO.getAllPoliticalChangesByPoliticalChangeId(politicalChangeId);
			for(int i=0;i<localPoliticalChanges.size();i++){
				Object[] parms = (Object[])localPoliticalChanges.get(i);
				politicalChangesVO.setTitle(parms[0]!=null?parms[0].toString():"");
				politicalChangesVO.setDescription(parms[1]!=null?parms[1].toString():"");
				if(parms[2]!=null){
			        SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);			       	
			        String identifiedDate = sdf.format((Date)parms[2]);
			        politicalChangesVO.setReportedDate(identifiedDate);
				}else{
					politicalChangesVO.setReportedDate("");
				}
				if(parms[3]!=null){
			        SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			        String strDateNew = sdf.format((Date)parms[3]);
			        politicalChangesVO.setOccuredDate(strDateNew);		
				}else{
					politicalChangesVO.setOccuredDate("");
				}
				politicalChangesVO.setLocalPoliticalChangeId(politicalChangeId);
				politicalChangesVO.setPartyName(parms[5]!=null?parms[5].toString():"");
				List<Party> parties = partyDAO.findByShortName(parms[5].toString());
				politicalChangesVO.setParty(parties.get(0).getPartyId());
				range = parms[6].toString();
				rangeId = new Long(parms[7].toString());
								
				if(range.equalsIgnoreCase(IConstants.STATE_LEVEL)){
					politicalChangesVO.setEffectRange("2");
					politicalChangesVO.setLocationName(stateDAO.get(rangeId).getStateName());
				}else if(range.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
					politicalChangesVO.setEffectRange("3");
					politicalChangesVO.setLocationName(districtDAO.get(rangeId).getDistrictName());
				}else if(range.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
					politicalChangesVO.setEffectRange("4");
					politicalChangesVO.setLocationName(constituencyDAO.get(rangeId).getName());
				}else if(range.equalsIgnoreCase(IConstants.TEHSIL)){
					politicalChangesVO.setEffectRange("5");
					politicalChangesVO.setLocationName(tehsilDAO.get(rangeId).getTehsilName());
				}else if(range.equalsIgnoreCase(IConstants.VILLAGE)){
					politicalChangesVO.setEffectRange("6");
					politicalChangesVO.setLocationName(townshipDAO.get(rangeId).getTownshipName());
				}else if(range.equalsIgnoreCase(IConstants.MUNICIPAL_CORP_GMC)){
					politicalChangesVO.setEffectRange("7");
					Long localBodyId = assemblyLocalElectionBodyDAO.get(rangeId).getLocalElectionBody().getLocalElectionBodyId();
					politicalChangesVO.setLocationName(localElectionBodyDAO.get(localBodyId).getName() + "Muncipality");
					
				}
				else if(range.equalsIgnoreCase(IConstants.WARD))
				{
					politicalChangesVO.setEffectRange("8");
					politicalChangesVO.setLocationName(constituencyDAO.get(rangeId).getName());
				}
				else if(range.equalsIgnoreCase(IConstants.BOOTH))
				{
					politicalChangesVO.setEffectRange("9");
					politicalChangesVO.setLocationName("BOOTH - "+ boothDAO.get(rangeId).getPartNo());
				}
				
				politicalChangesVO.setRange(parms[6]!=null?parms[6].toString():"");
				politicalChangesVO.setRangeId(parms[7]!=null?Long.parseLong(parms[7].toString()):0l);
				
				politicalChangesVO.setInformationSource(parms[8].toString());
				politicalChangesVO.setSourceOfInformation(parms[4].toString());
					if(parms[4].toString().equalsIgnoreCase(IConstants.EXTERNAL_PERSON) || parms[4].toString().equalsIgnoreCase(IConstants.CALL_CENTER)){
						List<ProblemExternalSource> result = problemExternalSourceDAO.getExternalPersonDetails(politicalChangeId);
						for(ProblemExternalSource resultIterator : result){
							politicalChangesVO.setName(resultIterator.getName());
							politicalChangesVO.setMobile(resultIterator.getMobile());
							politicalChangesVO.setTelephoneNo(resultIterator.getTelePhone());
							politicalChangesVO.setAddress(resultIterator.getAddress());
							politicalChangesVO.setEmail(resultIterator.getEmail());
						}
					}
					
				
			}	
			return politicalChangesVO;
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
			return null;	
		}	
	}
}
