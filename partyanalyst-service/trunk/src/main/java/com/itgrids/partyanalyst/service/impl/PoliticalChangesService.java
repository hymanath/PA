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

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.hibernate.PoliticalChangesDAO;
import com.itgrids.partyanalyst.dao.hibernate.PoliticalChangesInformationSourceDAO;
import com.itgrids.partyanalyst.dao.hibernate.ProblemExternalSourceDAO;
import com.itgrids.partyanalyst.dao.hibernate.RegistrationDAO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.PoliticalChanges;
import com.itgrids.partyanalyst.model.PoliticalChangesInformationSource;
import com.itgrids.partyanalyst.model.ProblemExternalSource;
import com.itgrids.partyanalyst.service.IPoliticalChangesService;
import com.itgrids.partyanalyst.utils.IConstants;

public class PoliticalChangesService implements IPoliticalChangesService {

	private final static Logger log =  Logger.getLogger(PoliticalChangesService.class);
	
	private TransactionTemplate transactionTemplate = null;
	private PoliticalChangesVO politicalChangesVO;
	private RegistrationDAO registrationDAO;
	private PoliticalChangesInformationSourceDAO politicalChangesInformationSourceDAO;
	private IPartyDAO partyDAO;
	private PoliticalChangesDAO politicalChangesDAO;
	private ProblemExternalSourceDAO problemExternalSourceDAO;
	
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	
	
	public RegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}
	public void setRegistrationDAO(RegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
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
	public PoliticalChangesInformationSourceDAO getPoliticalChangesInformationSourceDAO() {
		return politicalChangesInformationSourceDAO;
	}
	public void setPoliticalChangesInformationSourceDAO(
			PoliticalChangesInformationSourceDAO politicalChangesInformationSourceDAO) {
		this.politicalChangesInformationSourceDAO = politicalChangesInformationSourceDAO;
	}
	
	/**
	 * This method returns all types of Information Sources (which provide information for the user
	 *  about the political changes that are happening).This method is used in registration of political changes
	 *  in ConstituencyManagementPage. 
	 */
	public List<SelectOptionVO> getAllPoliticalInformationSources() {
		List<SelectOptionVO> sourcesIdsAndNames = new ArrayList<SelectOptionVO>();
		List<PoliticalChangesInformationSource> source;
		try{
			source = politicalChangesInformationSourceDAO.getAll();		
			if(source.size()!=0){
				for(PoliticalChangesInformationSource sources : source){
					SelectOptionVO selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId(sources.getSourceId());
					selectOptionVO.setName(sources.getSourceName());
					sourcesIdsAndNames.add(selectOptionVO);
				}
				return sourcesIdsAndNames;
			}else{
				throw new Exception("Data Not Available");
			}
			
		}catch(Exception e){
			log.debug(e);
			e.printStackTrace();			
			return null;
		}
	}
	
	/**
	 * This method get the Data from the User and saves the data into database.
	 * 
	 */
	public void savePoliticalChangeDataReceivedFromUser(PoliticalChangesVO politicalChangesVo){
		politicalChangesVO = politicalChangesVo;
		try{
			if(log.isDebugEnabled()){
				log.debug("Entered in to savePoliticalChangeDataReceivedFromUser() method..");
			}					
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
					PoliticalChanges politicalChanges = new PoliticalChanges();
					politicalChanges.setTitle(politicalChangesVO.getTitle());
					politicalChanges.setDescription(politicalChangesVO.getDescription());
					try {						
						politicalChanges.setIdentifiedDate(sdf.parse(politicalChangesVO.getReportedDate()));
						politicalChanges.setOccuredDate(sdf.parse(politicalChangesVO.getDate()));
						java.util.Date now = new java.util.Date();
				        String DATE_FORMAT = "dd/MM/yyyy";
				        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
				        String strDateNew = sdf.format(now);				        
						politicalChanges.setUpdatedDate(sdf.parse(strDateNew));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					politicalChanges.setSourceOfInformation(politicalChangesVO.getSourceOfInformation());
					politicalChanges.setParty(partyDAO.get(politicalChangesVO.getPartyId()));
					
					if(politicalChangesInformationSourceDAO.get(politicalChangesVO.getSelectedPersonId()).getSourceName()==IConstants.USER){
						politicalChanges.setRegistration(registrationDAO.get(politicalChangesVO.getUserId()));
					}else{
						politicalChanges.setRegistration(registrationDAO.get(politicalChangesVO.getUserId()));
						politicalChanges.setPoliticalChangesInformationSource(politicalChangesInformationSourceDAO.get(politicalChangesVO.getSelectedPersonId()));						
						ProblemExternalSource problemExternalSource = new ProblemExternalSource();
						problemExternalSource.setName(politicalChangesVO.getName());
						problemExternalSource.setMobile(politicalChangesVO.getMobile());
						problemExternalSource.setTelePhone(politicalChangesVO.getTelephoneNo());
						problemExternalSource.setEmail(politicalChangesVO.getEmail());
						problemExternalSource.setAddress(politicalChangesVO.getAddress());
						politicalChanges.setExternalSource(problemExternalSource);
					}		
					politicalChanges = politicalChangesDAO.save(politicalChanges);
					
					if(politicalChangesVO.getSaveType().equalsIgnoreCase(IConstants.EDIT)){
						PoliticalChanges editPoliticalChanges = new PoliticalChanges();						
						editPoliticalChanges = politicalChangesDAO.get(politicalChangesVO.getPoliticalChangeId());
						editPoliticalChanges.setIsDelete(IConstants.TRUE);
						editPoliticalChanges = politicalChangesDAO.save(editPoliticalChanges);
					}
			}});
		}catch(Exception e){
			e.printStackTrace();
			log.debug("Exception raised.");
		}
	}
	
	/**
	 *	This method retrieves all the political changes that are entered by the user,
	 *  and sets it in to a Value Object so that it can be used to display in the
	 *  ConsituencyManagement Page.
	 * */
	public List<PoliticalChangesVO> getAllPoliticalChanges(Long userId){
		List<PoliticalChangesVO> allPoliticalChanges = new ArrayList<PoliticalChangesVO>();		
		List localPoliticalChanges = politicalChangesDAO.getAllPoliticalChangesByUserId(userId);
		try{			
			for(int i=0;i<localPoliticalChanges.size();i++){
				Object[] parms = (Object[])localPoliticalChanges.get(i);
				PoliticalChangesVO politicalChangesVO = new PoliticalChangesVO();	
				politicalChangesVO.setPoliticalChangeId(Long.parseLong(parms[8].toString()));
				politicalChangesVO.setTitle(parms[0]!=null?parms[0].toString():"");
				politicalChangesVO.setDescription(parms[1]!=null?parms[1].toString():"");
				if(parms[2]!=null){
			        SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			        String strDateNew = sdf.format((Date)parms[2]);
			        politicalChangesVO.setDate(strDateNew);		
			        String identifiedDate = sdf.format((Date)parms[9]);
			        politicalChangesVO.setIdentifiedDate(identifiedDate);
				}else{
					politicalChangesVO.setDate("");
				}
				politicalChangesVO.setPartyName(parms[10].toString());
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

}
