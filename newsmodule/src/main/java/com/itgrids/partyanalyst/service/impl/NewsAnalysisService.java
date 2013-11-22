package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.hibernate.DesignationDAO;
import com.itgrids.partyanalyst.dao.hibernate.PartyDAO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Designation;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.INewsAnalysisService;

public class NewsAnalysisService implements INewsAnalysisService {
   
	private static final Logger LOG = Logger.getLogger(NewsAnalysisService.class);
	
	private TransactionTemplate transactionTemplate;	
	private DesignationDAO designationDAO;
	private PartyDAO partyDAO;
	
	
	
	
	
	/**
	 * @param partyDAO the partyDAO to set
	 */
	public void setPartyDAO(PartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	/**
	 * @param designationDAO the designationDAO to set
	 */
	public void setDesignationDAO(DesignationDAO designationDAO) {
		this.designationDAO = designationDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void analyseNewsWithSelectedParameters(AnalysisVO analysisVO){
		if(LOG.isInfoEnabled()){
			LOG.info("Enter in to analyseNewsWithSelectedParameters method ");
		}
		List<String> slectedFields = new ArrayList<String>();
		boolean sourcePresent = false;
		boolean destinationPresent = false;
		StringBuilder queryCandPart = new StringBuilder();
		StringBuilder queryPart = new StringBuilder();
		StringBuilder query = new StringBuilder();
		queryCandPart.append("select ");
		queryPart.append("select ");
		query.append("select ");
		if(sourcePresent && destinationPresent){
			
		}else if(sourcePresent){
			if(analysisVO.isDestCandPartyWise()){
			  query.append(" ");
			}
		}else if(destinationPresent){
			if(analysisVO.isSourceCandPartyWise())
			query.append(" ");
		}
	}
	
	public ResultStatus saveDesignationDetails(final String designationString)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try {
			LOG.debug("Entered into saveDesignationDetails method in NewsAnalysisService service");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					Long designationId = designationDAO.getDesignation(designationString);
					if(designationId == null)
					{
						Designation designation = new Designation();
						designation.setDesignation(designationString);
						designation = designationDAO.save(designation);
						if(designation != null)
						{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						
					}
					else
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					
					
				}
			});
		} catch (Exception e) {
			LOG.error("exception raised in saveDesignationDetails method in NewsAnalysisService service");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		return resultStatus;
	}
	
	public ResultStatus savePartyDetails(final String partyShortName,final String PartyLongName)
	{
		final ResultStatus resultStatus = new ResultStatus();
		try {
			LOG.debug("Entered into savePartyDetails method in NewsAnalysisService service");
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@SuppressWarnings("unused")
				public void doInTransactionWithoutResult(TransactionStatus status) {
					Long partyId = partyDAO.getPartyShortName(partyShortName);
					if(partyId == null)
					{
						Party party = new Party();
						party.setLongName(PartyLongName);
						party.setShortName(partyShortName);
						party.setIsNewsPortal("Y");
						partyDAO.save(party);
						if(party != null)
						{
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						
					}
					else
					{
						resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					
				}
			});
		} catch (Exception e) {
			LOG.error("exception raised in savePartyDetails method in NewsAnalysisService service");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		return resultStatus;
	}
}
